/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.models;
import java.time.LocalDate;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "UserAccounts")
public class UserAccount implements Serializable {
    
    @Id
    @Column(name = "Id")
    private int ID;
    
    @Column(name = "FirstName")   
    private String firstName;
    
    @Column(name = "LastName")      
    private String lastName;
    
    @Column(name = "Email")        
    private String email;
    
    @Column(name = "Password")     
    private String password;
    
    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "Active")
    private boolean active;
    
    @Column(name = "CreationDate")
    private LocalDate creationDate;

    @Column(name = "LastModificationDate")
    private LocalDate lastModificationDate;
    
    @Column(name = "ResetPasswordLink")
    private String resetPasswordLink;
    
    @Column(name = "ResetLinkValidateDate")
    private LocalDate resetLinkValidateDate;
    
    @Column(name = "IsRemoved")
    private boolean isRemoved;
    
    @ManyToOne
    private Type type;
    
    @ManyToOne
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
    public void setLastName(String lastName){
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

    public void setActive(boolean active){
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
