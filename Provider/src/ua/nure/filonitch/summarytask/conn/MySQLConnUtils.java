package ua.nure.filonitch.summarytask.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author D.Filonich
 *
 * MANAGEMENT OF MYSQL CONNECTION
 *
 */
public class MySQLConnUtils {
	private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/provider?user=root&password=1333&serverTimezone=UTC";

	public static Connection getMySQLConnection() throws SQLException {
		Properties connInfo = new Properties();

		connInfo.put("useUnicode", "true");
		connInfo.put("characterEncoding", "utf8");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection con = DriverManager.getConnection(CONNECTION_URL, connInfo);
		return con;
	}
}