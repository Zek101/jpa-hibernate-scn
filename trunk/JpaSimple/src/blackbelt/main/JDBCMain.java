package blackbelt.main;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCMain {
	public static void main(String[] args) {
		Connection con = null;
		 
		try{
		  // 1° Loading the driver
		  // The driver you use depends on your DB vendor
		  Class.forName("org.hsqldb.jdbcDriver");
		  // 2° Getting a Connection
		  // The parameters depend on your DB vendor
		  con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/","sa", "");
		  // 3° Creating and executing a statement
		  //Create a new PreparedStatement object to insert a new record in the PRODUCT table
		  PreparedStatement  stmt = con.prepareStatement("INSERT INTO PRODUCT(NAME, PRICE, PARTNBR, DESCR)VALUES(?,?,?,?)");
		
		  stmt.setString(1, "screen"); // sets the first parameter
		  stmt.setDouble(2, 100); // sets the first parameter
		  stmt.setString(3, "SCR-110"); // sets the first parameter
		  stmt.setString(4, "17'' screen"); // sets the first parameter
		  stmt.executeUpdate();
		    
		  //Reuse the statement object to insert 3 new records
		  stmt.executeUpdate();
		  stmt.executeUpdate();
		  stmt.executeUpdate();
		  
		  DatabaseMetaData dbdata = con.getMetaData();
		  System.out.println(dbdata.getDatabaseProductName() + " " + dbdata.getDatabaseProductVersion());
		  
		  con.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
		    // Whatever happens between try and finally, the
		    // finally block is guaranteed to be executed.
		    if (con != null) {
		        try {
		            con.close();
		        } catch (SQLException e) {
		            throw new RuntimeException(e);
		        }
		    }
		}
	
	}
	

}
