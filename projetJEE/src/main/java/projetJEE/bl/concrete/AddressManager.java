/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.bl.concrete;

import projetJEE.models.Address;
import projetJEE.domain.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressManager {
    
    private AddressRepository repo;
    
    @Autowired
    public AddressManager(AddressRepository uarepo) {
        this.repo = uarepo;
    }
    
    public Address getAddressById(int id) {
        return this.repo.findById(id).get();
    }
    
    public void addAddress(Address address){
        this.repo.save(address);
        this.repo.flush();
    }
}
