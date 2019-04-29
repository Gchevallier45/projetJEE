/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.controllers;
 
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
import projetJEE.bl.concrete.AddressManager;
import projetJEE.bl.concrete.StoreManager;
import projetJEE.bl.concrete.TypeManager;
import org.springframework.web.bind.annotation.RequestParam;
import projetJEE.bl.concrete.CountryManager;
import projetJEE.bl.concrete.PromotionManager;
import projetJEE.bl.concrete.UserAccountManager;
import projetJEE.models.Address;
import projetJEE.models.Country;
import projetJEE.models.Promotion;
import projetJEE.models.Store;
import projetJEE.models.Type;
import projetJEE.models.UserAccount;
 
@Controller public class DefaultController { 
    
    private static final Logger logger = Logger.getLogger(DefaultController.class);
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map) {
        
        //logs debug message
        if(logger.isDebugEnabled()){
                //logger.debug("getWelcome is executed!");
        }

        //logs exception
       // logger.error("This is Error message", new Exception("Testing"));
        
        logger.info("Accès à la page d'accueil du site.");
        return "index";    
    }
 
    @RequestMapping(value = "/tp1", method = RequestMethod.GET)
    public String tp1(ModelMap map) {
        map.put("msg", "Hello Spring 5 Web MVC!");
        return "tp1";    
    }

    @Resource
    UserAccountManager uamanager;
    @Resource
    AddressManager adrmanager;
    @Resource
    TypeManager typmanager;
    @Resource
    StoreManager storemanager;
    @Resource
    CountryManager countrymanager;
    @Resource
    PromotionManager promomanager;
    @RequestMapping(value = "/bddtest", method = RequestMethod.GET)
    public String bddtest(ModelMap map){
        //UserAccount ua = uamanager.getUserAccountById(1);
        //Address adr = adrmanager.getAddressById(1);
        //Type typ = typmanager.getTypeById(1);
        
        //map.put("msg", "BDD test");
        //map.put("userId", ua.getID());
        //map.put("userName", ua.getAddress().getCity());
        
        //UserAccount ub;
        //ub = new UserAccount("nom","prenom","email@email.fr","pass","0254879854",true,LocalDate.now(),LocalDate.now(),"mdr",LocalDate.now(),false,typ,adr);
        //uamanager.addUserAccount(ub);
        uamanager.changeUserUUID(1, "camarche");
        
        List<UserAccount> user = uamanager.getAllUsers();
        
        Store store = storemanager.getStoreById(0);
        
        Country country = countrymanager.getCountryByName("France");
        
        Promotion promo = promomanager.getPromotionById(1);
 
        //map.put("userName", user.get(0).getFirstName());
 
        map.put("magasinId",user.get(0).getUUID());
        map.put("magasinName", store.getOpeningHours().get("Monday").getOpenHour());
        map.put("userName",uamanager.validateLogin("coucou@coucou.fr", "password"));
        /*try {
            //trouver utilisateur avec adresse mail
            URL json = new URL("https://nominatim.openstreetmap.org/?street=Tour+Eiffel&postalcode=75000&city=Paris&country=france&format=json");
            logger.info(json.getContent().toString());
        } catch (MalformedURLException ex) {
            java.util.logging.Logger.getLogger(DefaultController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(DefaultController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return "bddtest";
    }
} 
