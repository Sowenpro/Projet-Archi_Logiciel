package serveurs;

import java.sql.*;
import java.util.Scanner;
import abonnes.Abonne;
import documents.Document;

public class Appli {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Choisir un port entre 3000 ou 4000");
		int port = sc.nextInt();
		if (!(port == 3000 || port == 4000)) {
			sc.close();
			throw new IllegalArgumentException("Port indisponible");
		}
		else if (port == 4000) {
			while (true) {
				System.out.println("Voulez-vous emprunter ou retourner ? (e/r)");
				String reponse = sc.next();
				if (reponse.compareTo("e") == 0) {
					System.out.print( "Veuillez saisir votre numero d'abonne: " );
					int tempString1 = sc.nextInt();
					System.out.println("Quel document voulez-vous retourner ?");
					String tempString2 = sc.next();
					sc.close();
					//Document.emprunterPar(tempString1,tempString2);
					return;
				}
				else if (reponse.compareTo("r") == 0) {
					System.out.print( "Veuillez saisir le numero du document: " );
					String tempString = sc.nextLine();
					sc.close();
					//Document.retour(tempString);
					return;
				}
				while (true) {
					System.out.println("Commande inconnue : voulez-vous quitter l'application ? (y/n)");
					reponse = sc.next();
					if (reponse.compareTo("y") == 0) {
						sc.close();
						return;
					} else if (reponse.compareTo("n") == 0) {
						break;
					}
				}
			}
		}
		else if (port == 3000) {
			System.out.print( "Veuillez saisir votre numero d'abonne: " );
			int tempString1 = sc.nextInt();
			System.out.println("Quel document voulez-vous retourner ?");
			String tempString2 = sc.next();
			sc.close();
			//Document.reservationPour(tempString1,tempString2);
			return;
		}
	}
}
