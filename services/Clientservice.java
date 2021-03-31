/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Therapeute;
import entities.client;
import utils.DataBase;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

/**
 *
 * @author yassi
 */
public class Clientservice   {
    
 private Connection c;
    private Statement ste;

    public Clientservice() {
 DataBase db = new DataBase();
        c = db.getConnection();
        }

    public void addclient(client cl) {
        try {
            String requete = "insert into user (cin,email,nom,prenom,password,type,etat) values(?,?,?,?,?,?,?)";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setInt(1, cl.getCin());
            pst.setString(2, cl.getEmail());

            pst.setString(3, cl.getNom());
            pst.setString(4, cl.getPrenom());
            pst.setString(5, cl.getPassword());
            pst.setString(6, "client");
            pst.setString(7, "attente");

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
                 p.setEtat(rs.getString("etat"));




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
    
    
    public boolean authentification(String a ,String b)
    { int i=0;String s="";   try {
            String requete = "select * from user where email =? && password=? && type=?  ";
            PreparedStatement pst = c.prepareStatement(requete);
                        pst.setString (1, a);
                        pst.setString (2, b);
                        pst.setString (3, "client");


            ResultSet rs = pst.executeQuery();
while(rs.next()){i++;s+=rs.getString("etat");

  }
                System.out.println(s);     
            if (( i!=0))
            {if (s.equals("inscrit"))
                    {return true;}
                else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("you were banned or not registered");
            alert.setContentText("you were banned or not registered ");

            alert.showAndWait();
                 return false;       
                        
                        
                        
                        
                        }
            }
            else return false;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    
    return false;
    
    
    
    
    
    }
    public boolean authentificationadmin(String a ,String b)
    { int i=0;   try {
            String requete = "select * from user where email =? && password=? && type=?  ";
            PreparedStatement pst = c.prepareStatement(requete);
                        pst.setString (1, a);
                        pst.setString (2, b);
                            pst.setString (3, "admin");


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
    
    public void Updatemdpclient(client cl) {
        try {
String requete = "UPDATE user set password=? where email =? ";            
PreparedStatement pst = c.prepareStatement(requete);
              pst.setString(1, cl.getPassword());
            pst.setString(2, cl.getEmail());

           


            pst.executeUpdate();
            System.out.println("Classroom Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}
         
    
    
    
    public void Updatebannedclient(client cl) {
        try {
String requete = "UPDATE user set etat=? where email =? ";            
PreparedStatement pst = c.prepareStatement(requete);
              pst.setString(1,"banned");
            pst.setString(2, cl.getEmail());

           


            pst.executeUpdate();
            System.out.println("Classroom Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}
          
    public void Updateinscritclient(client cl) {
        try {
String requete = "UPDATE user set etat=? where email =? ";            
PreparedStatement pst = c.prepareStatement(requete);
              pst.setString(1,"inscrit");
            pst.setString(2, cl.getEmail());

           


            pst.executeUpdate();
            System.out.println("Classroom Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}
    
    public boolean emailoui(String a ) throws SQLException
    { int i=0;   
            String requete = "select * from rating where email =?   ";
            PreparedStatement pst = c.prepareStatement(requete);
                        pst.setString (1, a);
                       


            ResultSet rs = pst.executeQuery();
while(rs.next()){i++;
}
 if (( i!=0))
            {
                return true;
            }
            else return false;

        }
    public void addratint(String a, double b) {
        try {
            String requete = "insert into rating (email,rating) values(?,?)";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setString(1,a);
                        pst.setDouble(2, b);


          

            pst.executeUpdate();
            System.out.println("ENtite added !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public double rating( ) throws SQLException
    { int i=0;  double a=0; 
            String requete = "select * from rating    ";
            PreparedStatement pst = c.prepareStatement(requete);
                       


            ResultSet rs = pst.executeQuery();
while(rs.next()){i++; a+=rs.getDouble("rating");
}
 if (( i!=0))
            {
return a/i;            }
            else return 0;

        }
    
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

