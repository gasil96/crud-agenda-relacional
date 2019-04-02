package crud.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

import crud.entities.Comunica;
import crud.entities.Contato;
import crud.jdbc.connection.DB;

public class ComunicaDAO {
	
	PreparedStatement st = null;
	Connection conn = null;

	public ComunicaDAO(Connection conn) {

		conn = DB.getConnection();
		
	}

	public ComunicaDAO() {
		
	}
	
	public void create(Comunica comunicao) {

		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"INSERT INTO agenda.comunica " + "(email, cpf, idade, sexo) " + "VALUES " + "(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, comunicao.getEmail());
			st.setString(2, contato.getCpf());
			st.setInt(3, contato.getIdade());
			st.setString(4, contato.getSexo());
			st.execute();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		finally {

			DB.closeStatement(st);
			// DB.closeConnection();

		}
	}

	
	
	
	
	
}

