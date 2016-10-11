package com.company.isassignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //creating the blank network
        Network network = new Network(144, 200, 12, .01);

        int trainNo = 0;

        //training the network

        System.out.println("Training Network");

        while((network.errorValue() > 0.005 || network.errorValue() == 0.0)){
            //increment train count
            trainNo++;

            //loop that trains for each patten
            for (int i = 0; i < 12; i++) {
                try {
                    //read the file and convert it
                    double[] inputValues = new double[144];
                    BufferedReader br = new BufferedReader(new FileReader("./Patterns/PatternFiles/" + i + ".txt"));
                    String line;
                    String bigLine = "";

                    while ((line = br.readLine()) != null) {
                        bigLine = bigLine + line;
                    }

                    for (int x = 0; x < 144; x++) {
                        double value = 0.0;

                        if (bigLine.charAt(x) == '*') value = 1.0;

                        inputValues[x] = value;
                    }

                    double[] outputValues = new double[12];

                    for (int x = 0; x < 12; x++) {
                        if (x == i)
                            outputValues[x] = 1.0;
                        else
                            outputValues[x] = 0.0;
                    }

                    //import those values into the network
                    network.importValues(inputValues, outputValues);

                    //forward prop the file
                    network.forwardProp();

                    //train the network
                    network.backProp();

                    //test the network
                    network.forwardProp();
                } catch (IOException e) {
                    System.out.print(e);
                }
            }
            //System.out.println(network.errorValue());
        }

        //Print statistics

        System.out.println("RMS Error -- " + network.errorValue());
        System.out.println("Training iterations -- " + trainNo);

        //test the network
        //testing no noise
        System.out.println("Testing no noise");
        for (int i = 0; i < 12; i++) {
            System.out.println("Testing image " + i);
            try {
                double[] inputValues = new double[144];
                BufferedReader br = new BufferedReader(new FileReader("./Patterns/PatternFiles/" + i + ".txt"));
                String line;
                String bigLine = "";

                while ((line = br.readLine()) != null) {
                    bigLine = bigLine + line;
                }

                for (int x = 0; x < 144; x++) {
                    double value = 0.0;

                    if (bigLine.charAt(x) == '*') value = 1.0;

                    inputValues[x] = value;
                }

                //import those values into the network
                network.importValues(inputValues);

                //forward prop the file
                network.forwardProp();

                network.returnResult();

            } catch (IOException e) {
                System.out.print(e);
            }
        }

        //testing 5% noise
        System.out.println("Testing 5% noise");
        for (int i = 0; i < 12; i++) {
            System.out.println("Testing image " + i);
            try {
                double[] inputValues = new double[144];
                BufferedReader br = new BufferedReader(new FileReader("./Patterns/TestFiles5/" + i + ".txt"));
                String line;
                String bigLine = "";

                while ((line = br.readLine()) != null) {
                    bigLine = bigLine + line;
                }

                for (int x = 0; x < 144; x++) {
                    double value = 0.0;

                    if (bigLine.charAt(x) == '*') value = 1.0;

                    inputValues[x] = value;
                }

                //import those values into the network
                network.importValues(inputValues);

                //forward prop the file
                network.forwardProp();

                network.returnResult();

            } catch (IOException e) {
                System.out.print(e);
            }
        }

        //testing 10% noise
        System.out.println("Testing 10% noise");
        for (int i = 0; i < 12; i++) {
            System.out.println("Testing image " + i);
            try {
                double[] inputValues = new double[144];
                BufferedReader br = new BufferedReader(new FileReader("./Patterns/TestFiles10/" + i + ".txt"));
                String line;
                String bigLine = "";

                while ((line = br.readLine()) != null) {
                    bigLine = bigLine + line;
                }

                for (int x = 0; x < 144; x++) {
                    double value = 0.0;

                    if (bigLine.charAt(x) == '*') value = 1.0;

                    inputValues[x] = value;
                }

                //import those values into the network
                network.importValues(inputValues);

                //forward prop the file
                network.forwardProp();

                //return the result
                network.returnResult();

            } catch (IOException e) {
                System.out.print(e);
            }
        }

        System.out.println("Want to specify your own file (y/n)");

        Scanner scan = new Scanner(System.in);

        if(scan.next().equals("y")){
            System.out.println("Type your file path here: ");

            String inputFile = scan.next();

            try {

                double[] inputValues = new double[144];
                BufferedReader br = new BufferedReader(new FileReader(inputFile));
                String line;
                String bigLine = "";

                while ((line = br.readLine()) != null) {
                    bigLine = bigLine + line;
                }

                for (int x = 0; x < 144; x++) {
                    double value = 0.0;

                    if (bigLine.charAt(x) == '*') value = 1.0;

                    inputValues[x] = value;
                }

                //import those values into the network
                network.importValues(inputValues);

                //forward prop the file
                network.forwardProp();

                network.returnResult();
            } catch (IOException e) {
                System.out.print(e);
            }
        }

        System.out.println("Done");
    }
}
