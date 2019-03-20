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
 
@Controller public class DefaultController { 
 
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map) {
        return "index";    
    }
    
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
} 
