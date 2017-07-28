package net.kiranatos.res;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OnePassObject implements Serializable{
    
    private Map<String, String> secrets = new HashMap <>();
    int index = 0;

    /*
    private String password = null;
    private String login = null;
    private String mail = null;
    private String site = null;
    private String[] tag = null;
    //private ArrayList<String> otherInfo;
    private Date createdTime;*/

    // CONSTRUCTORS
    private OnePassObject() {}
    public OnePassObject(String login, String password, String site, String mail, String[]... otherInfo) {
        secrets.put("Login", login);
        secrets.put("Password", password);
        secrets.put("Site", site);
        secrets.put("E-Mail", mail);        
                
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");        
        secrets.put("Date", dateFormat.format(new Date()));
        
        for (int i = 0; i < otherInfo.length; i++ ) {
            if (secrets.containsKey(otherInfo[i][0])) {
                secrets.put(otherInfo[i][0] + index, otherInfo[i][1]);            
                index++;
            }
            else secrets.put(otherInfo[i][0], otherInfo[i][1]);
        }        
    }
    
    // GETTERS
    public String getLogin()        { return secrets.get("Login"); }    
    public String getPassword()     { return secrets.get("Password");  }
    public String getSite()         { return secrets.get("Site"); }
    public String getMail()         { return secrets.get("E-Mail"); }
    public String getCreatedDate()  { return secrets.get("Date"); }
    public String[] getTags() {        
        return null;
    }
    public String[] getOtherInformation() {
        int z = 0;
        for (String str: secrets.keySet()) if (str.contains("tag")) z++;
        
        String[] arrayString = new String[z];
        z=0;

        Iterator<Map.Entry<String,String>> it = secrets.entrySet().iterator();
        while ( it.hasNext() ) {
            Map.Entry<String,String> pair = it.next();
            if (pair.getKey().contains("tag")) 
                arrayString[z++] = pair.getValue();            
        }
        return arrayString;
    }
    public String getTagsByString() {        
        return Arrays.toString(getOtherInformation());
    }

    //SETTERS
    public void setLogin(String login)          { secrets.put("Login", login); }    
    public void setPassword(String password)    { secrets.put("Password", password); }
    public void setSite(String site)            { secrets.put("Site", site); }
    public void setMail(String mail)            { secrets.put("E-Mail", mail); }
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


    // toString - переделать !
    @Override
    public String toString() {
        return "OnePassObject { " + "secrets=\n" + secrets + "\n}";
    }
    
    
    // MAIN Only for Test - need TO DELETE then:
    public static void main(String[] args) {
        OnePassObject p = new OnePassObject("Kiranatos", "Password123", "www.porn.com", "mail@mail.com", 
                new String[]{"tag1","music"}, 
                new String[]{"tag2","erotic"}, 
                new String[]{"tag3","music"}
        );
        
        OnePassObject p2 = new OnePassObject("Kiranatos", "Password123", "www.porn.com", "mail@mail.com");
        
        System.out.println(p);
        System.out.println(p2);
    }
}
