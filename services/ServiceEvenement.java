/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import utilis.DataBase;
import entities.Evenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author user
 */
public class ServiceEvenement {
    
    private Connection con;
    private Statement ste;

    public ServiceEvenement() {
        con = DataBase.getInstance().getConnection();

    }


    public void ajouter1(Evenement e) 
    {
    PreparedStatement pre;
        try {
            pre = con.prepareStatement("INSERT INTO `evenement` ( `id_organisateur`, `type`,`titre`,`description`,`lieu`,`date_event`,`image`) VALUES ( ?, ?, ?, ?, ?, ?,?);");
            pre.setInt(1, e.getId_organisateur());
            pre.setString(2, e.getType());
            pre.setString(3, e.getTitre());
            pre.setString(4, e.getDescription());
            pre.setString(5, e.getLieu());
            pre.setDate(6, new java.sql.Date(e.getDate_event().getTime()));
            pre.setString(7, e.getImage());

    pre.executeUpdate();
        System.out.println("evenement ajouté");
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("problem");
        }
 
    }
public void ModifierEvenement (Evenement e) {
    
        try {
            String requete = "update evenement set id_organisateur=?,type=?,titre=?,description=?,lieu=?,date_event=?,image=? where ? = id";
            PreparedStatement pre = con.prepareStatement(requete);
            pre.setInt(1, e.getId_organisateur());
            pre.setString(2, e.getType());
            pre.setString(3, e.getTitre());
            pre.setString(4, e.getDescription());
            pre.setString(5, e.getLieu());
            pre.setDate(6, new java.sql.Date(e.getDate_event().getTime()));
            pre.setString(7,e.getImage());
            pre.setInt(8,e.getId());

            pre.executeUpdate();
            System.out.println("evenement Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void SupprimerEvenement(Evenement cl) {
        try {
            String requete = "delete from evenement where ? = id";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, cl.getId());
            pst.executeUpdate();
            System.out.println("Evenement Deleted !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    public List<Evenement> ListClasse() {
        List<Evenement> Mylist = new ArrayList<>();
        try {
            String requete = "select * from evenement";
            PreparedStatement pst = con.prepareStatement(requete);
            ResultSet e = pst.executeQuery();
            
            while (e.next()) {
                Evenement pre = new Evenement();
               
            pre.setId(e.getInt("id"));
            pre.setId_organisateur(e.getInt("id_organisateur")); 
            pre.setType(e.getString("type"));
            pre.setTitre( e.getString("titre"));
            pre.setDescription( e.getString("description"));
            pre.setLieu( e.getString("lieu"));
            pre.setDate_event(e.getDate("date_event"));
//            ImageView emp0photo = new ImageView(new Image(this.getClass().getResourceAsStream(e.getString("image"))));
            pre.setImage(e.getString("image"));
//            pre.setImage(e.getString("image"));
           
           
                Mylist.add(pre);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }
    

//    public List<Evenement> readAll() throws SQLException {
//    List<Evenement> arr=new ArrayList<>();
//    ste=con.createStatement();
//    ResultSet rs=ste.executeQuery("select * from evenement");
//     while (rs.next()) {                
//               int id=rs.getInt(1);
//               int id_organisateur= rs.getInt(2);
//               String type =rs.getString(3);
//               String titre =rs.getString(4);
//               String lieu=rs.getString(5);
//               String description= rs.getString(6);       
//               Date date_event= rs.getDate(7);     
//                       
//               Evenement p=new Evenement(id, id_organisateur,type,titre,lieu,description, date_event);
//
//     arr.add(p);
//     }
//    return arr;
//    }


}
