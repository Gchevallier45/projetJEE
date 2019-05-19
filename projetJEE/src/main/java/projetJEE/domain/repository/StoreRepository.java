/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.domain.repository;

import java.util.List;
import projetJEE.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projetJEE.models.Store;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    @Query("SELECT s FROM Store s WHERE isClosed = false")
    public List<Store> getAllOpen();
    
    
    @Query("SELECT s FROM Store s WHERE isClosed = false AND Owner_id = id")
    public List<Store> getStoresManagerOfOwner(@Param("id") int id);
}
