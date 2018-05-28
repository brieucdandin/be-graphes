package org.insa.graph;

public class LabelStar extends Label {

	private double estimation;

	public LabelStar(Node noeud, boolean marquage, double cout, double estimation) {
		// TODO Modifier pour que le classement des noeuds se fasse avec la correction de AStar
		this.marquage = marquage;
		this.noeud = noeud;
		this.cout = cout;
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
