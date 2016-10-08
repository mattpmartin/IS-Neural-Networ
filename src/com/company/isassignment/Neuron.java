package com.company.isassignment;

/**
 * Created by matthewmartin on 19/09/2016.
 * What bridge should i jump off
 */
public class Neuron {
    private Synapse[] inputs;
    private Synapse[] outputs = new Synapse[0];
    private double desiredOutput = 0;
    private double value;
    private String description;

    public double activationFunction(double sum){
        return (1.0/( 1.0 + Math.pow(Math.E,(-1.0*sum))));
    }

    public void setInputs(Synapse[] inputs){
        this.inputs = inputs;
    }

    public Synapse[] getInputs(){
        return this.inputs;
    }

    public void setOutputs(Synapse[] outputs){
        this.outputs = outputs;
    }

    public Synapse[] getOutputs(){
        return this.outputs;
    }

    public void setDescription(String desc){
        this.description = desc;
    }

    public String getDescription(){
        return description;
    }

    public void setDesiredOutput(double desiredOutput){
        this.desiredOutput = desiredOutput;
    }

    public double getDesiredOutput(){
        return desiredOutput;
    }

    public double getValue(){
        return value;
    }

    public void fire(){
        double nextValue = 0;
        for(int i = 0; i < inputs.length; i++){
            nextValue += inputs[i].getValue();
        }

        value = activationFunction(nextValue);

        for(int i = 0; i < outputs.length; i++){
            outputs[i].setValue(value);
        }
    }

}
