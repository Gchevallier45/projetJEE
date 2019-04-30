/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.bl.concrete;

import java.util.List;
import org.apache.log4j.Logger;
import projetJEE.models.Country;
import projetJEE.domain.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryManager {
    
    private static final Logger logger = Logger.getLogger(CountryManager.class);
    private CountryRepository repo;
    
    @Autowired
    public CountryManager(CountryRepository uarepo) {
        this.repo = uarepo;
    }
    
    public Country getCountryById(int id) {
        logger.info("Entrée dans la fonction getCountryById");
        return this.repo.findById(id).get();
    }
    
    public Country getCountryByName(String name){
        logger.info("Entrée dans la fonction getCountryByName");
        List<Country> foundCountries = this.repo.findByName(name);
        if(!foundCountries.isEmpty())
            return foundCountries.get(0);
        else
            return null;
    }
    
    public void addCountry(Country country){
        logger.info("Entrée dans la fonction addCountry");
        this.repo.save(country);
        this.repo.flush();
    }
}
