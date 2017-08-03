package net.kiranatos.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.kiranatos.PassPaths;
import net.kiranatos.res.OnePassObject;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
Доработать:
related Phone Number
related vk.com
related facebook
related twitter
related skype
*/

public class ExcelWriter {
    
    private Workbook wbWriter;        
    private Sheet firstSheetWriter;
    private FileOutputStream fos;          
    private ArrayList<OnePassObject> listToSave;
    private int indexOfList = 1;
    private int indexOfRow = 2;
    private int max = 20000;
    private File file = new File( PassPaths.getSaveNameFile() );    
    
    //private String[] headOfTable = PassPaths.HEAD_EXCEL_TABLE;
    private String[] headOfTable = new String[] {"#", "Site", "Login", "Mail", "Password", "Name Surname", "Tags", "Date", "Other Info"};
    
    /**
     * Конструктор, который первым делом создает лист
     */    
    private ExcelWriter() {
        /* HSSFWorkbook() - для старого формата xls
        * XSSFWorkbook() - для нового формата xlsx, кроме того нужно добавить дополнительно 3 библиотеки:
        * - poi-ooxml.jar
        * - poi-ooxml-schemas.jar
        * - xmlbeans.jar    */
        wbWriter = new XSSFWorkbook(); //экземпляр класса Книга        
        //Вкладки в xls-файле:
        // Sheet - интерфейс
        firstSheetWriter = wbWriter.createSheet("Accounts");
        
    }

    /**
     * CONSTRUCTOR
     */
    public ExcelWriter(ArrayList<OnePassObject> listToSave) {
        this();
        this.listToSave = listToSave;        
    }

    /**
     * Сохраняет информацию в файл *.xlsx, соответсвенно подготавливая её перед этим.
     * Возмлжно нужно переделать основной класс OnePassObject, добавив в него:     
     */    
    public void writeAndSaveDataList (){
        Iterator<OnePassObject> it = listToSave.iterator();
        while (it.hasNext()) { 
            OnePassObject opo = it.next();
            
            String[] dataOfThisRow = new String[] {
                String.valueOf(indexOfList),
                opo.getSite(),
                opo.getLogin(),
                opo.getMail(),
                opo.getPassword(),
                opo.getNameSurname(), 
                opo.getTagsByString(),
                opo.getCreatedDate(),
                opo.getOtherInformation()
            };
            
            Row rowWriterOfThisOPO = firstSheetWriter.createRow(indexOfRow);            
            rowWriterOfThisOPO.setRowStyle( 
                    createStyleOfCells(IndexedColors.YELLOW, "Arial", 12, true, false, false, false) 
            );
            
            for (int i = 0; i < dataOfThisRow.length; i++) {
                Cell cellWriterOfThisOPO = rowWriterOfThisOPO.createCell(i);
                cellWriterOfThisOPO.setCellValue(dataOfThisRow[i]);                
            }
            
            indexOfList++;
            indexOfRow+=2;
        }
        
        max = indexOfRow + 100;
        
        /* Финальное оформление: */
        nColumnStyle(0, IndexedColors.LIGHT_TURQUOISE);
        nColumnStyle(2, IndexedColors.LIGHT_TURQUOISE);
        nColumnStyle(4, IndexedColors.LIGHT_TURQUOISE);
        nColumnStyle(6, IndexedColors.LIGHT_TURQUOISE);
        nColumnStyle(8, IndexedColors.LIGHT_TURQUOISE);
        
        head();
        
        writeToFile();
        indexOfList=1;  //возвращение к базовым значениям, скорее всего роли играть не будет, но пускай на всякий случай будет
        indexOfRow=2;
    }
    
    /**
     * Записывает всю информаци в сам файл
     */    
    private void writeToFile (){
        try {        
            fos = new FileOutputStream(file);
            wbWriter.write(fos);
            fos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            Logger.getLogger(ExcelWriter.class.getName()).log(Level.SEVERE, null, e);
        }        
    }
    
    
    /**
     * Метод возвращает стиль ячеек.
     * @return style     
     */
    private CellStyle createStyleOfCells(
            IndexedColors bgColor, String fontName, int fontHeight,
            boolean borderUp, boolean borderRight, boolean borderDown, boolean borderLeft            
    ){        
        CellStyle style = wbWriter.createCellStyle();
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
        style.setFillForegroundColor(bgColor.getIndex());        
        
        //Положение текста
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        
        // Table
        style.setBorderTop      ( borderUp    ? BorderStyle.MEDIUM : BorderStyle.NONE );
        style.setBorderRight    ( borderRight ? BorderStyle.MEDIUM : BorderStyle.NONE );
        style.setBorderBottom   ( borderDown  ? BorderStyle.MEDIUM : BorderStyle.NONE );
        style.setBorderLeft     ( borderLeft  ? BorderStyle.MEDIUM : BorderStyle.NONE );        
                
        // TEXT FONT: 
        Font font = wbWriter.createFont();
        font.setFontName(fontName);
        font.setFontHeightInPoints((short)fontHeight);
        font.setBold(false);        
        font.setColor(IndexedColors.BLACK.getIndex());        
        style.setFont(font);
        
        return style;
    }
    
    /**
     * Создает шапку - стиль и текст
    */
    private void head(){        
        //Отсчёт начинается с нуля, а не как в эксель-файле    
        
        Row rowWriter = firstSheetWriter.createRow(0); // Полностью 0-я строка, для шапки
        
        rowWriter.setRowStyle( 
                createStyleOfCells(IndexedColors.BRIGHT_GREEN, "Arial", 13, false, true, true, false) 
        );        
        CellStyle t = rowWriter.getRowStyle();
        t.setAlignment(HorizontalAlignment.CENTER);
        
        firstSheetWriter.setColumnWidth(0, 1100);
        for (int i = 0; i < headOfTable.length; i++) {
            Cell cellWriter = rowWriter.createCell(i);
            cellWriter.setCellValue(headOfTable[i]);
            firstSheetWriter.setColumnWidth(i+1, 5000);
        }     
    }
    
    /**
     * Создает стиль первой колонки, текст не трогает
    */
    private void nColumnStyle(int numOfColomn, IndexedColors bgColor){        
        for (int i = 1; i < max; i++) {
            Row r = firstSheetWriter.getRow(i);
            if (isRowEmpty(r)) { r = firstSheetWriter.createRow(i); }
            
            Cell c = r.getCell(numOfColomn);
            boolean upperLine = true;
            if (isCellEmpty(c)) { c = r.createCell(numOfColomn); upperLine = false; }
                c.setCellStyle(createStyleOfCells(bgColor, "Arial", 12, upperLine, true, false, true) );
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
    public void setFile(File file){
        this.file = file;
    }
}
