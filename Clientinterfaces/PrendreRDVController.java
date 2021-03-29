/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientinterfaces;

import static Clientinterfaces.ReservationrdvController.imgg;
import static Clientinterfaces.ReservationrdvController.idd;

import entities.Reservationconsultation;
import utils.JavaMailUtil;
import com.teknikindustries.bulksms.SMS;
import services.servicereservation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import services.UserSession;
import static utils.JavaMailUtil.sendMail;
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
    private List<String> listheure = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private JFXButton btnarticle;
    @FXML
    private JFXButton btnlivre;
    @FXML
    private JFXButton deconnexion;
    @FXML
    private JFXButton btnPageEvent;
    @FXML
    private Label labelemail;
    @FXML
    private FontAwesomeIcon btnUser;
    private int cinclient=svr.rendrecin(UserSession.instance.getUserName());
    private  int ok=0;
    public void test(){
    
    
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//list.remove("09:00");
//SMS test = new SMS();
//test.SendSMS("tchiz", "Azerty123", "test", "26868706", "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");

            
                                 labelemail.setText( UserSession.instance.getUserName());

        
nbreservations.setText(String.valueOf(svr.calculnb(cinclient)));
        Image i;
        
        try {
            i= new Image(new FileInputStream(imgg));
                                            Img.setImage(i);

        } catch (FileNotFoundException ex) {
        }
             int k=svr.calculnbnonvalideclient(cinclient);
        int y=svr.calculnb(cinclient);
        
        nbreservations.setText(String.valueOf(k));
                nbreservations1.setText(String.valueOf(y-k));
                
    }    

 private void notiff()
    {
        int k=svr.calculnbnonvalide(cinclient);
        int y=svr.calculnb(cinclient);
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("Reservations non valides");
        tray.setMessage("il existe "+(y-k)+ " reservations confirmé");
tray.setNotificationType(NotificationType.INFORMATION);
tray.showAndDismiss(Duration.millis(2000));
    }
// checkbock: c1.getText() ou if c1.isSelected()
    
    
    
    
    
    
    
    

    private void test2ret(ActionEvent event) {
           try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Clientinterfaces/test.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            retid.getScene().setRoot(root);
    } catch(Exception e) {
       e.printStackTrace();
      }
    }

    @FXML
    private void selectheure(ActionEvent event) {
              ok=1;

    }

    @FXML
    private void revenirpageaffichconsult(ActionEvent event) {
                   try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Clientinterfaces/reservationrdv.fxml"));
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
                     LocalDate localDate = LocalDate.parse(ddd);

//     LocalDateTime now = LocalDateTime.now();  
//String  k= now.toString(); 
                      
                      
        if (tp.getValue().isAfter(localDate)) {
           b = false;
       }
      return b;

    }
    

    
    @FXML
    private void reservrdv(ActionEvent event) {

        	ZoneId defaultZoneId = ZoneId.systemDefault();
                 
  /*  if (isDateEqToDate(datetest))
        
    {
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("saisie invalide");
             alert.setHeaderText("Erreur de date");
        alert.setContentText("Veuillez choisir une date valide");
                alert.showAndWait();
    }
    else */
    
         if(((!c1.isSelected())&&(!c2.isSelected()))||(c1.isSelected()&&c2.isSelected()))  
             
         {   
             Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("saisie invalide");
             alert.setHeaderText("Erreur de type");
        alert.setContentText("Veuillez cochez en ligne ou a domicile");
                alert.showAndWait();

         }    
        else 
        if              (( datetest.getValue()==null) || (isDateEqToDate(datetest)))

                {     
                    Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("saisie invalide");
               alert.setHeaderText("Erreur de date");
        alert.setContentText("Veuillez saisir la date de rendez-vous"); 
                alert.showAndWait();

                }
      
        else if( ok==0)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("saisie invalide");
        alert.setHeaderText("Erreur d'heure");
        alert.setContentText("Veuillez saisir l'heure du rendez-vous"); 
                alert.showAndWait();

     
        }
        
         else
         {
                          s=comb.getSelectionModel().getSelectedItem().toString();

             String type="";
             if (c1.isSelected()){
                 type="en ligne";
             }
             else
                 type ="a domicile";
             LocalDate localDate = datetest.getValue(); 
             Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
//Date date = Date.from(Instant.now());

             Reservationconsultation r= new Reservationconsultation(313,idd,cinclient,date,type,s,imgg);
   

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
        String test=datetest.getValue().toString();
        String test2=s;
        String mail= UserSession.instance.getUserName();
        String contenu="Ajout avec succés ! \n Votre date de réservation est : " + test +" \n avec l'heure : "+test2+" \n Soyez les bienvenus ";
      
        sendMail(mail,contenu);
        Optional<ButtonType> btn = alert3.showAndWait();
     if (btn.get()==ButtonType.OK){
 try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Clientinterfaces/reservationrdv.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btnreserv.getScene().setRoot(root);
    } catch(Exception e) {
       e.printStackTrace();
      }
     }
                         nbreservations.setText(String.valueOf(svr.calculnb(cinclient)));
                     } 
         }
    
}
    

    @FXML
    private void reservbutton(MouseEvent event) {
        try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Clientinterfaces/reservationaffichage.fxml"));
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

    @FXML
    private void datee(MouseEvent event) {
      
    }

    @FXML
    private void heurrr(MouseEvent event) {
               ObservableList<String> list = FXCollections.observableArrayList("09:00","09:30","10:00","10:30","11:00","11:30","12:00","14:00","14:30","15:00","15:30","16:00");

        if (datetest.getValue()==null)
        {
             Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Date non saisie");
               alert.setHeaderText("Erreur ");
        alert.setContentText("Veuillez saisir la date de rendez-vous"); 
                alert.showAndWait();
        }
        else {
        listheure=svr.calculheuredate(datetest.getValue().toString());
                    for (int i = 0; i < listheure.size(); i++) {
                        list.remove(listheure.get(i));

                    }
                                comb.setItems(list);

        }
    }

       @FXML
    private void afficherarticle(MouseEvent event) throws IOException {
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Clientinterfaces/clientyassine.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btnarticle.getScene().setRoot(root);
    }

    @FXML
    private void afficherlivre(MouseEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Clientinterfaces/clientyassinelivre.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btnlivre.getScene().setRoot(root);
    }

    @FXML
    private void deconnecter(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/authentification/authentification.fxml"));
        
 Parent root = fxmlLoader.load();
 deconnexion.getScene().setRoot(root);
    }

    @FXML
    private void PageEvent(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AfficherEventClient.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btnarticle.getScene().setRoot(root);
    }

    @FXML
    private void gotoconsulterreservation(MouseEvent event) {
    }
      
   
    
}
