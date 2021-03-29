/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Therapeute;
import utils.DataBase;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yassi
 */
public class Therapeuteservice {
    
Connection c;

    public Therapeuteservice() {
    DataBase db=new DataBase();
        c = db.getConnection();
    }

    public void addTherapeute(Therapeute cl) {
        try {
            String requete = "insert into user (cin,email,nom,prenom,password,type,numtel,adresse,image) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setInt(1, cl.getCin());
            pst.setString(2, cl.getEmail());

            pst.setString(3, cl.getNom());
            pst.setString(4, cl.getPrenom());
            pst.setString(5, cl.getPassword());
            pst.setString(6, "therapeute");
            pst.setInt(7, cl.getNumtel());
            pst.setString(8, cl.getAdresse());
            pst.setString(9, cl.getImther());
            pst.executeUpdate();
            System.out.println("ENtite added !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Therapeute> ListEntite() {
        List<Therapeute> Mylist = new ArrayList<>();
        try {
            String requete = "select * from user where type =? ";
            PreparedStatement pst = c.prepareStatement(requete);
                        pst.setString(1, "therapeute");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Therapeute p = new Therapeute();
                p.setCin(rs.getInt("Cin"));
                p.setEmail(rs.getString("email"));
                p.setNom(rs.getString("NOM"));
                p.setPrenom(rs.getString("prenom"));
                 p.setPassword(rs.getString("PASSWORD"));
                 p.setNumtel(rs.getInt("numtel"));
                p.setAdresse(rs.getString("adresse"));
                p.setImther(rs.getString("image"));




                Mylist.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }

    public void UpdateClasse(Therapeute cl) {
        try {
String requete = "UPDATE user set cin=?, email = ?, nom = ?, prenom = ?, ";
requete += " password = ?,numtel=?,adresse = ?,image=? where cin =? ";            
PreparedStatement pst = c.prepareStatement(requete);
              pst.setInt(1, cl.getCin());
            pst.setString(2, cl.getEmail());

            pst.setString(3, cl.getNom());
            pst.setString(4, cl.getPrenom());
            pst.setString(5, cl.getPassword());
            pst.setInt(6, cl.getNumtel());
            pst.setString(7, cl.getAdresse());
            pst.setString(8, cl.getImther());
              pst.setInt(9, cl.getCin());



            pst.executeUpdate();
            System.out.println("Classroom Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void DeleteClasse(int a) {
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
    
    public boolean authentification(String a ,String b)
    { int i=0;   try {
            String requete = "select * from user where email =? && password=? && type=?  ";
            PreparedStatement pst = c.prepareStatement(requete);
                        pst.setString (1, a);
                        pst.setString (2, b);
                            pst.setString (3, "therapeute");


            ResultSet rs = pst.executeQuery();
           
while(rs.next()){i++;  }
            
            
            if (( i!=0))
            {System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                return true;
            }
            else return false;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    
    return false;
    
    
    
    
    
    }
    
     public static String getMd5(String input) 
    { 
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        }} 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    }
