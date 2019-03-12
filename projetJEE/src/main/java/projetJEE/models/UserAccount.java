/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.models;
import java.time.LocalDate;
/**
 *
 * @author redti
 */
public class UserAccount {
    private int ID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean active;
    private LocalDate creationDate;
    private LocalDate lastModificationDate;
    private String resetPasswordLink;
    private LocalDate resetLinkValidateDate;
    private boolean isRemoved;
    private Type type;
    private Address address;
    
     public int getID(){
        return this.ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
     public String getFirstName(){
        return this.firstName;
    }
    public void setFirstname(String firstName){
        this.firstName = firstName;
    }
     public String getLastName(){
        return this.lastName;
    }
    public void setStreet(String lastName){
        this.lastName = lastName;
    }
     public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
     public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
     public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
     public boolean getActive(){
        return this.active;
    }
    public void setStreet(boolean active){
        this.active = active;
    }
     public LocalDate getCreationDate(){
        return this.creationDate;
    }
    public void setCreationDate(LocalDate creationDate){
        this.creationDate = creationDate;
    }
     public LocalDate getLastModificationDate(){
        return this.lastModificationDate;
    }
    public void setLastModificationDate(LocalDate lastModificationDate){
        this.lastModificationDate = lastModificationDate;
    }
     public String getResetPasswordLink(){
        return this.resetPasswordLink;
    }
    public void setResetPasswordLink(String resetPasswordLink){
        this.resetPasswordLink = resetPasswordLink;
    }
     public LocalDate getResetLinkValidateDate(){
        return this.resetLinkValidateDate;
    }
    public void setResetLinkValidateDate(LocalDate resetLinkValidateDate){
        this.resetLinkValidateDate = resetLinkValidateDate;
    }
     public boolean getIsRemoved(){
        return this.isRemoved;
    }
    public void setIsRemoved(boolean isRemoved){
        this.isRemoved = isRemoved;
    }
     public Type getType(){
        return this.type;
    }
    public void setType(Type type){
        this.type = type;
    }
     public Address getAddress(){
        return this.address;
    }
    public void setAddress(Address address){
        this.address = address;
    }
}
