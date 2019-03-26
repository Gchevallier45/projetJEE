/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.bl.concrete;

import projetJEE.models.Country;
import projetJEE.domain.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryManager {
    
    private CountryRepository repo;
    
    @Autowired
    public CountryManager(CountryRepository uarepo) {
        this.repo = uarepo;
    }
    
    public Country getCountryById(int id) {
        return this.repo.findById(id).get();
    }
    
    public void addCountry(Country country){
        this.repo.save(country);
        this.repo.flush();
    }
}
