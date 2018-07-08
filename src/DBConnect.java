
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
			  System.out.println("Press 4 to add Customer Interest");
			  System.out.println("Press 5 to update Artist style");
			  System.out.println("Press 6 to show Artist table");
			  System.out.println("Press 7 to show ArtWork table");
			  System.out.println("Press 8 to show ArtGroup table");
			  System.out.println("Press 9 to show Customer table");
			  System.out.println("Press 10 to show Classify entries");
			  System.out.println("Press 11 to show Like_Group entries");
			  System.out.println("Press 12 to show Like_Artist entries");
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
			  	case 4:
			  		System.out.print("Enter CustomerID: ");
			  		int CustID = Integer.parseInt(in.nextLine());
			  		System.out.print("Enter Customer favorite group: ");
			  		String favGroup = in.nextLine();
			  		updateCust(con, CustID, favGroup);
			  		System.out.println("\n");
			  		break;
			  	case 5:
			  		System.out.print("Enter Artist name: ");
			  		String name = in.nextLine();
			  		System.out.print("Enter new art style: ");
			  		String newStyle = in.nextLine();
			  		updateArtist(con, name, newStyle);
			  		System.out.println("\n");
			  		break;
			  	case 6:
			  		printTable(con, "ARTIST");
			  		System.out.println("\n");
			  		break;
			  	case 7:
			  		printTable(con, "ARTWORK");
			  		System.out.println("\n");
			  		break;
			  	case 8:
			  		printTable(con, "ARTGROUP");
			  		System.out.println("\n");
			  		break;
			  	case 9:
			  		printTable(con, "CUSTOMER");
			  		System.out.println("\n");
			  		break;
			  	case 10:
			  		printTable(con, "CLASSIFY");
			  		System.out.println("\n");
			  		break;
			  	case 11:
			  		printTable(con, "LIKE_GROUP");
			  		System.out.println("\n");
			  		break;
			  	case 12:
			  		printTable(con, "LIKE_ARTIST");
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
	  pstmt.executeUpdate();
	  System.out.println("Artist " + name + " is added into the gallery database.");
  }
  
  public static void addCustomer(Connection con, String name, String address, double amount) throws SQLException {
	  String sql = "INSERT INTO CUSTOMER(CName, Address, Amount) VALUES (?,?,?)";
	  PreparedStatement pstmt = con.prepareStatement(sql);
	  pstmt.setString(1, name);
	  pstmt.setString(2, address);
	  pstmt.setDouble(3, amount);
	  pstmt.executeUpdate();
	  System.out.println("A customer is added into the gallery database.");
  }
  
  public static void addArtwork(Connection con, String title, int year, String type, double price, String AName) throws SQLException {
	  String sql = "INSERT INTO ARTWORK VALUES (?,?,?,?,?)";
	  PreparedStatement pstmt = con.prepareStatement(sql);
	  pstmt.setString(1, title);
	  pstmt.setInt(2, year);
	  pstmt.setString(3, type);
	  pstmt.setDouble(4, price);
	  pstmt.setString(5, AName);
	  try {
		  pstmt.executeUpdate();
	  }
	  catch (Exception e) {
		  System.out.println(e);
		  System.out.println("Error! Please retry.");
		  return;
	  }
	  
	  System.out.println("'" + title + "' is added into the gallery.");
	  String group = addGroup(con);
	  classify(con, title, group);
	  
  }
  
  public static String addGroup(Connection con) throws SQLException {
	  Scanner input = new Scanner(System.in);

	  System.out.print("Enter group name for this art: ");
	  String group_name = input.nextLine();
	  
	  //check whether group name exists
	  String sql = "insert ignore into ArtGroup values (?)";
	  PreparedStatement pstmt = con.prepareStatement(sql);
	  pstmt.setString(1, group_name);
	  pstmt.executeUpdate();
	  System.out.println("Art Group updated!");
	  
	  return group_name;
  }
  
  public static void classify(Connection con, String title, String group) throws SQLException {
	  String sql = "insert into Classify VALUES (?, ?)";
	  PreparedStatement pstmt = con.prepareStatement(sql);
	  pstmt.setString(1, title);
	  pstmt.setString(2, group);
	  pstmt.executeUpdate();
  }
  
  public static void updateCust(Connection con, int CustID, String group) throws SQLException {
	  
	  //Add new record into Like_Group table
	  String sql = "insert into Like_Group values (?, ?)";
	  PreparedStatement pstmt = con.prepareStatement(sql);
	  pstmt.setInt(1, CustID);
	  pstmt.setString(2, group);
	  try {
		  pstmt.executeUpdate();
	  }
	  catch (Exception e) {
		  System.out.println(e);
		  System.out.println("Error! Please add Customer or ArtWork first.");
		  return;
	  }
	  
	  //query artist name based on ArtGroup
	  sql = "select artwork.AName from artwork " 
			  + "inner join classify on artwork.Title = classify.Title where GName = ?";
	  pstmt = con.prepareStatement(sql);
	  pstmt.setString(1, group);
	  ResultSet rs = pstmt.executeQuery();
	  //Insert all records that do not exist in Like_Artist table
	  while(rs.next()) {
		  sql = "insert ignore into LIKE_ARTIST values (?, ?)";
		  pstmt = con.prepareStatement(sql);
		  pstmt.setInt(1, CustID);
		  pstmt.setString(2, rs.getString(1));
		  pstmt.executeUpdate();
	  } 
	  System.out.println("Customer " + CustID + " liked the Art Group '" + group + "'");
  }
  
  public static void updateArtist(Connection con, String name, String style) throws SQLException {
	  //check whether artist is in DB
	  String sql = "select AName from Artist where AName = ?";
	  PreparedStatement pstmt = con.prepareStatement(sql);
	  pstmt.setString(1, name);
	  ResultSet rs = pstmt.executeQuery();
	  if(!rs.next()) {
		  System.out.println(name + " does not exist in gallery database.");
		  System.out.println("Please add the artist before performing this action!");
		  return;
	  }
	  
	  //Update artist style
	  sql = "update Artist set Style = ? where AName = ?";
	  pstmt = con.prepareStatement(sql);
	  pstmt.setString(1, style);
	  pstmt.setString(2, name);
	  pstmt.executeUpdate();
	  System.out.println(name + " style has changed to " + style);
  }
  
  public static void printTable(Connection con, String tableName) throws SQLException {
	  String sql = "select * from " + tableName;
	  PreparedStatement pstmt = con.prepareStatement(sql);
	  ResultSet rs = pstmt.executeQuery();
	  ResultSetMetaData rsmd = rs.getMetaData();
	  
	  int column = rsmd.getColumnCount();
	  int cellWidth = 20;	//column width
	  
	  //Print header row (column name)
	  printTableLine(column, cellWidth);
	  for(int i = 1; i <= column; i++) {
		  if(i == 1) System.out.print("|");
		  System.out.format("%-"+ cellWidth +"s|", rsmd.getColumnName(i));
	  }
	  System.out.println();
	  printTableLine(column, cellWidth);
	  
	  //print column row
	  while(rs.next()) {
		  for(int i = 1; i <= column; i++) {
			  if(i == 1) System.out.print("|");
			  System.out.format("%-"+cellWidth+"s|", rs.getString(i));
		  }
		  System.out.println();	//go to next row
	  }
	  printTableLine(column, cellWidth);
  }
  
  public static void printTableLine(int numberOfColumns, int cellWidth) {
	  for(int i = 1; i <= numberOfColumns; i++) {
		  if(i == 1) System.out.print("+");
		  int space = 0;
		  while(space < cellWidth) {
			  System.out.print("-");
			  space++;
		  }
		  System.out.print("+");
	  }
	  System.out.println();
  }
}