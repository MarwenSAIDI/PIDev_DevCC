/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientinterfaces;

import entities.Evenement;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ItemEventController {

    @FXML
    private ImageView img;
    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private Label dateEvent;
    @FXML
    private Label etatLabel;

 
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(evenement);
    }

    private Evenement evenement;
    private MyListener myListener;

    public void setData(Evenement evenement, MyListener myListener) {
        
        this.evenement = evenement;
        
        Date d=evenement.getDate_event();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");  
        String strDate = dateFormat.format(d);
        
        this.myListener = myListener;
        nameLabel.setText(evenement.getTitre());
        priceLable.setText(evenement.getLieu());
        dateEvent.setText(strDate);
        etatLabel.setText(evenement.getEtat());
        
        
        String imgg= evenement.getImage();
        String ch="/imgEvent/";
        String imgF= ch+imgg;
        
        Image imageF = new Image(getClass().getResourceAsStream(imgF));
        img.setImage(imageF);
        
    }
}
