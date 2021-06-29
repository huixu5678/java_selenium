package com.huixu.tools;

import java.io.File;

public class File_my {

    public static boolean fileExists(String filePath){
        return new File(filePath).exists();
    }
    public static void createFile(String filePath){
        if(!File_my.fileExists(filePath)){
            File file=new File(filePath);
            file.mkdir();
        }
    }
}
