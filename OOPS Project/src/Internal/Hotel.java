/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Internal;

/**
 *
 * @author shouv
 */
public class Hotel {
    private String name;
    private boolean available;
    private double rating;
    private int price;
    private String HotelId;
    private String amenities;

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }
 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return available;
    }


    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    public String getHotelId() {
        return HotelId;
    }
public void setHotelId(String HotelId) {
        this.HotelId = HotelId ;
    }
    
    public double getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    
}
