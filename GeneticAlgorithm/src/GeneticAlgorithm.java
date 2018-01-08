import java.util.concurrent.ThreadLocalRandom;

public class GeneticAlgorithm {
	
	private static final double mutationRate = 0.015;
    private static final int tournamentSize = 2;
    private static final boolean elitism = true;
    
    public static Tour tournament(Population population){
    	
    	Population toTournament = new Population(tournamentSize,false);
    	
    	for (int i = 0; i < tournamentSize; i++) {
    		int index = (int) (Math.random() * population.populationSize());
			toTournament.saveTour(i, population.getTour(index));
		}
    	Tour fittest = toTournament.getFittest();
    	
		return fittest;
    	
    }
    public static void mutate(Tour tour){
    	for(int i=0;i<tour.tourSize();i++){
    		if(Math.random() < mutationRate){
    			
    			int cityPos = (int) (Math.random() * tour.tourSize());
    			
    			tour.setCity(cityPos, tour.getCity(i));
    			tour.setCity(i, tour.getCity(cityPos));
    			
    		}
    	}
    }
    
    public static Tour PMXCrossover(Tour parent1, Tour parent2){
    	
    	Tour child = new Tour();
    	int randomNum = ThreadLocalRandom.current().nextInt(1, parent1.getTour().size() - 1);
    	//System.out.println("Moja liczba: "+ randomNum);
    	int startPos = ThreadLocalRandom.current().nextInt(1, parent1.getTour().size() - 1);
    	//System.out.println("Pozycja startowa: "+ startPos);
        int endPos = 0;
        if(startPos == 1){
        	endPos = ThreadLocalRandom.current().nextInt(startPos, parent1.getTour().size() - 2);
        	//System.out.println("Pozycja koncowa jesli startowa 1: "+ endPos);
        }
        else{
        	endPos = ThreadLocalRandom.current().nextInt(startPos, parent1.getTour().size() - 1);
        	//System.out.println("Pozycja koncowa: "+ endPos);
        }
        for (int i = startPos; i <= endPos; i++) {
			child.setCity(i, parent1.getCity(i));
		}
        
    	for(int i = startPos; i <= endPos; i++){
    		int cityToCheck = parent2.getCity(i);
    		boolean alreadyInChild = false;
    		
    		for(int j = startPos; j <= endPos && !alreadyInChild; j++){
    			if(child.getCity(j) == cityToCheck){
    				alreadyInChild = true;
    			}
    		}
    		if(!alreadyInChild){
    			int foundPosition = findPositionForValueFromSecondParent(startPos,endPos,parent1,parent2,i);
    			//System.out.println("Znaleziona pozycja: " + foundPosition);
    			child.setCity(foundPosition, cityToCheck);    		
    		}
    	}
    	copyRemainingCities(parent1,parent2,child,startPos,endPos);
		return child;
    	
    }
    
    
    
    private static void copyRemainingCities(Tour parent1, Tour parent2, Tour child, int startPos, int endPos) {
    	for(int i = 1 ; i < startPos ; i++) {
    		
			if(child.getCity(i) == 0) {
				child.setCity(i, parent2.getCity(i));
			}
		}
    	for(int i = endPos ; i < parent1.tourSize() - 1 ; i++) {
			if(child.getCity(i) == 0) {
				child.setCity(i, parent2.getCity(i));
			}
		}
		
	}
	private static int findPositionForValueFromSecondParent(int startPos, int endPos,
			Tour parent1, Tour parent2, int currentPosition) {
		int valueInFirstParent = parent1.getCity(currentPosition);
		
		boolean valueFound = false;
		int foundIndex = -1;
		//System.out.println("Dlugosc sciezki rodzica nr 2: " + parent2.tourSize());
		for(int j=1;j<=parent2.tourSize()-1 && !valueFound;j++){
			if(parent2.getCity(j) == valueInFirstParent){
				
				foundIndex = j;
				valueFound = true;
				//System.out.println("Znaleziona pozycja w petli z j: "+ foundIndex);
			}
			
		}
		//System.out.println("Znaleziona pozycja przed ifem w metodzie findPosition: " + foundIndex);
		if(startPos <= foundIndex && endPos >= foundIndex ){
			return findPositionForValueFromSecondParent(startPos,endPos,parent1,parent2,foundIndex);
		}else
		{
			//System.out.println("Znaleziona pozycja przy return z metody findPosition: " + foundIndex);
			return foundIndex;
			
		}
		
	}
	
	public static Population evolvePopulation(Population popToEvolve){
		
    	Population newPopulation = new Population(popToEvolve.populationSize(), false);
    	
    	  int positionOfBest = 0;
          if (elitism) {
              newPopulation.saveTour(0, popToEvolve.getFittest());
              positionOfBest = 1;
          }
         // System.out.println("Rozmiar nowej populacji: "+ newPopulation.populationSize());
    	for(int i = positionOfBest;i<newPopulation.populationSize();i++){
    		Tour parent1 = tournament(popToEvolve);
    		Tour parent2 = tournament(popToEvolve);
    		
    		Tour child = PMXCrossover(parent1, parent2);
    		
    		newPopulation.saveTour(i, child);  
    		 System.out.println("Przeszla petla nr:" + i);
    	}
    	for(int i = positionOfBest;i<newPopulation.populationSize();i++){
    		mutate(newPopulation.getTour(i));
    	}
    	
		return newPopulation;
    	
    }
}
