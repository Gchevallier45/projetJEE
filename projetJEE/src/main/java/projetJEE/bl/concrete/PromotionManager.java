/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.bl.concrete;

import org.apache.log4j.Logger;
import projetJEE.models.Promotion;
import projetJEE.domain.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PromotionManager {
    
    private static final Logger logger = Logger.getLogger(PromotionManager.class);
    private PromotionRepository repo;
    
    @Autowired
    public PromotionManager(PromotionRepository uarepo) {
        this.repo = uarepo;
    }
    
    public Promotion getPromotionById(int id) {
        logger.info("Entrée dans la fonction getPromotionById");
        return this.repo.findById(id).get();
    }
    
    public void addPromotion(Promotion promotion){
        logger.info("Entrée dans la fonction addPromotion");
        this.repo.save(promotion);
        this.repo.flush();
    }
}
