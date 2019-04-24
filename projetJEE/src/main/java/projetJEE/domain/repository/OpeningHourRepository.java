/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.domain.repository;

import java.util.List;
import projetJEE.models.OpeningHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OpeningHourRepository extends JpaRepository<OpeningHour, Integer> {
    @Query("SELECT oh FROM OpeningHour oh WHERE oh.store.ID = :idStore")
    public List<OpeningHour> findByIdStore(@Param("idStore") int idStore);
}
