package conexaoBD;

import java.sql.*;

public class ConectarBanco {
	
	public static Connection getConection(String servidor, String porta, String senha) throws SQLException{
		StringBuilder url = new StringBuilder();
		url.append("jdbc:postgresql://")
			.append(servidor)
			.append(':')
			.append(porta)
			.append('/')
			.append("BAN2_FINAL?user=postgres&password=")
			.append(senha);
		return DriverManager.getConnection(url.toString());
	}
}