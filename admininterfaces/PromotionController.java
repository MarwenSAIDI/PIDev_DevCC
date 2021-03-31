/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admininterfaces;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import entities.Promotion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import services.ServicePromotion;

/**
 * FXML Controller class
 *
 * @author foura
 */
public class PromotionController implements Initializable {

    @FXML
    private Label labelemail;
    @FXML
    private Hyperlink deconnecter;
    @FXML
    private JFXButton textcmient;
    @FXML
    private JFXButton textth;
    @FXML
    private JFXButton PageReservationEventLabel;
    @FXML
    private ImageView export;
    @FXML
    private TextField t_search;
    @FXML
    private TextField tfValP;
    @FXML
    private DatePicker tfDateD;
    @FXML
    private DatePicker tfDateF;
    @FXML
    private TableView<Promotion> tab_promo;
    
    private int ID_Promotion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServicePromotion promoc = new ServicePromotion();
        TableColumn<Promotion,String> idP = new TableColumn("ID Promotion");

            idP.setCellValueFactory(
                    new PropertyValueFactory<>("idP"));


            TableColumn<Promotion,String> valP = new TableColumn("Valeur");

            valP.setCellValueFactory(
                    new PropertyValueFactory<>("valP"));

            TableColumn<Promotion,String> dateD = new TableColumn("Date de début");

            dateD.setCellValueFactory(
                    new PropertyValueFactory<>("dateD"));



            TableColumn<Promotion,String> dateF = new TableColumn("Date de fin");

            dateF.setCellValueFactory(
                    new PropertyValueFactory<>("dateF"));

            

           
            tab_promo.getColumns().add(idP);
            tab_promo.getColumns().add(valP);
            tab_promo.getColumns().add(dateD);
            tab_promo.getColumns().add(dateF);
            


            tab_promo.getItems().addAll(promoc.ListPromotion());
        

        
    }    

    @FXML
    private void deconnexion(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/authentification/authentification.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            deconnecter.getScene().setRoot(root);

    }

    @FXML
    private void gotoGestionclients(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Gestionclient.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
        
       
    }

    @FXML
    private void gotoGestiontherapeutes(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
        
    }

    @FXML
    private void PageReservationEvent(MouseEvent event) {
    }

    @FXML
    private void go_to_Commandes(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Commande.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
    }

    @FXML
    private void go_to_Produit(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Produit.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
    }

    @FXML
    private void go_to_Paiements(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Payment.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
    }

    @FXML
    private void go_to_Panier(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Panier.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
    }

    @FXML
    private void go_to_Promotion(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Promotion.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
    }

    @FXML
    private void toPDF(MouseEvent event) throws BadElementException, DocumentException {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        final File selectedDirectory = directoryChooser.showDialog(null);
        if (selectedDirectory != null) {
            
            String dest = selectedDirectory.getAbsolutePath()+"\\Table des promotions.pdf";
            
            
            try {
                Document doc = new Document();
                PdfWriter.getInstance(doc, new FileOutputStream(dest));
                
                doc.open();
                
                String img = "C:\\Users\\foura\\OneDrive\\Bureau\\work\\Zenlife\\src\\images\\logo.JPG";
                Image image = Image.getInstance(img);
                doc.add(image);
                //Test PDF
                PdfPTable table = new PdfPTable(4);

                table.setWidthPercentage(100);
                table.setSpacingBefore(0f);
                table.setSpacingAfter(0f);

                // first row
                PdfPCell cell = new PdfPCell(new Phrase("Tableau des promotions"));
                cell.setColspan(4);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPadding(5.0f);
                cell.setBackgroundColor(new BaseColor(140, 221, 8));
                table.addCell(cell);

                table.addCell("ID promotion");
                table.addCell("Valeur de la promotion en %");
                table.addCell("Date de début");
                table.addCell("Date de fin");
                

               
                
                ServicePromotion promoc = new ServicePromotion();
                List<Promotion> liste_promo = promoc.ListPromotion();
                for (int i = 0; i < liste_promo.size(); i++){
                    
                    table.addCell(String.valueOf(liste_promo.get(i).getIdP()));
                    table.addCell(String.valueOf(liste_promo.get(i).getValP()));
                    table.addCell(String.valueOf(liste_promo.get(i).getDateD()));
                    table.addCell(String.valueOf(liste_promo.get(i).getDateF()));
                }
                doc.add(table); 
                
               
                doc.close();
                System.out.println("done");
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            
            
            
        }

    }

    private boolean searchFindsOrder(Promotion promo, String searchText){
        return (String.valueOf(promo.getIdP()).toLowerCase().contains(searchText.toLowerCase()));
    }

    private ObservableList<Promotion> filterList(List<Promotion> list, String searchText){
        List<Promotion> filteredList = new ArrayList<>();
        for (Promotion promo : list){
            if(searchFindsOrder(promo, searchText)) filteredList.add(promo);
        }
        return FXCollections.observableList(filteredList);
    }

    @FXML
    private void Search(KeyEvent event) {
        ServicePromotion promoc = new ServicePromotion();
        if(!t_search.getText().isEmpty()){
            t_search.textProperty().addListener((observable, oldValue, newValue) ->
                tab_promo.setItems(filterList(promoc.ListPromotion(), newValue))
            );
        }
    }


    @FXML
    private void Select_contenu(MouseEvent event) {
        Promotion promo = tab_promo.getSelectionModel().selectedItemProperty().get();
        ID_Promotion = promo.getIdP();
        
        tfValP.setText(String.valueOf(promo.getValP()));
        tfDateD.setValue(LocalDate.parse(String.valueOf(promo.getDateD())));
        tfDateF.setValue(LocalDate.parse(String.valueOf(promo.getDateF())));
        
    }

    @FXML
    private void ModifierPromotion(ActionEvent event) throws ParseException {
        if(((tfValP.getText().isEmpty()) || tfDateD.getValue()==null) || tfDateF.getValue()==null  )
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("data inalide");
            alert.setHeaderText("Erreur de saisie");
            alert.setContentText("Verifier les champs vides");
             
            alert.showAndWait();
        }
        
        
        else if ( (Integer.parseInt(tfValP.getText())<0)  ) {
             
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("valeur de promotion invalide");
            alert.setHeaderText("Erreur de saisie");
            alert.setContentText("la valeur de promotion doit etre superieur à 0 ");
             
            alert.showAndWait();
             
        }
        else{
             
             
              
            int b= Integer.parseInt(tfValP.getText());

            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate dd= tfDateD.getValue();
            LocalDate df= tfDateF.getValue();
            LocalDate localDate = tfDateD.getValue(); 
            Date date1 = Date.from(dd.atStartOfDay(defaultZoneId).toInstant());
            Date date2 = Date.from(df.atStartOfDay(defaultZoneId).toInstant());
            //Date_1 et Date_2 Format
            System.out.println("D_1 : "+date1+"\nD_2 : "+date2);
            //
            Promotion e=new Promotion();
            
            e.setIdP(ID_Promotion);
            e.setValP(b);
            e.setDateD(date1);
            e.setDateF(date2);
            
            ServicePromotion s=new ServicePromotion();

            Date date_du_jour= new Date();
            int test_date1=0;
            int test_date=0;
            String d_jour = new SimpleDateFormat("yyyy-MM-dd").format(date_du_jour);
            //Date du jour
            System.out.println("Date du jour : "+date_du_jour);
            //
            String DateEvent= String.valueOf(tfDateD.getValue());
            //Date Event
            System.out.println("Date Event : "+DateEvent);
            //
            Date Date=new SimpleDateFormat("yyyy-MM-dd").parse(DateEvent);
            String dated=new SimpleDateFormat("yyyy-MM-dd").format(Date);
            //Date_D
            System.out.println("Date_D : "+dated);
            //
            
            //Date du jour COMPAR_TO Date_D
            System.out.println("Date du jour COMPAR_TO Date_D : "+d_jour.compareTo(dated));
            //
            if(d_jour.compareTo(dated)  > 0){
                
                test_date1=1;
            }
               
            String DateEvent1= String.valueOf(tfDateF.getValue());
            //Date Event 1
            System.out.println("Date Event_1 : "+DateEvent1);
            //
            Date Date1=new SimpleDateFormat("yyyy-MM-dd").parse(DateEvent1);
            String datef=new SimpleDateFormat("yyyy-MM-dd").format(Date1);
            //Date_F
            System.out.println("Date_F : "+datef);
            //
            //Date_D COMPAR_TO Date_F
            System.out.println("Date_D COMPAR_TO Date_F : "+dated.compareTo(datef));
            //
            if(dated.compareTo(datef)  > 0){
                
                test_date=1;
            }
            
            if(test_date1==1){
               Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Alerte");
               alert.setHeaderText("Vous ne pouvez pas choisir cette Date");
               alert.setContentText(" Cette est déja passer.\n Veuillez choisir une autre date");
               
               alert.showAndWait(); 
               
            }
            else if(test_date==1){
               Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Alerte");
               alert.setHeaderText("Vous ne pouvez pas choisir cette Date");
               alert.setContentText(" la date fin doit etre apres la date debut .\n Veuillez choisir une autre date");
               
               alert.showAndWait(); 
            }
            else { 


                s.ModifierPromotion(e);
                
            }
            tab_promo.getItems().clear();
            tab_promo.getItems().addAll(s.ListPromotion());
        }
                
        
        tfValP.clear();
        tfDateD.getEditor().clear();
        tfDateF.getEditor().clear();
        

    }

    @FXML
    private void SupprimerPromotion(ActionEvent event) {
        ServicePromotion promoc = new ServicePromotion();
        promoc.SuprimerPromotion(ID_Promotion);
        
        tfValP.clear();
        tfDateD.getEditor().clear();
        tfDateF.getEditor().clear();
        
        tab_promo.getItems().clear();
        tab_promo.getItems().addAll(promoc.ListPromotion());
        
    }

    @FXML
    private void AjouterPromotion(ActionEvent event) throws ParseException {
        if(((tfValP.getText().isEmpty()) || tfDateD.getValue()==null) || tfDateF.getValue()==null  )
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("data inalide");
            alert.setHeaderText("Erreur de saisie");
            alert.setContentText("Verifier les champs vides");
             
            alert.showAndWait();
        }
        
        
        else if ( (Integer.parseInt(tfValP.getText())<0)  ) {
             
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("valeur de promotion invalide");
            alert.setHeaderText("Erreur de saisie");
            alert.setContentText("la valeur de promotion doit etre superieur à 0 ");
             
            alert.showAndWait();
             
        }
        else{
             
             
              
            int b= Integer.parseInt(tfValP.getText());

            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate dd= tfDateD.getValue();
            LocalDate df= tfDateF.getValue();
            LocalDate localDate = tfDateD.getValue(); 
            Date date1 = Date.from(dd.atStartOfDay(defaultZoneId).toInstant());
            Date date2 = Date.from(df.atStartOfDay(defaultZoneId).toInstant());
       
            Promotion e=new Promotion();
            
            e.setValP(b);
            e.setDateD(date1);
            e.setDateF(date2);
            
            ServicePromotion s=new ServicePromotion();

            Date date_du_jour= new Date();
            int test_date1=0;
            int test_date=0;
            String d_jour = new SimpleDateFormat("yyyy-MM-dd").format(date_du_jour);
            
            String DateEvent= String.valueOf(tfDateD.getValue());
            Date Date=new SimpleDateFormat("yyyy-MM-dd").parse(DateEvent);
            String dated=new SimpleDateFormat("yyyy-MM-dd").format(Date);
            if(d_jour.compareTo(dated)  > 0){
                
                test_date1=1;
            }
               
            String DateEvent1= String.valueOf(tfDateF.getValue());
            Date Date1=new SimpleDateFormat("yyyy-MM-dd").parse(DateEvent1);
            String datef=new SimpleDateFormat("yyyy-MM-dd").format(Date1);
            if(dated.compareTo(datef)  > 0){
                
                test_date=1;
            }
            
            if(test_date1==1){
               Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Alerte");
               alert.setHeaderText("Vous ne pouvez pas choisir cette Date");
               alert.setContentText(" Cette est déja passer.\n Veuillez choisir une autre date");
               
               alert.showAndWait(); 
               
            }
            else if(test_date==1){
               Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Alerte");
               alert.setHeaderText("Vous ne pouvez pas choisir cette Date");
               alert.setContentText(" la date fin doit etre apres la date debut .\n Veuillez choisir une autre date");
               
               alert.showAndWait(); 
            }
            else { 


                s.AjouterPromotion(e);
                
            }
            tab_promo.getItems().clear();
            tab_promo.getItems().addAll(s.ListPromotion());
        }
                
        
        tfValP.clear();
        tfDateD.getEditor().clear();
        tfDateF.getEditor().clear();

    }
    @FXML
    private void gotoaccueil(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accueiladmin.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
    }

    
}
