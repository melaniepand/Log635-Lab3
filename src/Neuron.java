import java.util.ArrayList;

public class Neuron {

	private int numInputs;
	private ArrayList<Synapse> inlinks;
	private ArrayList<Synapse> outlinks;
	String label = "";

	// private ArrayList<Neuron> succ = new ArrayList<Neuron>();
	// private ArrayList<Neuron> Pred = new ArrayList<Neuron>();
	private Double sum;
	private Double output;
	private Double delta;
	private Double momentum = 0.9;
	private Double learningRate = 0.3;

	public Neuron(int pNumInputs, String nlabel) {

		output = 0.0;
		delta = 0.0;
		sum = 0.0;
		numInputs = pNumInputs;

		inlinks = new ArrayList<Synapse>();
		outlinks = new ArrayList<Synapse>();
		label = new String(nlabel);

	}
	public int getNumInputs() {
		return numInputs;
	}

	public void setNumInputs(int numInputs) {
		this.numInputs = numInputs;
	}

	public void computeOutput() {
		sum = 0.0;
		for (int i = 0; i < getInlinks().size(); i++)
			sum += inlinks.get(i).getFrom().getOutput()
			* inlinks.get(i).getWeight();

		output = 1.0 / (1.0 + Math.exp(-sum)); // sigmoid function

	}

	public void computeBackpropDelta(double d) // for an output neuron
	{
		delta = (d - output) * output * (1.0 - output);

	}

	public void computeBackpropDelta() // for a hidden neuron
	{
		double errorSum = 0.0;

		for (int i = 0; i < outlinks.size(); i++)
			errorSum += outlinks.get(i).getTo().getDelta()
			* outlinks.get(i).getWeight();
		delta = output * (1.0 - output) * errorSum;

	}

	public void computeWeight() {

		for (int i = 0; i < inlinks.size(); i++) {
			Double newData = learningRate * delta
					* inlinks.get(i).getFrom().getOutput() + momentum
					* inlinks.get(i).getData();
			inlinks.get(i).setData(newData);
			Double newWeight = inlinks.get(i).getWeight() + newData;
			inlinks.get(i).setData(newWeight);

		}

	}

	public void print() {
		System.out.print(label + "=" + output + ": ");
		Synapse synapse;
		for (int i = 0; i < outlinks.size(); i++) {
			System.out.print(outlinks.get(i).getTo().label + "("
					+ outlinks.get(i).getWeight() + ") ");
			System.out.println("");
		}
	}

	public void setDelta(Double valeur) {
		delta = valeur;
	}

	public Double getDelta() {
		return delta;
	}

	public void setOutput(Double valeur) {
		output = valeur;
	}

	public Double getOutput() {
		return output;
	}

	public ArrayList<Synapse> getInlinks() {
		return inlinks;
	}

	public void addInlinks(Synapse sysnapse) {
		inlinks.add(sysnapse);
	}

	public void setInlinks(ArrayList<Synapse> inlinks) {
		inlinks = outlinks;
	}

	public ArrayList<Synapse> getOutlinks() {
		return outlinks;
	}

	public void addOutlinks(Synapse sysnapse) {
		inlinks.add(sysnapse);
	}

	public void setOutlinks(ArrayList<Synapse> outlinks) {
		inlinks = outlinks;
	}

	// -1 < n < 1
	// private double RandomClamped() {
	// Random random = new Random();
	// // return random.nextDouble() * 2 - 1;
	// return random.nextDouble() - 0.5;
	// }
}
