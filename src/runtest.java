import util.config;
import util.pdfdeal;

import java.io.IOException;

public class runtest {

    public static void main(String[] args) {
        config.setPdfPath("C:\\Users\\Aye10032\\Desktop\\test.pdf");
        config.setOutPath("C:\\Users\\Aye10032\\Desktop\\test.txt");
        try {
            new pdfdeal();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
