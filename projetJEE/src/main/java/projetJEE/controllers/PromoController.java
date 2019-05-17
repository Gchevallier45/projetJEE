/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.controllers;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
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
import org.springframework.web.multipart.MultipartFile;
import projetJEE.bl.concrete.AddressManager;
import projetJEE.bl.concrete.PromotionManager;
import projetJEE.bl.concrete.TypeManager;
import projetJEE.bl.concrete.UserAccountManager;
import projetJEE.models.Promotion;
import projetJEE.models.Verifications;
 
@Controller public class PromoController { 
    
    private static final Logger logger = Logger.getLogger(PromoController.class);
    String title;
    String shortDescription;
    String longDescription;
    String startDate;
    String endDate;
    String idPromo;
    
    @Resource
    UserAccountManager uamanager;
    @Resource
    AddressManager adrmanager;
    @Resource
    TypeManager typmanager;
    @Resource
    PromotionManager promotionManager;
    
    @RequestMapping(value = "/Promotions", method = RequestMethod.GET)
    public String Promotions(ModelMap map, HttpServletRequest request) {
        logger.info("Entry in Promotions");
        request.setAttribute("activePage","Promotions");

        List<Promotion> promotionsList = promotionManager.getPromotions();
        request.setAttribute("promotions", promotionsList);
        
        logger.info("Exit Promotions");
        return "promotions";    
    }
    
    @RequestMapping(value = "/AddPromo", method = RequestMethod.GET)
    public String AddPromo(ModelMap map, HttpServletRequest request, HttpSession session) {
        logger.info("Entry in AddPromo");
        try {
            if(session.getAttribute("userStatus") == null || !session.getAttribute("userStatus").equals("Owner"))
                throw new Exception("You are not allowed to access this page. You must log in as a Owner.");
            
            request.setAttribute("activePage","AddPromo");
            map.put("title", "Add Promotions");
            map.put("actionForm", "AddPromo");
            logger.info("Exit AddPromo");
            return "promoEdition";
        } catch(Exception e) {
            request.setAttribute("erreur", e.getMessage());
            logger.info("The Promo  has not been added. " + e.toString());
            return "index"; 
        }
    }
    
    @RequestMapping(value = "/AddPromo", method = RequestMethod.POST)
    @Transactional
    public String AddPromoPOST(HttpServletRequest request,HttpServletResponse response,HttpSession session,
          @RequestParam(value="name", required=false) String name, 
          @RequestParam(value="email", required=false) String email,
          @RequestParam("file") MultipartFile uploadedFile,
          ModelMap map) {
        logger.info("Entry in AddPromo");
        request.setAttribute("activePage","AddPromo");
        map.put("title", "Add Promotions");
        map.put("actionForm", "AddPromo");
        // ofLocalizedDate(dateStyle)
        try {
            if(verificationPromoInformations(request)) {
                String path = request.getServletContext().getRealPath("/WEB-INF/resources/img/promos/") + File.separator + uploadedFile.getOriginalFilename();
                try {
                    File file = new File(path);
                    uploadedFile.transferTo(file);
                } catch (IOException | IllegalStateException ex) {
                    java.util.logging.Logger.getLogger(PromoController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                Promotion promotion = new Promotion("", title, shortDescription, longDescription, 0, false, LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE), LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE), uploadedFile.getOriginalFilename());
                promotionManager.addPromotion(promotion);
                request.setAttribute("success", "The promotion has been registered");
                
                // add
                List<Promotion> promotionsList = promotionManager.getPromotions();
                
                 request.setAttribute("promotions", promotionsList);
                 request.setAttribute("activePage","Promotions");
                 logger.info("Exit in addStore");
            }
            
        } catch(Exception e) {
            // promo informations
            request.setAttribute("promoTitle", title);
            request.setAttribute("shortDescription", shortDescription);
            request.setAttribute("longDescription", longDescription);
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);
            request.setAttribute("idPromo", idPromo);

            request.setAttribute("erreur", "The promotion  has not been added. " + e.getMessage());
            logger.info("The promotion  has not been added. " + e.toString());
            return "promoEdition"; 
        }
        
        
        /*
        List<Store> storesLis = storeManager.getAll();
        request.setAttribute("stores", storesLis);
        */
        return "promotions"; 
    }
    
    @RequestMapping(value = "/UpdatePromotion", method = RequestMethod.GET)
    public String UpdatePromotion(ModelMap map, HttpServletRequest request, HttpSession session,
             @RequestParam(value="promotionId", required=false) int promotionId) {
        logger.info("Entry in UpdatePromotion");
        try {
            if(session.getAttribute("userStatus") == null || !session.getAttribute("userStatus").equals("Owner"))
                throw new Exception("You are not allowed to access this page. You must log in as a Owner.");
            
             request.setAttribute("activePage","UpdatePromotion");
             
            // get promotion id
            Promotion promotion = null;
            try {
                 promotion = promotionManager.getPromotionById(promotionId);
            } catch(Exception e) {
                throw new Exception("The store with id '" + promotionId + "' not exist.");
            }
            
            // add parameters
            // store informations
            request.setAttribute("promoTitle", promotion.getTitle());
            request.setAttribute("shortDescription", promotion.getShortDescription());
            request.setAttribute("longDescription", promotion.getLongDescription());
            request.setAttribute("startDate", promotion.getStartDate());
            request.setAttribute("endDate", promotion.getEndDate());
            request.setAttribute("idPromo", promotion.getID());
            
            map.put("title", "Update promotion");
            map.put("actionForm", "UpdatePromotion");
            logger.info("Exit UpdatePromotion");
            return "promoEdition";
        } catch(Exception e) {
            request.setAttribute("erreur", e.getMessage());
            logger.info("The Promo  has not been added. " + e.toString());
            return "index"; 
        }
    }
    
    @RequestMapping(value = "/UpdatePromotion", method = RequestMethod.POST)
    @Transactional
    public String UpdatePromotion(HttpServletRequest request,HttpServletResponse response,HttpSession session,
          @RequestParam(value="name", required=false) String name, 
          @RequestParam(value="email", required=false) String email,
          ModelMap map) {
        logger.info("Entry in UpdatePromotion");
        request.setAttribute("activePage","UpdatePromotion");
        map.put("title", "Update Promotions");
        map.put("actionForm", "UpdatePromotion");
        // ofLocalizedDate(dateStyle)
        try {
            if(verificationPromoInformations(request)) {
                
                
                Promotion promotion = promotionManager.getPromotionById(Integer.parseInt(idPromo));
                promotion.setTitle(title);
                promotion.setShortDescription(shortDescription);
                promotion.setLongDescription(longDescription);
                promotion.setStartDate(LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE));
                promotion.setEndDate(LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE));

                promotionManager.addPromotion(promotion);
                request.setAttribute("success", "The promotion has been updated");
                
                // add
                List<Promotion> promotionsList = promotionManager.getPromotions();
                
                 request.setAttribute("promotions", promotionsList);
                 request.setAttribute("activePage","Promotions");
                 logger.info("Exit in addStore");
            }
            
        } catch(Exception e) {
            // promo informations
            request.setAttribute("promoTitle", title);
            request.setAttribute("shortDescription", shortDescription);
            request.setAttribute("longDescription", longDescription);
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);
            request.setAttribute("idPromo", idPromo);

            request.setAttribute("erreur", "The promotion  has not been added. " + e.getMessage());
            logger.info("The promotion  has not been added. " + e.toString());
            return "promoEdition"; 
        }
        
        
        /*
        List<Store> storesLis = storeManager.getAll();
        request.setAttribute("stores", storesLis);
        */
        return "promotions"; 
    }
    
    @RequestMapping(value = "/DeletePromotion", method = RequestMethod.GET)
    public String DeletePromotion(ModelMap map, HttpServletRequest request, HttpSession session,
            @RequestParam(value="promotionId", required=false) int promotionId) {
        logger.info("Entry in DeletePromotion");
        try {
            if(session.getAttribute("userStatus") == null || !session.getAttribute("userStatus").equals("Owner") && !session.getAttribute("userStatus").equals("Administrator"))
                throw new Exception("You are not allowed to access this page. You must log in as a Owner.");
            
            Promotion promotion = null;
            try {
                 promotion = promotionManager.getPromotionById(promotionId);
            } catch(Exception e) {
                throw new Exception("The promotion with id '" + promotionId + "' not exist.");
            }

            promotionManager.removePromotion(promotion);
            
            request.setAttribute("success", "The promotion '"+promotion.getTitle()+"' has been removed.");
            request.setAttribute("activePage","Promotions");
            List<Promotion> promotionsList = promotionManager.getPromotions();
            request.setAttribute("promotions", promotionsList);
            logger.info("Exit DeletePromotion");
            return "promotions";
        } catch(Exception e) {
                request.setAttribute("erreur", e.getMessage());
                logger.info("The store  has not been deleted. " + e.toString());
                return "index"; 
        }
    }
    
    public boolean verificationPromoInformations(HttpServletRequest request) throws Exception {
        logger.info("Entry in VerificationStoreInformations");
        
        // promo informations
        title = request.getParameter("promoTitle");
        shortDescription = request.getParameter("shortDescription");
        longDescription= request.getParameter("longDescription");
        startDate = request.getParameter("startDate");
        endDate = request.getParameter("endDate");
        idPromo= request.getParameter("idPromo");
        
         Verifications verif = new Verifications();
        
        // promo verifications
        verif.titleVerification(title);
        verif.shortDesciptionVerification(shortDescription);
        verif.longDescriptionDesciptionVerification(longDescription);
        verif.longDescriptionDesciptionVerification(longDescription);
        verif.datesVerification(startDate, endDate);
        
        logger.info("Exit VerificationStoreInformations");
        return true;
    }
} 

