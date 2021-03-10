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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author foura
 */
public class PrendreRDVController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private Button retid;
    @FXML
    private VBox vboox;
    @FXML
    private ComboBox  comb;
    @FXML
    private JFXButton PageRDV;
    @FXML
    private JFXCheckBox c1;
    @FXML
    private JFXCheckBox c2;
    @FXML
    private Button btnreserv;
    @FXML
    private ImageView Img;
    @FXML
    private Label Titre;
    @FXML
    private Label prix;
    @FXML
    private JFXDatePicker datetest;
private static String s="";
    @FXML
    private Label nbreservations;
                       private          servicereservation svr= new servicereservation();
    @FXML
    private ImageView reservbtn;
    @FXML
    private Label nbreservations1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
ObservableList<String> list = FXCollections.observableArrayList("09:00","09:30","10:00","10:30","11:00","11:30","12:00","14:00","14:30","15:00","15:30","16:00");
comb.setItems(list);
nbreservations.setText(String.valueOf(svr.calculnb(39)));
        Image i;
        
        try {
            i= new Image(new FileInputStream(imgg));
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
        int k=svr.calculnbnonvalide();
        int y=svr.calculnb(39);
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("Reservations non valides");
        tray.setMessage("il existe "+(y-k)+ " reservations confirmé");
tray.setNotificationType(NotificationType.INFORMATION);
tray.showAndDismiss(Duration.millis(2000));
    }
// checkbock: c1.getText() ou if c1.isSelected()
    
    
    
    
    
    
    
    

    @FXML
    private void test2ret(ActionEvent event) {
           try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projet/test.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            retid.getScene().setRoot(root);
    } catch(Exception e) {
       e.printStackTrace();
      }
    }

    @FXML
    private void selectheure(ActionEvent event) {
     s=comb.getSelectionModel().getSelectedItem().toString();
      
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
   /*public static boolean isDateEqToDate(JFXDatePicker tp) {
        boolean b = true;
        String msg = null;
                         SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
    Date date2 = new Date();  
     LocalDateTime now = LocalDateTime.now();  

   String k = tp.getValue().toString();
   
       System.out.println(k);
          LocalDate localDate = LocalDate.parse(k);
        if (tp.getValue().isAfter(localDate)) {
            b = false;
        }
        return b;

    }*/
   public static boolean isDateEqToDate(JFXDatePicker tp) {
            
        boolean b = true;
        String msg = null;
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
           
            Date date2 = new Date();  
             String ddd = dateFormat.format(date2); 
     LocalDateTime now = LocalDateTime.now();  
String  k= now.toString(); 
        LocalDate localDate = LocalDate.parse(ddd);
                      
                      
        if (tp.getValue().isAfter(localDate)) {
           b = false;
       }
      return b;

    }
    

    
    @FXML
    private void reservrdv(ActionEvent event) {
        String ch="";
        	ZoneId defaultZoneId = ZoneId.systemDefault();
                 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                 
    if (isDateEqToDate(datetest))
        
    {
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("saisie invalide");
             alert.setHeaderText("Erreur de date");
        alert.setContentText("Veuillez choisir une date valide");
                alert.showAndWait();
    }
    else 
    
         if(((!c1.isSelected())&&(!c2.isSelected()))||(c1.isSelected()&&c2.isSelected()))  
             
         {   
             Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("saisie invalide");
             alert.setHeaderText("Erreur de type");
        alert.setContentText("Veuillez cochez en ligne ou a domicile");
                alert.showAndWait();

         }    
        else 
        if              ( datetest.getValue()==null)

                {     
                    Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("saisie invalide");
               alert.setHeaderText("Erreur de date");
        alert.setContentText("Veuillez saisir la date de rendez-vous"); 
                alert.showAndWait();

                }
        else if( s.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("saisie invalide");
        alert.setHeaderText("Erreur d'heure");
        alert.setContentText("Veuillez saisir l'heure du rendez-vous"); 
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
//Date date = Date.from(Instant.now());

             Reservationconsultation r= new Reservationconsultation(313,22,39,date,type,s,imgg);
   

                     svr.ajouter1(r);

                     if (svr.k==1){
                         
        Alert alert2 = new Alert(Alert.AlertType.WARNING);
        alert2.setTitle("Heure déja reservé ");
        alert2.setHeaderText("Veuillez choisir une autre date ou heure qui vous convient ");
        alert2.setContentText("Merci"); 
     
        alert2.showAndWait();
         }
                     
                     else
                     {
                             Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
    
        alert3.setContentText("Reservation ajouté!"); 
        Optional<ButtonType> btn = alert3.showAndWait();
     if (btn.get()==ButtonType.OK){
 try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projet/Clientinterface.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btnreserv.getScene().setRoot(root);
    } catch(Exception e) {
       e.printStackTrace();
      }
     }
                         nbreservations.setText(String.valueOf(svr.calculnb(39)));
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
