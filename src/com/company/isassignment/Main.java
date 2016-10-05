package com.company.isassignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        //creating the blank network
        Network network = new Network(144, 5, 12, 20);

        //training the network
        //loop that trains for each patten
        for(int i = 0; i < 12; i++){
            try {
                //read the file and convert it
                int[] inputValues = new int[144];
                BufferedReader br = new BufferedReader(new FileReader("/Users/matthewmartin/Documents/Documents - Matthew’s MacBook Pro/Uni/IS/IS Assignment 2 v 2/Patterns/PatternFiles/" + i + ".txt"));
                String line;
                String bigLine = "";

                while ((line = br.readLine()) != null) {
                    bigLine = bigLine + line;
                }

                for (int x = 0; x < 144; x++) {
                    int value = 0;

                    if (bigLine.charAt(x) == '*') value = 1;

                    inputValues[x] = value;
                }

                int[] outputValues = new int[12];

                for (int x = 0; x < 12; x++){
                    if(x == i)
                        outputValues[x] = 1;
                    else
                        outputValues[x] = 0;
                }

                //import those values into the networ
                network.importValues(inputValues, outputValues);

                //forward prop the file
                network.forwardProp();

                //train the network
                network.backProp();

                //test the network
                network.forwardProp();

                for(int x = 0; x < 12; x++){
                    System.out.println(network.getOutputs()[x].getDescription() + " : " +network.getOutputs()[x].getValue());
                }
            }catch (IOException e) {
                System.out.print(e);
            }
        }
    }
}
