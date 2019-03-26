/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
import org.apache.log4j.Logger;
 
@Controller public class SecondeController {
 
    private static final Logger logger = Logger.getLogger(SecondeController.class);
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map) {
        
        //logs debug message
        if(logger.isDebugEnabled()){
                logger.debug("getWelcome is executed!");
        }

        //logs exception
        logger.error("This is Error message", new Exception("Testing"));
        
        logger.info("Accès à la page d'accueil du site.");
        return "index";    
    }
    
}