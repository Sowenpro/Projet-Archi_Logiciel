package documents;

import abonnes.Abonne;

public class DVD extends AbstractDocument {
	private boolean estAdulte;

	public DVD(int NumeroDoc, String TitreDoc, Abonne emprunteur, Abonne reserveur, boolean estAdulte) {
		super(NumeroDoc, TitreDoc, emprunteur, reserveur);
		this.estAdulte = estAdulte;
	}

	public boolean estAdulte() {
		return estAdulte;
	}

	@Override
    public void reservationPour(Abonne ab) {
		if (reserveur != null) {
            throw new IllegalStateException("Impossible de réserver un document déjà réservé");
        } else if (emprunteur != null) {
            throw new IllegalStateException("Impossible de réserver un document emprunté");
        } else if (estAdulte && (ab.getAge() >= 16)) {
			reserveur = ab;
			return;
		} else if (!estAdulte) {
			reserveur = ab;
			return;
		}
        throw new IllegalArgumentException("L'abonné n'a pas l'âge requis");
    }

    @Override
    public void empruntPar(Abonne ab) {
		if (emprunteur != null) {
            throw new IllegalStateException("Impossible d'emprunter un document déjà emprunté");
        } else if (estAdulte && (ab.getAge() >= 16)) {
			emprunteur = ab;
        	reserveur = null;
			return;
		} else if (!estAdulte) {
			emprunteur = ab;
        	reserveur = null;
			return;
		}
        throw new IllegalArgumentException("L'abonné n'a pas l'âge requis");
    }
}
