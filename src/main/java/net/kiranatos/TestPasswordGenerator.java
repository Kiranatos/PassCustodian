package net.kiranatos;

import java.text.SimpleDateFormat;
import java.util.Date;
import net.kiranatos.res.PasswordGenerator;

public class TestPasswordGenerator {
    
    public static void main(String[] args) {
        System.out.println("Тестирование генератора паролей:");
        
        PasswordGenerator p1 = PasswordGenerator.getInstance(30);
        
        System.out.println("=================Слова и цыфры:");
        p1.setCheckboxWords(true);
        p1.setCheckboxNumbers(true);        
        for (int i=0; i<10; i++) {
            p1.generatePassword();
            System.out.println(p1);
        }
        
        System.out.println("=================Только цыфры:");
        p1.setCheckboxWords(false);        
        for (int i=0; i<10; i++) {
            p1.generatePassword();
            System.out.println(p1);
        }
        
        System.out.println("=================Слова и дата:");
        p1.setCheckboxDates(true);
        p1.setCheckboxWords(true);
        for (int i=0; i<10; i++) {
            p1.generatePassword();
            System.out.println(p1);
        }
        
        System.out.println("=================Только слова:");
        p1.setCheckboxDates(false);        
        for (int i=0; i<10; i++) {
            p1.generatePassword();
            System.out.println(p1);
        }
        
        System.out.println("=================Только дата:");
        p1.setCheckboxDates(true);
        p1.setCheckboxWords(false);
        for (int i=0; i<10; i++) {
            p1.generatePassword();
            System.out.println(p1);
        }
        System.out.println("=================Только буквы:");
        p1.setCheckboxDates(false);        
        for (int i=0; i<10; i++) {
            p1.generatePassword();
            System.out.println(p1);
        }
                
        
        System.out.println("\n\n====Конец Теста====");
        MullerWordsManager mwm = MullerWordsManager.getMuller();
        String s = mwm.getRandomWord();
        System.out.println(s);        
        s = mwm.getRandomWord(5);
        System.out.println(s);        
        String[] sma = mwm.getWordsStringArray(2);
        System.out.println(sma.length);
        for (String str : sma)
            System.out.print(" " + str);
        System.out.println();
        
           
    }    
}
