
public class GeneticAlgorithm {
	
	private static final double mutationRate = 0.015;
    private static final int tournamentSize = 2;
    private static final boolean elitism = true;
    
    public Tour tournament(Population population){
    	
    	Population toTournament = new Population(tournamentSize,false);
    	
    	for (int i = 0; i < tournamentSize; i++) {
    		int index = (int) (Math.random() * population.populationSize());
			toTournament.saveTour(i, population.getTour(index));
		}
    	Tour fittest = toTournament.getFittest();
    	
		return fittest;
    	
    }
    public void mutate(Tour tour){
    	for(int i=0;i<tour.tourSize();i++){
    		if(Math.random() < mutationRate){
    			
    			int cityPos = (int) (Math.random() * tour.tourSize());
    			
    			tour.setCity(cityPos, tour.getCity(i));
    			tour.setCity(i, tour.getCity(cityPos));
    			
    		}
    	}
    }
    
    public Tour PMXCrossover(Tour parent1, Tour parent2){
    	
    	Tour child = new Tour();
    	int startPos = (int) (Math.random() * parent1.tourSize());
        int endPos = (int) (Math.random() * parent1.tourSize());
        
        for (int i = 0; i < child.tourSize(); i++) {
           
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setCity(i, parent1.getCity(i));
            } 
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.setCity(i, parent1.getCity(i));
                }
            }
        }
    	for(int i = startPos; i <= endPos; i++){
    		int cityToCheck = parent2.getCity(i);
    		boolean alreadyInChild = false;
    		
    		for(int j = startPos; j<= endPos && !alreadyInChild; j++){
    			if(child.getCity(j) == cityToCheck){
    				alreadyInChild = true;
    			}
    		}
    		if(!alreadyInChild){
    			int foundPosition = findPositionForValueFromSecondParent(startPos,endPos,parent1,parent2,i);
    			parent2.setCity(foundPosition, cityToCheck);    		
    		}
    	}
    	copyRemainingCities(parent1,parent2,child,startPos,endPos);
		return child;
    	
    }
    
    
    
    private void copyRemainingCities(Tour parent1, Tour parent2, Tour child, int startPos, int endPos) {
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
	private int findPositionForValueFromSecondParent(int startPos, int endPos, Tour parent1, Tour parent2, int currentPosition) {
		int valueInFirstParent = parent1.getCity(currentPosition);
		
		boolean valueFound = false;
		int foundIndex = -1;
		
		for(int j=1;j<=parent2.tourSize()-2 && !valueFound;j++){
			if(parent2.getCity(j) == valueInFirstParent){
				valueFound = true;
				foundIndex = j;
			}
		}
		if(startPos <= foundIndex && endPos >= foundIndex){
			return findPositionForValueFromSecondParent(startPos,endPos,parent1,parent2,foundIndex);
		}else
		{
			return foundIndex;
		}
		
	}
	public Population evolvePopulation(Population popToEvolve){
    	Population newPopulation = new Population(popToEvolve.populationSize(), false);
    	
    	  int positionOfBest = 0;
          if (elitism) {
              newPopulation.saveTour(0, popToEvolve.getFittest());
              positionOfBest = 1;
          }
    	for(int i = positionOfBest;i<newPopulation.populationSize();i++){
    		Tour parent1 = tournament(popToEvolve);
    		Tour parent2 = tournament(popToEvolve);
    		
    		Tour child = PMXCrossover(parent1, parent2);
    		
    		newPopulation.saveTour(i, child);   		
    	}
    	for(int i = positionOfBest;i<newPopulation.populationSize();i++){
    		mutate(newPopulation.getTour(i));
    	}
    	
		return newPopulation;
    	
    }
}
