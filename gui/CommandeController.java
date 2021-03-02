/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pidev.entities.Commande;
import pidev.entities.Commande;
import pidev.services.CommandeCRUD;

/**
 * FXML Controller class
 *
 * @author Occurence
 */
public class CommandeController implements Initializable {

    @FXML
    private TextField t_prod;
    @FXML
    private TextField t_pay;
    @FXML
    private TextField t_quant;
    @FXML
    private TableView<Commande> tab_C;
    @FXML
    private Button ret;
    
    private int ID;
    
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
            CommandeCRUD comc = new CommandeCRUD();

            tab_C.setEditable(true);

            TableColumn<Commande,String> id_com = new TableColumn("ID Commande");

            id_com.setCellValueFactory(
                    new PropertyValueFactory<>("ID_Commande"));


            TableColumn<Commande,String> id_p = new TableColumn("ID Produit");

            id_p.setCellValueFactory(
                    new PropertyValueFactory<>("ID_Produit"));

            TableColumn<Commande,String> id_pay = new TableColumn("ID Payment");

            id_pay.setCellValueFactory(
                    new PropertyValueFactory<>("ID_Payment"));



            TableColumn<Commande,String> quant = new TableColumn("Quantitée");

            quant.setCellValueFactory(
                    new PropertyValueFactory<>("Quantitee"));

            TableColumn<Commande,String> prix = new TableColumn("Prix");

            prix.setCellValueFactory(
                    new PropertyValueFactory<>("Prix"));

            TableColumn<Commande,String> s_c = new TableColumn("Status Commande");

            s_c.setCellValueFactory(
                    new PropertyValueFactory<>("Status_commande"));

            tab_C.getColumns().add(id_com);
            tab_C.getColumns().add(id_p);
            tab_C.getColumns().add(id_pay);
            tab_C.getColumns().add(quant);
            tab_C.getColumns().add(prix);
            tab_C.getColumns().add(s_c);

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

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CRUD.fxml"));
        Parent root = loader.load();
            
        ret.getScene().setRoot(root);
    }

    @FXML
    private void AjouterCommande(ActionEvent event) {
        int id_p = Integer.parseInt(t_prod.getText());
        int pay = Integer.parseInt(t_pay.getText());
        int quant = Integer.parseInt(t_quant.getText());
        
        Commande com = new Commande();
        com.setID_Produit(id_p);
        com.setID_Payment(pay);
        com.setQuantitee(quant);
        CommandeCRUD comc = new CommandeCRUD();
        comc.Ajouter(com);
        
        
        tab_C.getItems().clear();
        try {
            tab_C.getItems().addAll(comc.AfficherCommande());
        } catch (SQLException ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Select_contenu(MouseEvent event) {
        Commande commandeM_S =  tab_C.getSelectionModel().selectedItemProperty().get();
        
        ID = commandeM_S.getID_Commande();
        t_prod.setText(String.valueOf(commandeM_S.getID_Produit()));
        t_pay.setText(String.valueOf(commandeM_S.getID_Payment()));
        t_quant.setText(String.valueOf(commandeM_S.getQuantitee()));
        
        
    }

    @FXML
    private void ModifierCommande(ActionEvent event) {
        
        int id_p = Integer.parseInt(t_prod.getText());
        int pay = Integer.parseInt(t_pay.getText());
        int quant = Integer.parseInt(t_quant.getText());
        
        Commande com = new Commande();
        com.setID_Commande(ID);
        com.setID_Produit(id_p);
        com.setID_Payment(pay);
        com.setQuantitee(quant);
        CommandeCRUD comc = new CommandeCRUD();
        comc.Modifier(com);
        
        
        tab_C.getItems().clear();
        try {
            tab_C.getItems().addAll(comc.AfficherCommande());
        } catch (SQLException ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    
}
