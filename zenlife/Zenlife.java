/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zenlife;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author foura
 */
public class Zenlife extends Application {
        public static final String CURRENCY = "DT";

    @Override
    public void start(Stage stage) throws IOException {
    
            Parent root = FXMLLoader.load(getClass().getResource("/authentification/authentification.fxml"));
//            Parent root = FXMLLoader.load(getClass().getResource("GestionRéservationClientEvent.fxml"));

            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
