package com.huixu.mysql;

import org.testng.annotations.DataProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlDBdriver {

    public static Connection getConnection() throws Exception
        {
            Class.forName("com.mysql.jdbc.Driver");

            String dburl="jdbc:mysql://localhost:3306/test";
            String username="root";
            String password="123456";
            Connection conn= DriverManager.getConnection(dburl,username,password);
            return conn;
        }
        @DataProvider(name="datafrommysql")
        public static Object[][] getdatafromMysql() throws Exception{
            Connection conn= MysqlDBdriver.getConnection();
            List<String[]> list=new ArrayList<>();

            String sql="select * from users_mysql";
            PreparedStatement state=conn.prepareStatement(sql);
            ResultSet rs=state.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();
            int cols=rsmd.getColumnCount();//获取结果集的列数
            int j=0;
            while (rs.next()){

                j++;
                String temp[] = new String[cols];
                for(int i=1;i<rsmd.getColumnCount()+1;i++) {
                    temp[i-1]= DataFromResult.getDataValue(rsmd,rs,i);
                }
                list.add(temp);
            }
            Object[][] data=new Object[list.size()][];
            for(int i=0;i<list.size();i++){
                data[i]=list.get(i);
            }
            state.close();
            conn.close();
            return data;
        }

        public static void insertData()throws  Exception{
          //  批量处理数据   批量插入数据
		 String sql="insert into users values(?,?)";
		 Connection conn=MysqlDBdriver.getConnection();
		 PreparedStatement state=conn.prepareStatement(sql);
		 for(int i=1;i<=100;i++){
			 state.setString(1, "zhangsa"+i);
			 state.setString(2, ""+i);
			 state.addBatch();//使用addBatch()来缓存数据，将多条sql语句缓存起来，
		 }
		 state.executeBatch();//再通过executeBatch()方法一次性发给数据库，提高执行效率。
		 state.clearBatch();//清除缓存
		 state.close();
		 conn.close();

        }
    }


