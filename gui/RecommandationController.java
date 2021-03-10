/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudvfinal;

import crudvfinal.entities.Recommandation;
import crudvfinal.entities.client;
import crudvfinal.services.Clientservice;
import crudvfinal.services.Servicerecommandation;
import java.io.File;
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
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author yassi
 */
public class RecommandationController implements Initializable {
    Clientservice cs = new Clientservice();

         List<client> lc = cs.Listclient();
        
          ObservableList<client> data =
                  
        FXCollections.observableArrayList(lc );
    @FXML
    private TextField textid;
    @FXML
    private TextField texttitre;
    @FXML
    private TextField textdescription;
    @FXML
    private TextField textecrivain;
    @FXML
    private TextField textimage;
    @FXML
    private TextField texttype;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TableView<Recommandation> table;
    @FXML
    private TableColumn<Recommandation, String> id;
    @FXML
    private TableColumn<Recommandation, String> titre;
    @FXML
    private TableColumn<Recommandation, String> description;
    @FXML
    private TableColumn<Recommandation, String> ecrivain;
    @FXML
    private TableColumn<Recommandation, String> image;
    @FXML
    private TableColumn<Recommandation, String> type;
    @FXML
    private Pane retour;
    @FXML
    private Label textrecom;
    @FXML
    private Label labelemail;
          
        

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Servicerecommandation cs = new Servicerecommandation();

         List<Recommandation> lc = cs.Listreco();
        
          ObservableList<Recommandation> data =
                  
        FXCollections.observableArrayList(lc );
        id.setCellValueFactory(
                new PropertyValueFactory<>("id"));
          
        titre.setCellValueFactory(
                new PropertyValueFactory<>("titre"));
 
       
        description.setCellValueFactory(
                new PropertyValueFactory<>("description"));
 
        
        ecrivain.setCellValueFactory(
                new PropertyValueFactory<>("ecrivain"));
        
        
        
        image.setCellValueFactory(
                new PropertyValueFactory<>("image"));
         type.setCellValueFactory(
                new PropertyValueFactory<>("type"));
      
 
        table.setItems(data);
        // TODO
    }    


    @FXML
    private void modifier(MouseEvent event) {
          if ((textid.getText().isEmpty())||(texttitre.getText().isEmpty())||(textdescription.getText().isEmpty())||(textecrivain.getText().isEmpty())||
                (texttype.getText().isEmpty()))
{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid data");
            alert.setHeaderText("Cannot add a new entry");
            alert.setContentText("un des champs est vide ");

            alert.showAndWait();
        } 
else{
        Recommandation e=new Recommandation (textid.getText(),texttitre.getText(),textdescription.getText(),textecrivain.getText(),textimage.getText(),texttype.getText());
Servicerecommandation s=new Servicerecommandation();
s.Updatereco(e);

Servicerecommandation csq = new Servicerecommandation();

         List<Recommandation> lcd = csq.Listreco();
        
          ObservableList<Recommandation> datanb =
                  
        FXCollections.observableArrayList(lcd );
          
      
 
 
 
        table.setItems(datanb);}

    }

    @FXML
    private void supprimer(MouseEvent event) {
        Recommandation t = table.getSelectionModel().getSelectedItem();         
Servicerecommandation s=new Servicerecommandation();
s.DeleteRECO(t.getId());
Servicerecommandation xs= new Servicerecommandation();

         List<Recommandation> lcd = xs.Listreco();
        
          ObservableList<Recommandation> datan =
                  
        FXCollections.observableArrayList(lcd );
           table.setItems(datan);
          
    }

    @FXML
    private void ajouter(MouseEvent event) {
           if ((textid.getText().isEmpty())||(texttitre.getText().isEmpty())||(textdescription.getText().isEmpty())||(textecrivain.getText().isEmpty())||
                (texttype.getText().isEmpty()))
{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid data");
            alert.setHeaderText("Cannot add a new entry");
            alert.setContentText("un des champs est vide ");

            alert.showAndWait();
        } 
           else{
         Recommandation e=new Recommandation (textid.getText(),texttitre.getText(),textdescription.getText(),textecrivain.getText(),textimage.getText(),texttype.getText());
Servicerecommandation s=new Servicerecommandation();
s.addreco(e);
       Servicerecommandation xs= new Servicerecommandation();

         List<Recommandation> lcd = xs.Listreco();
        
          ObservableList<Recommandation> datan =
                  
        FXCollections.observableArrayList(lcd );
           table.setItems(datan);    
           
           
           
           }
        
    }

    @FXML
    private void afficherform(MouseEvent event) {
        Recommandation t = table.getSelectionModel().getSelectedItem();         

        textid.setText(String.valueOf(t.getId()));
        texttitre.setText(String.valueOf(t.getTitre()));
        textdescription.setText(String.valueOf(t.getDescription()));
        textecrivain.setText(String.valueOf(t.getEcrivain()));
        textimage.setText(String.valueOf(t.getImage()));
                texttype.setText(String.valueOf(t.getType()));

        
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
            textimage.setText(selected.getName());
        }
    }

    @FXML
    private void retour(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("interfacetherapeutes.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            retour.getScene().setRoot(root);
    }

    @FXML
    private void gotoGestionreco(MouseEvent event) {
    }
    
}
