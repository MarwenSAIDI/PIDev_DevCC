/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgroundcontroller;

import Entite.consultation;
import Service.serviceconsultation;
import Service.servicereservation;
import TEST.test;
import static TEST.test.main;
import UtilsBD.DataBase;
import com.jfoenix.controls.JFXTextArea;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.IOException;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import javafx.scene.image.Image;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import static projet.ModifierRdvController.fcount;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author foura
 */
public class UIController implements Initializable {
public static Stage backstage;
    @FXML
    private TextField idField;
    
    @FXML
    private TextField TitreField;
    @FXML
    private JFXTextArea descField;
    @FXML
    private TextField empField;


    private TextArea textArea;
    @FXML
    private TableView<consultation> TableView;
    
 
    private FileInputStream fis;
    
    @FXML
    private TextField rech;
    private ImageView imageview;
    private Image image;
            DataBase db = new DataBase();
    @FXML
    private TextField urlpath;
    @FXML
    private Button idretour;
    
   
    @FXML
    private TableColumn<consultation, String> titreColumn;
    @FXML
    private TableColumn<consultation, String> descriptionColumn;
    @FXML
    private TableColumn<consultation, String> emplacementColumn;
    @FXML
    private TableColumn<consultation, String> imageColumn;
    @FXML
    private TextField prixField;
    @FXML
    private TableColumn<consultation, Integer> idcol;
    @FXML
    private TableColumn<consultation, Integer> idtherapColumn;
    @FXML
    private TableColumn<consultation, Double> prixColumn;
    @FXML
    private Label demres;
    @FXML
    private ImageView ntf;
    @FXML
    private Button insertButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Label stat;
    @FXML
    private Label cons;

    @FXML
    private void insertButton() {
        if(TitreField.getText().isEmpty() || descField.getText().isEmpty() || empField.getText().isEmpty()
                ||idField.getText().isEmpty() || urlpath.getText().isEmpty() )
        {
                 Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("data inalide");
        alert.setHeaderText("Erreur de saisie");
        alert.setContentText("Verifier les champs vides"); 
     
        alert.showAndWait();
        }
        else{
            double k=Double.parseDouble(prixField.getText());
            consultation c;
            c = new consultation(0,Integer.parseInt(idField.getText()),TitreField.getText(),descField.getText(),empField.getText(),k,urlpath.getText());
        serviceconsultation svc= new serviceconsultation();
        svc.ajouter1(c);
                        vider();

        }
        showconsultation();
        /*
    	String query = "insert into consultation values("+idField.getText()+",'"+TitreField.getText()+"','"+descField.getText()+"','"+empField.getText()+"')";
    	executeQuery(query);
    	showconsultation();*/

    }
    
    @FXML
    private void updateButton() {
        consultation co=TableView.getSelectionModel().getSelectedItem();
          if(TitreField.getText().isEmpty() || descField.getText().isEmpty() || empField.getText().isEmpty()
                ||idField.getText().isEmpty() || urlpath.getText().isEmpty() )
        {
                 Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("data inalide");
        alert.setHeaderText("Erreur de saisie");
        alert.setContentText("Verifier les champs vides"); 
     
        alert.showAndWait();
        }
          else{
  consultation c;
            c = new consultation(co.getId(),Integer.parseInt(idField.getText()),TitreField.getText(),descField.getText(),empField.getText(),Double.parseDouble(prixField.getText()),urlpath.getText()); 
            serviceconsultation svc= new serviceconsultation();
svc.update(c);
                vider();

          }
          
   /* String query = "UPDATE consultation SET titre='"+TitreField.getText()+"',description='"+descField.getText()+"',emplacement="+empField.getText()
            +" WHERE id="+idField.getText()+"";
    executeQuery(query);*/
	showconsultation();
    }
    
    @FXML
    private void deleteButton() {
     
   // TableView.getItems().removeAll(TableView.getSelectionModel().getSelectedItem());
    	/*String query = "DELETE FROM consultation WHERE id="+idField.getText()+"";
    	executeQuery(query);*/
     /*             consultation c = new consultation(Integer.parseInt(idField.getText()),TitreField.getText(),descField.getText(),empField.getText(),urlpath.getText());
        serviceconsultation svc= new serviceconsultation();
svc.delete(c);*/
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Voulez vous supprimer cette consultation?");
        alert.setContentText("Confirmer la suppression"); 
        Optional<ButtonType> btn = alert.showAndWait();
     if (btn.get()==ButtonType.OK){
consultation c = TableView.getSelectionModel().getSelectedItem();         
        serviceconsultation svc= new serviceconsultation();
svc.delete(c);
     }
     else
     {
         alert.close();
     }

              	showconsultation();

 
 
 
    }
    
    
   
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
    
    public void initialize(URL location, ResourceBundle resources) {
        	showconsultation();
         notiff();
         if (fcount>0)
             System.out.println("il ya "+fcount);
         

    }
 /*     private Connection con;
    private Statement ste;

    public ServicePersonne() {
        con = DataBase.getInstance().getConnection();

    }
  
*/    
    
  /*        public Connection getConnection() {
    	Connection conn;
    	try {
    		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet","root","");
    		return conn;
    	}
    	catch (Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }
   */ 
    public void vider()
    {
             idField.clear();
        TitreField.clear();
        descField.clear();
        empField.clear();
        urlpath.clear();
                prixField.clear();

    }
    public ObservableList<consultation> getconsultationList(){
    	ObservableList<consultation> consultationList = FXCollections.observableArrayList();
            
        
    	Connection connection = db.getConnection();
    	String query = "SELECT * FROM consultation ";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			consultation consultations;
			while(rs.next()) {
				consultations = new consultation(rs.getInt("id"),rs.getInt("idtherapeute"),rs.getString("titre"),rs.getString("description"),rs.getString("emplacement"),rs.getFloat("prix"),rs.getString("image"));
				consultationList.add(consultations);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return consultationList;
    }
    
    // I had to change ArrayList to ObservableList I didn't find another option to do this but this works :)
    public void showconsultation() {
    	ObservableList<consultation> list = getconsultationList();
    	
    	idcol.setCellValueFactory(new PropertyValueFactory<consultation,Integer>("id"));
            	idtherapColumn.setCellValueFactory(new PropertyValueFactory<consultation,Integer>("idtherapeute"));

    	titreColumn.setCellValueFactory(new PropertyValueFactory<consultation,String>("titre"));
    	descriptionColumn.setCellValueFactory(new PropertyValueFactory<consultation,String>("description"));
    	emplacementColumn.setCellValueFactory(new PropertyValueFactory<consultation,String>("emplacement"));
    	imageColumn.setCellValueFactory(new PropertyValueFactory<consultation,String>("image"));
    	    	prixColumn.setCellValueFactory(new PropertyValueFactory<consultation,Double>("prix"));

    	TableView.setItems(list);
    }


 

    @FXML
    private void rechtest(KeyEvent event) {
                   ObservableList<consultation> list = getconsultationList();
FilteredList<consultation> filtereddata= new FilteredList<>(list,b->true);
rech.textProperty().addListener((observable,oldValue,newValue)->{
        filtereddata.setPredicate(consultation -> {
            
            if (newValue == null || newValue.isEmpty()){
                return true;
        }
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (consultation.getTitre().toLowerCase().indexOf(lowerCaseFilter)!= -1)
            return true;
                else
            return false;
                });
                
    });
SortedList<consultation> sortedData = new SortedList<>(filtereddata);
sortedData.comparatorProperty().bind(TableView.comparatorProperty());
TableView.setItems(sortedData);
    }


    @FXML
    private void retourtest(ActionEvent event) {
        
 try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projet/Clientinterface.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            idretour.getScene().setRoot(root);
    } catch(Exception e) {
       e.printStackTrace();
      }
   
 
    }

    @FXML
    private void afficherform(MouseEvent event) {
        consultation c = TableView.getSelectionModel().getSelectedItem();         

        idField.setText(String.valueOf(c.getIdtherapeute()));
        TitreField.setText(String.valueOf(c.getTitre()));
        descField.setText(String.valueOf(c.getDescription()));
        empField.setText(String.valueOf(c.getEmplacement()));
        urlpath.setText(String.valueOf(c.getImage()));
        prixField.setText(String.valueOf(c.getPrix()));

        
    }

    @FXML
    private void imp(MouseEvent event) {
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
        urlpath.setText("");
            }else
            urlpath.setText(selected.getAbsolutePath());            
        }
    }

    @FXML
    private void demanderes(MouseEvent event) {
         try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projet/affichagereservationtherapeute.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            demres.getScene().setRoot(root);
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
    private void notif(MouseEvent event) {
      notiff();
        
    }

    @FXML
    private void rechtest2(KeyEvent event) {
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

    @FXML
    private void consclick(MouseEvent event) {
        
    }

    
}
