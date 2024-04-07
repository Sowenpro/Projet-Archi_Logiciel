package serveurs;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import abonnes.Abonne;
import documents.Document;

public class AppServeurEmpruntRendu implements Runnable{
	static final String DB_URL = "jdbc:mysql://localhost:3306/mediatheque?useSSL=false"; // MySQL
	static final String USER = "root";
	static final String PASS = "root";
	private Socket socket;

	public AppServeurEmpruntRendu(Socket accept) {
		this.socket = accept;
	}

	@Override
	public void run() {
		try {
			try{
				// **** Connexion à la base de donnée *****************
					
				Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				conn.setAutoCommit(false);
				System.out.println("On est connecté au serveur sur la base Mediatheque");
				// ********************************************************
					
					
				// **** Lancement du serveur *****************
					
				System.out.println("Serveur lancé sur le port 4000");
				// ********************************************************
					
					
				// **** Ressources partagées : les documents & les abonnés *****************
					
				ArrayList<Document> docs = (ArrayList<Document>) ServeurEmpruntRendu.getDocument(conn);
				ArrayList<Abonne> abos = (ArrayList<Abonne>) ServeurEmpruntRendu.getAbonne(conn);
				// ********************************************************
				
				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				String phrase_serveur;
				String phrase_serveur_err;
				phrase_serveur = ("Voulez-vous emprunter, retourner ou quitter ? (e/r/q)");
				out.writeUTF(phrase_serveur);
				String reponse = in.readUTF();
				if (reponse.compareTo("e") == 0) {
					phrase_serveur = ("Vous avez choisi d'emprunter\nVeuillez saisir votre numero d'abonné: ");
					out.writeUTF(phrase_serveur);
					Integer numAb = in.readInt();
					Abonne abonne = null;
					for(Abonne a : abos) {
						if(numAb == a.getNum()) {
							abonne = a;
						}
						else {
							phrase_serveur = ("Abonne inconnue, veuillez donner un NumAb valide.");
							out.writeUTF(phrase_serveur);
						}
					}
					phrase_serveur = ("Veuillez saisir le numéro du document: ");
					out.writeUTF(phrase_serveur);
					Integer empruntdoc = in.readInt();
					for(Document doc : docs) {
						if(empruntdoc == doc.numero()) {
							doc.reservationPour(abonne);
						}
						else {
							phrase_serveur = ("Document inconnue, veuillez donner un document valide.");
							out.writeUTF(phrase_serveur);
						}
					}
					String msgconfirmation = numAb +" a emprunté le document "+ empruntdoc;
					out.writeUTF(msgconfirmation);
				}
				else if (reponse.compareTo("r") == 0) {
					phrase_serveur = ("Vous avez choisi de retourner\nVeuillez saisir le numéro du document: ");
					out.writeUTF(phrase_serveur);
					Integer returndoc = in.readInt();
					for(Document doc : docs) {
						if(returndoc == doc.numero()) {
							doc.retour();
						}
						else {
							phrase_serveur = ("Document inconnue, veuillez donner un document valide.");
							out.writeUTF(phrase_serveur);
						}
					}
					String msgconfirmation = "Vous avez rendu le document "+ returndoc;
					out.writeUTF(msgconfirmation);
				}
				else if (reponse.compareTo("q") == 0) {
					phrase_serveur = ("Voulez-vous vraiment quitter l'application ? (y/n)");
					out.writeUTF(phrase_serveur);
					String rep = in.readUTF();
					if (rep.compareTo("y") == 0) {
					socket.close();
					}
					else if (rep.compareTo("n") == 0) {
					}
					else {
						phrase_serveur_err = ("Commande inconnue, veuillez réessayer. (y/n)");
						out.writeUTF(phrase_serveur_err);
					}
				}
				else {
					phrase_serveur_err = ("Commande inconnue, veuillez réessayer.(e/r/q)");
					out.writeUTF(phrase_serveur_err);
				}	
			}
			catch(SQLException e ){
				e.printStackTrace();
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
