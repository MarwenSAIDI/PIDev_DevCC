/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientinterface;

import Entite.consultation;
import Service.serviceconsultation;
import backgroundcontroller.UIController;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import TEST.consultationappel;
import Service.serviceconsultation;
import Service.servicereservation;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import projet.UneconsultationController;
import TEST.test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
public class Clientinterfacepackage implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private Button retid;
    private List<consultation> consultations = new ArrayList<>();
    private Image image;
    private consultationappel consultationappels;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private VBox vboox;
    @FXML
    private Label TitreLable;
    @FXML
    private Label consPriceLabel;
    @FXML
    private ImageView consImg;
    serviceconsultation se= new serviceconsultation();
        private          servicereservation svr= new servicereservation();
    @FXML
    private Button prRdv;
    @FXML
    private Label nbreservations;
    @FXML
    private ImageView reservbtn;
    public static String imgg="";
    @FXML
    private Label nbreservations1;
    
    /**
     * Initializes the controller class.
     */


      private void setChosenconsult(consultation cons) {

        TitreLable.setText(cons.getTitre());
        consPriceLabel.setText(test.CURRENCY + cons.getPrix());
imgg=cons.getImage();
        Image i;
        try {
            i= new Image(new FileInputStream(imgg));
                                consImg.setImage(i);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Clientinterfacepackage.class.getName()).log(Level.SEVERE, null, ex);
        }

   
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        notiff();
         int k=svr.calculnbnonvalideclient(39);
        int y=svr.calculnb(39);
        
        nbreservations.setText(String.valueOf(k));
                nbreservations1.setText(String.valueOf(y-k));

        consultations.addAll(se.Listconsultation());
        if (consultations.size()>0)
        {

                        setChosenconsult(consultations.get(0));

            consultationappels= new consultationappel()
            {
                @Override
                public void onClickCons(consultation cons) {
                                        setChosenconsult(cons);

                    
                }
                
            };
        }
         int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < consultations.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/projet/uneconsultation.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                UneconsultationController unec = fxmlLoader.getController();
                unec.setData(consultations.get(i),consultationappels);

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
            e.printStackTrace();
        }
        
        
      //  Image image = new Image(getClass().getResourceAsStream("/instagram/pics/avatar.JPG"));
        
       /* menhouni:
      Image image = new Image("file:///C:/Users/foura/OneDrive/Documents/template/Instagram/src/instagram/pics/avatar.jpg");
imgtest1.setImage(image);   */
   
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
   
    
    


    
    
    
    
    
    
    
    
    

  /*  public ObservableList<consultation> getconsultation() {
        ObservableList<consultation> consultations = FXCollections.observableArrayList();
        serviceconsultation cs = new serviceconsultation();
        List<consultation> lc = cs.Listconsultation();
        for (consultation c : lc) {
            consultations.add(c);
        }
        return consultations;
    }*/

    @FXML
    private void rdvaction(ActionEvent event) {
          try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projet/PrendreRDV.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            prRdv.getScene().setRoot(root);
    } catch(Exception e) {
       e.printStackTrace();
      }
    }

    @FXML
    private void nbreservationsclicked(MouseEvent event) {
        
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