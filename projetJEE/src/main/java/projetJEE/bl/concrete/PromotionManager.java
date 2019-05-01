/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.bl.concrete;

import java.util.List;
import projetJEE.models.Promotion;
import projetJEE.domain.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PromotionManager {
    
    private PromotionRepository repo;
    
    @Autowired
    public PromotionManager(PromotionRepository uarepo) {
        this.repo = uarepo;
    }
    
    public Promotion getPromotionById(int id) {
        return this.repo.findById(id).get();
    }
    
    public List<Promotion> getPromotions() {
        return this.repo.findAll();
    }
    
    public void addPromotion(Promotion promotion){
        this.repo.save(promotion);
        this.repo.flush();
    }
}
