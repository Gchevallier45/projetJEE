/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.serviceContrats;

import java.time.LocalDate;
import projetJEE.bl.concrete.UserAccountManager;
import projetJEE.models.UserAccount;
import javax.annotation.Resource;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
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
@RequestMapping("/api/promotionrest")
public class PromotionRest {
@Resource
    UserAccountManager uamanager;
/*@Resource
    PromotionManager promanager;*/
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
        return "Hello Promotion REST!";
    }

    @RequestMapping(value = "/putTest", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON)
    public String putJson(@RequestBody String content) {
        return content + " Data promotion have been saved!";
    }

 

    @GetMapping(value = "/getPromoInfo/{id}", produces = MediaType.APPLICATION_JSON)
    public String getpromoInfo(@PathVariable("id") String id) throws Exception {
          
        org.json.JSONObject obj = new  org.json.JSONObject();
        
        try{
            //Promotion promo = promanager.getPromoById(Integer.parseInt(id))
            /*Store shop = stmanager.getStoreById(Integer.parseInt(id));
            
            //Construction de l'objet Sunday
            org.json.JSONObject objSunday = new  org.json.JSONObject();
            objSunday.put("From",shop.getOpeningHour().getSunOpen());
            objSunday.put("To",shop.getOpeningHour().getSunClose());
            //Construction de l'objet Monday
            org.json.JSONObject objMonday = new  org.json.JSONObject();
            objMonday.put("From",shop.getOpeningHour().getMonOpen());
            objMonday.put("To",shop.getOpeningHour().getMonClose());
            //Construction de l'objet Tuesday
            org.json.JSONObject objTuesday = new  org.json.JSONObject();
            objTuesday.put("From",shop.getOpeningHour().getTuesOpen());
            objTuesday.put("To",shop.getOpeningHour().getTuesClose());
            //Construction de l'objet Wednesday
            org.json.JSONObject objWednesday = new  org.json.JSONObject();
            objWednesday.put("From",shop.getOpeningHour().getWedOpen());
            objWednesday.put("To",shop.getOpeningHour().getWedClose());
            //Construction de l'objet Thursday
            org.json.JSONObject objThursday = new  org.json.JSONObject();
            objThursday.put("From",shop.getOpeningHour().getThuOpen());
            objThursday.put("To",shop.getOpeningHour().getThuClose());
            //Construction de l'objet Friday
            org.json.JSONObject objFriday = new  org.json.JSONObject();
            objFriday.put("From",shop.getOpeningHour().getFriOpen());
            objFriday.put("To",shop.getOpeningHour().getFriClose());
            //Construction de l'objet Saturday
            org.json.JSONObject objSaturday = new  org.json.JSONObject();
            objSaturday.put("From",shop.getOpeningHour().getSatOpen());
            objSaturday.put("To",shop.getOpeningHour().getSatClose());
            
            
            //Construction de l'objet OpeningHours
            org.json.JSONObject objOpeningHours = new  org.json.JSONObject();
            objOpeningHours.put("Sunday",objSunday);
            objOpeningHours.put("Monday",objMonday);
            objOpeningHours.put("Tuesday",objTuesday);
            objOpeningHours.put("Wednesday",objWednesday);
            objOpeningHours.put("Thursday",objThursday);
            objOpeningHours.put("Friday",objFriday);
            objOpeningHours.put("Saturday",objSaturday);
            
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
            obj.put("adresse",objAddress);*/
            
        }catch(Exception e){
            throw new Exception("Cet ID ne correspond à aucune promotion dans la base de données");
        }        
        return obj.toString(2);
    }
    
    @GetMapping(value = "/getPromotions", produces = MediaType.APPLICATION_JSON)
    public String getPromotions() throws Exception {
          
        org.json.JSONObject obj = new  org.json.JSONObject();
        
        try{
            /*Store shop = stmanager.getStoreById(Integer.parseInt(id));
            
            //Construction de l'objet Sunday
            org.json.JSONObject objSunday = new  org.json.JSONObject();
            objSunday.put("From",shop.getOpeningHour().getSunOpen());
            objSunday.put("To",shop.getOpeningHour().getSunClose());
            //Construction de l'objet Monday
            org.json.JSONObject objMonday = new  org.json.JSONObject();
            objMonday.put("From",shop.getOpeningHour().getMonOpen());
            objMonday.put("To",shop.getOpeningHour().getMonClose());
            //Construction de l'objet Tuesday
            org.json.JSONObject objTuesday = new  org.json.JSONObject();
            objTuesday.put("From",shop.getOpeningHour().getTuesOpen());
            objTuesday.put("To",shop.getOpeningHour().getTuesClose());
            //Construction de l'objet Wednesday
            org.json.JSONObject objWednesday = new  org.json.JSONObject();
            objWednesday.put("From",shop.getOpeningHour().getWedOpen());
            objWednesday.put("To",shop.getOpeningHour().getWedClose());
            //Construction de l'objet Thursday
            org.json.JSONObject objThursday = new  org.json.JSONObject();
            objThursday.put("From",shop.getOpeningHour().getThuOpen());
            objThursday.put("To",shop.getOpeningHour().getThuClose());
            //Construction de l'objet Friday
            org.json.JSONObject objFriday = new  org.json.JSONObject();
            objFriday.put("From",shop.getOpeningHour().getFriOpen());
            objFriday.put("To",shop.getOpeningHour().getFriClose());
            //Construction de l'objet Saturday
            org.json.JSONObject objSaturday = new  org.json.JSONObject();
            objSaturday.put("From",shop.getOpeningHour().getSatOpen());
            objSaturday.put("To",shop.getOpeningHour().getSatClose());
            
            
            //Construction de l'objet OpeningHours
            org.json.JSONObject objOpeningHours = new  org.json.JSONObject();
            objOpeningHours.put("Sunday",objSunday);
            objOpeningHours.put("Monday",objMonday);
            objOpeningHours.put("Tuesday",objTuesday);
            objOpeningHours.put("Wednesday",objWednesday);
            objOpeningHours.put("Thursday",objThursday);
            objOpeningHours.put("Friday",objFriday);
            objOpeningHours.put("Saturday",objSaturday);
            
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
            obj.put("adresse",objAddress);*/
            
        }catch(Exception e){
            throw new Exception("Il n y a aucune promotions dans la base de données");
        }        
        return obj.toString(2);
    }
}
