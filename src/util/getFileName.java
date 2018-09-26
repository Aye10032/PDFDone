package util;

import java.io.File;

public class getFileName {

    private static File file;

    public static String filename(String filePath){

        file = new File(filePath);
        String allname = file.getName();

        String name = allname.split("\\.")[0];

        return name;
    }

}
