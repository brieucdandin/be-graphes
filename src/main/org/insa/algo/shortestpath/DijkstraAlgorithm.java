package org.insa.algo.shortestpath;

import org.insa.graph.*;
import java.util.ArrayList;

import org.insa.algo.utils.BinaryHeap;



public class DijkstraAlgorithm extends ShortestPathAlgorithm {

	private ArrayList<Label> ListeLabels = new ArrayList<Label>();
	
//	protected int zoneOrigine;
    protected int idOrigine;
//
//    protected int zoneDestination;
    protected int idDestination;
//
//    protected HashMap<Noeud, Label> labelNoeud;
//    protected BinaryHeap<Label> tas;
    protected Graph graphe;
//
//    protected Chemin chemin_result;
//
//    protected int mode;


    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
        BinaryHeap<Label> tas = null;
        
        
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
        
        
        boolean destinationAtteinte = false;

        // Pour les statistiques
        stats.start_counter();
        int taille_tas = 1; // pour eviter de demander la taille du tas à chaque tour
        int max_taille = taille_tas;
        int elts_marques = 0;

        
        boolean compareTemps;
        boolean compareDistance;

        while (!tas.isEmpty()) {
            // On récupere le plus petit element du tas pour le traiter
            Node x = tas.deleteMin().getNoeud();
            taille_tas--;

//            labelNoeud.get(x).setMarquage(true);	//TODO: hashmap a creer
            elts_marques++;
            //x.dessine(d); // laisser x...

            // Si on traite le sommet là on est bon
            if (x.getId() == this.idDestination) {
                destinationAtteinte = true;
                break;
            }

            //Pour chaque noeud voisin de x
            for (Arc a : x.getSuccessors()) {
                Node dest = a.getDestination();

                //On recupere son label
                Label dLabel = labelNoeud.get(dest);

                //S'il est pas deja marque on le traite
                if (!dLabel.getMarq()) {

                    compareTemps = dLabel.getCout() > labelNoeud.get(x).getCout() + a.getTravelTime() && this.mode == PlusCourtChemin.MODE_TEMPS;
                    compareDistance = dLabel.getCout() > labelNoeud.get(x).getCout() + a.getTravelTime() * a.getDescripteur().vitesseMax() * 1000 / 60 && (this.mode == PlusCourtChemin.MODE_DISTANCE);


                    // Si son cout actuel est plus grand que le cout depuis x alors on maj
                    if (compareTemps || compareDistance) {
                        if (compareTemps) {
                            dLabel.setCout(labelNoeud.get(x).getCout() + a.getTravelTime());
                        } else {
                            dLabel.setCout(labelNoeud.get(x).getCout() + a.getTravelTime() * a.getDescripteur().vitesseMax() * 1000 / 60);
                        }

                        //TODO: Utiliser les methodes de BinaryHeap
                        if (tas.exist(dLabel)) {
                            dLabel.setPere(x);
                            tas.update(dLabel);
                        } else {
                            dLabel.setPere(x);
                            tas.insert(dLabel);
                            taille_tas++;
                        }
                    }

                }

            }

            if (taille_tas > max_taille) {
                max_taille = taille_tas;
            }
        }

//        stats.setNbre_max_elt_tas(max_taille);
//        stats.setNbre_sommets_parcourus(elts_marques);
//
//        stats.stop_counter();

        // On recupere le chemin si on atteint le sommet
        if (destinationAtteinte) {

            Label noeud_courant = labelNoeud.get(this.graphe.getNoeudById(this.destination));

            PlusCourtChemin.addNoeud(noeud_courant.getSommet_courant());
            while (noeud_courant.getPere() != null) {
            	PlusCourtChemin.addNoeud(noeud_courant.getPere());
                noeud_courant = labelNoeud.get(noeud_courant.getPere());
            }

            PlusCourtChemin.inverseChemin();
            PlusCourtChemin.initArcAvecNoeuds();

            PlusCourtChemin.dessineChemin(graphe.getDessin(), false);

            stats.setNbre_sommets_chemin(PlusCourtChemin.getNoeuds_chemin().size());
            stats.setNbre_arcs_chemin(PlusCourtChemin.getArcs_chemin().size());
            stats.setCheminValide(PlusCourtChemin.checkChemin());
            stats.setLongueur_chemin(PlusCourtChemin.calculDistanceChemin());
            stats.setTemps_parcours_chemin(PlusCourtChemin.calculTempsChemin());

            if (affichage_stats) {
                stats.print();
            }

            PlusCourtChemin.setStatistiques(stats);
        } else {
            System.out.println("[Djikstra] Aucun chemin trouvé entre " + this.idOrigine + " et " + this.idDestination);
        }

        this.chemin_result = PlusCourtChemin;

        return PlusCourtChemin;

        
        
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
