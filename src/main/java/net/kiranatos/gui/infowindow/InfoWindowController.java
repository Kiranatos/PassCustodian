package net.kiranatos.gui.infowindow;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import net.kiranatos.HeartOfProgram;
import net.kiranatos.Information;
import net.kiranatos.res.OnePassObject;

public class InfoWindowController implements Initializable {
    
    private OnePassObject opo;

    public void setOnePassObject(OnePassObject opo) {
        this.opo = opo;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        opo = HeartOfProgram.getHeartOfProgramImstance().getOnePassObject();
        siteLabel.setText(opo.getSite());
        passLabel.setText(opo.getPassword());
        loginLabel.setText(opo.getLogin());
        mailLabel.setText(opo.getMail());
        tagsLabel.setText(opo.getTagsByString());
    }    
    
    
    // ----------------------------------------------------------- BUTTONS    
    @FXML
    private Button bufferSiteButton;
    
    @FXML
    private Button bufferLoginButton;
    
    @FXML
    private Button bufferPassButton;
    
    @FXML
    private Button bufferMailButton;
    
    @FXML
    private Button okInfoWindow;
    // ----------------------------------------------------------- LABELS
    @FXML
    private Label passLabel;
    
    @FXML
    private Label siteLabel;
    
    @FXML
    private Label loginLabel;
    
    @FXML
    private Label mailLabel;
    
    @FXML
    private Label tagsLabel;
    
    public void startListenerInfoWindowButtons (ActionEvent ae) {                
        Object source = ae.getSource();        
        if (!(source instanceof Button)) return; // если нажата не кнопка - выходим из метода
        
        Button clickedButton = (Button) source;
        String str = clickedButton.getId();
        Information.println(str);
        
        switch (str) {
            case "bufferSiteButton" : {                
                Information.setClipboard(siteLabel.getText());
                break;
            }
            case "bufferLoginButton" : {                
                Information.setClipboard(loginLabel.getText());
                break;
            }
            case "bufferMailButton" : {                
                Information.setClipboard(mailLabel.getText());
                break;
            }
            case "okInfoWindow" : {                
                actionClose(ae);
                break;
            }
            case "bufferPassButton" : {                
                Information.setClipboard(passLabel.getText());
                break;
            }
            default: { Information.println("Erorr");/*throw new CheckBoxException();*/ }
        }
    }
    
    /* В видеоуроках, этот метод напрямую цепляли в JavaFX Scene Builder 2.0 в onAction
    на кнопки OK и Cancel. У меня же этот метод вызывается через общий метод 
    startListenerInfoWindowButtons для всех кнопок  */
    private void actionClose(ActionEvent actionEvent) {
        //Этот вариант позволяет завкрыть текущее окно, без знания того, кто его открыл
        Node source = (Node) actionEvent.getSource(); //Node из общего графа
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }    
}
