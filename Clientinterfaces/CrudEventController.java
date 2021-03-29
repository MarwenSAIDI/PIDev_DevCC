/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientinterfaces;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author foura
 */
public class CrudEventController implements Initializable {

    @FXML
    private Button btnDeconnexion;
    @FXML
    private TableView<?> TableView;
    @FXML
    private TableColumn<?, ?> idEventColumn;
    @FXML
    private TableColumn<?, ?> IdOrganisteurEventColumn;
    @FXML
    private TableColumn<?, ?> TypeEventColumn;
    @FXML
    private TableColumn<?, ?> TitreEventColumn;
    @FXML
    private TableColumn<?, ?> DescriptionEventColumn;
    @FXML
    private TableColumn<?, ?> LieuEventColumn;
    @FXML
    private TableColumn<?, ?> DateEventColumn;
    @FXML
    private TableColumn<?, ?> ImageEventColumn;
    @FXML
    private TableColumn<?, ?> TarifEventColumn;
    @FXML
    private TableColumn<?, ?> capciteColoumn;
    @FXML
    private TableColumn<?, ?> nbRservatColomun;
    @FXML
    private TableColumn<?, ?> etatReservColoumn;
    @FXML
    private ImageView btnNotification;
    @FXML
    private ImageView imageView;
    @FXML
    private Label DateEventLabel;
    @FXML
    private Button ModiferEventButton;
    @FXML
    private Button SupprimerEventButton;
    @FXML
    private JFXButton StatEvent;
    @FXML
    private JFXButton PageReservationEventLabel;
    @FXML
    private Button BtnInterfaceAjout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void deconnexion(ActionEvent event) {
    }

    @FXML
    private void AfficherInfoForm(MouseEvent event) {
    }

    @FXML
    private void AfficherNotifications(MouseEvent event) {
    }

    @FXML
    private void ModiferEventButton(ActionEvent event) {
    }

    @FXML
    private void SupprimerEventButton(ActionEvent event) {
    }

    @FXML
    private void clear(MouseEvent event) {
    }

    @FXML
    private void PageStatEvent(MouseEvent event) {
    }

    @FXML
    private void PageReservationEvent(MouseEvent event) {
    }

    @FXML
    private void InterfaceAjout(ActionEvent event) {
    }
    
}
