package serveurs;

import java.util.Scanner;

import abonnes.Abonne;
import documents.Document;

public class Appli {
	static int PORT;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Choisir un port entre 3000 ou 4000");
		PORT = sc.nextInt();
		sc.close();
		if (!(PORT == 3000 || PORT == 4000)) {
			throw new IllegalArgumentException("Port indisponible");
		}
		else if (PORT==4000) {
			System.out.println("Voulez-vous emprunter ou retourner ?");
			String reponse = sc.next();
			if (reponse=="emprunter") {
				System.out.print( "Veuillez saisir votre numero d'abonne: " );
				String tempString1 = sc.nextLine();
				System.out.print( "Veuillez saisir le numero du document: " );
				String tempString2 = sc.nextLine();
				sc.close();
				//Document.emprunterPar();
			}
			else if (reponse=="retourner") {
				System.out.print( "Veuillez saisir le numero du document: " );
				String tempString = sc.nextLine();
				sc.close();
				//Document.retour();
			}
		}
		else if (PORT==3000) {
			System.out.print( "Veuillez saisir votre numero d'abonne: " );
			String tempString1 = sc.nextLine();
			System.out.println("Quel document voulez-vous retourner ?");
			String tempString2 = sc.next();
			sc.close();
			//Document.reservationPour();
		}
	}
}
