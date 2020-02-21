/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridge;
import Human.*;
import java.sql.*;
import java.math.BigInteger; 
import java.security.*; 
import Internal.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 *
 * @author prakh
 */
public class Helper
{
    final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    final static String DB_URL = "jdbc:mysql://localhost:3306/mysql";
    final static String USER = "root";
    final static String PASS = "admin";
    static int bookingID;
    public static void updateUserInfo(User u)
{
   Connection conn = null;
        Statement stmt = null;
        try{
           
            Class.forName(JDBC_DRIVER);
            
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            
            stmt = conn.createStatement();
            String sql="insert into shouvik.users(Name,Username,Email,DateOfBirth,Gender,Phone,Password,Address) values(\""+u.getName()+"\",\""+u.getUserName()+"\", \""+u.getEmail()+"\", '"+u.getDOB()+"' ,'"+u.getGender()+"',"+(u.getPhone()+"")+",\""+encryptThisString(u.getPassword())+"\",\""+u.getAddress()+"\")";
            
            int t=stmt.executeUpdate(sql);
            
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle JDBC Errors
            System.out.println(se.getMessage());
        }catch(Exception e){
            //Handle errors for Class.forname
            System.out.println(e.getMessage());
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
            System.out.println(se.getMessage());
        }//end finally try
    }//end try
            
            
   //query.getSt().executionUpdate(s);
}


 public static String encryptThisString(String s) 
{ 
        try { 
            // getInstance() method is called with algorithm SHA-1 
            MessageDigest md = MessageDigest.getInstance("SHA-1"); 
  
            // digest() method is called 
            // to calculate message digest of the input string 
            // returned as array of byte 
            byte[] messageDigest = md.digest(s.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
  
            // Add preceding 0s to make it 32 bit 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
  
            // return the HashText
            return hashtext; 
        } 
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    }
 

public static boolean checkUsername(User u)
{
    boolean flag=true;
    
    
        Connection conn = null;
        Statement stmt = null;
        try{
            //Register JDBC Driver
            Class.forName(JDBC_DRIVER);
            // Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //Execute query
           // System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT Username from shouvik.users";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){  
                //Retrieve by column
                String name = rs.getString("Username");
                if (u.getUserName().equals(name))
                {
                    flag=false;
                    break;
                }
               //Display values
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
return flag;
}
public static boolean checkPassword(User u)
{
    boolean flag=false;
    
    
        Connection conn = null;
        Statement stmt = null;
        try{
            //Register JDBC Driver
            Class.forName(JDBC_DRIVER);
            // Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //Execute query
           // System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT Password from shouvik.users where Username=\""+u.getUserName()+"\"";
            ResultSet rs = stmt.executeQuery(sql);
            
                rs.next();
                String name = rs.getString("Password");
                if (u.getPassword().equals(name))
                {
                    flag=true;
                    System.out.println("Password found");
                }
               //Display values
    
            
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
return flag;
}

public static void getData(User u)
{
    Connection conn = null;
        Statement stmt = null;
        try{
            //Register JDBC Driver
            Class.forName(JDBC_DRIVER);
            // Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //Execute query
            //System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * from shouvik.users where Username=\""+u.getUserName()+"\"";
            ResultSet rs = stmt.executeQuery(sql);
            
                rs.next();
                
        
        u.setName(rs.getString("Name"));
        u.setEmail(rs.getString("Email"));
        u.setAddress(rs.getString("Address"));
        u.setDOB(rs.getDate("DateOfBirth"));
        u.setPhone(rs.getLong("Phone"));
        u.setGender((rs.getString("Gender")).charAt(0));
               //Display values
    
            
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
}


public static boolean checkAvailability(Booking book,Hotel h)
{
       Timestamp checkIn = book.getCheckIn() ;
       Timestamp checkOut = book.getCheckOut() ;
       Timestamp currDate = checkIn ;
       while(currDate.after(new Timestamp(checkIn.getTime() - 86400000)) && currDate.before(checkOut))
       {
           Connection conn = null;
        Statement stmt = null;
        int roomsBooked=0 ;
        int totRooms=0 ;
        try{
            //Register JDBC Driver
            Class.forName(JDBC_DRIVER);
            // Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //Execute query
            //System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * from shouvik.bookings where HotelID=\""+h.getHotelId()+"\"";
            ResultSet rs = stmt.executeQuery(sql);
            
                while(rs.next())
                {
                    if(currDate.after(new Timestamp(rs.getDate("Checkin").getTime() - 86400000)) && currDate.before(rs.getDate("Checkout")))
                    {
                        roomsBooked=roomsBooked+rs.getInt("No_Rooms") ;
                    }
                }
                sql = "SELECT * from shouvik.hotels where HotelID=\""+h.getHotelId()+"\"";
             rs = stmt.executeQuery(sql);
             rs.next();
             totRooms = rs.getInt("NoRooms");
       
               
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
        currDate=new Timestamp(currDate.getTime()+86400000) ;
        if(book.getRooms()<=(totRooms-roomsBooked))
             {
                 continue;
             }
             else
             {
                return false ;
             }
        
       }
         
       return true ;
       
       
}     

       private static Long dayToMiliseconds(int days){
    Long result = Long.valueOf(days * 24 * 60 * 60 * 1000);
    return result;
}

public static Timestamp addDays(int days, Timestamp t1) throws Exception
{
    if(days < 0){
        throw new Exception("Day in wrong format.");
    }
    Long miliseconds = dayToMiliseconds(days);
    return new Timestamp(t1.getTime() + miliseconds);
    
}


public static ArrayList<Hotel> getHotels(Booking book)
{
    ArrayList<Hotel> ho=new ArrayList<>();
    int c=1;
    Hotel h = null;
    String loc = book.getLocation();
    Connection conn = null;
        Statement stmt = null;
        try{
            //Register JDBC Driver
            Class.forName(JDBC_DRIVER);
            // Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //Execute query
            //System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * from shouvik.hotels where Location=\""+loc+"\"";
            ResultSet rs = stmt.executeQuery(sql);
            
                while(rs.next())
                {   h=new Hotel();
                    h.setName(rs.getString("Hotel_Name"));
                    h.setPrice(rs.getInt("Price"));
                    h.setRating(rs.getFloat("Rating"));
                    h.setHotelId(rs.getString("HotelID"));
                    h.setAmenities(rs.getString("Amenities"));
                    h.setAvailable(checkAvailability(book , h));
                    ho.add(h);
                    
                }
            rs.close();
            stmt.close();
            conn.close();
            System.out.println("Closed");
        }catch(SQLException se){

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
    }
    return ho;
    
}
public static int generateBookingId()
{
    return ((int)(Math.random()*100000));
}


public static void addBooking(Booking book , User u , Hotel h , String Name , String aadhaar , String PAN )
{   
    Connection conn = null;
        Statement stmt = null;
        try{
           
            Class.forName(JDBC_DRIVER);
            
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            
            stmt = conn.createStatement();
            bookingID=generateBookingId();
            String sql="insert into shouvik.bookings(BookingID , Booking_Username , Booked_For , HotelID , No_Rooms , Checkin , Checkout , Aadhar , PAN , Amount) values(\""+bookingID+"\",\""+u.getUserName()+"\", \""+Name+"\", \""+h.getHotelId()+"\" ,"+book.getRooms()+",'"+(new SimpleDateFormat("yyyy-MM-dd").format(book.getCheckIn()))+"' , '"+(new SimpleDateFormat("yyyy-MM-dd").format(book.getCheckOut()))+"' ,"+aadhaar+",\""+PAN+"\","+book.getAmount()+")";                                                                   
            
            int t=stmt.executeUpdate(sql);
            
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle JDBC Errors
            System.out.println(se.getMessage());
        }catch(Exception e){
            //Handle errors for Class.forname
            System.out.println(e.getMessage());
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
            System.out.println(se.getMessage());
        }//end finally try
    }//end try
}

public static boolean checkAadhar(String a)
{   boolean flag=false ;
    if(a.length()==12&&a.charAt(0)!=0)
    {
        
        for(int i=0 ; i<a.length() ; i++)
        {
            flag = Character.isDigit(a.charAt(i)) ;
            if(!flag)
                break;
                 
        }
        return flag ;
    }
    return flag;
}

public static boolean checkPAN(String p)
{
    if(p.length()==10)
        return true;
    return false ;
}
public static int getBookingID()
{
    return bookingID;
}

public static int waiting(String HotelID)
{
    Connection conn = null;
        Statement stmt = null;
        int n=1 ;
        try{
            //Register JDBC Driver
            Class.forName(JDBC_DRIVER);
            // Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //Execute query
           // System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT count(*) as \"num\" from shouvik.wait_list where HotelID =\""+HotelID+"\"";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            n=rs.getInt("num");
            
            
            //Cleaning environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle JDBC Errors
            se.printStackTrace();
            System.out.println("E1");
        }catch(Exception e){
            //Handle errors for Class.forname
            e.printStackTrace();
            System.out.println("E2");
        }finally{
        //finally block used to close resources
        try{
            if(stmt!=null)
            stmt.close();
        }catch(SQLException se2){System.out.println("E3");
        }// nothing we can do
            try{
            if(conn!=null)
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
            System.out.println("E4");
        }//end finally try
    }//end try
        return n+1;
}
public static String enrolWaitList(User u , Hotel h , Booking book)
{
     Connection conn = null;
        Statement stmt = null;
        int wl=0;
        try{
           
            Class.forName(JDBC_DRIVER);
            
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            
            stmt = conn.createStatement();
            bookingID=generateBookingId();
            String waitID="W"+bookingID ;
            System.out.println("before wl");
            System.out.println(h.getHotelId());
            wl=waiting(h.getHotelId());
            System.out.println("wl passed");
            String sql="insert into shouvik.wait_list(WaitID , Username , HotelID , No_Rooms , Checkin , Checkout , WL, Hotel_name) values(\""+waitID+"\",\""+u.getUserName()+"\",\""+h.getHotelId()+"\" ,"+book.getRooms()+",'"+(new SimpleDateFormat("yyyy-MM-dd").format(book.getCheckIn()))+"' , '"+(new SimpleDateFormat("yyyy-MM-dd").format(book.getCheckOut()))+"' ,"+wl+",\""+h.getName()+"\")";                                                                   
            
            int t=stmt.executeUpdate(sql);
            
            stmt.close();
            conn.close();
            return (waitID+"and waiting list number "+wl);
        }catch(SQLException se){
            //Handle JDBC Errors
            System.out.println(se.getMessage());
            System.out.println("E5");
        }catch(Exception e){
            //Handle errors for Class.forname
            System.out.println(e.getMessage());
            System.out.println("E6");
        }finally{
        //finally block used to close resources
        try{
            if(stmt!=null)
            stmt.close();
        }catch(SQLException se2){
            System.out.println("E7");
        }// nothing we can do
            try{
            if(conn!=null)
            conn.close();
        }catch(SQLException se){
            System.out.println(se.getMessage());
            System.out.println("E8");
        }//end finally try
    }
return "";//end try
}
    public static int countDays(Timestamp start , Timestamp end) {
    
        int count=0 ;
        while(start.before(end))
       {
           count++ ;
           start=new Timestamp(start.getTime()+86400000) ;
       }
        return count ;
    }
public static int getTotPrice(Booking b , int price)
{
    Timestamp checkIn = b.getCheckIn() ;
       Timestamp checkOut = b.getCheckOut() ;
      
       int count=countDays(checkIn , checkOut) ;
       
       return (count*price)*b.getRooms() ;
}
public static ArrayList<Waitlists> getWaitList(User u)
{
    ArrayList<Waitlists> wa=new ArrayList<>();
    Waitlists w=null;
    String username = u.getUserName();
    Connection conn = null;
        Statement stmt = null;
        try{
            //Register JDBC Driver
            Class.forName(JDBC_DRIVER);
            // Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //Execute query
            //System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * from shouvik.wait_list where Username=\""+username+"\"";
            ResultSet rs = stmt.executeQuery(sql);
            
                while(rs.next())
                {   
                    w=new Waitlists();
                    w.setUsername(username);
                    w.setWaitID(rs.getString("WaitID"));
                    w.setCheckin(rs.getDate("Checkin"));
                    w.setCheckout(rs.getDate("Checkout"));
                    w.setNo_Rooms(rs.getInt("No_Rooms"));
                    w.setWl(rs.getInt("WL"));
                    w.setHotelName(rs.getString("Hotel_Name"));
                    wa.add(w);    
                }
            rs.close();
            stmt.close();
            conn.close();
            System.out.println("Closed");
        }catch(SQLException se){

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
    }
    return wa;
    
}

public static String getHotelName(String h)
{
    Connection conn = null;
        Statement stmt = null;
        String name="" ;
        try{
            //Register JDBC Driver
            Class.forName(JDBC_DRIVER);
            // Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //Execute query
            //System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * from shouvik.hotels where HotelID=\""+h+"\"";
            ResultSet rs = stmt.executeQuery(sql);
            
                rs.next();
                name = rs.getString("Hotel_Name");
        
        
               //Display values
    
            
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
        return name;
}

public static String getLocation(String h)
{
    Connection conn = null;
        Statement stmt = null;
        String loc="" ;
        try{
            //Register JDBC Driver
            Class.forName(JDBC_DRIVER);
            // Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //Execute query
            //System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * from shouvik.hotels where HotelID=\""+h+"\"";
            ResultSet rs = stmt.executeQuery(sql);
            
                rs.next();
                loc = rs.getString("Location");
        
        
               //Display values
    
            
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
        return loc;
}

public static int getPrice(String hid){
    Connection conn = null;
        Statement stmt = null;
        int price=0 ;
        try{
            //Register JDBC Driver
            Class.forName(JDBC_DRIVER);
            // Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //Execute query
            //System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * from shouvik.hotels where HotelID=\""+hid+"\"";
            ResultSet rs = stmt.executeQuery(sql);
            
                rs.next();
                price = rs.getInt("Price");
        
        
               //Display values
    
            
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
        return price;
    
}

public static void deleteWaitList(String boo)
{
    Connection conn = null;
        Statement stmt = null;
        try{
           
            Class.forName(JDBC_DRIVER);
            
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            
            stmt = conn.createStatement();
            bookingID=generateBookingId();
            String sql="Delete from shouvik.wait_list where WaitID=\""+boo+"\"";                                                                   
            
            int t=stmt.executeUpdate(sql);
            System.out.println("pass");
            
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle JDBC Errors
            System.out.println(se.getMessage());
        }catch(Exception e){
            //Handle errors for Class.forname
            System.out.println(e.getMessage());
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
            System.out.println(se.getMessage());
        }//end finally try
    }//end try
    }

public static ArrayList<CurrentBooking> generateBookingList(User u)
{
    ArrayList<CurrentBooking> cb=new ArrayList<>();
    CurrentBooking bo = null;
    String username=u.getUserName() ;
    Connection conn = null;
        Statement stmt = null;
        try{
            //Register JDBC Driver
            Class.forName(JDBC_DRIVER);
            // Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //Execute query
            //System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * from shouvik.bookings where Booking_Username=\""+username+"\"";
            ResultSet rs = stmt.executeQuery(sql);
            
                while(rs.next())
                {   bo=new CurrentBooking();
                    bo.setBookingID(rs.getString("BookingID"));
                    bo.setCheckIn(rs.getDate("Checkin"));
                    bo.setCheckOut(rs.getDate("Checkout"));
                    bo.setAmount(rs.getInt("Amount"));
                    bo.setHotelName(getHotelName(rs.getString("HotelID")));
                    bo.setLocation(getLocation(rs.getString("HotelID"))) ;
                    bo.setRooms(rs.getInt("No_Rooms"));
                    cb.add(bo);
                
                
                    
                    
                }
            rs.close();
            stmt.close();
            conn.close();
            System.out.println("Closed");
        }catch(SQLException se){

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
    }
    return cb;
}
public static void deleteBooking(String boo) {
        Connection conn = null;
        Statement stmt = null;
        try{
           
            Class.forName(JDBC_DRIVER);
            
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            
            stmt = conn.createStatement();
            bookingID=generateBookingId();
            String sql="Delete from shouvik.bookings where BookingID=\""+boo+"\"";                                                                   
            
            int t=stmt.executeUpdate(sql);
            System.out.println("pass");
            
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle JDBC Errors
            System.out.println(se.getMessage());
        }catch(Exception e){
            //Handle errors for Class.forname
            System.out.println(e.getMessage());
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
            System.out.println(se.getMessage());
        }//end finally try
    }//end try
    }

    public static void updateWaitPriority(String hname) {
        Connection conn = null;
        Statement stmt = null;
        Statement stmt1 = null;
        Statement stmt2 = null;
        Booking book=null;
        Hotel h=null;
        User u1=null ;
        try{
            //Register JDBC Driver
            Class.forName(JDBC_DRIVER);
            // Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //Execute query
            //System.out.println("Creating statement...");
            stmt = conn.createStatement();
            stmt1 = conn.createStatement();
            stmt2 = conn.createStatement();
            String sql , sql1;
            sql = "SELECT * from shouvik.wait_list where Hotel_Name=\""+hname+"\" order by WL";
            ResultSet rs = stmt.executeQuery(sql);
            ResultSet rs1 ;
                while(rs.next())
                {
                    sql1="select count(*) as \"num\" from shouvik.wait_list where Hotel_Name=\""+hname+"\" and WL<="+(rs.getInt("WL")) ;
                   rs1=stmt1.executeQuery(sql1);
                   rs1.next();
                   String sql2="update shouvik.wait_list set WL="+rs1.getInt("num")+" where WaitID=\""+rs.getString("WaitID")+"\"" ;
                   int t = stmt2.executeUpdate(sql2);
                }
                
                
        
       
               //Display values
    
            
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
    }
            
            public static void updateWaitList(String hname)
            {
                Connection conn = null;
        Statement stmt = null;
        Booking book=null;
        Hotel h=null;
        User u1=null ;
        try{
            //Register JDBC Driver
            Class.forName(JDBC_DRIVER);
            // Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //Execute query
            //System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * from shouvik.wait_list where Hotel_Name=\""+hname+"\" order by WL";
            ResultSet rs = stmt.executeQuery(sql);
            
                while(rs.next())
                {
                    book = new Booking();
                    h = new Hotel() ;
                    u1 = new User();
                    book.setRooms(rs.getInt("No_Rooms"));
                    book.setCheckIn(new Timestamp(rs.getDate("Checkin").getTime()));
                    book.setCheckOut(new Timestamp(rs.getDate("Checkout").getTime()));
                    h.setHotelId(rs.getString("HotelID"));
                    u1.setUserName(rs.getString("Username"));
                    book.setAmount(getTotPrice(book, getPrice(h.getHotelId())));
                    if(checkAvailability(book, h))
                    {
                        addBooking(book, u1, h, "", null, "");
                        deleteWaitList(rs.getString("WaitID"));
                    }
                   
                }
                updateWaitPriority(hname);
                
        
       
               //Display values
    
            
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
 }
          
            
public static String getHotelId(String bid){
     Connection conn = null;
        Statement stmt = null;
        String name="" ;
        try{
            //Register JDBC Driver
            Class.forName(JDBC_DRIVER);
            // Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //Execute query
            //System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * from shouvik.bookings where BookingID=\""+bid+"\"";
            ResultSet rs = stmt.executeQuery(sql);
            
                rs.next();
                name = rs.getString("HotelID");
        
        
               //Display values
    
            
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
        return name;
}



public static boolean modAvailable(Booking book , Hotel h , String Id){
    Timestamp checkIn = book.getCheckIn() ;
       Timestamp checkOut = book.getCheckOut() ;
       Timestamp currDate = checkIn ;
       while(currDate.after(new Timestamp(checkIn.getTime() - 86400000)) && currDate.before(checkOut))
       {
           Connection conn = null;
        Statement stmt = null;
        int roomsBooked=0 ;
        int totRooms=0 ;
        try{
            //Register JDBC Driver
            Class.forName(JDBC_DRIVER);
            // Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //Execute query
            //System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * from shouvik.bookings where HotelID=\""+h.getHotelId()+"\" and BookingID!=\""+Id+"\"";
            ResultSet rs = stmt.executeQuery(sql);
            
                while(rs.next())
                {
                    if(currDate.after(new Timestamp(rs.getDate("Checkin").getTime() - 86400000)) && currDate.before(rs.getDate("Checkout")))
                    {
                        roomsBooked=roomsBooked+rs.getInt("No_Rooms") ;
                    }
                }
                sql = "SELECT * from shouvik.hotels where HotelID=\""+h.getHotelId()+"\"";
             rs = stmt.executeQuery(sql);
             rs.next();
             totRooms = rs.getInt("NoRooms");
       
               
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
        currDate=new Timestamp(currDate.getTime()+86400000) ;
        if(book.getRooms()<=(totRooms-roomsBooked))
             {
                 continue;
             }
             else
             {
                return false ;
             }
        
       }
         
       return true ;
}
/*public static boolean checkCompleteInfo(User u)
{
     Connection conn = null;
        Statement stmt = null;
        String name="" ;
        try{
            //Register JDBC Driver
            Class.forName(JDBC_DRIVER);
            // Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //Execute query
            //System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM shouvik.bookings WHERE Booked_For + \"\" AND (Aadhar IS NULL OR PAN is NULL) AND Username=\" "+u.getUserName()+"\"";
            ResultSet rs = stmt.executeQuery(sql);
            
                while(rs.next())
                    return true;
        
        
               //Display values
    
            
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
        return false;
}*/

public static void modify(Booking book , String ID)
{
    Connection conn = null;
        Statement stmt = null;
        try{
           
            Class.forName(JDBC_DRIVER);
            
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            
            stmt = conn.createStatement();
            
            String sql="UPDATE shouvik.bookings SET Checkin = '"+(new SimpleDateFormat("yyyy-MM-dd").format(book.getCheckIn()))+"' , Checkout='"+(new SimpleDateFormat("yyyy-MM-dd").format(book.getCheckOut()))+"' WHERE BookingID=\""+ID+"\"" ;                                                                   
            
            int t=stmt.executeUpdate(sql);
            
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle JDBC Errors
            System.out.println(se.getMessage());
        }catch(Exception e){
            //Handle errors for Class.forname
            System.out.println(e.getMessage());
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
            System.out.println(se.getMessage());
        }//end finally try
    }//end try
}
    }

