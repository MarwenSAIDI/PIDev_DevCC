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
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
      
 
        table.setItems(data);
        
    }    

    @FXML
    private void modifier(MouseEvent event) {
        Clientservice tt=new Clientservice();

    
        if ((textcin.getText().isEmpty())||(textemail.getText().isEmpty())||(textnom.getText().isEmpty())||(textprenom.getText().isEmpty())||
                (textpassword.getText().isEmpty()))
{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid data");
            alert.setHeaderText("Cannot add a new entry");
            alert.setContentText("un des champs est vide ");

            alert.showAndWait();
        } 

        
     else   if((textcin.getText().length()!=8)||(!tt.estUnEntier(textcin.getText())))
           cinlabel.setText("le cin doit avoir8 chiffres");
     
     else if(!textemail.getText().contains("@zenlife.tn"))
         emaillabel.setText("l adresse doit contenir @zenlife.tn");
     else   if((textpassword.getText().length()<8))
          pwdlabel.setText("le pwd doit avoir8 chiffres minimum");
         
        
        
       else{          int a= Integer.parseInt(textcin.getText());

     client e=new client (a,textemail.getText(),textnom.getText(),textprenom.getText(),textpassword.getText());
Clientservice s=new Clientservice();
s.Updateclient(e);
Clientservice cs = new Clientservice();

         List<client> lc = cs.Listclient();
        
          ObservableList<client> data =
                  
        FXCollections.observableArrayList(lc );
          
      
 
 
 
        table.setItems(data);


         
    }
    
}
        
        
    

    @FXML
    private void supprimer(MouseEvent event) {
        client t = table.getSelectionModel().getSelectedItem();         
Clientservice s=new Clientservice();
s.Deleteclient(t.getCin());
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
    
}
