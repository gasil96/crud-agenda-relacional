package crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import crud.entities.Comunica;
import crud.entities.Comunica.TIPO;
import crud.jdbc.connection.DB;

public class ComunicaDAO {

	PreparedStatement st = null;
	Connection conn = null;

	public ComunicaDAO(Connection conn) {

		conn = DB.getConnection();

	}

	public ComunicaDAO() {
	}

	public List<Comunica> listarTodasC() throws Exception {
		List<Comunica> comunica = new ArrayList<Comunica>();
		conn = DB.getConnection();
		st = conn.prepareStatement("select * from comunica", Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			Comunica cmc = new Comunica();
			cmc.setId_comunica(rs.getInt("id_comunica"));
			cmc.setTipo(TIPO.valueOf(rs.getString("Tipo")));
			cmc.setRegistro(rs.getString("Registro"));
			cmc.setId_contato(rs.getInt("id_contato"));
			comunica.add(cmc);
		}
		rs.close();
		// st.close();
		return comunica;

	}

}
