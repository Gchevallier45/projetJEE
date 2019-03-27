/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.controllers;
 
import java.time.LocalDate;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
import projetJEE.bl.concrete.AddressManager;
import projetJEE.bl.concrete.TypeManager;
import projetJEE.bl.concrete.UserAccountManager;
import projetJEE.models.Address;
import projetJEE.models.Type;
import projetJEE.models.UserAccount;
 
@Controller public class DefaultController { 
    
    @RequestMapping(value = "/Register", method = RequestMethod.GET)
    public String registerPage(ModelMap map) {
        return "registerPage";    
    }
    
    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public String Login(ModelMap map) {
        return "login";    
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
} 
