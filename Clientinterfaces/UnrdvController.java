/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientinterfaces;
import static Clientinterfaces.ReservationrdvController.imgg;

import entities.Reservationconsultation;
import Clientinterfaces.reservationappel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author foura
 */
public class UnrdvController implements Initializable {

    @FXML
    private Label titre;
    @FXML
    private Label typerdv;
    @FXML
    private Label daterdv;
    @FXML
    private Label heurerdv;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
        private Reservationconsultation reserv;
        private reservationappel resapel;

    @FXML
    private void click(MouseEvent event) {
        resapel.onClickreserv(reserv);

    }
     public void setData(Reservationconsultation reserv,reservationappel resapel)
    {
        this.reserv=reserv;
        this.resapel=resapel;
        titre.setText(reserv.getEtat());
                typerdv.setText(reserv.getType());
                                daterdv.setText(reserv.getDate().toString());
   heurerdv.setText(reserv.getHeure());
   // String replaceString=cons.getImage().replaceAll("b" , "a");
   
   

   Image i;
        try {
            i= new Image(new FileInputStream(reserv.getImage()));
                               img.setImage(i);

        } catch (FileNotFoundException ex) {
            
        }


    }
    
}
