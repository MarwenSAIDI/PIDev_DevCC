/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package therapeuteinterfaces;

import Clientinterfaces.AfficherEventClientController;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import services.ServiceEvenement;
import entities.Evenement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Hyperlink;
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
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.ServiceEvenement;
import services.UserSession;
import utils.DataBase;
import utils.sqlexcept;

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
    private DatePicker DateEventField;
    private TextField IdOrganisteurEventField;
    private TextField TitreEventField;
    private TextArea DescriptionEventField;
    private TextField LieuEventField;
    @FXML
    private Button ModiferEventButton;
    @FXML
    private Button SupprimerEventButton;
    private Label TitreEventLabel;
    private Label DescriptionEventLabel;
    private Label IdOrganisteurEventLabel;
    private Label TypeEventLabel;
    private Label LieuEventLabel;
    @FXML
    private Label DateEventLabel;
    private TextField ImageEventField;
    @FXML
    private TableColumn <Evenement, String>ImageEventColumn;
    private Button btnDeconnexion;
  private Image image;
   private ImageView imageEvent;
    @FXML
    private ImageView imageView;
    private ComboBox TypeEventComboBox;
    private TextField TarifEventField;
    private Label TarifEventLabel;
    private TextField capaciteEventField;
    private Label capaciteEventLabel;
    @FXML
    private TableColumn<Evenement, Integer> capciteColoumn;
    @FXML
    private TableColumn<Evenement, Integer>nbRservatColomun;
    @FXML
    private TableColumn<Evenement, String>etatReservColoumn;
    @FXML
    private JFXButton StatEvent;
    @FXML
    private JFXButton PageReservationEventLabel;
 
    /**
     * Initializes the controller class.
     * 
     * 
     * 
     */
    private Statement ste;
                DataBase db=new DataBase();
               private Connection con=db.getConnection();
    private Label imageEventLabel;
    @FXML
    private Button BtnInterfaceAjout;
    @FXML
    private ImageView btnNotification;
    @FXML
    private Label labelemail;
    @FXML
    private Hyperlink deconnecter;
    @FXML
    private ComboBox<String> combox_recherche_type;
    @FXML
    private JFXButton btnaccueil;
    @FXML
    private JFXButton btnEvent;
    @FXML
    private JFXButton recommend;
    @FXML
    private JFXButton btncons;
    @FXML
    private JFXButton demres;
    @FXML
    private JFXButton stat;
         
    
    public void etat()
    {
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              ObservableList<String> typee;
        typee = FXCollections.observableArrayList("Conférence","Cinéma","Méditation","Musique","Randonnée","Sport et fitness" );
        combox_recherche_type.setItems(typee);
                                   labelemail.setText( UserSession.instance.getUserName());

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
        

         
        
  
    }    
    
  

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
               
            if(test_date==1)
            {//date1.compareTo(date2) < 0
                 Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Alerte");
               alert.setHeaderText("Vous avez déja un événement à cette date ");
               alert.setContentText(" Veuillez choisir une autre date");
               
               alert.showAndWait(); 
               DateEventLabel.setText("Choisir une autre Date");
            }
            else if(test_date1==1)
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
            Evenement c = new Evenement(0, idorga, Typee, Titre,LieuEvent,Description,Date,ImagEvent,tarifF,capa,0,"en cours");

                 cs.ajouter1(c);
               Notifications.create()
                       .title("Notification !!")
                       .text(" Vous avez ajouté un nouvel événement: "+Titre )
                       
                       .showInformation();
                //Champs();
            }
             }
       
         List<Evenement> lc = cs.ListClasse();
        
        ObservableList<Evenement> data =         
        FXCollections.observableArrayList(lc );
        TableView.setItems(data);
        
   
      
    }

    public void raffraichir_tableView()
    {
                ServiceEvenement cs = new ServiceEvenement();

         List<Evenement> lc = cs.ListClasse();
        
        ObservableList<Evenement> data =         
        FXCollections.observableArrayList(lc );
        TableView.setItems(data);
        
        combox_recherche_type.getSelectionModel().clearSelection();
    }
  
    @FXML
    private void ModiferEventButton(ActionEvent event) throws ParseException{
       
            Evenement ev = TableView.getSelectionModel().getSelectedItem();

 if(ev==null)
            {
                Alert alert1 = new Alert(Alert.AlertType.WARNING);
               alert1.setTitle("Alerte");
               alert1.setHeaderText("Séléctionner un événement");
               alert1.setContentText("Séléctionner un événement à modifier puis cliquez sur modifier");
               
               alert1.showAndWait(); 
            }
            else
            {
            int IdOrganisateur = ev.getId_organisateur();
            String idOrga=Integer.toString(IdOrganisateur);

            String Type= ev.getType();
            String Titre= ev.getTitre();
            String Description= ev.getDescription();
            String LieuEvent= ev.getLieu();
            
            Date DateEvent= ev.getDate_event();
//            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
//            String strDate = dateFormat.format(DateEvent);  

            String ImagEvent= ev.getImage();
            float tarif= ev.getTarif();
            String tarifF =Float.toString(tarif);
            
            int capacite = ev.getCapacite();
            String capaciteF=Integer.toString(capacite);

// Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Modification");
//            alert.setHeaderText("Voulez vous modifier cette événement");
//            alert.setContentText("Confirmez la modification");
//            Optional<ButtonType> btn = alert.showAndWait();
//            if(btn.get()==ButtonType.OK)
//            {
//           
//            Evenement c = new Evenement(ev.getId(), idorga, Type, Titre,LieuEvent,Description,Date,ImagEvent,tarifF,capa,ev.getNb_reservation(),ev.getEtat());
//        ServiceEvenement cs = new ServiceEvenement();
//
//        cs.ModifierEvenement(c);
//
//        List<Evenement> lc = cs.ListClasse();
//        
//        ObservableList<Evenement> data =         
//        FXCollections.observableArrayList(lc );
//        TableView.setItems(data);
//        
//      viderChamps();  
//       }
//            else {alert.close();}
            /////new part
            String nbReser=Integer.toString(ev.getNb_reservation());
            String id=Integer.toString(ev.getId());
            


             FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterEventTherapeute.fxml"));
             
        try {
            
            Parent root = loader.load();
            Scene scene = new Scene(root); 
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);  
            primaryStage.setTitle("Ajouter un événement");  
            
            primaryStage.centerOnScreen();  
            primaryStage.setResizable(false);  
            primaryStage.setOpacity(1);  
            primaryStage.show();
            
            AjouterEventTherapeuteController pdc = loader.getController();
            pdc.setCapaciteEventField(capaciteF);
            pdc.setDateEventField(DateEvent);
            pdc.setDescriptionEventField(Description);
            pdc.setIdOrganisteurEventField(idOrga);
            pdc.setImageEventField(ImagEvent);
            pdc.setLieuEventField(LieuEvent);
            pdc.setTarifEventField(tarifF);
            pdc.setTitreEventField(Titre);
            pdc.setTypeEventComboBox(Type);
            pdc.setNbrReservationLabel(nbReser);
            pdc.setEtatReservationLabel(ev.getEtat());
            pdc.setIdReservationLabel(id);
            pdc.setNbrReservationLabel1("Nombre de réservation :");
            pdc.setEtatReservationLabel1("Etat :");
            pdc.setIdReservationLabel1("ID réservation :");
            
           // ModiferEventButton.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(AfficherEventClientController.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }

    @FXML
    private void SupprimerEventButton(ActionEvent event) throws ParseException {
        
      Evenement ev = TableView.getSelectionModel().getSelectedItem();
            ServiceEvenement cs = new ServiceEvenement();
            if(ev==null)
            {
                Alert alert1 = new Alert(Alert.AlertType.WARNING);
               alert1.setTitle("Alerte");
               alert1.setHeaderText("Séléctionner un événement");
               alert1.setContentText("Séléctionner un événement à supprimer puis cliquez sur supprimer");
               
               alert1.showAndWait(); 
            }
            else
            {
                
            
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
                // viderChamps();
                
            }
            else {alert.close();}
            }
          
           
             
     
        
       
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
        
        
        
//        
//        ObservableList<String> list;
//        list = FXCollections.observableArrayList("Conférence","Cinéma","Méditation","Musique","Randonnée","Sport et fitness" );
//        TypeEventComboBox.setItems(list);
////        String combobox= TypeEventComboBox.getSelectionModel().toString();
////        
////        TypeEventComboBox.getSelectionModel().select(1);
                
                
                
//        IdOrganisteurEventField.setText(String.valueOf(IdOrganisateur));
////        TypeEventField.setText(String.valueOf(ev.getType()));
//        TitreEventField.setText(String.valueOf(ev.getTitre()));
//        LieuEventField.setText(String.valueOf(ev.getLieu()));
//        DateEventField.setValue(LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH)));
//        ImageEventField.setText(String.valueOf(ev.getImage()));
//        DescriptionEventField.setText(String.valueOf(ev.getDescription()));
//        TarifEventField.setText(String.valueOf(ev.getTarif()));
//        
//                capaciteEventField.setText(String.valueOf(ev.getCapacite()));
//
//               
//                String combobox= TypeEventComboBox.getSelectionModel().toString();
//                System.out.println(combobox);
//        if("Conférence".equals(ev.getType()))
//        TypeEventComboBox.getSelectionModel().select(0);
//        else if("Cinéma".equals(ev.getType()))
//        TypeEventComboBox.getSelectionModel().select(1);
//         else if("Méditation".equals(ev.getType()))
//        TypeEventComboBox.getSelectionModel().select(2);
//         else if("Musique".equals(ev.getType()))
//        TypeEventComboBox.getSelectionModel().select(3);
//         else if("Randonnée".equals(ev.getType()))
//        TypeEventComboBox.getSelectionModel().select(4);
//           else if("Sport et fitness".equals(ev.getType()))
//        TypeEventComboBox.getSelectionModel().select(5);
//        
//
//        String img= ev.getImage();
//        String ch="/imgEvent/";
//        String imgF= ch+img;
//        
//   //     DescriptionEventField.setText(String.valueOf(ev.getDescription()));
//
////         String chemin="/images/icons8_microsoft_powerpoint_2019_48px.png";
////       Image im = new Image(getClass().getResourceAsStream(chemin));
////        imageView.setImage(im);
//
//        Image im = new Image(getClass().getResourceAsStream(imgF));
//        imageView.setImage(im);
        

        List<Evenement> lc = cs.ListClasse();
        
        ObservableList<Evenement> data =         
        FXCollections.observableArrayList(lc );
        TableView.setItems(data);
    }

    /*
    
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
}*/


    

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
        
        raffraichir_tableView();
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

    @FXML
    private void InterfaceAjout(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("AjouterEventTherapeute.fxml"));

     Scene scene = new Scene(root);  
      Stage primaryStage = new Stage();
     primaryStage.setScene(scene);  
     primaryStage.setTitle("Ajouter un événement");  
     primaryStage.centerOnScreen();  
     primaryStage.setResizable(false);  
     primaryStage.setOpacity(1);  
     primaryStage.show();  
    }

    @FXML
    private void AfficherNotifications(MouseEvent event) throws SQLException {
        String requete = "select id,count(id) as idd FROM notifications  where etat = ?  ";
        int result;
        try (PreparedStatement p = con.prepareStatement(requete)) {
            p.setString(1, "non lu");
            result = 0;
            ResultSet ee = p.executeQuery();
            ee.next();
            result=ee.getInt(1);
            ee.close();
        }
            
            
        if(result!=0)
        {

            Notifications  notif= Notifications.create();
                 
            notif.title("Notifications des réservations");
            notif.text("vous avez "+result+" nouvelles réservations à vos événements");
            notif.graphic(null);
            notif.hideAfter(Duration.seconds(15));
            notif.position(Pos.TOP_RIGHT);
            notif.showConfirm();
            
             try {
         String requete2 = "update `notifications`  set etat=? where ? = etat ";
            PreparedStatement pre2 = con.prepareStatement(requete2);
            pre2.setString(1,"lu");           
            pre2.setString(2, "non lu");
            pre2.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
             
        }
        else {
            Notifications  notif= Notifications.create();
                 
               
            notif.title("Notifications des réservations");
            notif.text("Vous n'avez pas de nouvelles réservations à vos événements");
            notif.graphic(null);
            notif.hideAfter(Duration.seconds(15));
            notif.position(Pos.TOP_RIGHT);
            notif.showConfirm();
        }

    }


   
      @FXML
    private void recherchetype(ActionEvent event) {
        
        ObservableList<String> typee;
        typee = FXCollections.observableArrayList("Conférence","Cinéma","Méditation","Musique","Randonnée","Sport et fitness" );
        combox_recherche_type.setItems(typee);
          String Typee= combox_recherche_type.getSelectionModel().getSelectedItem().toString();
        
        ServiceEvenement cs = new ServiceEvenement();
        List<Evenement> lc = cs.ListClasse_recherche_type(Typee);
        
        ObservableList<Evenement> data =         
        FXCollections.observableArrayList(lc );
        TableView.setItems(data);
                  
    }

    @FXML
    private void deconnexion(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/authentification/authentification.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            deconnecter.getScene().setRoot(root);
            
    }



    @FXML
    private void cons(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Crudconsultation.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btncons.getScene().setRoot(root);
    }

    @FXML
    private void demanderes(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("affichagereservationtherapeute.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            demres.getScene().setRoot(root);
    }

    @FXML
    private void statclick(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chartcons.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            stat.getScene().setRoot(root);
    }


    @FXML
    private void gotocrudevent(MouseEvent event)throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CrudEvent.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btnEvent.getScene().setRoot(root);
            
    }

    @FXML
    private void gotostatevent(MouseEvent event)throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StatEventTherapeute.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            StatEvent.getScene().setRoot(root);
            
    }

    @FXML
    private void circleclick(MouseEvent event) {
    }

    @FXML
    private void gotoaccueil(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accueiltherapeute.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            recommend.getScene().setRoot(root);
    }

    @FXML
    private void Pagerecomm(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Recommandation.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            recommend.getScene().setRoot(root);
    }

}
