/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package therapeuteinterfaces;

import Clientinterfaces.AfficherEventClientController;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import services.ServiceReservationEvent;
import services.UserSession;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ReservationEventTherapeuteController implements Initializable {

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
    private JFXButton StatEvent;
    @FXML
    private JFXButton PageReservationEventLabel;
    private JFXButton GererEventLabel;
    @FXML
    private Label labelemail;
    @FXML
    private Hyperlink deconnecter;
    @FXML
    private JFXButton btnaccueil;
    @FXML
    private JFXButton btnEvent;
    @FXML
    private JFXButton recommend;
    @FXML
    private JFXButton btncons;
    @FXML
    private JFXButton demres;
    @FXML
    private JFXButton stat;
    @FXML
    private TextField rech;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
                           labelemail.setText( UserSession.instance.getUserName());

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
    private void AfficherInfoForm(MouseEvent event) {
    }

     @FXML
    private void deconnexion(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/authentification/authentification.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            deconnecter.getScene().setRoot(root);
            
    }

    @FXML
    private void PageReservationEvent(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReservationEventTherapeute.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            PageReservationEventLabel.getScene().setRoot(root);
            
    }

    @FXML
    private void cons(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Crudconsultation.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btncons.getScene().setRoot(root);
    }

    @FXML
    private void demanderes(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("affichagereservationtherapeute.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            demres.getScene().setRoot(root);
    }

    @FXML
    private void statclick(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chartcons.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            stat.getScene().setRoot(root);
    }


    @FXML
    private void gotocrudevent(MouseEvent event)throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CrudEvent.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btnEvent.getScene().setRoot(root);
            
    }

    @FXML
    private void gotostatevent(MouseEvent event)throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StatEventTherapeute.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            StatEvent.getScene().setRoot(root);
            
    }


    @FXML
    private void gotoaccueil(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accueiltherapeute.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            recommend.getScene().setRoot(root);
    }

    @FXML
    private void Pagerecomm(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Recommandation.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            recommend.getScene().setRoot(root);
    }

    @FXML
    private void rechtest(KeyEvent event) {
    }

}
