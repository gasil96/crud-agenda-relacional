package crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import crud.entities.Contato;
import crud.jdbc.connection.DB;

public class ContatoDAO {
	
	PreparedStatement st = null;
	Connection conn = null;

	public ContatoDAO(Connection conn) {
		
		conn = DB.getConnection();
		
	}

	public ContatoDAO() {

	}

	public void create(Contato contato) {

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

		} catch (SQLException e) {

			e.printStackTrace();

		}

		finally {

			DB.closeStatement(st);
			//DB.closeConnection();

		}
	}

	public void remove(int id) {

		try {

			conn = DB.getConnection();
			st = conn.prepareStatement("DELETE FROM contato WHERE id_contato = ?");

			st.setInt(1, id);

			st.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		finally {

			 //DB.closeStatement(st);
			 //DB.closeConnection();

		}
	}

	public void update(Contato contato) {

		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"UPDATE contato SET nome = ?, cpf = ?, idade = ?, sexo = ? " + "WHERE id_contato = ?",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, contato.getNome());
			st.setString(2, contato.getCpf());
			st.setInt(3, contato.getIdade());
			st.setString(4, contato.getSexo());
			st.setInt(5, contato.getId_contato());

			st.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		finally {

			DB.closeStatement(st);
			// DB.closeConnection();

		}
	}
	
	

	public List<Contato> listarTodos() throws Exception {
		List<Contato> contatos = new ArrayList<Contato>();
		conn = DB.getConnection();
		st = conn.prepareStatement("select * from contato", Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			Contato contato = new Contato();
			contato.setId_contato(rs.getInt("id_contato"));
			contato.setNome(rs.getString("nome"));
			contato.setCpf(rs.getString("cpf"));
			contato.setIdade(rs.getInt("idade"));
			contato.setSexo(rs.getString("sexo"));
			contatos.add(contato);
		}
		rs.close();
		// st.close();
		return contatos;

	}

}
