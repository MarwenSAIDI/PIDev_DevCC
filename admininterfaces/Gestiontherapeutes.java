/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admininterfaces;

import com.jfoenix.controls.JFXButton;
import entities.Therapeute;
import services.Therapeuteservice;
import services.UserSession;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

/**
 *
 * @author yassi
 */
public class Gestiontherapeutes implements Initializable {
    
    private Label label;
    @FXML
    private TextField textcin;
    @FXML
    private TextField textemail;
    @FXML
    private TextField textnom;
    @FXML
    private TextField textprenom;
    @FXML
    private TextField textadresse;
    
    @FXML
    private Button btninscr;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnsupp;
    @FXML
    private TableColumn<Therapeute, Integer> cin;
    @FXML
    private TableColumn<Therapeute, String> email;
    @FXML
    private TableColumn<Therapeute, String> nom;
    @FXML
    private TableColumn<Therapeute, String>prenom;
    @FXML
    private TableColumn<Therapeute, String> password;
    @FXML
    private TableColumn<Therapeute, Integer> numero;
    @FXML
    private TableColumn<Therapeute, String> adresse;
    @FXML
    private TableColumn<Therapeute, String> image;
    @FXML
    private TextField textnum;
    @FXML
    private TextField textimage;
    @FXML
    private TableView<Therapeute> table;
    @FXML
    private Label cinlabel;
    @FXML
    private Label numlabel;
    @FXML
    private Label emaillabel;
    @FXML
    private Label pwdlabel;
    @FXML
    private PasswordField textpassword;
    @FXML
    private JFXButton PageReservationEventLabel;
    @FXML
    private Label labelemail;
    @FXML
    private Hyperlink deconnecter;
    @FXML
    private JFXButton retour1;
    @FXML
    private JFXButton textclient1;
    @FXML
    private JFXButton textth;
        
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                                           labelemail.setText( UserSession.instance.getUserName());


        Therapeuteservice cs = new Therapeuteservice();

         List<Therapeute> lc = cs.ListEntite();
        
          ObservableList<Therapeute> data =
                  
        FXCollections.observableArrayList(lc );
          
        cin.setCellValueFactory(
                new PropertyValueFactory<>("cin"));
          
        email.setCellValueFactory(
                new PropertyValueFactory<>("email"));
 
       
        nom.setCellValueFactory(
                new PropertyValueFactory<>("nom"));
 
        
        prenom.setCellValueFactory(
                new PropertyValueFactory<>("prenom"));
        
        
        
        password.setCellValueFactory(
                new PropertyValueFactory<>("password"));
        numero.setCellValueFactory(
                new PropertyValueFactory<>("numtel"));
        adresse.setCellValueFactory(
                new PropertyValueFactory<>("adresse"));
        
        image.setCellValueFactory(
                new PropertyValueFactory<>("imther"));
 
 
        table.setItems(data);
        
    }   


    @FXML
    private void ajouteruser(ActionEvent event)
    {         
    Therapeuteservice tt=new Therapeuteservice();

    
        if ((textcin.getText().isEmpty())||(textemail.getText().isEmpty())||(textnom.getText().isEmpty())||(textprenom.getText().isEmpty())||
                (textpassword.getText().isEmpty())||(textadresse.getText().isEmpty())||(textimage.getText().isEmpty())||(textnum.getText().isEmpty())
){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid data");
            alert.setHeaderText("Cannot add a new entry");
            alert.setContentText("un des champs est vide ");

            alert.showAndWait();
        } 

        
     else   if((textcin.getText().length()!=8)||(!tt.estUnEntier(textcin.getText())))
           cinlabel.setText("le cin doit avoir8 chiffres");
     else   if((textnum.getText().length()!=8)||(!tt.estUnEntier(textnum.getText())))
           numlabel.setText("le numero doit avoir8 chiffres");
     else if(!textemail.getText().contains("@zenlife.tn"))
         emaillabel.setText("l adresse doit contenir @zenlife.tn");
     else   if((textpassword.getText().length()<8))
          pwdlabel.setText("le pwd doit avoir8 chiffres minimum");
         
        
        
       else{          int a= Integer.parseInt(textcin.getText());

         int b=Integer.parseInt(textnum.getText());
     Therapeute e=new Therapeute(b,textadresse.getText(),textimage.getText(),a,textemail.getText(),textnom.getText(),textprenom.getText(),Therapeuteservice.getMd5(textpassword.getText()));
Therapeuteservice s=new Therapeuteservice();
s.addTherapeute(e);
Therapeuteservice cs = new Therapeuteservice();

         List<Therapeute> lc = cs.ListEntite();
        
          ObservableList<Therapeute> data =
                  
        FXCollections.observableArrayList(lc );
          
      
 
 
 
        table.setItems(data);}
    }

    @FXML
    private void modifierchamp(MouseEvent event) {
            Therapeuteservice tt=new Therapeuteservice();

       if ((textcin.getText().isEmpty())||(textemail.getText().isEmpty())||(textnom.getText().isEmpty())||(textprenom.getText().isEmpty())||
                (textpassword.getText().isEmpty())||(textadresse.getText().isEmpty())||(textimage.getText().isEmpty())||(textnum.getText().isEmpty())
){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid data");
            alert.setHeaderText("Cannot add a new entry");
            alert.setContentText("un des champs est vide ");

            alert.showAndWait();
        } 

        
        if((textcin.getText().length()!=8)||(!tt.estUnEntier(textcin.getText())))
           cinlabel.setText("le cin doit avoir8 chiffres");
        if((textnum.getText().length()!=8)||(!tt.estUnEntier(textnum.getText())))
           numlabel.setText("le numero doit avoir8 chiffres");
      if(!textemail.getText().contains("@zenlife.tn"))
         emaillabel.setText("l adresse doit contenir @zenlife.tn");
        if((textpassword.getText().length()<8))
          pwdlabel.setText("le pwd doit avoir8 chiffres minimum");  

        

     else{
        int a= Integer.parseInt(textcin.getText());
         int b=Integer.parseInt(textnum.getText());
     Therapeute e=new Therapeute(b,textadresse.getText(),textimage.getText(),a,textemail.getText(),textnom.getText(),textprenom.getText(),textpassword.getText());
Therapeuteservice s=new Therapeuteservice();
s.UpdateClasse(e);
Therapeuteservice cs = new Therapeuteservice();

         List<Therapeute> lc = cs.ListEntite();
        
          ObservableList<Therapeute> data =
                  
        FXCollections.observableArrayList(lc );
          
      
 
 
 
        table.setItems(data);}
    }

    @FXML
    private void supprimer(MouseEvent event) {
Therapeute t = table.getSelectionModel().getSelectedItem();         
Therapeuteservice s=new Therapeuteservice();
s.DeleteClasse(t.getCin());
Therapeuteservice cs = new Therapeuteservice();

         List<Therapeute> lc = cs.ListEntite();
        
          ObservableList<Therapeute> data =
                  
        FXCollections.observableArrayList(lc );
          
      
 
 
 
        table.setItems(data);
    }

    @FXML
    private void afficherform(MouseEvent event) {
        Therapeute t = table.getSelectionModel().getSelectedItem();         

        textcin.setText(String.valueOf(t.getCin()));
        textemail.setText(String.valueOf(t.getEmail()));
        textnom.setText(String.valueOf(t.getNom()));
        textprenom.setText(String.valueOf(t.getPrenom()));
        textpassword.setText(String.valueOf(t.getPassword()));
        textnum.setText(String.valueOf(t.getNumtel()));
        textadresse.setText(String.valueOf(t.getAdresse()));        
        textimage.setText(String.valueOf(t.getImther()));
    }

    @FXML
    private void importimg(MouseEvent event) {
        System.out.println("ss");
        FileChooser fc = new FileChooser();
        File selected = fc.showOpenDialog(null);
        if(selected !=null )
        {
            String extension = selected.getAbsolutePath().substring(selected.getAbsolutePath().length()-3,selected.getAbsolutePath().length());
            System.out.println(extension);
            if(!extension.equals( "jpg") && !extension.equals("png"))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid picture");
        
        alert.setContentText("Invalid picture format (only jgp/png available)"); 
     
        alert.showAndWait();
        textimage.setText("");
            }else
            textimage.setText(selected.getAbsolutePath());
        }
    }

//    @FXML
//    private void retour(MouseEvent event) throws IOException { 
//        
//        
//    }
    

@FXML
    private void deconnexion(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/authentification/authentification.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            deconnecter.getScene().setRoot(root);

    }

    @FXML
    private void gotoGestionclients(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Gestionclient.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
        
       
    }

    @FXML
    private void gotoGestiontherapeutes(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
        
    }


    @FXML
    private void go_to_Commandes(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Commande.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
    }

    @FXML
    private void go_to_Produit(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Produit.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
    }

    @FXML
    private void go_to_Paiements(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Payment.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
    }

    @FXML
    private void go_to_Panier(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Panier.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
    }

    @FXML
    private void go_to_Promotion(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Promotion.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
    }

    @FXML
    private void gotoaccueil(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accueiladmin.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
    }

    @FXML
    private void PageReservationEvent(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Gestionreco.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
    }
   
    

   
    
}
