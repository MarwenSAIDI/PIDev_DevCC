/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pidev.entities.Panier;
import pidev.entities.Payment;
import pidev.services.PanierCRUD;
import java.lang.NullPointerException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import pidev.entities.Commande;
import pidev.services.TextFieldException;


/**
 * FXML Controller class
 *
 * @author Occurence
 */
public class PanierController implements Initializable {

    
    @FXML
    private TextField t_id;
    @FXML
    private ComboBox<String> cb_status;
    @FXML
    private TableView<Panier> tab_panier;
    private Button ret;
    
    private int ID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cb_status.getItems().addAll("En cours","Abondonner","Payer","Livrer");
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

            try {
                tab_panier.getItems().addAll(panc.AfficherPanier());
            } catch (SQLException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        catch(NullPointerException NPE){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText("No database was found");
            
            
            alert.showAndWait();
            
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CRUD.fxml"));
                Parent root;
                root = loader.load();
                ret.getScene().setRoot(root);
                
            } catch (IOException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }

            
        }
        
        
    }    

    private void AjouterPanier(ActionEvent event) {
        
        try{
            
            TextFieldException.verifEmpty(t_id.getText());
            int id_usr = Integer.parseInt(t_id.getText());
            
            
            

            TextFieldException.verifEmpty(cb_status.getValue());
            String status = cb_status.getValue();

            Panier pan = new Panier();
            pan.setID_User(id_usr);
            
            pan.setStatus_panier(status);

            PanierCRUD panc = new PanierCRUD();
            panc.Ajouter(pan);

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

    private void Retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CRUD.fxml"));
        Parent root = loader.load();
            
        ret.getScene().setRoot(root);
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
    
    

    @FXML
    private void go_to_Panier(MouseEvent event) {
        try{
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Panier.fxml"));
            Parent root = loader.load();
            
            t_id.getScene().setRoot(root);
          
        }
        catch (IOException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void go_to_Payment(MouseEvent event) {
        try{
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Payment.fxml"));
            Parent root = loader.load();
            t_id.getScene().setRoot(root);
        }
        catch (IOException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void go_to_Commande(MouseEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Commande.fxml"));
            Parent root = loader.load();
            t_id.getScene().setRoot(root);
        }
        catch (IOException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void go_to_Dashboard(MouseEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CRUD.fxml"));
            Parent root = loader.load();
            t_id.getScene().setRoot(root);
        }
        catch (IOException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
