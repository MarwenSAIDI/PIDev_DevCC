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
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import pidev.entities.Commande;
import pidev.entities.Panier;
import pidev.tools.MyConnection;

/**
 *
 * @author Occurence
 */
public class CommandeCRUD {
    private Connection cnx;
    private PreparedStatement ste;

    public CommandeCRUD() {
        cnx = MyConnection.getInstance().getConnection();
    }
    
    public void Ajouter(Commande com){
        String sql = "INSERT INTO commandes (ID_Produit, ID_Panier, Quantitee, Prix_U) VALUES( ?, ?, ?, ?)";
        try {
            ste = cnx.prepareStatement(sql);
            ste.setInt(1, com.getID_Produit());
            ste.setInt(2, com.getID_Panier());
            ste.setInt(3, com.getQuantitee());
            ste.setDouble(4, com.getPrix());
            ste.executeUpdate();
            System.out.println("Commande ajoutée");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("L'ID du panier n'exist pas !");

            alert.showAndWait();
        }
    }
    
    public List<Commande> AfficherCommande() throws SQLException {
        String query = "SELECT * FROM commandes";
        ste = cnx.prepareStatement(query);
        
        ResultSet result = ste.executeQuery(query);
            
        List<Commande> commandes = new ArrayList<>();
        while(result.next()){
            Commande com = new Commande();
            
            
            com.setID_Commande(result.getInt("ID_Commande"));
            com.setID_Produit(result.getInt("ID_Produit"));
            com.setID_Panier(result.getInt("ID_Panier"));
            com.setQuantitee(result.getInt("Quantitee"));
            com.setPrix(result.getDouble("Prix_U"));
            
            commandes.add(com);
        }
       

        return commandes;
    }
    
    
    public void Modifier(Commande com){
        
        String sql = "UPDATE commandes SET ID_Produit = ?, ID_Panier = ?, Quantitee = ?, Prix_U = ? WHERE ID_Commande = ?";
        try {
            ste = cnx.prepareStatement(sql);
            ste.setInt(1, com.getID_Produit());
            ste.setInt(2, com.getID_Panier());
            ste.setInt(3, com.getQuantitee());
            ste.setDouble(4, com.getPrix());
            ste.setInt(5, com.getID_Commande());
            
            ste.executeUpdate();
            System.out.println("Commande Modifier");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
        }
    }
    
    
    public void Supprimer(int ID){
        
        String sql = "DELETE FROM commandes WHERE ID_Commande = ?";
        try {
            ste = cnx.prepareStatement(sql);
            ste.setInt(1, ID);
            
            ste.executeUpdate();
            System.out.println("Commande Supprimer");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Commande> getCommande(int ID_panier) throws SQLException {
        String query = "SELECT * FROM commandes WHERE ID_Panier = ?";
        List<Commande> commandes = new ArrayList<>();
        try {
            ste = cnx.prepareStatement(query);
            
            ste.setInt(1, ID_panier);
            
            
            ResultSet result = ste.executeQuery();
            while(result.next()){
                Commande com = new Commande();


                com.setID_Commande(result.getInt("ID_Commande"));
                com.setID_Produit(result.getInt("ID_Produit"));
                com.setID_Panier(result.getInt("ID_Panier"));
                com.setQuantitee(result.getInt("Quantitee"));
                com.setPrix(result.getDouble("Prix_U"));

                commandes.add(com);
            }
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
        }
       

        return commandes;
    }
    
    
    
    
}
