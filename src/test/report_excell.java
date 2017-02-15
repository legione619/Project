package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class report_excell
{
    public void writeXLSXFile(String foglio,double []value) throws IOException {
    	int row=2; 
    	int col;
    	try {
            FileInputStream file = new FileInputStream("C:\\shared\\Tempi_AP_auto.XLSX");

            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet(foglio);
            Cell cell = null;
            for(row=2;row<=6;row++){
            //Retrieve the row and check for null
            	XSSFRow sheetrow = sheet.getRow(row);
            	if(sheetrow == null){
            		sheetrow = sheet.createRow(row);
            	}
            	//Update the value of cell
            	col=sheetrow.getLastCellNum();
            	cell = sheetrow.getCell(col);
            	if(cell == null){
            		cell = sheetrow.createCell(col);
            	}
            	cell.setCellValue(value[row-2]);
            }
            file.close();

            FileOutputStream outFile =new FileOutputStream(new File("C:\\shared\\Tempi_AP_auto.XLSX"));
            workbook.write(outFile);
            outFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
public void write_data(){
	//Args to add String foglio, String om, String TP, String zo, String pan,String stampa
	
	try
     {
         FileInputStream file = new FileInputStream(new File("C:\\shared\\Tempi_AP_auto.XLSX"));
         XSSFWorkbook workbook = new XSSFWorkbook(file);
         XSSFSheet sheet = workbook.getSheet("Cagliari");
         Row r = sheet.getRow(3);
         int freeCell=  r.getLastCellNum() + 1;  
         System.out.println(freeCell);
         r.createCell(freeCell).setCellValue("123.4");
         System.out.println(freeCell);
         FileOutputStream outFile =new FileOutputStream(new File("C:\\shared\\Tempi_AP_auto.XLSX"));
         file.close();
         workbook.write(outFile);
         outFile.close();
         workbook.close();
     } 
     catch (Exception e) 
	
	
     {
         e.printStackTrace();
     }
}

 }
