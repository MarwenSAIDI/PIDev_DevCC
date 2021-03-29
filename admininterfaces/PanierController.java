/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admininterfaces;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import entities.Panier;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import services.TextFieldException;
import services.PanierCRUD;

/**
 * FXML Controller class
 *
 * @author foura
 */
public class PanierController implements Initializable {

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
    private TextField t_search;
    @FXML
    private JFXComboBox<String> cb_Filtre;
    @FXML
    private TextField t_id;
    @FXML
    private ComboBox<String> cb_status;
    @FXML
    private TableView<Panier> tab_panier;
    
    private int ID;
    private String Filtre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cb_status.getItems().addAll("En cours","Abondonner","Payer","Livrer");
        cb_Filtre.getItems().addAll("ID Utilisateur","ID Panier","Date de creation","Date de mise à jour","Status panier");
        cb_status.getSelectionModel().select("En cours");
        
        try{
            PanierCRUD panc = new PanierCRUD();
        
            tab_panier.setEditable(true);

            TableColumn<Panier,String> id_p = new TableColumn("ID Panier");

            id_p.setCellValueFactory(
                    new PropertyValueFactory<>("ID_Panier"));

            TableColumn<Panier,String> id_usr = new TableColumn("ID User");

            id_usr.setCellValueFactory(
                    new PropertyValueFactory<>("ID_User"));

            TableColumn<Panier,String> d_c = new TableColumn("Date de creation");

            d_c.setCellValueFactory(
                    new PropertyValueFactory<>("Date_C"));

            TableColumn<Panier,String> d_u = new TableColumn("Date de mise à jour");

            d_u.setCellValueFactory(
                    new PropertyValueFactory<>("Date_U"));

            TableColumn<Panier,String> s_p = new TableColumn("Status panier");

            s_p.setCellValueFactory(
                    new PropertyValueFactory<>("Status_panier"));

            tab_panier.getColumns().add(id_p);
            tab_panier.getColumns().add(id_usr);
            tab_panier.getColumns().add(d_c);
            tab_panier.getColumns().add(d_u);
            tab_panier.getColumns().add(s_p);

            tab_panier.getItems().addAll(panc.AfficherPanier());
        }
        catch(NullPointerException NPE){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText("No database was found");
            
            
            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void Search(KeyEvent event) {
        PanierCRUD panc = new PanierCRUD();
        if(!t_search.getText().isEmpty()){
            t_search.textProperty().addListener((observable, oldValue, newValue) ->
                {
                try {
                    tab_panier.setItems(filterList(panc.AfficherPanier(), newValue));
                } catch (SQLException ex) {
                    Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            );
        }
    }

    private boolean searchFindsOrder(Panier pan, String searchText){
        
        if(Filtre == "ID Panier"){
            return (String.valueOf(pan.getID_Panier()).toLowerCase().contains(searchText.toLowerCase()));
        }
        else if(Filtre == "ID Utilisateur"){
            return (String.valueOf(pan.getID_User()).toLowerCase().contains(searchText.toLowerCase()));
        }
        else if(Filtre == "Date de creation"){
            return (String.valueOf(pan.getDate_C()).toLowerCase().contains(searchText.toLowerCase()));
        }
        else if(Filtre == "Date de mise à jour"){
            return (String.valueOf(pan.getDate_U()).toLowerCase().contains(searchText.toLowerCase()));
        }
        else if(Filtre == "Status panier"){
            return (pan.getStatus_panier().toLowerCase().contains(searchText.toLowerCase()));
        }
        
        
        return (String.valueOf(pan.getID_Panier()).toLowerCase().contains(searchText.toLowerCase()));
    }

    private ObservableList<Panier> filterList(List<Panier> list, String searchText){
        List<Panier> filteredList = new ArrayList<>();
        for (Panier pan : list){
            if(searchFindsOrder(pan, searchText)) filteredList.add(pan);
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
    private void Select_contenu(MouseEvent event) {
        Panier panierM_S =  tab_panier.getSelectionModel().selectedItemProperty().get();
        
        ID = panierM_S.getID_Panier();
        t_id.setText(String.valueOf(panierM_S.getID_User()));
        
        cb_status.getSelectionModel().select(panierM_S.getStatus_panier());
    }

    @FXML
    private void ModifierPanier(ActionEvent event) {
        try{
            TextFieldException.verifEmpty(t_id.getText());
            int id_usr = Integer.parseInt(t_id.getText());
            
            
            

            TextFieldException.verifEmpty(cb_status.getValue());
            String status = cb_status.getValue();

            Panier pan = new Panier();
            pan.setID_Panier(ID);
            pan.setID_User(id_usr);
            
            pan.setStatus_panier(status);

            PanierCRUD panc = new PanierCRUD();
            panc.Modifier(pan);

            tab_panier.getItems().clear();
            try {
                tab_panier.getItems().addAll(panc.AfficherPanier());
            } catch (SQLException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch(TextFieldException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty fields");
            alert.setHeaderText(e.getMessage());
            
            alert.showAndWait();
        }

    }

    @FXML
    private void SupprimerPanier(ActionEvent event) {
        PanierCRUD panc = new PanierCRUD();
        panc.Supprimer(ID);
        
        tab_panier.getItems().clear();
        try {
            tab_panier.getItems().addAll(panc.AfficherPanier());
        } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void gotoaccueuil(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accueiladmin.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
    }

    @FXML
    private void gotoaccueil(MouseEvent event) {
    }
    
}
