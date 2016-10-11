package com.company.isassignment;

import java.util.Random;

/**
 * Created by matthewmartin on 19/09/2016.
 * Class to define link between Neurtons
 */
public class Synapse {
    private double weight = 1;
    private double originalValue;
    private double value;
    private Neuron input;
    private Neuron output;

    public Synapse(){
        Random rand = new Random();
        this.weight = rand.nextDouble() - .5;
    }

    public Synapse(double weight, Neuron input, Neuron output){
        this.weight = weight;
        this.input = input;
        this.output = output;
    }

    public double getWeight(){
        return this.weight;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public Neuron getInput(){
        return this.input;
    }

    public void setInput(Neuron input){
        this.input = input;
    }

    public Neuron getOutput(){
        return this.output;
    }

    public void setOutput(Neuron output){
        this.output = output;
    }

    public void setValue(double value){
        this.value = value * this.weight;
        this.originalValue = value;
    }

    public double getValue(){
        return this.value;
    }

    public double getSig(){
        if(this.output.getOutputs().length == 0){
            //the node is an output node
            return this.output.getValue() * (1 - this.output.getValue()) * (this.output.getDesiredOutput() - this.output.getValue());
        }else{
            //establishing the summed value
            double summedValue = 0.0;

            for(int i = 0; i < output.getOutputs().length; i++){
                summedValue += output.getOutputs()[i].getValue() * output.getOutputs()[i].getSig();
            }

            //the node is a hidden node
            return this.getOutput().getValue() * (1.0 - this.getOutput().getValue()) * summedValue;
        }
    }

    public void backProp(double lr){
        if(this.input == null)
            //if between input and hidden value use inputted value
            this.weight = this.weight + lr * this.getSig() * this.originalValue;
        else
            //else use value if hidden layer input node
            this.weight = this.weight + lr * this.getSig() * this.input.getValue();
    }
}
