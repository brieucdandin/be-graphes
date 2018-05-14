package org.insa.graph;

public class Label implements Comparable <Label> {

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

	@Override
	//Algo renvoyant >0 (resp. < ou =) si supérieur (resp. in. ou egal) a other.
	public int compareTo(Label other) {
		if (this.getNoeud().getId() > other.getNoeud().getId()) {return 1;}
		else if (this.getNoeud().getId() > other.getNoeud().getId()) {return 0;}
		else {return -1;}
	}
}
