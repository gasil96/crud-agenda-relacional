package crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import crud.entities.Comunica;
import crud.entities.TipoComunicaEnum;
import crud.jdbc.connection.DB;

public class ComunicaDAO {

	PreparedStatement st = null;
	Connection conn = null;

	public ComunicaDAO(Connection conn) {

		conn = DB.getConnection();

	}

	
	public ComunicaDAO() {
	}
	public void newReg(Comunica comunica) {
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"INSERT INTO agenda.comunica " + "(tipo, registro, id_contato) " + "VALUES " + "(?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, comunica.getTipo().toString());
			st.setString(2, comunica.getRegistro());
			st.setInt(3, comunica.getId_contato());
			st.executeUpdate();

			
		} catch (SQLException e) {

			e.printStackTrace();

		}

		finally {

			DB.closeStatement(st);
			// DB.closeConnection();

		}
	}
	
	
	public void remove(int id) {
		
		try {
			
			conn = DB.getConnection();
			st = conn.prepareStatement("DELETE FROM comunica WHERE id_comunica = ?");
			
			st.setInt(1, id);
			
			st.executeUpdate();
			
		} catch (SQLException e3) {
			e3.printStackTrace();
			
		}
		

	}

	public List<Comunica> listarTodasC() throws Exception {
		List<Comunica> comunica = new ArrayList<Comunica>();
		conn = DB.getConnection();
		st = conn.prepareStatement("select * from comunica", Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			Comunica cmc = new Comunica();
			cmc.setId_comunica(rs.getInt("id_comunica"));
			cmc.setTipo(TipoComunicaEnum.valueOf(rs.getString("Tipo")));
			cmc.setRegistro(rs.getString("Registro"));
			cmc.setId_contato(rs.getInt("id_contato"));
			comunica.add(cmc);
		}
		rs.close();
		// st.close();
		return comunica;

	}

}
