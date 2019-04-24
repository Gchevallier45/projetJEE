/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.models;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Promotions")
public class Promotion implements Serializable {
    
    public Promotion(String keyStr, String title, String shortDescription, String longDescription, int position, boolean disabled, LocalDate startDate, LocalDate endDate, String imageURL) {
        this.keyStr = keyStr;
        this.title = title;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.position = position;
        this.disabled = disabled;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageURL = imageURL;
    }
        
    public Promotion(){
        
    }   
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int ID;
    
    @Column(name = "KeyStr")
    private String keyStr;
    
    @Column(name = "Title")
    private String title;

    @Column(name = "ShortDescription")
    private String shortDescription;

    @Column(name = "LongDescription")
    private String longDescription;

    @Column(name = "Position")
    private int position;

    @Column(name = "Disabled")
    private boolean disabled;

    @Column(name = "StartDate")
    private LocalDate startDate;

    @Column(name = "EndDate")
    private LocalDate endDate;

    @Column(name = "ImageURL")
    private String imageURL;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getKeyStr() {
        return keyStr;
    }

    public void setKeyStr(String keyStr) {
        this.keyStr = keyStr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

}
