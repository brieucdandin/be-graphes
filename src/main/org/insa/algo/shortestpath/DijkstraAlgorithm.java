package org.insa.algo.shortestpath;

import org.insa.graph.*;
import java.util.ArrayList;
import java.util.*;
//import java.util.HashMap;
import org.insa.algo.AbstractSolution.Status;
import org.insa.algo.utils.BinaryHeap;
//import org.insa.algo.utils.Label;
import org.insa.graph.Label;


public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    protected BinaryHeap<Label> tas = new BinaryHeap<Label>();

//    protected Chemin chemin_result;

//    protected int mode;


    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

	/**
	 * Parcours pour savoir si les labels ont ete marques
	 */
   /* public boolean ParcoursMarq(ArrayList<Label> Lab){
    	for(Label l: Lab) {
        	if(l.getMarq() == false){
       			return false;
        	}
        }
       	return true;
     }*/
    
    
    @Override
    protected ShortestPathSolution doRun() 
    {
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

        //Creation d'un tableau Label pour marquer les Labels'
        Label[] marquage = new Label[data.getGraph().size()];

        for(Node n: data.getGraph()) 
        {
            marquage[n.getId()] = new Label(n);
        }
        marquage[data.getOrigin().getId()].setCout(0.0);
        
        //Création d'un tableau contenant les noeud parcourus
        Arc[] prec = new Arc[data.getGraph().size()];

        //On notifie qu'on est déjà passé par l'origine
        notifyOriginProcessed(data.getOrigin());

        //Insertion du sommet source dans le tas
        //tas.insert(ListeLabels.get(idOrigine)); //TODO: Erreur ici ! (Cf. warning)
        tas.insert(marquage[data.getOrigin().getId()]);

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

        boolean val = true;
        int nbNoeud = 0;
        while (val) 
        {
            val = false;
            if(!tas.isEmpty())
            {
                Label lx = tas.deleteMin();
                Node x = lx.getNoeud();
                notifyNodeMarked(x);
                lx.setMarq(true);
            
                nbNoeud++;
            
                //On parcourt la liste des successeurs de x (obtenue ci-dessus).
                for (Arc succ : x)
                {
                	if(!data.isAllowed(succ))
                	{
                		continue;
                	}
                	Label ly = marquage[succ.getDestination().getId()];
                	if(!ly.getMarq())
                	{
                		val = true;
                		double a = ly.getCout();
                		double cout = Math.min(ly.getCout(), lx.getCout()+data.getCost(succ));
                		ly.setCout(cout);
                		if(cout != a)
                		{
                			tas.insert(ly);
                			prec[succ.getDestination().getId()] = succ;
                		} 
                		if (Double.isInfinite(a) && Double.isFinite(cout))
                		{
                			notifyNodeReached(ly.getNoeud());
                		}
                	}
                }

                if ((x.equals(data.getDestination())) || (nbNoeud >= data.getGraph().size())) 
                {
                	val = false;
                }
                else
                {
                	val = true;
                }
            }
        }

        ShortestPathSolution solution = null;

	    //la destination n'a pas de noeud precedent alors on ne peut pas utiliser l'algorithme
	    if (prec[data.getDestination().getId()]==null)
	    {
	    	solution = new ShortestPathSolution(data, Status.INFEASIBLE);
	    }
	    else
	    {
	    	notifyDestinationReached(data.getDestination());
	
	        ArrayList <Arc> ListArc = new ArrayList<>();
	        Arc arc = prec[data.getDestination().getId()];
	        while(arc != null)
	        {
	        	ListArc.add(arc);
	            arc = prec[arc.getOrigin().getId()];
	        }
	        Collections.reverse(ListArc);
	        solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(data.getGraph(), ListArc));
	    }
	    return solution;
    }
}
