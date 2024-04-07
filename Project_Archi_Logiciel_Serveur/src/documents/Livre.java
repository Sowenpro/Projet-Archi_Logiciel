package documents;

import abonnes.Abonne;

public class Livre extends AbstractDocument {
	private int nbPages;
	
	public Livre(int NumeroDoc, String TitreDoc, Abonne emprunteur, Abonne reserveur, int nbPages) {
		super(NumeroDoc, TitreDoc, emprunteur, reserveur);
		this.nbPages = nbPages;
	}

	public int nbPages() {
		return nbPages;
	}
}