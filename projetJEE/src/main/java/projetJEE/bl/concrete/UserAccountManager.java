/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.bl.concrete;

import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import projetJEE.models.UserAccount;
import projetJEE.domain.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAccountManager {
    
    private static final Logger logger = Logger.getLogger(UserAccountManager.class);
    private UserAccountRepository repo;
    
    @Autowired
    public UserAccountManager(UserAccountRepository uarepo) {
        this.repo = uarepo;
    }
    
    public UserAccount getUserAccountById(int id) {
        logger.info("Entrée dans la fonction getUserAccountById");
        return this.repo.findById(id).get();
    }
    
    public boolean userAccountExists(String email){
        logger.info("Entrée dans la fonction userAccountExists");  
        return !this.repo.findByEmail(email).isEmpty();
    }
    
    public List<UserAccount> getAllUsers(){
        logger.info("Entrée dans la fonction getAllUsers");
        return this.repo.findAll();
    }
    
    public boolean validateLogin(String login,String password){
        logger.info("Entrée dans la fonction validateLogin");
        logger.info(DigestUtils.sha256Hex("password"));
        return !this.repo.findByLoginPass(login, DigestUtils.sha256Hex(password)).isEmpty();
    }
    
    public void addUserAccount(UserAccount account){
        logger.info("Entrée dans la fonction addUserAccount");
        this.repo.save(account);
        this.repo.flush();
    }

}
