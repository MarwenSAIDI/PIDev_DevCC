/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package therapeuteinterfaces;

import Clientinterfaces.AfficherEventClientController;
import com.jfoenix.controls.JFXButton;
import entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import services.UserSession;
import utils.DataBase;


/**
 * FXML Controller class
 *
 * @author user
 */
public class StatEventTherapeuteController implements Initializable {

    @FXML
    private BarChart<?, ?> StatNbrReserEvent;
    @FXML
    private NumberAxis YnbRserv;
    @FXML
    private CategoryAxis XEvent;

    @FXML
    private BarChart<?, ?> StatTypeEvent;
    @FXML
    private NumberAxis YStatType;
    @FXML
    private CategoryAxis XStatType;
    
      private Statement ste;
                DataBase db=new DataBase();
               private Connection con=db.getConnection();
    @FXML
    private PieChart PaiementPieChart;
 private ObservableList data;
    private JFXButton GererEventLabel;
    @FXML
    private JFXButton StatEvent;
    @FXML
    private JFXButton PageReservationEventLabel;
    @FXML
    private Label labelemail;
    @FXML
    private Hyperlink deconnecter;
    @FXML
    private JFXButton btnaccueil;
    @FXML
    private JFXButton btnEvent;
    @FXML
    private JFXButton recommend;
    @FXML
    private JFXButton btncons;
    @FXML
    private JFXButton demres;
    @FXML
    private JFXButton stat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                                   labelemail.setText( UserSession.instance.getUserName());

        int nb= 0;
        XYChart.Series series3 = new XYChart.Series<>();
        
         try {
     String requete = "SELECT nb_reservation,titre FROM evenement ";
     PreparedStatement pst = con.prepareStatement(requete);               
    
     ResultSet e = pst.executeQuery();
     while (e.next()) {
        
         
         series3.getData().add(new XYChart.Data(e.getString("titre"),e.getInt("nb_reservation")));
       
}
    }
    catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        StatNbrReserEvent.getData().addAll(series3);
                
        
        XYChart.Series type = new XYChart.Series<>();
        
                       try {
     String requete = "SELECT sum(nb_reservation) as nb_reservations ,type FROM evenement  group by type";
     PreparedStatement pst = con.prepareStatement(requete);               
    
     ResultSet e = pst.executeQuery();
     while (e.next()) {
        
         
         type.getData().add(new XYChart.Data(e.getString("type"),e.getInt("nb_reservations")));
       
}
    }
    catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        StatTypeEvent.getData().addAll(type);
                  
       // SELECT SUM(mode_paiement) AS nbplace_total FROM reservation_event WHERE ? =id_event
        

         ObservableList<PieChart.Data> pieChartData  = FXCollections.observableArrayList();
                      
   try {
                          
     String requete = "SELECT mode_paiement,count(mode_paiement) as type_paiement FROM reservation_event  group by mode_paiement  ";
     PreparedStatement pst = con.prepareStatement(requete);               
    
     ResultSet e = pst.executeQuery();
     while (e.next()) {
                     
         pieChartData.add(  new PieChart.Data(e.getString("mode_paiement"), e.getInt("type_paiement")));
         
                    
                            System.out.println(e.getInt("type_paiement"));
                            System.out.println(e.getString("mode_paiement"));
                            
                          
               
}}
    
    catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }                            PaiementPieChart.setData(pieChartData);

    
    
    }    

    private void PageGerverEvent(MouseEvent event) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CrudEvent.fxml"));
        try {
            Parent root = loader.load();
            GererEventLabel.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherEventClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

