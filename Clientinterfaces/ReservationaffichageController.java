/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientinterfaces;

import static Clientinterfaces.ReservationrdvController.imgg;
import entities.Reservationconsultation;
import services.servicereservation;
import Clientinterfaces.reservationappel;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
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
public class ReservationaffichageController implements Initializable {

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
    @FXML
    private TextField rech;
    private List<String> datec = new ArrayList<>();
    @FXML
    private ComboBox comb;
    @FXML
    private ImageView refresh;


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
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(cinclient);
                                 labelemail.setText( UserSession.instance.getUserName());
           int k=svr.calculnbnonvalideclient(cinclient);
        int y=svr.calculnb(cinclient);
     nbreservations.setText(String.valueOf(k));
                nbreservations1.setText(String.valueOf(y-k));
        reservations.addAll(sr.Listreservationclient(cinclient));
        System.out.println("ok1");
         datec=svr.calculdate(cinclient);
                    
               ObservableList<String> list = FXCollections.observableArrayList();
      for (int i = 0; i < datec.size(); i++) {
                        list.add(datec.get(i));

                    }
      
                                comb.setItems(list);
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
                fxmlLoader.setLocation(getClass().getResource("/Clientinterfaces/unrdv.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                UnrdvController unec = fxmlLoader.getController();
                unec.setData(reservations.get(i),reservationappels);

                if (column == 2) {
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
    private void rdvaction(ActionEvent event) {
        
                 try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Clientinterfaces/modifierRdv.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            PageRDV.getScene().setRoot(root);
    } catch(Exception e) {
       e.printStackTrace();
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
    private void suppaction(ActionEvent event) {
        sr.delete(restest);
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

    @FXML
    private void rechtest(KeyEvent event) {
    }

    @FXML
    private void rechtest2(KeyEvent event) {
    }

    @FXML
    private void heurrr(MouseEvent event) {
    }

    @FXML
    private void selectdate(ActionEvent event) {
                 grid.setGridLinesVisible(false);
grid.getColumnConstraints().clear();
grid.getRowConstraints().clear();
grid.getChildren().clear();
grid.setGridLinesVisible(false);
                  String Typee= comb.getSelectionModel().getSelectedItem().toString();
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
                                if(Typee.equals(reservations.get(i).getDate().toString()))
                                {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Clientinterfaces/unrdv.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                UnrdvController unec = fxmlLoader.getController();
                unec.setData(reservations.get(i),reservationappels);

                if (column == 2) {
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
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }

    @FXML
    private void refreshclick(MouseEvent event) {
             grid.setGridLinesVisible(false);
grid.getColumnConstraints().clear();
grid.getRowConstraints().clear();
grid.getChildren().clear();
grid.setGridLinesVisible(false);
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
                fxmlLoader.setLocation(getClass().getResource("/Clientinterfaces/unrdv.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                UnrdvController unec = fxmlLoader.getController();
                unec.setData(reservations.get(i),reservationappels);

                if (column == 2) {
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
    }
    
}
