/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.bl.concrete;

import java.util.List;
import projetJEE.models.UserAccount;
import projetJEE.domain.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAccountManager {
    
    private UserAccountRepository repo;
    
    @Autowired
    public UserAccountManager(UserAccountRepository uarepo) {
        this.repo = uarepo;
    }
    
    public UserAccount getUserAccountById(int id) {
        return this.repo.findById(id).get();
    }
    
    public List<UserAccount> getAllUsers(){
        return this.repo.findAll();
    }
    
    public boolean validateLogin(String login,String password){
        return !this.repo.findByLoginPass(login, password).isEmpty();
    }
    
    public void addUserAccount(UserAccount account){
        this.repo.save(account);
        this.repo.flush();
    }
}
