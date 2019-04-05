package crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import crud.entities.Contato;
import crud.jdbc.connection.DB;

public class ComunicaDAO {
	
	PreparedStatement st = null;
	Connection conn = null;

	public ComunicaDAO(Connection conn) {
		
		conn = DB.getConnection();
		
	}


}
