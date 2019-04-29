/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.models;
import java.time.LocalDate;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.json.JsonObject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.apache.commons.codec.digest.DigestUtils;

@Entity
@Table(name = "UserAccounts")
public class UserAccount implements Serializable {
    
    public UserAccount() {
        /*this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.password = "";
        this.phoneNumber = "";
        this.active = false;
        this.creationDate = LocalDate.now();
        this.lastModificationDate = LocalDate.now();
        this.resetPasswordLink = "";
        this.resetLinkValidateDate = LocalDate.now();
        this.isRemoved = false;
        this.type = new Type();
        this.address = new Address();*/
    }
    
    public UserAccount(String firstName, String lastName, String email, String password, String phoneNumber, boolean active, LocalDate creationDate, LocalDate lastModificationDate, String resetPasswordLink, LocalDate resetLinkValidateDate, boolean isRemoved, String UUID, Type type, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = DigestUtils.sha256Hex(password);
        this.phoneNumber = phoneNumber;
        this.active = active;
        this.creationDate = creationDate;
        this.lastModificationDate = lastModificationDate;
        this.resetPasswordLink = resetPasswordLink;
        this.resetLinkValidateDate = resetLinkValidateDate;
        this.isRemoved = isRemoved;
        this.UUID = UUID;
        this.type = type;
        this.address = address;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    
    @Column(name = "UUID")
    private String UUID;
    
    @ManyToOne
    private Type type;
    
    @ManyToOne
    private Address address;
            
    public UserAccount(JsonObject root) throws ParseException {
        this.firstName = root.getString("firstName");
	this.lastName = root.getString("LastName");
        this.email = root.getString("email");
	this.password = root.getString("password");
        this.phoneNumber = root.getString("phoneNumber");
	this.active = root.getBoolean("active");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        String dateStr = root.getString("creationDate");
        Date strCreationDate = sdf.parse(dateStr);
        this.creationDate = strCreationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        String date2Str = root.getString("lastModificationDate");
        Date strCreationDate2 = sdf.parse(date2Str);
        this.lastModificationDate = strCreationDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        this.resetPasswordLink = root.getString("resetPasswordLink");
        
        String date3Str = root.getString("resetLinkValidateDate");
        Date strCreationDate3 = sdf.parse(date3Str);
        this.resetLinkValidateDate = strCreationDate3.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        this.isRemoved = root.getBoolean("isRemoved");
        
        if (root.getJsonObject("Type") != null) {
	    Type newtype = new Type((JsonObject)type);
            this.type = newtype;
        } else {
            this.type = null;
        }
        
        if (root.getJsonObject("Address") != null) {
	    Address newaddress = new Address((JsonObject)address);
            this.address = newaddress;
        } else {
            this.address = null;
        }
    }
    
    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }
    
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
        this.password = DigestUtils.sha256Hex(password);
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
    
     @Override
    public String toString() {
        return "UserAccount{" +
                "id='" + this.getID()+ '\'' +
                ", firstName:" + this.getFirstName() +
                ", lastName:" + this.getLastName()+
                ", email:" + this.getEmail() +
                ", password:" + this.getPassword() +
                ", phoneNumber:" + this.getPhoneNumber() +
                ", active:" + this.getActive() +
                ", CreationDate:" + this.getCreationDate() +
                ", lastModificationDate:" + this.getLastModificationDate() +
                ", resetPasswordLink:" + this.getResetPasswordLink() +
                ", resetLinkValidateDate:" + this.getResetLinkValidateDate() +
                ", isRemoved:" + this.getIsRemoved()+
                ", Type:" + this.getType().toString() +
                ", Address:" + this.getAddress().toString() +
                '}';
    }
}
