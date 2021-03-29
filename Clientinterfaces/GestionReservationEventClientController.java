/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientinterfaces;

import Clientinterfaces.AfficherEventClientController;
import com.jfoenix.controls.JFXButton;
import entities.Evenement;
import entities.ReservationEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import services.ServiceEvenement;
import services.ServiceReservationEvent;
import services.UserSession;
import utils.DataBase;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GestionReservationEventClientController implements Initializable {

    @FXML
    private JFXButton btnPageEvent;
    @FXML
    private TableView<ReservationEvent> TableView;
    @FXML
    private TableColumn<ReservationEvent, Integer> IdReservationColumn;
    @FXML
    private TableColumn<ReservationEvent, Integer> idEventColumn;
    @FXML
    private TableColumn<ReservationEvent, String> titreEventColumn;
    @FXML
    private TableColumn<ReservationEvent, String> DateEventColumn;
    @FXML
    private TableColumn<ReservationEvent, Integer>nbPlaceColumn;
    @FXML
    private TableColumn<ReservationEvent, Float>TotaPayerColumn;
    @FXML
    private TableColumn<ReservationEvent, String> ModePaimentColumn;
    @FXML
    private TableColumn<ReservationEvent, String> EtatReservationColumn;
    @FXML
    private Label DateEventLabel;
    @FXML
    private Label TarifEventLabel;
    @FXML
    private Label TotalEventLabel;
    @FXML
    private Label PlaceAreserverEventLabel;
    @FXML
    private ComboBox PlaceAreserverComboBox;
    @FXML
    private Label titreEventLabel;
    @FXML
    private Button Modifer;
    @FXML
    private Button annulerReservatiion;
    
     private Statement ste;
                DataBase db=new DataBase();
               private Connection con=db.getConnection();
    @FXML
    private Button btnTherpeute;
    @FXML
    private JFXButton btnarticle;
    @FXML
    private JFXButton btnlivre;
    @FXML
    private JFXButton deconnexion;
    @FXML
    private JFXButton btnRecommandation;
    @FXML
    private Label labelemail;

public int retourCin()
{int user_id=0;
try {
     String requete = "SELECT cin from user  where ?=email ";
     PreparedStatement pst = con.prepareStatement(requete);               
     pst.setString(1,labelemail.getText());
     ResultSet e = pst.executeQuery();
     while (e.next()) {
  user_id = e.getInt("cin");
}
    }
    catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
return user_id;

}
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


//        
//        
                                   labelemail.setText( UserSession.instance.getUserName());
int user_id=0;
try {
     String requete = "SELECT cin from user  where ?=email ";
     PreparedStatement pst = con.prepareStatement(requete);               
     pst.setString(1,labelemail.getText());
     ResultSet e = pst.executeQuery();
     while (e.next()) {
  user_id = e.getInt("cin");
}
    }
    catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


            ServiceReservationEvent cs = new ServiceReservationEvent();
            List<ReservationEvent> lc = cs.ListReservationEvent1(user_id);
            
            ObservableList<ReservationEvent> data =
                    
                    FXCollections.observableArrayList(lc );
            
            idEventColumn.setCellValueFactory(
                    new PropertyValueFactory<>("id_event"));
            
            IdReservationColumn.setCellValueFactory(
                    new PropertyValueFactory<>("id"));
            
            
            titreEventColumn.setCellValueFactory(
                    new PropertyValueFactory<>("Titre_event"));
            
            
            DateEventColumn.setCellValueFactory(
                    new PropertyValueFactory<>("date_event"));
            
            nbPlaceColumn.setCellValueFactory(
                    new PropertyValueFactory<>("nb_place"));
            
            TotaPayerColumn.setCellValueFactory(
                    new PropertyValueFactory<>("total"));
            
            ModePaimentColumn.setCellValueFactory(
                    new PropertyValueFactory<>("mode_paiement"));
            
            EtatReservationColumn.setCellValueFactory(
                    new PropertyValueFactory<>("etat"));
            
            
            TableView.setItems(data);
            
        ObservableList<Integer> listee;
        listee = FXCollections.observableArrayList(1,2,3,4,5);
        PlaceAreserverComboBox.setItems(listee);

        
    }    

    @FXML
    private void PageEvent(ActionEvent event) {
          
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherEventClient.fxml"));
        
        
        try {
            Parent root = loader.load();
            btnPageEvent.getScene().setRoot(root);
            
        } catch (IOException ex) {
            Logger.getLogger(AfficherEventClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


   
    
    public  float CalculTotal()
    {
         String nb_place= PlaceAreserverComboBox.getSelectionModel().getSelectedItem().toString();
       int place=Integer.parseInt(nb_place);
       String tarif = TarifEventLabel.getText();
       float tariff= Float.parseFloat(tarif);
       float total= place * tariff;
       
       return total;
    }
//  
//
//    @FXML
//    private void CalculTotalll(ActionEvent event) {
//         float total= CalculTotal();
//      String totall= String.valueOf(total);
//      TotalEventLabel.setText(totall);
//
//    }
  
  
public void viderChamps()
{
      titreEventLabel.setText(null);
        DateEventLabel.setText(null);
        TarifEventLabel.setText(null);
        TotalEventLabel.setText(null);
}

    @FXML
    private void modifierEvent(ActionEvent event) {
          ReservationEvent ev = TableView.getSelectionModel().getSelectedItem();

            
            
            String nb_place= PlaceAreserverComboBox.getSelectionModel().getSelectedItem().toString();
            int place = Integer.parseInt(nb_place);
            String total= TotalEventLabel.getText();
            float total_px= Float.parseFloat(total);

//            Date Date=new SimpleDateFormat("yyyy-MM-dd").parse(date);  
//            String ImagEvent= (ImageEventField.getText());
//            String tarif= TarifEventField.getText();
//            float tarifF = Float.parseFloat(tarif);
//   7


int capa=0;
int reser=0;
try {
     String requete = "SELECT capacite,nb_reservation FROM evenement  where ?=id  order by id desc";
     PreparedStatement pst = con.prepareStatement(requete);               
     pst.setInt(1, ev.getId());
     ResultSet e = pst.executeQuery();
     while (e.next()) {
  capa = e.getInt("capacite");
  reser= e.getInt("nb_reservation");
  System.out.println(capa + reser+ "\n");
}
    }
    catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
           
if(ev.getEtat()=="effectue")
{
     Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Attention ");
               alert.setHeaderText("Vous ne pouvez pas modifier votre réservation");
               alert.setContentText(" Cette événement a déjà eu lieu ");
               
               alert.showAndWait();
}

//
//else if(capa==reser)
//{
//    Alert alert = new Alert(Alert.AlertType.WARNING);
//               alert.setTitle("Attention ");
//               alert.setHeaderText("evenement plein");
//               alert.setContentText(" vous ne pouvez pas reserver  ");
//               
//               alert.showAndWait();
//}
//    
else
{
    
           
            ReservationEvent c = new ReservationEvent(ev.getId(), ev.getId_organisateur(), ev.getId_client(), ev.getId_event(),place,total_px,ev.getMode_paiement(),ev.getEtat());
        ServiceReservationEvent cs = new ServiceReservationEvent();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmer la modification");
            alert.setHeaderText("Vous souhaitez modifier la réservation à l'événement : " + ev.getTitre_event());
            alert.setContentText("Confirmez la modification ");
            Optional<ButtonType> btn = alert.showAndWait();
            if(btn.get()==ButtonType.OK)
            {
        cs.ModifierEvenement(c);
int cin = retourCin();
            List<ReservationEvent> lc = cs.ListReservationEvent1(cin);
        
        ObservableList<ReservationEvent> data =         
        FXCollections.observableArrayList(lc );
        TableView.setItems(data);
        
      viderChamps();    }
         else {alert.close();}   
      
}
    }

    @FXML
    private void TotalReservation(ActionEvent event) {
                 float total= CalculTotal();
      String totall= String.valueOf(total);
      TotalEventLabel.setText(totall);
    }

    @FXML
    private void AfficherInfo(MouseEvent event) {
            ReservationEvent ev = TableView.getSelectionModel().getSelectedItem();
         
        

                 
                 
        String titre= ev.getTitre_event();
        Date date= ev.getDate_event();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        String strDate = dateFormat.format(date);  
        

//        TypeEventField.setText(String.valueOf(ev.getType()));
        titreEventLabel.setText(titre);
        DateEventLabel.setText(strDate);
        TotalEventLabel.setText(String.valueOf(ev.getTotal()));
        
        
         float prix_unit= ev.getTotal()/ev.getNb_place();
         String taro= String.valueOf(prix_unit);
         
                 TarifEventLabel.setText(String.valueOf(taro));
                 
ObservableList<Integer> listee;
        listee = FXCollections.observableArrayList(1,2,3,4,5);
        PlaceAreserverComboBox.setItems(listee);


        if(ev.getNb_place()==1)
        PlaceAreserverComboBox.getSelectionModel().select(0);
        else if(ev.getNb_place()==2)
        PlaceAreserverComboBox.getSelectionModel().select(1);
         else if(ev.getNb_place()==3)
        PlaceAreserverComboBox.getSelectionModel().select(2);
         else if(ev.getNb_place()==4)
        PlaceAreserverComboBox.getSelectionModel().select(3);
         else if (ev.getNb_place()==5)
        PlaceAreserverComboBox.getSelectionModel().select(4);
        
        
         ServiceReservationEvent cs= new ServiceReservationEvent();

int cin = retourCin();
            List<ReservationEvent> lc = cs.ListReservationEvent1(cin);        
        ObservableList<ReservationEvent> data = FXCollections.observableArrayList(lc);
        TableView.setItems(data);
    }

    @FXML
    private void annulerReservatiionClient(ActionEvent event) {
        
          ReservationEvent ev = TableView.getSelectionModel().getSelectedItem();
            ServiceReservationEvent cs = new ServiceReservationEvent();
            if(ev==null)
            {
                 Alert a = new Alert(Alert.AlertType.WARNING);
               a.setTitle("Alerte !");
               a.setHeaderText("Selectionnez une réservation");
               a.setContentText("Vous devez choisir une réservation à annuler");
               
               a.showAndWait();  
            }
            else {
                
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Annulation événement ");
            alert.setHeaderText("Voulez vous annuler cette réservation");
            alert.setContentText("Confirmez l'anulation");
            Optional<ButtonType> btn = alert.showAndWait();
            if(btn.get()==ButtonType.OK )
            {
                if(ev.getEtat()=="effectue")
                {
                  Alert a = new Alert(Alert.AlertType.WARNING);
               a.setTitle("Alerte !");
               a.setHeaderText("Vous ne  pouvez pas annuler cette evenement");
               a.setContentText(" Cette événement a déja eu lieu");
               
               a.showAndWait();  
                }
                else 
                {
                    
                
                cs.SupprimerEvenement(ev);
int cin = retourCin();
            List<ReservationEvent> lc = cs.ListReservationEvent1(cin);
int reser=0;    
                    try {
     String requete = "SELECT nb_reservation FROM evenement  where ?=id  order by id desc";
     PreparedStatement pst = con.prepareStatement(requete);               
     pst.setInt(1, ev.getId_event());
     ResultSet e = pst.executeQuery();
     while (e.next()) {
  reser= e.getInt("nb_reservation");
  System.out.println(reser + "\n");
}
    }
    catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                    
        
                      try {
         String requete = "update `evenement`  set nb_reservation= ? where ? = id ";
            PreparedStatement pre = con.prepareStatement(requete);
            reser=reser-ev.getNb_place();
                       pre.setInt(1, reser);
                       pre.setInt(2,ev.getId_event() );

            

            pre.executeUpdate();
            System.out.println("nb rservation a levent modifier !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                      
        ObservableList<ReservationEvent> data =         
        FXCollections.observableArrayList(lc );
        TableView.setItems(data);
                System.out.println("Annulation effectue");
                 viderChamps();
                
            }}
            else {alert.close();}
            }
            
          
           
             
        
    }

    @FXML
    private void RetourInterfaceTherapeute(ActionEvent event) {
//         FXMLLoader loader = new FXMLLoader(getClass().getResource("CrudEvent.fxml"));
//        
//        
//        try {
//            Parent root = loader.load();
//            btnPageEvent.getScene().setRoot(root);
//            
//        } catch (IOException ex) {
//            Logger.getLogger(AfficherEventClientController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @FXML
    private void afficherarticle(MouseEvent event) {
    }

    @FXML
    private void afficherlivre(MouseEvent event) {
    }

    @FXML
    private void deconnecter(MouseEvent event) {
    }

    @FXML
    private void gotorecommandation(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("client.fxml"));
  
            Parent root = loader.load();
            btnRecommandation.getScene().setRoot(root);    
    }
    

   
}
