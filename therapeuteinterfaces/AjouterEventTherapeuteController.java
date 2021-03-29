/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package therapeuteinterfaces;

import therapeuteinterfaces.CrudEventController;
import entities.Evenement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import org.controlsfx.control.Notifications;
import services.ServiceEvenement;
import utils.DataBase;
import utils.sqlexcept;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjouterEventTherapeuteController implements Initializable {

    @FXML
    private TextField TitreEventField;
    @FXML
    private TextField TarifEventField;
    @FXML
    private Label TarifEventLabel;
    @FXML
    private TextArea DescriptionEventField;
    @FXML
    private Label TitreEventLabel;
    @FXML
    private DatePicker DateEventField;
    @FXML
    private Label DescriptionEventLabel;
    @FXML
    private TextField ImageEventField;
    @FXML
    private TextField IdOrganisteurEventField;
    @FXML
    private Label IdOrganisteurEventLabel;
    @FXML
    private Label TypeEventLabel;
    @FXML
    private TextField LieuEventField;
    @FXML
    private Label LieuEventLabel;
    @FXML
    private ComboBox TypeEventComboBox;
    @FXML
    private TextField capaciteEventField;
    @FXML
    private Label capaciteEventLabel;
    @FXML
    private Label imageEventLabel;
    @FXML
    private Label DateEventLabel;
    @FXML
    private Button AjoutEventButton;

    @FXML
    private Label EventAjouterLabel;
    @FXML
    private Button ModiferEventButton;
    @FXML
    private Label IdReservationLabel;
    @FXML
    private Label NbrReservationLabel;
    @FXML
    private Label EtatReservationLabel;
    @FXML
    private Label IdReservationLabel1;
    @FXML
    private Label NbrReservationLabel1;
    @FXML
    private Label EtatReservationLabel1;
    /**
     * Initializes the controller class.
     */
    private Statement ste;
                DataBase db=new DataBase();
               private Connection con=db.getConnection();
               
    @Override
    public void initialize(URL url, ResourceBundle rb) {

         ObservableList<String> list;
        list = FXCollections.observableArrayList("Conférence","Cinéma","Méditation","Musique","Randonnée","Sport et fitness" );
        TypeEventComboBox.setItems(list);
        
            }    

    public void setTitreEventField(String TitreEventField) {
        this.TitreEventField.setText(TitreEventField);
    }

    public void setTarifEventField(String TarifEventField) {
        this.TarifEventField.setText(TarifEventField);
    }

    public void setDescriptionEventField(String DescriptionEventField) {
        this.DescriptionEventField.setText(DescriptionEventField);
    }

    public void setDateEventField(Date DateEventField) {
         Date date =DateEventField;
      
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        this.DateEventField.setValue(LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH)));

    }

    public void setCapaciteEventField(String capaciteEventField) {
        this.capaciteEventField.setText(capaciteEventField);
    }

    public void setImageEventField(String ImageEventField) {
        this.ImageEventField.setText(ImageEventField);
    }

    public void setIdOrganisteurEventField(String IdOrganisteurEventField) {
        this.IdOrganisteurEventField.setText(IdOrganisteurEventField);
    }

    public void setLieuEventField(String LieuEventField) {
        this.LieuEventField.setText(LieuEventField);
    }

    public void setTypeEventComboBox(String TypeEventComboBox) {
        
        
       // this.TypeEventComboBox.setText(TypeEventComboBox);
        
        
        String combobox= this.TypeEventComboBox.getSelectionModel().toString();
        System.out.println(combobox);
        if("Conférence".equals(TypeEventComboBox))
        this.TypeEventComboBox.getSelectionModel().select(0);
        else if("Cinéma".equals(TypeEventComboBox))
        this.TypeEventComboBox.getSelectionModel().select(1);
         else if("Méditation".equals(TypeEventComboBox))
       this. TypeEventComboBox.getSelectionModel().select(2);
         else if("Musique".equals(TypeEventComboBox))
        this.TypeEventComboBox.getSelectionModel().select(3);
         else if("Randonnée".equals(TypeEventComboBox))
        this.TypeEventComboBox.getSelectionModel().select(4);
           else if("Sport et fitness".equals(TypeEventComboBox))
        this.TypeEventComboBox.getSelectionModel().select(5);
        
    }

    public void setNbrReservationLabel(String NbrReservationLabel) {
        this.NbrReservationLabel.setText(NbrReservationLabel);
    }

   
    public void setIdReservationLabel(String IdReservationLabel) {
        this.IdReservationLabel.setText(IdReservationLabel);
    }

    public void setEtatReservationLabel(String EtatReservationLabel) {
        this.EtatReservationLabel.setText(EtatReservationLabel);
    }
  public void setNbrReservationLabel1(String NbrReservationLabel) {
        this.NbrReservationLabel1.setText(NbrReservationLabel);
    }

   
    public void setIdReservationLabel1(String IdReservationLabel) {
        this.IdReservationLabel1.setText(IdReservationLabel);
    }

    public void setEtatReservationLabel1(String EtatReservationLabel) {
        this.EtatReservationLabel1.setText(EtatReservationLabel);
    }

 

    @FXML
    private void AjoutEventButton(ActionEvent event) throws ParseException, IOException, sqlexcept {
        
        
         ObservableList<String> list;
        list = FXCollections.observableArrayList("Conférence","Cinéma","Méditation","Musique","Randonnée","Sport et fitness" );
       TypeEventComboBox.setItems(list);
        
        String IdOrganisateur = IdOrganisteurEventField.getText();
//         String Type= TypeEventComboBox.getSelectionModel().getSelectedItem().toString();
         String Typee=" ";
         boolean isMyComboBoxEmpty = TypeEventComboBox.getSelectionModel().isEmpty();
if(isMyComboBoxEmpty)
             Typee=" ";
                     else
            Typee= TypeEventComboBox.getSelectionModel().getSelectedItem().toString();
            
            
            
            
            String Titre= TitreEventField.getText();
            String Description= DescriptionEventField.getText();
            String LieuEvent= LieuEventField.getText();
            String DateEvent=" ";
           DateEvent= String.valueOf(DateEventField.getValue());
            int idorga =0;
            if(IdOrganisateur.isEmpty())
            {
                idorga =0;
            }
            else 
            {
              idorga=Integer.parseInt(IdOrganisateur);

            }    
            String ImagEvent= ImageEventField.getText();
//            Date Date= null;
          

          
           
            String tarif= TarifEventField.getText();
             
            float tarifF = 0;
//                    if(tarif.isEmpty())
//                    {
//                       tarifF = 0;
//                    }
//                    else{
//                       tarifF = Float.parseFloat(tarif);
//                    } 
//                    
                    String capacite = capaciteEventField.getText();
                    int capa = 0;
//                    if(capacite.isEmpty())
//                    {
//                       capa = 0; 
//                    }
//                    else{
//                        capa=Integer.parseInt(capacite);
//                    } 
                  
                                     ServiceEvenement cs = new ServiceEvenement();
       
               int test_date=0;     
               int   test_date1=0;      


     
           if( isMyComboBoxEmpty==true ||tarif.isEmpty() || (" ".equals(Typee))|| (!cs.estUnFloat(tarif))|| (!cs.estUnEntier(capacite))|| /*(idorga==0)||(capa==0) ||*/ (DateEventField.getValue() == null) || (IdOrganisateur.isEmpty())||(Titre.isEmpty())||(Description.isEmpty())||(LieuEvent.isEmpty())||(DateEvent.isEmpty()))
           {
               Alert alert1 = new Alert(Alert.AlertType.WARNING);
               alert1.setTitle("Alerte");
               alert1.setHeaderText("Verifier vos champs");
               alert1.setContentText(" Un des champs est vide");
               
               alert1.showAndWait();
               
                          
            if(IdOrganisateur.isEmpty())
           {
               IdOrganisteurEventLabel.setText("Saisir l'id de l'Organisateur");
           }
            if(Titre.isEmpty()){
               TitreEventLabel.setText("Saisir le titre de l'événement");
           }
             if(Description.isEmpty()){
               DescriptionEventLabel.setText("Saisir la description de l'événement");
           }
              if(LieuEvent.isEmpty()){
               LieuEventLabel.setText("Saisir le Lieu de l'événement");
           }
              if(tarif.isEmpty()){
               TarifEventLabel.setText("Saisir le tarif");
           }
            
               if(!cs.estUnFloat(tarif))
            TarifEventLabel.setText("Vous devez taper un nombre");
               
              if(" ".equals(Typee))
              {
                  TypeEventLabel.setText("Saisir le Type ");
              }
              if(ImagEvent.isEmpty())
              {
                  imageEventLabel.setText("Choisir une image");
              }
              if(capacite.isEmpty())
              {
                  capaciteEventLabel.setText("Saisir la capacité");
              }
               if(!cs.estUnEntier(capacite))
            capaciteEventLabel.setText("Vous devez taper un nombre");
               
                      if( DateEventField.getValue() == null)
              {
                  DateEventLabel.setText("Saisir la Date");
              }
           }
           else
           {
               

                 Date Date=new SimpleDateFormat("yyyy-MM-dd").parse(DateEvent);
           
            String ta_date = new SimpleDateFormat("yyyy-MM-dd").format(Date);
               System.out.println("date : "+ta_date);
               
               try {
                     String requete = "SELECT * FROM `evenement`  where ? = id_organisateur ";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, idorga);
            ResultSet e = pst.executeQuery();
            while (e.next()) {
               Date d= e.getDate("date_event");
               String d2 = new SimpleDateFormat("yyyy-MM-dd").format(d);
           
               Date date_du_jour= new Date();
               String d_jour = new SimpleDateFormat("yyyy-MM-dd").format(date_du_jour);
                if(d2.equals(ta_date))
                {
                    test_date=1;
                }
                 if(d_jour.compareTo(ta_date) > 0)
                {
                    test_date1=1;
                }
               
                
            }
               } catch (Exception ex) {
                   System.out.println(ex);
               }
               
            if(test_date==1 || test_date1==1  )
            {//date1.compareTo(date2) < 0
                if(test_date==1 )
                {
                 Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Alerte");
               alert.setHeaderText("Vous avez déja un événement à cette date ");
               alert.setContentText(" Veuillez choisir une autre date");
               
               alert.showAndWait(); 
               DateEventLabel.setText("Choisir une autre Date");
                }
                if( test_date1==1)
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Alerte");
               alert.setHeaderText("Vous ne pouvez pas choisir cette Date");
               alert.setContentText(" Cette est déja passer.\n Veuillez choisir une autre date");
               
               alert.showAndWait(); 
               DateEventLabel.setText("Choisir une autre Date");
                }
            }
//            if(test_date1==1 )
//            {
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//               alert.setTitle("Alerte");
//               alert.setHeaderText("Vous ne pouvez pas choisir cette Date");
//               alert.setContentText(" Cette est déja passer.\n Veuillez choisir une autre date");
//               
//               alert.showAndWait(); 
//               DateEventLabel.setText("Choisir une autre Date");
//            }
            
            else
            {tarifF = Float.parseFloat(tarif);
               capa=Integer.parseInt(capacite);
            Evenement c = new Evenement(0, idorga, Typee, Titre,LieuEvent,Description,Date,ImagEvent,tarifF,capa,0,"en cours");

                 cs.ajouter1(c);
               Notifications.create()
                       .title("Notification !!")
                       .text(" Vous avez ajouté un nouvel événement: "+Titre )
                       
                       .showInformation();
                viderChamps();
                EventAjouterLabel.setText("L'événement a été ajouté avec succés");
    

            }
             }
       
//         List<Evenement> lc = cs.ListClasse();
//        
//        ObservableList<Evenement> data =         
//        FXCollections.observableArrayList(lc );
//        TableView.setItems(data);
        
   
      
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

    
public void viderChamps()
{
     IdOrganisteurEventLabel.setText(null);
     // TypeEventLabel.setText(null);
      TitreEventLabel.setText(null);
      DescriptionEventLabel.setText(null);
      LieuEventLabel.setText(null);
      DateEventLabel.setText(null);
      TarifEventLabel.setText(null);
 capaciteEventLabel.setText(null);
IdReservationLabel.setText(null);;
NbrReservationLabel.setText(null);;
EtatReservationLabel.setText(null);;
IdReservationLabel1.setText(null);;
NbrReservationLabel1.setText(null);;
EtatReservationLabel1.setText(null);;

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
      
      //imageView.setImage(null);
}

    private void clear(MouseEvent event) {
        
        viderChamps();
    }

    @FXML
    private void ModiferEventButton(ActionEvent event) throws ParseException {

             
        
        String IdOrganisateur = IdOrganisteurEventField.getText();
         String Typee=" ";
         boolean isMyComboBoxEmpty = TypeEventComboBox.getSelectionModel().isEmpty();
if(isMyComboBoxEmpty)
             Typee=" ";
                     else
            Typee= TypeEventComboBox.getSelectionModel().getSelectedItem().toString();

            String idEvent= IdReservationLabel.getText();
            String nbrReser= NbrReservationLabel.getText();
            String etat= EtatReservationLabel.getText();
            
            int id=Integer.parseInt(idEvent);
            int nbrReservation=Integer.parseInt(nbrReser);
            
            String Type= TypeEventComboBox.getSelectionModel().getSelectedItem().toString();
            
            
            
            String Titre= TitreEventField.getText();
            String Description= DescriptionEventField.getText();
            String LieuEvent= LieuEventField.getText();
            String DateEvent=" ";
           DateEvent= String.valueOf(DateEventField.getValue());
            int idorga =0;
            if(IdOrganisateur.isEmpty())
            {
                idorga =0;
            }
            else 
            {
              idorga=Integer.parseInt(IdOrganisateur);

            }    
            String ImagEvent= ImageEventField.getText();
//            Date Date= null;
          

          
           
            String tarif= TarifEventField.getText();
             
            float tarifF = 0;
                    if(tarif.isEmpty())
                    {
                       tarifF = 0;
                    }
                    else{
                       tarifF = Float.parseFloat(tarif);
                    } 
                    
                    String capacite = capaciteEventField.getText();
                    int capa = 0;
                    if(capacite.isEmpty())
                    {
                       capa = 0; 
                    }
                    else{
                        capa=Integer.parseInt(capacite);
                    } 
                  
                                     ServiceEvenement cs = new ServiceEvenement();
       
               int test_date=0;     
               int   test_date1=0;      


     
           if( isMyComboBoxEmpty==true ||tarif.isEmpty() || (" ".equals(Typee))||(idorga==0)||(capa==0) || (DateEventField.getValue() == null) || (IdOrganisateur.isEmpty())||(Titre.isEmpty())||(Description.isEmpty())||(LieuEvent.isEmpty())||(DateEvent.isEmpty()))
           {
               Alert alert1 = new Alert(Alert.AlertType.WARNING);
               alert1.setTitle("Alerte");
               alert1.setHeaderText("Verifier vos champs");
               alert1.setContentText(" Un des champs est vide");
               
               alert1.showAndWait();
               
                          
            if(IdOrganisateur.isEmpty())
           {
               IdOrganisteurEventLabel.setText("Saisir l'id de l'Organisateur");
           }
            if(Titre.isEmpty()){
               TitreEventLabel.setText("Saisir le titre de l'événement");
           }
             if(Description.isEmpty()){
               DescriptionEventLabel.setText("Saisir la description de l'événement");
           }
              if(LieuEvent.isEmpty()){
               LieuEventLabel.setText("Saisir le Lieu de l'événement");
           }
              if(tarif.isEmpty()){
               TarifEventLabel.setText("Saisir le tarif");
           }
              
              if(" ".equals(Typee))
              {
                  TypeEventLabel.setText("Saisir le Type ");
              }
              if(ImagEvent.isEmpty())
              {
                  imageEventLabel.setText("Choisir une image");
              }
              if(capacite.isEmpty())
              {
                  capaciteEventLabel.setText("Saisir la capacité");
              }
                      if( DateEventField.getValue() == null)
              {
                  DateEventLabel.setText("Saisir la Date");
              }
           }
           else
           {
                 Date Date=new SimpleDateFormat("yyyy-MM-dd").parse(DateEvent);
           
            String ta_date = new SimpleDateFormat("yyyy-MM-dd").format(Date);
               System.out.println("date : "+ta_date);
               
                  Date date_du_jour= new Date();
               String d_jour = new SimpleDateFormat("yyyy-MM-dd").format(date_du_jour);
              
                 if(d_jour.compareTo(ta_date) > 0)
                {
                    test_date1=1;
                }
         
               
            if(test_date1==1  )
            {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Alerte");
               alert.setHeaderText("Vous ne pouvez pas choisir cette Date");
               alert.setContentText(" Cette est déja passer.\n Veuillez choisir une autre date");
               
               alert.showAndWait(); 
               DateEventLabel.setText("Choisir une autre Date");
                
            }
            
            else
            {
            
 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Modification");
            alert.setHeaderText("Voulez vous modifier cette événement");
            alert.setContentText("Confirmez la modification");
            Optional<ButtonType> btn = alert.showAndWait();
            if(btn.get()==ButtonType.OK)
            {
           
            Evenement c = new Evenement(id, idorga, Type, Titre,LieuEvent,Description,Date,ImagEvent,tarifF,capa,nbrReservation,etat);

        cs.ModifierEvenement(c);

     
      viderChamps();  
       }
            else {alert.close();}
    

            }
             }
       
        
   
    }

    
}
