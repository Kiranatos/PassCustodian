/**
 * Класс руководит работой и обменом данных между GUI и самой программой
 */
package net.kiranatos;


import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.kiranatos.ancillary.CheckBoxException;
import net.kiranatos.res.LoginManager;
import net.kiranatos.gui.infowindow.*;
import net.kiranatos.res.OnePassObject;
import net.kiranatos.res.PasswordGenerator;
import net.kiranatos.res.PasswordManager;


public class HeartOfProgram {

    private static HeartOfProgram hop;
    private PasswordGenerator passwordGenerator;
    
    private OnePassObject opo;
    
    // элементы GUI JavaFX
    private TextField generatePassTextField;    
    private TextField siteTextField;    
    private TextField generateLoginTextField;
    private TextField inputTags;    
    private TextField inputNewMail;

    private ChoiceBox chooseLogin;        
    private ChoiceBox chooseMail;
    
    /* Flags for Generate Pass Button and Pass Area */
    private CheckBox checkBoxNumbers;    
    private CheckBox checkBoxWords;    
    private CheckBox checkBoxDate;
    
    private ChoiceBox choiceBoxLengthPass;
    
    private boolean numbersForGeneratePassButton = false;
    private boolean wordsForGeneratePassButton = true;
    private boolean dateForGeneratePassButton = true;
    private int lengthForGeneratePassButton = 10;
    /* Flags for Generate Pass Button and Pass Area */
    
    
    // ----------------------------------------------------- CONSTRUCTORS    
    /**
     * get Singlton class for class HeartOfProgram
     * @return 
     */
    public static HeartOfProgram getHeartOfProgramImstance() {
        if (hop == null) {
            hop = new HeartOfProgram();
        }        
        
        return hop;
    }

    private HeartOfProgram () {                
        passwordGenerator = PasswordGenerator.getInstance(lengthForGeneratePassButton);        
    }      
    
    // ----------------------------------------------------- SETTERS
    public void setGeneratePassTextField(TextField generatePassTextField) {
        this.generatePassTextField = generatePassTextField;
    }
    
    public void setSiteTextField(TextField siteTextField) {
        this.siteTextField = siteTextField;
    }

    public void setGenerateLoginTextField(TextField generateLoginTextField) {
        this.generateLoginTextField = generateLoginTextField;
    }

    public void setInputTags(TextField inputTags) {
        this.inputTags = inputTags;
    }

    public void setInputNewMail(TextField inputNewMail) {
        this.inputNewMail = inputNewMail;
    }

    // ----------------------------------------------------- GETTERS
    /**
     * Геттер для передачи значений объекта OnePassObject     
     * созданного в главном окне программы.
     * в класс InfoWindowController
     * @return 
     */
    public OnePassObject getOnePassObject() {
        return opo;
    }
    
    // ----------------------------------------------------- METODS
    
    // ----------------------------------------------------- ПЕРЕГРУЖЕННЫЕ МЕТОДЫ-СОБЫТИЯ
    
    /**
     * Для создания события по полученному id-объекта.
     * изначально создавался для обработки всех объектов, но по причине избыточности кода
     * сейчас используется только для кнопок. А метод решено было перегрузить.
     * @param action 
     */
    public void startEvent(String action) {
        Information.println("Нажата кнопка по id: " + action);
        switch (action) {
            case "genPass" : {
                passwordGenerator.setCheckboxDates(dateForGeneratePassButton);
                passwordGenerator.setCheckboxNumbers(numbersForGeneratePassButton);
                passwordGenerator.setCheckboxWords(wordsForGeneratePassButton);
                passwordGenerator.setLengthPass(lengthForGeneratePassButton);
                String password = passwordGenerator.generatePassword(); //получение сгенерированного пароля
                generatePassTextField.setText(password);
                break;
            }
            
            case "generateLoginButton" : {
                generateLoginTextField.setText(new LoginManager().getRandomLogin());
                break;
            }
            
            case "copyToBufferPassword" : {
                Information.setClipboard(generatePassTextField.getText());
                break;
            }
            case "copyToBufferLogin" : {
                Information.setClipboard(generateLoginTextField.getText());
                break;
            }
            
            
            case "saveInfo" : {
                saveData();
                break;
            }
            default: { Information.print("Erorr");/*throw new CheckBoxException();*/ }
        }        
    }
    
    /**
     * Для создания события по чекбоксам в главном сердце программы, для расспределния ролей
     * @param checkBox
     * @throws CheckBoxException 
     */
    public void startEvent(CheckBox checkBox) throws CheckBoxException {
        Information.println(checkBox.getId());
        switch (checkBox.getId()) {
            case "checkBoxNumbers" : {
                this.checkBoxNumbers = checkBox;
                numbersForGeneratePassButton = checkBoxNumbers.isSelected();    
                break;
            }
            
            case "checkBoxWords" : {
                this.checkBoxWords = checkBox;
                wordsForGeneratePassButton = checkBoxWords.isSelected();
                break;
            }
            
            case "checkBoxDate" : {
                this.checkBoxDate = checkBox;
                dateForGeneratePassButton = checkBoxDate.isSelected();
                break;
            }
            
            default: { throw new CheckBoxException(); }
        }        
    }
    
    /**
     * Для создания события по чекбоксам в главном сердце программы, для расспределния ролей
     * @param checkBox
     * @throws CheckBoxException 
     */
    public void startEvent(ChoiceBox choiceBox, int index) {
        Information.println(choiceBox.getId());
        ObservableList obL = choiceBox.getItems();
        Object value = obL.get(index);
        switch (choiceBox.getId()) {
            case "choiceBoxLengthPass" : {
                this.choiceBoxLengthPass = choiceBox;
                lengthForGeneratePassButton = (int)value;  //Доработать instanceof
                Information.println(String.valueOf(lengthForGeneratePassButton) + " (Выбранное значение)");               
                break;
            }

            default: { /*throw new CheckBoxException();*/ }
        }
    }
    
    /**
     * Для создания события по чекбоксам в главном сердце программы, для расспределния ролей
     * @param checkBox
     * @throws CheckBoxException 
     */
    public void startEvent(ComboBox comboBox, int index) {
        Information.println(comboBox.getId());
        ObservableList obL = comboBox.getItems();
        Object value = obL.get(index);
        switch (comboBox.getId()) {
            case "chooseLogin" : {                
                generateLoginTextField.setText((String)value); //Доработать instanceof
                break;
            }
            case "chooseMail" : {
                inputNewMail.setText((String)value); //Доработать instanceof
                break;
            }
            default: { Information.print("Erorr");/*throw new CheckBoxException();*/ }
        }
    }
    
    
    // ----------------------------------------------------- Private METODS
    private void saveData() {
        //Создание объекта OnePassObject
        String login = generateLoginTextField.getText();
        String password = generatePassTextField.getText();
        String site = siteTextField.getText();
        String mail = inputNewMail.getText();
        String[] tags = inputTags.getText().split(" ");
        String[][] tagPairs = new String[tags.length][2];
        for (int i =0; i<tags.length; i++) {
            tagPairs[i][0] = "tag" + i;
            tagPairs[i][1] = tags[i];
        }
        opo = new OnePassObject(login, password, site, mail, tagPairs);
        Information.println(opo.toString());
        
        //Создание информационного окна
        InfoWindowApplication iwa = new InfoWindowApplication();        
        try { 
            iwa.showDialog();
        } catch (Exception ex) { Logger.getLogger(HeartOfProgram.class.getName()).log(Level.SEVERE, null, ex); }
        
        //Сохранение значений в список
        
        PasswordManager pm = PasswordManager.getPasswordManager();
        pm.add(opo);
    }
}
