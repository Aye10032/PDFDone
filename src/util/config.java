package util;

import java.io.File;

public class config {

    private static String pdfPath = "";
    private static String outPath = "";
    private static int flag = 1;

    public static String getPdfPath() {
        return pdfPath;
    }

    public static void setPdfPath(String pdfPath) {
        config.pdfPath = pdfPath;
    }

    public static void setOutPath(String outPath) {
        config.outPath = outPath;
    }

    public static String getOutPath() {

        File outFile = new File(outPath);
        String path = outFile.getParent();
        String name = outFile.getName().split("\\.")[0];

        outPath = path+"\\"+name+getType();
        return outPath;
    }

    public static void setFlag(int flag) {
        config.flag = flag;
    }

    public static int getFlag() {
        return flag;
    }

    public static String getType() {

        String type = ".txt";

        switch (flag){
            case 1:
                type = ".txt";
                break;
            case 2:
                type = ".rtf";
                break;
        }

        return type;
    }
}
