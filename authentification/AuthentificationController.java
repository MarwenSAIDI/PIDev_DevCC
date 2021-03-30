/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentification;
import java.io.FileNotFoundException;
import services.Clientservice;
import services.Therapeuteservice;
import services.UserSession;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;
import utils.AuthentificationFB;

/**
 * FXML Controller class
 *
 * @author yassi
 */
public class AuthentificationController implements Initializable {

    @FXML
    private Button btnlogin;
    @FXML
    private TextField textemail;
    @FXML
    private PasswordField textpassword;
    @FXML
    private Hyperlink btnoublier;
    @FXML
    private Hyperlink sinsc;
    @FXML
    private Rating rating;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Clientservice s= new Clientservice();
        try {
            rating.setRating(s.rating());
        } catch (SQLException ex) {
            Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void sauthentifier(MouseEvent event) throws IOException {
        if ((textemail.getText().isEmpty())||(textpassword.getText().isEmpty()))
            {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("verifier les champs");
            alert.setContentText("un ou plusieurs champs est vide ");

            alert.showAndWait();
        }
        else {
            Therapeuteservice t =new Therapeuteservice();
            Clientservice c =new Clientservice();
            
            if (t.authentification(textemail.getText(),Therapeuteservice.getMd5( textpassword.getText())))
                
            {     
                UserSession.getInstace(textemail.getText(), Therapeuteservice.getMd5( textpassword.getText()));
            

               FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/therapeuteinterfaces/accueiltherapeute.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btnlogin.getScene().setRoot(root);
            
                }
         else   if (c.authentification(textemail.getText(),Therapeuteservice.getMd5( textpassword.getText())))
            {                            

                UserSession.getInstace(textemail.getText(), Therapeuteservice.getMd5( textpassword.getText()));
                
                 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Clientinterfaces/client.fxml"));
        try
        {
 Parent root = fxmlLoader.load();
 btnlogin.getScene().setRoot(root);}
        catch (IOException ex) {
        }
                }
         else   if (c.authentificationadmin(textemail.getText(),Therapeuteservice.getMd5( textpassword.getText())))
            {                               
             UserSession.getInstace(textemail.getText(), Therapeuteservice.getMd5( textpassword.getText()));

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/admininterfaces/accueiladmin.fxml"));
            
            Parent root = (Parent) fxmlLoader.load();
            btnlogin.getScene().setRoot(root);
                }
            else
                {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("un ou plusieurs champs sont faux");
            alert.setContentText("un ou plusieurs champs sont faux ");

            alert.showAndWait();
        }
            
            
        
        
        
        
        
        
        
        
        
        
        }
            
    }

    @FXML
    private void gotooublier(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("oublier.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btnoublier.getScene().setRoot(root);
    }

    @FXML
    private void gotoinscription(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("inscriptionfxml.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            sinsc.getScene().setRoot(root);
        
    }

    
    
    
}
