import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by kevin on 2015/1/8.
 */

public class XX {


    public static void main(String[] args) throws Exception

    {

        InputStream ins = null;

        Workbook wb = null;

        ins = new FileInputStream(new File("xxxx.xlsx"));
        // ins=
        // ExcelService.class.getClassLoader().getResourceAsStream(filePath);
        wb = WorkbookFactory.create(ins);

        Sheet sheet = wb.getSheetAt(0);

        Row row = sheet.getRow(0);
        Cell cell1 = row.getCell(0);


        System.out.println("cell[0]： " + cell1.getStringCellValue());

        cell1.setCellValue("AAA");

        Row row3 = sheet.getRow(2);
        Cell r3c1 = row3.getCell(0);
        if (r3c1 == null) {
            r3c1 = row3.createCell(0);
        }
        System.out.println("r3c1： " + r3c1.getStringCellValue());

        r3c1.setCellValue("R3C1");

        ins.close();


        try {
            FileOutputStream fout = new FileOutputStream("testOut.xlsx");
            wb.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
