/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.bl.concrete;

import java.util.List;
import org.apache.log4j.Logger;
import projetJEE.models.OpeningHour;
import projetJEE.domain.repository.OpeningHourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OpeningHourManager {
    
    private static final Logger logger = Logger.getLogger(OpeningHourManager.class);
    private OpeningHourRepository repo;
    
    @Autowired
    public OpeningHourManager(OpeningHourRepository uarepo) {
        this.repo = uarepo;
    }
    
    public List<OpeningHour> getOpeningHourByStoreId(int id) {
        logger.info("Entrée dans la fonction getOpeningHourByStoreId");
        return this.repo.findByIdStore(id);
    }
    
    public void addOpeningHour(OpeningHour openingHour){
        logger.info("Entrée dans la fonction addOpeningHour");
        this.repo.save(openingHour);
        this.repo.flush();
    }
}
