/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudvfinal;

import crudvfinal.services.UserSession;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author yassi
 */
public class InterfacetherapeutesController implements Initializable {

    @FXML
    private Label retour;
    @FXML
    private Label textrecom;
    @FXML
    private Label labelemail;
    @FXML
    private Hyperlink deconnecter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           labelemail.setText( UserSession.instance.getUserName());
    }    



    @FXML
    private void retour(MouseEvent event) {
    }

    @FXML
    private void gotoGestionreco(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Recommandation.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textrecom.getScene().setRoot(root);
        
        
    }

    @FXML
    private void deconnexion(MouseEvent event) throws IOException {
        UserSession.instance.cleanUserSession();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("authentification.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            deconnecter.getScene().setRoot(root);
    }
    
}
