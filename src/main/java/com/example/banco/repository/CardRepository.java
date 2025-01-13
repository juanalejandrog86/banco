package com.example.banco.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.banco.entity.Card;

public interface CardRepository extends CrudRepository<Card,Long>{

    @Query("SELECT c FROM Card c WHERE c.number = (:number)")    
    public Card findByNumber(@Param("number") Integer number);

}
