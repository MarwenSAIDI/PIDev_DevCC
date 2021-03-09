/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import utilis.DataBase;
import entities.Evenement;
import entities.ReservationEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.ImageIcon;
import utilis.Copy;
import utilis.sqlexcept;


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

  public int count_nbplace(Evenement e) throws SQLException
    {
         String requete = "SELECT SUM(nb_place) AS nbplace_total FROM reservation_event WHERE ? =id_event";
            PreparedStatement p = con.prepareStatement(requete);
            p.setInt(1, e.getId());
            
            int result=0;

            ResultSet ee = p.executeQuery();
            ee.next();
            
            result=ee.getInt(1);
            ee.close();
            p.close();
            
            return result;
    }
  
    public void ajouter1(Evenement e) throws IOException,sqlexcept
    {
    PreparedStatement pre;
        try {
           
            pre = con.prepareStatement("INSERT INTO `evenement` ( `id_organisateur`, `type`,`titre`,`description`,`lieu`,`date_event`,`image`,`tarif`,`capacite`,`nb_reservation`,`etat`) VALUES ( ?,?, ?, ?, ?, ?, ?,?, ?, ?,?);");
            pre.setInt(1, e.getId_organisateur());
            pre.setString(2, e.getType());
            pre.setString(3, e.getTitre());
            pre.setString(4, e.getDescription());
            pre.setString(5, e.getLieu());
            pre.setDate(6, new java.sql.Date(e.getDate_event().getTime()));
           // ajout image, avec un id unique    
           
//            String img = e.getImage();
//            UUID u = UUID.randomUUID();
//            String old = img;
//            String extension = img.substring(img.lastIndexOf("."));
//           img = img.substring(img.lastIndexOf("\\")+1,img.lastIndexOf("."));
//           img = img + u.toString() + extension;  
            // fin ajout image
            pre.setString(7, e.getImage());
            pre.setFloat(8, e.getTarif());
            pre.setInt(9, e.getCapacite());
            pre.setInt(10, e.getNb_reservation());
            pre.setString(11, "en cours");

             //deplacement vers le dossier du serveur web
//            File source = new File(old);
//            File dest = new File("C:\\Users\\user\\Desktop\\pi dev\\CrudPI\\src\\imgEvent\\"+img);
//         
//        Copy.copyFileUsingStream(source,dest);
    pre.executeUpdate();
        System.out.println("evenement ajouté");
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("problem");
        }
 
    }
public void ModifierEvenement (Evenement e) {
    
        try {
            String requete = "update evenement set id_organisateur=?,type=?,titre=?,description=?,lieu=?,date_event=?,image=?,tarif=?,capacite=? where ? = id";
            PreparedStatement pre = con.prepareStatement(requete);
            pre.setInt(1, e.getId_organisateur());
            pre.setString(2, e.getType());
            pre.setString(3, e.getTitre());
            pre.setString(4, e.getDescription());
            pre.setString(5, e.getLieu());
            pre.setDate(6, new java.sql.Date(e.getDate_event().getTime()));
            pre.setString(7,e.getImage());
            pre.setFloat(8,e.getTarif());
            
            pre.setInt(9, e.getCapacite());
            pre.setInt(10,e.getId());

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
    
        public void modifier_etat(Evenement r) 
    {

             try {
         String requete = "update `evenement`  set etat='effectue' where (? = id and  (DATEDIFF( ?,NOW()) )<=0 )";
            PreparedStatement pre = con.prepareStatement(requete);
                       pre.setInt(1, r.getId());
                       pre.setDate(2, (java.sql.Date) r.getDate_event());
            pre.executeUpdate();
            System.out.println("etat modifier !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
    }
        
         public void modifier_nb_reservation(Evenement r,int nbr) 
    {

             try {
         String requete = "update `evenement`  set nb_reservation=? where ? = id ";
            PreparedStatement pre = con.prepareStatement(requete);
            pre.setInt(1,nbr);           
            pre.setInt(2, r.getId());
            pre.executeUpdate();
            System.out.println("etat modifier !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
    }
//        public int capcite_event()(Evenement e)
//        {
//              String requete = "SELECT capacite FROM `evenement`  order by id desc";
//            PreparedStatement pst = con.prepareStatement(requete);
//            ResultSet e = pst.executeQuery();
//            
//            while (e.next()) {
//        }
    
    public List<Evenement> ListClasse() {
        List<Evenement> Mylist = new ArrayList<>();
        try {
            String requete = "SELECT now(),`id`,`id_organisateur`,`type`,`titre`,`description`,`lieu`,`date_event`,`image`,`tarif`,`capacite`,`nb_reservation`,`etat` FROM `evenement`  order by etat desc";
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
            pre.setImage(e.getString("image"));
            pre.setTarif(e.getFloat("tarif"));
            pre.setCapacite(e.getInt("capacite"));
            pre.setEtat(e.getString("etat"));
             
                int reserv = count_nbplace(pre);
                
            pre.setNb_reservation(reserv);
            modifier_nb_reservation(pre,reserv);

 
            Date date_event= e.getDate("date_event");
            Date jour = e.getDate("now()");

            if(jour.before(date_event)){
                System.out.println(
                    "Date aujourd'hui is before date event");
                pre.setEtat("en cours ");
            } 
            else {
                
                  modifier_etat(pre);
            
            
                  pre.setEtat("effectue");

            }
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
