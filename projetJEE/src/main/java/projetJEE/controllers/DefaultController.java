/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.controllers;
 
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.ResponseBody; 
import org.springframework.web.bind.annotation.RequestParam;
import projetJEE.bl.concrete.UserAccountManager;
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
    
    
    @RequestMapping(value = "/Register", method = RequestMethod.GET)
    public String registerPage(ModelMap map) {
        logger.info("Accès à la page register.");
        return "registerPage";    
    }
    
    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public String LoginPage(ModelMap map) {
        logger.info("Accès à la page de login.");
        return "login";    
    }

    @RequestMapping(value="/test",method=RequestMethod.POST)
    public String login(HttpServletRequest request,HttpServletResponse response,
          @RequestParam(value="email", required=false) String email, 
          @RequestParam(value="password", required=false) String password){
      
    logger.info("Accès à la page de login. POST");
    logger.info("email:"+email + " pass:" + password);

     return "index"; 
  }
    
    
    @RequestMapping(value = "/tp1", method = RequestMethod.GET)
    public String tp1(ModelMap map) {
        map.put("msg", "Hello Spring 5 Web MVC!");
        return "tp1";    
    }
    
    @Resource
    UserAccountManager uamanager;
    @RequestMapping(value = "/bddtest", method = RequestMethod.GET)
    public String bddtest(ModelMap map) {
        UserAccount ua = uamanager.getUserAccountById(1);
        
        map.put("msg", "BDD test");
        map.put("userId", ua.getID());
        map.put("userName", ua.getFirstName());
        return "bddtest";
    }
} 
