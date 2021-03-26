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
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import pidev.entities.Utilisateur;
import pidev.services.PanierCRUD;
import pidev.services.PaymentCRUD;

/**
 * FXML Controller class
 *
 * @author Occurence
 */
public class CRUDController implements Initializable {

    
    private Utilisateur user;
    @FXML
    private Label b_panier;
    @FXML
    private Label b_pay;
    @FXML
    private Label b_Commande;
    @FXML
    private HBox h_b_c;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        PanierCRUD panc = new PanierCRUD();
        PaymentCRUD payc = new PaymentCRUD();
        
        ObservableList<PieChart.Data> pieChartDataPanier = null;
        ObservableList<PieChart.Data> pieChartDataPayment = null;
        try {
            
            pieChartDataPanier = FXCollections.observableArrayList(
                    new PieChart.Data("En cours", panc.AfficherPanier().stream().filter(p-> p.getStatus_panier().equals("En cours")).count()),
                    new PieChart.Data("Abondonner", panc.AfficherPanier().stream().filter(p-> p.getStatus_panier().equals("Abondonner")).count()),
                    new PieChart.Data("Payer", panc.AfficherPanier().stream().filter(p-> p.getStatus_panier().equals("Payer")).count()),
                    new PieChart.Data("Livrer", panc.AfficherPanier().stream().filter(p-> p.getStatus_panier().equals("Livrer")).count()));
            
            pieChartDataPayment = FXCollections.observableArrayList(
                    new PieChart.Data("Espèce", payc.AfficherPayment().stream().filter(p-> p.getMode_Payment().equals("Espèce")).count()),
                    new PieChart.Data("Carte", payc.AfficherPayment().stream().filter(p-> p.getMode_Payment().equals("Carte")).count()));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        final PieChart chartPanier = new PieChart(pieChartDataPanier);
        chartPanier.setTitle("Status des paniers");
        
        final PieChart chartPayment = new PieChart(pieChartDataPayment);
        chartPayment.setTitle("Mode de payment");
        
        h_b_c.getChildren().addAll(chartPanier,chartPayment);
        
        
        
    }    

    

    @FXML
    private void go_to_Dashboard(MouseEvent event) {
        
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CRUD.fxml"));
            Parent root = loader.load();
            b_pay.getScene().setRoot(root);
        }
        catch (IOException ex) {
            Logger.getLogger(CRUDController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void go_to_Panier(MouseEvent event) {
        
        try{
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Panier.fxml"));
            Parent root = loader.load();
            
            b_panier.getScene().setRoot(root);
          
        }
        catch (IOException ex) {
            Logger.getLogger(CRUDController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void go_to_Payment(MouseEvent event) {
        
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Payment.fxml"));
            Parent root = loader.load();
            b_pay.getScene().setRoot(root);
        }
        catch (IOException ex) {
            Logger.getLogger(CRUDController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void go_to_Commande(MouseEvent event) {
        
        try{
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Commande.fxml"));
            Parent root = loader.load();
            b_Commande.getScene().setRoot(root);
        }
        catch (IOException ex) {
            Logger.getLogger(CRUDController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    


    
    
}
