/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import Entities.Produit;
//import entities.ReservationEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Service.ServiceProduit;
import utils.Maconnexion;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AfficherEventClientController implements Initializable {

    private Label fruitNameLable;
    private Label fruitPriceLabel;
    @FXML
    private ImageView fruitImg;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    ServiceProduit cs = new ServiceProduit();

    List<Produit> evenementss = cs.ListProduit();
    private Image image;
    private MyListener myListener;
    @FXML
    private VBox EventChoisieCarte;
    private Label TitreEventLable;
    private Label LieuEventLabel;
    @FXML
    private Label DateEventCarte;
    @FXML
    private Label PlaceRestanteCarte;
    @FXML
    private Label TarifEventCarte;
    @FXML
    private Label TitreEventCarte;
    @FXML
    private Label LieuEventCarte;
    @FXML
    private Button btnLirePlus;
    @FXML
    private Label idOrganisateurLabel;
    @FXML
    private Label imgEventLabel;
  //  @FXML
   // private FontAwesomeIconView GestionUserIcon;
    @FXML
    private Label PlaceRestante;
    @FXML
    private ComboBox combobox_recherType;
    
                private Statement ste;
               private Connection con= Maconnexion.getInstance().getConnection();
    @FXML
    private Label TarifPromolabel;
    @FXML
    private Label idProduitLabel;
    @FXML
    private Label nvPrixLabel;
    @FXML
    private Label dtLabel;
               
               
    private List<Produit> getData() {
    List<Produit> evenements = cs.ListProduit();
    
          ObservableList<Produit> data =
                  
        FXCollections.observableArrayList(evenements );

     
        return data;
    }

    private void setChosenEvent(Produit e) {
     
        
        TitreEventCarte.setText(e.getNom());
        LieuEventCarte.setText(e.getType());
        TarifEventCarte.setText(String.valueOf(e.getPrix()));
        PlaceRestanteCarte.setText(String.valueOf(e.getQuantite()));
        idProduitLabel.setText(String.valueOf(e.getID()));
        
        int valeur_promo=0;
          float nv_prix=0;
        try {
     String requete = "SELECT valP FROM promotion  where ?=idP and DATEDIFF( dateF, now( )) > 0 ";
     PreparedStatement pst = con.prepareStatement(requete);               
     pst.setInt(1, e.getID());
     ResultSet ee = pst.executeQuery();
     while (ee.next()) {
  valeur_promo= ee.getInt("valP");
  
  
 
  
}
     }
    catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
if (valeur_promo!=0 ) {
    nv_prix=((e.getPrix()*valeur_promo)/100);
    TarifPromolabel.setText(String.valueOf(nv_prix));
    nvPrixLabel.setText("Nouveau prix :");
    dtLabel.setText("DT");
    
    
}

        
        // DateEventCarte.setDate(e.getDateF());
        
        
        
        String imgg= e.getImage();
        String ch="/image/";
        String imgF= ch+imgg;
        
        Image imageF = new Image(getClass().getResourceAsStream(imgF));
        fruitImg.setImage(imageF);
       
        EventChoisieCarte.setStyle("-fx-background-color: #" + "6A7324" + ";\n" +
                "    -fx-background-radius: 30;");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      //  evenementss.addAll(getData());
      

      
        
        if (evenementss.size() > 0) {
            setChosenEvent(evenementss.get(0));
            myListener = new MyListener() {
                public void onClickListener(Produit e) {
                     TarifPromolabel.setText(null);
                             nvPrixLabel.setText(null);
                             dtLabel.setText(null);
                    setChosenEvent(e);
                }


            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < evenementss.size(); i++) {
                
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/crud/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(evenementss.get(i),myListener);

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

//    @FXML
//    private void LirePlus_Reserver(ActionEvent event) {
//        
//      
//        
//        String titre=  TitreEventCarte.getText();
////        String IdOrganisateur = idOrganisateurLabel.getText();
//        String LieuEvent= LieuEventCarte.getText();
//        String DateEvent= DateEventCarte.getText();
//        
//        String ImagEvent= (imgEventLabel.getText());
//        String tarif= TarifEventCarte.getText();
//        String Idevent= idEvent.getText();
//        int id_evenement= Integer.parseInt(Idevent);
//        String Descri=" ";
//          int id_orga=0;
//          String imageFf=" ";
//          String lieu=" ";
//        try {
//     String requete = "SELECT description,id_organisateur,image,lieu FROM evenement  where ?=id  order by id desc";
//     PreparedStatement pst = con.prepareStatement(requete);               
//     pst.setInt(1, id_evenement);
//     ResultSet e = pst.executeQuery();
//     while (e.next()) {
//  Descri = e.getString("description");
//  id_orga= e.getInt("id_organisateur");
//  imageFf= e.getString("image");
//  lieu= e.getString("lieu");
//  
//}
//    }
//    catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        String IdOrganisateur=  String.valueOf(id_orga);
//
//        
//        String ch="/imgEvent/";
//        String imgF= ch+imageFf;
//        Image imageF = new Image(getClass().getResourceAsStream(imgF));
//        
//    
//
//        if("Complet".equals(PlaceRestanteCarte.getText()))
//        {
//              Alert alert = new Alert(Alert.AlertType.WARNING);
//               alert.setTitle("Alerte !");
//               alert.setHeaderText("Vous ne  pouvez pas réserver cette événement");
//               alert.setContentText(" Cette événement est complet");
//               
//               alert.showAndWait();
//        }
//        else if("Event passer".equals(EvenementEffectueCarte.getText()))
//        {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//               alert.setTitle("Alerte !");
//               alert.setHeaderText("Vous ne  pouvez pas réserver cette événement");
//               alert.setContentText(" Cette événement a déja eu lieu");
//               
//               alert.showAndWait();
//        }
//                else
//        {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("RéservationClientEvent.fxml"));
//        try {
//            Parent root = loader.load();
//            RéservationClientEventController pdc = loader.getController();
//            pdc.setTitreEventLabel(titre);
//            pdc.setDescriptionEventLabel(Descri);
//            pdc.setDateEventLabel(DateEvent);
//            pdc.setOrganisateurEventLabel(IdOrganisateur);
//            pdc.setTarifEventLabel(tarif);
//            pdc.setimgEvent(imageF);
//            pdc.setidEventLabel(Idevent);
//            pdc.setlieuEventLabel(lieu);
//            
//            btnLirePlus.getScene().setRoot(root);
//
//        } catch (IOException ex) {
//            Logger.getLogger(AfficherEventClientController.class.getName()).log(Level.SEVERE, null, ex);
//        }}
//    }
//
//    @FXML
//    private void GestionUser(MouseEvent event) {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionReservationEventClient.fxml"));
//        try {
//            Parent root = loader.load();
//            GestionUserIcon.getScene().setRoot(root);
//        } catch (IOException ex) {
//            Logger.getLogger(AfficherEventClientController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
    @FXML
    private void rechercherType(ActionEvent event) {
         
                 grid.setGridLinesVisible(false);
grid.getColumnConstraints().clear();
grid.getRowConstraints().clear();
grid.getChildren().clear();
grid.setGridLinesVisible(false);
       ObservableList<String> typee;
        typee = FXCollections.observableArrayList("Conférence","Cinéma","Méditation","Musique","Randonnée","Sport et fitness" );
        combobox_recherType.setItems(typee);
          String Typee= combobox_recherType.getSelectionModel().getSelectedItem().toString();
        if (evenementss.size() > 0) {
            setChosenEvent(evenementss.get(0));
            myListener = new MyListener() {
                public void onClickListener(Produit e) {
                    setChosenEvent(e);
                }

            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < evenementss.size(); i++) {
                
                if(Typee.equals(evenementss.get(i).getType()))
                    {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(evenementss.get(i),myListener);

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
            }}
        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }
//
//    @FXML
//    private void AfficherMeteo(MouseEvent event) throws IOException {
//        
//        
//             Parent root = FXMLLoader.load(getClass().getResource("WeatherView.fxml"));
//
//     Scene scene = new Scene(root);  
//      Stage primaryStage = new Stage();
//     primaryStage.setScene(scene);  
//     primaryStage.setTitle("Météo du jour");  
//     primaryStage.centerOnScreen();  
//     primaryStage.setResizable(false);  
//     primaryStage.setOpacity(1);  
//     primaryStage.show();  
//        
//////        
//////        FXMLLoader loader = new FXMLLoader(getClass().getResource("WeatherView.fxml"));
//////        try {
//////            Parent root = loader.load();
//////            GestionUserIcon.getScene().setRoot(root);
//////        } catch (IOException ex) {
//////            Logger.getLogger(AfficherEventClientController.class.getName()).log(Level.SEVERE, null, ex);
//////        }
//        
//        
////        FXMLLoader loader = new FXMLLoader(getClass().getResource("WeatherView.fxml"));
////        try {
////       
////        Scene scene = new Scene(loader.load());
////       Stage Stage stage = new Stage();
////        stage.setTitle("MyWeather");
////        stage.setScene(scene);
////        stage.show();
////        }catch (IOException e) {
////        Logger logger = Logger.getLogger(getClass().getName());
////        logger.log(Level.SEVERE, "Failed to create new Window.", e);
////    }
//
//    }
    
}
