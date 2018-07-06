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
	  int input;	//user choice
	  
	  while(isRunning) {
		  try {
			  
			  System.out.println("---Client Menu---");
			  System.out.println("Press 1 to add Artist");
			  System.out.println("Press 2 to add Customer");
			  System.out.println("Press 3 to add Artwork");
			  System.out.println("Press 0 to exit");
			  System.out.println();
			  System.out.print("Client choice: ");
			  input = Integer.parseInt(in.nextLine());
		 
			  switch(input) {
			  	case 1:
			  		System.out.print("Enter artist name: ");
			  		String aname = in.nextLine();
			  		System.out.print("Enter artist birthplace: ");
			  		String birthplace = in.nextLine();
			  		System.out.print("Enter artist age: ");
			  		int age = Integer.parseInt(in.nextLine());
			  		System.out.print("Enter artist style: ");
			  		String style = in.nextLine();
			  		addArtist(con, aname, birthplace, age, style);
			  		System.out.println("\n");
			  		break;
			  	case 2:
			  		System.out.print("Enter customer name: ");
			  		String cname = in.nextLine();
			  		System.out.print("Enter customer address: ");
			  		String address = in.nextLine();
			  		System.out.print("Enter total amount of dollars spent (e.g. 130.25): ");
			  		double amount = Double.parseDouble(in.nextLine());
			  		addCustomer(con, cname, address, amount);
			  		System.out.println("\n");
			  		break;
			  	case 3:
			  		System.out.print("Enter art title: ");
			  		String title = in.nextLine();
			  		System.out.print("Enter art year: ");
			  		int year = Integer.parseInt(in.nextLine());
			  		System.out.print("Enter art type: ");
			  		String type = in.nextLine();
			  		System.out.print("Enter art price (e.g. 1500): ");
			  		double price = Double.parseDouble(in.nextLine());
			  		System.out.print("Enter artist: ");
			  		String artist = in.nextLine();
			  		addArtwork(con, title, year, type, price, artist);
			  		System.out.println("\n");
			  		break;
			  	case 0:
			  		isRunning = false;
			  		System.out.println("[...Program Terminated...]");
			  		break;
			  	default:
			  		System.out.println("Invalid input! Try again.\n\n");
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
  
  public static void addCustomer(Connection con, String name, String address, double amount) throws SQLException {
	  String sql = "INSERT INTO CUSTOMER(CName, Address, Amount) VALUES (?,?,?)";
	  PreparedStatement pstmt = con.prepareStatement(sql);
	  pstmt.setString(1, name);
	  pstmt.setString(2, address);
	  pstmt.setDouble(3, amount);
	  int result = pstmt.executeUpdate();
	  System.out.println(result + " record is inserted");
  }
  
  public static void addArtwork(Connection con, String title, int year, String type, double price, String AName) throws SQLException {
	  String sql = "INSERT INTO ARTWORK VALUES (?,?,?,?,?)";
	  PreparedStatement pstmt = con.prepareStatement(sql);
	  pstmt.setString(1, title);
	  pstmt.setInt(2, year);
	  pstmt.setString(3, type);
	  pstmt.setDouble(4, price);
	  pstmt.setString(5, AName);
	  int result = 0;
	  try {
		  result = pstmt.executeUpdate();
	  }
	  catch (Exception e) {
		  System.out.println(e);
		  System.out.println("Error! Please retry.");
		  return;
	  }
	  
	  System.out.println(result + " record is inserted");
	  String group = addGroup(con);
	  classify(con, title, group);
	  
  }
  
  public static String addGroup(Connection con) throws SQLException {
	  Scanner input = new Scanner(System.in);

	  System.out.print("Enter group name for this art: ");
	  String group_name = input.nextLine();
	  
	  //check whether group name exists
	  String sql = "select GName from ArtGroup where GName = ?";
	  PreparedStatement pstmt = con.prepareStatement(sql);
	  pstmt.setString(1, group_name);
	  ResultSet rs = pstmt.executeQuery();
	  //if no group name then add new group
	  if(!rs.next()) {
		  pstmt = con.prepareStatement("INSERT INTO ARTGROUP VALUES (?)");
		  pstmt.setString(1, group_name);
		  pstmt.executeUpdate();
		  System.out.println(group_name + " is added into ArtGroup");
	  }
	  
	  return group_name;
  }
  
  public static void classify(Connection con, String title, String group) throws SQLException {
	  String sql = "insert into Classify VALUES (?, ?)";
	  PreparedStatement pstmt = con.prepareStatement(sql);
	  pstmt.setString(1, title);
	  pstmt.setString(2, group);
	  pstmt.executeUpdate();
  }
}