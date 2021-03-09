/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


/**
 * FXML Controller class
 *
 * @author user
 */
public class PageEventController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private VBox EventChoisieCarte;
    @FXML
    private Label idEvent;
    @FXML
    private Label imgEventLabel;
    @FXML
    private Label idOrganisateurLabel;
    @FXML
    private Label TitreEventCarte;
    @FXML
    private Label LieuEventCarte;
    @FXML
    private ImageView fruitImg;
    @FXML
    private Label DateEventCarte;
    @FXML
    private Label TarifEventCarte;
    @FXML
    private Label PlaceRestanteCarte;
    @FXML
    private Button btnLirePlus;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void LirePlus_Reserver(ActionEvent event) {
    }
    
}
