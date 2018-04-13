package org.insa.algo.shortestpath;

//Importation des classes dans la classe imoprté
import org.insa.graph.*;
import java.util.ArrayList;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

	private ArrayList<Label> ListeLabels = new ArrayList<Label>();
	
    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
        
        //Initialisation
        for(Label l: ListeLabels) {
        	
        }
    }

    
    
    
    
    @Override
    protected ShortestPathSolution doRun() {
        ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        // TODO:
        return solution;
    }

}
