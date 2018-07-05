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

	  
	  boolean isRunning = true;
	  int input;
	  
	  while(isRunning) {
		  try {
			  
			  System.out.println("Select your choice:");
			  System.out.println("Press 1 to add Artist");
			  System.out.println("Press 0 to exit");
			  input = Integer.parseInt(in.nextLine());
		 
			  switch(input) {
			  	case 1:
			  		System.out.println("Enter artist name:");
			  		String name = in.nextLine();
			  		System.out.println("Enter artist birthplace:");
			  		String birthplace = in.nextLine();
			  		System.out.println("Enter artist age:");
			  		int age = Integer.parseInt(in.nextLine());
			  		System.out.println("Enter artist style:");
			  		String style = in.nextLine();
			  		addArtist(con, name, birthplace, age, style);
			  		System.out.println("\n");
			  		break;
			  	case 0:
			  		isRunning = false;
			  		System.out.println("[...Program Terminated...]");
			  		break;
			  	default:
			  		System.out.println("Invalid input! Try again.");
			  		break;
			  }
			  
		  }
		  catch(Exception e) {
			  System.out.println(e);
			  isRunning = false;
			  System.out.println("[...Program Terminated...]");
		  }
	  }
	  
	  in.close();	//close scanner
	  con.close();  //close connection
  }
  
  public static void addArtist(Connection con, String name, String birthplace, int age, String style) throws SQLException {
	  String sql = "INSERT INTO ARTIST VALUES (?,?,?,?)";
	  PreparedStatement pstmt = con.prepareStatement(sql);
	  pstmt.setString(1, name);
	  pstmt.setString(2, birthplace);
	  pstmt.setInt(3, age);
	  pstmt.setString(4, style);
	  int result = pstmt.executeUpdate();
	  System.out.println(result + " record is inserted");
  }
}