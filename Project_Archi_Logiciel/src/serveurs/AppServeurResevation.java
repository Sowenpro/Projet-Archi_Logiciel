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
			System.out.println("Voulez-vous réserver ou quitter ? (r/q)");
			String reponse = in.readUTF();
			if (reponse.compareTo("r") == 0) {
				System.out.println("Veuillez saisir votre numéro d'abonné: ");
				Integer numAb = in.readInt();
				System.out.println("Veuillez saisir le numéro du document: ");
				String reservationdoc = in.readUTF();
				String msgconfirmation = numAb +" a réservé le document "+ reservationdoc;
				out.writeUTF(msgconfirmation);
			}
			else if (reponse.compareTo("q") == 0) {
				System.out.println("Voulez-vous vraiment quitter l'application ? (y/n)");
				String rep = in.readUTF();
				if (rep.compareTo("y") == 0) {
				socket.close();
				}
				else if (rep.compareTo("n") == 0) {
					System.out.println("Donc que voulez-vous réserver ? (r)");
				}
				else {
					System.out.println("Commande inconnue, veuillez réessayer. (y/n)");
				}
			}
			else {
				System.out.println("Commande inconnue, veuillez réessayer.(r/q)");
			}
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
