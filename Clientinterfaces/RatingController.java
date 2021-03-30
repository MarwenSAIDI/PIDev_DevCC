/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientinterfaces;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;
import services.Clientservice;
import services.Servicerecommandation;
import services.UserSession;

/**
 * FXML Controller class
 *
 * @author yassi
 */
public class RatingController implements Initializable {

    @FXML
    private Rating rating;
    @FXML
    private Button avis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void avis(MouseEvent event) {
Clientservice s=new Clientservice();
s.addratint(UserSession.instance.getUserName(), rating.getRating());

        
    }
    
}
