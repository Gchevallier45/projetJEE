/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.controllers;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.*;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import projetJEE.bl.concrete.*;
import projetJEE.models.*;

@Controller public class StoreController {
    
    // store variables
    String storeName;
    String email;
    String phoneNumber;
    String street;
    String city;
    String zipCode;
    String state;
    String country;
    String latitude;
    String longitude;  
    
    // opening hours variables
    String[] days = { "Mon", "Tues", "Wed", "Thu", "Fri", "Sat", "Sun"};
    String[] daysLong = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    Map<String, String> hourliesFrom = new HashMap(); 
    Map<String, String> hourliesTo = new HashMap();
    Map<String, Boolean> isClosed = new HashMap();
    Map<String, Boolean> id24h = new HashMap();
    
    private static final Logger logger = Logger.getLogger(StoreController.class);
    @Resource
    UserAccountManager uamanager;
    @Resource
    AddressManager adrmanager;
    @Resource
    TypeManager typmanager;
    @Resource
    CountryManager countryManager;
    @Resource
    TypeManager typeManager;
    @Resource
    StoreManager storeManager;
    @Resource
    OpeningHourManager openingHourManager;
    
    
    @RequestMapping(value = "/AddStore", method = RequestMethod.GET)
    public String AddStore(ModelMap map, HttpServletRequest request) {
        request.setAttribute("activePage","AddStore");
        map.put("title", "Add Store");
        logger.info("Accès à la page addStore.");
        return "storeEdition";    
    }
    
    @RequestMapping(value = "/AddStore", method = RequestMethod.POST)
    public String AddStorePOST(HttpServletRequest request,HttpServletResponse response,HttpSession session,
          @RequestParam(value="name", required=false) String name, 
          @RequestParam(value="email", required=false) String email,
          ModelMap map) {
        request.setAttribute("activePage","AddStore");
        map.put("title", "Add Store");
        logger.info("Accès à la page addStore.");
        System.out.println("AddStore POST -> name:"+name);
        
        try {
        
            if(verificationStoreInformations(request)) {
                 // get country if exist
                 logger.info("Début");
                Country objtCountry = countryManager.getCountryByName(country);
                if(objtCountry == null) // so add country
                {
                    objtCountry = new Country(country);
                    countryManager.addCountry(objtCountry);
                }
                
                logger.info("Country");
                 // add a new adress
                Address address = new Address(street, city, state, zipCode, objtCountry);
                adrmanager.addAddress(address);
                logger.info("Address");
                LocalDate now = LocalDate.now();
                logger.info("storeName:"+storeName);
                logger.info("phoneNumber:"+phoneNumber);
                logger.info("email:"+email);
                logger.info("address:"+address);
                
                logger.info("latitude:"+latitude);
                logger.info("longitude:"+longitude);
                logger.info("latitude:"+Float.parseFloat(latitude));
                logger.info("longitude:"+Float.parseFloat(longitude));
                
                
                Store store = new Store("key_65464", storeName, phoneNumber, email, Float.parseFloat(latitude), Float.parseFloat(longitude), now, uamanager.getUserAccountById(1), address, null);
                storeManager.addStore(store);

                 logger.info("Store IIIIIIIIIIIDDDDDDDDDDDDDDD : "+store.getID());
                Map<String, OpeningHour> OpeningHours= new HashMap();
                /*
                OpeningHour openingHour1 = new OpeningHour("Monday", store, "10:10", "18:00", false, false);
                OpeningHour openingHour2 = new OpeningHour("Tuesday", store, "10:10", "18:00", false, false);
                OpeningHour openingHour3 = new OpeningHour("Wednesday", store, "10:10", "18:00", false, false);
                OpeningHour openingHour4 = new OpeningHour("Thursday", store, "10:10", "18:00", false, false);
                OpeningHour openingHour5 = new OpeningHour("Friday", store, "10:10", "18:00", false, false);
                OpeningHour openingHour6 = new OpeningHour("Saturday", store, "10:10", "18:00", false, false);
                OpeningHour openingHour7 = new OpeningHour("Sunday", store, "10:10", "18:00", false, false);
                
                openingHourManager.addOpeningHour(openingHour1);
                openingHourManager.addOpeningHour(openingHour2);
                openingHourManager.addOpeningHour(openingHour3);
                openingHourManager.addOpeningHour(openingHour4);
                openingHourManager.addOpeningHour(openingHour5);
                openingHourManager.addOpeningHour(openingHour6);
                openingHourManager.addOpeningHour(openingHour7);
                
                OpeningHours.put("Monday", openingHour1);
                OpeningHours.put("Tuesday", openingHour2);
                OpeningHours.put("Wednesday", openingHour3);
                OpeningHours.put("Thursday", openingHour4);
                OpeningHours.put("Friday", openingHour5);
                OpeningHours.put("Saturday", openingHour6);
                OpeningHours.put("Sunday", openingHour7);
                
                store.setOpeningHours(OpeningHours);
                storeManager.addStore(store);
*/
                /*
                 // opening hours
                 for(int indiceDay = 0; indiceDay < 7; indiceDay++) {
                     
                     OpeningHour openingHour = new OpeningHour(days[indiceDay], Store store, String openHour, String closeHour, boolean isClosed, boolean is24h)
                    request.setAttribute("from"+days[indiceDay], hourliesFrom.get(days[indiceDay]));
                    request.setAttribute("to"+days[indiceDay], hourliesTo.get(days[indiceDay]));
                    request.setAttribute("closed"+days[indiceDay], isClosed.get(days[indiceDay]).toString());
                    request.setAttribute("24hrs"+days[indiceDay], id24h.get(days[indiceDay]).toString());
                }
                
                 String name, Store store, String openHour, String closeHour, boolean isClosed, boolean is24h
                */
            }
            
        } catch(Exception e) {
                // store informations
                request.setAttribute("name", storeName);
                request.setAttribute("email", email);
                request.setAttribute("phoneNumber", phoneNumber);
                request.setAttribute("street", street);
                request.setAttribute("city", city);
                request.setAttribute("zipCode", zipCode);
                request.setAttribute("state", state);
                request.setAttribute("country", country);
                request.setAttribute("latitude", latitude);
                request.setAttribute("longitude", longitude);
                
                // opening hours
                 for(int indiceDay = 0; indiceDay < 7; indiceDay++) {
                    request.setAttribute("from"+days[indiceDay], hourliesFrom.get(days[indiceDay]));
                    request.setAttribute("to"+days[indiceDay], hourliesTo.get(days[indiceDay]));
                    request.setAttribute("closed"+days[indiceDay], isClosed.get(days[indiceDay]).toString());
                    request.setAttribute("24hrs"+days[indiceDay], id24h.get(days[indiceDay]).toString());
                }
                 
                
                request.setAttribute("erreur", "The store  has not been added. " + e.getMessage());
                logger.info("The store  has not been added. " + e.toString());
                return "storeEdition"; 
        }
        
        return "storeEdition";    
    }
    
    public boolean verificationStoreInformations(HttpServletRequest request) throws Exception {
        
       // store informations variables
        storeName = request.getParameter("name");
        email = request.getParameter("email");
        phoneNumber= request.getParameter("phoneNumber");
        street = request.getParameter("street");
        city = request.getParameter("city");
        zipCode= request.getParameter("zipCode");
        state = request.getParameter("state");
        country = request.getParameter("country");
        latitude = request.getParameter("latitude");
        longitude = request.getParameter("longitude");

        // opening hours
        for(int indiceDay = 0; indiceDay < 7; indiceDay++) {
            hourliesFrom.put(days[indiceDay], request.getParameter("from"+days[indiceDay]));
            hourliesTo.put(days[indiceDay], request.getParameter("to"+days[indiceDay]));
            isClosed.put(days[indiceDay], (request.getParameter("closed"+days[indiceDay]) != null && request.getParameter("closed"+days[indiceDay]).equals( "on" )) ? true : false );
            id24h.put(days[indiceDay], (request.getParameter("24hrs"+days[indiceDay]) != null &&  request.getParameter("24hrs"+days[indiceDay]).equals( "on" )) ? true : false );
            System.out.println("closed"+days[indiceDay] + ":"+request.getParameter("closed"+days[indiceDay]));
            System.out.println("24hrs"+days[indiceDay] + ":"+request.getParameter("24hrs"+days[indiceDay]));
        }
        
        System.out.println(hourliesTo.toString());
        System.out.println(isClosed.toString());
        System.out.println(id24h.toString());
        
        Verifications verif = new Verifications();
        
        // store verifications
        verif.storeNameVerification(storeName);
        verif.emailVerification(email);
        verif.phoneNumberVerification(phoneNumber);
        verif.streetVerification(street);
        verif.cityVerification(city);
        verif.zipCodeVerification(zipCode);
        verif.stateVerification(state);
        verif.countryVerification(country);
        
        logger.info("store verifications");
        // opening hours
        for(int indiceDay = 0; indiceDay < 7; indiceDay++) {
            verif.openingHourVerification(daysLong[indiceDay], hourliesFrom.get(days[indiceDay]), hourliesTo.get(days[indiceDay]), isClosed.get(days[indiceDay]), id24h.get(days[indiceDay]));
       }

        logger.info("opening hours verifications");
        
        return true;
    }
}
