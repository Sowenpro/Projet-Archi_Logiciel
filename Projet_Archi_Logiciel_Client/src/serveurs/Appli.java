package serveurs;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.net.Socket;
import java.util.Scanner;

public class Appli {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Choisir un port entre 3000 ou 4000");
		int port = sc.nextInt();
		if (port == 4000) {
			while (true) {
				// Connection au serveur Emprunt/Retour
				try (
				Socket client = new Socket("localhost", port)) {
					DataOutputStream out = new DataOutputStream(client.getOutputStream());
					String reponse = sc.next();
					out.writeUTF(reponse);
					if (reponse.compareTo("e") == 0) {
						int numAb = sc.nextInt();
						out.writeInt(numAb);
						String empruntdoc = sc.next();
						out.writeUTF(empruntdoc);
						//Recevoir le message de confirmatiion
						DataInputStream in = new DataInputStream(client.getInputStream());
						String msgconfirmation = in.readUTF();
						System.out.println(msgconfirmation);
					}
					else if (reponse.compareTo("r") == 0) {
						Integer returndoc = sc.nextInt();
						out.writeInt(returndoc);
						//Recevoir le message de confirmatiion
						DataInputStream in = new DataInputStream(client.getInputStream());
						String msgconfirmation = in.readUTF();
						System.out.println(msgconfirmation);
					}
					if (reponse.compareTo("q") == 0) {
						String rep = sc.next();
						out.writeUTF(rep);
					}
				}
			}
		}
		else if (port == 3000) {
			while (true) {
				// Connection au serveur Emprunt/Retour
				try (
				Socket reservation = new Socket("localhost", port)) {
					DataOutputStream out = new DataOutputStream(reservation.getOutputStream());
					String reponse = sc.next();
					out.writeUTF(reponse);
					if (reponse.compareTo("r") == 0) {
						int numAb = sc.nextInt();
						out.writeInt(numAb);
						String reservationdoc = sc.next();
						out.writeUTF(reservationdoc);
					}
					if (reponse.compareTo("q") == 0) {
						String rep = sc.next();
						out.writeUTF(rep);
					}
					//Recevoir le message de confirmatiion
					DataInputStream in = new DataInputStream(reservation.getInputStream());
					String msgconfirmation = in.readUTF();
					System.out.println(msgconfirmation);
				}					
			}
		}
		else {
			sc.close();
			throw new IllegalArgumentException("Port indisponible");
		}
	}
}
