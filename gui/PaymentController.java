/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;


import java.awt.Font;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import pidev.entities.Payment;
import pidev.services.PaymentCRUD;
import pidev.services.TextFieldException;

/**
 * FXML Controller class
 *
 * @author Occurence
 */
public class PaymentController implements Initializable {

    @FXML
    private TextField t_pf;
    @FXML
    private ComboBox<String> cb_mp;
    @FXML
    private TableView<Payment> tab_pay;
    private Button ret;
    
    private int ID;
    @FXML
    private TextField t_Pan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       
        cb_mp.getItems().addAll("Espece", "Carte");
        cb_mp.getSelectionModel().select("Espèce");
     
        try{
            PaymentCRUD payc = new PaymentCRUD();
            tab_pay.setEditable(true);

            TableColumn<Payment,String> id_p = new TableColumn("ID Payment");

            id_p.setCellValueFactory(
                    new PropertyValueFactory<>("ID_Payment"));
            
            TableColumn<Payment,String> id_pan = new TableColumn("ID Panier");

            id_pan.setCellValueFactory(
                    new PropertyValueFactory<>("ID_Panier"));

            TableColumn<Payment,String> p_f = new TableColumn("Prix Totale");

            p_f.setCellValueFactory(
                    new PropertyValueFactory<>("Prix_F"));

            TableColumn<Payment,String> m_p = new TableColumn("Mode de Payment");

            m_p.setCellValueFactory(
                    new PropertyValueFactory<>("Mode_Payment"));

            tab_pay.getColumns().add(id_p);
            tab_pay.getColumns().add(id_pan);
            tab_pay.getColumns().add(p_f);
            tab_pay.getColumns().add(m_p);

            try {
                tab_pay.getItems().addAll(payc.AfficherPayment());
            } catch (SQLException ex) {
                Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void AjouterPayment(ActionEvent event) {
        
            
        try {
            TextFieldException.verifEmpty(t_pf.getText());
            Double pf = Double.parseDouble(t_pf.getText());
            TextFieldException.verifEmpty(cb_mp.getValue());
            String mp = cb_mp.getValue();
            Payment pay = new Payment(22,0,pf,mp);
            PaymentCRUD payc = new PaymentCRUD();
            payc.Ajouter(pay);
            
            
            tab_pay.getItems().clear();
            try {
                tab_pay.getItems().addAll(payc.AfficherPayment());
            } catch (SQLException ex) {
                Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (TextFieldException e) {
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
        Payment paymentM_S =  tab_pay.getSelectionModel().selectedItemProperty().get();
        t_pf.setText(String.valueOf(paymentM_S.getPrix_F()));
        cb_mp.getSelectionModel().select(paymentM_S.getMode_Payment());
        
        ID = paymentM_S.getID_Payment();
        
        
    }

    @FXML
    private void ModifierPayment(ActionEvent event) {
        try{
            TextFieldException.verifEmpty(t_pf.getText());
            Double pf = Double.parseDouble(t_pf.getText());
            TextFieldException.verifEmpty(cb_mp.getValue());
            String mp = cb_mp.getValue();

            Payment pay = new Payment();
            pay.setID_Payment(ID);
            pay.setPrix_F(pf);
            pay.setMode_Payment(mp);
            PaymentCRUD payc = new PaymentCRUD();
            payc.Modifier(pay);


            tab_pay.getItems().clear();


            try {
                tab_pay.getItems().addAll(payc.AfficherPayment());
            } catch (SQLException ex) {
                Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch(TextFieldException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty fields");
            alert.setHeaderText(e.getMessage());
            
            alert.showAndWait();
        }
    }

    @FXML
    private void SupprimerPayment(ActionEvent event) {
        PaymentCRUD payc = new PaymentCRUD();
        
        payc.Supprimer(ID);

 
        tab_pay.getItems().clear();
       
        
        try {
            tab_pay.getItems().addAll(payc.AfficherPayment());
        } catch (SQLException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    

    
}
