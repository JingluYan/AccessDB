/**
 * This Class is for accessing the database using the JDBC and mySQL 
 *
 * @author Jinglu Yan 18/03/2015
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseAcc {
    
    private Statement SQLStatement;
    private Connection cntDB;
    private String tableColumns;
    private String tableData;
    private FileReader read;
    //Need to modify based on users' information 
    private static String username = "root";
    private static String password = "";
    
    /**
     * Constructor
     * Access database by using user name and password
     */
    public DatabaseAcc(){
        try{
            //get the drivers for MySQL
            Class.forName("com.mysql.jdbc.Driver");
            
            //connect to the database
            cntDB = DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/Test", username, password);
            cntDB.setAutoCommit(true);
        
        }catch(SQLException e){
            System.out.println(e); 
        }        
        catch(ClassNotFoundException e){
            System.out.println(e);
        }
    }
    
    /**
     * Input order data into database 
     * 
     * @throws SQLException
     * @throws IOException 
     */    
    public void inputOrderData(String fileName) throws SQLException, IOException{
        
        int i = 1;
        read = new FileReader(fileName);
        
        while(i<read.array.size()){
            tableColumns=(String) read.array.get(0);
            tableData=(String) read.array.get(i);
            
            SQLStatement = cntDB.createStatement();
            SQLStatement.executeUpdate(String.format("INSERT INTO `order`("+ tableColumns + ")"
                                                        + "VALUES(" + tableData + ")"));
            i++;
            
        }    
    }
    
    /**
     * Input person data into database
     * 
     * @throws SQLException
     * @throws IOException 
     */
    public void inputPersonData(String fileName) throws SQLException, IOException{
        
        int i = 1;
        read = new FileReader(fileName);
        //Formatting to run the text as an SQL statement
        read.formatForPeople();
        
        while(i<read.array.size()){
            tableColumns=(String) read.array.get(0);
            tableData=(String) read.array.get(i);
                        
            SQLStatement = cntDB.createStatement();
            SQLStatement.executeUpdate(String.format("INSERT INTO person ("+ tableColumns + ")"
                                                + "VALUES(" + tableData + ")"));
            i++;
        }
    }
    
    /**
     * Query all the users which have an order with one or more orders
     */
    public void personsWithOneOrder() throws SQLException{
        ResultSet rs;
        SQLStatement = cntDB.createStatement();
            
        rs = SQLStatement.executeQuery(String.format(
                "SELECT p.first_name, p.last_name, p.person_id,o.person_id, o.order_no\n" +
                "FROM person p, `order` o \n" +
                "WHERE o.person_id=p.person_id \n" + 
                "GROUP BY p.first_name"
                ));
            
        System.out.println("User who have one order or more");
        System.out.println("First_Name \t| Last_Name \t\t| Order_No");
            
            //loop to get the information from the ResultSet above
        while(rs.next()){
            String fName =rs.getString("first_name");
            String lName =rs.getString("last_name");
            int orderNo = rs.getInt("order_no");
                
            System.out.printf("%s \t\t| %s \t\t| %d \n", fName, lName, orderNo);
                
        }
    }

    /**
     * Query all Orders with First Name of the corresponding person
     */
    public void ordersWithName(String fName) throws SQLException{
        ResultSet rs;
        SQLStatement = cntDB.createStatement();
            
        rs = SQLStatement.executeQuery(String.format(
                "SELECT o.person_id, o.order_no\n" +
                "FROM person p, `order` o \n" +
                "WHERE o.person_id  = p.person_id AND p.first_name = \"" + fName + "\""
                ));
            
        System.out.println("All Orders with First Name:" + fName);
        System.out.println("Person_ID \t| Order_No \t");
            
        while(rs.next()){
            int personID =rs.getInt("person_id");
            int orderNo = rs.getInt("order_no");
                
            System.out.printf("%d \t\t| %d \t\t\n", personID, orderNo); 
        }
    }
}