package org.insa.graph;

public class Label implements Comparable <Label> {

	private Node noeud;
	private Node Nprec;
	private Arc Aprec;
	private boolean marquage;
	private int cout;

	

	

	public Label (Node noeud, Node Nprec, Arc Aprec, int cout, boolean marquage) {
		this.noeud = noeud; //noeud courant
		this.Nprec = Nprec; // noeud père
		this.Aprec = Aprec; // arc père
		this.cout = cout; // valeur depuis l'origine j'ausqu'au noeud courant
		this.marquage = marquage; // si coût min connu par l'algo
	}

	/*public Label (Node noeud, Label prec, int cout, boolean marquage) {
	public Label (Node noeud, Label prec, int cout, boolean marquage) {
		this.noeud = noeud;
		this.prec = prec;
		this.cout = cout;
		this.marquage = marquage;
	}*/

	
	public int getCout() {
		return this.cout;
	}
	
	public Node getNoeud() {
		return this.noeud;
	}
	
	public Node getNoeudPrec() {
		return this.Nprec;
	}
	
	public Arc getArcPrec() {
		return this.Aprec;
	}
	
	public boolean getMarq() {
		return this.marquage;
	}
	
	public void setCout(int cout) {
		this.cout=cout;
	}
	
	public void setNoeudPrec(Node Nprec) {
		this.Nprec=Nprec;
	}
	
	public void setArcPrec(Arc Aprec) {
		this.Aprec=Aprec;
	}

	public void setMarq(boolean marquage) {
		this.marquage=marquage;
	}
	
	public void setNoeud (Node noeud) {
		this.noeud = noeud;
	}

	@Override
	//Algo renvoyant >0 (resp. < ou =) si supérieur (resp. in. ou egal) a other.
	public int compareTo(Label other) {
		if (this.getNoeud().getId() > other.getNoeud().getId()) {return 1;}
		else if (this.getNoeud().getId() > other.getNoeud().getId()) {return 0;}
		else {return -1;}
	}
}
