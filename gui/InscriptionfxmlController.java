/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudvfinal;

import crudvfinal.entities.Therapeute;
import crudvfinal.entities.User;
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
    private TextField textpassword;
    @FXML
    private Button btninscri;
    @FXML
    private Label cinlabel;
    @FXML
    private Label emaillabel;
    @FXML
    private Label labelpwd;

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
          labelpwd.setText("le pwd doit avoir8 chiffres minimum");
         
        
        
       else{          int a= Integer.parseInt(textcin.getText());

     client e=new client (a,textemail.getText(),textnom.getText(),textprenom.getText(),textpassword.getText());
Clientservice s=new Clientservice();
s.addclient(e);

         
    }
    
}}
