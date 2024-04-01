package serveurs;

import java.sql.*;
import java.util.ArrayList;

import abonnes.Abonne;
import documents.Document;

public class AppServeurEmpruntRendu {
	static final String DB_URL = "jdbc:mysql://localhost:3306/mediatheque?useSSL=false"; // MySQL
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String[]args)throws Exception{
		try{
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			conn.setAutoCommit(false);
			System.out.println("On est connecté au serveur sur la base Mediatheque");
			ArrayList<Document> docs = (ArrayList<Document>) getDocument(conn);
			ArrayList<Abonne> abos = (ArrayList<Abonne>) getAbonne(conn);
			new Thread(new ServeurReservation(PORT, docs, abos)).start();
			System.out.println("Serveur lance sur le port " + PORT);

		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
}
