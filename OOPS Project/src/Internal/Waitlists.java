/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Internal;

import java.sql.Date;

/**
 *
 * @author shouv
 */
public class Waitlists {
    private String WaitID;
    private String Username;
    private Date Checkin;
    private Date Checkout;
    private int No_Rooms;
    private int wl;

    public String getHotelName() {
        return HotelName;
    }

    public void setHotelName(String HotelName) {
        this.HotelName = HotelName;
    }
    private String HotelName;

    public String getWaitID() {
        return WaitID;
    }

    public void setWaitID(String WaitID) {
        this.WaitID = WaitID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public Date getCheckin() {
        return Checkin;
    }

    public void setCheckin(Date Checkin) {
        this.Checkin = Checkin;
    }

    public Date getCheckout() {
        return Checkout;
    }

    public void setCheckout(Date Checkout) {
        this.Checkout = Checkout;
    }

    public int getNo_Rooms() {
        return No_Rooms;
    }

    public void setNo_Rooms(int No_Rooms) {
        this.No_Rooms = No_Rooms;
    }

    public int getWl() {
        return wl;
    }

    public void setWl(int wl) {
        this.wl = wl;
    }
    
}
