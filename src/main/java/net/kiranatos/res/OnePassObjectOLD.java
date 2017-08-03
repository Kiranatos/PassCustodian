package net.kiranatos.res;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OnePassObjectOLD implements Serializable{
    
    private Map<String, String> secrets = new HashMap <>();
    private ArrayList<OnePassObjectOLD> list;
    
    // ----------------------------------- CONSTRUCTORS
    private OnePassObjectOLD() {}
    public OnePassObjectOLD(String login, String password, String site, String mail, String[]... otherInfo) {
        int indexOfOtherInfo = 1;
        secrets.put("Login", login);
        secrets.put("Password", password);
        secrets.put("Site", site);
        secrets.put("E-Mail", mail);       
        
        // DATE                
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");        
        secrets.put("Date", dateFormat.format(new Date()));
                
        for (int i = 0; i < otherInfo.length; i++ ) {
            if (secrets.containsKey(otherInfo[i][0])) {
                secrets.put(otherInfo[i][0] + indexOfOtherInfo, otherInfo[i][1]);            
                indexOfOtherInfo++;
            }
            else secrets.put(otherInfo[i][0], otherInfo[i][1]);
        }        
    }
    
    // ----------------------------------- GETTERS
    public String getLogin()        { return secrets.get("Login"); }    
    public String getPassword()     { return secrets.get("Password");  }
    public String getSite()         { return secrets.get("Site"); }
    public String getMail()         { return secrets.get("E-Mail"); }
    public String getCreatedDate()  { return secrets.get("Date"); }
    
    public String[] getTags() { return getSomethingFromOtherInformation("tag"); }    
    public String getTagsByString() { return getStringFromArrayString(getTags()); }
    
    public String getNameSurname()          { return getStringFromArrayString( getSomethingFromOtherInformation("Name Surname") ); }    
    public String getVKSiteAccount()        { return getStringFromArrayString( getSomethingFromOtherInformation("vk.com") ); }
    public String getTwitterSiteAccount()   { return getStringFromArrayString( getSomethingFromOtherInformation("twitter.com") ); }
    public String getFacebookSiteAccount()  { return getStringFromArrayString( getSomethingFromOtherInformation("facebook.com") ); }
    public String getSkype()                { return getStringFromArrayString( getSomethingFromOtherInformation("skype") ); }
    public String getWhatsApp()             { return getStringFromArrayString( getSomethingFromOtherInformation("whatsapp") ); }
    public String getTelegram()             { return getStringFromArrayString( getSomethingFromOtherInformation("telegram") ); }
    public String getPhoneNumber()          { return getStringFromArrayString( getSomethingFromOtherInformation("phone number") ); }
    
//    public String[] getOtherInformationWithoutTags() {        
//        return getSomethingFromOtherInformationWithoutTHIS("Login","Password","Site","E-Mail","Date","Name Surname","tag");
//    }
//    public String getOtherInformationWithoutTagsInString() {        
//        return getStringFromArrayString( getOtherInformationWithoutTags());
//    }

    // ----------------------------------- SETTERS
    public void setLogin(String login)          { secrets.put("Login", login); }    
    public void setPassword(String password)    { secrets.put("Password", password); }
    public void setSite(String site)            { secrets.put("Site", site); }
    public void setMail(String mail)            { secrets.put("E-Mail", mail); }
    
    // ----------------------------------- OTHER METHODS
    /**
     * Выбирает из общей коллекции свойства, по заданому названию в аргументе метода
     */
    private String[] getSomethingFromOtherInformation(String whatDoYouWant) {
        int z = 0;
        for (String str: secrets.keySet()) if (str.contains(whatDoYouWant)) z++;
        
        String[] arrayString = new String[z];
        z=0;

        Iterator<Map.Entry<String,String>> it = secrets.entrySet().iterator();
        while ( it.hasNext() ) {
            Map.Entry<String,String> pair = it.next();
            if (pair.getKey().contains(whatDoYouWant)) 
                arrayString[z++] = pair.getValue();            
        }
        return arrayString;
    }
    
    /**
     * Выбирает из общей коллекции свойства, кроме тех, что в аргуметах
     */
//    private String[] getSomethingFromOtherInformationWithoutTHIS (String... whatDoNotYouWant) {
//        
//        Iterator<Map.Entry<String,String>> it = secrets.entrySet().iterator();
//        while ( it.hasNext() ) {
//            Map.Entry<String,String> pair = it.next();
//            for (String searchWord : whatDoNotYouWant) {
//                if (pair.getKey().contains(searchWord)) 
//            }
//            if (pair.getKey().contains(whatDoYouWant)) 
//                arrayString[z++] = pair.getValue();            
//        }
//        return arrayString; 
//    }
    
    /**
     * Возвращает строку парметров, разделённых ", " и без "[", "]"
     */
    public String getStringFromArrayString(String[] array) {
        String str = Arrays.toString( array );
        return str.substring(1, str.length()-1);
    }
    
    
    public void addTag (String tag) { 
        //secrets.put("", login); 
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
        return "OnePassObject { " + "secrets=\n" + secrets + "\n}";
    }
    
    
    // MAIN Only for Test - need TO DELETE then:
    public static void main(String[] args) {
        OnePassObjectOLD p = new OnePassObjectOLD("Kiranatos", "Password123", "www.porn.com", "mail@mail.com", 
                new String[]{"tag1","music"}, 
                new String[]{"tag2","erotic"}, 
                new String[]{"tag3","music"}
        );
        
        OnePassObjectOLD p2 = new OnePassObjectOLD("Kiranatos", "Password123", "www.porn.com", "mail@mail.com");
        
        System.out.println(p);
        System.out.println(p2);
    }
}
