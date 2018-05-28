package org.insa.graph;

public class Label implements Comparable <Label> {

	protected Node noeud;
	protected boolean marquage;
	protected double cout;

	// TODO Ici, j'ai rajoute un constructeur sans argument pour regler une erreur d'heritage vers LabelStar. Il serait mieux de ne pas s'en encombrer.
	public Label() {
		super();
	}
	
	public Label(Node noeud, boolean marquage, double cout) {
		this.marquage = marquage;
		this.noeud = noeud;
		this.cout = cout;
	}

	public double getCout() {
		return this.cout;
	}
	
	public Node getNoeud() {
		return this.noeud;
	}

	public boolean getMarq() {
		return this.marquage;
	}

	public double getCoutTotal() {
		return cout;
	}

	public void setCoutTotal(double cout) {
		this.cout = cout;
	}
	
	public void setCout(double cout) {
		this.cout=cout;
	}

	public void setMarq(boolean marquage) {
		this.marquage=marquage;
	}
	
	public void setNoeud(Node noeud) {
		this.noeud = noeud;
	}

	@Override
	/**
	 * Algo renvoyant >0 (resp. < ou =) si supérieur (resp. in. ou egal) a other.
	 */
	public int compareTo(Label other) {
		return (Double.compare(this.getCoutTotal(), other.getCoutTotal()));
	}

}
