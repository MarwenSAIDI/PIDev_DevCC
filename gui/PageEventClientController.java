/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import service.ServiceEvenement;

/**
 *
 * @author user
 */
public class PageEventClientController {


    private Label fruitNameLable;
    private Label fruitPriceLabel;
    @FXML
    private ImageView fruitImg;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    ServiceEvenement cs = new ServiceEvenement();

    List<Evenement> evenementss = cs.ListClasse();
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
    private Label idEvent;
    @FXML
    private Label idOrganisateurLabel;
    @FXML
    private Label imgEventLabel;
    @FXML
    private AnchorPane parent;

    private List<Evenement> getData() {
    List<Evenement> evenements = cs.ListClasse();
    
          ObservableList<Evenement> data =
                  
        FXCollections.observableArrayList(evenements );

     
        return data;
    }

    private void setChosenEvent(Evenement e) {
        String idOrga=String.valueOf(e.getId_organisateur());
        String id =String.valueOf(e.getId());
        idOrganisateurLabel.setText(idOrga);
        idEvent.setText(id);
        TitreEventCarte.setText(e.getTitre());
        LieuEventCarte.setText(e.getLieu());
        Date d=e.getDate_event();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");  
        String strDate = dateFormat.format(d); 
        DateEventCarte.setText(strDate);
        
        imgEventLabel.setText(e.getImage());
        
       float prix= e.getTarif();
       String prixeS=String.valueOf(prix);
        TarifEventCarte.setText(prixeS);
        
        String imgg= e.getImage();
        String ch="/imgEvent/";
        String imgF= ch+imgg;
        
        Image imageF = new Image(getClass().getResourceAsStream(imgF));
        fruitImg.setImage(imageF);
       
        EventChoisieCarte.setStyle("-fx-background-color: #" + "6A7324" + ";\n" +
                "    -fx-background-radius: 30;");
    }

    
    public void initialize(URL location, ResourceBundle resources) {
       // evenementss.addAll(getData());
        if (evenementss.size() > 0) {
            setChosenEvent(evenementss.get(0));
            myListener = new MyListener() {
                public void onClickListener(Evenement e) {
                    setChosenEvent(e);
                }

            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < evenementss.size(); i++) {
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void LirePlus_Reserver(ActionEvent event) {
        String titre=  TitreEventCarte.getText();
        String Description=  TitreEventCarte.getText();
        String IdOrganisateur = idOrganisateurLabel.getText();
        String LieuEvent= LieuEventCarte.getText();
        String DateEvent= DateEventCarte.getText();
        
        String ImagEvent= (imgEventLabel.getText());
        String tarif= TarifEventCarte.getText();
        String Idevent= idEvent.getText();
        
        String ch="/imgEvent/";
        String imgF= ch+ImagEvent;
        Image imageF = new Image(getClass().getResourceAsStream(imgF));

                
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RéservationClientEvent.fxml"));
        try {
            Parent root = loader.load();
            RéservationClientEventController pdc = loader.getController();
            pdc.setTitreEventLabel(titre);
            pdc.setDescriptionEventLabel(Description);
            pdc.setDateEventLabel(DateEvent);
            pdc.setOrganisateurEventLabel(IdOrganisateur);
            pdc.setTarifEventLabel(tarif);
            pdc.setimgEvent(imageF);
            pdc.setidEventLabel(Idevent);
            
            btnLirePlus.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(AfficherEventClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
