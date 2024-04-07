package serveurs;

import java.io.IOException;

public class Appli {

	public static void main(String[] args) throws IOException {
		new Thread(new ServeurEmpruntRendu(4000)).start();
		new Thread(new ServeurReservation(3000)).start();
	}
}
