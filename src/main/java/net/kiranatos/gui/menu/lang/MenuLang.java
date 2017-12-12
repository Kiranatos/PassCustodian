package net.kiranatos.gui.menu.lang;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import net.kiranatos.Information;
import net.kiranatos.workwithproperties.PropertyMaster;

public class MenuLang {
    
    private static List<MenuItem> menuLang = null;
    private static ResourceBundle rb = null;

           
    /**
     * Для выбора стандартных языков
     * 1) нужно сделать, чтобы изменения вступали сразу в рантайме, а не после перезагрузки
     * 2) чтобы добавлялись в список меню - новые, подключаемые языки из property-файлов
     * @param clickedCheckMenuItem 
     */
    public void actionDefaultLang(CheckMenuItem clickedCheckMenuItem) {       
        
        String id = clickedCheckMenuItem.getId();
        String lang = id.substring( id.length()-2, id.length() ).toLowerCase();
        
        for (MenuItem mi : menuLang) {
            if ( mi.getId().equals( id )) 
                clickedCheckMenuItem.setSelected(true);
            else if (mi instanceof CheckMenuItem)
                ( (CheckMenuItem)mi ).setSelected(false);                
        }       
        
        
        // Record choosed language to Main Property Configuration File
        try {
            PropertyMaster.addProperty("language", lang);
            PropertyMaster.createPropertiesFile();
        } catch (IOException ex) { Logger.getLogger(MenuLang.class.getName()).log(Level.SEVERE, null, ex); }
    }
    
    /**
     * Для выбора пользовательских языков из property-файла
     */
    public void actionAnothertLang() {
        Information.println(" NOT READY !!! ");
        try {
            PropertyMaster.addProperty("language", "NULL");
            PropertyMaster.addProperty("path_to_new_language", "NULL");
            PropertyMaster.createPropertiesFile();
        } catch (IOException ex) { Logger.getLogger(MenuLang.class.getName()).log(Level.SEVERE, null, ex); }
    }
    
    
    /**
     * Поставить галочку напротив языка из конфигурационного файла
     * метод нужно вызывать только после сеттеров: setMenuItemList и setResourceBundle
     */
    public static void setSelected() {
        String rbStr = rb.getLocale().getLanguage();        
        
        for (MenuItem mi : menuLang) {
            if (mi instanceof CheckMenuItem) {
                String id = mi.getId();
                String lang = id.substring( id.length()-2, id.length() ).toLowerCase();                
                if ( lang.equals( rbStr )) {
                    ( (CheckMenuItem)mi ).setSelected(true);
                } else { ( (CheckMenuItem)mi ).setSelected(false); }
                
            }
        }       
    }
    
    
    //--------------------------------------- Setters
    public static void setMenuItemList(List<MenuItem> list) {
        MenuLang.menuLang = list;
    }

    public static void setResourceBundle(ResourceBundle rb) {
        MenuLang.rb = rb;
    }
    
}
