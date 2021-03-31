/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.Payment;
import entities.Produit;
import utils.DataBase;

/**
 *
 * @author Occurence
 */
public class ProduitCRUD {
    
    private Connection cnx;
    private PreparedStatement ste;

    public ProduitCRUD() {
        DataBase db=new DataBase();
        cnx = db.getConnection();
    }
    
    public List<Produit> AfficherProduit() throws SQLException {
        String query = "SELECT * FROM produits";
        ste = cnx.prepareStatement(query);
        
        ResultSet result = ste.executeQuery(query);
            
        List<Produit> produits = new ArrayList<>();
        while(result.next()){
            Produit prod = new Produit();
            prod.setID_Produit(result.getInt("ID_Produit"));
            prod.setQuantitee(result.getInt("Quantitee"));
            prod.setPrix(result.getDouble("Prix"));
            prod.setNom(result.getString("Nom"));
            produits.add(prod);
        }
       
        return produits;
    }
    
    public int getProduit(int ID) throws SQLException {
        String query = "SELECT Quantitee FROM produits WHERE ID_Produit = ?";
        int quant = 0;
        try {
            ste = cnx.prepareStatement(query);
            ste.setInt(1, ID);
            
            ResultSet result = ste.executeQuery();
            
            
            if(result.next()){

                quant = result.getInt("Quantitee");
                
            }
            
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
        }
        
        return quant;    
    }
    
}
