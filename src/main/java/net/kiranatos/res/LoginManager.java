package net.kiranatos.res;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.kiranatos.MullerWordsManager;


public class LoginManager {
    
    MullerWordsManager mwm = MullerWordsManager.getMuller();
    
    /**
     * Метод возвращает случайное слово, котороя заканчивается на "tor" или "man"
     * @return 
     */
    public String getRandomLogin() {
        String[] sM = mwm.getWordsStringArray();
        List<String> words = new ArrayList<String>();
        for (int i = 0; i<sM.length; i++) {
            String endWord = sM[i].substring(sM[i].length()-3);            
            if ( (endWord.equals("tor"))||(endWord.equals("man")) )
                words.add(sM[i]);
        }
        
        int index = new Random().nextInt(words.size()-1);
                
        return words.get(index);
    }   
}
