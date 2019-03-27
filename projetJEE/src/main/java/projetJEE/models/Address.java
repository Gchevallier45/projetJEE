/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Addresses")
public class Address implements Serializable {
    
    public Address(){
        
    }
    
    public Address(String street, String city, String state, String zipCode, Country country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int ID;
    
    @Column(name = "Street")
    private String street;
    
    @Column(name = "City")
    private String city;
    
    @Column(name = "State")
    private String state;
    
    @Column(name = "ZipCode")
    private String zipCode;

    @ManyToOne
    private Country country;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getStreet(){
        return this.street;
    }
    public void setStreet(String street){
        this.street = street;
    }
    public String getCity(){
        return this.city;
    }
    public void setCity(String city){
        this.city = city;
    }
    public String getState(){
        return this.state;
    }
    public void setState(String state){
        this.state = state;
    }
    public String getZipCode(){
        return this.zipCode;
    }
    public void setZipCode(String zipCode){
        this.zipCode = zipCode;
    }
    public Country getCountry(){
        return this.country;
    }
    public void setCountry(Country country){
        this.country = country;
    }
}