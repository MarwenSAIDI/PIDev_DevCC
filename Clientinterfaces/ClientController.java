/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientinterfaces;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import services.Clientservice;
import services.UserSession;

/**
 * FXML Controller class
 *
 * @author foura
 */
public class ClientController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private JFXButton btnevent;
    @FXML
    private JFXButton btnarticle;
    @FXML
    private JFXButton btnlivre;
    @FXML
    private JFXButton btnconsultation;
    @FXML
    private JFXButton deconnexion;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Label labelemail;
    @FXML
    private ImageView reservbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            // TODO
            labelemail.setText( UserSession.instance.getUserName());
            Clientservice sd=new Clientservice();
            
        try {
            if(!sd.emailoui(labelemail.getText()))
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("rating.fxml"));
                
                try {
                    
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage primaryStage = new Stage();
                    primaryStage.setScene(scene);
                    
                    
                    primaryStage.centerOnScreen();
                    primaryStage.setResizable(false);
                    primaryStage.setOpacity(1);
                    primaryStage.show();
                    
                    
                    
                    
                    
                } catch (IOException ex) {
                    Logger.getLogger(RatingController.class.getName()).log(Level.SEVERE, null, ex);
                }}
        } catch (SQLException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                
                
                
                
            }
         
        

        

    @FXML
    private void gotoevent(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AfficherEventClient.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btnarticle.getScene().setRoot(root);
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
    private void gotoconsultation(MouseEvent event) {
           try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Clientinterfaces/reservationrdv.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btnconsultation.getScene().setRoot(root);
    } catch(Exception e) {
       e.printStackTrace();
      }
    }

    @FXML
    private void deconnecter(MouseEvent event) {
    }

    @FXML
    private void reservbutton(MouseEvent event) {
    }
    
}
