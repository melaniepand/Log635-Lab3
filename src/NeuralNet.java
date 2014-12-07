import java.util.ArrayList;

public class NeuralNet {

	private final int bias = 1;
	private int numInputs;
	private int numOutputs;
	private int numHiddenLayers;
	private int neuronsPerHiddenLyr;
	// Rajout

	private ArrayList<NeuronLayer> layers = new ArrayList<NeuronLayer>();

	public NeuralNet(int pNumInputs, int pNumOutputs, int pNumHiddenLayers,
			int pNeuronsPerHiddenLyr) {
		numInputs = pNumInputs;
		numOutputs = pNumOutputs;
		numHiddenLayers = pNumHiddenLayers;
		neuronsPerHiddenLyr = pNeuronsPerHiddenLyr;

		CreateNet();
	}

	public void CreateNet() {

		// Premiere couche
		layers.add(new NeuronLayer("I", numInputs + 1)); // +1

		// Les couches cachées
		if (numHiddenLayers > 0)
			for (int i = 0; i < numHiddenLayers; ++i)
				layers.add(new NeuronLayer("H", neuronsPerHiddenLyr));

		// La dernière couche
		layers.add(new NeuronLayer("O", numOutputs));

		// Initialiser et connecter les couches
		// Connecter la première couche

		for (int i = 0; i < layers.size(); i++)
			if (i > 0)
				for (int j = 0; j < layers.get(i).getNeurons().size(); j++) {
					biasConnect(i, j);
					for (int j2 = 0; j2 < layers.get(i - 1).getNumNeurons(); j2++)
						connect(i - 1, j2, i, j);
				}

	}
	public void biasConnect(int destLayer, int destNeuron) {
		new Synapse(layers.get(0).getNeurons()
				.get(layers.get(0).getNumNeurons() - 1), getLayer(destLayer)
				.getNeuron(destNeuron));
	}

	public void connect(int sourceLayer, int sourceNeuron, int destLayer,
			int destNeuron) {
		new Synapse(getLayer(sourceLayer).getNeuron(sourceNeuron), getLayer(
				destLayer).getNeuron(destNeuron));
	}

	public NeuronLayer getLayer(int index) {
		if (index < layers.size())
			return layers.get(index);
		return null;
	}

	// initialise les données d'entrée
	public void initInputs(ArrayList<Double> data) {

		for (int i = 0; i < data.size(); i++)
			layers.get(0).getNeurons().get(i).setOutput(data.get(i));
		layers.get(0).getNeurons().get(layers.get(0).getNumNeurons() - 1)
		.setOutput(1.0); // bias
	}

	public void learnPattern(ArrayList<Double> iS, ArrayList<Double> oS) {
		initInputs(iS);
		propagate();
		bpAdjustWeights(oS);
	}

	public ArrayList<Double> getOutput() {

		ArrayList<Double> oS = new ArrayList<Double>();

		for (int i = 0; i < layers.get(layers.size() - 1).getNumNeurons(); i++)
			oS.add(layers.get(layers.size() - 1).getNeurons().get(i)
					.getOutput());

		return oS;
	}

	void propagate() {

		for (int i = 1; i < layers.size(); i++)
			layers.get(i).computeOutputs();

	}

	void bpAdjustWeights(ArrayList<Double> oS) {
		layers.get(layers.size() - 1).computeBackpropDeltas(oS);
		for (int i = layers.size() - 2; i >= 1; i--)
			layers.get(i).computeBackpropDeltas();

		layers.get(layers.size() - 1).computeWeights();

		for (int i = layers.size() - 2; i >= 1; i--)
			layers.get(i).computeWeights();
	}

	// public ArrayList<Double> GetWeights() {
	// // this will hold the weights
	// ArrayList<Double> weights = new ArrayList<Double>();
	//
	// // for each layer
	// for (int i = 0; i < numHiddenLayers + 1; ++i)
	// // for each neuron
	// for (int j = 0; j < layers.get(i).getNumNeurons(); ++j)
	// // for each weight
	// for (int k = 0; k < layers.get(i).getNeurons().get(j)
	// .getNumInputs() - 1; ++k)
	// weights.add(layers.get(i).getNeurons().get(j).getWeight()
	// .get(k));
	//
	// return weights;
	// }

	// public void PutWeights(ArrayList<Double> pWeights) {
	// int weight = 0;
	//
	// // for each layer
	// for (int i = 0; i < numHiddenLayers + 1; ++i)
	// // for each neuron
	// for (int j = 0; j < layers.get(i).getNumNeurons(); ++j)
	// // for each weight
	// for (int k = 0; k < layers.get(i).getNeurons().get(j)
	// .getNumInputs(); ++k)
	// layers.get(i).getNeurons().get(j).getWeight()
	// .set(k, pWeights.get(weight++));
	// return;
	// }

	// public int GetNumberOfWeights() {
	//
	// int weights = 0;
	//
	// // for each layer
	// for (int i = 0; i < numHiddenLayers + 1; ++i)
	// // for each neuron
	// for (int j = 0; j < layers.get(i).getNumNeurons(); ++j)
	// // for each weight
	// for (int k = 0; k < layers.get(i).getNeurons().get(j)
	// .getNumInputs(); ++k)
	//
	// weights++;
	//
	// return weights;
	// }

	// @SuppressWarnings("unchecked")
	// public ArrayList<Double> feedForward(ArrayList<Double> pInputs) {
	// // System.out.println("Passage:" + pInputs);
	// // stores the resultant outputs from each layer
	// ArrayList<Double> outputs = new ArrayList<Double>();
	// ArrayList<Double> inputs = pInputs;
	//
	// int weight = 0;
	//
	// // first check that we have the correct amount of inputs
	// if (inputs.size() != numInputs)
	// // just return an empty vector if incorrect.
	// return outputs;
	//
	// // For each layer....
	// for (int i = 0; i < numHiddenLayers; i++) {
	// if (i > 0)
	// inputs = (ArrayList<Double>) outputs.clone();
	// // inputs = outputs;
	//
	// // * System.out.println("----------"); for (int cpt6=0; cpt6<
	// // * inputs.size(); cpt6++) { System.out.println(inputs.get(cpt6));
	// // }
	// // * System.out.println("----------");
	//
	// outputs.clear();
	//
	// weight = 0;
	//
	// // for each neuron sum the (inputs * corresponding weights).Throw
	// // the total at our sigmoid function to get the output.
	// System.out.println("layer size" + layers.size());
	// for (int j = 0; j < layers.get(i).getNumNeurons(); j++) {
	//
	// double netinput = 0;
	//
	// int NumInputs = layers.get(i).getNeurons().get(j)
	// .getNumInputs();
	//
	// // for each weight
	//
	// for (int k = 0; k < NumInputs; k++)
	// netinput += layers.get(i).getNeurons().get(j).getWeight()
	// .get(k)
	// * inputs.get(weight++);
	//
	// netinput += layers.get(i).getNeurons().get(j).getWeight()
	// .get(NumInputs - 1)
	// * bias;
	//
	// // we can store the outputs from each layer as we generate them.
	// // The combined activation is first filtered through the sigmoid
	// // function
	// Double localOutput = Sigmoid(netinput);
	// layers.get(i).getNeurons().get(j).setLocalOutput(localOutput);
	// outputs.add(Sigmoid(netinput));
	//
	// weight = 0;
	// }
	//
	// }
	// return outputs;
	// }

	// public void backpropagate(ArrayList<Double> outputOfOutputNeurone,
	// ArrayList<Double> expectedOutputValues) {
	// ArrayList<Double> outputErrors = new ArrayList<Double>();
	//
	// // Pour la couche de sortie
	// if (outputOfOutputNeurone.size() != numOutputs) {
	// System.out
	// .println("Nombre outPut différent des résultat en sortie\n");
	// return;
	// }
	//
	// for (int j = 0; j < numOutputs; j++) {
	// Double di = 0.0;
	// di = outputOfOutputNeurone.get(j)
	// * (1 - outputOfOutputNeurone.get(j))
	// * (expectedOutputValues.get(j) - outputOfOutputNeurone
	// .get(j));
	// layers.get(numHiddenLayers + 1).getNeurons().get(j)
	// .setLocalGradient(di);
	//
	// }
	//
	// // Pour les couche cachées
	// for (int j = numHiddenLayers - 1; j > 0; j--)
	// // Pour chaque Neurone de la couche courante
	// for (int k = 0; k < layers.get(j).getNumNeurons(); k++) {
	// Double somme = 0.0;
	// // Pour chaque neurone de la couche suivante
	// for (int t = 0; t < layers.get(j + 1).getNumNeurons(); t++)
	// somme += layers.get(j + 1).getNeurons().get(t).getWeight()
	// .get(t)
	// * layers.get(j + 1).getNeurons().get(t)
	// .getLocalGradient();
	//
	// Double di = 0.0;
	// di = layers.get(j).getNeurons().get(k).getLocalOutput()
	// * (1 - layers.get(j).getNeurons().get(k)
	// .getLocalOutput()) * somme;
	// layers.get(j).getNeurons().get(k).setLocalGradient(di);
	// }
	//
	// // Reajuster les poids comme cela se doit
	// for (int i = 1; i < layers.size(); i++)
	// // Pour chaque couche
	// for (int j = 0; j < layers.get(i).getNumNeurons(); j++)
	// // Pour chaque Neurone
	// for (int k = 0; k < layers.get(i).getNumNeurons(); k++)
	// for (int t = 0; t < layers.get(i).getNeurons().get(k)
	// .getWeight().size(); t++) {
	// // Penser a transformer Epcelone en variable
	// Double newWeight = 0.0;
	// newWeight = layers.get(i).getNeurons().get(k)
	// .getWeight().get(t)
	// + 0.5
	// * layers.get(i - 1).getNeurons().get(t)
	// .getLocalGradient()
	// * layers.get(i - 1).getNeurons().get(t)
	// .getLocalOutput();
	// layers.get(i).getNeurons().get(k).getWeight()
	// .set(t, newWeight);
	// }
	// }

	public double getOutputError(double desiredValue, double outputValue) {
		// return error gradient
		return desiredValue - outputValue;
	}

}
