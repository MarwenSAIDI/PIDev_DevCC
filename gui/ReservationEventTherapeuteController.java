/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Evenement;
import entities.ReservationEvent;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import service.ServiceReservationEvent;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ReservationEventTherapeuteController implements Initializable {

    @FXML
    private Button btnDeconnexion;
    @FXML
    private TableView<ReservationEvent> TableView;
    @FXML
    private TableColumn<ReservationEvent, Integer> IDreservationColumn;
    @FXML
    private TableColumn<ReservationEvent, String> TitreEventColumn;
    @FXML
    private TableColumn<ReservationEvent,String > NomClientColumn;
    @FXML
    private TableColumn<ReservationEvent, String> PrenomClientColumn;
    @FXML
    private TableColumn<ReservationEvent, Integer > TelClientColumn;
    @FXML
    private TableColumn<ReservationEvent, String> EmailClientColumn;
    @FXML
    private TableColumn<ReservationEvent, Integer > NbrPlaceColumn;
    @FXML
    private TableColumn<ReservationEvent, String> ModePaiementColumn;
    @FXML
    private TableColumn<ReservationEvent, String> EtatReservationColumn;
    @FXML
    private Label DateEventLabel;
    @FXML
    private Label StatEvent;
    @FXML
    private HBox PageReservationEventLabel;
    @FXML
    private Label GererEventLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        
            ServiceReservationEvent cs = new ServiceReservationEvent();
            
            List<ReservationEvent> lc = cs.ListReservationEvent();
            
            ObservableList<ReservationEvent> data =
                    
                    FXCollections.observableArrayList(lc );
            
            IDreservationColumn.setCellValueFactory(
                    new PropertyValueFactory<>("id"));
            
            TitreEventColumn.setCellValueFactory(
                    new PropertyValueFactory<>("Titre_event"));
            
            
            NomClientColumn.setCellValueFactory(
                    new PropertyValueFactory<>("nom_client"));
            
            
            PrenomClientColumn.setCellValueFactory(
                    new PropertyValueFactory<>("prenom_client"));
            
            TelClientColumn.setCellValueFactory(
                    new PropertyValueFactory<>("num_client"));
            
            EmailClientColumn.setCellValueFactory(
                    new PropertyValueFactory<>("email_client"));
            
            NbrPlaceColumn.setCellValueFactory(
                    new PropertyValueFactory<>("nb_place"));
            
            ModePaiementColumn.setCellValueFactory(
                    new PropertyValueFactory<>("mode_paiement"));
            
            EtatReservationColumn.setCellValueFactory(
                    new PropertyValueFactory<>("etat"));
            
            
            TableView.setItems(data);
    }    

    @FXML
    private void deconnexion(ActionEvent event) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherEventClient.fxml"));
        try {
            Parent root = loader.load();
            btnDeconnexion.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherEventClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AfficherInfoForm(MouseEvent event) {
    }

    @FXML
    private void PageStatEvent(MouseEvent event) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StatEventTherapeute.fxml"));
        try {
            Parent root = loader.load();
            btnDeconnexion.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherEventClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void PageReservationEvent(MouseEvent event) {
    }

    @FXML
    private void GererEvent(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CrudEvent.fxml"));
        try {
            Parent root = loader.load();
            btnDeconnexion.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherEventClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
