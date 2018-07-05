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
	  
	  add(stmt);
	  con.close();  
  }
  
  public static void add(Statement st) throws SQLException {
	  String sql = "select * from artgroup";
	  ResultSet rs = st.executeQuery(sql);
	  if(rs.next())
	  while(rs.next())
		  System.out.println(rs.getString(1));
	  else System.out.print("NOTHING");
  }
}