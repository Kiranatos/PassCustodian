package net.kiranatos;

/**
 * Главный класс-менеджер для работы со словарем Мюлера и английскими словами
 */



import java.io.*;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.kiranatos.ancillary.PassException;

public class MullerWordsManager { 
    
    static final long serialVersionUID = 20170104;
    
    private String currentPath                      = PassPaths.FROM_MULLER_VOCABULARY_PATH;
    private static final InputStream pathStandart   = PassPaths.FROM_MULLER_VOCABULARY;
    private InputStream pathIO                      = pathStandart;
    
    private TreeSet<String> vocTreeSetCollection;
    private ArrayList<String> vocArrayListCollection;
    private String[] vocStringArray;    
    private int wordsQuantity;
    private static MullerWordsManager muller;
    
    public static int totalSingltonObject = 0;
    
    
    
    // ----------------------------------------------------- CONSTRUCTORS
    
    /**
     * get Singlton class for class MullerWordsManager
     * @return 
     */
    public static MullerWordsManager getMuller() {
        if (muller==null) {
            muller = new MullerWordsManager();
        }
        if (totalSingltonObject > 1) PassException.SingletonException(totalSingltonObject);
        
        return muller;
    }

    private MullerWordsManager () {
        totalSingltonObject++;
        
        MullerWords m = new MullerWords(null);
        this.vocStringArray = m.load(pathIO);
        this.wordsQuantity = vocStringArray.length;              
    }

    
   
    
    // ----------------------------------------------------- GETTERS
    
    /**
     * Получить массив слов в виде String[]
     * @return 
     */
    public String[] getWordsStringArray() {        
        return this.vocStringArray;
    }    
    
    /**
     * Получить массив слов в виде String[] заданного размера слов (int sizeOfWord)
     * 3 < n < 21
     * @return 
     */
    public String[] getWordsStringArray(int sizeOfWord) {
        int k=0;
        for (String s : this.vocStringArray)
            if (s.length() == sizeOfWord) k++;
        
        String[] listOfWords = new String[k];
        
        for (String s : this.vocStringArray)
            if (s.length() == sizeOfWord) listOfWords[--k] = s;
        
        return listOfWords;
    }    
    
    /**
     * Получить коллекцию слов в виде ArrayList<String>
     * @return 
     */
    public ArrayList<String> getWordsArrayList() {        
        vocArrayListCollection = new ArrayList<String>(Arrays.asList(vocStringArray));
        return this.vocArrayListCollection;
    }
    
    /**
     * Получить коллекцию слов в виде TreeSet<String> - отсортированная, без дубликатов
     * @return 
     */
    public TreeSet<String> getWordsTreeSet() {
        System.err.println("Проверить правильность получения TreeSet-а");
        vocTreeSetCollection = new TreeSet<String>(Arrays.asList(vocStringArray));
        return this.vocTreeSetCollection;
    }
    
        
    /**
     * Получить количество слов в списке класса MullerWordsManager
     * @return 
     */
    public int getQuantityOfWords() {
        return wordsQuantity;
    }    
    
    /**
     * Получить адрес текущего файла со словами
     * @return 
     */
    public String getPath() { return currentPath; }
    
    /**
     * Получить случайное слово произвольной длины     
     * @return
     */
    public String getRandomWord() {                
        return vocStringArray[ new Random().nextInt(wordsQuantity-1) ];
    }
    
    /**
     * Получить случайное слово задданой длины     
     * @return
     */
    public String getRandomWord(int sizeOfWord) {        
        if ((sizeOfWord > 3) && (sizeOfWord < 19)) {
            String[] s = getWordsStringArray(sizeOfWord);
            return s[ new Random().nextInt(s.length-1) ];
        }
        else return "";        
    }
    
    
    // ----------------------------------------------------- SETTERS
    
    /**
     * Установить адрес нового файла со словами
     * @return 
     */
    public void setPath(String path) { 
        currentPath = path;
        this.pathIO = Start.class.getClassLoader().getResourceAsStream(path); 
    }
    /**
     * Вернуть адрес файла со словами по умолчанию
     * @return 
     */
    public void setDefaultPath() { 
        currentPath = PassPaths.FROM_MULLER_VOCABULARY_PATH;
        this.pathIO = pathStandart; 
    }

           
        
    // ----------------------------------------------------- toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MullerWords(");
        for (int i=0; i < vocStringArray.length-1; i++) {
            sb.append(vocStringArray[i]);
            sb.append(", ");
        }
        sb.append(vocStringArray[vocStringArray.length-1]);
        sb.append(")");
            
        return sb.toString();
    } 
    
    // ----------------------------------------------------- ВРЕМЕННЫЕ    
    /**
     * Метод для создания Muller.dat
     * Для работы программы не нужен - используется только для создания словаря,
     * который будет зашит в джарник
     * @param way 
     */
    public static void createMullerFile(String way) throws FileNotFoundException {
        String[] str = null;
        Scanner file = new Scanner(new File(way));
        while ( file.hasNextLine() ) {  
            String inLine = file.nextLine();
            str = inLine.split(" ");            
        }
        file.close();
        
        MullerWords m = new MullerWords(str);
        m.save();
    }
}




/*  @Override
    public void writeExternal(ObjectOutput out) throws IOException {        
        out.writeInt(num);
        System.out.println("writed:" + this.num);
        for (int i=0; i< num; i++) {
            out.writeObject(vocStringArray[i]); 
            System.out.println("writed:" + vocStringArray[i]);
        }
        
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.vocStringArray = (String[])in.readObject();
        this.num = (int) in.readInt();
        for (int i=0; i < this.num; i++)
            vocStringArray[i] = in.readUTF();
    }*/


