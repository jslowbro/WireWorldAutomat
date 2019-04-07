package com.wireworld;

import javax.imageio.ImageIO;
import java.io.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class IOManager {
    private static int genSignature = 0;
    private static int genNumber = 0;

    public static boolean loadFile(Matrix matrix1) {
        FileReader filereader;
        boolean flag = true;
        try {
            Random rand = new Random();
            genSignature = rand.nextInt(10000);
            genNumber = 0;
            matrix1.clear();
            Set<Integer> legalSize = new HashSet<>();
            legalSize.add(25);
            legalSize.add(50);
            legalSize.add(100);
            filereader = new FileReader(Options.getInstanceOf().getFilepath());
            BufferedReader buffered = new BufferedReader(filereader);
            int matrixSize = Integer.parseInt(buffered.readLine());
            if (legalSize.contains(matrixSize)) {
                Options.getInstanceOf().setSize(new Coordinates(matrixSize, matrixSize, true));
                matrix1.setSize(new Coordinates(matrixSize + 2, matrixSize + 2, true)); // dodane powiększenie matrixa na pola X
            } else {
                System.err.println("Nielegalny rozmiar macierzy");
                flag = false;
            }
            Set<String> set1 = new HashSet<>();
            set1.add("DIODE");
            set1.add("XOR");
            set1.add("OR");
            set1.add("AND");
            String czytanaLinia;

            while ((czytanaLinia = buffered.readLine()) != null) {
                String[] sprawdzanaLinia;
                sprawdzanaLinia = czytanaLinia.split(" ");
                String bigSprawdzanaZero = sprawdzanaLinia[0].toUpperCase();
                if (set1.contains(bigSprawdzanaZero)) {
                    generateWireStructure(matrix1, sprawdzanaLinia);
                } else {
                    switch (bigSprawdzanaZero) {
                        case "WIRE":
                            Coordinates crd1 = new Coordinates(sprawdzanaLinia[1]);
                            Coordinates crd2 = new Coordinates(sprawdzanaLinia[2]);
                            if (crd1.x == crd2.x || crd1.y == crd2.y) {
                                matrix1.getWires().add(new Wire(crd1, crd2));
                            } else {
                                System.out.println("Niepoprawny kabel, nie dodano go");
                            }
                            break;
                        case "HEAD":
                            matrix1.getElectronHeads().add(new Coordinates(sprawdzanaLinia[1]));
                            break;
                        case "TAIL":
                            matrix1.getElectronTails().add(new Coordinates(sprawdzanaLinia[1]));
                            break;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("nie ma pliku");
            flag = false;
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
        } catch (NullPointerException e) {
            System.out.println("NULL");
            flag = false;
        }
        try {
            matrix1.insertIntoGrid();
        } catch (NullPointerException e) {
            System.out.println("nie ma katalogu");
            flag = false;
        }
        return flag;

    }


    private static void printToTxt(Matrix matrix) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Options.getInstanceOf().getDirpath() + "//" + genSignature + "genNo" + genNumber + ".txt"));
            bufferedWriter.write(String.valueOf(Options.getInstanceOf().getSize().x));
            bufferedWriter.flush();
            bufferedWriter.newLine();
            for (Wire wire : matrix.getWires()) {
                bufferedWriter.write(wire.toString());
                bufferedWriter.flush();
                bufferedWriter.newLine();

            }
            for (WireStructure wireStructure : matrix.getWireStructures()) {
                bufferedWriter.write(wireStructure.toString());
                bufferedWriter.flush();
                bufferedWriter.newLine();
            }
            for (Coordinates coordinates : matrix.getElectronHeads()) {
                bufferedWriter.write("HEAD " + coordinates.toString());
                bufferedWriter.flush();
                bufferedWriter.newLine();
            }
            for (Coordinates coordinates : matrix.getElectronTails()) {
                bufferedWriter.write("TAIL " + coordinates.toString());
                bufferedWriter.flush();
                bufferedWriter.newLine();
            }
        } catch (IOException | NullPointerException e) {
            System.out.println("Nie ma katalogu");
        }
    }

    private static void printToPng(Matrix matrix) {
        try {
            File outputFilePng = new File(Options.getInstanceOf().getDirpath() + "//" + genSignature + "genNo" + genNumber + ".png");
            ImageIO.write(matrix.generateImage(), "png", outputFilePng);
        } catch (IOException | NullPointerException e) {
            System.out.println("Nie ma katalogu");
        }
    }

    public static void oneMove(Matrix matrix1) {
        Simulator.Simulate(matrix1);
        //printGridStdIn(matrix1.getGrid(), matrix1.getSize());
        if (Options.getInstanceOf().isTxt()) {
            printToTxt(matrix1);
        }
        if (Options.getInstanceOf().isPng()) {
            printToPng(matrix1);
        }
        genNumber++;
    }

    private static void generateWireStructure(Matrix matrix1, String[] strings) {
        String bigOrientation = strings[1].toUpperCase();
        switch (strings[0].toUpperCase()) {
            case "DIODE":
                Diode diode = new Diode(new Coordinates(strings[2]), bigOrientation.charAt(0));
                if (isLegalWireStructure(matrix1, diode)) {
                    matrix1.getWireStructures().add(diode);
                }
                break;
            case "XOR":
                XOR xor = new XOR(new Coordinates(strings[2]), bigOrientation.charAt(0));
                if (isLegalWireStructure(matrix1, xor)) {
                    matrix1.getWireStructures().add(xor);
                }
                break;
            case "AND":
                AND and = new AND(new Coordinates(strings[2]), bigOrientation.charAt(0));
                if (isLegalWireStructure(matrix1, and)) {
                    matrix1.getWireStructures().add(and);
                }
                break;
            case "OR":
                OR or = new OR(new Coordinates(strings[2]), bigOrientation.charAt(0));
                if (isLegalWireStructure(matrix1, or)) {
                    matrix1.getWireStructures().add(or);
                }
                break;
            default:
                System.err.println("Cos sie popsulo i nie bylo mnie słychac");

        }
    }

    public static void printGridStdIn(char[][] grid, Coordinates size) {
        for (int i = 0; i < size.y; i++) {
            for (int j = 0; j < size.x; j++) {
                System.out.print(grid[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println("\n\n");
    }

    private static boolean isLegalWireStructure(Matrix matrix1, WireStructure wireStructure) {
        if (wireStructure.getCoordinates().x + wireStructure.size.x > matrix1.getSize().x) {
            System.out.println("Struktura nie miesci sie na planszy, bo jest zbyt szeroka");
            return false;
        }
        if (wireStructure.getCoordinates().y + wireStructure.size.y > matrix1.getSize().y) {
            System.out.println("Struktura nie miesci sie na planszy, bo jest zbyt dluga");
            return false;
        }
        return true;
    }

}
