/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Produit;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import services.ServiceProduit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataBase;


/**
 *
 * @author aymen romdhani
 */
public class ServiceProduit {
    Connection c;
    private PreparedStatement ste;

    public ServiceProduit() {
        DataBase db=new DataBase();
        c = db.getConnection();
    }
     
    public void AjouterProduit(Produit cl) {
       
    
            try {
                String requete = "insert into produits (Nom,Type,Quantitee,Prix,Image) values(?,?,?,?,?)";
                PreparedStatement pst = c.prepareStatement(requete);
                
                pst.setString(1, cl.getNom());
                
                pst.setString(2, cl.getType());
                pst.setInt(3, cl.getQuantitee());
                pst.setDouble(4, cl.getPrix());
                pst.setString(5, cl.getImage());

                
                
                
                
                pst.executeUpdate();
                System.out.println("Produit ajouter!!!!");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            
        }
    }

    public List<Produit> ListProduit() {
        List<Produit>Mylist= new ArrayList<>();
        try {
            String requete = "select * from produits";
            PreparedStatement pst = c.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Produit t = new Produit();
                t.setID_Produit(rs.getInt("ID_Produit"));
                t.setNom(rs.getString("Nom"));
                t.setType(rs.getString("Type"));
                t.setQuantitee(rs.getInt("Quantitee"));
                t.setPrix(rs.getDouble("Prix"));
                t.setImage(rs.getString("Image"));

                Mylist.add(t);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  Mylist;
    }

    public void ModifierProduit(Produit cl) {
        try {int a=cl.getID_Produit();
String requete = "UPDATE produits set  Nom = ?, Type = ?, Quantitee = ?,  ";
requete += "Prix = ? , Image = ? where ID_Produit =? ";            
PreparedStatement pst = c.prepareStatement(requete);
              
            pst.setString(1,cl.getNom());

            pst.setString(2,cl.getType());
            pst.setInt(3,cl.getQuantitee());
                   pst.setDouble(4,cl.getPrix());
                   pst.setString(5,cl.getImage());
                  
                                pst.setInt(6,cl.getID_Produit());



            pst.executeUpdate();
            System.out.println(" produit modifier !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void SuprimerProduit(int id) {
        try {
            String requete = "delete from produits where ? = ID_Produit";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setInt(1,id);
            pst.executeUpdate();
            System.out.println("produit supprimer !!!!");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    public int getProduit(int ID_Prod) {
 String query = "SELECT Quantitee FROM produits WHERE ID_Produit = ?";
        int quant = 0;
        try {
            ste = c.prepareStatement(query);
            ste.setInt(1, ID_Prod);
            
            ResultSet result = ste.executeQuery();
            
            
            if(result.next()){

                quant = result.getInt("Quantitee");
                
            }
            
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
        }
        
        return quant;        }

   
}
    
    
    
    
    
   
    
    
    
    



