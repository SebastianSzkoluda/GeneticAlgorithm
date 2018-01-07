import java.io.File;
import java.io.IOException;

import org.jorlib.io.tspLibReader.TSPLibInstance;
import org.jorlib.io.tspLibReader.graph.DistanceTable;


public class InstanceFileReader {

	public double[][] read(String fileName) throws IOException {
		File instnaceFile = new File(fileName);
		TSPLibInstance readInstance = new TSPLibInstance(instnaceFile);

		return convert(readInstance.getDistanceTable(), fileName);
	}

	private double[][] convert(DistanceTable distanceTable, String instanceName) {
		int instanceSize = distanceTable.listNodes().length;
		double[][] matrix = new double [instanceSize][instanceSize];
		
		for (int i = 0; i < instanceSize; i++) {
			for (int j = 0; j < instanceSize; j++) {
				
				matrix[i][j] = distanceTable.getDistanceBetween(i, j);
			}
		}

		return matrix;
	}

}