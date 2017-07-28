package net.kiranatos.res;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import net.kiranatos.MullerWordsManager;


public class PasswordGenerator {
    
    private int lengthPass;
    private boolean checkboxNumbers;
    private boolean checkboxWords;
    private boolean checkboxDates;
    private String password;
    private static PasswordGenerator pg;

    // CONSTRUCTORS
    private PasswordGenerator() { }
    
    public static PasswordGenerator getInstance(int lengthPass) {
        if (pg == null) pg = new PasswordGenerator();
        
        pg.setLengthPass(lengthPass);
        
        return pg;
    }
    
    
    // GETTERS
    public int getLengthPass() {
        return lengthPass;
    }

    public boolean isCheckboxNumbers() {
        return checkboxNumbers;
    }

    public boolean isCheckboxWords() {
        return checkboxWords;
    }

    public boolean isCheckboxDates() {
        return checkboxDates;
    }

    public String getPassword() {
        return password;
    }
    
    
    //SETTERS
    public void setLengthPass(int lengthPass) {
        this.lengthPass = lengthPass;
    }

    public void setCheckboxNumbers(boolean checkboxNumbers) {
        if ( (checkboxNumbers) & isCheckboxDates() ) setCheckboxDates(false);
        
        this.checkboxNumbers = checkboxNumbers;
    }

    public void setCheckboxWords(boolean checkboxWords) {
        this.checkboxWords = checkboxWords;
    }

    public void setCheckboxDates(boolean checkboxDates) {
        if ( (checkboxDates) & isCheckboxNumbers() ) setCheckboxNumbers(false);
        
        this.checkboxDates = checkboxDates;
    }

    // to String
    @Override
    public String toString() {
        return this.password;
    }
    
    // METODS
    /**
     * Метод генерирует пароль в зависисмости от параметров, которые задаются сначала в методе getInstance();
     * @return 
     */
    public String generatePassword() {
        StringBuilder sb = new StringBuilder();
        
        if ( isCheckboxNumbers() & isCheckboxWords() ) {            // ++- r5t8r9
            while ( sb.length() < getLengthPass() ) {
                char c = (char)( (int)( (Math.random()*76) + 47 ) );
                
                if (Character.isLetterOrDigit(c)) sb.append(c);
            }
            
        } else if ( isCheckboxNumbers() & !isCheckboxWords() ) {    // +-- 55142124
            
            while ( sb.length() < getLengthPass() ) {                
                int k = (int)(Math.random()*10) ;                
                sb.append(k);
            }
            
        } else if ( isCheckboxDates() & isCheckboxWords() ) {       // -++ nikon110317
            Date d = new Date();
            SimpleDateFormat f = new SimpleDateFormat("ddMMyy");            
            sb.append( getWords( getLengthPass()-f.format(d).length() ) );            
            sb.append(f.format(d));
        
        } else if ( isCheckboxWords() ) {                           // -+- nikon
            if ((getLengthPass()>3) & (getLengthPass()<18)) {
                sb.append( MullerWordsManager.getMuller().getRandomWord(getLengthPass()) );
            } else {
                sb.append( getWords(getLengthPass()) );
            }
            
        } else if ( isCheckboxDates() ) {                           // +-- 011217
            Date d = new Date();
            SimpleDateFormat f;
            int u = getLengthPass();
            if (u > 12) u =12;
            switch ( u ) {
                case 4 : 
                    f = new SimpleDateFormat("ddMM");
                    break;
                case 8 : 
                    f = new SimpleDateFormat("ddMMyyyy");
                    break;
                case 12 : 
                    f = new SimpleDateFormat("HHmmssddMMyy");
                    break;
                default : f = new SimpleDateFormat("ddMMyy");
            }
            sb.append(f.format(d));
            
        } else {                                                //gkjhdskghdsughsfdgkujsdrtusdkfh
            sb = getSymbols(getLengthPass()); 
        }
        
        this.password = sb.toString();
        return this.password;
    }
    // вспомагательный метод для generatePassword(), возвращает набор букв
    private StringBuilder getSymbols (int n) {
        StringBuilder sb = new StringBuilder();
        while ( sb.length() < n ) {                
                char c = (char)( (int)( (Math.random()*59) + 64 ) );
                
                if (Character.isLetter(c)) sb.append(c);
            }
        return sb;
    }
    // вспомагательный метод для generatePassword(), возвращает набор слов, если пароль слишком долгий или короткий
    private StringBuilder getWords (int n) {
        StringBuilder sb = new StringBuilder();

        MullerWordsManager mwm = MullerWordsManager.getMuller();
        while ( sb.length() < n ) {
            int f = n-sb.length();
            if (f < 4) { 
                sb.append(getSymbols(f).toString().toUpperCase());
            }
            else { 
                int b =(int)((Math.random()*13) + 4);
                if (b <= f) {
                String word = mwm.getRandomWord(b);                
                sb.append(word.substring(0, 1).toUpperCase());
                sb.append(word.substring(1));}
            } 
        }        
        return sb;
    }
    
    
}

/*
6
---
qweryu

--+
110317

-+-
nikon

-++ (ограничение по количеству >7)
nikon110317

+-- (при числах не работает дата)
987654

+-+ (не активное)


++-
r5t8f5

+++
*/

/*
3 0
4 2849
5 4433
6 6474
7 7166
8 6864
9 5917
10 4472
11 2926
12 1749
13 977
14 441
15 201
16 84
17 28
18 9
19 1
20 1
21 0
*/