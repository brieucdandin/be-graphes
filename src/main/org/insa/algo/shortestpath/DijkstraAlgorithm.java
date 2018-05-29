package org.insa.algo.shortestpath;

import org.insa.graph.*;
import java.util.ArrayList;
import java.util.*;
import org.insa.algo.AbstractSolution.Status;
import org.insa.algo.utils.BinaryHeap;
import org.insa.graph.Label;


public class DijkstraAlgorithm extends ShortestPathAlgorithm {

	protected BinaryHeap<Label> tas = new BinaryHeap<Label>();


    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    /**
	 * Parcours pour savoir si les labels ont ete marques
	 */
/*

	public boolean ParcoursMarq(ArrayList<Label> Lab) {
    	for(Label l: Lab) {
        	if(l.getMarq() == false){
       			return false;
        	}
        }
       	return true;
     }

*/
    
    
    @Override
    protected ShortestPathSolution doRun() {
        ShortestPathData data = getInputData();

        /**         INITIALISATION
         * 
         * For tous les sommets i  de 1 a n
         *      Mark(i) <- Faux
         *      Cost(i) <- +inf
         *      Father(i) <- 0   //sommet inexistant
         * end for
         * Cost(s) <- 0
         * Insert(s, tas)
         */

        //Creation d'un tableau Label pour marquer les Labels
        Label[] marquage = initTabLabels();
        
        //Creation d'un tableau contenant les noeud parcourus
        Arc[] prec = new Arc[data.getGraph().size()];

        //On notifie qu'on est deja passe par l'origine
        notifyOriginProcessed(data.getOrigin());

        //Insertion du sommet source dans le tas
        tas.insert(marquage[data.getOrigin().getId()]);
        //tas.insert(ListeLabels.get(idOrigine));	//TODO: Erreur avec la liste de label a regler eventuellement

        
        /**         ITERATION
         * 
         * While il existe des sommets non marques
         *      x <- ExtractMin(tas)
         *      Mark(x) <- true
         *      For tous les y successeurs de x
         *          If not Mark(y) then
         *              Cost(y) <- Min(Cost(y), Cost(x)+W(x,y)) //W est le poids de l'arc allant de x a y
         *              If Cost(y) a ete mis a jour then
         *                  Placer(y, tas)
         *                  Father(y) <- x
         *              end if
         *          end if
         *      end for
         * end while
         */
        
        // TODO Le programme s'arrete au bout de 2 secondes

        boolean val = true;
        int nbNoeud = 0;
        while (val) {
            val = false;
            if (!tas.isEmpty()) {
                Label lx = tas.deleteMin();
                Node x = lx.getNoeud();
                notifyNodeMarked(x);
                lx.setMarq(true);
            
                nbNoeud++;
            
                //Parcours de la liste des successeurs de x (obtenue ci-dessus)
                for (Arc succ : x) {
                	if (!data.isAllowed(succ)) {
                		continue;
                	}
                	Label ly = marquage[succ.getDestination().getId()];
                	if (!ly.getMarq()) {
                		val = true;
                		double a = ly.getCout();
                		double cout = Math.min(ly.getCout(), lx.getCout()+data.getCost(succ));
                		ly.setCout(cout);
                		if (cout != a) {
                			tas.insert(ly);
                			prec[succ.getDestination().getId()] = succ;
                		} 
                		if (Double.isInfinite(a) && Double.isFinite(cout)) {
                			notifyNodeReached(ly.getNoeud());
                		}
                	}
                }

                val =  ( x.equals(data.getDestination()) || nbNoeud >= data.getGraph().size() )  ? false : true;

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

    /**
     * Methode initialisant la liste de labels (afin de n'avoir qu'a modifier ca dans AStar)
     */
	private Label[] initTabLabels() {
		Label[] marquage = new Label[data.getGraph().size()];

        for(Node n: data.getGraph()) {
        	marquage[n.getId()] = new Label(n, false, Double.POSITIVE_INFINITY);
        }
        marquage[((ShortestPathData) data).getOrigin().getId()].setCout(0.0);
        
		return marquage;
	}
}
