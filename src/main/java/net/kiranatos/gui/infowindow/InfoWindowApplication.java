package net.kiranatos.gui.infowindow;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.kiranatos.Information;
import net.kiranatos.PassPaths;
import net.kiranatos.gui.DisplayController;

public class InfoWindowApplication {

    private Stage editDialogStage;
    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private Stage mainStage;
    
    
    public void showDialog() throws Exception {
        
        setMainStage();
        
        if (editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Information");
            editDialogStage.setMinHeight(210);
            editDialogStage.setMinWidth(560);
            editDialogStage.setResizable(false);
            
            //fxmlLoader.setLocation(getClass().getResource("fxml//InfoWindow.fxml"));            
            //fxmlEdit = (Parent)fxmlLoader.load(getClass().getResourceAsStream("/fxml/InfoWindow.fxml"));
            //Information.println(PassPaths.INFO_WINDOW_INPUTSTREAM.toString());
            fxmlEdit = (Parent)fxmlLoader.load(PassPaths.getInfoWindowInputStream());
            
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage);
        }

        editDialogStage.showAndWait(); // для ожидания закрытия окна
        
    }
    
    public static void main(String[] args) throws IOException, Exception {
        new InfoWindowApplication().showDialog();
    }
    
    
    private void setMainStage() {
        ActionEvent a1 = DisplayController.getAeStatic();
        Scene s1 = ((Node)a1.getSource()).getScene();
        
        this.mainStage = (Stage)s1.getWindow();
    }
}
