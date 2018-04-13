package org.insa.algo.shortestpath;

//Importation des classes dans la classe imoprté
import org.insa.graph.*;
import java.util.ArrayList;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

	private ArrayList<Label> ListeLabels = new ArrayList<Label>();
	
    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
        
        /* Initialisation
         * 
         * For tous les sommets i  de 1 à n
         * 		Mark(i) <- Faux
         * 		Cost(i) <- +inf
         * 		Father(i) <- 0   // sommet inexistant
         * end for
         * Cost(s) <- 0
         * Insert(s, Tas)
         */
        
        for(Label l: ListeLabels) {
        	
        }
    }

    
    
    
    /* Itéartion
     * 
     * While il existe des sommets non marqués
     * 		x <- ExtractMin(Tas)
     * 		Mark(x) <- true
     * 		For tous les y successeurs de x
     * 			If not Mark(y) then
     * 				Cost(y) <- Min(Cost(y), Cost(x)+W(x,y))
     * 				If Cost(y) a été mis à jour then
     * 					Placer(y, Tas)
     * 					Father(y) <- x
     * 				end if
     * 			end if
     * 		end for
     * end while
     */
    
    
    @Override
    protected ShortestPathSolution doRun() {
        ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        // TODO:
        return solution;
    }

}
