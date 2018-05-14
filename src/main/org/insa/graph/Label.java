package org.insa.graph;

public class Label {

	private Node noeud;
	private Label prec;
	private boolean marquage;
	private int cout;
	
	
<<<<<<< HEAD
	public Label (Node noeud, Node prec, int cout, boolean marquage) {
		this.noeud = noeud; //noeud courant
		this.prec = prec; // noeud père
		this.cout = cout; // valeur depuis l'origine j'ausqu'au noeud courant
		this.marquage = marquage; // si coût min connu par l'algo
=======
	public Label (Node noeud, Label prec, int cout, boolean marquage) {
		this.noeud = noeud;
		this.prec = prec;
		this.cout = cout;
		this.marquage = marquage;
>>>>>>> 05c9d5a97f3e0458950d88a5ea4e25b28dfe36fb
	}
	
	public int getCout() {
		return this.cout;
	}
	
	public Node getNoeud() {
		return this.noeud;
	}
	
	public Label getNoeudPrec() {
		return this.prec;
	}
	
	public boolean getMarq() {
		return this.marquage;
	}
	
	public void setCout(int cout) {
		this.cout=cout;
	}
	
	public void setPrec(Label prec) {
		this.prec=prec;
	}

	public void setMarq(boolean marquage) {
		this.marquage=marquage;
	}
	
	public void setNoeud (Node noeud) {
		this.noeud = noeud;
	}
}
