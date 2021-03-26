/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import pidev.entities.Panier;
import pidev.entities.Payment;
import pidev.tools.MyConnection;
import java.util.Date;
import java.time.ZoneId;
import javafx.scene.control.Alert;

/**
 *
 * @author Occurence
 */
public class PanierCRUD {
    private Connection cnx;
    private PreparedStatement ste;

    public PanierCRUD() {
        cnx = MyConnection.getInstance().getConnection();
    }
    
    public void Ajouter(Panier pan){
        String sql = "INSERT INTO paniers (ID_User, Date_C, Status_Panier) VALUES( ?, ?, ?)";
        try {
            ste = cnx.prepareStatement(sql);
            ste.setInt(1, pan.getID_User());
            ste.setObject(2, pan.getDate_C());
            ste.setString(3, pan.getStatus_panier());
            ste.executeUpdate();
            System.out.println("Panier ajoutée");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("L'ID de l'utilisateur n'exist pas !");

            alert.showAndWait();
        }
    }
    
    
    public List<Panier> AfficherPanier() throws SQLException {
        String query = "SELECT * FROM paniers";
        ste = cnx.prepareStatement(query);
        
        ResultSet result = ste.executeQuery(query);
            
        List<Panier> paniers = new ArrayList<>();
        while(result.next()){
            Panier p = new Panier();
            p.setID_Panier(result.getInt("ID_Panier"));
            p.setID_User(result.getInt("ID_User"));
            p.setDate_C(result.getDate("Date_C").toLocalDate());
            p.setDate_U(result.getDate("Date_U").toLocalDate());
            p.setStatus_panier(result.getString("Status_panier"));
            
            
            paniers.add(p);
        }
       

        return paniers;
    }
    
    
    public void Modifier(Panier pan){
      
        String sql = "UPDATE paniers SET ID_User = ?, Status_Panier = ? WHERE ID_Panier = ?";
        try {
            ste = cnx.prepareStatement(sql);
            ste.setInt(1, pan.getID_User());
            ste.setString(2, pan.getStatus_panier());
            ste.setInt(3, pan.getID_Panier());
            ste.executeUpdate();
            System.out.println("Panier Modifier");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    public void Supprimer(int ID){
        
        String sql = "DELETE FROM paniers WHERE ID_Panier = ?";
        try {
            ste = cnx.prepareStatement(sql);
            ste.setInt(1, ID);
            
            ste.executeUpdate();
            System.out.println("Panier Supprimer");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
        }
    }
    
    
    public List<Panier> verifPanier(int ID_user) throws SQLException{
        String query = "SELECT * FROM paniers WHERE ID_User = ? AND Status_panier LIKE 'En cours'";
        List<Panier> paniers = new ArrayList<>();
        try {
            ste = cnx.prepareStatement(query);
            ste.setInt(1, ID_user);
            
            ResultSet result = ste.executeQuery();
            
            
            while(result.next()){
                Panier p = new Panier();
                p.setID_Panier(result.getInt("ID_Panier"));
                p.setID_User(result.getInt("ID_User"));
                p.setDate_C(result.getDate("Date_C").toLocalDate());
                p.setDate_U(result.getDate("Date_U").toLocalDate());
                p.setStatus_panier(result.getString("Status_panier"));


                paniers.add(p);
            }
            
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
        }
        
        return paniers;    
   
    }
    
    
    public List<Panier> getPanier(int ID_user) throws SQLException{
        String query = "SELECT * FROM paniers WHERE ID_User = ? AND Status_panier LIKE 'En cours'";
        List<Panier> paniers = new ArrayList<>();
        try {
            ste = cnx.prepareStatement(query);
            ste.setInt(1, ID_user);
            
            ResultSet result = ste.executeQuery();
            
            
            if(result.next()){
                Panier p = new Panier();
                p.setID_Panier(result.getInt("ID_Panier"));
                p.setID_User(result.getInt("ID_User"));
                p.setDate_C(result.getDate("Date_C").toLocalDate());
                p.setDate_U(result.getDate("Date_U").toLocalDate());
                p.setStatus_panier(result.getString("Status_panier"));


                paniers.add(p);
            }
            else{
                Panier p = new Panier();
                paniers.add(p);
            }
            
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
        }
        
        return paniers;    
   
    }
}
