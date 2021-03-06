/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Stores")
public class Store implements Serializable {

    public Store(){
    }
    
    public Store(String keyValue, String name, String phoneNumber, String email, float latitude, float longitude, LocalDate lastModifiedDate, UserAccount lastModifiedBy, Address address, OpeningHour openingHours, UserAccount owner, boolean isClosed) {
        this.keyValue = keyValue;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
        this.lastModifiedDate = lastModifiedDate;
        this.lastModifiedBy = lastModifiedBy;
        this.address = address;
        this.openingHours = openingHours;
        this.owner = owner;
        this.isClosed = isClosed;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int ID;
    
    @Column(name = "KeyValue")
    private String keyValue;
    
    @Column(name = "Name")
    private String name;
    
    @Column(name = "PhoneNumber")
    private String phoneNumber;
    
    @Column(name = "Email")
    private String email;
    
    @Column(name = "Latitude")
    private float latitude;
    
    @Column(name = "Longitude")
    private float longitude;
    
    @Column(name = "LastModifiedDate")
    private LocalDate lastModifiedDate;
    
    @Column(name = "IsClosed")
    private boolean isClosed;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private UserAccount lastModifiedBy;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    
    @OneToOne(cascade = CascadeType.ALL)
    private OpeningHour openingHours;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private UserAccount owner;
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public LocalDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDate lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public UserAccount getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(UserAccount lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
        
    public OpeningHour getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHour openingHours) {
        this.openingHours = openingHours;
    }
    
    public UserAccount getOwner() {
        return owner;
    }

    public void setOwner(UserAccount owner) {
        this.owner = owner;
    }
    
    public boolean isIsClosed() {
        return isClosed;
    }

    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }
    
    public org.json.JSONObject getJsonOpeningDayObject(LocalTime openingHour, LocalTime closedHour) {
         org.json.JSONObject jsonObj = new  org.json.JSONObject();
         
         jsonObj.put("From",openingHour);
         jsonObj.put("To",closedHour);
         jsonObj.put("Close",this.getOpeningHours().isClosed(openingHour,closedHour));
         jsonObj.put("24H",this.getOpeningHours().isOpen24H(openingHour,closedHour));     
         
         return jsonObj;
    }
}
