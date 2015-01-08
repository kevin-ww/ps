package com.philips.cn.hr.pps.core;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Created by kevin on 2015/1/7.
 */
public class POIExcelReader {

//    String fileName;

    static Logger log = Logger.getLogger(POIExcelReader.class.getName());

    XMLReader xmlReader;

    XSSFReader xssfReader; //?

//    ContentHandler contentHandler;


    public POIExcelReader(String fileName, DefaultSheetHandler contentHandler) throws InvalidFormatException, IOException, OpenXML4JException, SAXException {
        OPCPackage pkg = OPCPackage.open(fileName);
        this.xssfReader = new XSSFReader(pkg);
        SharedStringsTable sst = xssfReader.getSharedStringsTable();
        this.xmlReader = XMLReaderFactory.createXMLReader(
                "org.apache.xerces.parsers.SAXParser"
        );
        contentHandler.setSst(sst);
        this.xmlReader.setContentHandler(contentHandler);
    }

    public static void main(String[] args) throws InvalidFormatException, SAXException, IOException, OpenXML4JException {
        POIExcelReader example = new POIExcelReader("xxxx.xlsx", new DefaultSheetHandler());
//        example.processAllSheets();
        example.readDataFromColumn(0, 0);
    }

    private void dispose() {
//        this.r.
    }

    public void readData() {

    }

    public void readDataFromSheet(int sheetId) {

    }

    public void readDataFromColumn(int sheetId, int columnIndex) throws IOException, InvalidFormatException, SAXException {

        log.info("now reading data from column ${columnIndex} at sheet ${sheetId}");

        Iterator<InputStream> sheets = this.xssfReader.getSheetsData();

        int idx = 0;
        while (sheets.hasNext()) {
            log.info("now processing sheet ${idx}");
            InputStream sheet = sheets.next();
            if (sheetId != idx) {
                log.info("not the given sheet ${sheetId},proceed the next.");
            } else {
                log.info("start processing");
                InputSource sheetSource = new InputSource(sheet);
                this.xmlReader.parse(sheetSource);
            }
            sheet.close();
            idx++;
        }
    }


}
