/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudvfinal.services;

import crudvfinal.entities.Therapeute;
import crudvfinal.entities.client;
import crudvfinal.utils.DataBase;
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
public class Clientservice {
    
Connection c;

    public Clientservice() {
        c = DataBase.getInstance().getConnection();
    }

    public void addclient(client cl) {
        try {
            String requete = "insert into user (cin,email,nom,prenom,password,type) values(?,?,?,?,?,?)";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setInt(1, cl.getCin());
            pst.setString(2, cl.getEmail());

            pst.setString(3, cl.getNom());
            pst.setString(4, cl.getPrenom());
            pst.setString(5, cl.getPassword());
            pst.setString(6, "client");
           
            pst.executeUpdate();
            System.out.println("ENtite added !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<client> Listclient() {
        List<client> Mylist = new ArrayList<>();
        try {
            String requete = "select * from user where type =? ";
            PreparedStatement pst = c.prepareStatement(requete);
                        pst.setString(1, "client");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                client p = new client();
                p.setCin(rs.getInt("Cin"));
                p.setEmail(rs.getString("email"));
                p.setNom(rs.getString("NOM"));
                p.setPrenom(rs.getString("prenom"));
                 p.setPassword(rs.getString("PASSWORD"));
                 




                Mylist.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  Mylist;
    }

    public void Updateclient(client cl) {
        try {
String requete = "UPDATE user set cin=?, email = ?, nom = ?, prenom = ?, ";
requete += " password = ? where cin =? ";            
PreparedStatement pst = c.prepareStatement(requete);
              pst.setInt(1, cl.getCin());
            pst.setString(2, cl.getEmail());

            pst.setString(3, cl.getNom());
            pst.setString(4, cl.getPrenom());
            pst.setString(5, cl.getPassword());
            
              pst.setInt(6, cl.getCin());



            pst.executeUpdate();
            System.out.println("Classroom Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void Deleteclient(int a) {
        try {
            String requete = "delete from user where ? = cin";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setInt(1, a);
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    }
    

