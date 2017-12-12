package net.kiranatos.res;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import net.kiranatos.Information;

public class OnePassObjectProperty implements OPO {
    
    private OnePassObject obj;

    private StringProperty loginSP;
    private StringProperty passwordSP;
    private StringProperty siteSP;    
    private StringProperty mailSP;
    private StringProperty dateSP;
    private StringProperty secretKeySP;
    private StringProperty nameSurnameSP;
    private StringProperty vkSP;
    private StringProperty twitterSP;
    private StringProperty facebookSP;
    private StringProperty telegramSP;
    private StringProperty skypeSP;
    private StringProperty phoneSP;
    private StringProperty whatsappSP;
    private StringProperty tagsSP;
    private StringProperty otherInformationSP;
    
    // ----------------------------------- CONSTRUCTORS

    private OnePassObjectProperty() {}
    
    public OnePassObjectProperty(StringProperty login, StringProperty password, StringProperty site, 
            StringProperty mail, StringProperty date, StringProperty tags) {
        this.loginSP = login;
        this.passwordSP = password;
        this.siteSP = site;
        this.mailSP = mail;
        this.dateSP = date;
        this.tagsSP = tags;
    }    
    
    public OnePassObjectProperty(OnePassObject onePassObjectObject) {
        this.obj = onePassObjectObject;
    }
    
    // ----------------------------------- GETTERS
    public StringProperty getLoginSP() {            return loginSP;    }
    public StringProperty getPasswordSP() {         return passwordSP;    }
    public StringProperty getSiteSP() {             return siteSP;    }
    public StringProperty getMailSP() {             return mailSP;    }
    public StringProperty getDateSP() {             return dateSP;    }
    public StringProperty getSecretKeySP() {        return secretKeySP;    }
    public StringProperty getNameSurnameSP() {      return nameSurnameSP;    }
    public StringProperty getVkSP() {               return vkSP;    }
    public StringProperty getTwitterSP() {          return twitterSP;    }
    public StringProperty getFacebookSP() {         return facebookSP;    }
    public StringProperty getTelegramSP() {         return telegramSP;    }
    public StringProperty getSkypeSP() {            return skypeSP;    }
    public StringProperty getPhoneSP() {            return phoneSP;    }
    public StringProperty getWhatsappSP() {         return whatsappSP;    }
    public StringProperty getTagsSP() {             return tagsSP;    }
    public StringProperty getOtherInformationSP() { return otherInformationSP;    }  
    /*
        public StringProperty getProperty(String key) { //site login mail password TagsByString CreatedDate
        switch (key) {
        case "site" : { 
            if (siteSP==null) 
                siteSP = new SimpleStringProperty(this, key, obj); 
            return siteSP; 
        }
        case "login" : { 
            if (loginSP==null) 
                loginSP = new SimpleStringProperty(this, key, login); 
            return loginSP; 
        }
        case "mail" : { 
            if (mailSP==null) 
                mailSP = new SimpleStringProperty(this, key, mail); 
            return mailSP; 
        }
        case "password" : { 
            if (passwordSP==null) 
                passwordSP = new SimpleStringProperty(this, key, password); 
            return passwordSP; 
        }
        case "TagsByString" : { //getTagsByString()
            if (tagsSP==null) 
                tagsSP = new SimpleStringProperty(this, key, getTagsByString()); 
            return tagsSP; 
        }
        case "CreatedDate" : { 
            if (dateSP==null) 
                dateSP = new SimpleStringProperty(this, key, date); 
            return dateSP; 
        }
        default : return null;
        }
    }*/


    // ----------------------------------- SETTERS    
    public void setLoginSP(StringProperty loginSP) {                        this.loginSP = loginSP;    }
    public void setPasswordSP(StringProperty passwordSP) {                  this.passwordSP = passwordSP;    }
    public void setSiteSP(StringProperty siteSP) {                          this.siteSP = siteSP;    }
    public void setMailSP(StringProperty mailSP) {                          this.mailSP = mailSP;    }
    public void setDateSP(StringProperty dateSP) {                          this.dateSP = dateSP;    }
    public void setSecretKeySP(StringProperty secretKeySP) {                this.secretKeySP = secretKeySP;    }
    public void setNameSurnameSP(StringProperty nameSurnameSP) {            this.nameSurnameSP = nameSurnameSP;    }
    public void setVkSP(StringProperty vkSP) {                              this.vkSP = vkSP;    }
    public void setTwitterSP(StringProperty twitterSP) {                    this.twitterSP = twitterSP;    }
    public void setFacebookSP(StringProperty facebookSP) {                  this.facebookSP = facebookSP;    }
    public void setTelegramSP(StringProperty telegramSP) {                  this.telegramSP = telegramSP;    }
    public void setSkypeSP(StringProperty skypeSP) {                        this.skypeSP = skypeSP;    }
    public void setPhoneSP(StringProperty phoneSP) {                        this.phoneSP = phoneSP;    }
    public void setWhatsappSP(StringProperty whatsappSP) {                  this.whatsappSP = whatsappSP;    }
    public void setTagsSP(StringProperty tagsSP) {                          this.tagsSP = tagsSP;    }
    public void setOtherInformationSP(StringProperty otherInformationSP) {  this.otherInformationSP = otherInformationSP;    }
    
    // ----------------------------------- OTHER METHODS
        
    

    // ----------------------------------- toString - переделать !
    @Override
    public String toString() {
        return "OnePassObjectProperty { " + 
                "\n\t_______: \t\t"        + 5 +                 
                "\n\t}\n";
    }    
}


/*
       private String skype = "";
    private String whatsapp = "";
*/