package aplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.DB;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement st = null;

		try {

			conn = DB.getConnection();

			st = conn.prepareStatement("INSERT INTO Contato" 
					+ "(nome, cpf, idade, sexo)" 
					+ "VALUES " 
					+ "(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, "Gabriel");
			st.setString(2, "021.429.212-64");
			st.setInt(3, 22);
			st.setString(4, "H");
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

}
