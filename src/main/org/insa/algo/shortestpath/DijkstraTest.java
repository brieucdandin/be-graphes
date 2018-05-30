package org.insa.algo.shortestpath;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.insa.algo.ArcInspectorFactory;
import org.insa.graph.Graph;
import org.insa.graph.Node;
import org.insa.graph.RoadInformation;
import org.insa.graph.RoadInformation.RoadType;
import org.junit.BeforeClass;
import org.junit.Test;

public class DijkstraTest {

    // Small graph use for tests
    private static Graph graph;

    // List of nodes
    private static Node[] nodes;

    // List of arcs in the graph, a2b is the arc from node A (0) to B (1).
    /*@SuppressWarnings("unused")
    private static Arc a2b, a2c, a2e, b2c, c2d_1, c2d_2, c2d_3, c2a, d2a, d2e, e2d;

    // Some paths...
    private static Path emptyPath, singleNodePath, shortPath, longPath, loopPath, longLoopPath,
            invalidPath;
	*/

    @BeforeClass
    public static void initAll() throws IOException {

        // 10 and 20 meters per seconds
        
        RoadInformation speed10 = new RoadInformation(RoadType.UNCLASSIFIED, null, true, 1, null);
        

        // Create nodes
        nodes = new Node[6];
        for (int i = 0; i < nodes.length; ++i) {
            nodes[i] = new Node(i, null);
        }

        // Add arcs...
        Node.linkNodes(nodes[0], nodes[1], 7, speed10, new ArrayList<>());

        Node.linkNodes(nodes[0], nodes[2], 8, speed10, new ArrayList<>());

        Node.linkNodes(nodes[2], nodes[0], 7, speed10, new ArrayList<>());

        Node.linkNodes(nodes[2], nodes[1], 2, speed10, new ArrayList<>());

        Node.linkNodes(nodes[1], nodes[3], 4, speed10, new ArrayList<>());

        Node.linkNodes(nodes[1], nodes[4], 1, speed10, new ArrayList<>());

        Node.linkNodes(nodes[1], nodes[5], 5, speed10, new ArrayList<>());

        Node.linkNodes(nodes[2], nodes[5], 2, speed10, new ArrayList<>());

        Node.linkNodes(nodes[4], nodes[2], 2, speed10, new ArrayList<>());

        Node.linkNodes(nodes[4], nodes[3], 2, speed10, new ArrayList<>());

        Node.linkNodes(nodes[4], nodes[5], 3, speed10, new ArrayList<>());

        Node.linkNodes(nodes[5], nodes[4], 3, speed10, new ArrayList<>());

        graph = new Graph("ID", "", Arrays.asList(nodes), null);

        /*
        emptyPath = new Path(graph, new ArrayList<Arc>());
        singleNodePath = new Path(graph, nodes[1]);
        shortPath = new Path(graph, Arrays.asList(new Arc[] { a2b, b2c, c2d_1 }));
        longPath = new Path(graph, Arrays.asList(new Arc[] { a2b, b2c, c2d_1, d2e }));
        loopPath = new Path(graph, Arrays.asList(new Arc[] { a2b, b2c, c2d_1, d2a }));
        longLoopPath = new Path(graph,
                Arrays.asList(new Arc[] { a2b, b2c, c2d_1, d2a, a2c, c2d_3, d2a, a2b, b2c }));
        invalidPath = new Path(graph, Arrays.asList(new Arc[] { a2b, c2d_1, d2e }));
        */
    }
    
    protected ShortestPathAlgorithm Dijkstra(ShortestPathData test){
    	return new DijkstraAlgorithm(test);
    }

    private ShortestPathAlgorithm Bellman(ShortestPathData test){
    	return new BellmanFordAlgorithm(test);
    }

    private ShortestPathSolution DijkstraSol(ShortestPathAlgorithm algotest){
    	return algotest.doRun();
    }

    @Test
    public void CompBellmanDijkstra() {
        
    	for(int i=0; i < nodes.length; i++){
    		for (int j = 0; j < nodes.length; j++){
    			if (i != j){
    				ShortestPathData test = new ShortestPathData(graph, nodes[i], nodes[j], ArcInspectorFactory.getAllFilters().get(0));
					
					ShortestPathAlgorithm dijkstra = Dijkstra(test);
					ShortestPathAlgorithm bellman = Bellman(test);
					
					ShortestPathSolution dijkstraSol = DijkstraSol(dijkstra);
					ShortestPathSolution bellmanSol = DijkstraSol(bellman);

					assertEquals(dijkstraSol.isFeasible(), bellmanSol.isFeasible());

					if (dijkstraSol.isFeasible() && bellmanSol.isFeasible())
					{
						//test de validité
						assertEquals(dijkstraSol.getPath().isValid(), bellmanSol.getPath().isValid());

						//test de longueur
						assertEquals(dijkstraSol.getPath().getLength(), bellmanSol.getPath().getLength(), 1e-6);

						//test de temps
						assertEquals(dijkstraSol.getPath().getMinimumTravelTime(), bellmanSol.getPath().getMinimumTravelTime(), 1e-6);

						//test de taille
						assertEquals(dijkstraSol.getPath().size(), bellmanSol.getPath().size());

						System.out.println(dijkstraSol.getPath().getLength() + " ");
					}
					else
					{
						System.out.println("inf");
					}
				}
				else
				{
					System.out.println(" - ");
				}
			}
		}
	}
}