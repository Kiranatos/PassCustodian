package net.kiranatos.gui;

import java.io.File;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.kiranatos.Information;
import net.kiranatos.PassPaths;


public class DisplayApplication extends Application {
    
    private static Stage primaryStage;
    private static final Locale DEFAULT_LOCALE = new Locale( "en" );
    private static final Locale CONFIGURATION_LOCALE = new Locale( PassPaths.getDefaultLocale() );

    @Override
    public void start(Stage primaryStage) throws Exception { 
        this.primaryStage = primaryStage;
        
//        String path = "../../../resources/fxml/Scene.fxml";
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
//        
//        String path = "../../../../resources/fxml/Display.fxml";
//        String path = "/fxml/Display.fxml";
//            if ((new File(path)).exists()) Information.println("\nФайл Существует!\n");
//            else Information.println("\nФайлa No!\n");
//        Parent root = FXMLLoader.load(getClass().getResource(path));
//        Parent root = (Parent) new FXMLLoader().load(getClass().getResourceAsStream(path));


        //ResourceBundle rb = ResourceBundle.getBundle("bundles.Locale_jp");
        

        FXMLLoader fxmlLoader = new FXMLLoader();        
        
        initLocalization(fxmlLoader);
        
        Parent root = (Parent) fxmlLoader.load(PassPaths.MAIN_WINDOW_INPUTSTREAM);
        
        primaryStage.setTitle(fxmlLoader.getResources().getString("title"));
        primaryStage.setMinHeight(626);
        primaryStage.setMinWidth(950);
        Scene scene = new Scene(root, 950, 680);
        scene.getStylesheets().add(PassPaths.CSS_PATH_FOR_MAIN_WINDOW);        
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(PassPaths.FAVICON));        
        primaryStage.show();   
        
    }
    
    public static void go (String[] args) {  launch(args);  }   

    public static Stage getPrimaryStage() {
        return primaryStage;
    }   
    
    
    private void initLocalization(FXMLLoader fxmlLoader){        
        try {
            Locale.setDefault(CONFIGURATION_LOCALE);
            fxmlLoader.setResources(ResourceBundle.getBundle("bundles.Locale"));                                // Localization
        } catch (RuntimeException e) {
            Information.println("Неправильная локализация в конфигурационном файле");
            Locale.setDefault(DEFAULT_LOCALE);
            fxmlLoader.setResources(ResourceBundle.getBundle("bundles.Locale"));            
        }        
    }
}
