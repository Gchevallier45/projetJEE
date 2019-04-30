/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.bl.concrete;

import org.apache.log4j.Logger;
import projetJEE.models.Address;
import projetJEE.domain.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressManager {
    
    private static final Logger logger = Logger.getLogger(AddressManager.class);
    private AddressRepository repo;
    
    @Autowired
    public AddressManager(AddressRepository uarepo) {
        this.repo = uarepo;
    }
    
    public Address getAddressById(int id) {
        logger.info("Entrée dans la fonction getAddressById");
        return this.repo.findById(id).get();
    }
    
    public void addAddress(Address address){
        logger.info("Entrée dans la fonction addAddress");
        this.repo.save(address);
        this.repo.flush();
    }
}
