/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.bl.concrete;

import projetJEE.models.OpeningHour;
import projetJEE.domain.repository.OpeningHourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OpeningHourManager {
    
    private OpeningHourRepository repo;
    
    @Autowired
    public OpeningHourManager(OpeningHourRepository uarepo) {
        this.repo = uarepo;
    }
    
    public OpeningHour getOpeningHourById(int id) {
        return this.repo.findById(id).get();
    }
    
    public void addOpeningHour(OpeningHour openingHour){
        this.repo.save(openingHour);
        this.repo.flush();
    }
}