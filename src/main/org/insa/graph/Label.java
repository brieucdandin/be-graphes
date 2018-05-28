package org.insa.graph;

public class Label implements Comparable <Label> {

	private Node noeud;
	private boolean marquage;
	private double cout;

	public Label(Node noeud) {
		this.marquage = false;
		this.noeud = noeud;
		this.cout = Double.POSITIVE_INFINITY;
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
