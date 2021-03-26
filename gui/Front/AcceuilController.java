/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui.Front;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pidev.entities.Commande;
import pidev.entities.Panier;
import pidev.entities.Payment;
import pidev.entities.Utilisateur;
import pidev.gui.UtilisateurController;
import pidev.entities.Produit;
import pidev.services.CommandeCRUD;
import pidev.services.PanierCRUD;
import pidev.services.PaymentCRUD;
import pidev.services.ProduitCRUD;


/**
 * FXML Controller class
 *
 * @author Occurence
 */
public class AcceuilController implements Initializable {

    @FXML
    private TableView<Produit> tab_prod;
    @FXML
    private TextField t_nom;
    @FXML
    private TextField t_prix;
    @FXML
    private TextField t_quant;
    @FXML
    private Label l_user;
    
    private int ID_produitSelectionner;
    
    private Utilisateur user;
    @FXML
    private Button b_panier;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        user = UtilisateurController.getUserG();
        l_user.setText(user.getPrenom()+" "+user.getNom());
        t_quant.setText("1");
        tab_prod.setEditable(true);
        
        ImageView img_cart = new ImageView("pidev/gui/Front/001-trolley cart.png");
        img_cart.setFitWidth(49);
        img_cart.setFitHeight(49);
        
        b_panier.setText("");
        b_panier.setGraphic(img_cart);
        TableColumn<Produit,String> Nom = new TableColumn("Nom Produit");

        Nom.setCellValueFactory(
            new PropertyValueFactory<>("Nom"));

        TableColumn<Produit,String> prix = new TableColumn("Prix Unitaire");

        prix.setCellValueFactory(
            new PropertyValueFactory<>("Prix"));

            

        tab_prod.getColumns().add(Nom);
        tab_prod.getColumns().add(prix);
        
        ProduitCRUD prodc = new ProduitCRUD();
        
        try {
                tab_prod.getItems().addAll(prodc.AfficherProduit());
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

    }   

    @FXML
    private void achat(ActionEvent event) throws SQLException {
        double prix_U = Double.parseDouble(t_prix.getText());
        int quant = Integer.parseInt(t_quant.getText());
        
        Commande com = new Commande();
        Panier pan = new Panier();
        
        
        com.setID_Produit(ID_produitSelectionner);
        com.setPrix(prix_U);
        com.setQuantitee(quant);
        
        PanierCRUD panc = new PanierCRUD();
        CommandeCRUD comc = new CommandeCRUD();
        
        List<Panier> pan_liste = panc.verifPanier(user.getID_user());
        
        if(!pan_liste.isEmpty()){
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Panier existe");
            alert.setHeaderText("Il semble qu'un ancien panier est déjà ouvert");
            alert.setContentText("Voulez-vous ajouter votre commande a celuis là ?");
            ButtonType button_ok = new ButtonType("Oui");
            ButtonType button_new = new ButtonType("Non, nouveau panier");
            
            alert.getButtonTypes().setAll(button_ok, button_new);

            Optional<ButtonType> result = alert.showAndWait();
            
            System.out.println(result.get());
            System.out.println(result.get().getText());
            
            System.out.println(button_ok);
            System.out.println(button_new);
            
            if(result.get()== button_ok){
                Panier pan_f = pan_liste.stream().findFirst().get();
                com.setID_Panier(pan_f.getID_Panier());
                
                pan_f.setDate_U(LocalDate.now());
                panc.Modifier(pan_f);
                comc.Ajouter(com);
                
            }
            else{
                Panier pan_f = pan_liste.stream().findFirst().get();
                pan_f.setStatus_panier("Abondonner");
                panc.Modifier(pan_f);
                
                pan_f = new Panier();
                pan_f.setDate_C(LocalDate.now());
                pan_f.setID_User(user.getID_user());
                pan_f.setStatus_panier("En cours");
                
                panc.Ajouter(pan_f);
                
                pan_liste = panc.verifPanier(user.getID_user());
                pan_f = pan_liste.stream().findFirst().get();
                com.setID_Panier(pan_f.getID_Panier());
                
                comc.Ajouter(com);
               
            }
        }
        else{
            Panier pan_f = new Panier();
            pan_f.setDate_C(LocalDate.now());
            pan_f.setID_User(user.getID_user());
            pan_f.setStatus_panier("En cours");
                
            panc.Ajouter(pan_f);
                
            pan_liste = panc.verifPanier(user.getID_user());
            pan_f = pan_liste.stream().findFirst().get();
            com.setID_Panier(pan_f.getID_Panier());
                
            comc.Ajouter(com);
        }
        
        
    }

    @FXML
    private void selectProd(MouseEvent event) {
        Produit produit_S =  tab_prod.getSelectionModel().selectedItemProperty().get();
        t_nom.setText(produit_S.getNom());
        t_prix.setText(String.valueOf(produit_S.getPrix()));
        t_quant.setText("1");
        ID_produitSelectionner = produit_S.getID_Produit();
        
    }

    @FXML
    private void Decriment(ActionEvent event) {
        int quant = Integer.parseInt(t_quant.getText());
        
        if (quant > 0){
            quant -= 1;
        }
        else{
            quant = 0;
        }
        t_quant.setText(String.valueOf(quant));
    }

    @FXML
    private void Increment(ActionEvent event) {
        int quant = Integer.parseInt(t_quant.getText());
        quant += 1;
        t_quant.setText(String.valueOf(quant));
    }

    @FXML
    private void listeCommande(ActionEvent event) {
        try{
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Int_Payment.fxml"));
            Parent root = loader.load();
            
            b_panier.getScene().setRoot(root);
          
        }
        catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
