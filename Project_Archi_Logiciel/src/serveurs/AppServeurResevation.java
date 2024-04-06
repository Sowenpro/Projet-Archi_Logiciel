package serveurs;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import abonnes.Abonne;
import documents.Document;

public class AppServeurResevation implements Runnable{
	static final String DB_URL = "jdbc:mysql://localhost:3306/mediatheque?useSSL=false"; // MySQL
	static final String USER = "root";
	static final String PASS = "root";
	private Socket socket;

	public AppServeurResevation(Socket accept) {
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
				
				
				// **** Ressources partagées : les documents & les abonnés *****************
				
				//ArrayList<Document> docs = (ArrayList<Document>)getDocument(conn);
				//ArrayList<Abonne> abos = (ArrayList<Abonne>)getAbonne(conn);
				// ********************************************************
				
				
				// **** Lancement du serveur *****************
				
				System.out.println("Serveur lancé sur le port 3000");
				// ********************************************************
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			String phrase_serveur;
			String phrase_serveur_err;
			phrase_serveur = ("Voulez-vous réserver ou quitter ? (r/q)");
			out.writeUTF(phrase_serveur);
			String reponse = in.readUTF();
			if (reponse.compareTo("r") == 0) {
				phrase_serveur = ("Veuillez saisir votre numéro d'abonné: ");
				out.writeUTF(phrase_serveur);
				Integer numAb = in.readInt();
				phrase_serveur = ("Veuillez saisir le numéro du document: ");
				out.writeUTF(phrase_serveur);
				String reservationdoc = in.readUTF();
				String msgconfirmation = numAb +" a réservé le document "+ reservationdoc;
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
				phrase_serveur_err = ("Commande inconnue, veuillez réessayer.(r/q)");
				out.writeUTF(phrase_serveur_err);
			}
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
