package serveurs;
import java.net.Socket;
import java.util.List;

import abonnes.Abonne;
import documents.Document;

public class ServiceEmpruntRendu implements Runnable {

	// **** ressources partagées : les documents *****************
	private static List<Document> lesDocuments;

	public static void setLesDocs(List<Document> lesDocuments) {
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
		
	}
}
