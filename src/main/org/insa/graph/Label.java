package org.insa.graph;

public class Label {

	private Node noeud;
	private Label prec;
	private boolean marquage;
	private int cout;
	
	
	public Label (Node noeud, Label prec, int cout, boolean marquage) {
		this.noeud = noeud;
		this.prec = prec;
		this.cout = cout;
		this.marquage = marquage;
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
