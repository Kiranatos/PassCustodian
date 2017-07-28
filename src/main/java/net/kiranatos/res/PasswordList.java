package net.kiranatos.res;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import net.kiranatos.Information;
import net.kiranatos.PassPaths;


public class PasswordList {
    
    private ArrayList<OnePassObject> listOfSecrets;
    
    // SAVE
    public void save() {
        try (
                FileOutputStream f = new FileOutputStream(PassPaths.HOT_SECRETS_PATH);
                ObjectOutputStream objS = new ObjectOutputStream(f);
                ){
            
            objS.writeObject(listOfSecrets);
            
        }catch (FileNotFoundException ex) {
            Information.println("CLASS: MullerWordsManager - FILE NOT FOUNDED !");
            Logger.getLogger(PasswordList.class.getName()).log(Level.SEVERE, null, ex);        
        } catch (IOException ex) {
            Information.println("CLASS: MullerWordsManager - Unknown IOException");
            Logger.getLogger(PasswordList.class.getName()).log(Level.SEVERE, null, ex);        
        } 
    }
    
    /**
     * Загрузка файла HotSecrets.dat
     * Нужно добавить проверку на его существование.
     * @param path
     * @return 
     */
    public ArrayList<OnePassObject> load(String path) {
        
        File file = new File(path);
        if (file.exists()) {        
            try (
                    FileInputStream f = new FileInputStream(path);
                    ObjectInputStream objS = new ObjectInputStream(f);
                    ){
                
                listOfSecrets = (ArrayList<OnePassObject>)objS.readObject();                
            
            } catch (FileNotFoundException ex) {
                Information.println("CLASS: MullerWords - FILE NOT FOUNDED !");
                Logger.getLogger(PasswordList.class.getName()).log(Level.SEVERE, null, ex);        
            } catch (ClassNotFoundException ex) {
                Information.println("CLASS: MullerWords - CLASS NOT FOUNDED IN FILE " + path);
                Logger.getLogger(PasswordList.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Information.println("CLASS: MullerWords - Unknown IOException");
                Logger.getLogger(PasswordList.class.getName()).log(Level.SEVERE, null, ex);        
            }
        }
        if (listOfSecrets == null) listOfSecrets = new ArrayList<OnePassObject>();
        
        return listOfSecrets;
    }
    
    // *************************** GETTERS
    public ArrayList<OnePassObject> getListOfPasswords() {
        return listOfSecrets;
    }
    
    // *************************** SETTERS
    public void setListOfPasswords(ArrayList<OnePassObject> listOfSecrets) {
        this.listOfSecrets = listOfSecrets;
    }
    
    
    public void addToListOfPasswords(String login, String password, String site, String mail, String[]... otherInfo) {        
        addToListOfPasswords(new OnePassObject(login, password, site, mail, otherInfo));
    }    
    
    public void addToListOfPasswords(OnePassObject opo) {
        if (listOfSecrets == null) {
            listOfSecrets = new ArrayList< OnePassObject >();
        }
        listOfSecrets.add(opo);
    }
}
