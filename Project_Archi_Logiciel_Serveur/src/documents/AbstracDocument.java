package documents;

import abonnes.Abonne;

public abstract class AbstracDocument implements Document {
    protected int NumeroDoc;
    protected String TitreDoc;
    protected Abonne emprunteur;
    protected Abonne reserveur;

    public AbstracDocument(int NumeroDoc, String TitreDoc, Abonne emprunteur, Abonne reserveur) {
    	this.NumeroDoc = NumeroDoc;
        this.TitreDoc = TitreDoc;
        this.emprunteur = emprunteur;
        this.reserveur = reserveur;
    }

	@Override
    public int numero() {
        return NumeroDoc;
    }

    public String toString() {
        return TitreDoc;
    }

    @Override
    public Abonne emprunteur() {
        return emprunteur;
    }

    @Override
    public Abonne reserveur() {
        return reserveur;
    }

    @Override
    public void reservationPour(Abonne ab) {
        if (reserveur != null) {
            throw new IllegalStateException("Impossible de réserver un document déjà réservé");
        } else if (emprunteur != null) {
            throw new IllegalStateException("Impossible de réserver un document emprunté");
        }
        reserveur = ab;
    }

    @Override
    public void empruntPar(Abonne ab) {
        if (emprunteur != null) {
            throw new IllegalStateException("Impossible d'emprunter un document déjà emprunté");
        }
        emprunteur = ab;
        reserveur = null;
    }

    @Override
    public void retour() {
        reserveur = null;
        emprunteur = null;
    }
}