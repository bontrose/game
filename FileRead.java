package group_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileRead {

    public static String open(String filename) throws IOException {
        FileReader fileIn = new FileReader(filename);
        BufferedReader buffy = new BufferedReader(fileIn);
        String list="";
        String line="";
        while ((line = buffy.readLine()) != null) {
            list=line;
        }
        buffy.close();
        return list;
    }
}
