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
import pidev.entities.Utilisateur;
import pidev.tools.MyConnection;

/**
 *
 * @author Occurence
 */
public class UtilisateurCRUD {
    
    private Connection cnx;
    private PreparedStatement ste;

    public UtilisateurCRUD() {
        cnx = MyConnection.getInstance().getConnection();
    }
    
    
    
    public Utilisateur Connect(Utilisateur u) throws SQLException{
        String query = "SELECT * FROM users WHERE Email = ? and pwd = ?";
        
        

        
        try {
            ste = cnx.prepareStatement(query);
            System.out.println("OK 1");
            ste.setString(1, u.getEmail());
            System.out.println("OK 2");
            ste.setString(2, u.getPWD());
            System.out.println("OK 3");
            ResultSet result = ste.executeQuery();
            System.out.println("OK 4");
            
            while(result.next()){
                
                u.setID_user(result.getInt("ID_User"));
                u.setNom(result.getString("Nom"));
                u.setPrenom(result.getString("Prenom"));
            }
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
        }
        
        
        return u;
    }
    
}
