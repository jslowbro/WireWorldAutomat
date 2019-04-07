package com.wireworld;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.text.NumberFormat;

@SuppressWarnings("Duplicates")

public class GUI {


    public static void createUserInterface() {
        JFrame guiframe = new JFrame("Wireworld");
        guiframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiframe.setSize(520, 700);

        JButton go = new JButton("Go");
        JButton stop = new JButton("Stop");
        JButton loadfile = new JButton("Load File");
        JButton onemove = new JButton("One Move");

        DemoJFileChooser inputFile = new DemoJFileChooser('i', loadfile);
        DemoJFileChooser outputDir = new DemoJFileChooser('o', loadfile);


        go.setBackground(Color.GREEN);
        stop.setBackground(Color.red);
        loadfile.setBackground(Color.CYAN);
        onemove.setBackground(Color.green);


        JCheckBox txt = new JCheckBox("TXT");
        JCheckBox png = new JCheckBox("PNG");

        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(-1);
        formatter.setMaximum(10001);
        formatter.setAllowsInvalid(false);
        JFormattedTextField textField = new JFormattedTextField(formatter);
        textField.setColumns(10);

        JMenuBar menuBar = new JMenuBar();
        JMenu interval = new JMenu("Interval");
        JMenuItem menuItem1 = new JMenuItem("1/8s");
        JMenuItem menuItem2 = new JMenuItem("1/4s");
        JMenuItem menuItem3 = new JMenuItem("1/2s");
        JMenuItem menuItem4 = new JMenuItem("1s");
        JMenuItem menuItem5 = new JMenuItem("2s");
        interval.add(menuItem1);
        interval.add(menuItem2);
        interval.add(menuItem3);
        interval.add(menuItem4);
        interval.add(menuItem5);
        menuBar.add(interval);


        JRadioButton moore = new JRadioButton("Moore");
        moore.setSelected(true);
        Options.getInstanceOf().setMoore(true);
        JRadioButton vonNeumann = new JRadioButton("vonNeumann");
        ButtonGroup sasiedztwo = new ButtonGroup();
        sasiedztwo.add(moore);
        sasiedztwo.add(vonNeumann);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        panel1.add(go);
        panel1.add(stop);
        panel1.add(loadfile);
        panel1.add(onemove);


        panel2.add(inputFile);
        panel2.add(outputDir);
        panel2.add(txt);
        panel2.add(png);

        panel3.add(moore);
        panel3.add(vonNeumann);
        panel3.add(textField);
        panel3.add(menuBar);


        Matrix matrix1 = new Matrix();
        Picture picture = new Picture();
        //picture.setBounds(0,0,500,500);
        picture.setAlignmentY(1.0f);
        panel1.setMaximumSize(new Dimension(1000, 100));
        panel2.setMaximumSize(new Dimension(1000, 100));
        panel3.setMaximumSize(new Dimension(1000, 100));

        BoxLayout boxLayout = new BoxLayout(guiframe.getContentPane(), BoxLayout.Y_AXIS);
        guiframe.setLayout(boxLayout);
        guiframe.setLocationRelativeTo(null);
        guiframe.add(panel3);
        guiframe.add(panel2);
        guiframe.add(panel1);
        guiframe.add(picture);
        guiframe.setVisible(true);

        go.setEnabled(false);
        stop.setEnabled(false);
        onemove.setEnabled(false);


        //Action Listenery dla poszczególnych Buttonów
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Options.getInstanceOf().setInterval(125);
            }
        });
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Options.getInstanceOf().setInterval(250);
            }
        });
        menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Options.getInstanceOf().setInterval(500);
            }
        });
        menuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Options.getInstanceOf().setInterval(1000);
            }
        });
        menuItem5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Options.getInstanceOf().setInterval(2000);
            }
        });
        go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Options.getInstanceOf().setStop(false);
                loadfile.setEnabled(false);
                onemove.setEnabled(false);
                moore.setEnabled(false);
                vonNeumann.setEnabled(false);
                txt.setEnabled(false);
                png.setEnabled(false);
                go.setEnabled(false);

                textField.setEnabled(false);
                interval.setEnabled(false);
                inputFile.setEnabled(false);
                outputDir.setEnabled(false);
                Thread Simulation = new Thread(new Thread() {
                    @Override
                    public void run() {
                        int value = 10;
                        try {
                            value = Integer.parseInt(textField.getValue().toString());
                        } catch (Exception e) {
                            value = 0;
                            textField.setText("0");
                            System.out.println("Exception Found");
                        }
                        while (!Options.getInstanceOf().isStop() && value > 0) {
                            try {
                                sleep(Options.getInstanceOf().getInterval());
                                value--;
                                textField.setValue(value);
                                IOManager.oneMove(matrix1);
                                picture.image = matrix1.generateImage();
                                guiframe.repaint();
                            } catch (InterruptedException e) {
                                stop.doClick();
                            }
                        }
                        stop.doClick();
                    }
                });
                Simulation.start();
            }
        });

        stop.addActionListener((ActionEvent e) -> {
            {
                Options.getInstanceOf().setStop(true);
                loadfile.setEnabled(true);
                onemove.setEnabled(true);
                moore.setEnabled(true);
                vonNeumann.setEnabled(true);
                txt.setEnabled(true);
                png.setEnabled(true);
                textField.setEnabled(true);
                interval.setEnabled(true);
                inputFile.setEnabled(true);
                outputDir.setEnabled(true);
                go.setEnabled(true);
            }
        });
        txt.addActionListener((ActionEvent e) -> {
            {
                if (Options.getInstanceOf().isTxt()) {
                    Options.getInstanceOf().setTxt(false);
                } else {
                    Options.getInstanceOf().setTxt(true);
                }
            }
        });

        png.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Options.getInstanceOf().isPng()) {
                    Options.getInstanceOf().setPng(false);
                } else {
                    Options.getInstanceOf().setPng(true);
                }
            }
        });
        moore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Options.getInstanceOf().setMoore(true);
            }
        });

        vonNeumann.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Options.getInstanceOf().setMoore(false);
            }
        });

        onemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (Integer.parseInt(textField.getValue().toString()) > 0) {
                        textField.setValue(Integer.parseInt(textField.getValue().toString()) - 1);
                        IOManager.oneMove(matrix1);
                        picture.image = matrix1.generateImage();
                        guiframe.repaint();
                    }
                } catch (Exception i) {
                    textField.setValue(Integer.parseInt("0"));
                }

            }
        });
        loadfile.addActionListener(e -> {
            {
                if (IOManager.loadFile(matrix1)) {
                    picture.image = matrix1.generateImage();
                    guiframe.repaint();
                    go.setEnabled(true);
                    stop.setEnabled(true);
                    onemove.setEnabled(true);
                }


            }
        });

    }

    public static class DemoJFileChooser extends JPanel
            implements ActionListener {
        JButton Bgo;

        JFileChooser chooser;
        String choosertitle;
        char io;
        JButton button;

        DemoJFileChooser(char io, JButton button) {
            if (io == 'i') {
                Bgo = new JButton("Input");
            } else {
                Bgo = new JButton("Output");
            }
            Bgo.addActionListener(this);
            add(Bgo);
            this.io = io;
        }


        public void actionPerformed(ActionEvent e) {

            chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle(choosertitle);
            if (io == 'o') {
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            }
            chooser.setAcceptAllFileFilterUsed(false);
            //
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                if (io == 'i') {
                    Options.getInstanceOf().setFilepath(chooser.getSelectedFile().getAbsolutePath());
                } else {
                    Options.getInstanceOf().setDirpath(chooser.getSelectedFile().getAbsolutePath());
                }
            } else {
                System.out.println("No Selection ");
            }
        }
    }

    public static class Picture extends JPanel {
        private BufferedImage image;


        public void paint(Graphics g) {
            g.drawImage(image, 0, 0, this);
        }
    }
}
