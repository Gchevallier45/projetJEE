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
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Types")
public class Type implements Serializable {
    
    @Id
    @Column(name = "Id")
    private int ID;

    @Column(name = "Type")    
    private String type;

    public Type(JsonObject jsonObject) throws ParseException {
        this.ID = jsonObject.getInt("id");
	this.type = jsonObject.getString("type");
    }
    
    public Type() {
        this.ID = 0;
	this.type = "defaultType";
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
     @Override
    public String toString() {
        return "Type{" +
                "ID:'" + this.getID()+ '\'' +
                ", type:" + this.getType()+
                '}';
    }
}
