/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentification;

import entities.Therapeute;
import entities.User;
import entities.client;
import services.Clientservice;
import services.Therapeuteservice;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author yassi
 */
public class InscriptionfxmlController implements Initializable {

    @FXML
    private TextField textcin;
    @FXML
    private TextField textemail;
    @FXML
    private TextField textnom;
    @FXML
    private TextField textprenom;
    
    @FXML
    private Button btninscri;
    @FXML
    private Label cinlabel;
    @FXML
    private Label emaillabel;
    @FXML
    private Label labelpwd;
    @FXML
    private PasswordField textpassword;
    @FXML
    private FontAwesomeIcon retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void inscription(MouseEvent event) {
        Clientservice tt=new Clientservice();

    
        if ((textcin.getText().isEmpty())||(textemail.getText().isEmpty())||(textnom.getText().isEmpty())||(textprenom.getText().isEmpty())||
                (textpassword.getText().isEmpty()))
{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("un des champs est vide");
            alert.setContentText("un des champs est vide ");

            alert.showAndWait();
        } 

        
     else   if((textcin.getText().length()!=8)||(!tt.estUnEntier(textcin.getText())))
           cinlabel.setText("le cin doit avoir8 chiffres");
     
     else if(!textemail.getText().contains("@"))
         emaillabel.setText("l adresse doit contenir un @");
     else   if((textpassword.getText().length()<8))
          labelpwd.setText("le pwd doit avoir8 chiffres minimum");
         
        
        
       else{          int a= Integer.parseInt(textcin.getText());

     client e=new client (a,textemail.getText(),textnom.getText(),textprenom.getText(),getMd5(textpassword.getText()));
Clientservice s=new Clientservice();
s.addclient(e);
  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("inscription avec succes");
            alert.setContentText("Inscription avec succes ");

            alert.showAndWait();

         
    }
    
}

    @FXML
    private void retour(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("authentification.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            retour.getScene().setRoot(root);
    }
    
    
   public static String getMd5(String input) 
    { 
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
}
