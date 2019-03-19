package jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class DB {

	private static Connection conn = null;

	// metodo pra conexao ao banco de dados
	public static Connection getConnection() {
		if (conn == null) {

			try {

				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
				JOptionPane.showMessageDialog(null, "Conexão com o Banco Realizada");

			} catch (SQLException e) {

				JOptionPane.showMessageDialog(null, "Erro ao conectar no banco");
				throw new DbException(e.getMessage());

			}
		}

		return conn;
	}

	// metodo para fechar a conexao
	public static void closeConnection() {

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {

				throw new DbException(e.getMessage());

			}
		}
	}

	// metodo para carregar propriedas do db.properties
	private static Properties loadProperties() {

		try (FileInputStream fs = new FileInputStream("db.properties")) {

			Properties props = new Properties();
			props.load(fs);
			return props;

		} catch (IOException e) {

			throw new DbException(e.getMessage());

		}
	}

	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();

			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();

			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
