/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package therapeuteinterfaces;

import Clientinterfaces.AfficherEventClientController;
import entities.Reservationconsultation;
import services.servicereservation;
import utils.DataBase;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import services.UserSession;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author foura
 */
public class ChartconsController implements Initializable {

    @FXML
    private JFXButton demres;
    @FXML
    private ImageView ntf;
    @FXML
    private LineChart<String, Integer> idchart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private JFXButton btncons;
         private Connection con;
    @FXML
    private JFXButton StatEvent;
    @FXML
    private JFXButton PageReservationEventLabel;
    @FXML
    private JFXButton stat;
    @FXML
    private PieChart circlech;
    private final ObservableList <PieChart.Data> listpie =FXCollections.observableArrayList();
    @FXML
    private JFXButton btnaccueil;
    @FXML
    private Label labelemail;
    @FXML
    private Hyperlink deconnecter;
    @FXML
    private JFXButton btnEvent;
    @FXML
    private JFXButton recommend;
    
    public ChartconsController(){
        DataBase db=new DataBase();
        con = db.getConnection();
    }

    /**
     * Initializes the controller class.
     */
    servicereservation sr= new servicereservation();

    private int cinclient=sr.rendrecin(UserSession.instance.getUserName());
        List <Reservationconsultation> listr = sr.Listreservationtherapeute(cinclient);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
circlech.setData(listpie);
circlech.setTitle("statistique des réservations");
circlech.setLabelsVisible(true);

       int i=0;
        XYChart.Series <String,Integer> series = new XYChart.Series();
        idchart.setTitle("Le nombre de réservations");
        y.setLabel("Le nombre de réservations par date");
  PreparedStatement pst;
        try {
                            String requete="select distinct(date) from reservation where idconsultation='"+cinclient+"'";//(ordre by test aprés)
            pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                i++;
                Integer nb=sr.calculnbdate(rs.getDate(1).toString());
                System.out.println(sr.calculnbdate(rs.getDate(1).toString()));
                series.getData().add(new XYChart.Data<>(rs.getDate(1).toString(),nb));
            }
        } catch (SQLException ex) {
        }
        System.out.println(i);
idchart.getData().add(series);

    }    

   
private void notiff()
    {
          servicereservation sr=new servicereservation();
        int k=sr.calculnbnonvalide(cinclient);
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("Reservations non validées");
        tray.setMessage("il existe "+k + " reservations non validées");
tray.setNotificationType(NotificationType.INFORMATION);
tray.showAndDismiss(Duration.millis(2000));
    }
    @FXML
    private void notif(MouseEvent event) {
        notiff();
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
