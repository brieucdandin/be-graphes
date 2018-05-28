package org.insa.graph;

public class LabelStar extends Label {

	private double estimation;

	public LabelStar(Node noeud, boolean marquage, double cout, double estimation) {
		super(noeud, marquage, cout);
		this.estimation = estimation;
	}
	
	public void setCoutTotal(double cout, double estimation) {
		this.cout = cout;
		this.estimation = estimation;
	}
	
	public double getCoutTotal() {
		return (cout + estimation);
	}

	public void setCoutEstime(double estimation) {
		this.estimation = estimation;
	}
	
	public double getCoutEstime() {
		return estimation;
	}
	
}
