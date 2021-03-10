/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import static Clientinterface.Clientinterfacepackage.imgg;
import Entite.Reservationconsultation;
import Service.servicereservation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import static projet.ReservationaffichageController.restest;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author foura
 */
public class ModifierRdvController implements Initializable {

    @FXML
    private JFXButton PageRDV;
    @FXML
    private Label nbreservations;
    @FXML
    private Button retid;
    @FXML
    private VBox vboox;
    @FXML
    private ImageView Img;
    @FXML
    private Label Titre;
    @FXML
    private Label prix;
    @FXML
    private JFXCheckBox c1;
    @FXML
    private JFXCheckBox c2;
    @FXML
    private JFXDatePicker datetest;
    @FXML
    private ComboBox comb;
    @FXML
    private Button btnreserv;
    @FXML
    private ImageView reservbtn;
    private String s="";
                       private          servicereservation svr= new servicereservation();
    @FXML
    private Label nbreservations1;
    /**
     * Initializes the controller class.
     */
    public static int fcount=0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
        ObservableList<String> list = FXCollections.observableArrayList("09:00","09:30","10:00","10:30","11:00","11:30","12:00","14:00","14:30","15:00","15:30","16:00");
comb.setItems(list);
nbreservations.setText(String.valueOf(svr.calculnb(39)));
Image i;
        
        try {
            i= new Image(new FileInputStream(restest.getImage()));
                                            Img.setImage(i);

        } catch (FileNotFoundException ex) {
        }
         int k=svr.calculnbnonvalideclient(39);
        int y=svr.calculnb(39);
        
        nbreservations.setText(String.valueOf(k));
                nbreservations1.setText(String.valueOf(y-k));

    }    
    
 private void notiff()
    {
        int k=svr.calculnbnonvalideclient(39);
        int y=svr.calculnb(39);
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("Reservations non valides");
        tray.setMessage("il existe "+(y-k)+ " reservations confirmé");
tray.setNotificationType(NotificationType.INFORMATION);
tray.showAndDismiss(Duration.millis(2000));
    }
    @FXML
    private void revenirpageaffichconsult(ActionEvent event) {
         try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projet/Clientinterface.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            PageRDV.getScene().setRoot(root);
    } catch(Exception e) {
       e.printStackTrace();
      }
    }

    @FXML
    private void test2ret(ActionEvent event) {
    }

    @FXML
    private void selectheure(ActionEvent event) {
             s=comb.getSelectionModel().getSelectedItem().toString();

    }

    @FXML
    private void reservrdv(ActionEvent event) {
        ZoneId defaultZoneId = ZoneId.systemDefault();

         if((c1.getText().isEmpty()&& c2.getText().isEmpty())  || (c1.isSelected()&&c2.isSelected()) 
             || datetest.getValue()==null || s.isEmpty())
        {
                 Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("data inalide");
        alert.setHeaderText("Erreur de saisie");
        alert.setContentText("Verifier les champs vides"); 
     
        alert.showAndWait();
        }
         else
         {
             String type="";
             if (c1.isSelected()){
                 type="en ligne";
             }
             else
                 type ="a domicile";
             LocalDate localDate = datetest.getValue(); 
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        int id=restest.getIdreservation();
             Reservationconsultation r= new Reservationconsultation(id,22,39,date,type,s);
             svr.update(r);
             Reservationconsultation res= new Reservationconsultation(r.getIdreservation(),"En attente de confirmation");
svr.updateetat(res);
fcount++;

     if (svr.k==2){
                         
Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Heure déja reservé ");
        alert.setHeaderText("Veuillez choisir une date qui vous convient ");
        alert.setContentText("Merci"); 
     
        alert.showAndWait();
         }
 else
                     {
                                      Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
        alert.setContentText("Reservation modifié!"); 
        Optional<ButtonType> btn = alert.showAndWait();
     if (btn.get()==ButtonType.OK){
 try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projet/reservationaffichage.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btnreserv.getScene().setRoot(root);
    } catch(Exception e) {
       e.printStackTrace();
      }
                         nbreservations.setText(String.valueOf(svr.calculnb(39)));
                     }
        
    }
    }
    }
    

    @FXML
    private void reservbutton(MouseEvent event) {
        try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projet/reservationaffichage.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            reservbtn.getScene().setRoot(root);
    } catch(Exception e) {
       e.printStackTrace();
      }
    }

    @FXML
    private void notif(MouseEvent event) {
        notiff();
    }
    
}
