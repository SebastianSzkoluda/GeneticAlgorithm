import java.util.ArrayList;

public class TourManager {
	private static ArrayList<Integer> destinationCities  = new ArrayList<>();
	
	public static void addCity(Integer city){
		destinationCities .add(city);
	}
	public static Integer getCity(int index){
		return destinationCities .get(index);
		
	}
	public static int numerOfCities(){
		return destinationCities .size();
		
	}
	public static ArrayList<Integer> getDestinationCities() {
		return destinationCities;
	}
	public static void setDestinationCities(ArrayList<Integer> destinationCities) {
		TourManager.destinationCities = destinationCities;
	}
	
	
}
