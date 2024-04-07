package serveurs;

import java.io.IOException;
import java.net.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import abonnes.Abonne;
import documents.DVD;
import documents.Document;

public class ServeurEmpruntRendu implements Runnable {
	public ServerSocket listen_socket;

	public ServeurEmpruntRendu(int port) throws IOException {
		listen_socket = new ServerSocket(port);
	}
	
	@Override
	public void run() {
		try {
			System.err.println("Lancement du serveur au port " + this.listen_socket.getLocalPort());
			while (true)
				new Thread(new AppServeurEmpruntRendu(listen_socket.accept())).start();
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
        ArrayList<Document> numeros = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT numero FROM document";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                numeros.add(new DVD(rs.getInt("numero")));
                numeros.add(new Livre(rs.getInt("numero")));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numeros;
    }
	
	public static ArrayList<Abonne> getAbonne(Connection conn) {
        ArrayList<Abonne> numeros = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT NumeroAb FROM abonne";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int numero = rs.getInt("NumeroAb");
                numeros.add(new DVD(rs.getInt("NumeroAb")));
                numeros.add(new Livre(rs.getInt("NumeroAb")));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numeros;
    }
}
