/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientinterfaces;

import Clientinterfaces.ReservationrdvController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import entities.consultation;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Clientinterfaces.consultationappel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author foura
 */
public class UneconsultationController implements Initializable {

    @FXML
    private Label titre;
    @FXML
    private Label descr;
    @FXML
    private Label emplac;
    @FXML
    private Label prx;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
         private consultation cons;
    private consultationappel consultationappels;

    @FXML
    private void click(MouseEvent event) {
                consultationappels.onClickCons(cons);

    }
    
    public void setData(consultation cons,consultationappel consultationappel)
    {
        this.cons=cons;
        this.consultationappels=consultationappel;
        titre.setText(cons.getTitre());
                descr.setText(cons.getDescription());
                                emplac.setText(cons.getEmplacement());
   prx.setText(cons.getPrix()+"DT");
   // String replaceString=cons.getImage().replaceAll("b" , "a");
   
   

   Image i;
        try {
            i= new Image(new FileInputStream(cons.getImage()));
                                img.setImage(i);

        } catch (FileNotFoundException ex) {
        }
    /* menhouni:
      Image image = new Image("file:///C:/Users/foura/OneDrive/Documents/template/Instagram/src/instagram/pics/avatar.jpg");
imgtest1.setImage(image);   */
   // Image image=new Image(getClass().getResourceAsStream(cons.getImage()));

    }

    
    
}
