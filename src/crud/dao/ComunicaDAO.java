package crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;



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
					"INSERT INTO agenda.comunica " + "(telefone_celular, telefone_comercial, telefone_residencial, email, id_contato) "
							+ "VALUES " + "(?, ?, ?, ?, ?,)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, comunicao.getTelefone_celular());
			st.setString(2, comunicao.getTelefone_comercial());
			st.setString(3, comunicao.getTelefone_residencial());
			st.setString(4, comunicao.getEmail());
			st.setInt(5, comunicao.getId_contato());			
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

