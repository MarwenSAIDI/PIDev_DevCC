/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pidev.entities.Utilisateur;
import pidev.services.UtilisateurCRUD;

/**
 * FXML Controller class
 *
 * @author Occurence
 */
public class UtilisateurController implements Initializable {

    @FXML
    private TextField t_email;
    @FXML
    private PasswordField t_pwd;
    @FXML
    private Button login;
    
    private static Utilisateur userG;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ConnectUtilisateur(ActionEvent event) throws SQLException {
        
        Utilisateur u = new Utilisateur();
        userG = u;
        String email = t_email.getText();
        String pwd = t_pwd.getText();
        userG.setEmail(email);
        userG.setPWD(pwd);
        
        UtilisateurCRUD userC = new UtilisateurCRUD();
        userG = userC.Connect(userG);
        
        try{
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Front/Acceuil.fxml"));
            Parent root = loader.load();
            
            login.getScene().setRoot(root);
          
        }
        catch (IOException ex) {
            Logger.getLogger(UtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Utilisateur getUserG() {
        return userG;
    }
    
    
}
