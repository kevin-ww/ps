import junit.framework.Assert;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;

/**
 * Created by kevin on 2015/1/7.
 */
public class POIWriteSample {

    //    SXSSF (Streaming Usermodel API)
//    SXSSF (package: org.apache.poi.xssf.streaming) is an API-compatible streaming extension of XSSF to be used when very large spreadsheets have to be produced, and heap space is limited. SXSSF achieves its low memory footprint by limiting access to the rows that are within a sliding window, while XSSF gives access to all rows in the document. Older rows that are no longer in the window become inaccessible, as they are written to the disk.
//
//    You can specify the window size at workbook construction time via new SXSSFWorkbook(int windowSize) or you can set it per-sheet via SXSSFSheet#setRandomAccessWindowSize(int windowSize)
//
//    When a new row is created via createRow() and the total number of unflushed records would exceed the specified window size, then the row with the lowest index value is flushed and cannot be accessed via getRow() anymore.
//
//            The default window size is 100 and defined by SXSSFWorkbook.DEFAULT_WINDOW_SIZE.
//
//    A windowSize of -1 indicates unlimited access. In this case all records that have not been flushed by a call to flushRows() are available for random access.
//
//    Note that SXSSF allocates temporary files that you must always clean up explicitly, by calling the dispose method.
//
//    SXSSFWorkbook defaults to using inline strings instead of a shared strings table. This is very efficient, since no document content needs to be kept in memory, but is also known to produce documents that are incompatible with some clients. With shared strings enabled all unique strings in the document has to be kept in memory. Depending on your document content this could use a lot more resources than with shared strings disabled.
//
//    Carefully review your memory budget and compatibility needs before deciding whether to enable shared strings or not.

    public static void main(String[] args) throws Throwable {
        SXSSFWorkbook wb = new SXSSFWorkbook(100); // keep 100 rows in memory, exceeding rows will be flushed to disk
        Sheet sh = wb.createSheet();
        for (int rownum = 0; rownum < 1000; rownum++) {
            Row row = sh.createRow(rownum);
            for (int cellnum = 0; cellnum < 10; cellnum++) {
                Cell cell = row.createCell(cellnum);
                String address = new CellReference(cell).formatAsString();
                cell.setCellValue(address);
            }

        }

        // Rows with rownum < 900 are flushed and not accessible
        for (int rownum = 0; rownum < 900; rownum++) {
            Assert.assertNull(sh.getRow(rownum));
        }

        // ther last 100 rows are still in memory
        for (int rownum = 900; rownum < 1000; rownum++) {
            Assert.assertNotNull(sh.getRow(rownum));
        }

        FileOutputStream out = new FileOutputStream("sxssf.xlsx");
        wb.write(out);
        out.close();

        // dispose of temporary files backing this workbook on disk
//        wb.dispose();
    }
}
