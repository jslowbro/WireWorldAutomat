package com.wireworld;

public class Simulator extends Thread {


    public static void Simulate(Matrix matrix1) {
        if (Options.getInstanceOf().isMoore()) {
            Moore(matrix1);

        } else
            vonNeumann(matrix1);


    }

    private static void Moore(Matrix matrix1) {
        final int iloscElektronow = matrix1.getElectronHeads().size();
        final int iloscOgonow = matrix1.getElectronTails().size();
        for (int i = 0; i < iloscElektronow; i++) // for sprawdzający ilość sąsiadów przed dodaniem nowych głów
        {
            final int wspX = matrix1.getElectronHeads().get(i).x + 1;
            final int wspY = matrix1.getElectronHeads().get(i).y + 1;
            if (matrix1.getGrid()[wspX - 1][wspY] == '1' && (ileSasiadowMoore(matrix1, wspX - 1, wspY) == 1 || ileSasiadowMoore(matrix1, wspX - 1, wspY) == 2)) {

                matrix1.getGrid()[wspX - 1][wspY] = 'a';
            }
            if (matrix1.getGrid()[wspX - 1][wspY + 1] == '1' && (ileSasiadowMoore(matrix1, wspX - 1, wspY + 1) == 1 || ileSasiadowMoore(matrix1, wspX - 1, wspY + 1) == 2)) {

                matrix1.getGrid()[wspX - 1][wspY + 1] = 'a';
            }
            if (matrix1.getGrid()[wspX][wspY + 1] == '1' && (ileSasiadowMoore(matrix1, wspX, wspY + 1) == 1 || ileSasiadowMoore(matrix1, wspX, wspY + 1) == 2)) {

                matrix1.getGrid()[wspX][wspY + 1] = 'a';
            }
            if (matrix1.getGrid()[wspX + 1][wspY + 1] == '1' && (ileSasiadowMoore(matrix1, wspX + 1, wspY + 1) == 1 || ileSasiadowMoore(matrix1, wspX + 1, wspY + 1) == 2)) {

                matrix1.getGrid()[wspX + 1][wspY + 1] = 'a';
            }
            if (matrix1.getGrid()[wspX + 1][wspY] == '1' && (ileSasiadowMoore(matrix1, wspX + 1, wspY) == 1 || ileSasiadowMoore(matrix1, wspX + 1, wspY) == 2)) {

                matrix1.getGrid()[wspX + 1][wspY] = 'a';
            }
            if (matrix1.getGrid()[wspX + 1][wspY - 1] == '1' && (ileSasiadowMoore(matrix1, wspX + 1, wspY - 1) == 1 || ileSasiadowMoore(matrix1, wspX + 1, wspY - 1) == 2)) {

                matrix1.getGrid()[wspX + 1][wspY - 1] = 'a';
            }
            if (matrix1.getGrid()[wspX][wspY - 1] == '1' && (ileSasiadowMoore(matrix1, wspX, wspY - 1) == 1 || ileSasiadowMoore(matrix1, wspX, wspY - 1) == 2)) {

                matrix1.getGrid()[wspX][wspY - 1] = 'a';
            }
            if (matrix1.getGrid()[wspX - 1][wspY - 1] == '1' && (ileSasiadowMoore(matrix1, wspX - 1, wspY - 1) == 1 || ileSasiadowMoore(matrix1, wspX - 1, wspY - 1) == 2)) {

                matrix1.getGrid()[wspX - 1][wspY - 1] = 'a';
            }
        }
        for (int i = 0; i < iloscElektronow; i++) {
            final int wspX = matrix1.getElectronHeads().get(i).x + 1;
            final int wspY = matrix1.getElectronHeads().get(i).y + 1;
            if (matrix1.getGrid()[wspX - 1][wspY] == 'a') {

                matrix1.getGrid()[wspX - 1][wspY] = '2'; // zmiana kawałka kabla w głowę elektronu
                matrix1.getElectronHeads().add(new Coordinates(wspX - 1 - 1, wspY - 1)); // dodanie do arraylist
            }
            if (matrix1.getGrid()[wspX - 1][wspY + 1] == 'a') {

                matrix1.getGrid()[wspX - 1][wspY + 1] = '2'; // zmiana kawałka kabla w głowę elektronu
                matrix1.getElectronHeads().add(new Coordinates(wspX - 1 - 1, wspY + 1 - 1)); // dodanie do arraylist
            }
            if (matrix1.getGrid()[wspX][wspY + 1] == 'a') {

                matrix1.getGrid()[wspX][wspY + 1] = '2'; // zmiana kawałka kabla w głowę elektronu
                matrix1.getElectronHeads().add(new Coordinates(wspX - 1, wspY + 1 - 1)); // dodanie do arraylist
            }
            if (matrix1.getGrid()[wspX + 1][wspY + 1] == 'a') {

                matrix1.getGrid()[wspX + 1][wspY + 1] = '2'; // zmiana kawałka kabla w głowę elektronu
                matrix1.getElectronHeads().add(new Coordinates(wspX + 1 - 1, wspY + 1 - 1)); // dodanie do arraylist
            }
            if (matrix1.getGrid()[wspX + 1][wspY] == 'a') {

                matrix1.getGrid()[wspX + 1][wspY] = '2'; // zmiana kawałka kabla w głowę elektronu
                matrix1.getElectronHeads().add(new Coordinates(wspX + 1 - 1, wspY - 1)); // dodanie do arraylist
            }
            if (matrix1.getGrid()[wspX + 1][wspY - 1] == 'a') {

                matrix1.getGrid()[wspX + 1][wspY - 1] = '2'; // zmiana kawałka kabla w głowę elektronu
                matrix1.getElectronHeads().add(new Coordinates(wspX + 1 - 1, wspY - 1 - 1)); // dodanie do arraylist
            }
            if (matrix1.getGrid()[wspX][wspY - 1] == 'a') {

                matrix1.getGrid()[wspX][wspY - 1] = '2'; // zmiana kawałka kabla w głowę elektronu
                matrix1.getElectronHeads().add(new Coordinates(wspX - 1, wspY - 1 - 1)); // dodanie do arraylist
            }
            if (matrix1.getGrid()[wspX - 1][wspY - 1] == 'a') {

                matrix1.getGrid()[wspX - 1][wspY - 1] = '2'; // zmiana kawałka kabla w głowę elektronu
                matrix1.getElectronHeads().add(new Coordinates(wspX - 1 - 1, wspY - 1 - 1)); // dodanie do arraylist
            }


        }
        for (int i = 0; i < iloscElektronow; i++) {
            matrix1.getGrid()[matrix1.getElectronHeads().get(0).x + 1][matrix1.getElectronHeads().get(0).y + 1] = '3'; // zamiana głowy w ogon
            matrix1.getElectronTails().add(new Coordinates(matrix1.getElectronHeads().get(0).x + 1 - 1, matrix1.getElectronHeads().get(0).y + 1 - 1)); // dodanie do arraylist ogonów
            matrix1.getElectronHeads().remove(0); // usuwanie głów zmienionych w ogon (z arraylist)
        }
        for (int i = 0; i < iloscOgonow; i++) {
            matrix1.getGrid()[matrix1.getElectronTails().get(0).x + 1][matrix1.getElectronTails().get(0).y + 1] = '1'; // zmiana ogona w kabel
            matrix1.getElectronTails().remove(0); // usuwanie starych ogonów z arraylist
        }
    }

    private static void vonNeumann(Matrix matrix1) {
        final int iloscElektronow = matrix1.getElectronHeads().size();
        final int iloscOgonow = matrix1.getElectronTails().size();
        for (int i = 0; i < iloscElektronow; i++) // for sprawdzający ilość sąsiadów przed dodaniem głów
        {
            final int wspX = matrix1.getElectronHeads().get(i).x + 1;
            final int wspY = matrix1.getElectronHeads().get(i).y + 1;
            if (matrix1.getGrid()[wspX - 1][wspY] == '1' && (ileSasiadowVonNeumann(matrix1, wspX - 1, wspY) == 1 || ileSasiadowVonNeumann(matrix1, wspX - 1, wspY) == 2)) {

                matrix1.getGrid()[wspX - 1][wspY] = 'a';
            }
            if (matrix1.getGrid()[wspX][wspY + 1] == '1' && (ileSasiadowVonNeumann(matrix1, wspX, wspY + 1) == 1 || ileSasiadowVonNeumann(matrix1, wspX, wspY + 1) == 2)) {

                matrix1.getGrid()[wspX][wspY + 1] = 'a';
            }
            if (matrix1.getGrid()[wspX + 1][wspY] == '1' && (ileSasiadowVonNeumann(matrix1, wspX + 1, wspY) == 1 || ileSasiadowVonNeumann(matrix1, wspX + 1, wspY) == 2)) {

                matrix1.getGrid()[wspX + 1][wspY] = 'a';
            }
            if (matrix1.getGrid()[wspX][wspY - 1] == '1' && (ileSasiadowVonNeumann(matrix1, wspX, wspY - 1) == 1 || ileSasiadowVonNeumann(matrix1, wspX, wspY - 1) == 2)) {

                matrix1.getGrid()[wspX][wspY - 1] = 'a';
            }
        }
        for (int i = 0; i < iloscElektronow; i++) {
            final int wspX = matrix1.getElectronHeads().get(i).x + 1;
            final int wspY = matrix1.getElectronHeads().get(i).y + 1;
            if (matrix1.getGrid()[wspX - 1][wspY] == 'a') {

                matrix1.getGrid()[wspX - 1][wspY] = '2'; // zmiana kawałka kabla w głowę elektronu
                matrix1.getElectronHeads().add(new Coordinates(wspX - 1 - 1, wspY - 1)); // dodanie do arraylist
            }
            if (matrix1.getGrid()[wspX][wspY + 1] == 'a') {

                matrix1.getGrid()[wspX][wspY + 1] = '2'; // zmiana kawałka kabla w głowę elektronu
                matrix1.getElectronHeads().add(new Coordinates(wspX - 1, wspY + 1 - 1)); // dodanie do arraylist
            }
            if (matrix1.getGrid()[wspX + 1][wspY] == 'a') {

                matrix1.getGrid()[wspX + 1][wspY] = '2'; // zmiana kawałka kabla w głowę elektronu
                matrix1.getElectronHeads().add(new Coordinates(wspX + 1 - 1, wspY - 1)); // dodanie do arraylist
            }
            if (matrix1.getGrid()[wspX][wspY - 1] == 'a') {

                matrix1.getGrid()[wspX][wspY - 1] = '2'; // zmiana kawałka kabla w głowę elektronu
                matrix1.getElectronHeads().add(new Coordinates(wspX - 1, wspY - 1 - 1)); // dodanie do arraylist
            }
        }
        for (int i = 0; i < iloscElektronow; i++) {
            matrix1.getGrid()[matrix1.getElectronHeads().get(0).x + 1][matrix1.getElectronHeads().get(0).y + 1] = '3'; // zamiana głowy w ogon
            matrix1.getElectronTails().add(new Coordinates(matrix1.getElectronHeads().get(0).x + 1 - 1, matrix1.getElectronHeads().get(0).y + 1 - 1)); // dodanie do arraylist ogonów
            matrix1.getElectronHeads().remove(0); // usuwanie głów zmienionych w ogon (z arraylist)
        }
        for (int i = 0; i < iloscOgonow; i++) {
            matrix1.getGrid()[matrix1.getElectronTails().get(0).x + 1][matrix1.getElectronTails().get(0).y + 1] = '1'; // zmiana ogona w kabel
            matrix1.getElectronTails().remove(0); // usuwanie starych ogonów z arraylist
        }
    }

    private static int ileSasiadowMoore(Matrix matrix1, int wspX, int wspY) {
        int iloscSasiadow = 0;
        if (matrix1.getGrid()[wspX - 1][wspY] == '2') {
            iloscSasiadow++;
        }
        if (matrix1.getGrid()[wspX - 1][wspY + 1] == '2') {
            iloscSasiadow++;
        }
        if (matrix1.getGrid()[wspX][wspY + 1] == '2') {
            iloscSasiadow++;
        }
        if (matrix1.getGrid()[wspX + 1][wspY + 1] == '2') {
            iloscSasiadow++;
        }
        if (matrix1.getGrid()[wspX + 1][wspY] == '2') {
            iloscSasiadow++;
        }
        if (matrix1.getGrid()[wspX + 1][wspY - 1] == '2') {
            iloscSasiadow++;
        }
        if (matrix1.getGrid()[wspX][wspY - 1] == '2') {
            iloscSasiadow++;
        }
        if (matrix1.getGrid()[wspX - 1][wspY - 1] == '2') {
            iloscSasiadow++;
        }
        return iloscSasiadow;
    }

    private static int ileSasiadowVonNeumann(Matrix matrix1, int wspX, int wspY) {
        int iloscSasiadow = 0;
        if (matrix1.getGrid()[wspX - 1][wspY] == '2') {
            iloscSasiadow++;
        }
        if (matrix1.getGrid()[wspX][wspY + 1] == '2') {
            iloscSasiadow++;
        }
        if (matrix1.getGrid()[wspX + 1][wspY] == '2') {
            iloscSasiadow++;
        }
        if (matrix1.getGrid()[wspX][wspY - 1] == '2') {
            iloscSasiadow++;
        }
        return iloscSasiadow;
    }
}
