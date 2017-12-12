package net.kiranatos.gui;

import java.util.Iterator;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import net.kiranatos.res.OnePassObject;
import net.kiranatos.res.PasswordManager;

/**
 * Класс-Mенеджер для работы с Observable коллекциями
 * их обработкой и редактированием
 * для передачи информации в DisplayController
 * Класс создан мною, а не JavaFX Scene Builder 2.0
 */
public class ObservableManager {
    
    private ObservableList<OnePassObject> mainObservableList;
    private ObservableList<String> logins = FXCollections.observableArrayList();
    private ObservableList<String> mails = FXCollections.observableArrayList();

    public ObservableManager() {
        PasswordManager pm = PasswordManager.getPasswordManager();
        mainObservableList = pm.getObservableList();
    }
    
    /**
     * Метод генерирует Observable список числовых значений 6..30 для ChoiceBox (длина пароля).
     */
    public ObservableList<Integer> getListForChoiceBoxLengthPass() {
        
        Integer [] a = new Integer [25];
        
        for (int i=0; i<a.length; i++)
            a[i] = i+6;
        
        ObservableList<Integer> test = FXCollections.observableArrayList(a);
        
        return test;
    }
    
    
    /**
     * Нужно разобраться - метод-слушатель для каждой отдельной колоники, позволяющий
     * сортировать данные в таблице
     * @param nameOfColumn - название колонки
     * @return 
     */    /*
    public Callback getColumnForTable(String nameOfColumn) {
        Callback<
                TableColumn.CellDataFeatures <OnePassObject, String>, 
                ObservableValue<String>
                > value;
        
        value = new PropertyValueFactory<
                OnePassObject, String
                >(nameOfColumn);
        
        return value;
    }*/
    public Callback getColumnForTable(String nameOfColumn) {
        return new Callback<TableColumn.CellDataFeatures<OnePassObject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<OnePassObject, String> cellData) {
                
                return cellData.getValue().getProperty(nameOfColumn);
            }
        };
    }
    
    
    
    /**
     * Метод возвращает полный Observable список (с логинами, паролями, сайтами и тд)
     * для таблицы. Изначально в других классах использовался метод getObservableList() класса PasswordManager
     * но было решено перенести его сюда, а отсюдова брать уже дальше. Чтобы можно было обработать тут необходимую информацию.
     * @return 
     */
    public ObservableList<OnePassObject> getAllObservableListForTable() {
        return mainObservableList;
    }
    
    
    /**
     * Метод возвращает Observable список Логинов, взятых из общего полного списка паролей OnePassObject
     * Нужно будет потом дописать метод так, чтобы не пробегало каждый раз итератором список, а инициализировало только раз
     * а потом брало уже проинициализированный список.
     * + дописать контроль количества позиций, в случае добавления нового поля
     * @return 
     */
    public ObservableList<String> getListForComboBoxLogin() {
        
        if (mails.size() < 1) {
            
            Iterator<OnePassObject> it = mainObservableList.iterator();
            while ( it.hasNext() ) { 
                OnePassObject a = it.next();
                String s = a.getLogin();
                if (!mails.contains(s)) { mails.add(s); }
                //Information.println(mails.size() + " " + mainObservableList.size() + " " + s);
            }
        }
        
        return mails;
    }
    
    /**
     * Метод возвращает Observable список E-mail'ов, взятых из общего полного списка паролей OnePassObject
     * Нужно будет потом дописать метод так, чтобы не пробегало каждый раз итератором список, а инициализировало только раз     * 
     * а потом брало уже проинициализированный список.
     * + дописать контроль количества позиций, в случае добавления нового поля
     * @return 
     */
    public ObservableList<String> getListForComboBoxMail() {
        
        if (logins.size() < 1) {
            
            Iterator<OnePassObject> it = mainObservableList.iterator();
            while ( it.hasNext() ) { 
                OnePassObject a = it.next();
                String s = a.getMail();                
                if (!logins.contains(s)) { logins.add(s); }
                //Information.println(logins.size() + " " + mainObservableList.size() + " " + s);
                //Information.printAllStack();
            }
        }
        
        return logins;
    }
    
}
