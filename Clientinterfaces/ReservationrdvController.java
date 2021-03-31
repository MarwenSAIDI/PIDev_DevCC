/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientinterfaces;

import entities.consultation;
import services.serviceconsultation;
import therapeuteinterfaces.CrudconsultationController;
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
import services.serviceconsultation;
import services.servicereservation;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import Clientinterfaces.UneconsultationController;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import static java.lang.Float.parseFloat;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import services.UserSession;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.DataBase;
/**
 * FXML Controller class
 *
 * @author foura
 */
public class ReservationrdvController implements Initializable {

    @FXML
    private AnchorPane parent;
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
        public static int idd=0;
    private List<Double> prixc = new ArrayList<>();

    @FXML
    private Label nbreservations1;
    @FXML
    private JFXButton btnarticle;
    @FXML
    private JFXButton btnlivre;
    @FXML
    private JFXButton deconnexion;
    @FXML
    private Label labelemail;
    @FXML
    private FontAwesomeIcon btnUser;
    @FXML
    private JFXButton btnPageEvent;
    @FXML
    private JFXButton PageRDV;
        private int cinclient=svr.rendrecin(UserSession.instance.getUserName());
    @FXML
    private TextField rech;
            DataBase db = new DataBase();
    @FXML
    private ComboBox comb;
    @FXML
    private ImageView refresh;

    /**
     * Initializes the controller class.
     */


      private void setChosenconsult(consultation cons) {

        TitreLable.setText(cons.getTitre());
        consPriceLabel.setText( cons.getPrix()+"DT");
imgg=cons.getImage();
        idd=cons.getId();

        Image i;
        try {
            i= new Image(new FileInputStream(imgg));
                                consImg.setImage(i);

        } catch (FileNotFoundException ex) {
        }

   
    }
    
    public void initialize(URL url, ResourceBundle rb) {
                                 labelemail.setText( UserSession.instance.getUserName());
                    prixc=se.calculprix();
                    
               ObservableList<String> list = FXCollections.observableArrayList();
      for (int i = 0; i < prixc.size(); i++) {
                        list.add(prixc.get(i).toString());

                    }
      
                                comb.setItems(list);
        notiff();
         int k=svr.calculnbnonvalideclient(cinclient);
        int y=svr.calculnb(cinclient);
        
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
                fxmlLoader.setLocation(getClass().getResource("/Clientinterfaces/uneconsultation.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                UneconsultationController unec = fxmlLoader.getController();
                unec.setData(consultations.get(i),consultationappels);

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
            e.printStackTrace();
        }
        
        
      //  Image image = new Image(getClass().getResourceAsStream("/instagram/pics/avatar.JPG"));
        
       /* menhouni:
      Image image = new Image("file:///C:/Users/foura/OneDrive/Documents/template/Instagram/src/instagram/pics/avatar.jpg");
imgtest1.setImage(image);   */
   
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
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Clientinterfaces/PrendreRDV.fxml"));
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
    private void revenirpageaffichconsult(ActionEvent event) {
    }
  public ObservableList<consultation> getconsultationList(){
    	ObservableList<consultation> consultationList = FXCollections.observableArrayList();
            

    	Connection connection = db.getConnection();
    	String query = "SELECT * FROM consultation ";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			consultation consultations;
			while(rs.next()) {
				consultations = new consultation(rs.getInt("id"),rs.getInt("idtherapeute"),rs.getString("titre"),rs.getString("description"),rs.getString("emplacement"),rs.getFloat("prix"),rs.getString("image"));
				consultationList.add(consultations);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return consultationList;
    }
    @FXML
    private void rechtest(KeyEvent event) {
       
      
            grid.setGridLinesVisible(false);
grid.getColumnConstraints().clear();
grid.getRowConstraints().clear();
grid.getChildren().clear();
grid.setGridLinesVisible(false);
       
            ObservableList<consultation> list = getconsultationList();
FilteredList<consultation> filtereddata= new FilteredList<>(list,b->true);
rech.textProperty().addListener((observable,oldValue,newValue)->{
        filtereddata.setPredicate(consultation -> {
            
            if (newValue == null || newValue.isEmpty()){
                return true;
        }
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (consultation.getTitre().toLowerCase().indexOf(lowerCaseFilter)!= -1)
            return true;
                else
                    if(consultation.getDescription().toLowerCase().indexOf(lowerCaseFilter)!= -1)
                                    return true;
else
                    if(consultation.getEmplacement().toLowerCase().indexOf(lowerCaseFilter)!= -1)
                                    return true;
                    else
                    if(String.valueOf(consultation.getPrix()).indexOf(lowerCaseFilter)!= -1)
                                    return true;
                        else
                        
            return false;
                });
                
    });
SortedList<consultation> sortedData = new SortedList<>(filtereddata);
//sortedData.comparatorProperty().bind(grid.comparatorProperty());
//TableView.setItems(sortedData);
   if (consultations.size()>0)
        {

                        setChosenconsult(sortedData.get(0));

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
            for (int i = 0; i < sortedData.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Clientinterfaces/uneconsultation.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                UneconsultationController unec = fxmlLoader.getController();
                unec.setData(sortedData.get(i),consultationappels);
                
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
            e.printStackTrace();
        }

    }
    
    

    @FXML
    private void rechtest2(KeyEvent event) {
    }

    @FXML
    private void heurrr(MouseEvent event) {
    }

    @FXML
    private void selectprix(ActionEvent event) {
         grid.setGridLinesVisible(false);
grid.getColumnConstraints().clear();
grid.getRowConstraints().clear();
grid.getChildren().clear();
grid.setGridLinesVisible(false);
                  String Typee= comb.getSelectionModel().getSelectedItem().toString();
                  Double k = Double.parseDouble(Typee);
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
                if(k==consultations.get(i).getPrix())
                    {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Clientinterfaces/uneconsultation.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                UneconsultationController unec = fxmlLoader.getController();
                unec.setData(consultations.get(i),consultationappels);

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
            e.printStackTrace();
        }
    }

    @FXML
    private void refreshclick(MouseEvent event) {
          grid.setGridLinesVisible(false);
grid.getColumnConstraints().clear();
grid.getRowConstraints().clear();
grid.getChildren().clear();
grid.setGridLinesVisible(false);
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
                fxmlLoader.setLocation(getClass().getResource("/Clientinterfaces/uneconsultation.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                UneconsultationController unec = fxmlLoader.getController();
                unec.setData(consultations.get(i),consultationappels);

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
            e.printStackTrace();
        }
    }
    
    
    
}