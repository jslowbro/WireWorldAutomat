package com.wireworld;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class Matrix {
    private Coordinates size;
    private char[][] grid;
    private ArrayList<WireStructure> wireStructures = new ArrayList<>();
    private ArrayList<Wire> wires = new ArrayList<>();
    private ArrayList<Coordinates> electronTails = new ArrayList<>();
    private ArrayList<Coordinates> electronHeads = new ArrayList<>();


    public char[][] getGrid() {
        return grid;
    }


    public ArrayList<WireStructure> getWireStructures() {
        return wireStructures;
    }


    public ArrayList<Wire> getWires() {
        return wires;
    }


    public Coordinates getSize() {
        return size;
    }

    public void setSize(Coordinates size) {

        this.size = size;
    }

    public ArrayList<Coordinates> getElectronTails() {
        return electronTails;
    }


    public ArrayList<Coordinates> getElectronHeads() {
        return electronHeads;
    }


    public void copyContentsOfMatrixTo(Matrix matrix2) {
        matrix2.setSize(new Coordinates(this.size.x, this.size.y));
        matrix2.getWireStructures().addAll(this.getWireStructures());
        matrix2.getWires().addAll(this.getWires());
        matrix2.getElectronHeads().addAll(this.getElectronHeads());
        matrix2.getElectronTails().addAll(this.getElectronTails());
    }

    private void createBlankGrid() {
        this.grid = new char[size.x][size.y];
        //wypełniamy zerami
        for (int i = 0; i < size.y; i++) {
            for (int j = 0; j < size.x; j++) {
                grid[i][j] = '0';
            }
        }

        //wypełniamy X
        for (int i = 0; i < size.x; i++) {
            grid[i][0] = grid[i][size.y - 1] = 'X';
        }
        for (int i = 0; i < size.y; i++) {
            grid[0][i] = grid[size.x - 1][i] = 'X';
        }
    }

    public void insertIntoGrid() {

        createBlankGrid();
        //kladziemy kabelki
        InsertWires();
        //kladziemy struktury logiczne
        InsertStructures();
        //dodajmey prad
        InsertHeadsNTails();

    }

    private void InsertStructures() {
        for (WireStructure wireStructure : wireStructures) {
            Coordinates tempCords = wireStructure.getCoordinates();
            Coordinates tempSize = wireStructure.size;
            if (tempSize.x + tempCords.x > this.size.x - 2) { // wyjątek wypala w razie wyjścia poza macierz, ale jest wypisywany w trakcie wypluwania macierzy!
                throw new AssertionError("Struktura została dodana w niepoprawnym miejscu i wychodzi poza macierz.");
            }
            for (int i = 0; i < tempSize.y; i++) {
                for (int j = 0; j < tempSize.x; j++) {
                    try {
                        this.grid[j + tempCords.x + 1][i + tempCords.y + 1] = wireStructure.grid[i][j]; //uwzględnione zostało przesunięcie o jeden w prawo i w dół
                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        /*System.out.println("Dodano struktury logiczne :: \n\n\n");
        IOManager.printGridStdIn(this.grid, this.size);*/
    }

    private void InsertWires() {
        for (Wire wire : wires) {
            Coordinates tempStart = wire.getStartCoordinates();
            Coordinates tempEnd = wire.getEndCoordinates();
            //sprawdzamy czy kabel idzie poziomo, jesli tak to:
            if (tempStart.y == tempEnd.y) {
                for (int j = Math.min(tempStart.x, tempEnd.x); j < Math.max(tempStart.x, tempEnd.x) + 1; j++) {
                    this.grid[j + 1][tempEnd.y + 1] = '1'; //zmienione wartości dla przesunięcia kabla w prawo i w dół
                }
            } else {
                for (int j = Math.min(tempStart.y, tempEnd.y); j < Math.max(tempStart.y, tempEnd.y) + 1; j++) {
                    this.grid[tempEnd.x + 1][j + 1] = '1'; //zmienione wartości dla przesunięcia kabla w prawo i w dół
                }
            }
        }
        /*System.out.println("Kabelki polozone :: \n\n\n");
        IOManager.printGridStdIn(this.grid, this.size);*/
    }

    private void InsertHeadsNTails() {
        for (Coordinates heads : electronHeads) {
            if (this.grid[heads.x + 1][heads.y + 1] == '1') {
                this.grid[heads.x + 1][heads.y + 1] = '2';
            }
        }
        for (Coordinates tails : electronTails) {
            if (this.grid[tails.x + 1][tails.y + 1] == '1') {
                this.grid[tails.x + 1][tails.y + 1] = '3';
            }
        }
        /*System.out.println("Plynie prad :: \n\n\n");
        IOManager.printGridStdIn(this.grid, this.size);*/
    }

    public void clear() {
        this.wireStructures.clear();
        this.electronTails.clear();
        this.wires.clear();
        this.electronHeads.clear();
    }

    public BufferedImage generateImage() {
        BufferedImage newImage = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        HashMap<Character, Color> colorHashMap = new HashMap<>();
        colorHashMap.put('0', Color.BLACK);
        colorHashMap.put('1', Color.YELLOW);
        colorHashMap.put('2', Color.BLUE);
        colorHashMap.put('3', Color.RED);
        for (int i = 1; i < this.size.y - 1; i++) {
            for (int j = 1; j < this.size.x - 1; j++) {
                for (int p = (i - 1) * (newImage.getHeight() / (this.size.y - 2)); p < i * (newImage.getHeight() / (this.size.y - 2)); p++) {
                    for (int n = (j - 1) * (newImage.getWidth() / (this.size.x - 2)); n < j * (newImage.getWidth() / (this.size.x - 2)); n++) {
                        newImage.setRGB(n, p, colorHashMap.get(grid[j][i]).getRGB());
                    }
                }
            }
        }
        return newImage;
    }
}
