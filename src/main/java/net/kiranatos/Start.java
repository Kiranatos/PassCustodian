package net.kiranatos;

import java.io.File;
import java.io.IOException;
import net.kiranatos.gui.DisplayApplication;
import net.kiranatos.res.PasswordManager;

public class Start {    
    public static void main(String[] args) throws IOException {        
        
        if ( ! new File(PassPaths.HOT_SECRETS_PATH).exists() )
            PasswordManager.createFile();
        
        DisplayApplication.go(args);
        
        //HeartOfProgram hop = HeartOfProgram.getHeartOfProgramImstance();        
    }    
}