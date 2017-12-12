package net.kiranatos.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.kiranatos.Information;
import net.kiranatos.res.OnePassObject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
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
                Information.println("строка №" + r.getRowNum() + " номер посл.ячейки: " + r.getLastCellNum() + " всего ячеек: " + r.getPhysicalNumberOfCells());
                
                if (r.getRowNum()!=0) {
                    //Cell c = r.getCell(0);
                    if (getCellStringValue(r, 0).length() > 0) {
                        //OnePassObject(String login(2), String password(4), String site(1), String mail(3), String[]... otherInfo)
                        //Excel#	Site	Login	Mail	Password	Name Surname    Phone	Tags	Date	Other Info
                        readedList.add( new OnePassObject(
                                getCellStringValue(r, 2),
                                getCellStringValue(r, 4),
                                getCellStringValue(r, 1),
                                getCellStringValue(r, 3),
                                new String[]{"namesurname",     getCellStringValue(r, 5)}, 
                                new String[]{"phone",           getCellStringValue(r, 6)}, 
                                new String[]{"tag",             getCellStringValue(r, 7)}, 
                                new String[]{"date",            getCellStringValue(r, 8)}, 
                                new String[]{"otherInfo",       getCellStringValue(r, 9)}                                
                        ));                                                
                    }
                }
            }
            
        } catch (FileNotFoundException ex) { 
            Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE, null, ex); 
        } catch (IOException ex) { 
            Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }
    
    private String getCellStringValue(Row currentRow, int numOfCell) {
        String str = "";
        Cell c = currentRow.getCell(numOfCell);
        if (c != null) {
            System.out.println(c.getCellTypeEnum());
            if (c.getCellTypeEnum().equals(CellType.NUMERIC)) { str = String.valueOf(c.getNumericCellValue()); }
            else str = c.getStringCellValue();
            //Information.println("numOfCell=" + numOfCell + " length=" + str.length() + " : " + str);
        }
        return str;
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
