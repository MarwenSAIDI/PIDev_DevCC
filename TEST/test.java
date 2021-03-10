/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TEST;



/**
 *
 * @author foura
 */
import static javafx.application.Application.launch;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class test extends Application {
    
public static Stage stg;
    public static final String CURRENCY = "DT";

    @Override
    public void start(Stage stage) throws Exception {
        this.stg=stage;
        Parent parent = (Parent) FXMLLoader.load(getClass().getResource(
                "/projet/test.fxml"));
        Scene scene = new Scene(parent,1800,1000);
        stage.setScene(scene);
      
                stage.setTitle("consultation");

        stage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
