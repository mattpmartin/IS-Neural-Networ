package com.company.isassignment;

/**
 * Created by matthewmartin on 5/10/16.
 */
public class Network {
    private Synapse[][] inputSynapses;
    private Neuron[] hiddenLayer;
    private Synapse[][] hiddenToOutputs;
    private Neuron[] outputLayer;
    public double lr;

    //create new network with no values
    public Network(int inputs, int hiddens, int outputs, double lr){
        this.inputSynapses = new Synapse[hiddens][inputs];
        this.hiddenLayer = new Neuron[hiddens];
        this.hiddenToOutputs = new Synapse[outputs][hiddens];
        this.outputLayer = new Neuron[outputs];

        //set up the hidden neurons
        for(int i = 0; i < hiddens; i++){
            hiddenLayer[i] = new Neuron();
            hiddenLayer[i].setDescription("Hidden Layer Neuron " + i);

            // set up input synapses
            for(int x = 0; x < inputs; x++){
                inputSynapses[i][x] = new Synapse();
                inputSynapses[i][x].setOutput(hiddenLayer[i]);
            }

            hiddenLayer[i].setInputs(inputSynapses[i]);
        }

        //set up output neurons
        for(int i = 0; i < outputs; i++){
            outputLayer[i] = new Neuron();
            outputLayer[i].setDescription("Output Layer Neuron" + i );

            for(int x = 0; x < hiddens; x++){
                hiddenToOutputs[i][x] = new Synapse();
                hiddenToOutputs[i][x].setInput(hiddenLayer[x]);
                hiddenToOutputs[i][x].setOutput(outputLayer[i]);
            }

            outputLayer[i].setInputs(hiddenToOutputs[i]);
        }

        //set up Synapse from hidden layer to outputs
        for(int i = 0; i < hiddens; i++){
            Synapse[] hiddensOutputs = new Synapse[outputs];

            for(int x = 0; x < outputs; x++){
                hiddensOutputs[x] = hiddenToOutputs[x][i];
            }

            hiddenLayer[i].setOutputs(hiddensOutputs);
        }

        //set the lr
        this.lr = lr;
    }

    public void importValues(double[] inputValues, double[] outputValues){
        //set values for input synapses
        for(int i = 0; i < inputValues.length; i++){
            for(int x = 0; x < hiddenLayer.length; x++){
                this.inputSynapses[x][i].setValue(inputValues[i]);
            }
        }

        //set output desired values
        for(int i = 0; i < outputLayer.length; i++){
            outputLayer[i].setDesiredOutput(outputValues[i]);
        }
    }

    public void importValues(double[] inputValues){
        //set values for input synapses
        for(int i = 0; i < inputValues.length; i++){
            for(int x = 0; x < hiddenLayer.length; x++){
                this.inputSynapses[x][i].setValue(inputValues[i]);
            }
        }
    }

    public void forwardProp(){
        //forward prop the hidden layer
        for(int i = 0; i < hiddenLayer.length; i++){
            hiddenLayer[i].fire();
        }

        //forward prop output layer
        for(int i = 0; i < outputLayer.length; i++){
            outputLayer[i].fire();
        }
    }

    public void backProp(){
        //back prop synapses between output and hidden layer
        for(int i = 0; i < hiddenToOutputs.length; i++){
            for(int x = 0; x < hiddenToOutputs[i].length; x++){
                hiddenToOutputs[i][x].backProp(this.lr);
            }
        }

        //back prop input synapses
        for(int i = 0; i < inputSynapses.length; i++){
            for(int x = 0; x < inputSynapses[i].length; x++){
                inputSynapses[i][x].backProp(this.lr);
            }
        }
    }

    public void printValue(){
        System.out.println("Printing Results");
        for(int i = 0; i < this.getOutputs().length; i++){
            System.out.println(i + " gave         " + this.getOutputs()[i].getValue() + " -- ");
//            System.out.print(outputLayer[i].getDesiredOutput() + " -- ");
//
//            for(int x = 0; x < outputLayer[i].getInputs().length; x++){
//                System.out.print((lr * outputLayer[i].getInputs()[x].getSig() * Math.abs(outputLayer[i].getInputs()[x].getValue()) )+ " -- ");
//            }
//
//            System.out.println(outputLayer[i].getInputs().length);
        }
    }

    public void returnResult(){
        for(int i = 0; i < this.outputLayer.length; i++){
            if(this.outputLayer[i].getValue() > 0.5){
                System.out.println("There is a " + this.outputLayer[i].getValue() + " change input is image " + i);
            }
        }
    }

    public Neuron[] getOutputs(){
        return this.outputLayer;
    }

    public double errorValue(){
        double summedValue = 0;

        for(int i = 0; i < this.outputLayer.length; i++){
            summedValue += Math.pow(this.outputLayer[i].getDesiredOutput() - this.outputLayer[i].getValue(), 2);
        }

        summedValue = summedValue / Math.pow(this.outputLayer.length, 2);

        return Math.sqrt(summedValue);
    }
}
