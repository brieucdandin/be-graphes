package org.insa.algo.shortestpath;

import org.insa.graph.LabelStar;
import org.insa.graph.Node;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }

    /**
     * Methode initialisant la liste de labels (afin de n'avoir qu'a modifier ca dans AStar)
     */
	private LabelStar[] initTabLabels() {
		LabelStar[] marquage = new LabelStar[data.getGraph().size()];

        for(Node n: data.getGraph()) {
        	double estimation = n.getPoint().distanceTo( data.getGraph().get(-1).getPoint() );
        	marquage[n.getId()] = new LabelStar(n, false, Double.POSITIVE_INFINITY, estimation);
        }
        marquage[((ShortestPathData) data).getOrigin().getId()].setCout(0.0);
        
		return marquage;
	}
}
