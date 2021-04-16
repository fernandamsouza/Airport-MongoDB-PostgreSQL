package conexaoBD;

import java.sql.*;

public class ExecutarQuery {
	
	public static boolean cadastra(StringBuilder query, Connection conection) {
		
		if (conection == null || query == null) {
			return false;
		}
		
		try {
			Statement st = conection.createStatement();
			ResultSet rs = st.executeQuery(query.toString());	
			rs.close();
			st.close();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static ResultSet busca(StringBuilder query, Connection conection) throws SQLException {
		
		try {
			
			Statement st = conection.createStatement();
			ResultSet rs = st.executeQuery(query.toString());
			return rs;
			
		} catch (SQLException e) {
			return null;
		}
	}

}