/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Promotion;
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
import java.util.Date;


/**
 *
 * @author LENEVO
 */
public class ServicePromotion {
    
     Connection c;

    public ServicePromotion() {
        c = Maconnexion.getInstance().getConnection();
}

  public void AjouterPromotion(Promotion cc) {
       
    
            try {
                String requete = "insert into promotion (idP,valP,dateD,dateF) values(?,?,?,?)";
                PreparedStatement pst = c.prepareStatement(requete);
                pst.setInt(1, cc.getIdP());
                pst.setInt(2, cc.getValP());
                pst.setDate(3, new java.sql.Date(cc.getDateD().getTime()));
                pst.setDate(4, new java.sql.Date(cc.getDateF().getTime()));
               
//              pst.setDate(3,cc.getDateD());
//                pst.setDate(4, cc.getDateF());
                
                
                
                
                
                pst.executeUpdate();
                System.out.println("Promotion ajouter!!!!");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            
        }}
  
 public List<Promotion> ListPromotion() {
        List<Promotion>Mylist= new ArrayList<>();
        try {
            String requete = "select * from promotion";
            PreparedStatement pst = c.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Promotion t = new Promotion();
                t.setIdP(rs.getInt("idP"));
                t.setValP(rs.getInt("valP"));
                t.setDateD(rs.getDate("dateD"));
                 t.setDateF(rs.getDate("dateF"));
                 


                Mylist.add(t);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }

 public void ModifierPromotion(Promotion cc) {
        try {int a=cc.getIdP();
String requete = "UPDATE promotion SET idP=?, valP=?, dateD=?, dateF=? WHERE where idP =? ";            
PreparedStatement pst = c.prepareStatement(requete);
              pst.setInt(1,cc.getIdP());
            pst.setInt(2,cc.getValP());
               pst.setDate(3, new java.sql.Date(cc.getDateD().getTime()));
                pst.setDate(4, new java.sql.Date(cc.getDateF().getTime()));
           // pst.setDate(3, (Date) cc.getDateD());
            //pst.setDate(4, (Date) cc.getDateF());
                   
                  
                               pst.setInt(5,cc.getIdP());



            pst.executeUpdate();
            System.out.println(" promotion modifier !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void SuprimerPromotion(int idP) {
        try {
            String requete = "delete from promotion where ? = idP";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setInt(1,idP);
            pst.executeUpdate();
            System.out.println("promotion supprimer !!!!");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
public ObservableList<Promotion> getterList()

   {
       ObservableList<Promotion> ListPromotion = FXCollections.observableArrayList();
       String query = "SELECT * from `promotion`";
       Statement stm ;
       ResultSet rst;
       try{
           stm = c.createStatement();
           rst = stm.executeQuery(query);
           
           
       
           while (rst.next())
            {
                Promotion cc = new Promotion();
                cc.setIdP(rst.getInt("idP"));
                cc.setValP(rst.getInt("valP"));
                cc.setDateD(rst.getDate("dateD"));
                cc.setDateF(rst.getDate("datef"));
                
             
                ListPromotion.add(cc);
            
       }
       }catch(Exception ex)
       {
           ex.printStackTrace();
       }
        return ListPromotion;
       
   }
}