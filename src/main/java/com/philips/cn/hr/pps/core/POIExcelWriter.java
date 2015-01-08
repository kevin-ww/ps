package com.philips.cn.hr.pps.core;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

/**
 * Created by kevin on 2015/1/7.
 */
public class POIExcelWriter {

    //TODO "write based on template"

    private String template;

    private SXSSFWorkbook workbook;

    private String output;

//    XSSFWorkbook workbook;

    public POIExcelWriter(String template, String output) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook(OPCPackage.open(template));
        this.workbook = new SXSSFWorkbook(workbook);
        this.output = output;
    }

    public static void main(String[] args) throws Exception {

        String template = "xxx.xlsx";
        String out = "o.xlsx";
        POIExcelWriter w = new POIExcelWriter(template, out);
        w.writeData("damnit");
        w.save();
    }

    public void writeData(String data) {

        //set the cell[3,3] to "data"

//        XSSFSheet sheet = this.workbook.getSheetAt(0);

        SXSSFSheet sheet = (SXSSFSheet) this.workbook.getSheetAt(0);

        sheet.setRandomAccessWindowSize(1000);

        String sheetName = sheet.getSheetName();


//        sheet.getRow()
        //
        Row row = sheet.getRow(30);
        if (row == null) {
            row = sheet.createRow(30);
        }

        Cell cell = row.getCell(4);
        if (cell == null) {
            cell = row.createCell(4);
        }

        String address = new CellReference(cell).formatAsString();
        cell.setCellValue(address);

    }

    public void save() throws Exception {
        FileOutputStream out = new FileOutputStream(output);
        this.workbook.write(out);
        out.close();
//        this.workbook.dispose();
    }


}
