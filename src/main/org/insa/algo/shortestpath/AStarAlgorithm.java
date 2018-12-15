package org.insa.algo.shortestpath;

import org.insa.graph.*;

import org.insa.algo.AbstractInputData.Mode;
import org.insa.algo.utils.BinaryHeap;


public class AStarAlgorithm extends DijkstraAlgorithm {

	protected BinaryHeap<Label> tas = new BinaryHeap<Label>();
	
	public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }

    /**
     * Methode initialisant la liste de labels (afin de n'avoir qu'a modifier ca dans AStar)
     **/
	@Override
	protected LabelStar[] initTabLabels(ShortestPathData data) {
		LabelStar[] marquage = new LabelStar[data.getGraph().size()];

		for(Node n : data.getGraph()) {
			double estimationDist = n.getPoint().distanceTo(data.getDestination().getPoint());
			//Adaptation au mode TIME ou DISTANCE
			double estimation = (data.getMode() == Mode.LENGTH) ? estimationDist : ((data.getMode() == Mode.TIME) ? estimationDist/36.0 : estimationDist) ;
        	marquage[n.getId()] = new LabelStar(n, false, Double.POSITIVE_INFINITY, estimation);
		}
		marquage[data.getOrigin().getId()].setCout(0.0);

		return marquage;
	}

}
