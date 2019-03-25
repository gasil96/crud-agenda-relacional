package crud.aplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import crudo.jdbc.connection.DB;

public class Program {

	public static void main(String[] args) {

		
		//INSERÇÃO DADOS *BANCO MYSQL*
		Connection conn = null;
		PreparedStatement st = null;

		try {
			
			conn = DB.getConnection();
			st = conn.prepareStatement("INSERT INTO agenda.contato " // ' removido
					+ "(nome, cpf, idade, sexo) " // ' removido
					+ "VALUES " + "(?, ?, ?, ?)", // ; e ' removido
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, "Gabriel");
			st.setString(2, "021.429.212-64");
			st.setInt(3, 22);
			st.setString(4, "Masculino");

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();

				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id = " + id);
				
				}

			} else {
				System.out.println("No rown affected!");
			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

		finally {

			DB.closeStatement(st);
			DB.closeConnection();

		}
				
		//ATUALIZAR DADOS *BANCO MYSQL*
				
	}

}
