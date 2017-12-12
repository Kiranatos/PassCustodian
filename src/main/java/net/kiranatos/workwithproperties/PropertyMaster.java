package net.kiranatos.workwithproperties;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.kiranatos.PassPaths;


public class PropertyMaster {
    
    private static Map<String,String> propertyMap = new HashMap();
    
    static { readingPropertiesFile(); }
    
    /**
     * Создать конфигурационный Properу файл в той же директории, где находится программа
     * @throws IOException 
     */
    public static void createPropertiesFile() throws IOException {
        Properties p = new Properties();

        Iterator<Map.Entry<String,String>> it = propertyMap.entrySet().iterator();
        while ( it.hasNext() ) {
            Map.Entry<String, String> pair = it.next();
            p.setProperty(pair.getKey(), pair.getValue());            
        }       
        
        FileWriter writer = new FileWriter(PassPaths.CONFIGURATION_PATH);
        p.store(writer, "User's Configuration of Password Manager");
        writer.close();
    }
    
    /**
     * 
     * @param key
     * @param value 
     */
    public static void addProperty(String key, String value) {
        propertyMap.put(key, value);
    }
    
    /**
     * 
     */    
    public static void initializeDefaultProperty() {
        propertyMap.put("language", "en");
    }
    
    /**
     * Getter
     * @return 
     */
    public static Map<String, String> getPropertyMap() {
        return propertyMap;
    }
    
    /**
     * Getter
     * @return 
     */
    public static String getPropertyByKey(String key) {
        return propertyMap.get(key);
    }
    
    
    /**
     * 
     * @return
     * @throws Exception 
     */
    private static void readingPropertiesFile() {
        try {

            if ( ! new File(PassPaths.CONFIGURATION_PATH).exists() ) {            
                initializeDefaultProperty();
                createPropertiesFile();
            }      
            
            FileReader reader = new FileReader(PassPaths.CONFIGURATION_PATH);
            Properties p = new Properties();
            p.load(reader);
            
            Enumeration keys = p.propertyNames();            
            while(keys.hasMoreElements()) {
                String key = (String)keys.nextElement();
                propertyMap.put(key, p.getProperty(key));                
            }
            
            reader.close();
        } catch (IOException en) { Logger.getLogger(PropertyMaster.class.getName()).log(Level.SEVERE, null, en);   }        
    }
}
