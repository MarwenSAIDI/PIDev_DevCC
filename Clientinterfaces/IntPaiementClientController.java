/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientinterfaces;

import com.jfoenix.controls.JFXButton;
import entities.Commande;
import entities.Panier;
import entities.Payment;
import entities.Produit;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import services.CommandeCRUD;
import services.PanierCRUD;
import services.PaymentCRUD;
import services.ProduitCRUD;
import services.ServiceProduit;
import services.TextFieldException;
import services.UserSession;
import utils.TwilioSMS;

/**
 * FXML Controller class
 *
 * @author Occurence
 */
public class IntPaiementClientController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private JFXButton btnarticle;
    @FXML
    private JFXButton btnlivre;
    @FXML
    private JFXButton deconnexion;
    @FXML
    private VBox v_box_1;
    @FXML
    private HBox Title;
    @FXML
    private Label labelemail;
    @FXML
    private VBox v_box_2;
    @FXML
    private ComboBox<String> cb_pay;
    @FXML
    private TextField t_ch;
    @FXML
    private TextField t_num;
    @FXML
    private TextField t_cvv;
    @FXML
    private Button b_valider;
    
    private Double totale;
    
    private Label l_totale;

    /**
     * Initializes the controller class.
     */
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //labelemail.setText( UserSession.instance.getUserName());
        
        PanierCRUD panc = new PanierCRUD();
        //System.out.println(panc.getUser(UserSession.instance.getUserName()));
        
        cb_pay.getItems().addAll("Espece", "Carte");
        cb_pay.getSelectionModel().select("Espèce");
        // TODO
        
        
        totale = 0.0;
        l_totale = new Label();
        v_box_1.setSpacing(25);
        try {
            
            Panier panier = panc.getPanier(panc.getUser(UserSession.instance.getUserName()).get(0).getCin()).stream().findFirst().get();
            CommandeCRUD comc = new CommandeCRUD();
            int size  = (int) comc.getCommande(panier.getID_Panier()).stream().count();
            List<Commande> liste_com = comc.getCommande(panier.getID_Panier());
            for(int i = 0; i< size; i++){

                Commande com = liste_com.get(i);
                
                try{
                    Produit p = comc.getProduit(com.getID_Produit());
                    String imgg= p.getImage();
                    String ch="/images/";
                    String imgF= ch+imgg;

                    Image imageF = new Image(getClass().getResourceAsStream(imgF));
                    
                    ImageView img_product = new ImageView();
                    
                    img_product.setImage(imageF);
                    
                    
                    ImageView img_delete = new ImageView("images/020-close.png");
                    
                    TextField l_quant = new TextField(String.valueOf(com.getQuantitee()));
                    Label l_prix = new Label(String.valueOf(com.getPrix()*com.getQuantitee()));
                    Button b_inc = new Button("+");
                    Button b_dec = new Button("-");
                    Button b_delete = new Button();
                    
                    b_inc.setStyle("-fx-background-radius : 0px 50px 50px 0px");
                    b_dec.setStyle("-fx-background-radius : 50px 0px 0px 50px");
                    b_delete.setStyle("-fx-background-radius : 50px 50px 50px 50px");
                    
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
                            System.out.println(ex.getMessage());
                        }
                        
                        try {
                            totale = CalculeTotale((int) comc.getCommande(panier.getID_Panier()).stream().count(), panier.getID_Panier());
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        }
                        l_totale.setText("Prix totale : "+String.valueOf(totale)); 
                    });
                    
                    b_dec.setOnAction(e->{
                        l_quant.setText(String.valueOf(DecrementQauntitee(e, com)));
                        l_prix.setText(String.valueOf(com.getPrix()*com.getQuantitee()));
                            try {
                                totale = CalculeTotale((int) comc.getCommande(panier.getID_Panier()).stream().count(), panier.getID_Panier());
                            } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
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
                            System.out.println(ex.getMessage());
                        }
                        
                        l_totale.setText("Prix totale : "+String.valueOf(totale)); 
                    });
                    
                    b_valider.setOnAction(e -> {
                        try {
                            AjouterPayment(e, com.getID_Panier(), totale, cb_pay.getValue(),liste_com);
                            
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        }
                        
                        
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
            
            
            
            ImageView img_retour = new ImageView("images/undo.png");
            img_retour.setFitWidth(45);
            img_retour.setFitHeight(45);
            Button b_retour = new Button();
            b_retour.setStyle("-fx-background-radius : 50px 50px 50px 50px");
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
            System.out.println(ex.getMessage());
        }
    }   
    
    private void returnShop(ActionEvent event) {
        try{
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduitClient.fxml"));
            Parent root = loader.load();
            try{
                v_box_1.getScene().setRoot(root);
            }
            catch(NullPointerException ex){
                System.out.println(ex.getMessage());
            }
          
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    private int IncrementQauntitee(ActionEvent event, Commande com, int ID_Prod) throws SQLException{
        ServiceProduit proc = new ServiceProduit();
   
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


   private void AjouterPayment(ActionEvent event, int id, Double totale, String mp, List<Commande> liste_com) throws SQLException {
        
       
        if(mp == "Carte"){
            try{
                TextFieldException.verifEmpty(t_ch.getText());
                String card_holder = t_ch.getText();
                
                TextFieldException.verifEmpty(t_num.getText());
                String Num_card = t_num.getText();
                
                TextFieldException.verifEmpty(t_cvv.getText());
                String cvv = t_cvv.getText();
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Payment valider");
                alert.setHeaderText("Voulez vous finaliser la commande");

                ButtonType button_ok = new ButtonType("OK");
                ButtonType button_cancel = new ButtonType("Annuer");   
                alert.getButtonTypes().setAll(button_ok, button_cancel);

                Optional<ButtonType> result = alert.showAndWait();

                if(result.get() == button_ok){
                    PaymentCRUD payc = new PaymentCRUD();
                    Payment pay = new Payment();
                    PanierCRUD panc = new PanierCRUD();
                    Panier panier = panc.getPanier(panc.getUser(UserSession.instance.getUserName()).get(0).getCin()).stream().findFirst().get();
                    panier.setStatus_panier("Payer");
                    pay.setID_Panier(id);
                    pay.setPrix_F(totale);
                    pay.setMode_Payment(mp);

                    panc.Modifier(panier);
                    payc.Ajouter(pay);
                    
                    /*JavaMailUtil mail = new JavaMailUtil();
                    mail.sendMail(user.getEmail(), "<h2>Monsieur "+user.getNom()+" "+user.getPrenom()+",</h2><p>Votre commande a bien été enregistrer. Vous la reseverée après <b>7 jours</b> ou moins.</p><p>Cordialement.</p>");*/
                    
                    
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduitClient.fxml"));
                        Parent root;
                        root = loader.load();
                        b_valider.getScene().setRoot(root);

                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
            catch(TextFieldException e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Empty fields");
                alert.setHeaderText(e.getMessage());

                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Payment valider");
            alert.setHeaderText("Voulez vous finaliser la commande");

            ButtonType button_ok = new ButtonType("OK");
            ButtonType button_cancel = new ButtonType("Annuler");   
            alert.getButtonTypes().setAll(button_ok, button_cancel);


            Optional<ButtonType> result = alert.showAndWait();

            if(result.get() == button_ok){
                PaymentCRUD payc = new PaymentCRUD();
                Payment pay = new Payment();
                PanierCRUD panc = new PanierCRUD();
                Panier panier = panc.getPanier(panc.getUser(UserSession.instance.getUserName()).get(0).getCin()).stream().findFirst().get();
                panier.setStatus_panier("Payer");
                pay.setID_Panier(id);
                pay.setPrix_F(totale);
                pay.setMode_Payment(mp);

                panc.Modifier(panier);
                payc.Ajouter(pay);
                
                ServiceProduit prodc = new ServiceProduit();
                
                List<Produit> liste_prod = prodc.ListProduit();
                
                for(Commande com: liste_com){
                    List<Produit> prod = liste_prod.stream().filter(p->p.getID_Produit() == com.getID_Produit())
                                                      .collect(Collectors.toList());
                    int new_quant = prod.get(0).getQuantitee() - com.getQuantitee();
                    prod.get(0).setQuantitee(new_quant);
                    
                    prodc.ModifierProduit(prod.get(0));
                    
                }
                

                TwilioSMS twilio = new TwilioSMS();
                
                String msg = "Cher client,\nVotre commande n° "+String.valueOf(liste_com.get(0).getID_Panier())+" a  été effectuer avec succer.\nVous la receverer dans les prochains délais.\nZenlife";

                twilio.sendSMS(msg, "+21652836953");


                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduitClient.fxml"));
                    Parent root;
                    root = loader.load();
                    b_valider.getScene().setRoot(root);

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        
   
    } 

    @FXML
    private void afficherarticle(MouseEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("clientyassine.fxml"));
  
            Parent root = loader.load();
            btnarticle.getScene().setRoot(root);   
    }

    @FXML
    private void afficherlivre(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("clientyassine.fxml"));
  
            Parent root = loader.load();
            btnlivre.getScene().setRoot(root);   
    }

    @FXML
    private void deconnecter(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/authentification/authentification.fxml"));
        
 Parent root = fxmlLoader.load();
 deconnexion.getScene().setRoot(root);}
    
    
    
}
