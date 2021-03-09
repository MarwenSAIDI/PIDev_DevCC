/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import service.ServiceEvenement;
import entities.Evenement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.controlsfx.control.Notifications;
import service.ServiceEvenement;
import utilis.sqlexcept;

/**
 * FXML Controller class
 *
 * @author user
 */
public class CrudEventController implements Initializable {

    @FXML
    private TableView<Evenement> TableView;
    @FXML
    private TableColumn<Evenement,Integer> idEventColumn;
    @FXML
    private TableColumn<Evenement,Integer > IdOrganisteurEventColumn;
    @FXML
    private TableColumn<Evenement, String> TypeEventColumn;
    @FXML
    private TableColumn<Evenement, String> TitreEventColumn;
    @FXML
    private TableColumn<Evenement, String> DescriptionEventColumn;
    @FXML
    private TableColumn<Evenement, String> LieuEventColumn;
    @FXML
    private TableColumn<Evenement, Date> DateEventColumn;
       @FXML
    private TableColumn<Evenement, Date> TarifEventColumn;
    @FXML
    private DatePicker DateEventField;
    @FXML
    private TextField IdOrganisteurEventField;
    @FXML
    private TextField TitreEventField;
    @FXML
    private TextArea DescriptionEventField;
    @FXML
    private TextField LieuEventField;
    @FXML
    private Button AjoutEventButton;
    @FXML
    private Button ModiferEventButton;
    @FXML
    private Button SupprimerEventButton;
    @FXML
    private Label TitreEventLabel;
    @FXML
    private Label DescriptionEventLabel;
    @FXML
    private Label IdOrganisteurEventLabel;
    @FXML
    private Label TypeEventLabel;
    @FXML
    private Label LieuEventLabel;
    @FXML
    private Label DateEventLabel;
    @FXML
    private TextField ImageEventField;
    @FXML
    private TableColumn <Evenement, String>ImageEventColumn;
    @FXML
    private Button btnDeconnexion;
  private Image image;
   private ImageView imageEvent;
    @FXML
    private ImageView imageView;
    @FXML
    private ComboBox TypeEventComboBox;
    @FXML
    private TextField TarifEventField;
    @FXML
    private Label TarifEventLabel;
    @FXML
    private TextField capaciteEventField;
    @FXML
    private Label capaciteEventLabel;
    @FXML
    private TableColumn<Evenement, Integer> capciteColoumn;
    @FXML
    private TableColumn<Evenement, Integer>nbRservatColomun;
    @FXML
    private TableColumn<Evenement, String>etatReservColoumn;
    @FXML
    private Label StatEvent;
    @FXML
    private Label PageReservationEventLabel;
 
    /**
     * Initializes the controller class.
     * 
     * 
     * 
     */
    
         
    
    public void etat()
    {
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        ServiceEvenement cs = new ServiceEvenement();

         List<Evenement> lc = cs.ListClasse();
        
          ObservableList<Evenement> data =
                  
        FXCollections.observableArrayList(lc );

        idEventColumn.setCellValueFactory(
                new PropertyValueFactory<>("id"));
          
        IdOrganisteurEventColumn.setCellValueFactory(
                new PropertyValueFactory<>("id_organisateur"));
 
       
        TypeEventColumn.setCellValueFactory(
                new PropertyValueFactory<>("type"));
 
        
        TitreEventColumn.setCellValueFactory(
                new PropertyValueFactory<>("titre"));
        
        DescriptionEventColumn.setCellValueFactory(
                new PropertyValueFactory<>("description"));
        
        LieuEventColumn.setCellValueFactory(
                new PropertyValueFactory<>("lieu"));
        
        DateEventColumn.setCellValueFactory(
                new PropertyValueFactory<>("date_event"));
        
        ImageEventColumn.setPrefWidth(80); 
        
        ImageEventColumn.setCellValueFactory(
                new PropertyValueFactory<>("image"));
        
         TarifEventColumn.setCellValueFactory(
                new PropertyValueFactory<>("tarif"));
         
         capciteColoumn.setCellValueFactory(
                new PropertyValueFactory<>("capacite"));
         
         nbRservatColomun.setCellValueFactory(
                new PropertyValueFactory<>("nb_reservation"));
         etatReservColoumn.setCellValueFactory(
                new PropertyValueFactory<>("etat"));
         
         
         
        TableView.setItems(data);
        

         ObservableList<String> list;
        list = FXCollections.observableArrayList("Conférence","Cinéma","Méditation","Musique","Randonnée","Sport et fitness" );
        TypeEventComboBox.setItems(list);
        
  
    }    
    
  

    @FXML
    private void AjoutEventButton(ActionEvent event) throws ParseException, IOException, sqlexcept {
        String IdOrganisateur = IdOrganisteurEventField.getText();
         String Type= TypeEventComboBox.getSelectionModel().getSelectedItem().toString();
             String Typee=Type;
            String Titre= TitreEventField.getText();
            String Description= DescriptionEventField.getText();
            String LieuEvent= LieuEventField.getText();
            String DateEvent= String.valueOf(DateEventField.getValue());
            int idorga = Integer.parseInt(IdOrganisateur);
            String ImagEvent= ImageEventField.getText();
            Date Date=new SimpleDateFormat("yyyy-MM-dd").parse(DateEvent);   
            String tarif= TarifEventField.getText();
            float tarifF = Float.parseFloat(tarif);
            
                    String capacite = capaciteEventField.getText();
                    int capa = Integer.parseInt(capacite);
                    
                   
                    
                    


           
//            
//             image = new Image(getClass().getResourceAsStream(ImagEvent));
//        imageEvent.setImage(image);
//         Calendar ca= Calendar.getInstance();
//       Date dd = new Date();
//       ca.setTime(DateEvent);
//       dd=ca.getTime();

//           if(Type.isEmpty())
//           {
//               
//               TypeEventLabel.setText( " Saisir le type de l'événement");
//           }
            if(DateEvent.isEmpty())
            {
                               DateEventLabel.setText("Saisir la date de l'événement");

            }
           else if(IdOrganisateur.isEmpty())
           {
               IdOrganisteurEventLabel.setText("Saisir l'id de l'Organisateur");
           }
           else if(Titre.isEmpty()){
               TitreEventLabel.setText("Saisir le titre de l'événement");
           }
           else  if(Description.isEmpty()){
               DescriptionEventLabel.setText("Saisir la description de l'événement");
           }
           else   if(LieuEvent.isEmpty()){
               LieuEventLabel.setText("Saisir le Lieu de l'événement");
           }
           else   if(tarif.isEmpty()){
               TarifEventLabel.setText("Saisir le tarif");
           }
          
//
//           if((Type.isEmpty())||(IdOrganisateur.isEmpty())||(Titre.isEmpty())||(Description.isEmpty())||(LieuEvent.isEmpty())||(DateEvent.isEmpty()))
//           {
//               Alert alert = new Alert(Alert.AlertType.WARNING);
//               alert.setTitle("Alerte");
//               alert.setHeaderText("Verifier vos champs");
//               alert.setContentText(" Un des champs est vide");
//               
//               alert.showAndWait();
//           }
           else
           {
            Evenement c = new Evenement(0, idorga, Typee, Titre,LieuEvent,Description,Date,ImagEvent,tarifF,capa,0,"en cours");
            ServiceEvenement cs = new ServiceEvenement();
            cs.ajouter1(c);
               Notifications.create()
                       .title("Notification !!")
                       .text(" Vous avez ajouté un nouvel événement: "+Titre )
                       
                       .showInformation();
                       
         List<Evenement> lc = cs.ListClasse();
        
        ObservableList<Evenement> data =         
        FXCollections.observableArrayList(lc );
        TableView.setItems(data);
        
    viderChamps();
        }
    }

  
    @FXML
    private void ModiferEventButton(ActionEvent event) throws ParseException {
       
            Evenement ev = TableView.getSelectionModel().getSelectedItem();


            String IdOrganisateur = IdOrganisteurEventField.getText();
            String Type= TypeEventComboBox.getSelectionModel().getSelectedItem().toString();

            String Titre= TitreEventField.getText();
            String Description= DescriptionEventField.getText();
            String LieuEvent= LieuEventField.getText();
            String DateEvent= String.valueOf(DateEventField.getValue());
            int idorga = Integer.parseInt(IdOrganisateur);
            Date Date=new SimpleDateFormat("yyyy-MM-dd").parse(DateEvent);  
            String ImagEvent= (ImageEventField.getText());
            String tarif= TarifEventField.getText();
            float tarifF = Float.parseFloat(tarif);
            String capacite = capaciteEventField.getText();
                    int capa = Integer.parseInt(capacite);
           

 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Modification");
            alert.setHeaderText("Voulez vous modifier cette événement");
            alert.setContentText("Confirmez la modification");
            Optional<ButtonType> btn = alert.showAndWait();
            if(btn.get()==ButtonType.OK)
            {
           
            Evenement c = new Evenement(ev.getId(), idorga, Type, Titre,LieuEvent,Description,Date,ImagEvent,tarifF,capa,ev.getNb_reservation(),ev.getEtat());
        ServiceEvenement cs = new ServiceEvenement();

        cs.ModifierEvenement(c);

        List<Evenement> lc = cs.ListClasse();
        
        ObservableList<Evenement> data =         
        FXCollections.observableArrayList(lc );
        TableView.setItems(data);
        
      viderChamps();  
       }
            else {alert.close();}
            
    }

    @FXML
    private void SupprimerEventButton(ActionEvent event) throws ParseException {
        
      Evenement ev = TableView.getSelectionModel().getSelectedItem();
            ServiceEvenement cs = new ServiceEvenement();
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confimer supprsion");
            alert.setHeaderText("Voulez vous supprimer cette événement");
            alert.setContentText("Confirmez la suppression");
            Optional<ButtonType> btn = alert.showAndWait();
            if(btn.get()==ButtonType.OK)
            {
                cs.SupprimerEvenement(ev);
                    List<Evenement> lc = cs.ListClasse();
        
        ObservableList<Evenement> data =         
        FXCollections.observableArrayList(lc );
        TableView.setItems(data);
                System.out.println("Suppression effectue");
                 viderChamps();
                
            }
            else {alert.close();}
            
          
           
             
     
        
       
    }
    

    @FXML
    private void AfficherInfoForm(MouseEvent event) {
        

         Evenement ev = TableView.getSelectionModel().getSelectedItem();
         int IdOrganisateur = ev.getId_organisateur();
         ServiceEvenement cs = new ServiceEvenement();
        float tarif =ev.getTarif();
        Date date =ev.getDate_event();
      
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        
        
        
        ObservableList<String> list;
        list = FXCollections.observableArrayList("Conférence","Cinéma","Méditation","Musique","Randonnée","Sport et fitness" );
        TypeEventComboBox.setItems(list);
////        String combobox= TypeEventComboBox.getSelectionModel().toString();
////        
////        TypeEventComboBox.getSelectionModel().select(1);
                
                
                
        IdOrganisteurEventField.setText(String.valueOf(IdOrganisateur));
//        TypeEventField.setText(String.valueOf(ev.getType()));
        TitreEventField.setText(String.valueOf(ev.getTitre()));
        LieuEventField.setText(String.valueOf(ev.getLieu()));
        DateEventField.setValue(LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH)));
        ImageEventField.setText(String.valueOf(ev.getImage()));
        DescriptionEventField.setText(String.valueOf(ev.getDescription()));
        TarifEventField.setText(String.valueOf(ev.getTarif()));
        
                capaciteEventField.setText(String.valueOf(ev.getCapacite()));

               
                String combobox= TypeEventComboBox.getSelectionModel().toString();
                System.out.println(combobox);
        if("Conférence".equals(ev.getType()))
        TypeEventComboBox.getSelectionModel().select(0);
        else if("Cinéma".equals(ev.getType()))
        TypeEventComboBox.getSelectionModel().select(1);
         else if("Méditation".equals(ev.getType()))
        TypeEventComboBox.getSelectionModel().select(2);
         else if("Musique".equals(ev.getType()))
        TypeEventComboBox.getSelectionModel().select(3);
         else if("Randonnée".equals(ev.getType()))
        TypeEventComboBox.getSelectionModel().select(4);
           else if("Sport et fitness".equals(ev.getType()))
        TypeEventComboBox.getSelectionModel().select(5);
        

        String img= ev.getImage();
        String ch="/imgEvent/";
        String imgF= ch+img;
        
   //     DescriptionEventField.setText(String.valueOf(ev.getDescription()));

//         String chemin="/images/icons8_microsoft_powerpoint_2019_48px.png";
//       Image im = new Image(getClass().getResourceAsStream(chemin));
//        imageView.setImage(im);

        Image im = new Image(getClass().getResourceAsStream(imgF));
        imageView.setImage(im);
        

        List<Evenement> lc = cs.ListClasse();
        
        ObservableList<Evenement> data =         
        FXCollections.observableArrayList(lc );
        TableView.setItems(data);
    }

    
    
public void viderChamps()
{
     IdOrganisteurEventLabel.setText(null);
     // TypeEventLabel.setText(null);
      TitreEventLabel.setText(null);
      DescriptionEventLabel.setText(null);
      LieuEventLabel.setText(null);
      DateEventLabel.setText(null);
      TarifEventLabel.setText(null);
 

 IdOrganisteurEventField.clear();
      //TypeEventField.clear();
      TitreEventField.clear();
      DescriptionEventField.clear();
      LieuEventField.clear();
      DateEventField.setValue(null);
      IdOrganisteurEventField.clear();
      ImageEventField.clear();
      TarifEventField.clear();
      capaciteEventField.clear();
      
      imageView.setImage(null);
}

    @FXML
    private void deconnexion(ActionEvent event) {
      
      
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherEventClient.fxml"));
        try {
            Parent root = loader.load();
            btnDeconnexion.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherEventClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    

    @FXML
    private void importerImage(MouseEvent event) {
        
 
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
        ImageEventField.setText("");
            }else
            ImageEventField.setText(selected.getName());
        }
    }

    @FXML
    private void clear(MouseEvent event) {
        
        viderChamps();
    }

    @FXML
    private void PageStatEvent(MouseEvent event) {
    
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StatEventTherapeute.fxml"));
        try {
            Parent root = loader.load();
            StatEvent.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherEventClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

////    @FXML
////    private void PageReservationEvent(MouseEvent event) {
////        
////        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservationEventTherapeute.fxml"));
////        try {
////            Parent root = loader.load();
////            btnDeconnexion.getScene().setRoot(root);
////        } catch (IOException ex) {
////            Logger.getLogger(AfficherEventClientController.class.getName()).log(Level.SEVERE, null, ex);
////        }
////    }

    @FXML
    private void PageReservationEvent(MouseEvent event) {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservationEventTherapeute.fxml"));
        try {
            Parent root = loader.load();
            PageReservationEventLabel.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherEventClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
}
