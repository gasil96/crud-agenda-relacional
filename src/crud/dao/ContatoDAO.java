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
	
	public ContatoDAO() {
	}

	public void create(Contato contato) {
		Connection conn = DB.getConnection();
		PreparedStatement st = null;
		try {
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

			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
	}

	public void remove(int id, Connection conn) {

		try {

			PreparedStatement st = conn.prepareStatement("DELETE FROM contato WHERE id_contato = ?");

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

	public void update(Contato contato, Connection conn) {

		PreparedStatement st = null;
		try {
			
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

			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// DB.closeConnection();

		}
	}
	
	

	public List<Contato> listarTodos() throws Exception {
		
		Connection conn = DB.getConnection();
		
		List<Contato> contatos = new ArrayList<Contato>();
		PreparedStatement st = conn.prepareStatement("select * from contato");
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
		st.close();
		return contatos;

	}

	public Contato buscarPorId(int idSelecionado) throws Exception {
		
		Contato contato = null;
		PreparedStatement st = DB.getConnection().prepareStatement("select * from contato where id_contato = ?");
		st.setInt(1, idSelecionado);
		ResultSet retorno = st.executeQuery();
		
		while (retorno.next()) {
			contato = new Contato();
			contato.setId_contato(retorno.getInt("id_contato"));
			contato.setNome(retorno.getString("nome"));
			contato.setCpf(retorno.getString("cpf"));
			contato.setIdade(retorno.getInt("idade"));
			contato.setSexo(retorno.getString("sexo"));
		}
		retorno.close();
		return contato;

	}

	
	
}
