package util;

public class config {

    private static String pdfPath = "";
    private static String outPath = "";

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
        return outPath;
    }
}
