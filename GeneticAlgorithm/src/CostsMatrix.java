import java.io.IOException;
import java.util.Scanner;

public class CostsMatrix {
	private static Integer[][] costsMatrix;
	
	public static void generateMatrix(int numberOfCities){
	
		 costsMatrix = new Integer[numberOfCities][numberOfCities];
		
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
	
	public static Integer[][] getCostsMatrix() {
		return costsMatrix;
	}

	public static void setCostsMatrix(Integer[][] costsMatrix) {
		CostsMatrix.costsMatrix = costsMatrix;
	}

	
}
