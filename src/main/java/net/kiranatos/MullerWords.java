package net.kiranatos;

/**
 * Класс для сохранения архива слов
 */

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MullerWords implements Serializable {
    
    private String[] words;
    public MullerWords(String[] w) { words = w;}
    
    /**
     * Теоретически: файл словаря Мюллера будет зашит в джарник, поэтому
     * сохранятся новые слова в него не будут и этот метод используется только для 
     * времееного стиатического метода createMullerFile(String way) в классе 
     * MullerWordsManager, который призван для того, чтобы создавть новый файл
     * словаря Muller.dat
     */
    public void save() {
        try (
                FileOutputStream f = new FileOutputStream("src\\res\\Muller.dat");
                ObjectOutputStream objS = new ObjectOutputStream(f);
                ){
            
            objS.writeObject(words);
            
        }catch (FileNotFoundException ex) {
            System.out.println("CLASS: MullerWordsManager - FILE NOT FOUNDED !");
            Logger.getLogger(MullerWordsManager.class.getName()).log(Level.SEVERE, null, ex);        
        } catch (IOException ex) {
            System.out.println("CLASS: MullerWordsManager - Unknown IOException");
            Logger.getLogger(MullerWordsManager.class.getName()).log(Level.SEVERE, null, ex);        
        } 
    }
    
    public String[] load(InputStream resource) {
    //public String[] load(String path) {
        
        try (
                //FileInputStream f = new FileInputStream(path);                
                ObjectInputStream objS = new ObjectInputStream(resource);
                ){
            
        words = (String[])objS.readObject();     
            
        } catch (FileNotFoundException ex) {
            System.err.println("CLASS: MullerWords - FILE NOT FOUNDED !");
            Logger.getLogger(MullerWordsManager.class.getName()).log(Level.SEVERE, null, ex);        
        } catch (ClassNotFoundException ex) {
            System.err.println("CLASS: MullerWords - CLASS NOT FOUNDED IN FILE " + resource.toString());
            Logger.getLogger(MullerWordsManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.err.println("CLASS: MullerWords - Unknown IOException");
            Logger.getLogger(MullerWordsManager.class.getName()).log(Level.SEVERE, null, ex);        
        }    
        
        return words;
    }
    
}
