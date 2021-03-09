/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Evenement;
import entities.ReservationEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import service.ServiceEvenement;
import service.ServiceReservationEvent;
import utilis.DataBase;
import utilis.JavaMailUtil;
import utilis.sqlexcept;

/**
 * FXML Controller class
 *
 * @author user
 */
public class RéservationClientEventController implements Initializable {

    @FXML
    private Label TitreEventLabel;
    @FXML
    private Label DescriptionEventLabel;
    @FXML
    private Label DateEventLabel;
    @FXML
    private Label OrganisateurEventLabel;
    @FXML
    private Label TarifEventLabel;
    @FXML
    private Label PlaceAreserverEventLabel;
    @FXML
    private ComboBox PlaceAreserverComboBox;
    @FXML
    private Label ClientEventLabel;
    @FXML
    private Label TotalEventLabel;
    @FXML
    private Label paiementEventLabel;
    @FXML
    private ComboBox paiementEventCombobox;
    @FXML
    private Button btnReserverEvent;
    @FXML
    private Button btnPageEvent;
    @FXML
    private ImageView imgEvent;
    @FXML
    private Label idEventLabel;
    @FXML
    private FontAwesomeIconView GestionUserIcon;
    @FXML
    private Label lieuEventLabel;

     private Statement ste;
               private Connection con= DataBase.getInstance().getConnection();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
         ObservableList<Integer> list;
        list = FXCollections.observableArrayList(1,2,3,4,5);
        PlaceAreserverComboBox.setItems(list);
        
        
        ObservableList<String> list1;
        list1 = FXCollections.observableArrayList("Sur place ","En ligne");
        paiementEventCombobox.setItems(list1);
    }    
    
    public void setlieuEventLabel(String titre)
{
    this.lieuEventLabel.setText(titre);
}   
    
 public void setidEventLabel(String titre)
{
    this.idEventLabel.setText(titre);
}   
    
public void setTitreEventLabel(String titre)
{
    this.TitreEventLabel.setText(titre);
}
public void setDescriptionEventLabel(String titre)
{
    this.DescriptionEventLabel.setText(titre);
}
public void setDateEventLabel(String titre)
{
    this.DateEventLabel.setText(titre);
}

public void setOrganisateurEventLabel(String titre)
{
    this.OrganisateurEventLabel.setText(titre);
}
public void setClientEventLabel(String titre)
{
    this.ClientEventLabel.setText(titre);
}
public void setTarifEventLabel(String titre)
{
    this.TarifEventLabel.setText(titre);
}
public void setimgEvent(Image imageF)
{
    this.imgEvent.setImage(imageF);
}

    @FXML
    private void PageEvent(ActionEvent event) {
          
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherEventClient.fxml"));
        
        
        try {
            Parent root = loader.load();
            btnPageEvent.getScene().setRoot(root);
            
        } catch (IOException ex) {
            Logger.getLogger(AfficherEventClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AjouterReservationEvent(ActionEvent event) throws IOException, sqlexcept {
        
        
            String IdOrganisateur = OrganisateurEventLabel.getText();
           // String client = ClientEventLabel.getText();
            String idevent = idEventLabel.getText();
            String nb_place= PlaceAreserverComboBox.getSelectionModel().getSelectedItem().toString();
            String total= TotalEventLabel.getText();
            String modePaiement= paiementEventCombobox.getSelectionModel().getSelectedItem().toString();
            
            float totalF= Float.parseFloat(total);
            int orga=Integer.parseInt(IdOrganisateur);
           // int clientt = Integer.parseInt(client);
           int eventt= Integer.parseInt(idevent);
           int place =Integer.parseInt(nb_place);
           int user_id=7878;
           
                      if((PlaceAreserverComboBox.getItems().isEmpty())||(paiementEventCombobox.getItems().isEmpty()))
           {
               Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Alerte");
               alert.setHeaderText("Verifier vos champs");
               alert.setContentText(" Un des champs est vide");
               
               alert.showAndWait();
           }

                      else
                      {
            ReservationEvent c = new ReservationEvent(0,orga,user_id,eventt,place,totalF ,modePaiement,"en cours");
            ServiceReservationEvent cs = new ServiceReservationEvent();
            cs.ajouter1(c);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Bravo");
               alert.setHeaderText("Vous êtes inscrit à l'événement :" + TitreEventLabel.getText());
               alert.setContentText(" Soyez au rendez vous le "+ DateEventLabel.getText());
               alert.showAndWait();
               
//chercher le mail du user 
String email =" ";
String nom_p=" ";
String prenom=" ";
                try {
     String requete = "SELECT email,nom,prenom FROM user  where ?=cin ";
     PreparedStatement pst = con.prepareStatement(requete);               
     pst.setInt(1, user_id);
     ResultSet e = pst.executeQuery();
     
     while (e.next()) {
  email = e.getString("email");
  nom_p=e.getString("nom");
 prenom =e.getString("prenom");
}
    }
    catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                
               String contenu=" Bonjour "+nom_p+" "+prenom+ " \n\n Vous avez réserver "+ nb_place+" place(s) à l'événement :" + TitreEventLabel.getText()
                       +". \n Cette événement aura lieu le "+DateEventLabel.getText()+ ". \n Soyez au rendez vous. \n \n L'équipe de ZenLife vous remercie pour votre confiance";
                       
                       
                      JavaMailUtil.sendMail(email,contenu);

               FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherEventClient.fxml"));
        try {
            Parent root = loader.load();
            btnReserverEvent.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherEventClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
                      }
        
    }

    public  float CalculTotal()
    {
         String nb_place= PlaceAreserverComboBox.getSelectionModel().getSelectedItem().toString();
       int place=Integer.parseInt(nb_place);
       String tarif = TarifEventLabel.getText();
       float tariff= Float.parseFloat(tarif);
       float total= place * tariff;
       
       return total;
    }
  

    @FXML
    private void CalculTotall(ActionEvent event) {
         float total= CalculTotal();
      String totall= String.valueOf(total);
      TotalEventLabel.setText(totall);

    }

    @FXML
    private void GestionUserIcon(MouseEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionReservatioEventClient.fxml"));
        try {
            Parent root = loader.load();
            GestionUserIcon.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherEventClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
