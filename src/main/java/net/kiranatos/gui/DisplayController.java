package net.kiranatos.gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import net.kiranatos.HeartOfProgram;
import net.kiranatos.Information;
import net.kiranatos.ancillary.CheckBoxException;
import net.kiranatos.listeners.ChoiceBoxLengthPassListener;
import net.kiranatos.listeners.ChooseMailAndLoginListener;
import net.kiranatos.res.PasswordManager;

public class DisplayController implements Initializable {
    
    private HeartOfProgram hop = HeartOfProgram.getHeartOfProgramImstance();
    private PasswordManager pm = PasswordManager.getPasswordManager();
    private ObservableManager observableManager = new ObservableManager();
    private static ActionEvent aeStatic;
    
    
    public void initialize(URL url, ResourceBundle rb) {
        Information.printAllStack();
                
        siteTextField.setPromptText         ("Введите название сайта");
        hop.setSiteTextField(siteTextField);
                
        generateLoginTextField.setPromptText("Введите логин");
        hop.setGenerateLoginTextField(generateLoginTextField);
        
        generatePassTextField.setPromptText ("Сгенерируйте пароль");
        hop.setGeneratePassTextField(generatePassTextField);
        
        inputNewMail.setPromptText          ("Введите почту");
        hop.setInputNewMail(inputNewMail);
        
        inputTags.setPromptText             ("Введите теги, через запятую");
        hop.setInputTags(inputTags);
        
        
        
        // Для Таблицы         
        siteTableColumn.setCellValueFactory(observableManager.getColumnForTable("site"));        
        loginTableColumn.setCellValueFactory(observableManager.getColumnForTable("login"));
        //numberTableColumn.setCellValueFactory(new PropertyValueFactory<OnePassObject, String>("login"));
        emailTableColumn.setCellValueFactory(observableManager.getColumnForTable("mail"));
        passTableColumn.setCellValueFactory(observableManager.getColumnForTable("password"));
        tagsTableColumn.setCellValueFactory(observableManager.getColumnForTable("TagsByString"));
        dateTableColumn.setCellValueFactory(observableManager.getColumnForTable("CreatedDate"));
        
        mainTable.setItems(observableManager.getAllObservableListForTable());
        
        
        //Для ChoiceBox choiceBoxLengthPass
        choiceBoxLengthPass.setValue(10); /* отображаемое значение должно быть установлено ранее, чем установлен ObservableList,
        также это значение ДОЛЖНО совпадать со значенеим внутри установленного позже ObservableList-а */
        choiceBoxLengthPass.setItems(observableManager.getListForChoiceBoxLengthPass());
        choiceBoxLengthPass.setTooltip(new Tooltip("Выберите длину пароля!"));
        choiceBoxLengthPass.getSelectionModel().selectedIndexProperty().addListener(new ChoiceBoxLengthPassListener(choiceBoxLengthPass));
        //choiceBoxLengthPass
        
        
        //Для ComboBox chooseLogin        
        chooseLogin.setItems(observableManager.getListForComboBoxLogin());
        chooseLogin.setValue(observableManager.getListForComboBoxLogin().get(0));
        chooseLogin.setVisibleRowCount(5);
        chooseLogin.setTooltip(new Tooltip("Выберите использованный ранее логин!"));
        chooseLogin.getSelectionModel().selectedIndexProperty().addListener(new ChooseMailAndLoginListener(chooseLogin));
        
        //Для ComboBox chooseMail
        chooseMail.setItems(observableManager.getListForComboBoxMail());
        chooseMail.setValue(observableManager.getListForComboBoxMail().get(0));
        chooseMail.setVisibleRowCount(5);
        chooseMail.setTooltip(new Tooltip("Выберите Ваш E-mail!"));
        chooseMail.getSelectionModel().selectedIndexProperty().addListener(new ChooseMailAndLoginListener(chooseMail));
    }
    
    // ----------------------------------------------------------- BUTTONS
    
    @FXML
    private Button generatePassButton;     
    public void startListenerGeneratePassButton (ActionEvent actionEvent) {        
        actionEventToHeartOfProgram(actionEvent);
    }
    
    @FXML
    private Button generateLoginButton;
    public void startListenerGenerateLoginButton (ActionEvent actionEvent) {
        actionEventToHeartOfProgram(actionEvent);
    }
     
    @FXML
    private Button copyToBufferLogin;
    public void startListenerCopyToBufferLogin (ActionEvent actionEvent) {        
        actionEventToHeartOfProgram(actionEvent);
    }
    
    @FXML
    private Button copyToBufferPassword;
    public void startListenerCopyToBufferPassword (ActionEvent actionEvent) {
        actionEventToHeartOfProgram(actionEvent);
    }
    
    @FXML
    private Button saveInfo;
    public void startListenerSaveInfo (ActionEvent actionEvent) {        
        actionEventToHeartOfProgram(actionEvent);
    }
    
    @FXML
    private Button clearAll;
    public void startListenerClearAll (ActionEvent actionEvent) {        
        actionEventToHeartOfProgram(actionEvent);
    }
    
    @FXML
    private Button pasteFromBufferSite;
    public void startListenerPasteFromBufferSite (ActionEvent actionEvent) {
        actionEventToHeartOfProgram(actionEvent);
    }

    @FXML
    private Button saveToExcelFile;
    public void startListenerSaveToExcelFile (ActionEvent actionEvent) {
        actionEventToHeartOfProgram(actionEvent);
    }
    
    @FXML
    private Button loadFromExcelFile;
    public void startListenerLoadFromExcelFile (ActionEvent actionEvent) {
        actionEventToHeartOfProgram(actionEvent);
    }
    
    // ----------------------------------------------------------- TEXT FIELDS
    
    @FXML
    private TextField generatePassTextField;    
    public void startListenerGeneratePassTextField (ActionEvent e) {
        System.out.print("  Field ");        
    }
    
    @FXML
    private TextField siteTextField;
    public void startListenerSiteTextField (ActionEvent e) {
        System.out.print(" startListenerSiteTextField ");
    }    
    public void startListenerSiteTextFieldKeyPressed (KeyEvent e) {        
        System.out.print(" key " + e.getCode().toString());        
    }
    
    @FXML
    private TextField generateLoginTextField;
    public void startListenerGenerateLoginTextField (ActionEvent e) {
        System.out.print(" startListenerGenerateLoginTextField ");
    }    
    
    @FXML
    private TextField inputTags;
    public void startListenerInputTags (ActionEvent e) {
        System.out.print(" startListenerInputTags ");
    }
    
    @FXML
    private TextField inputNewMail;
    public void startListenerInputNewMail (ActionEvent e) {
        System.out.print(" startListenerInputNewMail ");
    }
    
    
    // ----------------------------------------------------------- CHECK BOXES
    
    @FXML
    private CheckBox checkBoxNumbers;
    public void startListenerCheckBoxNumbers (ActionEvent actionEvent) {        
        actionEventToHeartOfProgram(actionEvent);
    }
    
    @FXML
    private CheckBox checkBoxWords;
    public void startListenerCheckBoxWords (ActionEvent actionEvent) {        
        actionEventToHeartOfProgram(actionEvent);
    }
    
    @FXML
    private CheckBox checkBoxDate;
    public void startListenerCheckBoxDate (ActionEvent actionEvent) {        
        actionEventToHeartOfProgram(actionEvent);
    }
    
    // ----------------------------------------------------------- COMBO BOXES
    @FXML
    private ComboBox chooseLogin;
    
    @FXML
    private ChoiceBox choiceBoxLengthPass;
    
    @FXML
    private ComboBox chooseMail;
    
    // ----------------------------------------------------------- TABLE VIEW
    
    @FXML
    private TableView mainTable;
    public void startListenerMainTable (ActionEvent actionEvent) {        
        System.out.print(" startListenerMainTable ");
    }
    
    @FXML
    private TableColumn numberTableColumn;
    
    @FXML
    private TableColumn siteTableColumn;
    
    @FXML
    private TableColumn loginTableColumn;
    
    @FXML
    private TableColumn emailTableColumn;
    
    @FXML
    private TableColumn passTableColumn;
    
    @FXML
    private TableColumn tagsTableColumn;
    
    @FXML
    private TableColumn dateTableColumn;       
    
    // ----------------------------------------------------------- PRIVATE METODS
    
    private void actionEventToHeartOfProgram (ActionEvent ae) {
        Object source = ae.getSource();        
        //if (!(source instanceof Button)) return; // если нажата не кнопка - выходим из метода        
        aeStatic = ae;
        
        //Кнопки
        if (source instanceof Button) {
            Button clickedButton = (Button) source;
            hop.startEvent(clickedButton.getId());
        }
        //Чекбоксы
        else if (source instanceof CheckBox) {
            correctCheckBoxFlags();
            CheckBox clickedCheckBox = (CheckBox) source;         
            try {
                hop.startEvent(clickedCheckBox);
            } catch (CheckBoxException ex) {
                Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Возможная ошибка в классе: " + this.getClass().getSimpleName());
            }
        }
        
        //else throw new Exception();
        
        
        
    }

    
    public static ActionEvent getAeStatic() {
        return aeStatic;
    }
    
    /**
     * Метод для корректировки правильности установленных галочек на чекбоксах генератора паролей.
     * логику нужно будет взять из PasswordGenerator;
     */
    private void correctCheckBoxFlags() {
        
    }
}
