package net.kiranatos.gui.infowindow;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.kiranatos.HeartOfProgram;
import net.kiranatos.Information;
import net.kiranatos.res.OnePassObject;

public class InfoWindowController implements Initializable {
    
    private OnePassObject opo;

    public void setOnePassObject(OnePassObject opo) {
        this.opo = opo;
    }
    
    // ----------------------------------------------------------- BUTTONS    
    @FXML
    private Button bufferSiteButton, bufferLoginButton, bufferPassButton, bufferMailButton, 
            bufferTagsButton, bufferPhoneButton, bufferNameButton, bufferInfoButton,
            okInfoWindow;
    
    // ----------------------------------------------------------- TextField
    @FXML
    private TextField textField_Site, textField_Login, textField_Pass, 
            textField_Mail, textField_Tags, textField_Phone, 
            textField_NameSurname, textField_Info;
    private List<TextField> listTextField;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listTextField = new ArrayList<>(Arrays.asList(textField_Site, textField_Login, textField_Pass,
                textField_Mail, textField_Tags, textField_Phone, 
                textField_NameSurname, textField_Info));
        
        opo = HeartOfProgram.getHeartOfProgramImstance().getOnePassObject();
        
        textField_Site.setText(opo.getSite());        
        
        
        textField_Login.setText(opo.getLogin());
        textField_Pass.setText(opo.getPassword());
        textField_Mail.setText(opo.getMail());
        textField_Tags.setText(opo.getTagsByString());
        textField_Phone.setText(opo.getPhoneNumber());
        textField_NameSurname.setText(opo.getNameSurname());
        textField_Info.setText(opo.getOtherInformation());
        
        for (TextField tf : listTextField) {
            tf.setEditable(false);
            //tf.setDisable(true);
        }
    }    
    
    
    
    
    public void startListenerInfoWindowButtons (ActionEvent ae) {                
        Object source = ae.getSource();        
        if (!(source instanceof Button)) return; // если нажата не кнопка - выходим из метода
        
        Button clickedButton = (Button) source;
        String str = clickedButton.getId();
        Information.println(str);
        
        switch (str) {
            case "bufferSiteButton" : {                
                Information.setClipboard(textField_Site.getText());
                break;
            }
            case "bufferLoginButton" : {                
                Information.setClipboard(textField_Login.getText());
                break;
            }
            case "bufferMailButton" : {                
                Information.setClipboard(textField_Mail.getText());
                break;
            }            
            case "bufferPassButton" : {                
                Information.setClipboard(textField_Pass.getText());
                break;
            }
            case "bufferTagsButton" : {                
                Information.setClipboard(textField_Tags.getText());
                break;
            }
            case "bufferPhoneButton" : {                
                Information.setClipboard(textField_Phone.getText());
                break;
            }
            case "bufferNameButton" : {                
                Information.setClipboard(textField_NameSurname.getText());
                break;
            }
            case "bufferInfoButton" : {                
                Information.setClipboard(textField_Info.getText());
                break;
            }
            case "okInfoWindow" : {                
                actionClose(ae);
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
