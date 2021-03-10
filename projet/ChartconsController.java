/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import Service.servicereservation;
import UtilsBD.DataBase;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
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
    private Label demres;
    @FXML
    private ImageView ntf;
    @FXML
    private Button idretour;
    @FXML
    private LineChart<String, Integer> idchart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private Label btncons;
         private Connection con;

    public ChartconsController(){
        DataBase db=new DataBase();
        con = db.getConnection();
    }

    /**
     * Initializes the controller class.
     */
    servicereservation sr= new servicereservation();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       int i=0;
        XYChart.Series <String,Integer> series = new XYChart.Series();
        idchart.setTitle("Le nombre de réservations");
        y.setLabel("Le nombre de réservations par date");
  PreparedStatement pst;
        try {
                            String requete="select distinct(date) from reservation ";//(ordre by test aprés)
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

    @FXML
    private void demanderes(MouseEvent event) {
                 try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projet/affichagereservationtherapeute.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            demres.getScene().setRoot(root);
    } catch(Exception e) {
       e.printStackTrace();
      }
    }
private void notiff()
    {
          servicereservation sr=new servicereservation();
        int k=sr.calculnbnonvalide();
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
    private void retourtest(ActionEvent event) {
    }

    @FXML
    private void cons(MouseEvent event) {
         try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projet/test.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btncons.getScene().setRoot(root);
    } catch(Exception e) {
       e.printStackTrace();
      }
    }
    
}
