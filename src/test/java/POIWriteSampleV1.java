import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by kevin on 2015/1/8.
 */
public class POIWriteSampleV1 {
    public static void main(String[] args) throws Exception {

        String filename = "y.xlsx";
        String out = "o.xlsx";
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(filename));
        FileOutputStream stream = new FileOutputStream(out);
        HSSFSheet sheet = wb.getSheetAt(0);

        for (int k = 0; k < 25; k++) {
            HSSFRow row = sheet.getRow(k);

            sheet.removeRow(row);
        }
        for (int k = 74; k < 100; k++) {
            HSSFRow row = sheet.getRow(k);

            sheet.removeRow(row);
        }
        HSSFRow row = sheet.getRow(39);
        HSSFCell cell = row.getCell(3);
        cell.setCellValue("MODIFIED CELL!!!!!");

        wb.write(stream);
        stream.close();

    }
}
