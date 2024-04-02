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
					System.out.println("Voulez-vous emprunter, retourner ou quitter ? (e/r/q)");
					String reponse = sc.next();
					out.writeUTF(reponse);
					if (reponse.compareTo("e") == 0) {
						System.out.println("Vous avez choisi d'emprunter");
						System.out.print("Veuillez saisir votre numero d'abonné: ");
						int numAb = sc.nextInt();
						out.writeInt(numAb);
						System.out.println("Veuillez saisir le numéro du document: ");
						String empruntdoc = sc.next();
						out.writeUTF(empruntdoc);
						sc.close();
						DataInputStream in = new DataInputStream(client.getInputStream());
						String msgconfirmation = in.readUTF();
						System.out.println(msgconfirmation);

					} 
					else if (reponse.compareTo("r") == 0) {
						DataOutputStream out1 = new DataOutputStream(client.getOutputStream());
						System.out.println("Vous avez choisi de retourner");
						System.out.print("Veuillez saisir le numéro du document: ");
						Integer returndoc = sc.nextInt();
						out1.writeInt(returndoc);
						sc.close();
						DataInputStream in = new DataInputStream(client.getInputStream());
						String msgconfirmation = in.readUTF();
						System.out.println(msgconfirmation);
					} 
					else if (reponse.compareTo("q") == 0) {
						System.out.println("Voulez-vous vraiment quitter l'application ? (y/n)");
						String rep = sc.next();
						rep = sc.next();
						if (rep.compareTo("y") == 0) {
							sc.close();

						} 
						else if (rep.compareTo("n") == 0) {
							break;
						}
					}
				}
				while (true) {
					System.out.println("Commande inconnue, veuillez réessayer.");
				}
			}
		}
		else if (port == 3000) {
			while (true) {
				// Connection au serveur Emprunt/Retour
				try (
				Socket reservation = new Socket("localhost", port)) {
					DataOutputStream out = new DataOutputStream(reservation.getOutputStream());
					System.out.println("Bienvenue dans le mode réservation, voulez-vous réserver ou quitter ? (r/q)");
					String reponse = sc.next();
					if (reponse.compareTo("e") == 0) {
						System.out.print("Veuillez saisir votre numéro d'abonné: ");
						int numAb = sc.nextInt();
						out.writeInt(numAb);
						System.out.print("Veuillez saisir le numéro du document: ");
						String reservationdoc = sc.next();
						out.writeUTF(reservationdoc);
						sc.close();
						DataInputStream in = new DataInputStream(reservation.getInputStream());
						String msgconfirmation = in.readUTF();
						System.out.println(msgconfirmation);
					}
					else if (reponse.compareTo("q") == 0) {
						System.out.println("Voulez-vous vraiment quitter l'application ? (y/n)");
						String rep = sc.next();
						rep = sc.next();
						if (rep.compareTo("y") == 0) {
							sc.close();

						} 
						else if (rep.compareTo("n") == 0) {
							break;
						}
					}
				}
				while (true) {
					System.out.println("Commande inconnue, veuillez réessayer.");
				}
			}
		} 
		else {
			sc.close();
			throw new IllegalArgumentException("Port indisponible");
		}
	}
}
