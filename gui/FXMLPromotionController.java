/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Alert;
import Entities.Promotion;
import Service.ServicePromotion;
import static java.lang.String.valueOf;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.List;
import java.util.ResourceBundle;
import java.time.ZoneId;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author LENEVO
 */
public class FXMLPromotionController implements Initializable {

    @FXML
    private TextField tfIDP;
    @FXML
    private TextField tfValP;
    @FXML
    private DatePicker tfDateD;
    @FXML
    private DatePicker tfDateF;
    @FXML
    private TableColumn<Promotion, Integer> colIDP;
    @FXML
    private TableColumn<Promotion, Integer> colValP;
    @FXML
    private TableColumn<Promotion, Date> colDateD;
    @FXML
    private TableColumn<Promotion, Date> colDateF;
    @FXML
    private TextField tfRecherche;
    @FXML
    private TableView<Promotion> tvPromotion;
    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ShowPromotion();
    }    

    
    public void ShowPromotion(){
      
       ServicePromotion ser = new ServicePromotion();
      ObservableList<Promotion> list  = ser.getterList();
        colIDP.setCellValueFactory(new PropertyValueFactory<Promotion,Integer>("idP"));
        colValP.setCellValueFactory(new PropertyValueFactory<Promotion,Integer>("valP"));
        colDateD.setCellValueFactory(new PropertyValueFactory<Promotion,Date>("dateD"));
        colDateF.setCellValueFactory(new PropertyValueFactory<Promotion,Date>("dateF"));
        
        tvPromotion.setItems(list);
        FilteredList<Promotion> filteredData = new FilteredList<>(list, b -> true);
//       
//        tfRecherche.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
//            filteredData.setPredicate((Promotion cattype ) -> {
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//                String lowerCaseFilter = newValue.toLowerCase();
//String s=String.valueOf(i);
//                if (cattype.getIdP().contains(newValue)).toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter matches username
//                }
//               
//                else {
//                    return false; 
//                }
//            });
//        });
//        SortedList<Promotion> sortedData = new SortedList<>(filteredData);
//        sortedData.comparatorProperty().bind(tvPromotion.comparatorProperty());
//        tvPromotion.setItems(sortedData);
//      
//          
      
 
 
 
      
        
        
   }
    
     @FXML
    private void AjouterPromotion(ActionEvent event) throws ParseException {          
        int i = Integer.parseInt(tfValP.getText());
        int ii = Integer.parseInt(tfIDP.getText());
         if(((tfIDP.getText().isEmpty() || tfValP.getText().isEmpty() ) || tfDateD.getValue()==null) || tfDateF.getValue()==null  )
         {
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("data invalide");
             alert.setHeaderText("Erreur de saisie");
             alert.setContentText("Verifier les champs ");
             
             alert.showAndWait();
         }
         else if ( (ii<0)  ) {
             
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("ID invalide");
             alert.setHeaderText("Erreur de saisie");
             alert.setContentText("l'id doit etre superieur à 0 ");
             
             alert.showAndWait();
             
         }
          else if ( (i<0)  ) {
             
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("valeur de promotion invalide");
             alert.setHeaderText("Erreur de saisie");
             alert.setContentText("la valeur de promotion doit etre superieur à 0 ");
             
             alert.showAndWait();
             
         }
         
          else{
                   int a= Integer.parseInt(tfIDP.getText());
                   int b= Integer.parseInt(tfValP.getText());
                  //int c = valueOf(tfDateD.getValue()); 
                  ZoneId defaultZoneId = ZoneId.systemDefault();
                  LocalDate dd= tfDateD.getValue();
                  LocalDate df= tfDateF.getValue();
                    LocalDate localDate = tfDateD.getValue(); 
        Date date = Date.from(dd.atStartOfDay(defaultZoneId).toInstant());
        Date date2 = Date.from(df.atStartOfDay(defaultZoneId).toInstant());
       
     Promotion e=new Promotion(a,b,date,date2);
ServicePromotion s=new ServicePromotion();


Date date_du_jour= new Date();
int test_date1=0;
int test_date=0;
               String d_jour = new SimpleDateFormat("yyyy-MM-dd").format(date_du_jour);
               String DateEvent= String.valueOf(tfDateD.getValue());
               Date Date=new SimpleDateFormat("yyyy-MM-dd").parse(DateEvent);
               String dated=new SimpleDateFormat("yyyy-MM-dd").format(Date);
               if(d_jour.compareTo(dated)  > 0)
                   
                {
                    test_date1=1;
                }
               
               String DateEvent1= String.valueOf(tfDateF.getValue());
               Date Date1=new SimpleDateFormat("yyyy-MM-dd").parse(DateEvent1);
               String datef=new SimpleDateFormat("yyyy-MM-dd").format(Date1);
               if(dated.compareTo(datef)  > 0)
                   
                {
                    test_date=1;
                }
if(test_date1==1)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Alerte");
               alert.setHeaderText("Vous ne pouvez pas choisir cette Date");
               alert.setContentText(" Cette date est déja passer.\n Veuillez choisir une autre date");
               
               alert.showAndWait(); 
            }
else if(test_date==1)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Alerte");
               alert.setHeaderText("Vous ne pouvez pas choisir cette Date");
               alert.setContentText(" la date_fin doit etre plus recente que la date_debut .\n Veuillez choisir une autre date_fin");
               
               alert.showAndWait(); 
            }


else { 
               
               
               
s.AjouterPromotion(e);
}


ShowPromotion();
    
         }}
    
    @FXML
    private void ModifierPromotion(ActionEvent event) throws ParseException {
        int i = Integer.parseInt(tfValP.getText());
        int ii = Integer.parseInt(tfIDP.getText());
        if(((tfIDP.getText().isEmpty() || tfValP.getText().isEmpty()) || tfDateD.getValue()==null) || tfDateF.getValue()==null  )
         {
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("data inalide");
             alert.setHeaderText("Erreur de saisie");
             alert.setContentText("Verifier les champs vides");
             
             alert.showAndWait();
         }
        
        else if ( (ii<0)  ) {
             
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("ID invalide");
             alert.setHeaderText("Erreur de saisie");
             alert.setContentText("l'id doit etre superieur à 0 ");
             
             alert.showAndWait();
             
         }
          else if ( (i<0)  ) {
             
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("valeur de promotion invalide");
             alert.setHeaderText("Erreur de saisie");
             alert.setContentText("la valeur de promotion doit etre superieur à 0 ");
             
             alert.showAndWait();
             
         }
        
          else{
             
             
              int a= Integer.parseInt(tfIDP.getText());
                   int b= Integer.parseInt(tfValP.getText());
                  //int c = valueOf(tfDateD.getValue()); 
                  ZoneId defaultZoneId = ZoneId.systemDefault();
                  LocalDate dd= tfDateD.getValue();
                  LocalDate df= tfDateF.getValue();
                    LocalDate localDate = tfDateD.getValue(); 
        Date date = Date.from(dd.atStartOfDay(defaultZoneId).toInstant());
        Date date2 = Date.from(df.atStartOfDay(defaultZoneId).toInstant());
       
     Promotion e=new Promotion(a,b,date,date2);
ServicePromotion s=new ServicePromotion();

Date date_du_jour= new Date();
int test_date1=0;
int test_date=0;
               String d_jour = new SimpleDateFormat("yyyy-MM-dd").format(date_du_jour);
               String DateEvent= String.valueOf(tfDateD.getValue());
               Date Date=new SimpleDateFormat("yyyy-MM-dd").parse(DateEvent);
               String dated=new SimpleDateFormat("yyyy-MM-dd").format(Date);
               if(d_jour.compareTo(dated)  > 0)
                   
                {
                    test_date1=1;
                }
               
               String DateEvent1= String.valueOf(tfDateF.getValue());
               Date Date1=new SimpleDateFormat("yyyy-MM-dd").parse(DateEvent1);
               String datef=new SimpleDateFormat("yyyy-MM-dd").format(Date1);
               if(dated.compareTo(datef)  > 0)
                   
                {
                    test_date=1;
                }
if(test_date1==1)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Alerte");
               alert.setHeaderText("Vous ne pouvez pas choisir cette Date");
               alert.setContentText(" Cette est déja passer.\n Veuillez choisir une autre date");
               
               alert.showAndWait(); 
            }
else if(test_date==1)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Alerte");
               alert.setHeaderText("Vous ne pouvez pas choisir cette Date");
               alert.setContentText(" la date fin doit etre apres la date debut .\n Veuillez choisir une autre date");
               
               alert.showAndWait(); 
            }


else { 


             s.ModifierPromotion(e);
}
             
             ShowPromotion();
             
         } }
    
    
    @FXML
    private void SuprimerPromotion(ActionEvent event) {
        
          int a= Integer.parseInt(tfIDP.getText());
          
ServicePromotion s=new ServicePromotion();
s.SuprimerPromotion(a);
        ServicePromotion cs = new ServicePromotion();

         List<Promotion> lc = cs.ListPromotion();
        
          ObservableList<Promotion> data =
                  
        FXCollections.observableArrayList(lc );
          
      
 
 
 
        tvPromotion.setItems(data);
    }

   

    @FXML
    private void RetourOnClick(ActionEvent event) {
        
        
              try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FXMLMain.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);        
            ((Stage)((Node)event.getTarget()).getScene().getWindow()).setScene(scene);
        }catch(Exception e){
            System.out.println(e);
        }
    
    }
}
    
    
    
   
    

