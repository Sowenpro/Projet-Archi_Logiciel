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
					DataInputStream in = new DataInputStream(client.getInputStream());
					String phrase_serveur;
					String phrase_serveur_err;
					phrase_serveur = in.readUTF();
					System.out.println(phrase_serveur);
					String reponse = sc.next();
					out.writeUTF(reponse);
					if (reponse.compareTo("e") == 0) {
						phrase_serveur = in.readUTF();
						System.out.println(phrase_serveur);
						int numAb = sc.nextInt();
						out.writeInt(numAb);
						phrase_serveur = in.readUTF();
						System.out.println(phrase_serveur);
						String empruntdoc = sc.next();
						out.writeUTF(empruntdoc);
						//Recevoir le message de confirmatiion
						String msgconfirmation = in.readUTF();
						System.out.println(msgconfirmation);
					}
					else if (reponse.compareTo("r") == 0) {
						Integer returndoc = sc.nextInt();
						out.writeInt(returndoc);
						//Recevoir le message de confirmatiion
						String msgconfirmation = in.readUTF();
						System.out.println(msgconfirmation);
					}
					if (reponse.compareTo("q") == 0) {
						phrase_serveur = in.readUTF();
						System.out.println(phrase_serveur);
						String rep = sc.next();
						if(rep == "y" || rep =="n" ) {
							out.writeUTF(rep);
						}
						else {
							phrase_serveur_err = in.readUTF();
							System.out.println(phrase_serveur_err);
						}
					}
					phrase_serveur_err = in.readUTF();
					System.out.println(phrase_serveur_err);
				}
			}
		}
		else if (port == 3000) {
			while (true) {
				// Connection au serveur Emprunt/Retour
				try (
				Socket reservation = new Socket("localhost", port)) {
					DataOutputStream out = new DataOutputStream(reservation.getOutputStream());
					DataInputStream in = new DataInputStream(reservation.getInputStream());
					String phrase_serveur;
					String phrase_serveur_err;
					phrase_serveur = in.readUTF();
					System.out.println(phrase_serveur);
					String reponse = sc.next();
					out.writeUTF(reponse);
					if (reponse.compareTo("r") == 0) {
						phrase_serveur = in.readUTF();
						System.out.println(phrase_serveur);
						int numAb = sc.nextInt();
						out.writeInt(numAb);
						phrase_serveur = in.readUTF();
						System.out.println(phrase_serveur);
						String reservationdoc = sc.next();
						out.writeUTF(reservationdoc);
						//Recevoir le message de confirmatiion
						String msgconfirmation = in.readUTF();
						System.out.println(msgconfirmation);
					}
					if (reponse.compareTo("q") == 0) {
						phrase_serveur = in.readUTF();
						System.out.println(phrase_serveur);
						String rep = sc.next();
						if(rep == "y" || rep =="n" ) {
							out.writeUTF(rep);
						}
						else {
							phrase_serveur_err = in.readUTF();
							System.out.println(phrase_serveur_err);
						}
					}
					phrase_serveur_err = in.readUTF();
					System.out.println(phrase_serveur_err);
				}					
			}
		}
		else {
			sc.close();
			throw new IllegalArgumentException("Port indisponible");
		}
	}
}
