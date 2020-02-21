/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author prakh
 */
package Human;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class User {
    private long customerID;
    private Date DOB;
    private String name;
    private String userName;
    private String password;
    private String email;
    private String address;
    private char gender;
    private long phone;

    

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public char getGender() {
        return gender;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public void setDOB(Date DOB) {
        this.DOB=DOB ;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCustomerID() {
        return customerID;
    }

    public String getDOB() {
        DateFormat oDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String szDate = oDateFormat.format(this.DOB);
        return szDate;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public long getPhone() {
        return phone;
    }
    
    public String getAddress() {
        return address;
    }
    
    
}
