package net.kiranatos;

/**
 * Создать ещё принтовский метод, выводящий весь стек вызова метода
 */

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

public class Information {
    private static int def = 2;
    /**
     * Обёртка над System.out.print, которая добавляет к выводимой строке
     * информацию про класс и метод, где она была вызвана
     * @param str 
     */
    public static void print(String str){
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        String sClass = ste[def].getClassName(); 
        String sMetod = ste[def].getMethodName();                                                                                     //String sMetod = Thread.currentThread().getStackTrace()[2].getMethodName(); 
                                                                                    //for (int i=0; i<ste.length; i++) System.out.println(ste[i].getClassName() + " ");
        System.out.print("[" + sClass + " : " + sMetod + "] " + str);        
                                                                                    //Thread.currentThread().getName().getClass()
                                                                                    //Thread.currentThread().getStackTrace()[2].getClassName()
        def = 2;
    }
    
    /**
     * Обёртка над System.out.println, которая добавляет к выводимой строке
     * информацию про класс и метод, где она была вызвана
     * @param str 
     */
    public static void println(String str){
        def = 3;
        print(str+"\n");        
    }
    
    /**
     * Обёртка над System.out.print, которая добавляет к выводимой строке
     * информацию про класс и метод, где она была вызвана
     * @param str 
     */
    public static void printAllStack(){
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        for(int i = 0; i< ste.length; i++)
            System.out.println("[" + ste[i].getClassName() + " : " + ste[i].getMethodName() + "]");
    }
    
    /**
     * Метод для копирования строки в системный буфер обмена.
     * нужно просмотреть и изучить след. доки:
     * http://docs.oracle.com/javase/7/docs/api/java/awt/datatransfer/Clipboard.html
     * http://docs.oracle.com/javase/1.5.0/docs/api/java/awt/Toolkit.html
     * @param str 
     */
    public static void setClipboard(String str) {                
        StringSelection ss = new StringSelection(str);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        System.out.println("Содержимое буфера обмена:" + str);
    }    
}
