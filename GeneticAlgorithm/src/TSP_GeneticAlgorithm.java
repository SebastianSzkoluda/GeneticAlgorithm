import java.io.IOException;

public class TSP_GeneticAlgorithm {

	public static void main(String[] args) throws IOException {
		Integer[][] matrix;
		InstanceFileReader file = new InstanceFileReader();
		
			matrix = file.read("bayg29.tsp");
		
		CostsMatrix.setCostsMatrix(matrix);
		for(int i = 0;i<matrix.length;i++){
			
			TourManager.addCity(i);
		}
		Population pop = new Population(matrix.length, true);
		System.out.println("Initial distance: " + pop.getFittest().getDistance());
		System.out.println("Path of first best: " + pop.getFittest().getTour() );
		
		 // pop = GeneticAlgorithm.evolvePopulation(pop);
	        for (int i = 0; i < 100; i++) {
	            pop = GeneticAlgorithm.evolvePopulation(pop);
	           
	        }
		 
	        System.out.println("Finished");
	        System.out.println("Final distance: " + pop.getFittest().getDistance());
	        System.out.println("Solution:");
	        System.out.println(pop.getFittest().getTour());
		
	}
	

}
