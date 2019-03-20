package aplication;


import java.sql.Connection;

import jdbc_connection.DB;

public class Program {

	public static void main(String[] args) {

		Connection conn = DB.getConnection();
		DB.closeConnection();
		
	}

}
