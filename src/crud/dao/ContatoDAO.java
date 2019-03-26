package crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import crud.entities.Contato;
import crud.jdbc.connection.DB;

public class ContatoDAO {

	PreparedStatement st = null;
	Connection conn = null; 
	
	public ContatoDAO(Connection conn) {
		conn = DB.getConnection();
	}

	public void cadastrar(Contato contato) {

		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"INSERT INTO agenda.contato " + "(nome, cpf, idade, sexo) " + "VALUES " + "(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, contato.getNome());
			st.setString(2, contato.getCpf());
			st.setInt(3, contato.getIdade());
			st.setString(4, contato.getSexo());
			st.execute();
			
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
	}
}
