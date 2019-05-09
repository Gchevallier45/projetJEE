/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.serviceContrats;

import java.time.LocalDate;
import java.util.List;
import projetJEE.bl.concrete.UserAccountManager;
import projetJEE.models.UserAccount;
import javax.annotation.Resource;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import projetJEE.bl.concrete.*;
import projetJEE.models.*;

/**
 *
 * @author redti
 */

@RestController
@RequestMapping("/api/promotionrest")
public class PromotionRest {
@Resource
    UserAccountManager uamanager;
@Resource
    PromotionManager promanager;
@Resource
    StoreManager stmanager;
@Resource
    OpeningHourManager ophmanager;
@Resource
    AddressManager admanager;
@Resource
    CountryManager comanager;

    @GetMapping(value = "/getTest", produces = MediaType.APPLICATION_JSON)
    public String getJson() {
        return "Hello Promotion REST!";
    }

    @RequestMapping(value = "/putTest", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON)
    public String putJson(@RequestBody String content) {
        return content + " Data promotion have been saved!";
    }

 

    @GetMapping(value = "/getPromotions", produces = MediaType.APPLICATION_JSON)
    public String getpromoInfo() throws Exception {
          
        //org.json.JSONObject obj = new  org.json.JSONObject();
        org.json.JSONObject jsonObjPromotions= new org.json.JSONObject();
        org.json.JSONArray jsonArrayPromotions = new org.json.JSONArray();
        org.json.JSONObject jsonObjPromotion;// = new org.json.JSONObject();
        
        try{
            List<Promotion> promoList = promanager.getPromotions();
            
            //Promotion promo;
            for (int i = 0; i < promoList.size(); i++) {
                jsonObjPromotion = new org.json.JSONObject();
                jsonObjPromotion.put("key", promoList.get(i).getKeyStr());
                jsonObjPromotion.put("position", promoList.get(i).getPosition());
                jsonObjPromotion.put("title", promoList.get(i).getTitle());
                jsonObjPromotion.put("shortDesc", promoList.get(i).getShortDescription());
                jsonObjPromotion.put("longDesc", promoList.get(i).getLongDescription());
                jsonObjPromotion.put("disabled", promoList.get(i).isDisabled());
                jsonObjPromotion.put("startDate", promoList.get(i).getStartDate());
                jsonObjPromotion.put("endDate", promoList.get(i).getEndDate());
                jsonObjPromotion.put("imageURL", promoList.get(i).getImageURL());
                jsonArrayPromotions.put(i, jsonObjPromotion);
            }
            
            jsonObjPromotions.put("promotions", jsonArrayPromotions);
            
        }catch(Exception e){
            throw new Exception("Il n y a aucune promotion dans la base de données");
        }        
        return jsonObjPromotions.toString(2);
    }
}
