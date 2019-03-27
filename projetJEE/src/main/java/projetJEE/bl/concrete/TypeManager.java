/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.bl.concrete;

import projetJEE.models.Type;
import projetJEE.domain.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TypeManager {
    
    private TypeRepository repo;
    
    @Autowired
    public TypeManager(TypeRepository uarepo) {
        this.repo = uarepo;
    }
    
    public Type getTypeById(int id) {
        return this.repo.findById(id).get();
    }
    
    public void addType(Type type){
        this.repo.save(type);
        this.repo.flush();
    }
}
