package org.insa.graph;

public class Label {

	private Node noeud;
	private Node prec;
	private boolean marquage;
	private int cout;
	
	
	public Label (Node noeud, Node prec, int cout, boolean marquage) {
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
	
	public Node getNoeudPrec() {
		return this.prec;
	}
	
	public boolean getMarq() {
		return this.marquage;
	}
	
	public void setCout(int cout) {
		this.cout=cout;
	}
	
	public void setPrec(Node prec) {
		this.prec=prec;
	}

	public void setMarq(boolean marquage) {
		this.marquage=marquage;
	}
}
