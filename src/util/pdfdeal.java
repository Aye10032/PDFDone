package util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

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

    public pdfdeal() throws IOException {
        pdffile = new File(config.getPdfPath());
        PDFdoc = PDDocument.load(pdffile);
        outfile = new File(config.getOutPath());
        page = PDFdoc.getNumberOfPages();

        pdfRead();
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

}
