/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oops.project;
import java.sql.*;

/**
 *
 * @author shouv
 */
public class SQLTest {
    // JDBC Driver Name and Database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/mysql";
    //  Database credentials
    static final String USER = "root";
    static final String PASS = "admin";
    public static void main(String args[]){
        Connection conn = null;
        Statement stmt = null;
        try{
            //Register JDBC Driver
            Class.forName(JDBC_DRIVER);
            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //Execute query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT name, Checkin_Date, Checkout_Date from shouvik.users";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){  
                //Retrieve by column
                String name = rs.getString("Name");
                Date checkin = rs.getDate("Checkin_Date");
                Date checkout = rs.getDate("Checkout_Date");
                
                //Display values
                System.out.println(name + " has booked from: " + checkin + " to " + checkout);
            }
            //Cleaning environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle JDBC Errors
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forname
            e.printStackTrace();
        }finally{
        //finally block used to close resources
        try{
            if(stmt!=null)
            stmt.close();
        }catch(SQLException se2){
        }// nothing we can do
            try{
            if(conn!=null)
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }//end finally try
    }//end try
}//end main
}//end SQLTest
    
