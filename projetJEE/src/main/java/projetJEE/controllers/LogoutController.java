package projetJEE.controllers;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
 
@Controller public class LogoutController { 
    
    private static final Logger logger = Logger.getLogger(DefaultController.class);

    
    @RequestMapping(value = "/Logout", method = RequestMethod.GET)
    public String LoginPage(HttpServletRequest request,HttpServletResponse response,HttpSession session,
            ModelMap map) {
        request.getSession().invalidate();
        logger.info("Logout");
        return "index";    
    }

  
} 

