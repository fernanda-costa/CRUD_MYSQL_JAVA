package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlSingleton {

	private static Connection conn;

	private MysqlSingleton() {

		carregarDriver();
		criarConexao();

	}

	public static synchronized Connection getConnection() {

		if (conn == null) {
			criarConexao();
		}

		return conn;

	}
	
	private static void criarConexao() {

		try {
			conn = DriverManager.getConnection(
					"", "", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	private void carregarDriver() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	
	



}
