package com.philips.cn.hr.pps.core;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by kevin on 2015/1/8.
 */
public class POIExcelReaderDOM {

    static Logger log = Logger.getLogger(POIExcelReaderDOM.class.getName());

    private XSSFWorkbook workbook;

    private FormulaEvaluator evaluator;

    public POIExcelReaderDOM(String fileName) throws OpenXML4JException, IOException {

        this.workbook = new XSSFWorkbook(new FileInputStream(fileName));
//        this.workbook = new SXSSFWorkbook(workbook);
        this.evaluator = workbook.getCreationHelper().createFormulaEvaluator();

    }

    public static void main(String[] args) throws Exception {
        String fileName = "xxxx.xlsx";
        new POIExcelReaderDOM(fileName).readDataFromSheet(0);
    }

    public void readData() {

    }

    public void readDataFromSheet(int sheetId) {

        log.info("now reading data from column ${columnIndex} at sheet ${sheetId}");

        Sheet sheet = this.workbook.getSheetAt(sheetId);

        if (sheet == null) {
            log.info("no sheet ${sheetId} in this Excel file,please verify");
        } else {
            String sheetName = sheet.getSheetName();
            log.info("now reading data from sheet "+sheetName);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

//                    System.out.println("now getting cell"+cell);
                    switch (evaluator.evaluateInCell(cell).getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.println(cell.getNumericCellValue());
                            break;
                        case Cell.CELL_TYPE_STRING:
                            System.out.println(cell.getStringCellValue());
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                            //Not again
                            break;
                    }
                }
            }
        }

    }

    public void readDataFromSheet(int sheetId, int columnId) {


    }

    public void dispose() {
//        this.workbook//
    }


}
