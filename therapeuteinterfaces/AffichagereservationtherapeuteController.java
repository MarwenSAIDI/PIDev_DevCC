
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package therapeuteinterfaces;

import Clientinterfaces.AfficherEventClientController;
import entities.Reservationconsultation;
import entities.consultation;
import entities.client;
import services.servicereservation;
import utils.DataBase;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import services.UserSession;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author foura
 */
public class AffichagereservationtherapeuteController implements Initializable {

    @FXML
    private TextField rech;
    @FXML
    private Button insertButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<Reservationconsultation> TableView;
    @FXML
    private TableColumn<Reservationconsultation, Integer> idclientColumn;
    @FXML
    private TableColumn<Reservationconsultation, Date> dateColumn;
    @FXML
    private TableColumn<Reservationconsultation, String> typeColumn;
    @FXML
    private TableColumn<Reservationconsultation, String> heureColumn;
            DataBase db = new DataBase();
    @FXML
    private JFXButton btncons;
    @FXML
    private TableColumn<Reservationconsultation, String> etatColumn;
    @FXML
    private ImageView notifbtn;
    @FXML
    private JFXButton stat;
    @FXML
    private Label nbtotale;
    @FXML
    private Label nbattente;
servicereservation sr= new servicereservation();


    private int cinclient=sr.rendrecin(UserSession.instance.getUserName());

    @FXML
    private TableColumn<Reservationconsultation, client> id;
    @FXML
    private Label nomclient;
    @FXML
    private Label Stat;
    @FXML
    private TableColumn<Reservationconsultation, Integer> idreservation;
    @FXML
    private JFXButton StatEvent;
    @FXML
    private JFXButton PageReservationEventLabel;
    @FXML
    private JFXButton btnaccueil;
    @FXML
    private JFXButton demres;
    @FXML
    private JFXButton btnEvent;
    @FXML
    private JFXButton recommend;
    @FXML
    private Label labelemail;
    @FXML
    private Hyperlink deconnecter;


    /**
     * Initializes the controller class.
     */
     public void executeQuery(String query) {
    	Connection conn = db.getConnection();
    	Statement st;
    	try {
			st = conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
         public ObservableList<Reservationconsultation> getconsultationList(){
    	ObservableList<Reservationconsultation> reservationsList = FXCollections.observableArrayList();
            
        
    	Connection connection = db.getConnection();
    	String query = "SELECT user.nom,reservation.* FROM reservation INNER join user on user.cin=reservation.idclient and reservation.idconsultation= '"+cinclient+"'";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Reservationconsultation reservations;
			while(rs.next()) {
                            //    public Reservationconsultation(int idclient, Date date, String type, String heure, String etat, String image, user client) {

				reservations = new Reservationconsultation(rs.getInt("reservation.idclient"), rs.getDate("reservation.date"), rs.getString("reservation.type"), rs.getString("reservation.heure"), rs.getString("reservation.etat"), rs.getString("reservation.image"), rs.getString("user.nom"),rs.getInt("reservation.idreservation"));
                                        reservationsList.add(reservations);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return reservationsList;
    }
         public void showconsultation() {
    	ObservableList<Reservationconsultation> list = getconsultationList();
    	
 
    	idclientColumn.setCellValueFactory(new PropertyValueFactory<Reservationconsultation,Integer>("idclient"));
    	idreservation.setCellValueFactory(new PropertyValueFactory<Reservationconsultation,Integer>("idreservation"));
        idclientColumn.setVisible(false);
        idreservation.setVisible(false);
    	id.setCellValueFactory(new PropertyValueFactory<Reservationconsultation,client>("client"));
    	dateColumn.setCellValueFactory(new PropertyValueFactory<Reservationconsultation,Date>("date"));
    	typeColumn.setCellValueFactory(new PropertyValueFactory<Reservationconsultation,String>("type"));
    	heureColumn.setCellValueFactory(new PropertyValueFactory<Reservationconsultation,String>("heure"));
        etatColumn.setCellValueFactory(new PropertyValueFactory<Reservationconsultation,String>("etat"));


    	TableView.setItems(list);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                           labelemail.setText( UserSession.instance.getUserName());

showconsultation();

        for (Map.Entry<Integer, Integer> en : sr.calcullnb(cinclient).entrySet()) {
            nbattente.setText(""+en.getKey());
            nbtotale.setText(""+(en.getValue()-en.getKey()));
          
   
        }
        

    }    

    @FXML
    private void rechtest(KeyEvent event) {
            ObservableList<Reservationconsultation> list = getconsultationList();
FilteredList<Reservationconsultation> filtereddata= new FilteredList<>(list,b->true);
rech.textProperty().addListener((observable,oldValue,newValue)->{
        filtereddata.setPredicate(Reservationconsultation -> {
            
            if (newValue == null || newValue.isEmpty()){
                return true;
        }
                String lowerCaseFilter = newValue.toLowerCase();
                       //             if(String.valueOf(consultation.getPrix()).indexOf(lowerCaseFilter)!= -1)

                if (String.valueOf(Reservationconsultation.getClient()).indexOf(lowerCaseFilter)!= -1)
            return true;
                else
                if (String.valueOf(Reservationconsultation.getType()).indexOf(lowerCaseFilter)!= -1)
            return true;
                else
                     if (String.valueOf(Reservationconsultation.getDate()).indexOf(lowerCaseFilter)!= -1)
            return true;
                else
                       if (Reservationconsultation.getHeure().toLowerCase().indexOf(lowerCaseFilter)!= -1)
            return true;
                else
                              if (Reservationconsultation.getEtat().toLowerCase().indexOf(lowerCaseFilter)!= -1)
            return true;
              
                else
            return false;
                });
                
    });
SortedList<Reservationconsultation> sortedData = new SortedList<>(filtereddata);
sortedData.comparatorProperty().bind(TableView.comparatorProperty());
TableView.setItems(sortedData);
        
    }

    @FXML
    private void rechtest2(KeyEvent event) {
    }

    @FXML
    private void insertButton(ActionEvent event) {
        System.out.println("bbbaaaaaaaaaaaaaaaa");
        try {
                      Reservationconsultation    res2=TableView.getSelectionModel().getSelectedItem();
Reservationconsultation r= new Reservationconsultation(res2.getIdreservation(),"Confirmé");
sr.updateetat(r);
refresh();
      } catch(Exception e) {
            System.out.println(e);      }
        System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkk");

    }

    void refresh()
    {
        try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/therapeuteinterfaces/affichagereservationtherapeute.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btncons.getScene().setRoot(root);
    } catch(Exception e) {
       e.printStackTrace();
      }
    }
    
    @FXML
    private void deleteButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Voulez vous refuser cette reservation?");
        alert.setContentText("Confirmer la suppression"); 
        Optional<ButtonType> btn = alert.showAndWait();
     if (btn.get()==ButtonType.OK){
                        Reservationconsultation res=TableView.getSelectionModel().getSelectedItem();
sr.delete(res);
     }
     else
     {
         alert.close();
     }
refresh();
        
    }
    

    @FXML
    private void afficherform(MouseEvent event) {
        Reservationconsultation res;

              res=TableView.getSelectionModel().getSelectedItem();
                
                for (Map.Entry<String , Float> entry : sr.calcullnb2(res.getIdclient(),cinclient).entrySet()) {
                    if(entry.getKey()!=null)
                    {nomclient.setText("Client : "+entry.getKey());
                        System.out.println("testt"+entry.getValue());
                        
Stat.setText("pourcentage des demandes confirmé "+(entry.getValue()*100)+"%");}

            
        }
    }

    @FXML
    private void cons(MouseEvent event) {
          try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/therapeuteinterfaces/Crudconsultation.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btncons.getScene().setRoot(root);
    } catch(Exception e) {
       e.printStackTrace();
      }
    }
 private void notiff()
    {
          servicereservation sr=new servicereservation();
        int k=sr.calculnbnonvalide(cinclient);
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("Reservations non validées");
        tray.setMessage("il existe "+k + " reservations non validées");
tray.setNotificationType(NotificationType.INFORMATION);
tray.showAndDismiss(Duration.millis(2000));
    }
    @FXML
    private void notifclick(MouseEvent event) {
              notiff();

    }

       @FXML
    private void deconnexion(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/authentification/authentification.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            deconnecter.getScene().setRoot(root);
            
    }

    @FXML
    private void PageReservationEvent(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReservationEventTherapeute.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            PageReservationEventLabel.getScene().setRoot(root);
            
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
