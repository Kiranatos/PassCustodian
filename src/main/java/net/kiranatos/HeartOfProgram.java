/**
 * Класс руководит работой и обменом данных между GUI и самой программой
 */
package net.kiranatos;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.kiranatos.ancillary.CheckBoxException;
import net.kiranatos.file.ExcelReader;
import net.kiranatos.gui.DisplayApplication;
import net.kiranatos.res.LoginManager;
import net.kiranatos.gui.infowindow.*;
import net.kiranatos.file.ExcelWriter;
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
    
    private OnePassObject selectedOPO = null;
    
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
    
    public void setSelectedOPO(OnePassObject selectedOPO) {
        this.selectedOPO = selectedOPO;
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
            case "pasteFromBufferSite" : {
                String temp = Information.getClipboard();                
                siteTextField.setText(temp!=null ? temp : "...Error...");
                break;
            }
            case "clearAll" : {                
                siteTextField.clear();
                generatePassTextField.clear();
                generateLoginTextField.clear();
                inputNewMail.clear();
                inputTags.clear();
                break;
            }
            case "saveToExcelFile" : {                
                FileChooser fileChooser = new FileChooser();
                
                //Set extension filter
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");
                fileChooser.getExtensionFilters().add(extFilter);
                fileChooser.setTitle("Сохранить список паролей в эксель-файл");
                fileChooser.setInitialFileName( PassPaths.getSaveNameFile() );
                
                //Show save file dialog
                File file = fileChooser.showSaveDialog(DisplayApplication.getPrimaryStage());
                if(file != null){
                    ExcelWriter xlsxWriter = new ExcelWriter(PasswordManager.getPasswordManager().getListOfPasswords());
                    xlsxWriter.setFile(file);    
                    xlsxWriter.writeAndSaveDataList();
                }
                break;
            }
            case "loadFromExcelFile" : {                
                FileChooser fileChooser = new FileChooser();
                
                //Set extension filter
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");
                fileChooser.getExtensionFilters().add(extFilter);
                fileChooser.setTitle("Загрузить список паролей из эксель-файла");                
                
                //Show save file dialog
                File file = fileChooser.showOpenDialog(DisplayApplication.getPrimaryStage());
                if(file != null){
                    Information.println("Был открыт файл: " + file.getName());
                    ExcelReader xlsxReader = new ExcelReader(file);
                    List<OnePassObject> readedTempoList = xlsxReader.getArrayListOPO();                       
                    
                    for (OnePassObject opoo : readedTempoList) {
                        System.out.println(opoo);
                    }
                    PasswordManager.getPasswordManager().setListOfPasswords(readedTempoList);
                    PasswordManager.getPasswordManager().saveToDefaultFile();
                }
                
                break;
            }            
            case "saveInfo" : {
                saveData();
                break;
            }
            case "deleteButtonFromTable" : {            
                Information.println("deleteButtonFromTable");
                if ( isSelectedOPO (this.selectedOPO) )
                    PasswordManager.getPasswordManager().delete(this.selectedOPO);                
                break;
            }
            case "dublicateButtonFromTable" : {
                if ( isSelectedOPO (this.selectedOPO) )
                    PasswordManager.getPasswordManager().add(this.selectedOPO);                
                break;
            }
            case "editButtonFromTable" : {
                // SomeMetod
                break;
            }
            case "showInformationButtonFromTable" : {
                // SomeMetod
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
        String[] tags = inputTags.getText().split(",");
        for (int i = 0; i < tags.length; i++) { tags[i] = tags[i].trim(); }
        
        String[][] tagPairs = new String[tags.length][2];
        for (int i =0; i<tags.length; i++) {
            tagPairs[i][0] = "tag" + i;
            tagPairs[i][1] = tags[i];
        }

        /* Если нечего не заполнено, то не создавать новый объект. Есть баг с пробелами в строках логина, пароля и т.д.
        Возможно стоит добавить и для них проверку. Или же создать приватные метод для проверки String-овых значений,
        полученных из окошек выше. С присвоением им null  в случае не удовлетворения условий. <- тогда if можно переделать в по-проще
        */
        if (!(  
                ((login==null)|(login.length()==0)) &
                ((password==null)|(password.length()==0)) &
                ((site==null)|(site.length()==0)) &
                ((mail==null)|(mail.length()==0)) &
                ((tags[0].length()==0)&&(tags.length==1))
                )) {
            opo = new OnePassObject(login, password, site, mail, tagPairs);
            Information.println(opo.toString());
            
            //Создание информационного окна
            InfoWindowApplication iwa = new InfoWindowApplication();        
            try { 
                iwa.showDialog();
            } catch (Exception ex) { Logger.getLogger(HeartOfProgram.class.getName()).log(Level.SEVERE, null, ex); }
            
            //Сохранение значений в список
            
            PasswordManager pm = PasswordManager.getPasswordManager();
            pm.add(opo);}
    }
    
    /**
     * Метод для определения была ли выбрана строка в таблице при нажатии на кнопку
     * @param opo
     * @return 
     */
    private boolean isSelectedOPO(OnePassObject opo) {
        if(opo == null){
            //DialogManager.showInfoDialog(resourceBundle.getString("error"), resourceBundle.getString("select_person"));
            Information.println("В таблице нечего не было выбрано");
            return false;
        }
        return true;
    }
}
