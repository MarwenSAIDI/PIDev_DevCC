/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package therapeuteinterfaces;

import Clientinterfaces.AfficherEventClientController;
import entities.consultation;
import services.serviceconsultation;
import services.servicereservation;

import utils.DataBase;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

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

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
public class CrudconsultationController implements Initializable {
public static Stage backstage;
    
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
    private JFXButton demres;
    @FXML
    private ImageView ntf;
    @FXML
    private Button insertButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private JFXButton stat;
    @FXML
    private ImageView photo;
    @FXML
    private Button vider;
    @FXML
    private JFXButton StatEvent;
    @FXML
    private JFXButton PageReservationEventLabel;
    @FXML
    private JFXButton btncons;
    @FXML
    private JFXButton btnaccueil;
    @FXML
    private Label labelemail;
    @FXML
    private Hyperlink deconnecter;
            private          servicereservation svr= new servicereservation();

    private int cinclient=svr.rendrecin(UserSession.instance.getUserName());
    @FXML
    private JFXButton btnEvent;
    @FXML
    private JFXButton recommend;

    @FXML
    private void insertButton() {
        int ok=0;
         try {
        Double.parseDouble(prixField.getText());
        System.out.println("It is numerical string");
        ok=0;
        }catch(NumberFormatException e) {
                        ok=1;
            System.out.println("It is not numerical string");

      }
                              System.out.println(ok);

  
        
        if(TitreField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Titre invalide");
        alert.setHeaderText("Erreur de saisie");
        alert.setContentText("Verifier le titre"); 
     
        alert.showAndWait();
                
        }
         else if (urlpath.getText().isEmpty())
                     {
                               Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Image invalide");
        alert.setHeaderText("Erreur de saisie");
        alert.setContentText("Veuillez importer une image"); 
     
        alert.showAndWait();
                }
       
            else 
                if (empField.getText().isEmpty()){
                          Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Emplacement invalide");
        alert.setHeaderText("Erreur de saisie");
        alert.setContentText("Verifier l'emplacement"); 
     
        alert.showAndWait();
                
                    
                }
        
                else if (ok==1)
                {
                                  Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Prix invalide");
        alert.setHeaderText("Erreur de saisie");
        alert.setContentText("Le prix ne peut pas comptenir des caractères "); 
     
        alert.showAndWait();   
                }
                    
                else if (prixField.getText().isEmpty() || Double.parseDouble(prixField.getText())<0)

                    
                {

                
                                      Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Prix invalide");
        alert.setHeaderText("Erreur de saisie");
        alert.setContentText("Verifier le prix"); 
     
        alert.showAndWait();
        
                }
              
                
                 else   if (descField.getText().isEmpty())
            {
                  Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Description invalide");
        alert.setHeaderText("Erreur de saisie");
        alert.setContentText("Verifier la Description"); 
     
        alert.showAndWait();
                
            }
               
       
        else{
                     System.out.println("aaa");
            double k=Double.parseDouble(prixField.getText());
            consultation c;
            c = new consultation(0,cinclient,TitreField.getText(),descField.getText(),empField.getText(),k,urlpath.getText());
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
        int ok=0;
         try {
        Double.parseDouble(prixField.getText());
        System.out.println("It is numerical string");
        ok=0;
        }catch(NumberFormatException e) {
                        ok=1;
            System.out.println("It is not numerical string");

      }
                              System.out.println(ok);

 
        if(TitreField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Titre invalide");
        alert.setHeaderText("Erreur de saisie");
        alert.setContentText("Verifier le titre"); 
     
        alert.showAndWait();
                
        }
         else if (urlpath.getText().isEmpty())
                     {
                               Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Image invalide");
        alert.setHeaderText("Erreur de saisie");
        alert.setContentText("Veuillez importer une image"); 
     
        alert.showAndWait();
                }
       
            else 
                if (empField.getText().isEmpty()){
                          Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Emplacement invalide");
        alert.setHeaderText("Erreur de saisie");
        alert.setContentText("Verifier l'emplacement"); 
     
        alert.showAndWait();
                
                    
                }
        
                else if (ok==1)
                {
                                  Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Prix invalide");
        alert.setHeaderText("Erreur de saisie");
        alert.setContentText("Le prix ne peut pas comptenir des caractères "); 
     
        alert.showAndWait();   
                }
                    
                else if (prixField.getText().isEmpty() || Double.parseDouble(prixField.getText())<0)

                    
                {

                
                                      Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Prix invalide");
        alert.setHeaderText("Erreur de saisie");
        alert.setContentText("Verifier le prix"); 
     
        alert.showAndWait();
        
                }
              
                
                 else   if (descField.getText().isEmpty())
            {
                  Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Description invalide");
        alert.setHeaderText("Erreur de saisie");
        alert.setContentText("Verifier la Description"); 
     
        alert.showAndWait();
                
            
        }
          else{
  consultation c;
  
            c = new consultation(co.getId(),cinclient,TitreField.getText(),descField.getText(),empField.getText(),Double.parseDouble(prixField.getText()),urlpath.getText()); 
     
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
                           labelemail.setText( UserSession.instance.getUserName());

        	showconsultation();
         notiff();
           

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
        TitreField.clear();
        descField.clear();
        empField.clear();
        urlpath.clear();
                prixField.clear();
                photo.setImage(null);
    }
    public ObservableList<consultation> getconsultationList(){
    	ObservableList<consultation> consultationList = FXCollections.observableArrayList();
            

    	Connection connection = db.getConnection();
    	String query = "SELECT * FROM consultation where idtherapeute='"+cinclient+"'";
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
                    if(consultation.getDescription().toLowerCase().indexOf(lowerCaseFilter)!= -1)
                                    return true;
else
                    if(consultation.getEmplacement().toLowerCase().indexOf(lowerCaseFilter)!= -1)
                                    return true;
                    else
                    if(String.valueOf(consultation.getPrix()).indexOf(lowerCaseFilter)!= -1)
                                    return true;
                        else
                        
            return false;
                });
                
    });
SortedList<consultation> sortedData = new SortedList<>(filtereddata);
sortedData.comparatorProperty().bind(TableView.comparatorProperty());
TableView.setItems(sortedData);
    }


    private void retourtest(ActionEvent event) {
        
 try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/therapeuteinterfaces/Clientinterface.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            idretour.getScene().setRoot(root);
    } catch(Exception e) {
       e.printStackTrace();
      }
   
 
    }

    @FXML
    private void afficherform(MouseEvent event) {
        consultation c = TableView.getSelectionModel().getSelectedItem();         

        TitreField.setText(String.valueOf(c.getTitre()));
        descField.setText(String.valueOf(c.getDescription()));
        empField.setText(String.valueOf(c.getEmplacement()));
        urlpath.setText(String.valueOf(c.getImage()));
        prixField.setText(String.valueOf(c.getPrix()));
 Image i;
        try {
            i= new Image(new FileInputStream(c.getImage()));
                               photo.setImage(i);

        } catch (FileNotFoundException ex) {
            
        }
        
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
             Image i;
        try {
            i= new Image(new FileInputStream(selected.getAbsolutePath()));
                               photo.setImage(i);

        } catch (FileNotFoundException ex) {
            
        }
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
    private void notif(MouseEvent event) {
      notiff();
        
    }

    @FXML
    private void rechtest2(KeyEvent event) {
    }




    @FXML
    private void viderButton(ActionEvent event) {
                                vider();

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
