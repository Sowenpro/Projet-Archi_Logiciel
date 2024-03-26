package documents;

public class Livre extends AbstracDocument {
	private int nbPages;

	public Livre(String nom, int nbPages) {
		super(nom);
		this.nbPages = nbPages;
	}

	public int nbPages() {
		return nbPages;
	}
}