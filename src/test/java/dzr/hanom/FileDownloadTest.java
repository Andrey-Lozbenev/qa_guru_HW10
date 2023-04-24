package dzr.hanom;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileDownloadTest {

    private ClassLoader cl = FileDownloadTest.class.getClassLoader();

    @Test
    void zipTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("testZip.zip");
             ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zs.getNextEntry()) != null) {

                if (entry.getName().contains("testPdf")) {
                    PDF pdf = new PDF(zs);
                    Assertions.assertTrue((pdf.text).contains("Счет на оплату"));
                } else if (entry.getName().contains("testCsv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zs));
                    List<String[]> content = csvReader.readAll();
                    Assertions.assertArrayEquals(new String[]{"Организация", "Тип", "Email"}, content.get(0));
                } else if (entry.getName().contains("testXlsx")) {
                    XLS xls = new XLS(zs);
                    Assertions.assertTrue(
                            xls.excel.getSheetAt(0).getRow(1).getCell(4).getStringCellValue()
                                    .startsWith("Оплата есть и привязана"));
                }

            }
        }
    }
}
