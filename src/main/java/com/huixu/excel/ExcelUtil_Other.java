package com.huixu.excel;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import static java.awt.image.ImageObserver.ERROR;
import static java.sql.Types.BOOLEAN;
import static java.sql.Types.NUMERIC;
import static org.apache.poi.ss.usermodel.DataValidationConstraint.ValidationType.FORMULA;
import static org.apache.xmlbeans.impl.piccolo.xml.Piccolo.STRING;

public class ExcelUtil_Other {
    public static Workbook getWorkbook(String filePath){
        Workbook wb=null;
        try{
            if(filePath.endsWith(".xls")){
                File file=new File(filePath);
                InputStream is=new FileInputStream(file);
                wb=new HSSFWorkbook(is);
            }else if(filePath.endsWith(".xlsx")){
                wb=new XSSFWorkbook(filePath);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return wb;
    }
    public static String getCellValue1(Sheet sheet, int rowNum, int cellNum){
        Cell cell=sheet.getRow(rowNum).getCell(cellNum);
        //System.out.println("cell is "+cell);
        String value=ExcelUtil_Other.getCellValue(cell);
        return value;
    }
    public static String getCellValue(Cell cell){
        String value="";
        //System.out.println("type is +++++"+cell.getCellType());
        switch (cell.getCellType()){
            case 1 :
                value=String.valueOf(cell.getRichStringCellValue());
                //System.out.println("value="+value);
                return value;
            case NUMERIC :
                value=String.valueOf(cell.getNumericCellValue());
                //System.out.println("value="+value);
                return value;
            case BOOLEAN :
                value=String.valueOf(cell.getBooleanCellValue());
                //System.out.println("value="+value);
                return value;
            case FORMULA :
                value=String.valueOf(cell.getCellFormula());
                //System.out.println("value="+value);
                return value;
            case ERROR :
                value=String.valueOf(cell.getErrorCellValue());
                //System.out.println("value="+value);
                return value;
            default :
                return value;
        }

    }

    public static Object[][] testData(String file){
        ArrayList<String> arrKey=new ArrayList<String>();
        Workbook workbook=ExcelUtil_Other.getWorkbook(file);
        Sheet sheet=workbook.getSheetAt(0);
        int rowTotalNum=sheet.getLastRowNum()+1;
        int columns=sheet.getRow(0).getPhysicalNumberOfCells();

        HashMap<String,String>[][] map=new HashMap[rowTotalNum-1][1];
        if(rowTotalNum>1){
            for(int i=0;i<rowTotalNum-1;i++){
                map[i][0]=new HashMap();
            }
        }else{
            System.out.println("行数小鱼1");
        }
        for(int c=0;c<columns;c++){
            String cellvalue=ExcelUtil_Other.getCellValue1(sheet,0,c);
            arrKey.add(cellvalue);

        }
        for(int r=1;r<rowTotalNum;r++){
            for(int c=0;c<columns;c++){

                String cellvalue=ExcelUtil_Other.getCellValue1(sheet,r,c);
                map[r-1][0].put(arrKey.get(c),cellvalue);

            }
        }
        return map;
    }
    @DataProvider(name="datafromeExcel_other")
    public Object[][] getDataFromeExcel(){

        return ExcelUtil_Other.testData("E:/users.xls");
    }


    @Test(dataProvider = "datafromeExcel_other")
    public void testExcelUtil_other(HashMap<String,String> data){
        System.out.println("1"+data.get("username"));
        System.out.println("2"+data.get("pwd"));
    }
}
