package aplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc_connection.DB;

public class Program {

	public static void main(String[] args) {

		
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"INSERT INTO agenda.contato " // remover apostrofe
					+ "(nome, cpf, idade, sexo) " //remove apostrofe
					+ "VALUES "
					+ "(?, ?, ?, ?)"); //pont  virgula e apost sql apagado
			
			st.setString(1, "Gerusa");
			st.setString(2, "021.429.212-64");
			st.setInt(3, 39);
			st.setString(4, "Feminino");

			int rowsAffected = st.executeUpdate();
			System.out.println("Done! Rows Affected " + rowsAffected);

		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		finally {
		
			DB.closeStatement(st);
			DB.closeConnection();
			
		}
	}

}
