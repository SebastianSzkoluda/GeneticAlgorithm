import java.util.ArrayList;
import java.util.Collections;

public class Tour {
	
	
	private ArrayList<Integer> tour = new ArrayList<>();
	
	private double fitness = 0;
	private int distance = 0;
	
	public ArrayList<Integer> getTour() {
		
		return tour;
	}

	public void setTour(ArrayList<Integer> tour) {
		this.tour = tour;
	}

	public Tour(){
		for(int i=0;i<TourManager.numerOfCities();i++){
			tour.add(0);
		}
	}
	
	 public Tour(ArrayList tour){
	        this.tour = tour;
	    }
	 
	 public int getCity(int index){
		return tour.get(index);
		 
	 }
	 
	 public void setCity(int index,int city){
		 tour.set(index, city);
		 
		
	 }
	 public double getFitness() {
	        if (fitness == 0) {
	            fitness = 1/(double)getDistance();
	            //System.out.println(getDistance());
	        }
	        return fitness;
	    }
	 public void generateOneTour(){
		 
		 int liczba = CostsMatrix.getCostsMatrix().length;
		// System.out.println("Liczba miast: "+liczba);
		 for(int i = 0;i<TourManager.numerOfCities();i++){
			 tour.set(i, TourManager.getCity(i));
		 }
		 Collections.shuffle(tour);
		 for(int i = 0;i<TourManager.numerOfCities();i++){
			// System.out.print(tour.get(i));
			 if(i!=TourManager.numerOfCities()-1){
				// System.out.print(" --> ");
			 }
			
		 }
		// System.out.println();
	 }
	 
	 public int getDistance(){
		
		int cost = 0;
		 for(int i = 0;i<TourManager.numerOfCities()-1;i++){
			// System.out.println(tour.get(i));
			 //System.out.println(tour.get(i+1));
			 //System.out.println(tour.get(TourManager.numerOfCities()-1));
			 cost += CostsMatrix.getCostsMatrix()[tour.get(i)][tour.get(i+1)];
			 //System.out.println(cost);
			 
		 }
		 cost += CostsMatrix.getCostsMatrix()[tour.get(TourManager.numerOfCities()-1)][tour.get(0)];
		return cost; 
	 }
	  // Get number of cities on our tour
	    public int tourSize() {
	        return tour.size();
	    }
	    
	    // Check if the tour contains a city
	    public boolean containsCity(int city){
	        return tour.contains(city);
	    }
}
