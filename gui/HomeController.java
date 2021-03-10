/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudvfinal;
import crudvfinal.entities.client;

import crudvfinal.services.Clientservice;
import crudvfinal.services.UserSession;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author yassi
 */
public class HomeController implements Initializable {

   
    @FXML
    private Label textth;
    @FXML
    private Label textcmient;
    @FXML
    private Label labelemail;
    @FXML
    private Hyperlink deconnecter;
    private final ObservableList <PieChart.Data> details =FXCollections.observableArrayList();
        private PieChart piechart;
    @FXML
    private BorderPane pane;
    
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
notif.hideAfter(Duration.seconds(10));
notif.position(Pos.TOP_RIGHT);
notif.showConfirm();}

                 
                 

    }    

    @FXML
    private void gotoGestiontherapeutes(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);

    }

    @FXML
    private void gotoGestionclients(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Gestionclient.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textcmient.getScene().setRoot(root);
    }

    @FXML
    private void deconnexion(MouseEvent event) throws IOException {
         UserSession.instance.cleanUserSession();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("authentification.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            deconnecter.getScene().setRoot(root);
    }

    

    

    
    
}
