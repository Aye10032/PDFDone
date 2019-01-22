package util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class pdfdeal {

    File pdffile = null;
    File outfile = null;
    PDDocument PDFdoc = null;
    int page;
    String content = null;
    OutputStream os = null;

    public pdfdeal(int flag) throws IOException {
        pdffile = new File(config.getPdfPath());
        PDFdoc = PDDocument.load(pdffile);
        outfile = new File(config.getOutPath());
        page = PDFdoc.getNumberOfPages();

        if (flag == 1 || flag == 2) {
            pdfRead();
        } else if (flag == 4) {
            pdftoimg();
        }

    }

    private void pdfRead() throws IOException {
        PDFTextStripper stripper = new PDFTextStripper();

        stripper.setSortByPosition(true);
        stripper.setStartPage(1);
        stripper.setEndPage(page);
        content = stripper.getText(PDFdoc);
        os = new FileOutputStream(outfile,true);

        byte[] data = content.getBytes();
        os.write(data, 0, data.length);

        os.flush();
    }

    private void pdftoimg() {
        try {
            PDDocument document = PDDocument.load(new File(config.getPdfPath()));
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            int pageCounter = 0;
            for (PDPage page : document.getPages()) {
                // note that the page number parameter is zero based
                BufferedImage bim = pdfRenderer.renderImageWithDPI(pageCounter, 300, ImageType.RGB);

                // suffix in filename will be used as the file format
                ImageIOUtil.writeImage(bim, config.getOutName() + "-" + (pageCounter++) + ".png", 300);
            }
            document.close();
        } catch (InvalidPasswordException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
