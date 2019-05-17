/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    String idStore;
    
    // opening hours variables
    String[] days = { "Mon", "Tues", "Wed", "Thu", "Fri", "Sat", "Sun"};
    String[] daysLong = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    DateTimeFormatter FOMATTER_HOUR = DateTimeFormatter.ofPattern("HH:mm");
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
    
    @RequestMapping(value = "/Stores", method = RequestMethod.GET)
    public String Stores(ModelMap map, HttpServletRequest request) {
        logger.info("Entry in Stores");
        request.setAttribute("activePage","Stores");

        List<Store> storesLis = storeManager.getAll();
        request.setAttribute("stores", storesLis);
        
        logger.info("Exit AddStore");
        return "stores";    
    }
    
    @RequestMapping(value = "/AddStore", method = RequestMethod.GET)
    public String AddStore(ModelMap map, HttpServletRequest request, HttpSession session) {
        logger.info("Entry in AddStore");
        try {
            if(session.getAttribute("userStatus") == null || !session.getAttribute("userStatus").equals("Owner"))
                throw new Exception("You are not allowed to access this page. You must log in as a Owner.");
            
            request.setAttribute("activePage","AddStore");
            map.put("title", "Add Store");
            map.put("actionForm", "AddStore");
            logger.info("Exit AddStore");
            return "storeEdition";
        } catch(Exception e) {
                request.setAttribute("erreur", e.getMessage());
                logger.info("The store  has not been added. " + e.toString());
                return "index"; 
        }
    }
    
     @RequestMapping(value = "/DeleteStore", method = RequestMethod.GET)
    public String DeleteStore(ModelMap map, HttpServletRequest request, HttpSession session,
            @RequestParam(value="storeId", required=false) int storeId) {
        logger.info("Entry in DeleteStore");
        try {
            if(session.getAttribute("userStatus") == null || !session.getAttribute("userStatus").equals("Owner"))
                throw new Exception("You are not allowed to access this page. You must log in as a Owner.");
            
            Store store = null;
            try {
                 store = storeManager.getStoreById(storeId);
            } catch(Exception e) {
                throw new Exception("The store with id '" + storeId + "' not exist.");
            }
            
            if(session.getAttribute("userId") == null || ((int) session.getAttribute("userId")) != store.getOwner().getID())
                throw new Exception("You are not the owner of this store.");
            
            storeManager.removeStore(store);
            
            request.setAttribute("success", "The store '"+store.getName()+"' has been removed.");
            request.setAttribute("activePage","Stores");
            logger.info("Exit DeleteStore");
            return "stores";
        } catch(Exception e) {
                request.setAttribute("erreur", e.getMessage());
                logger.info("The store  has not been deleted. " + e.toString());
                return "index"; 
        }
    }
    
    @RequestMapping(value = "/UpdateStore", method = RequestMethod.GET)
    public String UpdateStore(ModelMap map, HttpServletRequest request,HttpSession session,
            @RequestParam(value="storeId", required=false) int storeId) {
        logger.info("Entry in UpdateStore");
        try {
            if(session.getAttribute("userStatus") == null || !session.getAttribute("userStatus").equals("Owner"))
                throw new Exception("You are not allowed to access this page. You must log in as a Owner.");
            
            request.setAttribute("activePage","UpdateStore");
            // get store id
            Store store = null;
            try {
                 store = storeManager.getStoreById(storeId);
            } catch(Exception e) {
                throw new Exception("The store with id '" + storeId + "' not exist.");
            }
            
            if(session.getAttribute("userId") == null || ((int) session.getAttribute("userId")) != store.getOwner().getID())
                throw new Exception("You are not the owner of this store.");
            
            // add parameters
            // store informations
            request.setAttribute("name", store.getName());
            request.setAttribute("email", store.getEmail());
            request.setAttribute("phoneNumber", store.getPhoneNumber());
            request.setAttribute("street", store.getAddress().getStreet());
            request.setAttribute("city", store.getAddress().getCity());
            request.setAttribute("zipCode", store.getAddress().getZipCode());
            request.setAttribute("state", store.getAddress().getState());
            request.setAttribute("country", store.getAddress().getCountry().getCountry());
            request.setAttribute("latitude", store.getLatitude());
            request.setAttribute("longitude", store.getLongitude());
            request.setAttribute("idStore", store.getID());

            OpeningHour openingHours = store.getOpeningHours();

            hourliesFrom.put(days[0], openingHours.getMonOpen().format(FOMATTER_HOUR));
            hourliesTo.put(days[0], openingHours.getMonClose().format(FOMATTER_HOUR));

            hourliesFrom.put(days[1], openingHours.getTuesOpen().format(FOMATTER_HOUR));
            hourliesTo.put(days[1], openingHours.getTuesClose().format(FOMATTER_HOUR));

            hourliesFrom.put(days[2], openingHours.getWedOpen().format(FOMATTER_HOUR));
            hourliesTo.put(days[2], openingHours.getWedClose().format(FOMATTER_HOUR));

            hourliesFrom.put(days[3], openingHours.getThuOpen().format(FOMATTER_HOUR));
            hourliesTo.put(days[3], openingHours.getThuClose().format(FOMATTER_HOUR));

            hourliesFrom.put(days[4], openingHours.getFriOpen().format(FOMATTER_HOUR));
            hourliesTo.put(days[4], openingHours.getFriClose().format(FOMATTER_HOUR));

            hourliesFrom.put(days[5], openingHours.getSatOpen().format(FOMATTER_HOUR));
            hourliesTo.put(days[5], openingHours.getSatClose().format(FOMATTER_HOUR));

            hourliesFrom.put(days[6], openingHours.getSunOpen().format(FOMATTER_HOUR));
            hourliesTo.put(days[6], openingHours.getSunClose().format(FOMATTER_HOUR));


            // opening hours
             for(int indiceDay = 0; indiceDay < 7; indiceDay++) {
                if(hourliesFrom.get(days[indiceDay]).equals("00:00") && hourliesTo.get(days[indiceDay]).equals("23:59")) 
                    id24h.put(days[indiceDay], true);
                else
                    id24h.put(days[indiceDay], false);

                if(hourliesFrom.get(days[indiceDay]).equals("00:00") && hourliesTo.get(days[indiceDay]).equals("00:00")) 
                    isClosed.put(days[indiceDay], true);
                else
                    isClosed.put(days[indiceDay], false);

                request.setAttribute("from"+days[indiceDay], hourliesFrom.get(days[indiceDay]));
                request.setAttribute("to"+days[indiceDay], hourliesTo.get(days[indiceDay]));
                request.setAttribute("closed"+days[indiceDay], isClosed.get(days[indiceDay]).toString());
                request.setAttribute("24hrs"+days[indiceDay], id24h.get(days[indiceDay]).toString());
            }

            request.setAttribute("activePage","UpdateStore");
            map.put("title", "Update Store");
            map.put("actionForm", "UpdateStore");
            logger.info("Exit UpdateStore");
            return "storeEdition";
        } catch(Exception e) {
                request.setAttribute("erreur", e.getMessage());
                logger.info("The store  has not been added. " + e.toString());
                return "index"; 
        }
    }
    
    @RequestMapping(value = "/AddStore", method = RequestMethod.POST)
    @Transactional
    public String AddStorePOST(HttpServletRequest request,HttpServletResponse response,HttpSession session,
          @RequestParam(value="name", required=false) String name, 
          @RequestParam(value="email", required=false) String email,
          ModelMap map) {
        logger.info("Entry in addStore");
        request.setAttribute("activePage","AddStore");
        map.put("title", "Add Store");
        map.put("actionForm", "AddStore");
        System.out.println("AddStore POST -> name:"+name);
        
        try {
        
            if(verificationStoreInformations(request)) {
                Country objtCountry = countryManager.getCountryByName(country);
                if(objtCountry == null)
                {
                    objtCountry = new Country(country);
                }
                Address address = new Address(street, city, state, zipCode, objtCountry);
                LocalDate now = LocalDate.now();

                OpeningHour openingHour = new OpeningHour(LocalTime.parse(hourliesFrom.get(days[6])+":00"),LocalTime.parse(hourliesTo.get(days[6])+":00"),
                        LocalTime.parse(hourliesFrom.get(days[0])+":00"), LocalTime.parse(hourliesTo.get(days[0])+":00"),
                        LocalTime.parse(hourliesFrom.get(days[1])+":00"), LocalTime.parse(hourliesTo.get(days[1])+":00"),
                        LocalTime.parse(hourliesFrom.get(days[2])+":00"), LocalTime.parse(hourliesTo.get(days[2])+":00"),
                        LocalTime.parse(hourliesFrom.get(days[3])+":00"), LocalTime.parse(hourliesTo.get(days[3])+":00"),
                        LocalTime.parse(hourliesFrom.get(days[4])+":00"), LocalTime.parse(hourliesTo.get(days[4])+":00"),
                        LocalTime.parse(hourliesFrom.get(days[5])+":00"), LocalTime.parse(hourliesTo.get(days[5])+":00"));
                Store store = new Store("", storeName, phoneNumber, email, Float.parseFloat(latitude), Float.parseFloat(longitude), now, uamanager.getUserAccountById((int) session.getAttribute("userId")), address, openingHour,uamanager.getUserAccountById((int) session.getAttribute("userId")),false);
                storeManager.addStore(store);
                request.setAttribute("success", "The store '"+storeName+"' has been registered");
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
        request.setAttribute("activePage","Stores");

        List<Store> storesLis = storeManager.getAll();
        request.setAttribute("stores", storesLis);
        
        logger.info("Exit in addStore");
        return "stores";    
    }
    
    @RequestMapping(value = "/UpdateStore", method = RequestMethod.POST)
    @Transactional
    public String UpdateStorePOST(HttpServletRequest request,HttpServletResponse response,HttpSession session,
          @RequestParam(value="name", required=false) String name, 
          @RequestParam(value="email", required=false) String email,
          ModelMap map) {
        logger.info("Entry in updateStore");
        request.setAttribute("activePage","UpdateStore");
        map.put("title", "Update Store");
        map.put("actionForm", "UpdateStore");
        
        try {
        
            if(verificationStoreInformations(request)) {
                
                Store store = storeManager.getStoreById(Integer.parseInt(idStore));

                Country objtCountry = countryManager.getCountryByName(country);
                if(objtCountry == null)
                {
                    objtCountry = new Country(country);
                }
                
                Address address = store.getAddress();
                address.setCity(city);
                address.setStreet(street);
                address.setState(state);
                address.setZipCode(zipCode);
                address.setCountry(objtCountry);

                OpeningHour openingHour = store.getOpeningHours();
                openingHour.setMonOpen(LocalTime.parse(hourliesFrom.get(days[0])+":00"));
                openingHour.setMonClose(LocalTime.parse(hourliesTo.get(days[0])+":00"));
                openingHour.setTuesOpen(LocalTime.parse(hourliesFrom.get(days[1])+":00"));
                openingHour.setTuesClose(LocalTime.parse(hourliesTo.get(days[1])+":00"));
                openingHour.setWedOpen(LocalTime.parse(hourliesFrom.get(days[2])+":00"));
                openingHour.setWedClose(LocalTime.parse(hourliesTo.get(days[2])+":00"));
                openingHour.setThuOpen(LocalTime.parse(hourliesFrom.get(days[3])+":00"));
                openingHour.setThuClose(LocalTime.parse(hourliesTo.get(days[3])+":00"));
                openingHour.setFriOpen(LocalTime.parse(hourliesFrom.get(days[4])+":00"));
                openingHour.setFriClose(LocalTime.parse(hourliesTo.get(days[4])+":00"));
                openingHour.setSatOpen(LocalTime.parse(hourliesFrom.get(days[5])+":00"));
                openingHour.setSatClose(LocalTime.parse(hourliesTo.get(days[5])+":00"));
                openingHour.setSunOpen(LocalTime.parse(hourliesFrom.get(days[6])+":00"));
                openingHour.setSunClose(LocalTime.parse(hourliesTo.get(days[6])+":00"));
                
                store.setName(storeName);
                store.setPhoneNumber(phoneNumber);
                store.setEmail(email);
                store.setLatitude(Float.parseFloat(latitude));
                store.setLongitude(Float.parseFloat(longitude));
                store.setLastModifiedDate(LocalDate.now());
                store.setLastModifiedBy(uamanager.getUserAccountById((int) session.getAttribute("userId")));

                storeManager.addStore(store);
                request.setAttribute("success", "The store '"+storeName+"' has been updated.");
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
                request.setAttribute("idStore", idStore);
                
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
        request.setAttribute("activePage","Stores");
        List<Store> storesLis = storeManager.getAll();
        request.setAttribute("stores", storesLis);
        
        logger.info("Exit in addStore");
        return "stores";    
    }
    
    public boolean verificationStoreInformations(HttpServletRequest request) throws Exception {
        logger.info("Entry in VerificationStoreInformations");
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
        idStore = request.getParameter("idStore");

        // opening hours
        for(int indiceDay = 0; indiceDay < 7; indiceDay++) {
            hourliesFrom.put(days[indiceDay], request.getParameter("from"+days[indiceDay]));
            hourliesTo.put(days[indiceDay], request.getParameter("to"+days[indiceDay]));
            isClosed.put(days[indiceDay], (request.getParameter("closed"+days[indiceDay]) != null && request.getParameter("closed"+days[indiceDay]).equals( "on" )) ? true : false );
            id24h.put(days[indiceDay], (request.getParameter("24hrs"+days[indiceDay]) != null &&  request.getParameter("24hrs"+days[indiceDay]).equals( "on" )) ? true : false );
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
        verif.pointPositionVerification(latitude, longitude);
        
        logger.info("store verifications");
        // opening hours
        for(int indiceDay = 0; indiceDay < 7; indiceDay++) {
            verif.openingHourVerification(daysLong[indiceDay], hourliesFrom.get(days[indiceDay]), hourliesTo.get(days[indiceDay]), isClosed.get(days[indiceDay]), id24h.get(days[indiceDay]));
            
            // data translation for the database
            if(id24h.get(days[indiceDay])) {
                hourliesFrom.replace(days[indiceDay], "00:00");
                hourliesTo.replace(days[indiceDay], "23:59");
            }
            if(isClosed.get(days[indiceDay])) {
                hourliesFrom.replace(days[indiceDay], "00:00");
                hourliesTo.replace(days[indiceDay], "00:00");
            }
                
       }
        logger.info("Exit VerificationStoreInformations");
        return true;
    }
}
