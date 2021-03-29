/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientinterfaces;

//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Commande;
import entities.Panier;
import entities.Produit;
//import entities.ReservationEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.CommandeCRUD;
import services.PanierCRUD;
import services.ServiceProduit;
import services.UserSession;
import utils.DataBase;

/**
 * FXML Controller class
 *
 * @author user
 */

public class AfficherProduitClientController implements Initializable {
 private Statement ste;
                DataBase db=new DataBase();
               private Connection con=db.getConnection();
    private Label fruitNameLable;
    private Label fruitPriceLabel;
    @FXML
    private ImageView fruitImg;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    ServiceProduit cs = new ServiceProduit();

    List<Produit> evenementss = cs.ListProduit();
    private Image image;
    private MyListenerProduit MyListenerProduit;
    @FXML
    private VBox EventChoisieCarte;
    private Label TitreEventLable;
    private Label LieuEventLabel;
    private Label PlaceRestanteCarte;
    @FXML
    private Label TarifEventCarte;
    @FXML
    private Label TitreEventCarte;
    private Label LieuEventCarte;
    @FXML
    private Label idOrganisateurLabel;
    @FXML
    private Label imgEventLabel;
    private ComboBox combobox_recherType;
    
               
    private Label TarifPromolabel;
    private Label idProduitLabel;
    private Label nvPrixLabel;
    private Label dtLabel;
    @FXML
    private ImageView b_panier;
    @FXML
    private Label labelemail;
    @FXML
    private Label idEvent;
    @FXML
    private HBox h_prix_prod;
    @FXML
    private JFXButton b_dec;
    @FXML
    private JFXTextField tf_quant;
    @FXML
    private JFXButton b_inc;
    @FXML
    private Button b_Commande;
    @FXML
    private JFXButton btnRecommandation;
    @FXML
    private JFXButton btnarticle;
    @FXML
    private JFXButton btnlivre;
    @FXML
    private JFXButton deconnexion;
    
    
    private int ID_produitSelectionner;
               
               
    private List<Produit> getData() {
    List<Produit> evenements = cs.ListProduit();
    
          ObservableList<Produit> data =
                  
        FXCollections.observableArrayList(evenements );

     
        return data;
    }

    private void setChosenEvent(Produit e) {
    
        ID_produitSelectionner = e.getID_Produit();
        String EventPrix = String.valueOf(e.getPrix());
        
        TitreEventCarte.setText(e.getNom());
//        LieuEventCarte.setText(e.getType());
        
        TarifEventCarte.setText(EventPrix);
//        PlaceRestanteCarte.setText(String.valueOf(e.getQuantitee()));
//        idProduitLabel.setText(String.valueOf(e.getID_Produit()));
        
        int valeur_promo=0;
        Double nv_prix=0.0;
        try {
     String requete = "SELECT valP FROM promotion  where ?=idP and DATEDIFF( dateF, now( )) > 0 ";
     PreparedStatement pst = con.prepareStatement(requete);               
     pst.setInt(1, e.getID_Produit());
     ResultSet ee = pst.executeQuery();
     while (ee.next()) {
  valeur_promo= ee.getInt("valP");
  
  
 
  
}
     }
    catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
if (valeur_promo!=0 ) {
    nv_prix= ((e.getPrix()*valeur_promo)/100);
    //TarifPromolabel.setText(String.valueOf(nv_prix));
    //nvPrixLabel.setText("Nouveau prix :");
    //dtLabel.setText("DT");
  
    
}

    
        
        // DateEventCarte.setDate(e.getDateF());
        
        
        
        String imgg= e.getImage();
        String ch="/images/";
        String imgF= ch+imgg;
        
        Image imageF = new Image(getClass().getResourceAsStream(imgF));
        fruitImg.setImage(imageF);
       
        EventChoisieCarte.setStyle("-fx-background-color: #" + "6187ED" + ";\n" +
                "    -fx-background-radius: 30;");
        
        tf_quant.setText(String.valueOf(0));
        
        b_inc.setOnAction(event->{
            Increment(event, e);
        });
        
        b_dec.setOnAction(event->{
            Decrement(event, e);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      // evenementss.addAll(getData());
      

      
        
        if (evenementss.size() > 0) {
            setChosenEvent(evenementss.get(0));
            MyListenerProduit = new MyListenerProduit() {
                public void onClickListener(Produit e) {
                     //TarifPromolabel.setText(null);
                             //nvPrixLabel.setText(null);
                             //dtLabel.setText(null);
                    setChosenEvent(e);
                }


            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < evenementss.size(); i++) {
                
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemProduit.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemProduitController ItemProduitController = fxmlLoader.getController();
                ItemProduitController.setData(evenementss.get(i),MyListenerProduit);
                
                

                if (column == 2) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @FXML
//    private void LirePlus_Reserver(ActionEvent event) {
//        
//      
//        
//        String titre=  TitreEventCarte.getText();
////        String IdOrganisateur = idOrganisateurLabel.getText();
//        String LieuEvent= LieuEventCarte.getText();
//        String DateEvent= DateEventCarte.getText();
//        
//        String ImagEvent= (imgEventLabel.getText());
//        String tarif= TarifEventCarte.getText();
//        String Idevent= idEvent.getText();
//        int id_evenement= Integer.parseInt(Idevent);
//        String Descri=" ";
//          int id_orga=0;
//          String imageFf=" ";
//          String lieu=" ";
//        try {
//     String requete = "SELECT description,id_organisateur,image,lieu FROM evenement  where ?=id  order by id desc";
//     PreparedStatement pst = con.prepareStatement(requete);               
//     pst.setInt(1, id_evenement);
//     ResultSet e = pst.executeQuery();
//     while (e.next()) {
//  Descri = e.getString("description");
//  id_orga= e.getInt("id_organisateur");
//  imageFf= e.getString("image");
//  lieu= e.getString("lieu");
//  
//}
//    }
//    catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        String IdOrganisateur=  String.valueOf(id_orga);
//
//        
//        String ch="/imgEvent/";
//        String imgF= ch+imageFf;
//        Image imageF = new Image(getClass().getResourceAsStream(imgF));
//        
//    
//
//        if("Complet".equals(PlaceRestanteCarte.getText()))
//        {
//              Alert alert = new Alert(Alert.AlertType.WARNING);
//               alert.setTitle("Alerte !");
//               alert.setHeaderText("Vous ne  pouvez pas réserver cette événement");
//               alert.setContentText(" Cette événement est complet");
//               
//               alert.showAndWait();
//        }
//        else if("Event passer".equals(EvenementEffectueCarte.getText()))
//        {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//               alert.setTitle("Alerte !");
//               alert.setHeaderText("Vous ne  pouvez pas réserver cette événement");
//               alert.setContentText(" Cette événement a déja eu lieu");
//               
//               alert.showAndWait();
//        }
//                else
//        {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("RéservationClientEvent.fxml"));
//        try {
//            Parent root = loader.load();
//            RéservationClientEventController pdc = loader.getController();
//            pdc.setTitreEventLabel(titre);
//            pdc.setDescriptionEventLabel(Descri);
//            pdc.setDateEventLabel(DateEvent);
//            pdc.setOrganisateurEventLabel(IdOrganisateur);
//            pdc.setTarifEventLabel(tarif);
//            pdc.setimgEvent(imageF);
//            pdc.setidEventLabel(Idevent);
//            pdc.setlieuEventLabel(lieu);
//            
//            btnLirePlus.getScene().setRoot(root);
//
//        } catch (IOException ex) {
//            Logger.getLogger(AfficherEventClientController.class.getName()).log(Level.SEVERE, null, ex);
//        }}
//    }
//
//    @FXML
//    private void GestionUser(MouseEvent event) {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionReservationEventClient.fxml"));
//        try {
//            Parent root = loader.load();
//            GestionUserIcon.getScene().setRoot(root);
//        } catch (IOException ex) {
//            Logger.getLogger(AfficherEventClientController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    private void rechercherType(ActionEvent event) {
//         
//                 grid.setGridLinesVisible(false);
//grid.getColumnConstraints().clear();
//grid.getRowConstraints().clear();
//grid.getChildren().clear();
//grid.setGridLinesVisible(false);
//       ObservableList<String> typee;
//        typee = FXCollections.observableArrayList("Conférence","Cinéma","Méditation","Musique","Randonnée","Sport et fitness" );
//        combobox_recherType.setItems(typee);
//          String Typee= combobox_recherType.getSelectionModel().getSelectedItem().toString();
//        if (evenementss.size() > 0) {
//            setChosenEvent(evenementss.get(0));
//            MyListenerProduit = new MyListenerProduit() {
//                public void onClickListener(Produit e) {
//                    setChosenEvent(e);
//                }
//
//            };
//        }
//        int column = 0;
//        int row = 1;
//        try {
//            for (int i = 0; i < evenementss.size(); i++) {
//                
//                if(Typee.equals(evenementss.get(i).getType()))
//                    {
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
//                AnchorPane anchorPane = fxmlLoader.load();
//
//                ItemProduitController itemController = fxmlLoader.getController();
//                itemController.setData(evenementss.get(i),MyListenerProduit);
//
//                if (column == 2) {
//                    column = 0;
//                    row++;
//                }
//
//                grid.add(anchorPane, column++, row); //(child,column,row)
//                //set grid width
//                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
//                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                grid.setMaxWidth(Region.USE_PREF_SIZE);
//
//                //set grid height
//                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
//                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
//                grid.setMaxHeight(Region.USE_PREF_SIZE);
//
//                GridPane.setMargin(anchorPane, new Insets(10));
//            }}
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        
//    }
//
//    @FXML
//    private void AfficherMeteo(MouseEvent event) throws IOException {
//        
//        
//             Parent root = FXMLLoader.load(getClass().getResource("WeatherView.fxml"));
//
//     Scene scene = new Scene(root);  
//      Stage primaryStage = new Stage();
//     primaryStage.setScene(scene);  
//     primaryStage.setTitle("Météo du jour");  
//     primaryStage.centerOnScreen();  
//     primaryStage.setResizable(false);  
//     primaryStage.setOpacity(1);  
//     primaryStage.show();  
//        
//////        
//////        FXMLLoader loader = new FXMLLoader(getClass().getResource("WeatherView.fxml"));
//////        try {
//////            Parent root = loader.load();
//////            GestionUserIcon.getScene().setRoot(root);
//////        } catch (IOException ex) {
//////            Logger.getLogger(AfficherEventClientController.class.getName()).log(Level.SEVERE, null, ex);
//////        }
//        
//        
////        FXMLLoader loader = new FXMLLoader(getClass().getResource("WeatherView.fxml"));
////        try {
////       
////        Scene scene = new Scene(loader.load());
////       Stage Stage stage = new Stage();
////        stage.setTitle("MyWeather");
////        stage.setScene(scene);
////        stage.show();
////        }catch (IOException e) {
////        Logger logger = Logger.getLogger(getClass().getName());
////        logger.log(Level.SEVERE, "Failed to create new Window.", e);
////    }
//
//    }

    @FXML
    private void go_to_Panier(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("IntPaiementClient.fxml"));
  
            Parent root = loader.load();
            b_Commande.getScene().setRoot(root); 
    }

    @FXML
    private void AjouterCommande(ActionEvent event) throws SQLException {
        double prix_U = Double.parseDouble(TarifEventCarte.getText());
        int quant = Integer.parseInt(tf_quant.getText());
        
        Commande com = new Commande();
        Panier pan = new Panier();
        
        
        com.setID_Produit(ID_produitSelectionner);
        com.setPrix(prix_U);
        com.setQuantitee(quant);
        
        PanierCRUD panc = new PanierCRUD();
        CommandeCRUD comc = new CommandeCRUD();
        
        List<Panier> pan_liste = panc.verifPanier(panc.getUser(UserSession.instance.getUserName()).get(0).getCin());
        
        if(!pan_liste.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
                pan_f.setID_User(panc.getUser(UserSession.instance.getUserName()).get(0).getCin());
                pan_f.setStatus_panier("En cours");
                
                panc.Ajouter(pan_f);
                
                pan_liste = panc.verifPanier(panc.getUser(UserSession.instance.getUserName()).get(0).getCin());
                pan_f = pan_liste.stream().findFirst().get();
                com.setID_Panier(pan_f.getID_Panier());
                
                comc.Ajouter(com);
               
            }
        }
        else{
            Panier pan_f = new Panier();
            pan_f.setDate_C(LocalDate.now());
            pan_f.setID_User(panc.getUser(UserSession.instance.getUserName()).get(0).getCin());
            pan_f.setStatus_panier("En cours");
                
            panc.Ajouter(pan_f);
                
            pan_liste = panc.verifPanier(panc.getUser(UserSession.instance.getUserName()).get(0).getCin());
            pan_f = pan_liste.stream().findFirst().get();
            com.setID_Panier(pan_f.getID_Panier());
                
            comc.Ajouter(com);
        }
    }

    @FXML
    private void gotorecommandation(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("client.fxml"));
  
            Parent root = loader.load();
            btnRecommandation.getScene().setRoot(root);    
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
    private void go_to_Produit_Client(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduitClient.fxml"));
  
            Parent root = loader.load();
            b_Commande.getScene().setRoot(root); 
    }

    @FXML
    private void deconnecter(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/authentification/authentification.fxml"));
        
 Parent root = fxmlLoader.load();
 deconnexion.getScene().setRoot(root);}
    
    
    public void Increment(ActionEvent event, Produit p){
        int quant = Integer.parseInt(tf_quant.getText());
        if(quant < p.getQuantitee()){
            quant += 1;
            
            
        }
        
        tf_quant.setText(String.valueOf(quant));
        
    }
    
    
    public void Decrement(ActionEvent event, Produit p){
        int quant = Integer.parseInt(tf_quant.getText());
        if(quant > 0){
            quant -= 1;
            
            
        }
        
        tf_quant.setText(String.valueOf(quant));
        
    }
    
    
    
}
