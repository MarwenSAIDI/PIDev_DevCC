/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admininterfaces;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import entities.Commande;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import services.CommandeCRUD;

/**
 * FXML Controller class
 *
 * @author foura
 */
public class CommandeController implements Initializable {

    @FXML
    private Label labelemail;
    @FXML
    private Hyperlink deconnecter;
    @FXML
    private JFXButton textcmient;
    @FXML
    private JFXButton textth;
    @FXML
    private JFXButton PageReservationEventLabel;
    @FXML
    private TextField t_pan;
    @FXML
    private TableView<Commande> tab_C;
    @FXML
    private TextField t_search;
    @FXML
    private JFXComboBox<String> cb_Filtre;
    
    private int ID;
    private String Filtre;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
            
            cb_Filtre.getItems().addAll("ID Commande","ID Produit","ID Panier");
        
            CommandeCRUD comc = new CommandeCRUD();

            tab_C.setEditable(true);

            TableColumn<Commande,String> id_com = new TableColumn("ID Commande");

            id_com.setCellValueFactory(
                    new PropertyValueFactory<>("ID_Commande"));


            TableColumn<Commande,String> id_p = new TableColumn("ID Produit");

            id_p.setCellValueFactory(
                    new PropertyValueFactory<>("ID_Produit"));

            TableColumn<Commande,String> id_pan = new TableColumn("ID Panier");

            id_pan.setCellValueFactory(
                    new PropertyValueFactory<>("ID_Panier"));



            TableColumn<Commande,String> quant = new TableColumn("Quantitée");

            quant.setCellValueFactory(
                    new PropertyValueFactory<>("Quantitee"));

            TableColumn<Commande,String> prix = new TableColumn("Prix");

            prix.setCellValueFactory(
                    new PropertyValueFactory<>("prix"));

           
            tab_C.getColumns().add(id_com);
            tab_C.getColumns().add(id_p);
            tab_C.getColumns().add(id_pan);
            tab_C.getColumns().add(quant);
            tab_C.getColumns().add(prix);


            try {
                tab_C.getItems().addAll(comc.AfficherCommande());
            } catch (SQLException ex) {
                Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        catch(NullPointerException NPE){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText("No database was found");
            
            
            alert.showAndWait();
            
            
        }

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
    private void PageReservationEvent(MouseEvent event) {
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
    private void Select_contenu(MouseEvent event) {
        Commande commandeM_S =  tab_C.getSelectionModel().selectedItemProperty().get();
        
        ID = commandeM_S.getID_Commande();
        t_pan.setText(String.valueOf(commandeM_S.getID_Panier()));
    }

    @FXML
    private void SupprimerCommande(ActionEvent event) {
        CommandeCRUD comc = new CommandeCRUD();
        comc.Supprimer(ID);
        
        
        tab_C.getItems().clear();
        try {
            tab_C.getItems().addAll(comc.AfficherCommande());
        } catch (SQLException ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Search(KeyEvent event) {
        CommandeCRUD comc = new CommandeCRUD();
        if(!t_search.getText().isEmpty()){
            t_search.textProperty().addListener((observable, oldValue, newValue) ->
                {
                try {
                    tab_C.setItems(filterList(comc.AfficherCommande(), newValue));
                } catch (SQLException ex) {
                    Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            );
        }
    }

    private boolean searchFindsOrder(Commande com, String searchText){
        
        if(Filtre == "ID Panier"){
            return (String.valueOf(com.getID_Panier()).toLowerCase().contains(searchText.toLowerCase()));
        }
        else if(Filtre == "ID Commande"){
            return (String.valueOf(com.getID_Commande()).toLowerCase().contains(searchText.toLowerCase()));
        }
        else if(Filtre == "ID Produit"){
            return (String.valueOf(com.getID_Produit()).toLowerCase().contains(searchText.toLowerCase()));
        }
        
        return (String.valueOf(com.getID_Commande()).toLowerCase().contains(searchText.toLowerCase()));
    }

    private ObservableList<Commande> filterList(List<Commande> list, String searchText){
        List<Commande> filteredList = new ArrayList<>();
        for (Commande com : list){
            if(searchFindsOrder(com, searchText)) filteredList.add(com);
        }
        return FXCollections.observableList(filteredList);
    }

    @FXML
    private void getFiltre(ActionEvent event) {
        
        if(Filtre != cb_Filtre.getValue()){
            Filtre = cb_Filtre.getValue();
            
            t_search.clear();
        }

    }
    @FXML
    private void gotoaccueil(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accueiladmin.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
    }

    

}
