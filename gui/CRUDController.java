/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Occurence
 */
public class CRUDController implements Initializable {

    @FXML
    private Button b_panier;
    @FXML
    private Button b_Commande;
    @FXML
    private Button b_pay;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void go_to_Panier(ActionEvent event) {
        
        try{
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Panier.fxml"));
            Parent root = loader.load();
            
            b_panier.getScene().setRoot(root);
          
        }
        catch (IOException ex) {
            Logger.getLogger(CRUDController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void go_to_Commande(ActionEvent event) {

        try{
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Commande.fxml"));
            Parent root = loader.load();
            b_Commande.getScene().setRoot(root);
        }
        catch (IOException ex) {
            Logger.getLogger(CRUDController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void go_to_Payment(ActionEvent event) {

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Payment.fxml"));
            Parent root = loader.load();
            b_pay.getScene().setRoot(root);
        }
        catch (IOException ex) {
            Logger.getLogger(CRUDController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}
