/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudvfinal;

import crudvfinal.entities.Therapeute;
import crudvfinal.entities.client;
import crudvfinal.services.Clientservice;
import crudvfinal.services.Therapeuteservice;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author yassi
 */
public class GestionclientController implements Initializable {

    @FXML
    private TextField textcin;
    @FXML
    private TextField textemail;
    @FXML
    private TextField textnom;
    @FXML
    private TextField textprenom;
    @FXML
    private TextField textpassword;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnsupp;
    @FXML
    private TableView<client> table;
    @FXML
    private TableColumn<client, Integer> cin;
    @FXML
    private TableColumn<client, String> email;
    @FXML
    private TableColumn<client, String> nom;
    @FXML
    private TableColumn<client, String>prenom;
    @FXML
    private TableColumn<client, String> password;
    @FXML
    private Label cinlabel;
    @FXML
    private Label emaillabel;
    @FXML
    private Label pwdlabel;
    @FXML
    private Label retour;
    @FXML
    private TableColumn<client, String> etat;
    @FXML
    private Pane textacc;
    @FXML
    private Label textth;
    @FXML
    private Label textclient;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Clientservice cs = new Clientservice();

         List<client> lc = cs.Listclient();
        
          ObservableList<client> data =
                  
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
      etat.setCellValueFactory(
                new PropertyValueFactory<>("etat"));
 
        table.setItems(data);
        
    }    

    @FXML
    private void modifier(MouseEvent event) {
                 client t = table.getSelectionModel().getSelectedItem();         
Clientservice s=new Clientservice();
s.Updateinscritclient(t);
Clientservice cs = new Clientservice();

         List<client> lc = cs.Listclient();
        
          ObservableList<client> data =
                  
        FXCollections.observableArrayList(lc );
          
      
 
 
 
        table.setItems(data);
          OublierController.send("zenlifezenlife02@gmail.com","zenlife123",textemail.getText(),"Compte Zenlife","felicitations vous etes inscrits a zenlife merci de nous rejoindre");             

    
}
        
        
    

    @FXML
    private void supprimer(MouseEvent event) {
        client t = table.getSelectionModel().getSelectedItem();         
Clientservice s=new Clientservice();
s.Updatebannedclient(t);
Clientservice cs = new Clientservice();

         List<client> lc = cs.Listclient();
        
          ObservableList<client> data =
                  
        FXCollections.observableArrayList(lc );
          
      
 
 
 
        table.setItems(data);
        
    }

    @FXML
    private void afficerform(MouseEvent event) {
        client t = table.getSelectionModel().getSelectedItem();         

        textcin.setText(String.valueOf(t.getCin()));
        textemail.setText(String.valueOf(t.getEmail()));
        textnom.setText(String.valueOf(t.getNom()));
        textprenom.setText(String.valueOf(t.getPrenom()));
        textpassword.setText(String.valueOf(t.getPassword()));
       
        
    }

    @FXML
    private void retour(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            retour.getScene().setRoot(root);
    }

    @FXML
    private void gotohome(MouseEvent event) {
        
    }

    @FXML
    private void gotogestiontherapeutes(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
    }

    @FXML
    private void gotoclient(MouseEvent event) {
    }
    
}
