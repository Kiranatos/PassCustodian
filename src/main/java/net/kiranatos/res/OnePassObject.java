package net.kiranatos.res;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OnePassObject implements Serializable{
    
    private Map<String, String> secrets = new HashMap <>();
    private List<String> tags = new ArrayList<String>();
    private List<String[]> otherInformation = new ArrayList<>();
    
    private String login = "";
    private String password = "";
    private String site = "";
    private String mail = "";
    private String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    private String secretKey = "";
    private String nameSurname = "";
    private String vk = "";
    private String twitter = "";
    private String facebook = "";
    private String telegram = "";
    private String skype = "";
    private String phone = "";
    private String whatsapp = "";
    
    // ----------------------------------- CONSTRUCTORS
    private OnePassObject() {}
    public OnePassObject(String login, String password, String site, String mail, String[]... otherInfo) {
        int indexOfOtherInfo = 1;
        this.login = login;
        this.password = password;
        this.site = site;
        this.mail = mail;               
                 
        for (int i = 0; i < otherInfo.length; i++ ) {
            if(otherInfo[i][0].startsWith("tag")) { 
                tags.add(otherInfo[i][1]);
                continue;
            } else if (otherInfo[i][0].toLowerCase().startsWith("namesurname")) {
                nameSurname = otherInfo[i][1];
                continue;
            } else if (otherInfo[i][0].toLowerCase().startsWith("vk.com")) {
                vk = otherInfo[i][1];
                continue;
            } else if (otherInfo[i][0].toLowerCase().startsWith("phone")) {
                phone = otherInfo[i][1];
                continue;
            } else if (otherInfo[i][0].toLowerCase().startsWith("facebook.com")) {
                facebook = otherInfo[i][1];
                continue;
            }
            
            otherInformation.add(new String[] {otherInfo[i][0] , otherInfo[i][1]});
        }        
    }
    
    // ----------------------------------- GETTERS
    public String getLogin()        { return this.login; }    
    public String getPassword()     { return this.password;  }
    public String getSite()         { return this.site; }
    public String getMail()         { return this.mail; }
    public String getCreatedDate()  { return this.date; }
    public String getNameSurname()          { return this.nameSurname; }    
    public String getVKSiteAccount()        { return this.vk; }
    public String getTwitterSiteAccount()   { return this.twitter; }
    public String getFacebookSiteAccount()  { return this.facebook; }
    public String getSkype()                { return this.skype; }
    public String getWhatsApp()             { return this.whatsapp; }
    public String getTelegram()             { return this.telegram; }
    public String getPhoneNumber()          { return this.phone; }
    public String getTagsByString() {
        String temp = tags.toString();
        return temp.substring(1, temp.length()-1);
    }
    public String getOtherInformation() {
        StringBuilder sb = new StringBuilder();
        Iterator<String[]> it = otherInformation.iterator();
        while (it.hasNext()) { 
            String[] temp = it.next(); 
            sb.append(temp[0]).append("=").append(temp[1]).append("; ");
        }
        return sb.toString();
    }
    
    

    // ----------------------------------- SETTERS    
    public void addTag          (String tag)            { this.tags.add(tag); }
    public void setLogin        (String login)          { this.login = login;    }
    public void setPassword     (String password)       { this.password = password;    }
    public void setSite         (String site)           { this.site = site; }
    public void setMail         (String mail)           { this.mail = mail;    }
    public void setSecretKey    (String secretKey)      { this.secretKey = secretKey;    }
    public void setNameSurname  (String nameSurname)    { this.nameSurname = nameSurname;    }
    public void setVk           (String vk)             { this.vk = vk;    }
    public void setTwitter      (String twitter)        { this.twitter = twitter;    }
    public void setFacebook     (String facebook)       { this.facebook = facebook;   }
    public void setTelegram     (String telegram)       { this.telegram = telegram;  }
    public void setSkype        (String skype)          { this.skype = skype;    }
    public void setPhone        (String phone)          { this.phone = phone;    }

    // ----------------------------------- OTHER METHODS
        
    
    /**
     * Возвращает строку парметров, разделённых ", " и без "[", "]"
     */
    public String getStringFromArrayString(String[] array) {
        String str = Arrays.toString( array );
        return str.substring(1, str.length()-1);
    }
    
    
    public void addNewInformation(String information) {
        //secrets.put("Login", login); 
    }
    public void deleteTag(String tag) {
        //secrets.put("Login", login); 
    }
    public void deleteOldInformation(String information) { 
        //secrets.put("Login", login); 
    }


    // ----------------------------------- toString - переделать !
    @Override
    public String toString() {
        return "***OnePassObject { " + 
                "\n\tlogin: \t\t"        + this.login + 
                "\n\tpassword: \t"       + this.password + 
                "\n\twebsite: \t"        + this.site + 
                "\n\te-mail: \t"         + this.mail + 
                "\n\tdate: \t\t"         + this.date + 
                "\n\tsecret key: \t"     + this.secretKey + 
                "\n\tName Surname: \t"   + this.nameSurname + 
                "\n\tphone number: \t"   + this.phone + 
                "\n\tTwitter: \t"        + this.twitter + 
                "\n\tFacebook: \t"       + this.facebook + 
                "\n\tTelegram: \t"       + this.telegram + 
                "\n\t}\n";
    }    
}


/*
       private String skype = "";
    private String whatsapp = "";
*/