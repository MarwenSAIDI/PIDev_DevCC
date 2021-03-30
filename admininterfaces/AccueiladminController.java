/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admininterfaces;

import com.jfoenix.controls.JFXButton;
import entities.client;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.Clientservice;
import services.PanierCRUD;
import services.UserSession;

/**
 * FXML Controller class
 *
 * @author foura
 */
public class AccueiladminController implements Initializable {

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
    private BorderPane pane;
    private final ObservableList <PieChart.Data> details =FXCollections.observableArrayList();
        private PieChart piechart;
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        int ca=0;int ci=0; int cb=0;
        labelemail.setText( UserSession.instance.getUserName());
        Clientservice c=new Clientservice();
                 List <client> listc = c.Listclient();
                 for( int i=0;i<listc.size();i++)
                 { if (listc.get(i).getEtat().equals("attente"))
                     ca++;
                else if (listc.get(i).getEtat().equals("inscrit"))
                     ci++;
                else cb++;
                 
                 
                 
                 
                 
                 
                 }
                 details.addAll(new PieChart.Data("Banned clients",cb ),new PieChart.Data("Registered clients",ci ),new PieChart.Data("Waiting clients",ca ))  ;
                 piechart=new PieChart();
                 piechart.setData(details);
                 piechart.setTitle("statistique des clients");
                 piechart.setLabelsVisible(true);
                 
                 pane.setCenter(piechart);
                 Notifications  notif= Notifications.create();
                 if(ca>0)
                 {notif.title("vous avez "+ca+" clients en attente de votre permission");
notif.text("vous avez "+ca+" clients en attente de votre permission");
notif.graphic(null);
notif.hideAfter(Duration.seconds(30));
notif.position(Pos.TOP_RIGHT);
notif.showConfirm();}

        // TODO
        
        PanierCRUD panc = new PanierCRUD();
        ObservableList<PieChart.Data> pieChartDataPanier = null;
        
        try {
            pieChartDataPanier = FXCollections.observableArrayList(
                    new PieChart.Data("En cours", panc.AfficherPanier().stream().filter(p-> p.getStatus_panier().equals("En cours")).count()),
                    new PieChart.Data("Abondonner", panc.AfficherPanier().stream().filter(p-> p.getStatus_panier().equals("Abondonner")).count()),
                    new PieChart.Data("Payer", panc.AfficherPanier().stream().filter(p-> p.getStatus_panier().equals("Payer")).count()),
                    new PieChart.Data("Livrer", panc.AfficherPanier().stream().filter(p-> p.getStatus_panier().equals("Livrer")).count()));
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        final PieChart chartPanier = new PieChart(pieChartDataPanier);
        chartPanier.setTitle("Status des paniers");
        
        pane.setRight(chartPanier);
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
    
}
