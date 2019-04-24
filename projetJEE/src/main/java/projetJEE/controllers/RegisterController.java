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
import projetJEE.bl.concrete.*;
import projetJEE.models.*;

@Controller public class RegisterController {
    private static final Logger logger = Logger.getLogger(DefaultController.class);
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
          @RequestParam(value="street", required=false) String street,
          @RequestParam(value="city", required=false) String city,
          @RequestParam(value="zipCode", required=false) String zipCode,
          @RequestParam(value="state", required=false) String state,
          @RequestParam(value="country", required=false) String country,
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
        street
        city
        zipCode
        state
        country
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
            
            if(!password.equals(password2)) {
                    throw new Exception("Les deux mots de passes ne sont pas identiques.");
            }
            
            // we remove espace and point
             phoneNumber = phoneNumber.replace(" ","").replace(".","");
            
            if(phoneNumber == null || phoneNumber.isEmpty()) {
                    throw new Exception("Le champ phoneNumber doit être complété.");
            }
            
            // verif number
            if(!phoneNumber.matches("[0-9]*") || phoneNumber.length() != 10) {
                    throw new Exception("Le fomat du champ phoneNumber n'est pas respectée. Exemple : 02 78 84 84 65");
            }
            
            
            if(street == null || street.isEmpty()) {
                    throw new Exception("Le champ address doit être complété.");
            }
            
            if(city == null || city.isEmpty()) {
                    throw new Exception("Le champ city doit être complété.");
            }
            
            // we remove espace and point
             zipCode = zipCode.replace(" ","").replace(".","");
            
            if(zipCode == null || zipCode.isEmpty()) {
                    throw new Exception("Le champ zipCode doit être complété.");
            }
            
            // verif number
            if(!zipCode.matches("[0-9]*") || zipCode.length() != 5) {
                    throw new Exception("Le fomat du champ zipCode n'est pas respectée. Exemple : 49000");
            }
            
            if(state == null || state.isEmpty()) {
                    throw new Exception("Le champ state doit être complété.");
            }
            
            if(country == null || country.isEmpty()) {
                    throw new Exception("Le champ contry doit être complété.");
            }
            
            if(uamanager.userAccountExists(email))
                throw new Exception("Le mail est déjà utilisé pour un autre compte.");

            // get country if exist
            Country objtCountry = countryManager.getCountryByName(country);
            if(objtCountry == null) // so add country
            {
                objtCountry = new Country(country);
                countryManager.addCountry(objtCountry);
            }
            
            // add a new adress
            Address address = new Address(street, city, state, zipCode, objtCountry);
            adrmanager.addAddress(address);

            Type type = typeManager.getTypeByName("client");

            // add user account
            LocalDate now = LocalDate.now();
            System.out.println("Before add account");
            UserAccount newUser = new UserAccount(firstName, lastName, email, password, phoneNumber, false, now, now, "fff", null, false, type, address);
            
           System.out.println("After create object");
            uamanager.addUserAccount(newUser);
System.out.println("After add account");
           return "index"; 
            
        } catch(Exception e) {
                request.setAttribute("firstName", firstName);
                request.setAttribute("lastName", lastName);
                request.setAttribute("email", email);
                request.setAttribute("phoneNumber", phoneNumber);
                request.setAttribute("street", street);
                request.setAttribute("city", city);
                request.setAttribute("zipCode", zipCode);
                request.setAttribute("state", state);
                request.setAttribute("country", country);
                
                request.setAttribute("erreur", e.getMessage());
                logger.info("Connexion refusé");
                return "registerPage"; 
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

