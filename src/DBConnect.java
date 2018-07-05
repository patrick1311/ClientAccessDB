/*
 * Statement interface
1) public ResultSet executeQuery(String sql): is used to execute SELECT query. It returns the object of ResultSet.
2) public int executeUpdate(String sql): is used to execute specified query, it may be create, drop, insert, update, delete etc.
3) public boolean execute(String sql): is used to execute queries that may return multiple results.
4) public int[] executeBatch(): is used to execute batch of commands.
 */

import java.io.*;
import java.sql.*;
import java.util.*;

public class DBConnect {

  public static void main(String[] args) throws ClassNotFoundException, SQLException {
	  
	  //Scanner for user input
	  Scanner in = new Scanner(System.in);
	  
	  
	  //Create connection to MySQL local server
	  Class.forName("com.mysql.jdbc.Driver");  
	  Connection con = DriverManager.getConnection(  
			  "jdbc:mysql://localhost:3306/ArtBase?useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false"
			  ,"root","");   
	  Statement stmt = con.createStatement();  
	  /*
	  ResultSet rs = stmt.executeQuery("select * from artgroup");  
	  while(rs.next())  
		  System.out.println(rs.getString(1));
	  
	  */
	  boolean isRunning = true;
	  int input;
	  
	  while(isRunning) {
		  System.out.println("Select your choice:");
		  System.out.println("Press 1 to add Artist");
		  System.out.println("Press 0 to exit");
		  input = in.nextInt();
		  
		  switch(input) {
		  	case 1:
		  		System.out.println("Enter artist name:");
		  		String name = in.next();
		  		System.out.println("Enter artist birthplace:");
		  		String birthplace = in.next();
		  		System.out.println("Enter artist age:");
		  		int age = in.nextInt();
		  		System.out.println("Enter artist style:");
		  		String style = in.next();
		  		addArtist(stmt, name, birthplace, age, style);
		  		break;
		  	case 0:
		  		isRunning = false;
		  		System.out.println("Program terminating...");
		  		break;
		  	default:
		  		System.out.println("Invalid input! Try again.");
		  		break;
			  
		  }
	  }
	  
	  
	  con.close();  
  }
  
  public static void addArtist(Statement st) throws SQLException {
	  String sql = "select * from artgroup";
	  ResultSet rs = st.executeQuery(sql);
	  if(rs.next())
	  while(rs.next())
		  System.out.println(rs.getString(1));
	  else System.out.print("NOTHING");
  }
}