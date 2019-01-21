import util.config;
import util.pdfdeal;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class runtest {

    public static void main(String[] args) {
        System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");

        String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int windowWidth = 600;
        int windowHeight = 700;

        pdfGUI window1 = new pdfGUI();
        window1.setTitle("PDF转换 V0.1");
        window1.setBounds((screenWidth - windowWidth) / 2, (screenHeight - windowHeight) / 2, windowWidth, windowHeight);
        window1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window1.show();
    }

}
