package org.insa.algo.shortestpath;

import org.insa.graph.LabelStar;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
		// TODO Modifier pour que tous les Labels soient des LabelStar. La modification de getCoutTotal() dans LabelStar permettra alors de classer les labels par ordre de cout+estimation croissant.
    }

}
