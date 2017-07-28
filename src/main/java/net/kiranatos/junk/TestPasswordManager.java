package net.kiranatos.junk;

import net.kiranatos.res.PasswordManager;



public class TestPasswordManager {
    
    public static void main(String[] args) {
        
       PasswordManager.createFile();
        
      PasswordManager pm = PasswordManager.getPasswordManager();
                
       System.out.println(pm);
    }
    
}
