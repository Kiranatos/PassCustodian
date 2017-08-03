package net.kiranatos.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.kiranatos.res.OnePassObject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {   
    
    private ArrayList<OnePassObject> readedList;    
    //private String[] headOfTable = PassPaths.HEAD_EXCEL_TABLE;
    //private String[] headOfTable = new String[] {"#", "Site", "Login", "Mail", "Password", "Name Surname", "Tags", "Date", "Other Info"};

    
    public ArrayList<OnePassObject> getArrayListOPO() {
        return readedList;
    }
    
    /**
     * CONSTRUCTOR
     */    
    private ExcelReader() {}
    public ExcelReader(File file) {
        readedList = new ArrayList<>();
        
        try ( InputStream fis = new FileInputStream(file); ) {
            Workbook wbReader = new XSSFWorkbook(fis);
            Sheet sheetRead01 = wbReader.getSheetAt(0);            
                        
            for (Row r : sheetRead01) {
                if (r.getRowNum()!=0) {
                    Cell c = r.getCell(0);
                    if (c.getStringCellValue().length() > 0) {
                        //OnePassObject(String login(2), String password(4), String site(1), String mail(3), String[]... otherInfo)
                        //Excel#	Site	Login	Mail	Password	Name Surname	Tags	Date	Other Info
                        readedList.add( new OnePassObject(
                                r.getCell(2).getStringCellValue(),
                                r.getCell(4).getStringCellValue(),
                                r.getCell(1).getStringCellValue(),
                                r.getCell(3).getStringCellValue(),                        
                                new String[]{"namesurname", r.getCell(5).getStringCellValue()}, 
                                new String[]{"tag", r.getCell(6).getStringCellValue()}, 
                                new String[]{"date", r.getCell(7).getStringCellValue()}, 
                                new String[]{"otherInfo", r.getCell(8).getStringCellValue()}                                
                        ));                                                
                    }
                }
            }
            //System.out.println("" + sheetRead01);
            
            /*
            Row rowRead01 = sheetRead01.getRow(0);
            Cell cellRead01  = rowRead01.getCell(0);
            
            String result01 = cellRead01.getStringCellValue();
            System.out.println(result01);
            
            System.out.println(wbRead.getSheetAt(0).getRow(0).getCell(1).getStringCellValue());
            getStringFromSell(wbRead);
            
            list.add(new OnePassObject("DartVader",  "LukeIamYourFaser", "www.porn.com",     "power@mail.com",    
                new String[]{"tag1","sw"}, 
                new String[]{"tag2","erotic"}, 
                new String[]{"tag3","music"},                
                new String[]{"tag","music"},                
                new String[]{"tag","bully bully"},                
                new String[]{"twitter","ooooooooooooooooo"},
                new String[]{"NameSurname","Анаккин"}
        ));
            
            */
        
        
            
            
        } catch (FileNotFoundException ex) { 
            Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE, null, ex); 
        } catch (IOException ex) { 
            Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }

    
   
    
       
    private boolean isRowEmpty(Row row){
        if (row == null){ return true; }
        else { return false; }
    }
    private boolean isCellEmpty(Cell cell){
        if (cell == null){ return true; }
        else { return false; }
    }
    
    // ********************************** SETTERS
   
}
