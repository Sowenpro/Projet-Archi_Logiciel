package documents;

import abonnes.Abonne;

public class Livre extends AbstracDocument {
	private int nbPages;
	
	public Livre(int NumeroDoc, String TitreDoc, String TypeDoc, Abonne emprunteur, Abonne reserveur, int nbPages) {
		super(NumeroDoc);
		super(TitreDoc);
		super(TypeDoc);
		super(emprunteur);
		super(reserveur);
		this.nbPages = nbPages;
	}

	public int nbPages() {
		return nbPages;
	}
}