package org.insa.graph;

public class Label implements Comparable <Label> {

	private Node noeud;
	private Node noeudPrec;
//	private Arc arcPrec;
	private boolean marquage;
	private double cout;


	public Label (Node noeud, Node noeudPrec, Arc arcPrec, double cout, boolean marquage) {
		this.noeud = noeud; 		// Noeud courant
		this.noeudPrec = noeudPrec;	// Noeud pere
//		this.arcPrec = arcPrec;		// Arc pere	//TODO: A enlever si pas necessaire
		this.cout = cout;			// Valeur depuis l'origine jusqu'au noeud courant
		this.marquage = marquage;	// Si cout min connu par l'algo
	}

	public double getCout() {
		return this.cout;
	}
	
	public Node getNoeud() {
		return this.noeud;
	}
	
	public Node getNoeudPrec() {
		return this.noeudPrec;
	}
	
//	public Arc getArcPrec() {
//		return this.arcPrec;
//	}
	
	public boolean getMarq() {
		return this.marquage;
	}
	
	public void setCout(double cout) {
		this.cout=cout;
	}
	
	public void setNoeudPrec(Node noeudPrec) {
		this.noeudPrec = noeudPrec;
	}
	
//	public void setArcPrec(Arc arcPrec) {
//		this.arcPrec=arcPrec;
//	}

	public void setMarq(boolean marquage) {
		this.marquage=marquage;
	}
	
	public void setNoeud (Node noeud) {
		this.noeud = noeud;
	}

	@Override
	/**
	 * Algo renvoyant >0 (resp. < ou =) si supérieur (resp. in. ou egal) a other.
	 */
	public int compareTo(Label other) {
		if (this.getNoeud().getId() > other.getNoeud().getId()) {return 1;}
		else if (this.getNoeud().getId() > other.getNoeud().getId()) {return 0;}
		else {return -1;}
	}

}
