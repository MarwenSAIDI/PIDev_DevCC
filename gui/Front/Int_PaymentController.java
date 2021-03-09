/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui.Front;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import pidev.entities.Commande;
import pidev.entities.Panier;
import pidev.entities.Utilisateur;
import pidev.gui.MainFX;
import pidev.gui.UtilisateurController;
import pidev.services.CommandeCRUD;
import pidev.services.PanierCRUD;
import java.lang.IllegalArgumentException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pidev.services.ProduitCRUD;

/**
 * FXML Controller class
 *
 * @author Occurence
 */
public class Int_PaymentController implements Initializable {

    @FXML
    private VBox v_box_2;
    @FXML
    private VBox v_box_1;
    @FXML
    private HBox Title;

    private Utilisateur user;
    
    private Double totale;
    
    private Label l_totale;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        user = UtilisateurController.getUserG();
        PanierCRUD panc = new PanierCRUD();
        totale = 0.0;
        l_totale = new Label();
        v_box_1.setSpacing(25);
        try {
            Panier panier = panc.getPanier(user.getID_user()).stream().findFirst().get();
            CommandeCRUD comc = new CommandeCRUD();
            int size  = (int) comc.getCommande(panier.getID_Panier()).stream().count();
            List<Commande> liste_com = comc.getCommande(panier.getID_Panier());
            for(int i = 0; i< size; i++){

                Commande com = liste_com.get(i);
                
                try{
                    ImageView img_product = new ImageView("pidev/gui/Front/masque.jpg");
                    ImageView img_delete = new ImageView("pidev/gui/Front/020-close.png");
                    
                    TextField l_quant = new TextField(String.valueOf(com.getQuantitee()));
                    Label l_prix = new Label(String.valueOf(com.getPrix()*com.getQuantitee()));
                    Button b_inc = new Button("+");
                    Button b_dec = new Button("-");
                    Button b_delete = new Button();
                    
                    img_delete.setFitWidth(45);
                    img_delete.setFitHeight(45);
                   
                    b_delete.setGraphic(img_delete);
                    

                    HBox h_delete = new HBox(b_delete);
                    h_delete.setAlignment(Pos.CENTER);
                    
                    b_inc.setPrefSize(40, 40);
                    b_dec.setPrefSize(40, 40);
                    
                    b_inc.setFont(Font.font("FontAwesome", 20));
                    b_dec.setFont(Font.font("FontAwesome", 20));
                    l_quant.setPrefSize(50, 30);
                    
                    totale += com.getPrix()*com.getQuantitee();
                    b_inc.setOnAction(e->{
                        try {
                            l_quant.setText(String.valueOf(IncrementQauntitee(e, com, com.getID_Produit())));
                            l_prix.setText(String.valueOf(com.getPrix()*com.getQuantitee()));
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(Int_PaymentController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        try {
                            totale = CalculeTotale(size, panier.getID_Panier());
                        } catch (SQLException ex) {
                            Logger.getLogger(Int_PaymentController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        l_totale.setText("Prix totale : "+String.valueOf(totale)); 
                    });
                    
                    b_dec.setOnAction(e->{
                        l_quant.setText(String.valueOf(DecrementQauntitee(e, com)));
                        l_prix.setText(String.valueOf(com.getPrix()*com.getQuantitee()));
                            try {
                                totale = CalculeTotale(size, panier.getID_Panier());
                            } catch (SQLException ex) {
                                Logger.getLogger(Int_PaymentController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        l_totale.setText("Prix totale : "+String.valueOf(totale)); 
                    });
                    
                    
                    
                    img_product.setFitWidth(100);
                    img_product.setFitHeight(100);

                    l_quant.setFont(Font.font("FontAwesome", 20));
                    l_prix.setFont(Font.font("FontAwesome", 35));

                    HBox h_product = new HBox(img_product);
                    HBox h_prix = new HBox(l_prix);
                    HBox h_quant = new HBox();
                    
                    h_quant.getChildren().addAll(b_dec,l_quant,b_inc);

                    h_product.setPrefSize(200, 100);
                    h_quant.setPrefSize(200, 100);
                    h_prix.setPrefSize(200, 100);

                    h_product.setAlignment(Pos.CENTER);
                    h_quant.setAlignment(Pos.CENTER);
                    h_prix.setAlignment(Pos.CENTER);
                    
                    
                    
                    HBox h_p = new HBox();
                    h_p.getChildren().addAll(h_product,h_quant, h_prix,h_delete);

                    v_box_1.getChildren().add(h_p);
                    
                    b_delete.setOnAction(e->{
                        SupprimerCommande(e, com, h_p);
                        try {
                            
                            totale = CalculeTotale((int) comc.getCommande(panier.getID_Panier()).stream().count(), panier.getID_Panier());
                        } catch (SQLException ex) {
                            Logger.getLogger(Int_PaymentController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        l_totale.setText("Prix totale : "+String.valueOf(totale)); 
                    });
                }
                catch(IllegalArgumentException ex){
                    System.out.println(ex.getMessage());
                }           
            }
            l_totale.setText("Prix totale : "+String.valueOf(totale)); 
            l_totale.setFont(Font.font("FontAwesome", 35));
            HBox h_totale = new HBox(l_totale);
            h_totale.setAlignment(Pos.CENTER);
            
            
            
            ImageView img_retour = new ImageView("pidev/gui/Front/undo.png");
            img_retour.setFitWidth(45);
            img_retour.setFitHeight(45);
            Button b_retour = new Button();
            b_retour.setGraphic(img_retour);
            b_retour.setOnAction(e->returnShop(e));
            
            HBox h_button = new HBox(b_retour);
            h_button.setAlignment(Pos.CENTER);
            
            
            HBox h_final = new HBox();
            h_final.setAlignment(Pos.CENTER_RIGHT);
            h_final.setSpacing(50);
            h_final.getChildren().addAll(b_retour,h_totale);
            
            v_box_1.getChildren().addAll(h_final);
            
        } catch (SQLException ex) {
            Logger.getLogger(Int_PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    private void returnShop(ActionEvent event) {
        try{
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
            Parent root = loader.load();
            try{
                v_box_1.getScene().setRoot(root);
            }
            catch(NullPointerException ex){
                
            }
          
        }
        catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private int IncrementQauntitee(ActionEvent event, Commande com, int ID_Prod) throws SQLException{
        ProduitCRUD proc = new ProduitCRUD();
   
        if(proc.getProduit(ID_Prod) - com.getQuantitee() > 0){
            com.setQuantitee(com.getQuantitee() + 1);
            CommandeCRUD comc = new CommandeCRUD();
            comc.Modifier(com);
            
        }
        
        return com.getQuantitee();
    }
    
    private int DecrementQauntitee(ActionEvent event, Commande com){
        if(com.getQuantitee() > 0){
            com.setQuantitee(com.getQuantitee() - 1);
            CommandeCRUD comc = new CommandeCRUD();
            comc.Modifier(com);
        }
        
        return com.getQuantitee();
        
    }
    
    public Double CalculeTotale(int size, int ID) throws SQLException{
        Double totale = 0.0;
        CommandeCRUD comc = new CommandeCRUD();
        List<Commande> liste_com = comc.getCommande(ID);
        for(int i = 0; i< size; i++){
            Commande com = liste_com.get(i);
            totale += com.getPrix()*com.getQuantitee();
        }
        return totale;
    }
    
    private void SupprimerCommande(ActionEvent event, Commande com, HBox h){
        CommandeCRUD comc = new CommandeCRUD();
        comc.Supprimer(com.getID_Commande());
        h.getChildren().clear();
    }

   

    
}
