import java.sql.*;
import java.util.Scanner;


public class ConnexionMySQL {
	static final String DB_URL = "jdbc:mysql://localhost:3306/Mediatheque?useSSL=false"; // MySQL
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String[] args) {
		try {
			//Class.forName("oracle.jdbc.OracleDriver"); // Oracle
			Class.forName("com.mysql.jdbc.Driver");  // MySQL
		} 
		catch (ClassNotFoundException e1) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e1.getMessage());
		}
		try {
			// Open connection
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("On est connecté au serveur sur la base Mediatheque") ;

			// Close connection
			conn.close();   
			System.out.println("Bye Bye") ;
		} 
		catch (SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
		} 
	}
}
