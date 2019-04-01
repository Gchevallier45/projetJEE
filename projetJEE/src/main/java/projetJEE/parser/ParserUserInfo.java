/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import projetJEE.models.*;

/**
 *
 * @author redti
 */
public class ParserUserInfo  extends DefaultHandler{
    
    private UserAccount user;
    private Type type;
    private Address address;
    private Country country;
    
   /*  @Override
    public void startDocument() {
		System.out.println("Debut du Document");
    }
    
     @Override
    public void endDocument() {
	System.out.println("Fin du Document");
    }
        
    @Override
	public void startElement(String namespace, String lName, String qName,
			Attributes attributes) {

		if(qName.equals("User")){
                    //Determiner id  du user
                    // newUser.setFirstname(attributes.getValue(id à determiner));
                    user.setFirstname(attributes.getValue("qName"));
                    user.setLastName(attributes.getValue("lName"));
                    user.setFirstname(attributes.getValue("email"));
                    user.setFirstname(attributes.getValue("password"));
                    user.setFirstname(attributes.getValue("phoneNumber"));
                    user.setFirstname(attributes.getValue("active"));
                    user.setFirstname(attributes.getValue("creationDate"));
                    user.setFirstname(attributes.getValue("lastModificationDate"));
                    user.setFirstname(attributes.getValue("resetPasswordLink"));
                    user.setFirstname(attributes.getValue("resetLinkValidateDate"));
                    user.setFirstname(attributes.getValue("isRemoved"));
		}
                
                if(qName.equals("Type")){
                        type.setID(Integer.parseInt(attributes.getValue("id")));
			type.setType(attributes.getValue("type"));
		}
		if(qName.equals("Adress")){
                        address.setID(Integer.parseInt(attributes.getValue("id")));
                        address.setStreet(attributes.getValue("street"));
                        address.setZipCode(attributes.getValue("zipCode"));
                        address.setCity(attributes.getValue("city"));
                        address.setState(attributes.getValue("state"));
		}
                if(qName.equals("Country")){
                        country.setID(Integer.parseInt(attributes.getValue("id")));
                        country.setCountry(attributes.getValue("country"));
		}
	}
        
        @Override
	public void endElement(String namespace, String lName, String qName) {

                if(qName.equals("Type")){
                    user.setType(type);
		}
                if(qName.equals("Adress")){
                    user.setAddress(address);
		}
                if(qName.equals("Country")){
                    address.setCountry(country);
		}
        }*/
    /**
     * Parse JSonFile to object model user to add a user account to the BD
     * @param file
     */
    public UserAccount parse(String file) throws ParseException {
		JsonReader reader = null;
                
		try {
			reader = Json.createReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonObject jsonObj = (JsonObject) reader.read();
		user = new UserAccount(jsonObj.getJsonObject("User"));
		
		System.out.println(user);
			   
		return user;
	}
}
