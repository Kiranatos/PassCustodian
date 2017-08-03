/**
 * 
 * https://poi.apache.org/
 * 
 * Доки по библиотеке poi
 * https://poi.apache.org/apidocs/index.html
 * 
 * https://poi.apache.org/spreadsheet/index.html
 * 
 * https://poi.apache.org/spreadsheet/quick-guide.html#CellContents
 * 
 * Остановился на Java+Excel. Урок 11: Сводная таблица - Часть 1 
 * Тема сводных таблиц сложнее, разобрать потом подробнее и сделать примеры в отдельном файле
 * https://www.youtube.com/playlist?list=PLwcDaxeEINafif17no5JAO0iAi9Gw4g6H
 */

/**
 * Примеры:
 * https://poi.apache.org/spreadsheet/examples.html
 * Отсюда взяты классы LineChart и ScatterChart - которые рисуют диаграммы.
 * Посмотреть, попробовать другие примеры, которые там есть. * 
 */

/**
 * Некоторые ограничения самой библиотеки poi
 * https://poi.apache.org/spreadsheet/limitations.html
 */



package net.kiranatos.junk.testExcel;
//в проект нужно подключать файл библиотеки poi-3.14-20160307.jar
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSimpleShape;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.WorkbookUtil;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MakeAndReadExcelFile {
    
    public static void main(String[] args) throws IOException {
        
        //ЗАПИСЬ В ФАЙЛ:
        // Workbook - интерфейс, HSSFWorkbook - класс
        Workbook wbWrite = new HSSFWorkbook(); //экземпляр класса Книга
        //Workbook wbWrite = new XSSFWorkbook(); //экземпляр класса Книга
        /**
         * HSSFWorkbook() - для старого формата xls
         * XSSFWorkbook() - для нового формата xlsx, кроме того нужно добавить дополнительно 3 библиотеки:
         * - poi-ooxml-3.14-20160307.jar
         * - poi-ooxml-schemas-3.14-20160307.jar
         * - xmlbeans-2.6.0.jar
         */
        
        //Вкладки в xls-файле:
        // Sheet - интерфейс
        Sheet sheetWrite = wbWrite.createSheet("НазваниеВкладки1");
        //Отсчёт начинается с нуля, а не как в эксель-файле
        Row rowWrite = sheetWrite.createRow(3); // Полностью 3-я строка
        Cell cellWrite01 = rowWrite.createCell(4); // 4-я ячейка в 3-ей строке
        Cell cellWrite02 = rowWrite.createCell(7); // 7-я ячейка в 3-ей строке
        cellWrite01.setCellValue("some text #1");
        cellWrite02.setCellValue("some text #2");
        
        /**
         * ==============================================
         * ДЕМОНСТРАЦИЯ ФОРМУЛЫ
         * ==============================================
         */
        
        Sheet shWrite02 = wbWrite.createSheet("Демонстрация формулы");
        for (int i=0; i<10; i++)
            shWrite02.createRow(i).createCell(0).setCellValue(i);
        //Писать "=" не требуется. Писать только на английском:
        shWrite02.createRow(10).createCell(0).setCellFormula("SUM(A1:A10)");        
        
        /**
         * ==============================================
         * ДЕМОНСТРАЦИЯ ИЗМЕНЕНИЯ РАЗМЕРОВ ЯЧЕЕК
         * ==============================================
         */
        
        Sheet shWrite03 = wbWrite.createSheet(WorkbookUtil.createSafeSheetName("размер ячейки №?%;$")); //Для создания листов со спецсимволами. Без этого джава выбрасывает исключение при запуске
        
        Cell cellSize01 = shWrite03.createRow(0).createCell(0);
        cellSize01.setCellValue("Демострация изменения ширины ячейки");
        shWrite03.setColumnWidth(10, 6000); // номер ячейки и её ширина. Используется Sheet
        
        Cell cellSize02 = shWrite03.createRow(3).createCell(3);        
        cellSize02.setCellValue("Демострация подбора по ширине ячейки");
        shWrite03.autoSizeColumn(3); // Используется Sheet
        
        Row rowSize03 = shWrite03.createRow(6);
        Cell cellFontSize03 = rowSize03.createCell(1);                
        cellFontSize03.setCellValue("Демострация изменения высоты ячейки");
        // Используется Row
        rowSize03.setHeightInPoints(35);        
        
        Cell cellFontSize04 = shWrite03.createRow(8).createCell(5);
        cellFontSize04.setCellValue("Демострация объединения ячеек");
        // Используется Sheet. Параметры CellRangeAddress(firstRow, lastRow, firstCol, lastCol)
        shWrite03.addMergedRegion(new CellRangeAddress(8,13,5,9));        
        
        /**
         * ==============================================
         * ДЕМОНСТРАЦИЯ СТИЛЯ ЯЧЕЕК, ТЕКСТА, ФОНА
         * ==============================================
         */
        
        Sheet shWrite04 = wbWrite.createSheet("Демонстрация стиля");
        Cell cellFont = shWrite04.createRow(0).createCell(0);
        cellFont.setCellValue("Текст для демонстрации");
        CellStyle style = wbWrite.createCellStyle();
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        //style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
        style.setBorderBottom(CellStyle.BORDER_DASH_DOT_DOT);
        style.setBottomBorderColor(IndexedColors.GREEN.getIndex());
        
        //Важно: выбрать import org.apache.poi.ss.usermodel.Font;
        Font font = wbWrite.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short)15);
        font.setBold(true);
        font.setStrikeout(true);
        font.setUnderline(Font.U_SINGLE);
        font.setColor(IndexedColors.RED.getIndex());
        
        style.setFont(font);
        cellFont.setCellStyle(style);  
        
        /**
         * ==============================================
         * ДЕМОНСТРАЦИЯ РИСОВАНИЯ ФИГУР: ЛИНИЯ, ПРЯМОУГОЛЬНИК
         * ==============================================
         */
        
        Sheet shWrite05 = wbWrite.createSheet("Рисование");
        Cell cell05 = shWrite05.createRow(0).createCell(0);
        cell05.setCellValue("Line");
        HSSFPatriarch pat = (HSSFPatriarch)shWrite05.createDrawingPatriarch(); //создаем художника
        HSSFClientAnchor anchor = new HSSFClientAnchor();
        anchor.setCol1(2);
        anchor.setRow1(2);
        anchor.setCol2(10);
        anchor.setRow2(10);
        HSSFSimpleShape shape = pat.createSimpleShape(anchor);
        shape.setShapeType(HSSFSimpleShape.OBJECT_TYPE_LINE);
        shape.setLineStyleColor(255, 0, 0);
        shape.setLineWidth(HSSFSimpleShape.LINEWIDTH_ONE_PT*3);
        shape.setLineStyle(HSSFSimpleShape.LINESTYLE_DASHDOTGEL);
        
        HSSFClientAnchor anchor2 = new HSSFClientAnchor();
        anchor2.setCol1(2);
        anchor2.setRow1(15);
        anchor2.setCol2(10);
        anchor2.setRow2(30);
        HSSFSimpleShape shape2 = pat.createSimpleShape(anchor2);
        shape2.setShapeType(HSSFSimpleShape.OBJECT_TYPE_RECTANGLE);
        shape2.setFillColor(0,0,255);
        
        /**
         * ==============================================
         * ЗАПИСЫВАЕМ САМ ФАЙЛ:
         * ==============================================
         */
        
        FileOutputStream fos = new FileOutputStream("test1.xls");        
        wbWrite.write(fos);
        fos.close();
        
        /**
         * ==============================================
         * ЧТЕНИЕ ИЗ ФАЙЛА:
         * ==============================================
         */        
        
        FileInputStream fis = new FileInputStream("F:\\test2.xlsx");        
        Workbook wbRead = new HSSFWorkbook(fis);
        // Получаем лист/вкладку по индексу 0
        Sheet sheetRead01 = wbRead.getSheetAt(0);
        // Получаем строку по индексу 0
        Row rowRead01 = sheetRead01.getRow(0);
        // Получаем ячейку по индексу 0
        Cell cellRead01  = rowRead01.getCell(0);
        // получаем String'овое значение в ячейке
        // если в ячейке будет не строка, то выбросит ошибку, а не преобразует !!!
        String result01 = cellRead01.getStringCellValue();
        System.out.println(result01);
        // То же смаое в сокращённом виде:
        System.out.println(wbRead.getSheetAt(0).getRow(0).getCell(1).getStringCellValue());
        
        getStringFromSell(wbRead);
        
        fis.close();        
    }
    
    /**
     * 
     * ==============================================
     * МЕТОД ПОЗВОЛЯЮЩИЙ ПОЛУЧИТЬ ДАННЫЕ НУЖНОГО ТИПА ИЗ ЯЧЕЙКИ, Т.К. В ЭКСЕЛЕ МОГУТ БЫТЬ ЗАПИСАНЫ РАЗНОГО ТИПА: строка, число, дата
     * взят из https://poi.apache.org/spreadsheet/quick-guide.html#CellContents
     * ==============================================
     */            
    private static void getStringFromSell(Workbook wb) {
        Sheet sheet1 = wb.getSheetAt(0);
        for (Row row : sheet1) {
            for (Cell cell : row) {
                //CellReference позволяет выводить координаты ячейки, типо A1 B5 C10
                CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                System.out.print(cellRef.formatAsString());
                System.out.print(" - ");
                
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        System.out.println(cell.getRichStringCellValue().getString());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            System.out.println(cell.getDateCellValue());
                        } else {
                            System.out.println(cell.getNumericCellValue());
                        }
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        System.out.println(cell.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        System.out.println(cell.getCellFormula());
                        break;
                    default:
                        System.out.println();
                }
            }
        }
    }    
}

//2007-xlsx
