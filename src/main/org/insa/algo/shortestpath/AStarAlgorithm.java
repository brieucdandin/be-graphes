package org.insa.algo.shortestpath;

import org.insa.graph.Label;
import org.insa.graph.LabelStar;
import org.insa.graph.Node;

public class AStarAlgorithm extends DijkstraAlgorithm {
	// TODO Modifier pour que tous les Labels soient des LabelStar. La modification de getCoutTotal() dans LabelStar permettra alors de classer les labels par ordre de cout+estimation croissant.
    // TODO cf. initialisation de la liste de labels dans Dijkstra
	
    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }

    /**
     * Methode initialisant la liste de labels (afin de n'avoir qu'a modifier ca dans AStar)
     */
	private Label[] initTabLabels() {
		LabelStar[] marquage = new LabelStar[data.getGraph().size()];

        for(Node n: data.getGraph()) {
        	marquage[n.getId()] = new LabelStar(n, false, Double.POSITIVE_INFINITY/*, estimation*/);	// TODO Ajouter estimation
        }
        marquage[((ShortestPathData) data).getOrigin().getId()].setCout(0.0);
        
		return marquage;
	}
}
