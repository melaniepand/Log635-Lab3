import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		if (args.length == 0 || args[0].isEmpty()) {
			System.out.println(" ../donnees_sources.csv");
			System.exit(0);
		}
		ArrayList<Data> lstData = new ArrayList<Data>();
		lstData = getDataFromFile(args[0]);
		System.out.println("Donnees recupere : " + lstData.size());
		// System.out.println("premirèe ligne: " + lstData.get(0).printData());
		int listMidSize = lstData.size() / 2;

		// Appliquer l�entrainement sur la list de donn�e ( index 0 �
		// listMidSize)
		ArrayList<Data> lstDataTrainning = new ArrayList<Data>();
		for (int cpt = 0; cpt < listMidSize; cpt++)
			lstDataTrainning.add(lstData.get(cpt));
		NeuralNet nn = new NeuralNet(11, 1, 4, 6);
		NeuralNetTrainer nnt = new NeuralNetTrainer(nn);
		nnt.trainNetwork(lstDataTrainning);
		//
		// // Valider les donn�es ( index listMidSize a lstDonnes.size() )
		ArrayList<Data> lstValidationTrainning = new ArrayList<Data>();
		for (int cpt = listMidSize; cpt < lstData.size(); cpt++)
			lstValidationTrainning.add(lstData.get(cpt));
		nnt.ValidNetwork(lstValidationTrainning);

		// Faire un test
		System.out
				.println("***************Systeme prêt pour test:*******************");
		System.out.println("Entrez le chemin vers votre fichier");
		System.out
		.println("Assurer vous que le fichier est a la racine du projet et renseigner juste le nom du fichier");
		Scanner sc = new Scanner(System.in);
		String path = sc.nextLine();

		ArrayList<Data> lstValidationTest = getDataFromFile(path);

		nnt.ValidNetwork(lstValidationTest);

		System.out.println("***************Terminer *******************");

	}
	private static ArrayList<Data> getDataFromFile(String pFilePath) {
		ArrayList<Data> tempDonnees = new ArrayList<Data>();
		String[] tabDonnees;
		try {
			String path = new File("").getAbsolutePath() + "/" + pFilePath;
			System.out.println("Reading file : " + path);
			FileInputStream fstream = new FileInputStream(path);

			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			boolean firstLine = true;
			while ((strLine = br.readLine()) != null) {
				if (!firstLine) {
					tabDonnees = strLine.split(";");
					Data don = new Data(Double.parseDouble(tabDonnees[0]),
							Double.parseDouble(tabDonnees[1]),
							Double.parseDouble(tabDonnees[2]),
							Double.parseDouble(tabDonnees[3]),
							Double.parseDouble(tabDonnees[4]),
							Double.parseDouble(tabDonnees[5]),
							Double.parseDouble(tabDonnees[6]),
							Double.parseDouble(tabDonnees[7]),
							Double.parseDouble(tabDonnees[8]),
							Double.parseDouble(tabDonnees[9]),
							Double.parseDouble(tabDonnees[10]),
							Integer.parseInt(tabDonnees[11]));
					tempDonnees.add(don);
				}
				firstLine = false;
			}

			in.close();
			return tempDonnees;
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			System.exit(0);
			return null;
		}
	}

}
