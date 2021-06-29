package com.huixu.excel;


import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {
    public static Object[][] getValueFromExcel(String file) throws Exception{
        List<String []> list=new ArrayList<String[]>();
        InputStream fileis=new FileInputStream(file);
        Workbook excel=Workbook.getWorkbook(fileis);
        Sheet sheet=excel.getSheet(0);
        Cell cell=null;
        for(int i=1;i<sheet.getRows();i++){
            String[] clos=new String[sheet.getColumns()];
            for(int j=0;j<sheet.getColumns();j++){
                cell=sheet.getCell(j, i);
                clos[j]=cell.getContents();
            }
            list.add(clos);
        }
        Object[][] data=new Object[list.size()][];
        for(int i=0;i<list.size();i++){
            data[i]=list.get(i);
        }

        fileis.close();
        return data;
    }
    @DataProvider(name="datafromexcel")
    public static Object[][] getdata() throws Exception {

        return ExcelUtil.getValueFromExcel("E:/cases.xls");
    }
}
