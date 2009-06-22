package blackbelt.main;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCMain {
	public static void main(String[] args) {
		try{
		 // 1° Loading the driver
		  // The driver you use depends on your DB vendor
		  Class.forName("org.hsqldb.jdbcDriver");
		  // 2° Getting a Connection
		  // The parameters depend on your DB vendor
		  Connection con = DriverManager.getConnection(
		"jdbc:hsqldb:hsql://localhost/","sa", "");
		  // 3° Creating and executing a statement
		  Statement stmt = con.createStatement();
		  ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT");
		  // 4° Processing results
		  while (rs.next()) {
		    System.out.println(rs.getString("ID") + " | " + rs.getString("NAME"));
		  }
		}catch(Exception e){ e.printStackTrace();}
	
	}
}
