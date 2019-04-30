/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.bl.concrete;

import org.apache.log4j.Logger;
import projetJEE.models.Type;
import projetJEE.domain.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TypeManager {
    
    private static final Logger logger = Logger.getLogger(TypeManager.class);
    private TypeRepository repo;
    
    @Autowired
    public TypeManager(TypeRepository uarepo) {
        this.repo = uarepo;
    }
    
    public Type getTypeById(int id) {
        logger.info("Entrée dans la fonction getTypeById");
        return this.repo.findById(id).get();
    }
    
    public void addType(Type type){
        logger.info("Entrée dans la fonction addType");
        this.repo.save(type);
        this.repo.flush();
    }
    
    public Type getTypeByName(String typeName){
        logger.info("Entrée dans la fonction getTypeByName");
        return this.repo.findTypeByName(typeName);
    }
}
