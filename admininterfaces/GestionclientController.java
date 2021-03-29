/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admininterfaces;

import authentification.OublierController;
import com.jfoenix.controls.JFXButton;
import entities.Therapeute;
import entities.client;
import services.Clientservice;
import services.Therapeuteservice;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import services.UserSession;

/**
 * FXML Controller class
 *
 * @author yassi
 */
public class GestionclientController implements Initializable {

    @FXML
    private TextField textcin;
    @FXML
    private TextField textemail;
    @FXML
    private TextField textnom;
    @FXML
    private TextField textprenom;
    @FXML
    private TextField textpassword;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnsupp;
    @FXML
    private TableView<client> table;
    @FXML
    private TableColumn<client, Integer> cin;
    @FXML
    private TableColumn<client, String> email;
    @FXML
    private TableColumn<client, String> nom;
    @FXML
    private TableColumn<client, String>prenom;
    @FXML
    private TableColumn<client, String> password;
    @FXML
    private Label cinlabel;
    @FXML
    private Label emaillabel;
    @FXML
    private Label pwdlabel;
    @FXML
    private Label retour;
    @FXML
    private TableColumn<client, String> etat;
    @FXML
    private JFXButton textth;
    @FXML
    private JFXButton retour1;
    @FXML
    private JFXButton textclient1;
    @FXML
    private JFXButton PageReservationEventLabel;
    @FXML
    private AnchorPane yassine;
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

        Clientservice cs = new Clientservice();

         List<client> lc = cs.Listclient();
        
          ObservableList<client> data =
                  
        FXCollections.observableArrayList(lc );
          
        cin.setCellValueFactory(
                new PropertyValueFactory<>("cin"));
          
        email.setCellValueFactory(
                new PropertyValueFactory<>("email"));
 
       
        nom.setCellValueFactory(
                new PropertyValueFactory<>("nom"));
 
        
        prenom.setCellValueFactory(
                new PropertyValueFactory<>("prenom"));
        
        
        
        password.setCellValueFactory(
                new PropertyValueFactory<>("password"));
      etat.setCellValueFactory(
                new PropertyValueFactory<>("etat"));
 
        table.setItems(data);
        
    }    

    @FXML
    private void modifier(MouseEvent event) {
                 client t = table.getSelectionModel().getSelectedItem();         
Clientservice s=new Clientservice();
s.Updateinscritclient(t);
Clientservice cs = new Clientservice();

         List<client> lc = cs.Listclient();
        
          ObservableList<client> data =
                  
        FXCollections.observableArrayList(lc );
          
      
 
 
 
        table.setItems(data);
          OublierController.send("zenlifezenlife02@gmail.com","zenlife123",textemail.getText(),"Compte Zenlife","felicitations vous etes inscrits a zenlife merci de nous rejoindre");             

    
}
        
        
    

    @FXML
    private void supprimer(MouseEvent event) {
        client t = table.getSelectionModel().getSelectedItem();         
Clientservice s=new Clientservice();
s.Updatebannedclient(t);
Clientservice cs = new Clientservice();

         List<client> lc = cs.Listclient();
        
          ObservableList<client> data =
                  
        FXCollections.observableArrayList(lc );
          
      
 
 
 
        table.setItems(data);
        
    }

    @FXML
    private void deconnexion(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/authentification/authentification.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            deconnecter.getScene().setRoot(root);

    }

    @FXML
    private void gotoGestionclients(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Gestionclient.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
        
       
    }

    @FXML
    private void gotoGestiontherapeutes(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
        
    }


    @FXML
    private void go_to_Commandes(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Commande.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
    }

    @FXML
    private void go_to_Produit(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Produit.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
    }

    @FXML
    private void go_to_Paiements(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Payment.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
    }

    @FXML
    private void go_to_Panier(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Panier.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
    }

    @FXML
    private void go_to_Promotion(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Promotion.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
    }

    @FXML
    private void gotoaccueuil(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accueiladmin.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
    }

    

    
   
    
}
