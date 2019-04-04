/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.domain.repository;

import java.util.List;
import projetJEE.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
    @Query("SELECT ua FROM UserAccount ua WHERE ua.email = :email AND ua.password = :password AND ua.isRemoved = 0")
    public List<UserAccount> findByLoginPass(@Param("email") String email, @Param("password") String password);

    @Query("SELECT ua FROM UserAccount ua WHERE ua.email = :email")
    public List<UserAccount> findByEmail(@Param("email") String email);
}
