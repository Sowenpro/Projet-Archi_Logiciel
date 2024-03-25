package serveurs;

import java.util.Scanner;

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
	}
}
