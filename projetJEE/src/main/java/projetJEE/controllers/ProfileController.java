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

@Controller public class ProfileController {
 
    private static final Logger logger = Logger.getLogger(ProfileController.class);
    @Resource
    UserAccountManager uamanager;
    
    @RequestMapping(value = "/Profile", method = RequestMethod.GET)
    public String Profile(ModelMap map, HttpServletRequest request, HttpSession session) {
        logger.info("Entry in profile");
 
        UserAccount user = uamanager.getUserAccountById((int)session.getAttribute("userId"));
        
        if(user != null){
            request.setAttribute("user",user);    
        }

        logger.info("Exit Profile");
        return "profile";    
    }
}
