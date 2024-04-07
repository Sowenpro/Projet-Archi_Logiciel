package serveurs;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import abonnes.Abonne;
import documents.DVD;
import documents.Document;

public class ServeurReservation implements Runnable {
	public ServerSocket listen_socket;

	public ServeurReservation(int port) throws IOException {
		listen_socket = new ServerSocket(port);
	}

	@Override
	public void run() {
		try {
			System.err.println("Lancement du serveur au port " + this.listen_socket.getLocalPort());
			while (true)
				new Thread(new AppServeurResevation(listen_socket.accept())).start();
		} 
		catch (IOException e) {
			try {
				this.listen_socket.close();
			} 
			catch (IOException e1) {
			}
			System.err.println("Arrêt du serveur au port " + this.listen_socket.getLocalPort());
		}
	}
	
	public static ArrayList<Document> getDocument(Connection conn) {
	    ArrayList<Document> docs = new ArrayList<>();
	    Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        String sql;
	        sql = "SELECT NumeroDoc, TitreDoc, TypeDoc, emprunteur, reserveur FROM document";
	        ResultSet rs = stmt.executeQuery(sql);
	        while (rs.next()) {
	            int NumeroDoc = rs.getInt("NumeroDoc");
	            String TitreDoc = rs.getString("TitreDoc");
	            String TypeDoc = rs.getString("TypeDoc");
	            String emprunteur = rs.getString("emprunteur");
	            String reserveur = rs.getString("reserveur");
	            docs.add(new DVD(NumeroDoc, TitreDoc, TypeDoc, emprunteur, reserveur));
	        }
	        rs.close();
	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return docs;
	}
	
	public static ArrayList<Abonne> getAbonne(Connection conn) {
        ArrayList<Abonne> abos = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT NumeroAb, NomAb, DateDeNaissanceAb FROM abonne";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int NumeroAb = rs.getInt("NumeroAb");
                String NomAb = rs.getString("NomAb");
	            Date DateDeNaissanceAb = rs.getDate("DateDeNaissanceAb");
	            abos.add(new Abonne(NumeroAb, NomAb, DateDeNaissanceAb));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return abos;
    }
}
