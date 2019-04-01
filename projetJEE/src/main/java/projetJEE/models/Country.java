/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.models;

import java.io.Serializable;
import java.text.ParseException;
import javax.json.JsonObject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Countries")
public class Country implements Serializable {

    public Country(){
        
    }
    
    public Country(String country) {
        this.country = country;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int ID;

    @Column(name = "Country")    
    private String country;

    public Country(JsonObject jsonObject)throws ParseException {
        this.ID = jsonObject.getInt("id");
        this.country = jsonObject.getString("country");
    }
    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
     @Override
    public String toString() {
        return "Country{" +
                "id:'" + this.getID()+ '\'' +
                ", country:" + this.getCountry()+
                '}';
    }
}
