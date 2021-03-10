/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import static Clientinterface.Clientinterfacepackage.imgg;
import Entite.Reservationconsultation;
import Service.servicereservation;
import TEST.reservationappel;
import com.jfoenix.controls.JFXButton;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
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
public class ReservationaffichageController implements Initializable {

    @FXML
    private Button retid;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private VBox vboox;
    @FXML
    private Label TitreLable;
    @FXML
    private ImageView consImg;
    @FXML
    private Button prRdv;
    @FXML
    private ImageView reservbtn;
    @FXML
    private Label typelabel;
    @FXML
    private Label datelabel;
    @FXML
    private Label heurelabel;
    @FXML
    private Label nbreservations;
    private reservationappel reservationappels;
    
        servicereservation sr= new servicereservation();

        private          servicereservation svr= new servicereservation();
    private List<Reservationconsultation> reservations = new ArrayList<>();
    @FXML
    private JFXButton PageRDV;
    @FXML
    private Button supp;
    public static Reservationconsultation restest;
    @FXML
    private Label nbreservations1;
    

    /**
     * Initializes the controller class.
     */
    
 
    private void setChosenconsult(Reservationconsultation res) {
restest=res;
        TitreLable.setText(res.getEtat());
        typelabel.setText(res.getType());
        datelabel.setText(res.getDate().toString());
        heurelabel.setText(res.getHeure());
       
        Image i;
        try {
            i= new Image(new FileInputStream(res.getImage()));
                                consImg.setImage(i);

        } catch (FileNotFoundException ex) {
        }

   
    }
  private void retimage(String image)
  {
      
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
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
           int k=svr.calculnbnonvalideclient(39);
        int y=svr.calculnb(39);
     nbreservations.setText(String.valueOf(k));
                nbreservations1.setText(String.valueOf(y-k));
        reservations.addAll(sr.Listreservationclient(39));
        System.out.println("ok1");
        if (reservations.size()>0)
        {
            setChosenconsult(reservations.get(0));
            reservationappels = new reservationappel() {
                @Override
                public void onClickreserv(Reservationconsultation res) {
                    setChosenconsult(res);
                    restest=res;
                }
            };
        }
            int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < reservations.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/projet/unrdv.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                UnrdvController unec = fxmlLoader.getController();
                unec.setData(reservations.get(i),reservationappels);

                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        
        // TODO
    }    
    
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
    private void rdvaction(ActionEvent event) {
        
                 try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projet/modifierRdv.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            PageRDV.getScene().setRoot(root);
    } catch(Exception e) {
       e.printStackTrace();
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
    private void suppaction(ActionEvent event) {
        sr.delete(restest);
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
    }
    
}
