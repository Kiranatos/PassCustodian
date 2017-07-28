package net.kiranatos.res;

import java.util.*;
import java.io.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.kiranatos.Information;
import net.kiranatos.PassPaths;


public class PasswordManager {
    
    private static PasswordManager passwordManager;    
    private ArrayList<OnePassObject> arrayListOfOnePassObject;    // main keeper for all accounts
    private int passQuantity;
    
    private ObservableList<OnePassObject> observableListOfOnePassObject = FXCollections.observableArrayList();
    private String defaultPath = PassPaths.HOT_SECRETS_PATH;
    
    // *************************** CONSTRUCTOTRS
    /**
     * Singlton class PasswordManager
     * @return 
     */
    public static PasswordManager getPasswordManager() {
        if (passwordManager==null) {
            passwordManager = new PasswordManager();
        }             
        return passwordManager;
    }    

    private PasswordManager () {               
        PasswordList m = new PasswordList();
        this.arrayListOfOnePassObject = m.load(defaultPath);
        this.passQuantity = arrayListOfOnePassObject.size();
        createPassList(arrayListOfOnePassObject);
        Information.println("размер ArrayList списка объектов OnePassObject =  = " + arrayListOfOnePassObject.size());        
        Information.println("размер Observable списка объектов OnePassObject = " + observableListOfOnePassObject.size());
    }

    // *************************** GETTERS
    /**
     * Получить всю коллекцию логинов-паролей в формате OnePassObject
     * @return 
     */
    public ArrayList<OnePassObject> getListOfPasswords() {
        return arrayListOfOnePassObject;
    }
    
    /**
     * Получить коллекцию слов в виде ObservableList<OnePassObject> - для JavaFX
     * @return 
     */
    public ObservableList<OnePassObject> getObservableList() {        
        return this.observableListOfOnePassObject;
    }
    
    // *************************** SETTERS
    

    
    
    // *************************** МЕТОДЫ ПОИСКА
    /**
     * Метод поиска по логину
     */
    //public OnePassObject getIdByLogin ();

    /**
     * toString - потом нужно переделать
     * @return 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PasswordManager class INFO:\n");
        
        Iterator<OnePassObject> it = arrayListOfOnePassObject.iterator();
        while (it.hasNext()) {
            OnePassObject a = it.next();             
            sb.append(a).append("\n");            
        }
        return sb.toString();
    }    
    
    // *************************** OTHER METHODS:
    private void createPassList(ArrayList<OnePassObject> list)
    {
        Iterator<OnePassObject> it = list.iterator();
        while (it.hasNext())
        {
            observableListOfOnePassObject.add(it.next());
        }
    }
    
    /**
     * Добавление объеквта OnePassObject в список ObservableList<OnePassObject>
     * @param onePassObject 
     */
    public void add(OnePassObject onePassObject) {
        observableListOfOnePassObject.add(onePassObject);
    }
    
    public void saveToDefaultFile() {
        
    }
    
    
    // *************************** Тестовые методы
    /**
     * Временный метод для заполнения
     * Сначала загружается вся информация, которая есть в файле - эту работу проводит класс PasswordList
     * он же создает внутреннний список ArrayList<OnePassObject> listOfSecrets
     * потом в список добавляются новые значения.
     * и весь список записывается в файл
     */
    public static void createFile(){               
        PasswordList m = new PasswordList();        
        m.load(PassPaths.HOT_SECRETS_PATH);
        m.addToListOfPasswords("DartVader",  "LukeIamYourFaser", "www.porn.com",     "power@mail.com",    
                new String[]{"tag1","sw"}, 
                new String[]{"tag2","erotic"}, 
                new String[]{"tag3","music"});
        m.addToListOfPasswords("Yos", "пыщьпыщьололо",     "http://anime-yume.net/",    "yos@gmail.com",    
                new String[]{"tag1","anime"}, 
                new String[]{"tag2","yaoi"}, 
                new String[]{"tag3","hentai"});
        m.addToListOfPasswords("Render", "jam666",        "www.vk.com",    "renderlex@gmail.com",    
                new String[]{"tag1","social"}, 
                new String[]{"tag2","sport"}, 
                new String[]{"tag3","lazy"});
        m.save();
        
        //OnePassObject(String login, String password, String site, String mail, String[]... otherInfo)
    }
}
