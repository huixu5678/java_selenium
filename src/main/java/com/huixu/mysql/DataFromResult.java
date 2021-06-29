package com.huixu.mysql;

import com.huixu.tools.StringConvert;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

public class DataFromResult {
    public static String getDataValue(ResultSetMetaData rsmd, ResultSet rs, int i)
    {
                 String strValue = "";

                 try
                 {
                        int dataType = rsmd.getColumnType(i);
                        String name = rsmd.getColumnTypeName(i);
                        System.out.println("sql类型名称为："+name);
                        int dataScale = rsmd.getScale(i);
                        int dataPrecision = rsmd.getPrecision(i);
                        //数据类型为字符
                        if ((dataType == Types.CHAR) || (dataType == Types.VARCHAR))
                              {
                                  //由于存入数据库的数据是GBK模式，因此没有必要做一次unicodeToGBK
                  //                strValue = StrTool.unicodeToGBK(rs.getString(i));
                              strValue = rs.getString(i);
                            }
                      //数据类型为日期、时间
                        else if ((dataType == Types.TIMESTAMP) || (dataType == Types.DATE))
                           {

                                 strValue= StringConvert.toString(rs.getDate(i),"yyyy-MM-dd HH:mm:ss");

                           }
                    //数据类型为浮点
                        else if ((dataType == Types.DECIMAL) || (dataType == Types.FLOAT))
                          {
                                 //strValue = String.valueOf(rs.getFloat(i));
                                 //采用下面的方法使得数据输出的时候不会产生科学计数法样式
                                  strValue = String.valueOf(rs.getBigDecimal(i));
                                 //去零处理
                                 //strValue = PubFun.getInt(strValue);
                          }
                       //数据类型为整型
                      else if ((dataType == Types.INTEGER) || (dataType == Types.SMALLINT))
                           {
                                 strValue = String.valueOf(rs.getInt(i));
                               //strValue = PubFun.getInt(strValue);


                            }
                         //数据类型为浮点
                        else if (dataType == Types.NUMERIC || dataType == Types.BIGINT)
                            {
                               if (dataScale == 0)
                                   {
                                        if (dataPrecision == 0)
                                        {
                                                //strValue = String.valueOf(rs.getDouble(i));
                                                 //采用下面的方法使得数据输出的时候不会产生科学计数法样式
                                                strValue = String.valueOf(rs.getBigDecimal(i));
                                           }
                                        else
                                       {
                                              strValue = String.valueOf(rs.getLong(i));
                                           }
                                  }
                                else
                               {
                                         //strValue = String.valueOf(rs.getDouble(i));
                                         //采用下面的方法使得数据输出的时候不会产生科学计数法样式
                                         strValue = String.valueOf(rs.getBigDecimal(i));
                                   }
                             //strValue = PubFun.getInt(strValue);
                            }

                 }
              catch (SQLException ex)
              {
                       ex.printStackTrace();
                    }

               // return PubFun.cTrim(strValue);
        return strValue;
             }
}
