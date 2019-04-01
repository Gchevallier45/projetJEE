/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.controllers;
 
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
import projetJEE.bl.concrete.AddressManager;
import projetJEE.bl.concrete.StoreManager;
import projetJEE.bl.concrete.TypeManager;
import org.springframework.web.bind.annotation.RequestParam;
import projetJEE.bl.concrete.UserAccountManager;
import projetJEE.models.Address;
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
    
} 
