import java.io.File;
import java.io.IOException;

import org.jorlib.io.tspLibReader.TSPLibInstance;
import org.jorlib.io.tspLibReader.graph.DistanceTable;


public class InstanceFileReader {

	public Integer[][] read(String fileName) throws IOException {
		File instnaceFile = new File(fileName);
		TSPLibInstance readInstance = new TSPLibInstance(instnaceFile);

		return convert(readInstance.getDistanceTable(), fileName);
	}

	private Integer[][] convert(DistanceTable distanceTable, String instanceName) {
		int instanceSize = distanceTable.listNodes().length;
		Integer[][] matrix = new Integer [instanceSize][instanceSize];
		
		for (int i = 0; i < instanceSize; i++) {
			for (int j = 0; j < instanceSize; j++) {
				
				matrix[i][j] = (int) distanceTable.getDistanceBetween(i, j);
			}
		}

		return matrix;
	}

}