package documents;

import abonnes.Abonne;

public abstract class AbstracDocument implements Document {
    private static int cpt = 0;
    protected int num;
    protected String nom;
    protected Abonne emprunteur;
    protected Abonne reserveur;

    public AbstracDocument(String nom) {
        num = cpt++;
        this.nom = nom;
        reserveur = null;
        emprunteur = null;
    }


	@Override
    public int numero() {
        return num;
    }

    public String toString() {
        return nom;
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