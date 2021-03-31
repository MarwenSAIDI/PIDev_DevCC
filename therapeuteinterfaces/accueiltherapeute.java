/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package therapeuteinterfaces;

import com.jfoenix.controls.JFXButton;
import entities.Reservationconsultation;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import services.UserSession;
import services.servicereservation;

/**
 * FXML Controller class
 *
 * @author foura
 */
public class accueiltherapeute implements Initializable {

    @FXML
    private Label labelemail;
    @FXML
    private Hyperlink deconnecter;
    @FXML
    private JFXButton PageReservationEventLabel;
    @FXML
    private JFXButton StatEvent;
    @FXML
    private JFXButton btncons;
    @FXML
    private JFXButton demres;
    @FXML
    private JFXButton stat;
    @FXML
    private PieChart circlechart;

    /**
     * Initializes the controller class.
     */
     private final ObservableList <PieChart.Data> listpie =FXCollections.observableArrayList();
   

    /**
     * Initializes the controller class.
     */
    servicereservation sr= new servicereservation();
    List <Reservationconsultation> listr = sr.Listreservation();
    @FXML
    private JFXButton btnEvent;
    private JFXButton btngererReservEvent;
    @FXML
    private JFXButton btnaccueil;
    @FXML
    private JFXButton recommend;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

                   labelemail.setText( UserSession.instance.getUserName());


            int t1=0;
        int t2=0;
         for( int i=0;i<listr.size();i++)
                 { if (listr.get(i).getType().equals("en ligne"))
                     t1++;
                else  
                     t2++;
                 
                 
                 
                 
                 
                 
                 }
    
                         listpie.addAll(new PieChart.Data("Les réservations en ligne ",t1 ),new PieChart.Data("Les réservations à domicile",t2 ))  ;
circlechart.setData(listpie);
circlechart.setTitle("statistique des réservations");
circlechart.setLabelsVisible(true);

                   
                   
                   
    }    

    @FXML
    private void deconnexion(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/authentification/authentification.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            deconnecter.getScene().setRoot(root);
            
    }

    @FXML
    private void PageReservationEvent(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReservationEventTherapeute.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            PageReservationEventLabel.getScene().setRoot(root);
            
    }

    @FXML
    private void cons(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Crudconsultation.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btncons.getScene().setRoot(root);
    }

    @FXML
    private void demanderes(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("affichagereservationtherapeute.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            demres.getScene().setRoot(root);
    }

    @FXML
    private void statclick(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chartcons.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            stat.getScene().setRoot(root);
    }


    @FXML
    private void gotocrudevent(MouseEvent event)throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CrudEvent.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btnEvent.getScene().setRoot(root);
            
    }

    @FXML
    private void gotostatevent(MouseEvent event)throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StatEventTherapeute.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            StatEvent.getScene().setRoot(root);
            
    }

    @FXML
    private void circleclick(MouseEvent event) {
    }

    @FXML
    private void gotoaccueil(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accueiltherapeute.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            recommend.getScene().setRoot(root);
    }

    @FXML
    private void Pagerecomm(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Recommandation.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            recommend.getScene().setRoot(root);
    }

    
}
