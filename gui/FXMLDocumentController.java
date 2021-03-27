/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import Service.ServiceProduit;
import Entities.Produit;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import Entities.Produit;
import Service.ServiceProduit;
import Services.IServiceProduit;
import java.io.File;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import static sun.management.ConnectorAddressLink.export;

/**
 *
 * @author aymen romdhani
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField tfID;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfType;
    @FXML
    private TextField tfQuantite;
    @FXML
    private TextField tfPrix;
    @FXML
    private TableColumn<Produit, Integer> colID;
    @FXML
    private TableColumn<Produit, String> colNom;
    @FXML
    private TableColumn<Produit, String> colType;
    @FXML
    private TableColumn<Produit, Integer> colQuantite;
    @FXML
    private TableColumn<Produit, Float> colPrix;
    @FXML
    private TableView<Produit> tvProduit;
    @FXML
    private TextField tfRecherche;
    
    ObservableList<Produit> dataList;
    private Node Barchart;
    private Node tableview;
    @FXML
    private Button export;
    @FXML
    private TextField ImageEventField;
    @FXML
    private ImageView imageView;
    
 /*   private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    } 
*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        
//         ServiceProduit cs = new ServiceProduit();
//
//         List<Produit> lc = cs.ListProduit();
//        
//          ObservableList<Produit> data =
//                  
//        FXCollections.observableArrayList(lc);
//          
//        colID.setCellValueFactory(
//                new PropertyValueFactory<>("ID"));
//          
//        colNom.setCellValueFactory(
//                new PropertyValueFactory<>("nom"));
// 
//       
//        colType.setCellValueFactory(
//                new PropertyValueFactory<>("type"));
// 
//        
//        colQuantite.setCellValueFactory(
//                new PropertyValueFactory<>("quantite"));
//        
//        colPrix.setCellValueFactory(
//                new PropertyValueFactory<>("prix"));
//        
//        tvProduit.setItems(data); 

       ShowMatch(); 
       
       export.setOnAction(event ->{pdf();});
            Produit e = tvProduit.getSelectionModel().getSelectedItem();
       
       
    }    
    
    public void ShowMatch(){
      
       ServiceProduit ser = new ServiceProduit();
      ObservableList<Produit> list  = ser.getterList();
        colID.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("ID"));
        colNom.setCellValueFactory(new PropertyValueFactory<Produit,String>("Nom"));
        colType.setCellValueFactory(new PropertyValueFactory<Produit,String>("Type"));
        colQuantite.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("Quantite"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Produit,Float>("Prix"));
        tvProduit.setItems(list);
        FilteredList<Produit> filteredData = new FilteredList<>(list, b -> true);
       
        tfRecherche.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredData.setPredicate((Produit cattype ) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (cattype.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
                }
               
                else {
                    return false; 
                }
            });
        });
        SortedList<Produit> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvProduit.comparatorProperty());
        tvProduit.setItems(sortedData);
      
          
      
 
 
 
      
        
        
   }
    
//    private void search(ActionEvent event) {
//          Produit m = new Produit();
//         colID.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("type"));
//                  colNom.setCellValueFactory(new PropertyValueFactory<Produit,String>("date"));
//
//         colType.setCellValueFactory(new PropertyValueFactory<Produit,String>("date"));
//                  colType.setCellValueFactory(new PropertyValueFactory<Produit,String>("date"));
//
//         colQuantite.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("nbjoueurs"));
//         colPrix.setCellValueFactory(new PropertyValueFactory<Produit,Float>("date"));
// 
//        ObservableList<Produit> dataList;
//   ServiceProduit ser = new ServiceProduit();
//        dataList = ser.getterList();
//       
//        tvProduit.setItems(dataList);
//       
//        
//        
//    }
//    
//    
//    /**
//     *
//     * @return
//     */
//    public boolean validateField(){
//        
//        
//        
//         if(((tfQuantite.getText().isEmpty() || tfPrix.getText().isEmpty()) || tfID.getText().isEmpty()) || tfNom.getText().isEmpty() )
//         {
//             Alert alert = new Alert(Alert.AlertType.WARNING);
//             alert.setTitle("data invalide");
//             alert.setHeaderText("Erreur de saisie");
//             alert.setContentText("Verifier les champs vides");
//             
//             alert.showAndWait();
//             return false;
//             
//         }
//         return true;
//
//    }
//    
    
    
    
     
    
    
    
    
    
    @FXML
    private void AjouterProduit(ActionEvent event) {
        
         if(((tfQuantite.getText().isEmpty() || tfPrix.getText().isEmpty()) || tfID.getText().isEmpty()) || tfNom.getText().isEmpty()  )
         {
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("data inalide");
             alert.setHeaderText("Erreur de saisie");
             alert.setContentText("Verifier les champs vides");
             
             alert.showAndWait();
         }
         
         
          else{
       
                   int a= Integer.parseInt(tfID.getText());
                   int b= Integer.parseInt(tfQuantite.getText());
                   float c= Float.parseFloat(tfPrix.getText());
                   
     Produit e=new Produit(a,tfNom.getText(),tfType.getText(),b,c,ImageEventField.getText());
ServiceProduit s=new ServiceProduit();
s.AjouterProduit(e);
ShowMatch();

  String img= ImageEventField.getText();
        String ch="/image/";
        String imgF= ch+img;
Image im = new Image(getClass().getResourceAsStream(imgF));
        imageView.setImage(im);
//ServiceProduit cs = new ServiceProduit();
//
//         List<Produit> lc = cs.ListProduit();
//        
//          ObservableList<Produit> data =
//                  
//        FXCollections.observableArrayList(lc );
//          
//      
// 
// 
// 
//        tvProduit.setItems(data);

    }}
    
    @FXML
    private void ModifierProduit(ActionEvent event) {
        
         if(((tfQuantite.getText().isEmpty() || tfPrix.getText().isEmpty()) || tfID.getText().isEmpty()) || tfNom.getText().isEmpty()  )
         {
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("data inalide");
             alert.setHeaderText("Erreur de saisie");
             alert.setContentText("Verifier les champs vides");
             
             alert.showAndWait();
         }
          else{
             
             
             int a= Integer.parseInt(tfID.getText());
             int b= Integer.parseInt(tfQuantite.getText());
             float c= Float.parseFloat(tfPrix.getText());
             Produit e=new Produit(a,tfNom.getText(),tfType.getText(),b,c,ImageEventField.getText());
             ServiceProduit s=new ServiceProduit();
             s.ModifierProduit(e);
             ShowMatch();
             
         } }

    @FXML
    private void SuprimerProduit(ActionEvent event) {
        
          int a= Integer.parseInt(tfID.getText());
          
ServiceProduit s=new ServiceProduit();
s.SuprimerProduit(a);
        ServiceProduit cs = new ServiceProduit();

         List<Produit> lc = cs.ListProduit();
        
          ObservableList<Produit> data =
                  
        FXCollections.observableArrayList(lc );
          
      
 
 
 
        tvProduit.setItems(data);
    }
    
    void pdf() {
 System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(primaryStage); 
            
    Node root = this.tvProduit ;
        
           job.printPage(root);
           job.endJob();
            
       

  }}
    
    
    

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
    

}
    

