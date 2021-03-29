/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientinterfaces;

import entities.Recommandation;
import java.net.URL;
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
public class ItemController {

    @FXML
    private ImageView img;
    @FXML
    private Label titreLabel;
    @FXML
    private Label ecrivainLable;

 
    

    private Recommandation recommandation;
    @FXML
    private Label labeldesc;

    public void setData(Recommandation recommandation) {
        
        this.recommandation = recommandation;
        

        
        titreLabel.setText("Titre:"+" "+recommandation.getTitre());
        ecrivainLable.setText("Ecrivain:"+" "+recommandation.getEcrivain());
        labeldesc.setText("Description:"+" "+recommandation.getDescription());
        
        String imgg= recommandation.getImage();
        String ch="/images/";
        String imgF= ch+imgg;
        
        Image imageF = new Image(getClass().getResourceAsStream(imgF));
        img.setImage(imageF);
        
    }
}
