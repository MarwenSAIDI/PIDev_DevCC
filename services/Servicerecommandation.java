/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Recommandation;
import entities.client;
import utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yassi
 */
public class Servicerecommandation {
    Connection c;

    public Servicerecommandation() {
    DataBase db=new DataBase();
        c = db.getConnection();
    }

    public void addreco(Recommandation cl) {
        try {
            String requete = "insert into reco (id,titre,description,ecrivain,image,type) values(?,?,?,?,?,?)";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setString(1, cl.getId());
            pst.setString(2, cl.getTitre());

            pst.setString(3, cl.getDescription());
            pst.setString(4, cl.getEcrivain());
            pst.setString(5, cl.getImage());
            pst.setString(6, cl.getType());
           
            pst.executeUpdate();
            System.out.println("ENtite added !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Recommandation> Listreco() {
        List<Recommandation> Mylist = new ArrayList<>();
        try {
            String requete = "select * from reco  ";
            PreparedStatement pst = c.prepareStatement(requete);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Recommandation p = new Recommandation();
                p.setId(rs.getString("id"));
                p.setTitre(rs.getString("titre"));
                p.setDescription(rs.getString("description"));
                p.setEcrivain(rs.getString("ecrivain"));
                 p.setImage(rs.getString("image"));
                 p.setType(rs.getString("type"));

                 




                Mylist.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  Mylist;
    }

    public void Updatereco(Recommandation cl) {
        try {
String requete = "UPDATE reco set id=?, titRe = ?, description = ?, ecrivain = ?, ";
requete += " image = ? ,type=? where id =? ";            
PreparedStatement pst = c.prepareStatement(requete);
              pst.setString(1, cl.getId());
            pst.setString(2, cl.getTitre());

            pst.setString(3, cl.getDescription());
            pst.setString(4, cl.getEcrivain());
            pst.setString(5, cl.getImage());
            
              pst.setString(6, cl.getType());
                            pst.setString(7, cl.getId());




            pst.executeUpdate();
            System.out.println("Classroom Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void DeleteRECO(String a) {
        try {
            String requete = "delete from reco where ? = ID";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setString(1, a);
            pst.executeUpdate();
            System.out.println("Classrooms Deleted !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public boolean estUnEntier(String chaine) {
		try {
			Integer.parseInt(chaine);
		} catch (NumberFormatException e){
			return false;
		}
 
		return true;
	}
    
     public List<Recommandation> Listrecoarticles() {
        List<Recommandation> Mylist = new ArrayList<>();
        try {
            String requete = "select * from reco where type=?  ";
            
            PreparedStatement pst = c.prepareStatement(requete);
                          pst.setString(1, "article");


            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Recommandation p = new Recommandation();
                p.setId(rs.getString("id"));
                p.setTitre(rs.getString("titre"));
                p.setDescription(rs.getString("description"));
                p.setEcrivain(rs.getString("ecrivain"));
                 p.setImage(rs.getString("image"));
                 p.setType(rs.getString("type"));

                 




                Mylist.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  Mylist;
    }

     public List<Recommandation> Listrecolivres() {
        List<Recommandation> Mylist = new ArrayList<>();
        try {
            String requete = "select * from reco where type=?  ";
            
            PreparedStatement pst = c.prepareStatement(requete);
                          pst.setString(1, "livre");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Recommandation p = new Recommandation();
                p.setId(rs.getString("id"));
                p.setTitre(rs.getString("titre"));
                p.setDescription(rs.getString("description"));
                p.setEcrivain(rs.getString("ecrivain"));
                 p.setImage(rs.getString("image"));
                 p.setType(rs.getString("type"));

                 




                Mylist.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  Mylist;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    }
    
    

