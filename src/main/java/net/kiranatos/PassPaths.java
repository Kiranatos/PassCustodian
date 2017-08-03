package net.kiranatos;

/*ЗЫ: узнать лучше разницу getClassLoader() и getClass()
Start.class.getClass().getResourceAsStream(MAIN_WINDOW_PATH);
Start.class.getClassLoader().getResourceAsStream(MAIN_WINDOW_PATH);
*/

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class PassPaths {
    public final static String HOT_SECRETS_PATH = "HotSecrets.dat";
    
    public final static String FROM_MULLER_VOCABULARY_PATH = "Muller.dat";
    public final static InputStream FROM_MULLER_VOCABULARY = Start.class.getClassLoader().getResourceAsStream(FROM_MULLER_VOCABULARY_PATH);        
    
    public final static String MAIN_WINDOW_PATH = "/fxml/Display.fxml";    
    public final static InputStream MAIN_WINDOW_INPUTSTREAM = Start.class.getClass().getResourceAsStream(MAIN_WINDOW_PATH);
    
    public final static String INFO_WINDOW_PATH = "/fxml/InfoWindow.fxml";    
    private static InputStream INFO_WINDOW_INPUTSTREAM; // = Start.class.getClass().getResourceAsStream(INFO_WINDOW_PATH);
    
    /**
     * Метод создает каждый раз новый поток (для нового окошка), т.к. если его объявить final,
     * то новое окошко создастся только один раз - потом будет выбрасываться исключение, что поток закрыт!
    */
    public static InputStream getInfoWindowInputStream(){
        INFO_WINDOW_INPUTSTREAM = Start.class.getClass().getResourceAsStream(INFO_WINDOW_PATH);
        return INFO_WINDOW_INPUTSTREAM;
    }
    
    public final static String CSS_PATH_FOR_MAIN_WINDOW = "/styles/display.css";
    
    public final static InputStream FAVICON = Start.class.getClass().getResourceAsStream("/images/favicon.png");
    
    /**
     * 
     */
    public static String getSaveNameFile() {
        return "Passwords" + new SimpleDateFormat("yy-MM-dd-hh-mm-ss").format( new Date() ) + ".xlsx"; 
    }
   
}
