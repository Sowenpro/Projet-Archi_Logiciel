package serveurs;
import java.net.Socket;
import abonnes.Abonne;
import documents.Document;

public class ServiceEmpruntRendu implements Runnable {

	// **** ressources partagées : les documents *****************
	private static List<doc> lesDocuments;

	public static void setLesDocs(List<doc> lesDocuments) {
		ServiceEmpruntRendu.lesDocuments = lesDocuments;
	}
	
	private static Document getDoc(int numero) {
		for (Document doc : lesDocuments)
			if (doc.numero() == numero)
				return doc;
		return null;
	}
	// ********************************************************

	private final Socket document;

	ServiceEmpruntRendu(Socket socket) {
		this.document = socket;
	}

	@Override
	public void run() {
		// **** Methode Emprunt *****************
		empruntPar(Abonne ab,document doc){
			Abonne emprunteur(doc)=emprunteur;
				if (emprunteur==null) {
					Abonne emprunteur = this.NumeroAb;
				}
				else {
					System.out.println("Emprunt impossible");
				}	
			}
		
		
		// **** Methode Retour *****************
		retour(Abonne ab,document doc){
			Abonne emprunteur(doc)= emprunteur;
			reservationPour(Abonne ab) = reserveur;
			if(!(emprunteur==null || reserveur==null)) {
				Abonne emprunteur = null;
				Abonne reserveur = null;
			}
			else {
				System.out.println("Retour impossible");
			}
		}
	}
}
