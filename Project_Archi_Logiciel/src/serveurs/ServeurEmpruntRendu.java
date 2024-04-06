package serveurs;

import java.io.IOException;
import java.net.*;

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
}
