/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientinterfaces;

import entities.Produit;
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
 * @author Occurence
 */
public class ItemProduitController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private ImageView img;

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click(MouseEvent event) {
        MyListenerProduit.onClickListener(prod);
    }
   private MyListenerProduit MyListenerProduit;
    
    private Produit prod;
    
    public void setData(Produit prod, MyListenerProduit MyListenerProduit) {
        
        this.prod = prod;
        
        
        
        this.MyListenerProduit = MyListenerProduit;
        nameLabel.setText(prod.getNom());
        
        
        
        String imgg= prod.getImage();
        String ch="/images/";
        String imgF= ch+imgg;
        
        Image imageF = new Image(getClass().getResourceAsStream(imgF));
        img.setImage(imageF);        
    }
    
    public ImageView getImg(){
        return img;
    }
    
}
