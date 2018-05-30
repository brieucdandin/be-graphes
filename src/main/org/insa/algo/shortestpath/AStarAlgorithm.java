package org.insa.algo.shortestpath;

import org.insa.graph.*;
import java.util.*;

import org.insa.algo.AbstractInputData.Mode;
import org.insa.algo.AbstractSolution.Status;
import org.insa.algo.utils.BinaryHeap;


public class AStarAlgorithm extends DijkstraAlgorithm {

	protected BinaryHeap<Label> tas = new BinaryHeap<Label>();
	
	public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }
	
	
	/*private LabelStar[] initTabLabels(Node n, ShortestPathData d) {
		LabelStar[] marquage = new LabelStar[data.getGraph().size()];

        for(Node n: data.getGraph()) {
        	double estimation = n.getPoint().distanceTo( data.getGraph().get(-1).getPoint() );
        	marquage[n.getId()] = new LabelStar(n, false, Double.POSITIVE_INFINITY, estimation);
        }
        marquage[((ShortestPathData) data).getOrigin().getId()].setCout(0.0);
        
		return marquage;
	}*/
	
	

    /**
     * Methode initialisant la liste de labels (afin de n'avoir qu'a modifier ca dans AStar)
     **/
	@Override
	protected ShortestPathSolution doRun() {
		ShortestPathData data = getInputData();
		LabelStar[] marquage = new LabelStar[data.getGraph().size()];
		
		//La différence avec l'algo de Dijkstra a lieu dans cette boucle for, le reste est identique à DijkstraAlgorithme
		for(Node n : data.getGraph()) {
			if (data.getMode() == Mode.TIME) {
				marquage[n.getId()] = new LabelStar(n, false, Double.POSITIVE_INFINITY, n.getPoint().distanceTo(data.getDestination().getPoint())/36.1);
			}
			else {
				marquage[n.getId()] = new LabelStar(n, false, Double.POSITIVE_INFINITY, n.getPoint().distanceTo(data.getDestination().getPoint())/36.1);
			}
		}
		marquage[data.getOrigin().getId()].setCout(0.0);
		
		Arc[] prec = new Arc[data.getGraph().size()];
        notifyOriginProcessed(data.getOrigin());
        tas.insert(marquage[data.getOrigin().getId()]);
        
        boolean val = true;
        int nbNoeud = 0;
        while (val) {
            val = false;
            if (!tas.isEmpty()) {
            	LabelStar lx = (LabelStar)tas.deleteMin();
            	Node x = lx.getNoeud();
            	notifyNodeMarked(x);
            	lx.setMarq(true);
            	
            	nbNoeud++;
      
            	for(Arc succ : x) {
            		if(!data.isAllowed(succ)) {
            			continue;
            		}
            		LabelStar ly = marquage[succ.getDestination().getId()];
            		if (!ly.getMarq()) {
            			val = true;
            			double a = ly.getCout();
            			double cout = Math.min(ly.getCout(), lx.getCout() + data.getCost(succ));
            			ly.setCout(cout);
            			if (cout != a) {
            				tas.insert(ly);;
            				prec[succ.getDestination().getId()] = succ;
            			}
            			if(Double.isInfinite(a) && Double.isFinite(cout)) {
            				notifyNodeReached(ly.getNoeud());
            			}
            		}
            	}
            	
            	val =  (x.equals(data.getDestination()) || nbNoeud >= data.getGraph().size()) ? false : true;
            }
            	
        }
		
        ShortestPathSolution PlusCourtChemin = null;

	    //Si la destination n'a pas de noeud precedent, alors on ne peut pas utiliser l'algorithme.
	    if (prec[data.getDestination().getId()] == null) {
	    	PlusCourtChemin = new ShortestPathSolution(data, Status.INFEASIBLE);
	    }
	    else {
	    	notifyDestinationReached(data.getDestination());
	
	        ArrayList <Arc> ListArc = new ArrayList<>();
	        Arc arc = prec[data.getDestination().getId()];
	        while(arc != null) {
	        	ListArc.add(arc);
	            arc = prec[arc.getOrigin().getId()];
	        }
	        Collections.reverse(ListArc);
	        PlusCourtChemin = new ShortestPathSolution(data, Status.OPTIMAL, new Path(data.getGraph(), ListArc));
	    }
	    
	    return PlusCourtChemin;
    }
		
		
}
	