package serveurs;

import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	System.out.println("Choisir un port entre 3000 ou 4000");
	static final int Port = sc.nextInt();
	if (Port!= 3000 || Port != 4000) {
		return IllegalArgumentException("Port indisponible");
	}
}
