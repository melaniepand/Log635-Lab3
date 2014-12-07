import java.util.ArrayList;

public class NeuralNetTrainer {

	private NeuralNet neutralNet;

	public NeuralNetTrainer(NeuralNet pNnNet) {
		neutralNet = pNnNet;

	}

	public void trainNetwork(ArrayList<Data> pData) {

		int errorCount = 0;
		int epoch = 0;
		ArrayList<Double> lstInputTrainning = new ArrayList<Double>();
		System.out.println("*************");
		System.out.println("Training on " + pData.size() + " data");
		System.out.println("*************");
		while (true) {
			epoch++;
			errorCount = 0;
			for (int cpt = 0; cpt < pData.size(); cpt++) {

				lstInputTrainning.add(pData.get(cpt).getAlcohol());
				lstInputTrainning.add(pData.get(cpt).getChlorides());
				lstInputTrainning.add(pData.get(cpt).getCitricAcid());
				lstInputTrainning.add(pData.get(cpt).getDensity());
				lstInputTrainning.add(pData.get(cpt).getFixedAcidity());
				lstInputTrainning.add(pData.get(cpt).getFreeSulfurDioxide());
				lstInputTrainning.add(pData.get(cpt).getpH());
				lstInputTrainning.add(pData.get(cpt).getResidualSugar());
				lstInputTrainning.add(pData.get(cpt).getSulphates());
				lstInputTrainning.add(pData.get(cpt).getTotalSulfurDioxide());
				lstInputTrainning.add(pData.get(cpt).getVolatileAcidity());
				int qualite = pData.get(cpt).getQuality();
				ArrayList<Double> expectedOutputs = new ArrayList<Double>();
				expectedOutputs.add((double) qualite);

				neutralNet.initInputs(lstInputTrainning);
				neutralNet.propagate();

				Double Error = neutralNet.getOutputError(neutralNet.getOutput()
						.get(0), sigmoid(expectedOutputs.get(0)));

				// Afficher l'erreur a chaque itération
				System.out.println("Valeur Entrée:" + lstInputTrainning);
				System.out.println("Valeur Sotie:" + neutralNet.getOutput());
				System.out.println("Valeur qualité:" + expectedOutputs.get(0));
				System.out.println("Valeur obtenue:"
						+ logit(neutralNet.getOutput().get(0)));
				System.out.println("Erreur:" + Error);

				// Ajutster ls poids
				neutralNet.bpAdjustWeights(expectedOutputs);

				int desiredOutput = pData.get(cpt).getQuality();
				Double neuralOutput = logit(neutralNet.getOutput().get(0));

				if (desiredOutput - neuralOutput != 0) {
					System.out.println(desiredOutput + " / " + neuralOutput);
					errorCount++;
				}
				// System.out.println("desired Output : " + desiredOutput +
				// " / Output : " + neuralOutput);

				expectedOutputs.clear();
				lstInputTrainning.clear();
			}
			System.out.println(errorCount);
			// System.out.println("% training valid " + (errorCount /
			// pData.size()) * 100 );
			if (errorCount < 2000) {
				System.out.println("BREAK");
				break;
			}

		}

	}

	public void ValidNetwork(ArrayList<Data> pData) {

		ArrayList<Double> lstInputTrainning = new ArrayList<Double>();
		// System.out.println("*************");
		// System.out.println("Validation on " + pData.size() + " data");
		// System.out.println("*************");
		for (int cpt = 0; cpt < pData.size(); cpt++) {
			System.out.println("Validation " + cpt);
			lstInputTrainning.add(pData.get(cpt).getAlcohol());
			lstInputTrainning.add(pData.get(cpt).getChlorides());
			lstInputTrainning.add(pData.get(cpt).getCitricAcid());
			lstInputTrainning.add(pData.get(cpt).getDensity());
			lstInputTrainning.add(pData.get(cpt).getFixedAcidity());
			lstInputTrainning.add(pData.get(cpt).getFreeSulfurDioxide());
			lstInputTrainning.add(pData.get(cpt).getpH());
			lstInputTrainning.add(pData.get(cpt).getResidualSugar());
			lstInputTrainning.add(pData.get(cpt).getSulphates());
			lstInputTrainning.add(pData.get(cpt).getTotalSulfurDioxide());
			lstInputTrainning.add(pData.get(cpt).getVolatileAcidity());
			// int qualite = pData.get(cpt).getQuality();
			// ArrayList<Double> outputs = new ArrayList<Double>();
			// outputs.add((double) qualite);

			// neutralNet.learnPattern(lstInputTrainning, outputs);

			// Double Error = neutralNet.getOutputError(sigmoid((double)
			// qualite),
			// outputs.get(0));

			// if (neuralOutput == qualite)
			// System.out.println("output " + neuralOutput);
			// outputs.clear();
			// lstInputTrainning.clear();
		}

	}

	private Double sigmoid(Double nb) {
		return 1.0 / (1.0 + Math.exp(-nb));
	}
	private Double logit(Double nb) {
		Double res = 0.0;
		System.out.println("Dsans logbin:");
		System.out.println("nb:" + nb);

		res = Math.log(nb / (1.0 - nb));
		System.out.println("nb:" + res);

		return res;
	}
	private double clampOutput(double pOutput) {
		return pOutput * 10.0;
	}
}
