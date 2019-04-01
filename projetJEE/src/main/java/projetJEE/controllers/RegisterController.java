/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.controllers;

import java.time.LocalDate;
import java.util.List;
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
import projetJEE.bl.concrete.AddressManager;
import projetJEE.bl.concrete.TypeManager;
import projetJEE.bl.concrete.UserAccountManager;
import projetJEE.models.Address;
import projetJEE.models.Type;
import projetJEE.models.UserAccount;

@Controller public class RegisterController {
    private static final Logger logger = Logger.getLogger(DefaultController.class);
    @Resource
    UserAccountManager uamanager;
    @Resource
    AddressManager adrmanager;
    @Resource
    TypeManager typmanager;
    
    @RequestMapping(value = "/Register", method = RequestMethod.GET)
    public String registerPage(ModelMap map) {
        logger.info("Accès à la page register.");
        return "registerPage";    
    }
    
    @RequestMapping(value="/register",method=RequestMethod.POST)
  public String register(HttpServletRequest request,HttpServletResponse response,HttpSession session,
          @RequestParam(value="firstName", required=false) String firstName, 
          @RequestParam(value="lastName", required=false) String lastName,
          @RequestParam(value="email", required=false) String email,
          @RequestParam(value="password", required=false) String password,
          @RequestParam(value="password2", required=false) String password2,
          @RequestParam(value="phoneNumber", required=false) String phoneNumber,
          @RequestParam(value="address", required=false) String address,
          @RequestParam(value="city", required=false) String city,
          @RequestParam(value="zipCode", required=false) String zipCode,
          @RequestParam(value="state", required=false) String state,
          @RequestParam(value="contry", required=false) String contry,
          ModelMap map){
      
        logger.info("Accès à la page de register. POST");

        /* 
        var:
        firstName
        lastName
        email
        password
        password2
        phoneNumber
        address
        city
        zipCode
        state
        contry
        */
        
        try {

            if(firstName == null || firstName.isEmpty()) {
                    throw new Exception("Le champ firstName doit être complété.");
            }
            
            if(lastName == null || lastName.isEmpty()) {
                    throw new Exception("Le champ lastName doit être complété.");
            }
            
            if(email == null || email.isEmpty()) {
                    throw new Exception("Le champ email doit être complété.");
            }
            
            if(password == null || password.isEmpty()) {
                    throw new Exception("Le champ password doit être complété.");
            }
            
            if(password.equals(password)) {
                    throw new Exception("Les deux mots de passes ne sont pas identiques.");
            }
            
            if(phoneNumber == null || phoneNumber.isEmpty()) {
                    throw new Exception("Le champ phoneNumber doit être complété.");
            }
            
            if(address == null || address.isEmpty()) {
                    throw new Exception("Le champ address doit être complété.");
            }
            
            if(city == null || city.isEmpty()) {
                    throw new Exception("Le champ city doit être complété.");
            }
            
            if(zipCode == null || zipCode.isEmpty()) {
                    throw new Exception("Le champ zipCode doit être complété.");
            }
            
            if(state == null || state.isEmpty()) {
                    throw new Exception("Le champ state doit être complété.");
            }
            
            if(contry == null || contry.isEmpty()) {
                    throw new Exception("Le champ contry doit être complété.");
            }
            
            List<UserAccount> user = uamanager.getAllUsers();
           /* 
            if(uamanager.validateLogin(email, password))
            {
                LocalDate now = LocalDate.now();
                UserAccount newUser = UserAccount(firstName, lastName, email, password, phoneNumber, false, now, now, null, null, false, Type type, Address address)
                
                    // la connexion est établie
                    session.setAttribute("user", email);
                    logger.info("La connexion est établie en tant que " + email);
                    return "index"; 
            }
            else
            {
                    // connexion refusée
                    throw new Exception("Le pseudo ou le mot de passe n'est pas correcte !");
            }
*/return "index"; 
        } catch(Exception e) {

                request.setAttribute("email", email);
                request.setAttribute("password", password);

                request.setAttribute("erreur", e.getMessage());
                logger.info("Connexion refusé");
                return "login"; 
        }
  }
  
    
    
    
    
/*
    @Resource
    UserAccountManager uamanager;
    @Resource
    AddressManager adrmanager;
    @Resource
    TypeManager typmanager;
 @RequestMapping(value="/login",method=RequestMethod.POST)
  public String myAnotherFunc(HttpServletRequest request,HttpServletResponse response,HttpSession session,
          @RequestParam(value="email", required=false) String email, 
          @RequestParam(value="password", required=false) String password,
          ModelMap map){
      
        logger.info("Accès à la page de login. POST");

        try {

            if(email == null || email.isEmpty() || password== null || password.isEmpty()) {
                    throw new Exception("L'adresse mail ou le mot de passe est null");
            }
            
            List<UserAccount> user = uamanager.getAllUsers();
            
            if(uamanager.validateLogin(email, password))
            {
                    // la connexion est établie
                    session.setAttribute("user", email);
                    logger.info("La connexion est établie en tant que " + email);
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
*/
  /*@Resource
    UserAccountManager uamanager;
    @Resource
    AddressManager adrmanager;
    @Resource
    TypeManager typmanager;
    @RequestMapping(value = "/bddtest", method = RequestMethod.GET)
    public String bddtest(ModelMap map) {
        //UserAccount ua = uamanager.getUserAccountById(1);
        //Address adr = adrmanager.getAddressById(1);
        //Type typ = typmanager.getTypeById(1);
        
        //map.put("msg", "BDD test");
        //map.put("userId", ua.getID());
        //map.put("userName", ua.getAddress().getCity());
        
        //UserAccount ub;
        //ub = new UserAccount("nom","prenom","email@email.fr","pass","0254879854",true,LocalDate.now(),LocalDate.now(),"mdr",LocalDate.now(),false,typ,adr);
        //uamanager.addUserAccount(ub);
        
        List<UserAccount> user = uamanager.getAllUsers();
        //map.put("userName", user.get(0).getFirstName());
        
        map.put("userName",uamanager.validateLogin("coucou@coucou.fr", "password"));
        
        return "bddtest";
    }
  */
} 

