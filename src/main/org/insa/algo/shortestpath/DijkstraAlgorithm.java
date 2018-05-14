package org.insa.algo.shortestpath;

import org.insa.graph.*;
import java.util.ArrayList;

import org.insa.algo.utils.BinaryHeap;
/*
 * import org.insa.graph.Arc;
 * import org.insa.graph.Graph;
 * import org.insa.graph.Node;
 * import org.insa.graph.Path;
 */

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

	private ArrayList<Label> ListeLabels = new ArrayList<Label>();

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
        BinaryHeap<Label> tas = null;
        
        
        /**			INITIALISATION
         * 
         * For tous les sommets i  de 1 a n
         * 		Mark(i) <- Faux
         * 		Cost(i) <- +inf
         * 		Father(i) <- 0   // sommet inexistant
         * end for
         * Cost(s) <- 0
         * Insert(s, tas)
         */
        
        for(Node n: data.getGraph()) {
        	Label l = new Label(n, null, null, Double.POSITIVE_INFINITY, false);
        	ListeLabels.add(l);
        }
        ListeLabels.get(0).setCout(0);

        //Insertion du sommet source dans le tas
        tas.insert(ListeLabels.get(0));	//TODO: Erreur ici ! Cf. warning
        
        
        /**			ITERATION
         * 
         * While il existe des sommets non marques
         * 		x <- ExtractMin(tas)
         * 		Mark(x) <- true
         * 		For tous les y successeurs de x
         * 			If not Mark(y) then
         * 				Cost(y) <- Min(Cost(y), Cost(x)+W(x,y))	//W est le poids de l'arc allant de x a y
         * 				If Cost(y) a ete mis a jour then
         * 					Placer(y, tas)
         * 					Father(y) <- x
         * 				end if
         * 			end if
         * 		end for
         * end while
         */
        
        while (!ParcoursMarq(ListeLabels)) {
        	
       		Label lx = tas.findMin();
       		lx.setMarq(true);
       		
       		//On parcourt la liste de labels pour enregistrer ceux des points fils de x dans Successeursdex.
       		ArrayList<Label> Successeursdex = new ArrayList<Label>();
       		for (Label lk : ListeLabels) {
       			if (lk.getNoeudPrec() == lx.getNoeud()) {
       				Successeursdex.add(lk);
       			}
       		}
       		
       		//On parcourt la liste des labels des successeurs de x (obtenue ci-dessus).
        	for (Label ly : Successeursdex) {
        		if (ly.getMarq() == false) {
        			
        			// Mise en memoire tampon de Cost(y) pour le test du prochain if
        			double a = ly.getCout();
        			
        			// TODO: Verifier que l'algo fonctionne bien comme ca : on est partis du principe que W(x,y) = min(cout(x), Cout(x))
        			ly.setCout(Math.min(ly.getCout(), lx.getCout() + Math.min(ly.getCout(), lx.getCout()) ));
        			
        			// Si y a ete modifie
        			if (a != ly.getCout()) {
        				tas.insert(ly);
        				ly.setNoeudPrec(lx.getNoeud());
        			}
        		}
        	} // FIN FOR successeurs
            
        } // Fin ITERATION
        
    } // Fin constructeur

    
	/**
	 * Parcours pour savoir si les labels ont ete marques
	 */
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
