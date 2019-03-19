package aplication;

import java.sql.Connection;
import java.util.Locale;
import java.util.Scanner;

import jdbc.DB;


public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		//teste conexao bd
		Connection conn = DB.getConnection();
		DB.closeConnection();
		
		
		sc.close();
	}

}
