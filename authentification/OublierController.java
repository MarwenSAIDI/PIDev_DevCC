/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentification;
import entities.client;
import services.Clientservice;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.util.Random;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * FXML Controller class
 *
 * @author yassi
 */
public class OublierController implements Initializable {

    @FXML
    private TextField textemail;
    @FXML
    private Button btnrecuperer;
    @FXML
    private TextField code;
    @FXML
    private TextField password;
    @FXML
    private TextField password1;
    @FXML
    private Button modifier;
    @FXML
    private Label labelrondom;
    @FXML
    private FontAwesomeIcon retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        

        
    }    

    @FXML
    private void recuperer(MouseEvent event) {
        if (textemail.getText().isEmpty())
        {Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("verifier les chmps");
            alert.setContentText("un ou plusieurs champs est vide ");

            alert.showAndWait();
        
        
        
        
        
        }
        else { Random random = new Random();

		int value = random.nextInt(100000 + 999999) + 100000;
                String s=String.valueOf(value);
                labelrondom.setText(s);
                
		System.out.println(value);
                 OublierController.send("zenlifezenlife02@gmail.com","zenlife123",textemail.getText(),"Code de recuperation",s);    }           

        
    }public static void send(String from,String password,String to,String sub,String msg){  
          //Get properties object    
          Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");    
          //get Session   
          Session session = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication(from,password);  
           }    
          });    
          //compose message    
          try {    
           MimeMessage message = new MimeMessage(session);    
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
           message.setSubject(sub);    
           message.setText(msg);    
          
           //send message  
           Transport.send(message);    
           System.out.println("message sent successfully");    
          } catch (MessagingException e) {throw new RuntimeException(e);}    
             
    }  

    @FXML
    private void modifiermotdepasse(MouseEvent event) {
                if (code.getText().isEmpty()||password.getText().isEmpty()||password1.getText().isEmpty())
                {
                Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("verifier les chmps");
            alert.setContentText("un ou plusieurs champs est vide ");

            alert.showAndWait();
                
                
                
                
                
                
                
                }
                
                else{
       
                  if (labelrondom.getText().equals(code.getText())&&(password.getText().equals(password1.getText())))
        { Clientservice c=new Clientservice();
        client cl=new client();
        cl.setEmail(textemail.getText());
        cl.setPassword(Clientservice.getMd5(password.getText()));
        c.Updatemdpclient(cl);
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("mot de passe change avec succes");
            alert.setContentText("mot de passe change avec succes ");

            alert.showAndWait();}
        else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("champs incorrectes");
            alert.setContentText("champs incorrectes ");

            alert.showAndWait();
                
                
                
                
                }
        
        
        
        
    }}

    @FXML
    private void retour(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("authentification.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            retour.getScene().setRoot(root);
    }
    
}
