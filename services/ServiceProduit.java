/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Produit;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import Services.IServiceProduit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Maconnexion;


/**
 *
 * @author aymen romdhani
 */
public class ServiceProduit {
    Connection c;

    public ServiceProduit() {
        c = Maconnexion.getInstance().getConnection();
    }

            
    public void AjouterProduit(Produit cl) {
       
    
            try {
                String requete = "insert into produit (id,nom,type,quantite,prix) values(?,?,?,?,?)";
                PreparedStatement pst = c.prepareStatement(requete);
                pst.setInt(1, cl.getID());
                pst.setString(2, cl.getNom());
                
                pst.setString(3, cl.getType());
                pst.setInt(4, cl.getQuantite());
                pst.setFloat(5, cl.getPrix());
                
                
                
                
                pst.executeUpdate();
                System.out.println("Produit ajouter!!!!");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            
        }}

    public List<Produit> ListProduit() {
        List<Produit>Mylist= new ArrayList<>();
        try {
            String requete = "select * from produit";
            PreparedStatement pst = c.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Produit t = new Produit();
                t.setID(rs.getInt("ID"));
                t.setNom(rs.getString("Nom"));
                t.setType(rs.getString("Type"));
                 t.setQuantite(rs.getInt("Quantite"));
                 t.setPrix(rs.getFloat("Prix"));


                Mylist.add(t);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }

    public void ModifierProduit(Produit cl) {
        try {int a=cl.getID();
String requete = "UPDATE produit set  ID=?,nom = ?, type = ?, quantite = ?, ";
requete += "prix = ? where id =? ";            
PreparedStatement pst = c.prepareStatement(requete);
              pst.setInt(1,cl.getID());
            pst.setString(2,cl.getNom());

            pst.setString(3,cl.getType());
            pst.setInt(4,cl.getQuantite());
                   pst.setFloat(5,cl.getPrix());
                  
                                pst.setInt(6,cl.getID());



            pst.executeUpdate();
            System.out.println(" produit modifier !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void SuprimerProduit(int id) {
        try {
            String requete = "delete from produit where ? = id";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setInt(1,id);
            pst.executeUpdate();
            System.out.println("produit supprimer !!!!");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    public ObservableList<Produit> getterList()

   {
       ObservableList<Produit> ListProduit = FXCollections.observableArrayList();
       String query = "SELECT * from `produit`";
       Statement stm ;
       ResultSet rst;
       try{
           stm = c.createStatement();
           rst = stm.executeQuery(query);
           
           
       
           while (rst.next())
            {
                Produit cl = new Produit();
                cl.setID(rst.getInt("ID"));
                cl.setNom(rst.getString("Nom"));
                cl.setType(rst.getString("Type"));
                cl.setQuantite(rst.getInt("Quantite"));
                cl.setPrix(rst.getFloat("Prix"));
             
                ListProduit.add(cl);
            
       }
       }catch(Exception ex)
       {
           ex.printStackTrace();
       }
        return ListProduit;
       
   }
}
    
    
    
    
    
   
    
    
    
    



