package com.huixu.log4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;





import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class Log4j {
        private static Logger logger=null;
        private static Log4j logg=null;

        public static Log4j getlogger(Class<?> T){
            if(logger==null){
//            实例化properties类，处理properties格式的文件
                Properties props=new Properties();
                try{
                    String path=System.getProperty("user.dir");
                    String filepath=path+"/configs/log4j.properties";
                    InputStream is=new FileInputStream(filepath);
                    props.load(is);
                }catch (IOException e){
                    e.printStackTrace();
                }
                PropertyConfigurator.configure(props);
                logger=Logger.getLogger(T);
                logg=new Log4j();
            }
            return logg;
        }

        public void info(Object msg){

            logger.info(msg);
        }
        public void debug(String msg){

            logger.debug(msg);
        }
        public void warn(String msg){

            logger.warn(msg);
        }
        public void err(String msg){

            logger.error(msg);

        }
    }








