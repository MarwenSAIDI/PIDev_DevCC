/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientinterfaces;

import static Clientinterfaces.ReservationrdvController.imgg;
import entities.Reservationconsultation;
import services.servicereservation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import static utils.JavaMailUtil.sendMail;
import static Clientinterfaces.ReservationaffichageController.restest;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import services.UserSession;
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
                       private          servicereservation svr= new servicereservation();
                           private List<String> listheure = new ArrayList<>();

    @FXML
    private Label nbreservations1;
    /**
     * Initializes the controller class.
     */
    public static int fcount=0;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
                                         labelemail.setText( UserSession.instance.getUserName());

nbreservations.setText(String.valueOf(svr.calculnb(cinclient)));
Image i;
        
        try {
            i= new Image(new FileInputStream(restest.getImage()));
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
        int k=svr.calculnbnonvalideclient(cinclient);
        int y=svr.calculnb(cinclient);
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
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Clientinterfaces/reservationrdv.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            PageRDV.getScene().setRoot(root);
    } catch(Exception e) {
       e.printStackTrace();
      }
    }


    @FXML
    private void selectheure(ActionEvent event) {
                      ok=1;


    }
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
        String s="";

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
        int id=restest.getIdreservation();
             Reservationconsultation r= new Reservationconsultation(id,22,cinclient,date,type,s);
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
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Clientinterfaces/reservationaffichage.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btnreserv.getScene().setRoot(root);
    } catch(Exception e) {
       e.printStackTrace();
      }
                         nbreservations.setText(String.valueOf(svr.calculnb(cinclient)));
                     }
          String test=datetest.getValue().toString();
        String test2=s;
        String mail= UserSession.instance.getUserName();
        String contenu="Votre modification a été faite avec succés \n Votre date de réservation est  : " + test +"\n avec l'heure : "+test2+" \nsoyez les bienvenus";
      
        sendMail(mail,contenu);
        
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
