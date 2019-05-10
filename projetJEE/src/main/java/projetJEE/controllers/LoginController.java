/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.controllers;
 
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
import projetJEE.bl.concrete.AddressManager;
import projetJEE.bl.concrete.TypeManager;
import org.springframework.web.bind.annotation.RequestParam;
import projetJEE.bl.concrete.UserAccountManager;
import projetJEE.models.UserAccount;
 
@Controller public class LoginController { 
    
    private static final Logger logger = Logger.getLogger(DefaultController.class);
    @Resource
    UserAccountManager uamanager;
    @Resource
    AddressManager adrmanager;
    @Resource
    TypeManager typmanager;
    
    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public String LoginPage(ModelMap map) {
        logger.info("Accès à la page de login.");
        return "login";    
    }
    
 @RequestMapping(value="/Login",method=RequestMethod.POST)
  public String login(HttpServletRequest request,HttpServletResponse response,HttpSession session,
          @RequestParam(value="email", required=false) String email, 
          @RequestParam(value="password", required=false) String password,
          ModelMap map){
      
        logger.info("Accès à la page de login. POST");

        try {

            if(email == null || email.isEmpty() || password== null || password.isEmpty()) {
                    throw new Exception("L'adresse mail ou le mot de passe est null");
            }
            
            if(uamanager.validateLogin(email, password))
            {
                UserAccount user = uamanager.getByEmail(email);
                // la connexion est établie
                session.setAttribute("userId", user.getID());
                session.setAttribute("userEmail", user.getEmail());
                session.setAttribute("userLastName", user.getLastName());
                session.setAttribute("userFirstName", user.getFirstName());
                session.setAttribute("userStatus", user.getType().getType());
                logger.info("La connexion est établie en tant que " + user.getFirstName() +" "+ user.getLastName()+'('+email+")");
                return "index"; 
            }
            else
            {
                    // connexion refusée
                    throw new Exception("Le pseudo ou le mot de passe n'est pas correcte !");
            }

        } catch(Exception e) {

                request.setAttribute("email", email);
                request.setAttribute("password", password);

                request.setAttribute("erreur", e.getMessage());
                logger.info("Connexion refusé");
                return "login"; 
        }
  }  
} 

