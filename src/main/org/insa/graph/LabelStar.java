package org.insa.graph;

public class LabelStar extends Label {

	private double estimationCout;

	public LabelStar(Node noeud, boolean marquage, double cout, double estimationCout) {
		super(noeud, marquage, cout);
		this.estimationCout = estimationCout;
	}
	
	public void setCoutTotal(double cout, double estimationCout) {
		this.cout = cout;
		this.estimationCout = estimationCout;
	}
	
	public double getCoutTotal() {
		return (cout + estimationCout);
	}

	public void setCoutEstime(double estimationCout) {
		this.estimationCout = estimationCout;
	}
	
	public double getCoutEstime() {
		return estimationCout;
	}
	
	@Override
	/**
	 * Algo renvoyant >0 (resp. < ou =) si supérieur (resp. in. ou egal) a other.
	 */
	public int compareTo(Label other) {
		return (Double.compare(this.getCoutTotal(), other.getCoutTotal()));
	}
	
}
