import java.sql.*;
import javax.swing.*;

public class sqlscon{
	Connection conn=null;
	public static Connection dbConnector(){
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Assignment1Re;user=raman;password=bionicle");
			//System.out.println("Connection Successful!");
			//JOptionPane.showMessageDialog(null, "Connection Sucessful");
			return conn;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
	}
	}
