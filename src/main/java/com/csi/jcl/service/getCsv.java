package com.csi.jcl.service;

import com.csvreader.CsvReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class getCsv {
    public static XSSFWorkbook getWorkbookByCsv(InputStream inputStream) throws IOException {

        ArrayList<String[]> dataList = new ArrayList<>();

        // 文件的编码，这里设为GB2312
        CsvReader reader = new CsvReader(inputStream, ',', Charset.forName("utf-8"));

        while (reader.readRecord()) {
            dataList.add(reader.getValues());
        }
        reader.close();

        XSSFWorkbook result = new XSSFWorkbook();
        XSSFSheet sheet = result.createSheet("new sheet");

        for (int rowNum = 0; rowNum < dataList.size(); rowNum++) {
            String[] data = dataList.get(rowNum);
            XSSFRow row = sheet.createRow(rowNum);
            for (int columnNum = 0; columnNum < data.length; columnNum++) {
                Cell cell = row.createCell(columnNum);
                cell.setCellValue(data[columnNum]);
            }
        }
        return result;
    }
}
