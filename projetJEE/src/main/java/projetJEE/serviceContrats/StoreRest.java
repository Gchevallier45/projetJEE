/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.serviceContrats;

import java.time.LocalDate;
import java.util.List;
import projetJEE.bl.concrete.UserAccountManager;
import projetJEE.models.UserAccount;
import javax.annotation.Resource;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import projetJEE.bl.concrete.*;
import projetJEE.models.*;

/**
 *
 * @author redti
 */
@RestController
@RequestMapping("/api/storerest")
public class StoreRest {

    private static final Logger logger = Logger.getLogger(StoreRest.class);
    
@Resource
    UserAccountManager uamanager;
@Resource
    StoreManager stmanager;
@Resource
    OpeningHourManager ophmanager;
@Resource
    AddressManager admanager;
@Resource
    CountryManager comanager;

    @GetMapping(value = "/getTest", produces = MediaType.APPLICATION_JSON)
    public String getJson() {
        return "Hello Store REST!";
    }

    @RequestMapping(value = "/putTest", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON)
    public String putJson(@RequestBody String content) {
        return content + " Data store have been saved!";
    }

    /*@GetMapping(value = "/getStoreInfo/{id}", produces = MediaType.APPLICATION_JSON)
    public String getStoreInfo(@PathVariable("id") String id) throws Exception {
        org.json.JSONObject obj = new  org.json.JSONObject();     
        try{
            Store shop = stmanager.getStoreById(Integer.parseInt(id));
             
            //OpeningHour
            List<OpeningHour> ListOpeningHours = ophmanager.getOpeningHourByStoreId(Integer.parseInt(id));
                       
            org.json.JSONObject objOpeningHours = new  org.json.JSONObject();
            org.json.JSONObject objMonday = new  org.json.JSONObject();
            org.json.JSONObject objTueday = new  org.json.JSONObject();
            org.json.JSONObject objWednesday = new  org.json.JSONObject();
            org.json.JSONObject objThursday = new  org.json.JSONObject();
            org.json.JSONObject objFriday = new  org.json.JSONObject();
            org.json.JSONObject objSaturday = new  org.json.JSONObject();
            org.json.JSONObject objSunday = new  org.json.JSONObject();
            for(OpeningHour day : ListOpeningHours){
                switch(day.getName()) {
                    case "Monday":
                    objMonday.put("From",day.getOpenHour());
                    objMonday.put("To",day.getCloseHour());
                    objMonday.put("24h",day.isIs24h());
                    objMonday.put("Close",day.isIsClosed());
                    objOpeningHours.put("Monday",objMonday);
                    break;
                    case "Tuesday":
                    objTueday.put("From",day.getOpenHour());
                    objTueday.put("To",day.getCloseHour());
                    objTueday.put("24h",day.isIs24h());
                    objTueday.put("Close",day.isIsClosed());
                    objOpeningHours.put("Tuesday",objTueday);    
                    break;
                    case "Wednesday":
                    objWednesday.put("From",day.getOpenHour());
                    objWednesday.put("To",day.getCloseHour());
                    objWednesday.put("24h",day.isIs24h());
                    objWednesday.put("Close",day.isIsClosed());
                    objOpeningHours.put("Wednesday",objWednesday);    
                    break;
                    case "Thursday":
                    objThursday.put("From",day.getOpenHour());
                    objThursday.put("To",day.getCloseHour());
                    objThursday.put("24h",day.isIs24h());
                    objThursday.put("Close",day.isIsClosed());
                    objOpeningHours.put("Thursday",objThursday);    
                    break;
                    case "Friday":
                    objFriday.put("From",day.getOpenHour());
                    objFriday.put("To",day.getCloseHour());
                    objFriday.put("24h",day.isIs24h());
                    objFriday.put("Close",day.isIsClosed());
                    objOpeningHours.put("Friday",objFriday);    
                    break;
                    case "Saturday":
                    objSaturday.put("From",day.getOpenHour());
                    objSaturday.put("To",day.getCloseHour());
                    objSaturday.put("24h",day.isIs24h());
                    objSaturday.put("Close",day.isIsClosed());
                    objOpeningHours.put("Saturday",objSaturday);    
                    break;
                    case "Sunday":
                    objSunday.put("From",day.getOpenHour());
                    objSunday.put("To",day.getCloseHour());
                    objSunday.put("24h",day.isIs24h());
                    objSunday.put("Close",day.isIsClosed());
                    objOpeningHours.put("Sunday",objSunday); 
                    break;
                    
                    default:
                }
            }
            
            logger.info(objOpeningHours.toString());
            
            //Construction de l'objet Country
            JSONObject objCountry = new JSONObject();
            objCountry.put("id",shop.getAddress().getCountry().getID());
            objCountry.put("country",shop.getAddress().getCountry().getCountry());

            //Construction de l'objet Address
            org.json.JSONObject objAddress = new  org.json.JSONObject();
            objAddress.put("id",shop.getAddress().getID());
            objAddress.put("street",shop.getAddress().getStreet());
            objAddress.put("city",shop.getAddress().getCity());
            objAddress.put("state",shop.getAddress().getState());
            objAddress.put("zipCode",shop.getAddress().getZipCode());
            objAddress.put("country",objCountry);
            
             //Construction de l'objet Store
            obj.put("ID",shop.getID());
            obj.put("Key",shop.getKeyValue());
            obj.put("OpeningHours",objOpeningHours);
            obj.put("Name",shop.getName());
            obj.put("PhoneNumber",shop.getPhoneNumber());
            obj.put("Email",shop.getEmail());
            obj.put("Lattitude",shop.getLatitude());
            obj.put("Longitude",shop.getLongitude());
            obj.put("lastModifiedDate",shop.getLastModifiedDate());
            obj.put("lastModifiedBy",shop.getLastModifiedBy());
            obj.put("adresse",objAddress);
            
        }catch(Exception e){
            throw new Exception("Cet ID ne correspond a aucun magasin dans la base de donnees");
        }        
        return obj.toString(2);
    }*/
}
