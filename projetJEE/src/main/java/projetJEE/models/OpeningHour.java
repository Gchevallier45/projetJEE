/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.models;


import java.io.Serializable;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OpeningHours")
public class OpeningHour implements Serializable {

    public OpeningHour(){
    }

    public OpeningHour(LocalTime sunOpen, LocalTime sunClose, LocalTime monOpen, LocalTime monClose, LocalTime tuesOpen, LocalTime tuesClose, LocalTime wedOpen, LocalTime wedClose, LocalTime thuOpen, LocalTime thuClose, LocalTime friOpen, LocalTime friClose, LocalTime satOpen, LocalTime satClose) {
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int ID;

    @Column(name = "SunOpen")
    private LocalTime sunOpen;

    @Column(name = "SunClose")
    private LocalTime sunClose;

    @Column(name = "MonOpen")
    private LocalTime monOpen;

    @Column(name = "MonClose")
    private LocalTime monClose;

    @Column(name = "TuesOpen")
    private LocalTime tuesOpen;

    @Column(name = "TuesClose")
    private LocalTime tuesClose;

    @Column(name = "WedOpen")
    private LocalTime wedOpen;

    @Column(name = "WedClose")
    private LocalTime wedClose;

    @Column(name = "ThuOpen")
    private LocalTime thuOpen;

    @Column(name = "ThuClose")
    private LocalTime thuClose;

    @Column(name = "FriOpen")
    private LocalTime friOpen;

    @Column(name = "FriClose")
    private LocalTime friClose;

    @Column(name = "SatOpen")
    private LocalTime satOpen;

    @Column(name = "SatClose")
    private LocalTime satClose;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public LocalTime getSunOpen() {
        return sunOpen;
    }

    public void setSunOpen(LocalTime sunOpen) {
        this.sunOpen = sunOpen;
    }

    public LocalTime getSunClose() {
        return sunClose;
    }

    public void setSunClose(LocalTime sunClose) {
        this.sunClose = sunClose;
    }

    public LocalTime getMonOpen() {
        return monOpen;
    }

    public void setMonOpen(LocalTime monOpen) {
        this.monOpen = monOpen;
    }

    public LocalTime getMonClose() {
        return monClose;
    }

    public void setMonClose(LocalTime monClose) {
        this.monClose = monClose;
    }

    public LocalTime getTuesOpen() {
        return tuesOpen;
    }

    public void setTuesOpen(LocalTime tuesOpen) {
        this.tuesOpen = tuesOpen;
    }

    public LocalTime getTuesClose() {
        return tuesClose;
    }

    public void setTuesClose(LocalTime tuesClose) {
        this.tuesClose = tuesClose;
    }

    public LocalTime getWedOpen() {
        return wedOpen;
    }

    public void setWedOpen(LocalTime wedOpen) {
        this.wedOpen = wedOpen;
    }

    public LocalTime getWedClose() {
        return wedClose;
    }

    public void setWedClose(LocalTime wedClose) {
        this.wedClose = wedClose;
    }

    public LocalTime getThuOpen() {
        return thuOpen;
    }

    public void setThuOpen(LocalTime thuOpen) {
        this.thuOpen = thuOpen;
    }

    public LocalTime getThuClose() {
        return thuClose;
    }

    public void setThuClose(LocalTime thuClose) {
        this.thuClose = thuClose;
    }

    public LocalTime getFriOpen() {
        return friOpen;
    }

    public void setFriOpen(LocalTime friOpen) {
        this.friOpen = friOpen;
    }

    public LocalTime getFriClose() {
        return friClose;
    }

    public void setFriClose(LocalTime friClose) {
        this.friClose = friClose;
    }

    public LocalTime getSatOpen() {
        return satOpen;
    }

    public void setSatOpen(LocalTime satOpen) {
        this.satOpen = satOpen;
    }

    public LocalTime getSatClose() {
        return satClose;
    }

    public void setSatClose(LocalTime satClose) {
        this.satClose = satClose;
    }
    
    public boolean isClosed(LocalTime OpenHour, LocalTime CloseHour){
        boolean closed = false;
        
        LocalTime midnight = LocalTime.MIDNIGHT;
        
        if(OpenHour.equals(midnight) && CloseHour.equals(midnight)){
            closed = true;
        }
        
        return closed;
    }
    
    public boolean isOpen24H(LocalTime OpenHour, LocalTime CloseHour){
        boolean Opened24H = false;
        
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalTime closedHour = LocalTime.parse("23:59:59");
        
        if(OpenHour.equals(midnight) && CloseHour.equals(closedHour)){
            Opened24H = true;
        }
        
        return Opened24H;
    }
}