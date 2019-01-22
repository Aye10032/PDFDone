package util;

import java.io.File;

public class config {

    private static String pdfPath = "";
    private static String outPath = "C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\PDFDone\\new.txt";
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

    public static String getOutName() {
        File outFile = new File(outPath);
        String path = outFile.getParent();
        String name = outFile.getName().split("\\.")[0];

        String OutName = path + "\\" + name;

        return OutName;
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
            case 4:
                type = ".png";
                break;
        }

        return type;
    }
}
