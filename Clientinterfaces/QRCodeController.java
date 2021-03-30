package Clientinterfaces;




import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;

public class QRCodeController implements Initializable {

    @FXML
    private VBox root;

    /* Start generator attributes */
    @FXML
    private Label areaInputGen;

    @FXML
    private JFXTextField fieldWidthGen, fieldHeightGen;


    @FXML
    private ImageView imgQRCodeGen;

    private Image genQRCodeImg; // Generated QR Code image
    
    /* End generator attributes */

    /* Start reader attributes */
     public void setdetail(String TitreEventField) {
        this.areaInputGen.setText(TitreEventField);
    }

    private ImageView imgQRCodeReader;

    private TextArea areaResultReader;

    /* End reader attributes */

    private FileChooser imageChooser;
    private FileChooser pngImageSaveChooser;
    private FileChooser textSaveChooser;

    private JFXSnackbar toastError;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Make fields accept numbers only
        FXTools.fieldAcceptNumbersOnly(fieldWidthGen);
        FXTools.fieldAcceptNumbersOnly(fieldHeightGen);

        // Init toast error message
        toastError = new JFXSnackbar(root);

        // Init fields
        fieldWidthGen.setText("200");
        fieldHeightGen.setText("200");

        // Init file chooser
        pngImageSaveChooser = new FileChooser();
        pngImageSaveChooser.setTitle("Save Image");
        pngImageSaveChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image (*.png)", "*.png "));

        // Init File chooser for image
        imageChooser = new FileChooser();
        imageChooser.setTitle("Select Image File");
        imageChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*.png", "*.jpg", "*.jpeg"));

        // Init File chooser for saving text
        textSaveChooser = new FileChooser();
        textSaveChooser.setTitle("Select Saving File");
        textSaveChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File (*.txt)", "*.txt"));
    }

    /* Start generator methods */

    @FXML
    public void onGenerate() {
       

        genQRCodeImg = QRCodeEngine.encode(areaInputGen.getText(), Integer.parseInt(fieldWidthGen.getText()), Integer.parseInt(fieldHeightGen.getText()), "#EFEBE9", "#212121");
        if(genQRCodeImg != null) {
            imgQRCodeGen.setImage(genQRCodeImg);
        }
    }

    @FXML
    public void onExportGen() {
        if(genQRCodeImg == null)
            return;
        
        // Choose path of save
        File file = pngImageSaveChooser.showSaveDialog(RéservationClientEventController.primaryStage);
        // Write in a file
        if (file != null) {
            Utils.exportImage(genQRCodeImg, "png", file);
        }
    }

    /* End generator methods */

    /* Start reader methods */

    public void onLoadReader() {
        File file = imageChooser.showOpenDialog(RéservationClientEventController.primaryStage);

        if(file != null) {
            areaResultReader.setText(null);
            imgQRCodeReader.setImage(new Image(file.toURI().toString()));
        }
    }

    public void onRead() {
        if(imgQRCodeReader.getImage() == null) {
            toastError.show("Please, load an image to read it!", 1500);
            return;
        }

        String decodedText = QRCodeEngine.decode(imgQRCodeReader.getImage());
        if(decodedText == null) {
            toastError.show("Error decoding QR Code image!", 1500);
        } else
            areaResultReader.setText(decodedText);
    }

    public void onExportReader() {
        if(areaResultReader.getText() == null && areaResultReader.getText().trim().isEmpty())
            return;

        // Choose path of save
        File file = textSaveChooser.showSaveDialog(RéservationClientEventController.primaryStage);
        // Write in a file
        if (file != null) {
            Utils.exportText(areaResultReader.getText().trim(), file);
        }
    }

    /* End reader methods */
}
