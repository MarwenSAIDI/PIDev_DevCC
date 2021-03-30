/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admininterfaces;

import com.jfoenix.controls.JFXButton;
import entities.Recommandation;
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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.Servicerecommandation;
import services.UserSession;

/**
 * FXML Controller class
 *
 * @author yassi
 */
public class GestiotnrecoController implements Initializable {

    @FXML
    private AnchorPane yassine;
    @FXML
    private Label retour;
    @FXML
    private JFXButton retour1;
    @FXML
    private JFXButton textclient1;
    @FXML
    private JFXButton textth;
    @FXML
    private JFXButton PageReservationEventLabel;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnsupp;
    @FXML
    private Label labelemail;
    @FXML
    private Hyperlink deconnecter;
    @FXML
    private TableView<Recommandation> table;
    @FXML
    private TableColumn<Recommandation, String> id;
    @FXML
    private TableColumn<Recommandation, String> titre;
    @FXML
    private TableColumn<Recommandation, String> description;
    @FXML
    private TableColumn<Recommandation, String> ecrivain;
    @FXML
    private TableColumn<Recommandation, String> image;
    @FXML
    private TableColumn<Recommandation, String> type;
    @FXML
    private TableColumn<Recommandation, String> etat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelemail.setText(UserSession.instance.getUserName());
        Servicerecommandation cs = new Servicerecommandation();

         List<Recommandation> lc = cs.Listrecoadmin();
        
          ObservableList<Recommandation> data =
                  
        FXCollections.observableArrayList(lc );
        id.setCellValueFactory(
                new PropertyValueFactory<>("id"));
          
        titre.setCellValueFactory(
                new PropertyValueFactory<>("titre"));
 
       
        description.setCellValueFactory(
                new PropertyValueFactory<>("description"));
 
        
        ecrivain.setCellValueFactory(
                new PropertyValueFactory<>("ecrivain"));
        
        
        
        image.setCellValueFactory(
                new PropertyValueFactory<>("image"));
         type.setCellValueFactory(
                new PropertyValueFactory<>("type"));
         etat.setCellValueFactory(
                new PropertyValueFactory<>("etat"));
         
      
 
        table.setItems(data);
        // TODO
    }    
@FXML
    private void modifier(MouseEvent event) {
            Recommandation t = table.getSelectionModel().getSelectedItem();         
Servicerecommandation s=new Servicerecommandation();
s.Updaterecoadminconfirm(t);
Servicerecommandation cs = new Servicerecommandation();

         List<Recommandation> lc = cs.Listrecoadmin();
        
          ObservableList<Recommandation> data =
                  
        FXCollections.observableArrayList(lc );
          
      
 
 
 
        table.setItems(data);
    }

    @FXML
    private void supprimer(MouseEvent event) {
         Recommandation t = table.getSelectionModel().getSelectedItem();         
Servicerecommandation s=new Servicerecommandation();
s.DeleteRECO(t.getId());
Servicerecommandation xs= new Servicerecommandation();

         List<Recommandation> lcd = xs.Listrecoadmin();
        
          ObservableList<Recommandation> datan =
                  
        FXCollections.observableArrayList(lcd );
           table.setItems(datan);
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

    @FXML
    private void afficherform(MouseEvent event) {
    }
    
    
}
