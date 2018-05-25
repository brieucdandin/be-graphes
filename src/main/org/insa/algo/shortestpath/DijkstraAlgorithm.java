package org.insa.algo.shortestpath;

import org.insa.graph.*;
import java.util.ArrayList;
import java.util.HashMap;

import org.insa.algo.utils.BinaryHeap;



public class DijkstraAlgorithm extends ShortestPathAlgorithm {

	private ArrayList<Label> ListeLabels = new ArrayList<Label>();
	
//	protected int zoneOrigine;
    protected int idOrigine;

//    protected int zoneDestination;
    protected int idDestination;

    protected HashMap<Node, Label> labelNoeud;
    protected BinaryHeap<Label> tas;
    protected Graph graphe;

//    protected Chemin chemin_result;

//    protected int mode;


    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);

        
        /**			INITIALISATION
         * 
         * For tous les sommets i  de 1 a n
         * 		Mark(i) <- Faux
         * 		Cost(i) <- +inf
         * 		Father(i) <- 0   //sommet inexistant
         * end for
         * Cost(s) <- 0
         * Insert(s, tas)
         */


        Path PlusCourtChemin = new Path(this.data.getGraph(), this.data.getGraph().get(this.ListeLabels.get(idOrigine).getNoeud().getId() /*origine*/),
        													  this.data.getGraph().get(this.ListeLabels.get(idDestination).getNoeud().getId()) /*destination*/);

        //Verification de l'existence des extremites 
        if (PlusCourtChemin.getOrigin() == null) {
            System.out.println("[ERREUR] L'origine demandée (" + this.idOrigine + ") n'existe pas");
        }
        else if (PlusCourtChemin.getDestination() == null) {
            System.out.println("[ERREUR] La destination demandée (" + this.idDestination + ") n'existe pas");
        }

        //Creation des labels
        for(Node n: data.getGraph().getNoeuds()) {
        	Label l = new Label(n, null, null, Double.POSITIVE_INFINITY, false);
        	ListeLabels.add(l);
        }
        ListeLabels.get(idOrigine).setCout(0);
        Node NoeudOrigine = data.getGraph().get(this.idOrigine);
        

        //Insertion du sommet source dans le tas
        tas.insert(ListeLabels.get(idOrigine));	//TODO: Il y avait une erreur ici ! (Cf. warning)


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
        
        
        boolean DestinationAtteinte = false;

//        // Pour les statistiques
//        stats.start_counter();
        int TailleTas = 1;	//Permet de ne pas avoir a demander la taille du tas a chaque iteration
        int TailleMax = TailleTas;
        int ElementsMarques = 0;

        
        boolean compareTemps;
        boolean compareDistance;

        while (!tas.isEmpty()) {
            // On récupere le plus petit element du tas pour le traiter
            Node x = tas.deleteMin().getNoeud();
            TailleTas--;

            labelNoeud.get(x).setMarq(true);
            ElementsMarques++;
//            x.dessine(d); // laisser x...

            //Fin si la destination est atteinte
            if (x.getId() == this.idDestination) {
            	DestinationAtteinte = true;
                break;
            }

            //Pour chaque noeud voisin de x
            for (Arc a : x.getSuccessors()) {
                Node dest = a.getDestination();

                //On recupere son label
                Label LabelDestination = labelNoeud.get(dest);

                //Traitement des sommets non-marques
                if (!LabelDestination.getMarq()) {

                    compareTemps = LabelDestination.getCout() > labelNoeud.get(x).getCout() + a.getTravelTime() && this.mode == PlusCourtChemin.MODE_TEMPS;
                    compareDistance = LabelDestination.getCout() > labelNoeud.get(x).getCout() + a.getTravelTime() * a.getDescripteur().vitesseMax() * 1000 / 60 && (this.mode == PlusCourtChemin.MODE_DISTANCE);


                    //Si le out est plus petit, on l'update
                    if (compareTemps || compareDistance) {
                        if (compareTemps) {
                        	LabelDestination.setCout(labelNoeud.get(x).getCout() + a.getTravelTime());
                        } else {
                        	LabelDestination.setCout(labelNoeud.get(x).getCout() + a.getTravelTime() * a.getDescripteur().vitesseMax() * 1000 / 60);
                        }

                        //TODO: Utiliser les methodes de BinaryHeap
                        if (tas.exist(LabelDestination)) {
                        	LabelDestination.setNoeudPrec(x);
                            tas.update(LabelDestination);
                        } else {
                        	LabelDestination.setNoeudPrec(x);
                            tas.insert(LabelDestination);
                            TailleTas++;
                        }
                    }
                }
            }

            if (TailleTas > TailleMax) {
            	TailleMax = TailleTas;
            }
        }

//        stats.setNbre_max_elt_tas(max_taille);
//        stats.setNbre_sommets_parcourus(elts_marques);
//
//        stats.stop_counter();

        // On recupere le chemin si on atteint le sommet
        if (DestinationAtteinte) {

            Label noeud_courant = labelNoeud.get(this.graphe.get(this.idDestination));

<<<<<<< HEAD
            PlusCourtChemin.addNoeud(noeud_courant.getSommet_courant());
=======
            PlusCourtChemin.addNoeud(noeud_courant.getNoeud());
>>>>>>> 5d41b4e9a1721de5e131071517d089f4663c9136
            while (noeud_courant.getNoeudPrec() != null) {
            	PlusCourtChemin.addNoeud(noeud_courant.getNoeudPrec());
                noeud_courant = labelNoeud.get(noeud_courant.getNoeudPrec());
            }

            PlusCourtChemin.inverseChemin();
            PlusCourtChemin.initArcAvecNoeuds();

//            PlusCourtChemin.dessineChemin(graphe.getDessin(), false);

//            stats.setNbre_sommets_chemin(PlusCourtChemin.getNoeuds_chemin().size());
//            stats.setNbre_arcs_chemin(PlusCourtChemin.getArcs_chemin().size());
//            stats.setCheminValide(PlusCourtChemin.checkChemin());
//            stats.setLongueur_chemin(PlusCourtChemin.calculDistanceChemin());
//            stats.setTemps_parcours_chemin(PlusCourtChemin.calculTempsChemin());

//            if (affichage_stats) {
//                stats.print();
//            }
//
//            PlusCourtChemin.setStatistiques(stats);
//        } else {
//            System.out.println("[Djikstra] Aucun chemin trouvé entre " + this.idOrigine + " et " + this.idDestination);
//        }

//        this.chemin_result = PlusCourtChemin;

//        return PlusCourtChemin;

        
        
//        while (!ParcoursMarq(ListeLabels)) {
//        	
//       		Label lx = tas.findMin();
//       		lx.setMarq(true);
//       		
//       		//On parcourt la liste de labels pour enregistrer ceux des points fils de x dans Successeursdex.
//       		ArrayList<Label> Successeursdex = new ArrayList<Label>();
//       		for (Label lk : ListeLabels) {
//       			if (lk.getNoeudPrec() == lx.getNoeud()) {
//       				Successeursdex.add(lk);
//       			}
//       		}
//       		
//       		//On parcourt la liste des labels des successeurs de x (obtenue ci-dessus).
//        	for (Label ly : Successeursdex) {
//        		if (ly.getMarq() == false) {
//        			
//        			// Mise en memoire tampon de Cost(y) pour le test du prochain if
//        			double a = ly.getCout();
//        			
//        			// TODO: Verifier que l'algo fonctionne bien comme ca : on est partis du principe que W(x,y) = min(cout(x), Cout(x))
//        			ly.setCout(Math.min(ly.getCout(), lx.getCout() + Math.min(ly.getCout(), lx.getCout()) ));
//        			
//        			// Si y a ete modifie
//        			if (a != ly.getCout()) {
//        				tas.insert(ly);
//        				ly.setNoeudPrec(lx.getNoeud());
//        			}
//        		}
//        	} // FIN FOR successeurs
//            
//        } // Fin ITERATION
        
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
