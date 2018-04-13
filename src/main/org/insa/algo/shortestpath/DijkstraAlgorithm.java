package org.insa.algo.shortestpath;

//Importation des classes dans la classe imoprté
import org.insa.graph.*;
import java.util.ArrayList;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

	private ArrayList<Label> ListeLabels;
	
    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
        
        //Initialisation
        for(Label l: ListeLabels) {
        	l.setMarq(false);
        	l.setCout(999999);
        	l.setPrec(null);
        }
        ListeLabels.get(0).setCout(0);
        //insertion du sommet source dans le tas
        Insert(ListeLabels.get(0), Tas);

        //Iteration
        while (ParcoursMarq(ListeLabels) == false) {
        	int x = findMin(Tas);
        	ListeLabels.
        	
        	for (Label)
        }
        
    }

    //Parcours pour savoir si les labels ont été marqué
    public boolean ParcoursMarq(ArrayList<Label> Lab){
    	for(Label l: Lab) {
        	if(l.getMarq() == false){
       			return false;
        	}
        }
       	return true;
     } 
    
    
    
    @Override
    protected ShortestPathSolution doRun() {
        ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        // TODO:
        return solution;
    }

}
