package net.kiranatos.junk.testExcel;

import java.io.File;
import net.kiranatos.file.ExcelWriter;
import java.util.ArrayList;
import net.kiranatos.file.ExcelReader;
import net.kiranatos.res.OnePassObject;

public class TestMainStart {    
    private ArrayList<OnePassObject> list;
    private static ArrayList<OnePassObject> listReader;
    
    private void create() {        
        list = new ArrayList<OnePassObject>();
        list.add(new OnePassObject("DartVader",  "LukeIamYourFaser", "www.porn.com",     "power@mail.com",    
                new String[]{"tag1","sw"}, 
                new String[]{"tag2","erotic"}, 
                new String[]{"tag3","music"},                
                new String[]{"tag","music"},                
                new String[]{"tag","bully bully"},                
                new String[]{"twitter","ooooooooooooooooo"},
                new String[]{"NameSurname","Анаккин"}
        ));
        list.add(new OnePassObject("Yos", "пыщьпыщьололо",     "http://anime-yume.net/",    "yos@gmail.com",    
                new String[]{"tag1","anime"}, 
                new String[]{"tag2","yaoi"}, 
                new String[]{"tag3","hentai"},
                new String[]{"vrrrrrk","basta"},
                new String[]{"tw","ooooooooooooooooo"},
                new String[]{"fffvk","basta"},
                new String[]{"twigggtter","ooooooooooooooooo"},
                new String[]{"NameSurname","Vlad"}
        ));
        list.add(new OnePassObject("Render", "jam666",        "www.vk.com",    "renderlex@gmail.com",    
                new String[]{"tag1","social"}, 
                new String[]{"tag2","sport"}, 
                new String[]{"tag3","lazy"},
                new String[]{"vk","basta"},
                new String[]{"twitter","ooooooooooooooooo"},
                new String[]{"NameSurname","Alex Teslenko"}
        ));
    }
    
    public static void main(String[] args) {
        /*
        TestMainStart tms = new TestMainStart();
        tms.create();
        
        System.out.println(tms.list);
        
        ExcelWriter ew = new ExcelWriter(tms.list);
        ew.writeAndSaveDataList();*/
        
        File file = new File("Passwords17-08-02-09-13-10.xlsx");
        if (file.equals(file)) System.out.println(" ***************** FILE EXIST ***************** ");
        ExcelReader er = new ExcelReader(file);
        listReader = er.getArrayListOPO();
        if (listReader!=null) System.out.println(" ***************** List gotted! ***************** ");
        for(OnePassObject opo : listReader)  {
            System.out.println(opo);
            System.out.println();
        }
    }
    
}
