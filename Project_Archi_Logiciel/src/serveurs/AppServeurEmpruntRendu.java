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
				Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				conn.setAutoCommit(false);
				System.out.println("On est connecté au serveur sur la base Mediatheque");
				//ArrayList<Document> docs = (ArrayList<Document>) getDocument(conn);
				//ArrayList<Abonne> abos = (ArrayList<Abonne>) getAbonne(conn);
				System.out.println("Serveur lancé sur le port 4000");
			}
			catch(SQLException e ){
				e.printStackTrace();
			}
			
			DataInputStream in = new DataInputStream(socket.getInputStream());
			String reponse = in.readUTF();
			if (reponse == "e") {
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				Integer numAb = in.readInt();
				String empruntdoc = in.readUTF();
				String msgconfirmation = numAb +" a emprunté le document "+ empruntdoc;
				out.writeUTF(msgconfirmation);
			}
			else if (reponse == "r") {
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				Integer returndoc = in.readInt();
				String msgconfirmation = "Vous avez rendu le document "+ returndoc;
				out.writeUTF(msgconfirmation);
			}
			if (reponse == "q") {
				socket.close();
			}
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
