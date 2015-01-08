package com.philips.cn.hr.pps.core;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

/**
 * Created by kevin on 2015/1/8.
 */
public class POIExcelReaderDOM {

    static Logger log = Logger.getLogger(POIExcelReaderDOM.class.getName());

    private SXSSFWorkbook workbook;

    public POIExcelReaderDOM(String fileName) throws OpenXML4JException, IOException {

        XSSFWorkbook workbook = new XSSFWorkbook(OPCPackage.open(fileName));
        this.workbook = new SXSSFWorkbook(workbook);

    }

    public void readData() {

    }

    public void readDataFromSheet(int sheetId) {
    }


    public void readDataFromSheet(int sheetId,int columnId) {

        log.info("now reading data from column ${columnIndex} at sheet ${sheetId}");

        Sheet sheet = this.workbook.getSheetAt(sheetId);

        if(sheet==null){
            log.info("no sheet ${sheetId} in this Excel file,please verify");
        } else{
            String sheetName = sheet.getSheetName();
            log.info("now reading data from sheet ${sheetName}");
//            sheet.getRow(0);

//            sheet.getr
        }



    }



}
