import java.sql.*;
public class ConnectVertica {
	
	public static Connection conn;
	public ConnectVertica(){}
	public static Connection Getconnection() throws ClassNotFoundException,SQLException
	{
			Class.forName("com.vertica.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:vertica://localhost:5433/VMart", "dbadmin","dbadmin");
			return conn;		
	}	
}