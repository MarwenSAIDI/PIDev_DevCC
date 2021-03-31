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
import entities.Produit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
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
import javafx.stage.FileChooser;
import services.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author foura
 */
public class ProduitController implements Initializable {

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
    private TableView<Produit> tab_prod;
    @FXML
    private ImageView export;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfType;
    @FXML
    private TextField tfQuantite;
    @FXML
    private TextField tfPrix;
    @FXML
    private TextField ImageEventField;
    @FXML
    private TextField t_search;
    
    
    private String Filtre;
    
    private int ID_produit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
            ServiceProduit prodc = new ServiceProduit();
        
            tab_prod.setEditable(true);

            TableColumn<Produit,String> id_prod = new TableColumn("ID Produit");

            id_prod.setCellValueFactory(
                    new PropertyValueFactory<>("ID_Produit"));

            TableColumn<Produit,String> nom = new TableColumn("Nom");

            nom.setCellValueFactory(
                    new PropertyValueFactory<>("Nom"));

            TableColumn<Produit,String> type = new TableColumn("Type");

            type.setCellValueFactory(
                    new PropertyValueFactory<>("Type"));
            
            TableColumn<Produit,String> quant = new TableColumn("Quantitée");

            quant.setCellValueFactory(
                    new PropertyValueFactory<>("Quantitee"));

            TableColumn<Produit,String> prix = new TableColumn("Prix");

            prix.setCellValueFactory(
                    new PropertyValueFactory<>("Prix"));

            

            tab_prod.getColumns().add(id_prod);
            tab_prod.getColumns().add(nom);
            tab_prod.getColumns().add(type);
            tab_prod.getColumns().add(quant);
            tab_prod.getColumns().add(prix);

            tab_prod.getItems().addAll(prodc.ListProduit());
        }
        catch(NullPointerException NPE){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText("No database was found");
            
            
            alert.showAndWait();
            
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CRUD.fxml"));
                Parent root;
                root = loader.load();
                tab_prod.getScene().setRoot(root);
                
            } catch (IOException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    private void ModifierProduit(ActionEvent event) {
        Produit prod = new Produit();
        prod.setID_Produit(ID_produit);
        prod.setNom(tfNom.getText());
        prod.setType(tfType.getText());
        prod.setQuantitee(Integer.parseInt(tfQuantite.getText()));
        prod.setPrix(Double.parseDouble(tfPrix.getText()));
        prod.setImage(ImageEventField.getText());
        
        ServiceProduit s = new ServiceProduit();
        s.ModifierProduit(prod);
        
        tab_prod.getItems().clear();
        tab_prod.getItems().addAll(s.ListProduit());
        
        tfNom.clear();
        tfType.clear();
        tfQuantite.clear();
        tfPrix.clear();
        ImageEventField.clear();
     
    }

    @FXML
    private void SupprimerProduit(ActionEvent event) {
        ServiceProduit s=new ServiceProduit();
        s.SuprimerProduit(ID_produit);
        
        tab_prod.getItems().clear();
        tab_prod.getItems().addAll(s.ListProduit());
        
        
        tfNom.clear();
        tfType.clear();
        tfQuantite.clear();
        tfPrix.clear();
        ImageEventField.clear();
    }

    @FXML
    private void AjouterProduit(ActionEvent event) {
        if(tfQuantite.getText().isEmpty() || tfPrix.getText().isEmpty() || tfNom.getText().isEmpty())
         {
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("data invalide");
             alert.setHeaderText("Erreur de saisie");
             alert.setContentText("Verifier les champs vides");
             
             alert.showAndWait();
         }
        
        else if(Integer.parseInt(tfPrix.getText()) < 0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Prix invalide");
             alert.setHeaderText("Erreur de saisie");
             alert.setContentText("le prix doit etre superieur à 0 ");
             
             alert.showAndWait();
        }
        else if(Integer.parseInt(tfQuantite.getText()) < 0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Quantite invalide");
             alert.setHeaderText("Erreur de saisie");
             alert.setContentText("la quantite doit etre superieur à 0 ");
             
             alert.showAndWait();
        }
         
         
          else{
       
                   
            int b= Integer.parseInt(tfQuantite.getText());
            float c= Float.parseFloat(tfPrix.getText());
                   
            Produit e=new Produit();
            
            e.setNom(tfNom.getText());
            e.setType(tfType.getText());
            e.setQuantitee(b);
            e.setPrix(c);
            e.setImage(ImageEventField.getText());
            ServiceProduit s=new ServiceProduit();
            s.AjouterProduit(e);
  
            tab_prod.getItems().clear();
            tab_prod.getItems().addAll(s.ListProduit());
            
            tfNom.clear();
            tfType.clear();
            tfQuantite.clear();
            tfPrix.clear();
            ImageEventField.clear();
            /*String img= ImageEventField.getText();
            String ch="image/";
            String imgF= ch+img;
            Image im = new Image(getClass().getResourceAsStream(imgF));
            imageView.setImage(im);
            */
            
            
                 

        }

    }

    @FXML
    private void Select_contenu(MouseEvent event) {
        Produit prod = tab_prod.getSelectionModel().selectedItemProperty().get();
        ID_produit = prod.getID_Produit();
        tfNom.setText(String.valueOf(prod.getNom()));
        tfType.setText(String.valueOf(prod.getType()));
        tfQuantite.setText(String.valueOf(prod.getQuantitee()));
        tfPrix.setText(String.valueOf(prod.getPrix()));
    }

    @FXML
    private void toPDF(MouseEvent event) throws DocumentException {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        final File selectedDirectory = directoryChooser.showDialog(null);
        if (selectedDirectory != null) {
            
            String dest = selectedDirectory.getAbsolutePath()+"\\Table des produits.pdf";
            
            
            try {
                Document doc = new Document();
                PdfWriter.getInstance(doc, new FileOutputStream(dest));
                
                doc.open();
                
                String img = "C:\\Users\\foura\\OneDrive\\Bureau\\work\\Zenlife\\src\\images\\logo.JPG";
                com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(img);
                doc.add(image);
                //Test PDF
                PdfPTable table = new PdfPTable(4);

                table.setWidthPercentage(100);
                table.setSpacingBefore(0f);
                table.setSpacingAfter(0f);

                // first row
                PdfPCell cell = new PdfPCell(new Phrase("Tableau des produits"));
                cell.setColspan(4);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPadding(5.0f);
                cell.setBackgroundColor(new BaseColor(140, 221, 8));
                table.addCell(cell);

                table.addCell("ID produit");
                table.addCell("Nom du produit");
                table.addCell("Quantitée");
                table.addCell("Prix DTN");
                

               
                
                ServiceProduit prodc = new ServiceProduit();
                List<Produit> liste_prod = prodc.ListProduit();
                for (int i = 0; i < liste_prod.size(); i++){
                    
                    table.addCell(String.valueOf(liste_prod.get(i).getID_Produit()));
                    table.addCell(String.valueOf(liste_prod.get(i).getNom()));
                    table.addCell(String.valueOf(liste_prod.get(i).getQuantitee()));
                    table.addCell(String.valueOf(liste_prod.get(i).getPrix()));
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

    @FXML
    private void importerImage(MouseEvent event) {
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

    private boolean searchFindsOrder(Produit prod, String searchText){
        return (prod.getNom().toLowerCase().contains(searchText.toLowerCase()));
    }

    private ObservableList<Produit> filterList(List<Produit> list, String searchText){
        List<Produit> filteredList = new ArrayList<>();
        for (Produit prod : list){
            if(searchFindsOrder(prod, searchText)) filteredList.add(prod);
        }
        return FXCollections.observableList(filteredList);
    }

    @FXML
    private void Search(KeyEvent event) {
        ServiceProduit prodc = new ServiceProduit();
        if(!t_search.getText().isEmpty()){
            t_search.textProperty().addListener((observable, oldValue, newValue) ->
                tab_prod.setItems(filterList(prodc.ListProduit(), newValue))
            );
        }
    }
      @FXML
    private void gotoaccueil(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accueiladmin.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            textth.getScene().setRoot(root);
    }

    

    
}
