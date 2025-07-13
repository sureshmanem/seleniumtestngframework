// src/test/java/com/mycompany/automation/utils/ExcelUtil.java
package com.suresh.automation.utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    public static List<Map<String, String>> getTestData(String filePath, String sheetName) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            
            XSSFSheet sheet = workbook.getSheet(sheetName);
            List<Map<String, String>> testDataList = new ArrayList<>();
            Row headerRow = sheet.getRow(0);
            DataFormatter formatter = new DataFormatter();
            
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row currentRow = sheet.getRow(i);
                if (currentRow == null) {
                    continue;
                }
                
                Map<String, String> rowMap = new HashMap<>();
                for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                    String key = headerRow.getCell(j).getStringCellValue();
                    if (currentRow.getCell(j) != null) {
                        String value = formatter.formatCellValue(currentRow.getCell(j)).trim();
                        rowMap.put(key, value);
                    }
                }
                
                if (rowMap.containsKey("FirstName") && rowMap.get("FirstName") != null && !rowMap.get("FirstName").isEmpty()) {
                   testDataList.add(rowMap);
                }
            }
            return testDataList;
        }
    }
}
