
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import Entite.Reservationconsultation;
import Entite.consultation;
import Entite.user;
import Service.servicereservation;
import UtilsBD.DataBase;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
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
    private Button idretour;
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
    private Label btncons;
    @FXML
    private TableColumn<Reservationconsultation, String> etatColumn;
    @FXML
    private ImageView notifbtn;
    @FXML
    private Label stat;
    @FXML
    private Label nbtotale;
    @FXML
    private Label nbattente;
servicereservation sr= new servicereservation();


    @FXML
    private TableColumn<Reservationconsultation, user> id;
    @FXML
    private Label nomclient;
    @FXML
    private Label Stat;
    @FXML
    private TableColumn<Reservationconsultation, Integer> idreservation;

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
    	String query = "SELECT user.nom,reservation.* FROM reservation INNER join user on user.cin=reservation.idclient";
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
    	id.setCellValueFactory(new PropertyValueFactory<Reservationconsultation,user>("client"));
    	dateColumn.setCellValueFactory(new PropertyValueFactory<Reservationconsultation,Date>("date"));
    	typeColumn.setCellValueFactory(new PropertyValueFactory<Reservationconsultation,String>("type"));
    	heureColumn.setCellValueFactory(new PropertyValueFactory<Reservationconsultation,String>("heure"));
        etatColumn.setCellValueFactory(new PropertyValueFactory<Reservationconsultation,String>("etat"));


    	TableView.setItems(list);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
showconsultation();

        for (Map.Entry<Integer, Integer> en : sr.calcullnb().entrySet()) {
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
                
                if (Reservationconsultation.getClient().toLowerCase().indexOf(lowerCaseFilter)!= -1)
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
                      Reservationconsultation    res2=TableView.getSelectionModel().getSelectedItem();
Reservationconsultation r= new Reservationconsultation(res2.getIdreservation(),"Confirmé");
sr= new servicereservation();
sr.updateetat(r);
refresh();
        System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkk");

    }

    void refresh()
    {
        try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projet/affichagereservationtherapeute.fxml"));
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
    private void retourtest(ActionEvent event) {
    }

    @FXML
    private void afficherform(MouseEvent event) {
        Reservationconsultation res;

              res=TableView.getSelectionModel().getSelectedItem();
                
                for (Map.Entry<String , Float> entry : sr.calcullnb2(res.getIdclient()).entrySet()) {
                    if(entry.getKey()!=null)
                    {nomclient.setText("Client : "+entry.getKey());
                        Stat.setText("pourcentage des demandes confirmé "+(entry.getValue()*100)+"%");}

            
        }
    }

    @FXML
    private void cons(MouseEvent event) {
          try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projet/test.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            btncons.getScene().setRoot(root);
    } catch(Exception e) {
       e.printStackTrace();
      }
    }
 private void notiff()
    {
          servicereservation sr=new servicereservation();
        int k=sr.calculnbnonvalide();
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
    private void statclick(MouseEvent event) {
                    try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projet/chartcons.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            stat.getScene().setRoot(root);
    } catch(Exception e) {
       e.printStackTrace();
      }
    }
    
}
