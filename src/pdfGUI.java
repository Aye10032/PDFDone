import util.LayoutUtil;
import util.config;
import util.pdfdeal;
import util.getFileName;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class pdfGUI extends JFrame implements ActionListener, DropTargetListener {

    private DropTarget dropTarget;

    JLabel chooseJL = new JLabel("PDF路径：");
    JTextField chooseJF = new JTextField("");
    JButton chooseJB = new JButton("…");
    JFileChooser chooserInput = new JFileChooser();
    JLabel outputJL = new JLabel("输出路径：");
    JTextField outputJF = new JTextField("");
    JButton outputJB = new JButton("…");
    JFileChooser chooserOutPut = new JFileChooser();

    JRadioButton txt = new JRadioButton("txt");
    JRadioButton rtf = new JRadioButton("rtf");
    JRadioButton word = new JRadioButton("word");
    JRadioButton img = new JRadioButton("图片");
    ButtonGroup whic = new ButtonGroup();

    JButton splitIMG = new JButton();

    JButton open = new JButton("打开文件");
    JButton openFile = new JButton("打开文件夹");
    JButton start = new JButton("开始");

    public pdfGUI(){
        chooserInput.setFileFilter(new choosetFileFilter());

        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createEtchedBorder());
        p.setLayout(new GridBagLayout());

        LayoutUtil.add(p, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 1, 1, 0, 0, 6, 1, new JLabel());

        LayoutUtil.add(p, GridBagConstraints.NONE, GridBagConstraints.CENTER, 0, 0, 0, 1, 1, 1, chooseJL, new Insets(10, 5, 10, 5));
        LayoutUtil.add(p, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, 1, 0, 1, 1, 4, 1, chooseJF, new Insets(10, 5, 10, 5));
        LayoutUtil.add(p, GridBagConstraints.NONE, GridBagConstraints.CENTER, 0, 0, 6, 1, 1, 1, chooseJB, new Insets(10, 5, 10, 5));
        LayoutUtil.add(p, GridBagConstraints.NONE, GridBagConstraints.CENTER, 0, 0, 0, 2, 1, 1, outputJL, new Insets(10, 5, 10, 5));
        LayoutUtil.add(p, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, 1, 0, 1, 2, 4, 1, outputJF, new Insets(10, 5, 10, 5));
        LayoutUtil.add(p, GridBagConstraints.NONE, GridBagConstraints.CENTER, 0, 0, 6, 2, 1, 1, outputJB, new Insets(10, 5, 10, 5));

        LayoutUtil.add(p, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 1, 1, 0, 3, 6, 1, new JLabel());

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(4, 0));
        p1.setBorder(BorderFactory.createTitledBorder("输出文件类型："));

        word.setEnabled(false);
        whic.add(txt);
        p1.add(txt);
        whic.add(rtf);
        p1.add(rtf);
        whic.add(word);
        p1.add(word);
        whic.add(img);
        p1.add(img);

        if (config.getFlag() == 1) {
            txt.setSelected(true);
        }else if (config.getFlag() == 2){
            rtf.setSelected(true);
        } else if (config.getFlag() == 3) {
            word.setSelected(true);
        } else if (config.getFlag() == 4) {
            img.setSelected(true);
        }

        LayoutUtil.add(p, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 1, 1, 0, 4, 4, 1, p1);

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(4, 0));
        p2.setBorder(BorderFactory.createTitledBorder("其他功能："));

        LayoutUtil.add(p, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 1, 1, 4, 4, 4, 1, p2);

        getContentPane().add(p, BorderLayout.CENTER);

        JPanel dp = new JPanel();
        dp.setBorder(BorderFactory.createEtchedBorder());
        dp.setLayout(new GridBagLayout());

        LayoutUtil.add(dp, GridBagConstraints.NONE, GridBagConstraints.CENTER, 0, 0, 0, 0, 1, 1, start,new Insets(5,10,5,10));
        LayoutUtil.add(dp, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, 1, 0, 1, 0, 1, 1, new JLabel());
        LayoutUtil.add(dp, GridBagConstraints.NONE, GridBagConstraints.CENTER, 0, 0, 2, 0, 1, 1, open,new Insets(5,10,5,10));
        LayoutUtil.add(dp, GridBagConstraints.NONE, GridBagConstraints.CENTER, 0, 0, 3, 0, 1, 1, openFile,new Insets(5,10,5,10));

        getContentPane().add(dp, BorderLayout.SOUTH);

        chooseJB.addActionListener(this);
        outputJB.addActionListener(this);
        start.addActionListener(this);
        txt.addActionListener(this);
        rtf.addActionListener(this);
        word.addActionListener(this);
        img.addActionListener(this);
        open.addActionListener(this);
        openFile.addActionListener(this);

        dropTarget = new DropTarget(chooseJF, DnDConstants.ACTION_COPY_OR_MOVE, this, true);
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {

    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragExit(DropTargetEvent dte) {

    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        try {
            if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                java.util.List list = (List) (dtde.getTransferable()
                        .getTransferData(DataFlavor.javaFileListFlavor));
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    File f = (File) iterator.next();
                    chooseJF.setText(f.getAbsolutePath());
                    config.setPdfPath(chooseJF.getText());
                }
                dtde.dropComplete(true);
            } else {
                dtde.rejectDrop();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (UnsupportedFlavorException ufe) {
            ufe.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == start){
            if (chooseJF.getText().equals("") || outputJF.getText().equals("")){
                JOptionPane.showMessageDialog(this, "请正确选择目录！", "warning", JOptionPane.YES_NO_OPTION);
            }else {
                config.setPdfPath(chooseJF.getText());
                config.setOutPath(outputJF.getText());
                //System.out.println(getFileName.filename(config.getPdfPath()));
                try {
                    new pdfdeal(config.getFlag());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }finally {
                    JOptionPane.showMessageDialog(this, "处理完毕！", "warning", JOptionPane.PLAIN_MESSAGE);
                }
            }
        }else if (source == chooseJB){
            int result = chooserInput.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) ;
            chooseJF.setText(chooserInput.getSelectedFile().getAbsolutePath());
            config.setPdfPath(chooseJF.getText());
        }else if (source == outputJB){
            File outFile = new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\PDFDone");
            if (!outFile.exists()){
                outFile.mkdirs();
            }
            chooserOutPut.setCurrentDirectory(new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\PDFDone"));
            chooserOutPut.setSelectedFile(new File(getFileName.filename(config.getPdfPath()) + config.getType()));
            chooserOutPut.setFileFilter(new saveFileFilter());
            int result = chooserOutPut.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION);
            outputJF.setText(chooserOutPut.getSelectedFile().getAbsolutePath());
            config.setOutPath(outputJF.getText());
        }else if (source == txt){
            if (outputJF.getText().equals("")){
                JOptionPane.showMessageDialog(this, "请先选择输出目录！", "warning", JOptionPane.YES_NO_OPTION);
            }else {
                config.setFlag(1);
                outputJF.setText(config.getOutPath());
                config.setOutPath(outputJF.getText());
            }
        }else if (source == rtf){
            if (outputJF.getText().equals("")){
                JOptionPane.showMessageDialog(this, "请先选择输出目录！", "warning", JOptionPane.YES_NO_OPTION);
            }else {
                config.setFlag(2);
                outputJF.setText(config.getOutPath());
                config.setOutPath(outputJF.getText());
            }
        } else if (source == img) {
            if (outputJF.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "请先选择输出目录！", "warning", JOptionPane.YES_NO_OPTION);
            } else {
                config.setFlag(4);
                outputJF.setText(config.getOutPath());
                config.setOutPath(outputJF.getText());
            }
        }else if (source == open){
            if (outputJF.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "请先选择输出目录！", "warning", JOptionPane.YES_NO_OPTION);
            }else {
                try {
                    Desktop.getDesktop().open(new File(outputJF.getText()));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }else if (source == openFile){
            if (outputJF.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "请先选择输出目录！", "warning", JOptionPane.YES_NO_OPTION);
            }else {
                try {
                    Desktop.getDesktop().open(new File(new File(outputJF.getText()).getParent()));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}

class choosetFileFilter extends javax.swing.filechooser.FileFilter implements FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.isDirectory())
        {
            return true;
        }

        String fileName = f.getName();
        String extension = null;
        int index = fileName.lastIndexOf('.');
        if (index > 0 && index < fileName.length() - 1) {
            extension = fileName.substring(index + 1).toLowerCase();
        }
        if (extension != null)
        {
            if (extension.equals("pdf") || extension.equals("PDF"))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "便携式文档(*.pdf)";
    }
}

class saveFileFilter extends javax.swing.filechooser.FileFilter implements FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.isDirectory())
        {
            return true;
        }

        if (f.getName().endsWith(config.getType())) {
            return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        String des = null;

        switch (config.getFlag()){
            case 1:
                des = "文本文档(*.txt)";
                break;
            case 2:
                des = "RTF格式(*.rtf)";
                break;
            case 4:
                des = "便携式网络图形PNG(*.png)";
        }

        return des;
    }
}