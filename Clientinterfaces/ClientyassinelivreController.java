/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientinterfaces;

import Clientinterfaces.ItemController;
import com.jfoenix.controls.JFXButton;
import entities.Recommandation;
import services.Servicerecommandation;
import services.UserSession;
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
 * @author yassi
 */
public class ClientyassinelivreController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
Servicerecommandation cs = new Servicerecommandation();

    List <Recommandation> recommandationssarticle = cs.Listrecoarticles();
        List <Recommandation> recommandationsslivre = cs.Listrecolivres();

    @FXML
    private JFXButton deconnexion;
    @FXML
    private Label labelemail;
    @FXML
    private JFXButton btnarticle;
    @FXML
    private JFXButton btnlivre;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                          labelemail.setText( UserSession.instance.getUserName());

                    scroll.setVisible(true);
        grid.getChildren().clear();

    
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < recommandationsslivre.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(recommandationsslivre.get(i));

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
                        Logger.getLogger(ClientyassinelivreController.class.getName()).log(Level.SEVERE, null, e);

            
        }
    
        
    
    }
        // TODO
        

    

    @FXML
    private void deconnecter(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/authentification/authentification.fxml"));
        
 Parent root = fxmlLoader.load();
 deconnexion.getScene().setRoot(root);
    }

    @FXML
    private void afficherarticle(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("clientyassine.fxml"));
        
 Parent root = fxmlLoader.load();
 btnlivre.getScene().setRoot(root);
         
    }

    @FXML
    private void afficherlivre(MouseEvent event) {
    }
}
