import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Created by kevin on 2015/1/8.
 */

public class XX {


    public static void main(String[] args) throws Exception

    {

        XX.readSheetWithFormula();
//        InputStream ins = null;
//
//        Workbook wb = null;
//
//        ins = new FileInputStream(new File("xxxx.xlsx"));
//        // ins=
//        // ExcelService.class.getClassLoader().getResourceAsStream(filePath);
//        wb = WorkbookFactory.create(ins);
//
//        Sheet sheet = wb.getSheetAt(0);
//
//        Row row = sheet.getRow(0);
//        Cell cell1 = row.getCell(0);
//
//
//        System.out.println("cell[0]： " + cell1.getStringCellValue());
//
//        cell1.setCellValue("AAA");
//
//        Row row3 = sheet.getRow(2);
//        Cell r3c1 = row3.getCell(0);
//        if (r3c1 == null) {
//            r3c1 = row3.createCell(0);
//        }
//        System.out.println("r3c1： " + r3c1.getStringCellValue());
//
//        r3c1.setCellValue("R3C1");
//
//        ins.close();
//
//
//        try {
//            FileOutputStream fout = new FileOutputStream("testOut.xlsx");
//            wb.write(fout);
//            fout.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }

    public static void readSheetWithFormula()
    {
        try
        {
            FileInputStream file = new FileInputStream(new File("xxxx.xlsx"));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    //Check the cell type after eveluating formulae
                    //If it is formula cell, it will be evaluated otherwise no change will happen
                    switch (evaluator.evaluateInCell(cell).getCellType())
                    {
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "tt");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            System.out.print(cell.getStringCellValue() + "tt");
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                            //Not again
                            break;
                    }
                }
                System.out.println("");
            }
            file.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
