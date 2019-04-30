/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.bl.concrete;

import org.apache.log4j.Logger;
import projetJEE.models.Store;
import projetJEE.domain.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreManager {
    
    private static final Logger logger = Logger.getLogger(StoreManager.class);
    private StoreRepository repo;
    
    @Autowired
    public StoreManager(StoreRepository uarepo) {
        this.repo = uarepo;
    }
    
    public Store getStoreById(int id) {
        logger.info("Entrée dans la fonction getStoreById");
        return this.repo.findById(id).get();
    }
    
    public void addStore(Store store){
        logger.info("Entrée dans la fonction addStore");
        this.repo.save(store);
        this.repo.flush();
    }
}
