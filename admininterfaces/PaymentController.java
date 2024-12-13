/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admininterfaces;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import entities.Payment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import services.PaymentCRUD;
import services.TextFieldException;

/**
 * FXML Controller class
 *
 * @author foura
 */
public class PaymentController implements Initializable {

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
    private TableView<Payment> tab_pay;
    @FXML
    private TextField t_search;
    @FXML
    private ComboBox<String> cb_mp;
    @FXML
    private TextField t_pf;
    @FXML
    private Label t_Pan;
    
    private int ID;
    
    private String Filtre;
    @FXML
    private JFXComboBox<String> cb_Filtre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cb_mp.getItems().addAll("Espece", "Carte");
        cb_Filtre.getItems().addAll("ID Payment","ID Panier","Prix Totale","Mode de Payment");
        cb_mp.getSelectionModel().select("Espèce");
     
        try{
            PaymentCRUD payc = new PaymentCRUD();
            tab_pay.setEditable(true);

            TableColumn<Payment,String> id_p = new TableColumn("ID Payment");

            id_p.setCellValueFactory(
                    new PropertyValueFactory<>("ID_Payment"));
            
            TableColumn<Payment,String> id_pan = new TableColumn("ID Panier");

            id_pan.setCellValueFactory(
                    new PropertyValueFactory<>("ID_Panier"));

            TableColumn<Payment,String> p_f = new TableColumn("Prix Totale");

            p_f.setCellValueFactory(
                    new PropertyValueFactory<>("Prix_F"));

            TableColumn<Payment,String> m_p = new TableColumn("Mode de Payment");

            m_p.setCellValueFactory(
                    new PropertyValueFactory<>("Mode_Payment"));

            tab_pay.getColumns().add(id_p);
            tab_pay.getColumns().add(id_pan);
            tab_pay.getColumns().add(p_f);
            tab_pay.getColumns().add(m_p);

            tab_pay.getItems().addAll(payc.AfficherPayment());
        }
        catch(NullPointerException NPE){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText("No database was found");
            
            
            alert.showAndWait();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
    private void Select_contenu(MouseEvent event) {
        Payment paymentM_S =  tab_pay.getSelectionModel().selectedItemProperty().get();
        t_pf.setText(String.valueOf(paymentM_S.getPrix_F()));
        cb_mp.getSelectionModel().select(paymentM_S.getMode_Payment());
        t_Pan.setText(t_Pan.getText() + String.valueOf(paymentM_S.getID_Panier()));
        
        ID = paymentM_S.getID_Payment();
    }

   @FXML
    private void Search(KeyEvent event) {
        PaymentCRUD payc = new PaymentCRUD();
        if(!t_search.getText().isEmpty()){
            t_search.textProperty().addListener((observable, oldValue, newValue) ->
                {
                try {
                    tab_pay.setItems(filterList(payc.AfficherPayment(), newValue));
                } catch (SQLException ex) {
                    Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            );
        }
    }

    private boolean searchFindsOrder(Payment pay, String searchText){
        
        if(Filtre == "ID Panier"){
            return (String.valueOf(pay.getID_Panier()).toLowerCase().contains(searchText.toLowerCase()));
        }
        else if(Filtre == "ID Payment"){
            return (String.valueOf(pay.getID_Payment()).toLowerCase().contains(searchText.toLowerCase()));
        }
        else if(Filtre == "Mode de Payment"){
            return (pay.getMode_Payment().toLowerCase().contains(searchText.toLowerCase()));
        }
        
        return (String.valueOf(pay.getID_Payment()).toLowerCase().contains(searchText.toLowerCase()));
    }

    private ObservableList<Payment> filterList(List<Payment> list, String searchText){
        List<Payment> filteredList = new ArrayList<>();
        for (Payment pay : list){
            if(searchFindsOrder(pay, searchText)) filteredList.add(pay);
        }
        return FXCollections.observableList(filteredList);
    }

    @FXML
    private void getFiltre(ActionEvent event) {
        
        if(Filtre != cb_Filtre.getValue()){
            Filtre = cb_Filtre.getValue();
            
            t_search.clear();
        }
        
        
    }

    @FXML
    private void ModifierPayment(ActionEvent event) {
        try{
            
            TextFieldException.verifEmpty(t_pf.getText());
            Double pf = Double.parseDouble(t_pf.getText());
            TextFieldException.verifEmpty(cb_mp.getValue());
            String mp = cb_mp.getValue();

            Payment pay = new Payment();
            pay.setID_Payment(ID);
            pay.setPrix_F(pf);
            pay.setMode_Payment(mp);
            PaymentCRUD payc = new PaymentCRUD();
            payc.Modifier(pay);


            tab_pay.getItems().clear();


            try {
                tab_pay.getItems().addAll(payc.AfficherPayment());
            } catch (SQLException ex) {
                Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch(TextFieldException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty fields");
            alert.setHeaderText(e.getMessage());
            
            alert.showAndWait();
        }
        t_Pan.setText("");
        t_pf.clear();

    }

    @FXML
    private void SupprimerPayment(ActionEvent event) {
        PaymentCRUD payc = new PaymentCRUD();
        
        payc.Supprimer(ID);

 
        tab_pay.getItems().clear();
       
        
        try {
            tab_pay.getItems().addAll(payc.AfficherPayment());
        } catch (SQLException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        t_Pan.setText("");
        t_pf.clear();
    }

    @FXML
    private void toPDF(MouseEvent event) throws DocumentException, SQLException {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        final File selectedDirectory = directoryChooser.showDialog(null);
        if (selectedDirectory != null) {
            
            String dest = selectedDirectory.getAbsolutePath()+"\\Table des paiements.pdf";
            
            
            try {
                Document doc = new Document();
                PdfWriter.getInstance(doc, new FileOutputStream(dest));
                
                doc.open();
                
                String img = "src/images/logo.JPG";
                com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(img);
                doc.add(image);
                //Test PDF
                PdfPTable table = new PdfPTable(4);

                table.setWidthPercentage(100);
                table.setSpacingBefore(0f);
                table.setSpacingAfter(0f);

                // first row
                PdfPCell cell = new PdfPCell(new Phrase("Tableau des paiements"));
                cell.setColspan(4);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPadding(5.0f);
                cell.setBackgroundColor(new BaseColor(140, 221, 8));
                table.addCell(cell);

                table.addCell("ID Paiement");
                table.addCell("ID Panier");
                table.addCell("Prix Totale");
                table.addCell("Mode de paiement");
                

               
                
                PaymentCRUD payc = new PaymentCRUD();
                List<Payment> liste_pay = payc.AfficherPayment();
                for (int i = 0; i < liste_pay.size(); i++){
                    
                    table.addCell(String.valueOf(liste_pay.get(i).getID_Payment()));
                    table.addCell(String.valueOf(liste_pay.get(i).getID_Panier()));
                    table.addCell(String.valueOf(liste_pay.get(i).getPrix_F()));
                    table.addCell(String.valueOf(liste_pay.get(i).getMode_Payment()));
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

    
    
}
