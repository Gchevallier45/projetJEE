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
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "OpeningHours")
public class OpeningHour implements Serializable {
    
    public OpeningHour(String name, Store store, String openHour, String closeHour, boolean isClosed, boolean is24h) {
        this.name = name;
        this.store = store;
        this.openHour = openHour;
        this.closeHour = closeHour;
        this.isClosed = isClosed;
        this.is24h = is24h;
    }
    
    public OpeningHour(){
    }
    
    @Id
    @Column(name = "Name")
    private String name;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Store store;
    
    @Column(name = "OpenHour")
    private String openHour;

    @Column(name = "CloseHour")
    private String closeHour;
    
    @Column(name = "IsClosed")
    private boolean isClosed;
    
    @Column(name = "Is24h")
    private boolean is24h;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getOpenHour() {
        return openHour;
    }

    public void setOpenHour(String openHour) {
        this.openHour = openHour;
    }

    public String getCloseHour() {
        return closeHour;
    }

    public void setCloseHour(String closeHour) {
        this.closeHour = closeHour;
    }

    public boolean isIsClosed() {
        return isClosed;
    }

    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public boolean isIs24h() {
        return is24h;
    }

    public void setIs24h(boolean is24h) {
        this.is24h = is24h;
    }
}
