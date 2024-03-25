package serveurs;
import java.net.Socket;
import abonnes.Abonne;
import documents.Document;

public class ServiceReservation implements Runnable {	
	// **** ressources partagées : les documents *****************
	private static List<doc> lesDocuments;

	public static void setLesDocs(List<doc> lesDocuments) {
		ServiceReservation.lesDocuments = lesDocuments;
	}
	
	private static Document getDoc(int numero) {
		for (Document doc : lesDocuments)
			if (doc.numero() == numero)
				return doc;
		return null;
	}
	// ********************************************************
	
	private final Socket document;


	ServiceReservation(Socket socket) {
		this.document = socket;
	}
	
	@Override
	public void run() {
		// **** Methode Reservation *****************	
		reservationPour(Abonne ab, document doc){
			Abonne reserveur(doc) = reserveur;
			if (reserveur==null) {
				Abonne reserveur = this.NumeroAb;
			}
			else {
				System.out.println("Reservation impossible");
			}
		}
	}
}
