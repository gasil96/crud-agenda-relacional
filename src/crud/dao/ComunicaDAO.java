package crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crud.entities.Comunica;
import crud.entities.TipoComunicaEnum;
import crud.jdbc.connection.DB;

public class ComunicaDAO {

	public ComunicaDAO() {
	}

	public void newReg(Comunica comunica) {
		Connection conn = DB.getConnection();
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO agenda.comunica " + "(tipo, registro, id_contato) " + "VALUES " + "(?, ?, ?)");

			st.setString(1, comunica.getTipo().toString());
			st.setString(2, comunica.getRegistro());
			st.setInt(3, comunica.getId_contato());
			st.executeUpdate();
			System.out.println("teste do get dentro do dao new reg: " + comunica.getId_contato());
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
			// DB.closeConnection();

		}
	}

	public void remove(int id) {

		Connection conn = DB.getConnection();
		PreparedStatement st = null;
		try {

			st = conn.prepareStatement("DELETE FROM comunica WHERE id_comunica = ?");

			st.setInt(1, id);

			st.executeUpdate();

		} catch (SQLException e3) {
			e3.printStackTrace();

		}
	}

	// NAO CONSIGO FAZER ISSO FORA DA CLASSA `COMUNICADAO`
	public List<Comunica> listarTodasC(Comunica valor) throws Exception {
		Connection conn = DB.getConnection();
//		Comunica novaComunica = new Comunica();
		List<Comunica> comunica = new ArrayList<Comunica>();
		PreparedStatement st = conn.prepareStatement("select * from comunica where id_contato = ?");
		st.setInt(1, valor.getId_contato());// n consigo settar esse valor `3`
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			Comunica cmc1 = new Comunica();
			cmc1.setId_comunica(rs.getInt("id_comunica"));
			cmc1.setTipo(TipoComunicaEnum.valueOf(rs.getString("Tipo")));
			cmc1.setRegistro(rs.getString("Registro"));
			cmc1.setId_contato(rs.getInt("id_Contato"));
			comunica.add(cmc1);
		}

		rs.close();
		st.close();
		return comunica;

	}

}
