/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.serviceContrats;

import java.time.LocalDate;
import java.time.LocalTime;
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

    
    @GetMapping(value = "/getStoreInfo/{id}", produces = MediaType.APPLICATION_JSON)
    public String getStoreInfo(@PathVariable("id") String id) throws Exception {
        org.json.JSONObject obj = new  org.json.JSONObject();     
        try{
            Store shop = stmanager.getStoreById(Integer.parseInt(id));
             
            //OpeningHour
            org.json.JSONObject objMonday = shop.getJsonOpeningDayObject(shop.getOpeningHours().getMonOpen(), shop.getOpeningHours().getMonClose());
            org.json.JSONObject objTuesday = shop.getJsonOpeningDayObject(shop.getOpeningHours().getTuesOpen(), shop.getOpeningHours().getTuesClose());
            org.json.JSONObject objWednesday = shop.getJsonOpeningDayObject(shop.getOpeningHours().getWedOpen(), shop.getOpeningHours().getWedClose());
            org.json.JSONObject objThursday = shop.getJsonOpeningDayObject(shop.getOpeningHours().getThuOpen(), shop.getOpeningHours().getThuClose());
            org.json.JSONObject objFriday = shop.getJsonOpeningDayObject(shop.getOpeningHours().getFriOpen(), shop.getOpeningHours().getFriClose());
            org.json.JSONObject objSaturday = shop.getJsonOpeningDayObject(shop.getOpeningHours().getSatOpen(), shop.getOpeningHours().getSatClose());
            org.json.JSONObject objSunday = shop.getJsonOpeningDayObject(shop.getOpeningHours().getSunOpen(), shop.getOpeningHours().getSunClose());
            
            //Construction de l'objet OpeningHours
            org.json.JSONObject objOpeningHours = new  org.json.JSONObject();
            objOpeningHours.put("Sunday",objSunday);
            objOpeningHours.put("Monday",objMonday);
            objOpeningHours.put("Tuesday",objTuesday);
            objOpeningHours.put("Wednesday",objWednesday);
            objOpeningHours.put("Thursday",objThursday);
            objOpeningHours.put("Friday",objFriday);
            objOpeningHours.put("Saturday",objSaturday);
            
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
    }
}
