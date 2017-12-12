package net.kiranatos.res;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import net.kiranatos.Information;
import net.kiranatos.PassPaths;


public class PasswordList {
    
    private List<OnePassObject> listOfSecrets;
    
    /**
     * Сохранение в файл HotSecrets.dat, если его нет - то создастся,
     * Внимание: метод не дописывает, а переписывает файл заново, на основании созданного программой листа ArrayList<OnePassObject> @paramlistOfSecrets
     */
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
     * Проверка на его существование добавлена!
     * @param path
     * @return 
     */
    public List<OnePassObject> load(String path) {
        
        File file = new File(path);
        if (file.exists()) {        
            try (
                    FileInputStream f = new FileInputStream(path);
                    ObjectInputStream objS = new ObjectInputStream(f);
                    ){
                Information.println("trying to read...");
                listOfSecrets = (ArrayList<OnePassObject>)objS.readObject(); 
                //testAllParametrs();
                Information.println("was read...");
            
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
    public List<OnePassObject> getListOfPasswords() {
        return listOfSecrets;
    }
    
    // *************************** SETTERS
    public void setListOfPasswords(List<OnePassObject> listOfSecrets) {
        this.listOfSecrets = listOfSecrets;
    }
    
    /**
     * Создать и добавить в список объект OnePassObject, по переданным значениям пароля, логина, почты, сайта и т.д.
     */
    public void addToListOfPasswords(String login, String password, String site, String mail, String[]... otherInfo) {        
        addToListOfPasswords(new OnePassObject(login, password, site, mail, otherInfo));
    }    
    
    /**
     * Добавить в список уже созданный объект OnePassObject в другом классе
     */
    public void addToListOfPasswords(OnePassObject opo) {
        if (listOfSecrets == null) {
            listOfSecrets = new ArrayList< OnePassObject >();
        }
        listOfSecrets.add(opo);
    }
    
//    private void testAllParametrs(){
//        for (OnePassObject opo : listOfSecrets) {
//            System.out.println("\t\t" + opo);
//            System.out.println("\t\t" + opo.getCreatedDate());
//            System.out.println("\t\t" + opo.getLogin());
//            System.out.println("\t\t" + opo.getMailSSP());
//            System.out.println("\t\t" + opo.getMailSSP().getName());
//            System.out.println("\t\t" + opo.getMailSSP().getValue());
//            System.out.println("\t\t" + opo.getMailSSP().get());
//            System.out.println("\t\t" + opo.getMailSSP().getValueSafe());
//            System.out.println("\t\t" + opo.getMailSSP().toString());
//            System.out.println("\t\t" + opo);
//        }       
//        
//    }
}
