/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.models;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OpeningHours")
public class OpeningHour implements Serializable {
    
    public OpeningHour(){
    }
    
    public OpeningHour(String sunOpen, String sunClose, String monOpen, String monClose, String tuesOpen, String tuesClose, String wedOpen, String wedClose, String thuOpen, String thuClose, String friOpen, String friClose, String satOpen, String satClose) {
        this.sunOpen = sunOpen;
        this.sunClose = sunClose;
        this.monOpen = monOpen;
        this.monClose = monClose;
        this.tuesOpen = tuesOpen;
        this.tuesClose = tuesClose;
        this.wedOpen = wedOpen;
        this.wedClose = wedClose;
        this.thuOpen = thuOpen;
        this.thuClose = thuClose;
        this.friOpen = friOpen;
        this.friClose = friClose;
        this.satOpen = satOpen;
        this.satClose = satClose;
    }
    
    @Id
    @Column(name = "Id")
    private int ID;
    
    @Column(name = "SunOpen")
    private String sunOpen;
    
    @Column(name = "SunClose")
    private String sunClose;
    
    @Column(name = "MonOpen")
    private String monOpen;
    
    @Column(name = "MonClose")
    private String monClose;
    
    @Column(name = "TuesOpen")
    private String tuesOpen;
    
    @Column(name = "TuesClose")
    private String tuesClose;
    
    @Column(name = "WedOpen")
    private String wedOpen;
    
    @Column(name = "WedClose")
    private String wedClose;
    
    @Column(name = "ThuOpen")
    private String thuOpen;
    
    @Column(name = "ThuClose")
    private String thuClose;
    
    @Column(name = "FriOpen")
    private String friOpen;
    
    @Column(name = "FriClose")
    private String friClose;
    
    @Column(name = "SatOpen")
    private String satOpen;
    
    @Column(name = "SatClose")
    private String satClose;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSunOpen() {
        return sunOpen;
    }

    public void setSunOpen(String sunOpen) {
        this.sunOpen = sunOpen;
    }

    public String getSunClose() {
        return sunClose;
    }

    public void setSunClose(String sunClose) {
        this.sunClose = sunClose;
    }

    public String getMonOpen() {
        return monOpen;
    }

    public void setMonOpen(String monOpen) {
        this.monOpen = monOpen;
    }

    public String getMonClose() {
        return monClose;
    }

    public void setMonClose(String monClose) {
        this.monClose = monClose;
    }

    public String getTuesOpen() {
        return tuesOpen;
    }

    public void setTuesOpen(String tuesOpen) {
        this.tuesOpen = tuesOpen;
    }

    public String getTuesClose() {
        return tuesClose;
    }

    public void setTuesClose(String tuesClose) {
        this.tuesClose = tuesClose;
    }

    public String getWedOpen() {
        return wedOpen;
    }

    public void setWedOpen(String wedOpen) {
        this.wedOpen = wedOpen;
    }

    public String getWedClose() {
        return wedClose;
    }

    public void setWedClose(String wedClose) {
        this.wedClose = wedClose;
    }

    public String getThuOpen() {
        return thuOpen;
    }

    public void setThuOpen(String thuOpen) {
        this.thuOpen = thuOpen;
    }

    public String getThuClose() {
        return thuClose;
    }

    public void setThuClose(String thuClose) {
        this.thuClose = thuClose;
    }

    public String getFriOpen() {
        return friOpen;
    }

    public void setFriOpen(String friOpen) {
        this.friOpen = friOpen;
    }

    public String getFriClose() {
        return friClose;
    }

    public void setFriClose(String friClose) {
        this.friClose = friClose;
    }

    public String getSatOpen() {
        return satOpen;
    }

    public void setSatOpen(String satOpen) {
        this.satOpen = satOpen;
    }

    public String getSatClose() {
        return satClose;
    }

    public void setSatClose(String satClose) {
        this.satClose = satClose;
    }
}
