import java.io.IOException;
import java.util.Scanner;

public class CostsMatrix {
	private static double[][] costsMatrix;
	
	public static void generateMatrix(int numberOfCities){
	
		 costsMatrix = new double[numberOfCities][numberOfCities];
		
		for(int i=0;i<numberOfCities;i++){
			for(int j=0;j<numberOfCities;j++){
				
				costsMatrix[i][j] = (int) (Math.random()*10);
				if(i == j){
					costsMatrix[i][j] = 9999;
				}
				System.out.print(costsMatrix[i][j]+"  ");
			}
			System.out.print("\n");
		}
	}
	
	public static double[][] getCostsMatrix() {
		return costsMatrix;
	}

	public static void setCostsMatrix(double[][] costsMatrix) {
		CostsMatrix.costsMatrix = costsMatrix;
	}

	public static void main(String[] args) {
		//int numberOfCities;
		//Scanner sc = new Scanner(System.in);
		
		//System.out.print("Prosze podaæ iloœæ miast: ");
		//numberOfCities = sc.nextInt();
		
		//generateMatrix(numberOfCities);
		InstanceFileReader file = new InstanceFileReader();
		try {
			costsMatrix = file.read("bayg29.tsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<costsMatrix.length;i++){
			for(int j=0;j<costsMatrix.length;j++){
				
				 System.out.printf(" %6.2f ",costsMatrix[i][j]);
			}
			System.out.println();
		}
		
		for(int i=0;i<costsMatrix.length;i++){
			TourManager.addCity(i);
		}
		Tour tour = new Tour();
		System.out.println();
		tour.generateOneTour();
		System.out.println("\n");
		System.out.println(tour.getDistance());
		
		
	}
}
