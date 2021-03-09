 /*  
  * MyWeather App with OpenWeatherMap API  
  */  
 package gui;  
   
 import java.net.URL;  
 import java.util.logging.Level;  
 import java.util.logging.Logger;  
 import javafx.application.Application;  
 import javafx.beans.value.ObservableValue;  
 import javafx.fxml.FXML;  
 import javafx.fxml.FXMLLoader;  
 import javafx.scene.Scene;  
 import javafx.scene.control.Button;  
 import javafx.scene.control.Label;  
 import javafx.scene.control.Toggle;  
 import javafx.scene.control.ToggleButton;  
 import javafx.scene.control.ToggleGroup;  
 import javafx.scene.image.ImageView;  
 import javafx.scene.layout.AnchorPane;  
 import javafx.stage.Stage;  
 import gui.WeatherModel;  
import javafx.scene.Parent;
 import org.json.JSONException;  
   
 /**  
  *  
  * @author djordje gavrilovic  
  */  
 public class WeatherController extends Application {  
     
   WeatherModel wm;  
   int cityID=2464470;  
   
   @FXML  
   private ToggleGroup cityTg;  
   @FXML  
   private Label cityNameLbl;  
   @FXML  
   private Label descriptionLbl;  
   @FXML  
   private Label temperatureLbl;  
   @FXML  
   private Label pressureLbl;  
   @FXML  
   private Label humidityLbl;  
   @FXML  
   private Label windLbl;  
   @FXML  
   private Label iconLbl;  
   @FXML  
   private Button refreshBtn;  
   @FXML  
   private Label timeLbl;  
     
   @FXML  
   private void initialize() throws ClassNotFoundException, JSONException {  
       
     wm = new WeatherModel(cityID);  
     System.out.println(wm.toString());  
       
     cityNameLbl.textProperty().bindBidirectional(wm.cityNameProperty());  
     descriptionLbl.textProperty().bindBidirectional(wm.descriptionProperty());  
     temperatureLbl.textProperty().bindBidirectional(wm.temperatureProperty());  
     pressureLbl.textProperty().bindBidirectional(wm.pressureProperty());  
     humidityLbl.textProperty().bindBidirectional(wm.humidityProperty());  
     windLbl.textProperty().bindBidirectional(wm.windProperty());  
       
     iconLbl.setGraphic(new ImageView("http://openweathermap.org/img/w/"+wm.getIconID()+".png"));  
     iconLbl.setScaleX(1.5);  
     iconLbl.setScaleY(1.5);  
//     refreshBtn.setGraphic(new ImageView("../img/kiwi.png"));  
//     refreshBtn.setScaleX(0.8);  
  //   refreshBtn.setScaleY(0.8);  
     timeLbl.setText(new java.util.Date().toString());  
     cityTg.selectedToggleProperty().addListener((observable,  
       oldValue, newValue) -> {  
       try {  
         toggle(observable, oldValue, newValue);  
       } catch (JSONException ex) {  
         Logger.getLogger(WeatherController.class.getName()).log(Level.SEVERE, null, ex);  
       } catch (ClassNotFoundException ex) {  
         Logger.getLogger(WeatherController.class.getName()).log(Level.SEVERE, null, ex);  
       }  
     });  
   }  
     
//   @Override  
//   public void start(Stage primaryStage) throws Exception {      
////     URL fxmlUrl = getClass().getClassLoader().getResource("WeatherView.fxml");  
////     AnchorPane root = FXMLLoader.<AnchorPane>load(fxmlUrl);  
//     Parent root = FXMLLoader.load(getClass().getResource("WeatherView.fxml"));
//     Scene scene = new Scene(root);  
//     primaryStage.setScene(scene);  
//     primaryStage.setTitle("MyWeather");  
//     primaryStage.centerOnScreen();  
//     primaryStage.setResizable(false);  
//     primaryStage.setOpacity(0.9);  
//     primaryStage.show();  
//   }  
//     
   public static void main(String[] args) {launch(args);}  
     
   private void toggle(ObservableValue<? extends Toggle> observable,  
     Toggle oldValue, Toggle newValue) throws JSONException, ClassNotFoundException {  
       
       ToggleButton tBtn = (ToggleButton) newValue;  
       switch(tBtn.getText()){  
         case "Tunis":  
           cityID=2464470; // City IDs provided by OWM ( not WOEID )  
           break;  
         case "Sousse":  
           cityID=2464915;  
           break;  
         case "Monastir":  
           cityID=2524096;  
           break;  
       }  
       initialize();      
   }  

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
 }