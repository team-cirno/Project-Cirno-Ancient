package Tool;

import java.io.File; 
import java.io.IOException;
import java.util.ArrayList;

import jxl.Cell; 
import jxl.CellType; 
import jxl.Sheet; 
import jxl.Workbook; 
import jxl.read.biff.BiffException; 

public class ReadExcel { 

  private String inputFile; 

  public void setInputFile(String inputFile) { 
    this.inputFile = inputFile; 
  } 
  
  public void read(String s) {
	    
  }

  public void read() throws IOException  { 
    File inputWorkbook = new File(inputFile); 
    Workbook w; 
    try { 
      w = Workbook.getWorkbook(inputWorkbook); 
      // Get the first sheet 
      Sheet sheet = w.getSheet(0); 
      // Loop over first 10 column and lines 

      for (int j = 0; j < sheet.getColumns(); j++) { 
        for (int i = 0; i < sheet.getRows(); i++) { 
          Cell cell = sheet.getCell(j, i); 
          CellType type = cell.getType(); 
          System.out.print(j+"-"+i+"=> ");
          if (type == CellType.LABEL) { 
            System.out.print("label " 
                + cell.getContents()); 
          } else if (type == CellType.NUMBER) { 
            System.out.print("number " 
                + cell.getContents()); 
          } else {
              System.out.print("hum " 
                      + cell.getContents()); 
          }
          System.out.println();

        } 
      } 
    } catch (BiffException e) { 
      e.printStackTrace(); 
    } 
  } 
  public static void main(String[] args) throws IOException { 
    ReadExcel test = new ReadExcel(); 
    test.setInputFile("red/亮片颜色目录.xls"); 
    test.read(); 
  } 
}  
