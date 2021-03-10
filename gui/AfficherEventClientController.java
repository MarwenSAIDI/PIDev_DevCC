/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudvfinal;

import crudvfinal.entities.Recommandation;
import crudvfinal.services.Servicerecommandation;

import java.io.IOException;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author user
 */
public class AfficherEventClientController implements Initializable {

    @FXML
    private ImageView recoimg;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    Servicerecommandation cs = new Servicerecommandation();

    List <Recommandation> recommandationss = cs.Listreco();
    private MyListener myListener;
    @FXML
    private VBox EventChoisieCarte;
    private Label TitreEventLable;
    private Label LieuEventLabel;
    @FXML
    private Button btnLirePlus;
    @FXML
    private Label idreco;
    @FXML
    private Label imgrecolabel;
    @FXML
    private Label titrerecocarte;
    @FXML
    private Label descrecocarte;
    @FXML
    private Label ecrivainrecocarte;
    @FXML
    private Label typerecocarte;

    private List<Recommandation> getData() {
    List<Recommandation> recommandation = cs.Listreco();
    
          ObservableList<Recommandation> data =
                  
        FXCollections.observableArrayList(recommandation);

     
        return data;
    }

    private void setChosenEvent(Recommandation e) {
        idreco.setText(e.getId());
        titrerecocarte.setText(e.getTitre());
        descrecocarte.setText(e.getDescription());
        
        ecrivainrecocarte.setText(e.getEcrivain());
        
        typerecocarte.setText(e.getType());
        
       
        
        String imgg= e.getImage();
        String ch="imgReco/";
        String imgF= ch+imgg;
        
        Image imageF = new Image(getClass().getResourceAsStream(imgF));
        recoimg.setImage(imageF);
       
        EventChoisieCarte.setStyle("-fx-background-color: #" + "6A7324" + ";\n" +
                "    -fx-background-radius: 30;");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // evenementss.addAll(getData());
//        if (recommandationss.size() > 0) {
//            setChosenEvent(recommandationss.get(0));
//            myListener = new MyListener() {
//                public void onClickListener(Recommandation e) {
//                    setChosenEvent(e);
//                }
//
//            };
//        
            System.out.println("yesssssssssssssssssssssssssssss");


        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < recommandationss.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(recommandationss.get(i));

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
        }
    }

    @FXML
    private void LirePlus_Reserver(ActionEvent event) {
       

                
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("lireplusreco.fxml"));
//        try {
//            Parent root = loader.load();
//            RéservationClientEventController pdc = loader.getController();
//            pdc.setTitreEventLabel(titre);
//            pdc.setDescriptionEventLabel(Description);
//            pdc.setDateEventLabel(DateEvent);
//            pdc.setOrganisateurEventLabel(IdOrganisateur);
//            pdc.setTarifEventLabel(tarif);
//            pdc.setimgEvent(imageF);
//            pdc.setidEventLabel(Idevent);
//            
//            btnLirePlus.getScene().setRoot(root);
//
//        } catch (IOException ex) {
//            Logger.getLogger(AfficherEventClientController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    
    

}}