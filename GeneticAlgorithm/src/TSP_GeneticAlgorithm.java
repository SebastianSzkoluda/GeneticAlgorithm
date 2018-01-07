import java.io.IOException;

public class TSP_GeneticAlgorithm {

	public static void main(String[] args) {
		
		InstanceFileReader file = new InstanceFileReader();
		try {
			CostsMatrix.setCostsMatrix(file.read("bayg29.tsp"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Population pop = new Population(CostsMatrix.getCostsMatrix().length, true);
		System.out.println("Initial distance: " + pop.getFittest().getDistance());
	}

}
