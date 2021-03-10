/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Reservationconsultation;
import UtilsBD.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.scene.control.Alert;

/**
 *
 * @author foura
 */
public class servicereservation {

    private Connection con;
    private Statement ste;
    public static int k = 0;

    public servicereservation() {
        DataBase db = new DataBase();
        con = db.getConnection();

    }

    public void ajouter1(Reservationconsultation p) {
        PreparedStatement pre;
        k = 0;
        try {
            pre = con.prepareStatement("INSERT INTO  reservation(idconsultation,idclient, date,type,heure,image) VALUES (?,?,?,?,?,?);");
            pre.setInt(1, p.getIdconsultation());
            pre.setInt(2, p.getIdclient());
            pre.setDate(3, new java.sql.Date(p.getDate().getTime()));
            pre.setString(4, p.getType());
            pre.setString(5, p.getHeure());
            pre.setString(6, p.getImage());

            pre.executeUpdate();
            System.out.println("reservation ajouté");
        } catch (SQLException ex) {
            System.out.println(ex);
            k = 1;

            System.out.println("problem");
        }

    }

    public void update(Reservationconsultation p) {
        PreparedStatement pre;
        k=0;
        try {
            pre = con.prepareStatement("UPDATE reservation SET idconsultation=?,idclient=?,date=?,type=?,heure=? WHERE idreservation=? ;");
            pre.setInt(1, p.getIdconsultation());
            pre.setInt(2, p.getIdclient());
            pre.setDate(3, new java.sql.Date(p.getDate().getTime()));
            pre.setString(4, p.getType());
            pre.setString(5, p.getHeure());

            pre.setInt(6, p.getIdreservation());

            pre.executeUpdate();
            System.out.println("reservation modifié");
        } catch (SQLException ex) {
            System.out.println(ex);
            k = 2;
            System.out.println("problem");
        }
    }

    public void updateetat(Reservationconsultation p) {
        PreparedStatement pre;
        try {
            pre = con.prepareStatement("UPDATE reservation SET etat=? WHERE idreservation=? ;");

            pre.setString(1, p.getEtat());

            pre.setInt(2, p.getIdreservation());

            pre.executeUpdate();
            System.out.println("etat modifié");
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("problem");
        }

    }
 
    public void delete(Reservationconsultation p) {
        try {
            PreparedStatement pst;
            
            String req = "DELETE FROM `reservation` WHERE `reservation`.`idreservation` = " + p.getIdreservation() + "";
            pst = con.prepareStatement(req);
            pst.execute();
            System.out.println("test delete "+pst.execute());
        } catch (SQLException ex) {
            Logger.getLogger(servicereservation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int calculnb(int id) {

        PreparedStatement pre;
        int count = 19;
        try {
            Statement stmt = con.createStatement();

            String query = "SELECT COUNT(*) FROM reservation WHERE idclient='"+id+"'";

            ResultSet rs = stmt.executeQuery(query);

            rs.next();
            count = rs.getInt(1);
            return count;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;

    }
   public int calculnbnonvalideclient(int id) {

        PreparedStatement pre;
        int count = 19;
        try {
            Statement stmt = con.createStatement();

            String query = "SELECT COUNT(*) FROM reservation WHERE etat like 'En attente de confirmation' and idclient='"+id+"' ";

            ResultSet rs = stmt.executeQuery(query);

            rs.next();
            count = rs.getInt(1);
            return count;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;

   }
    public int calculnbnonvalide() {

        PreparedStatement pre;
        int count = 19;
        try {
            Statement stmt = con.createStatement();

            String query = "SELECT COUNT(*) FROM reservation WHERE etat like 'En attente de confirmation' ";

            ResultSet rs = stmt.executeQuery(query);

            rs.next();
            count = rs.getInt(1);
            return count;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;

    }

    public int confirmation() {

        PreparedStatement pre;
        int count = 19;
        try {
            Statement stmt = con.createStatement();

            String query = "SELECT COUNT(*) FROM reservation WHERE etat like 'En attente de confirmation'";

            ResultSet rs = stmt.executeQuery(query);

            rs.next();
            count = rs.getInt(1);
            return count;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;

    }

    public int calculnbdate(String date) {
        int y = 0;
        PreparedStatement pre;
        int count = 19;
        try {
            Statement stmt = con.createStatement();

            String query = "SELECT COUNT(*) FROM reservation WHERE date='" + date + "'";

            ResultSet rs = stmt.executeQuery(query);

            rs.next();
            count = rs.getInt(1);
            return count;
        } catch (SQLException ex) {
            System.out.println(ex);
            return 1;

        }

    }

    public List<Reservationconsultation> Listreservation() {
        List<Reservationconsultation> Mylist = new ArrayList<>();
        try {
            String requete = "select * from reservation ";
            PreparedStatement pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                /*     p.setId(rs.getInt("id));
                p.setId(rs.getInt("id"));*/

                Reservationconsultation p = new Reservationconsultation();
                p.setIdreservation(rs.getInt("idreservation"));

                p.setIdconsultation(rs.getInt("idconsultation"));
                p.setIdclient(rs.getInt("idclient"));

                p.setDate(rs.getDate("date"));

                p.setType(rs.getString("type"));

                p.setHeure(rs.getString("heure"));
                p.setImage(rs.getString("image"));
                p.setEtat(rs.getString("etat"));
                Mylist.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }
        public List<Reservationconsultation> Listreservationclient(int id) {
        List<Reservationconsultation> Mylist = new ArrayList<>();
        try {
            String requete = "select * from reservation where idclient='"+id+"'";
            PreparedStatement pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                /*     p.setId(rs.getInt("id));
                p.setId(rs.getInt("id"));*/

                Reservationconsultation p = new Reservationconsultation();
                p.setIdreservation(rs.getInt("idreservation"));

                p.setIdconsultation(rs.getInt("idconsultation"));
                p.setIdclient(rs.getInt("idclient"));

                p.setDate(rs.getDate("date"));

                p.setType(rs.getString("type"));

                p.setHeure(rs.getString("heure"));
                p.setImage(rs.getString("image"));
                p.setEtat(rs.getString("etat"));
                Mylist.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }

    public Map<Integer, Integer> calcullnb() {
        Map<Integer, Integer> arr = new HashMap<>();
        try {
            String requete = "SELECT (SELECT count(idreservation) FROM `reservation` WHERE etat='En attente de confirmation') as nbEnattent ,count(idreservation) totale FROM `reservation`";
            PreparedStatement pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int reservationEnattente = rs.getInt("nbEnattent");
                int totale = rs.getInt("totale");
                arr.put(reservationEnattente, totale);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return arr;
    }
    // SELECT nom,COUNT(reservation.idclient) nb FROM `user` INNER JOIN reservation on user.cin=reservation.idclient where etat="Confirmé" ;

    public Map<String, Float> calcullnb2(int id) {
        Map<String, Float> arr = new HashMap<>();

        try {
            String requete = "SELECT nom,COUNT(reservation.idclient) as nb ,(select count(idreservation) from reservation) totale FROM `user` INNER JOIN reservation on user.cin=reservation.idclient where etat='Confirmé' and reservation.idclient='" + id + "'";
            PreparedStatement pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String client = rs.getString("nom");
                System.out.println("nb:"+ rs.getInt("nb"));
                System.out.println("totale:"+ rs.getInt("totale"));
               
              
               
                int f=rs.getInt("nb"),y=rs.getInt("totale");
                 float nombre =(float) f/y;
                       
                System.out.println("res"+(float) nombre);
                arr.put(client, nombre);
               
               
               
            }
           

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return arr;
    }
   public List<Integer> allUsers() {
        List<Integer> arr = new ArrayList<>();
        try {
            String requete = "select cin from user";
            PreparedStatement pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int cin = rs.getInt("cin");
                arr.add(cin);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return arr;
    }
}
/*
SELECT cin FROM `user` 

@FXML
    private void select(MouseEvent event) throws SQLException {
        
        nom_club.setText(tab.getSelectionModel().getSelectedItem().getKey());
        nbr_evenement_organiser.setText(tab.getSelectionModel().getSelectedItem().getValue());
        int x = Integer.parseInt(tab.getSelectionModel().getSelectedItem().getValue());
        int y = evs.nbr_evenementTotale();
        float pr = ((float) x / y) * 100;
        pourcentage.setText(""+pr+"%");
        
    }
*/
