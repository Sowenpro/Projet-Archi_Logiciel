package documents;

import abonnes.Abonne;

public class Livre implements Document {

	@Override
	public int numero() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Abonne emprunteur() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Abonne reserveur() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reservationPour(Abonne ab) {
		// TODO Auto-generated method stub

	}

	@Override
	public void empruntPar(Abonne ab) {
		// TODO Auto-generated method stub

	}

	@Override
	public void retour() {
		// TODO Auto-generated method stub

	}

}
