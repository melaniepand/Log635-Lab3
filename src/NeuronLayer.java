import java.util.ArrayList;

public class NeuronLayer {

	private int numNeurons;
	private ArrayList<Neuron> neurons = new ArrayList<Neuron>();

	public NeuronLayer(String plabel, int NumNeurons) {
		numNeurons = NumNeurons;
		String label;
		for (int i = 0; i < NumNeurons; i++) {
			label = new String(plabel);
			neurons.add(new Neuron(NumNeurons, label));
		}
	}

	public Neuron getNeuron(int i) {
		// int j =0;
		boolean found = false;
		Neuron neuron = null;
		int j = 0;
		for (j = 0; j < neurons.size(); j++)
			if (i == j) {

				found = true;
				break;
			}

		if (!found)
			return null;

		return neurons.get(i);
	}

	public void computeOutputs() {

		for (int i = 0; i < neurons.size(); i++)
			neurons.get(i).computeOutput();
	}

	public void computeBackpropDeltas(ArrayList<Double> deltaList) // for output
	// neurons
	{

		for (int i = 0; i < neurons.size(); i++)
			neurons.get(i).computeBackpropDelta(deltaList.get(i));
	}

	public void computeBackpropDeltas() // for hidden neurons
	{
		for (int i = 0; i < neurons.size(); i++)
			neurons.get(i).computeBackpropDelta();

	}

	public void computeWeights() {
		for (int i = 0; i < neurons.size(); i++)
			neurons.get(i).computeWeight();
	}

	public void print() {
		for (int i = 0; i < neurons.size(); i++)
			neurons.get(i).print();
	}

	/* Renvoi le nombre de neurone de la couche */
	public int getNumNeurons() {
		return numNeurons;
	}

	public void setNumNeurons(int numNeurons) {
		this.numNeurons = numNeurons;
	}

	public ArrayList<Neuron> getNeurons() {
		return neurons;
	}

	public void setNeurons(ArrayList<Neuron> neurons) {
		this.neurons = neurons;
	}

}
