package com.example.banco.controller;

import java.util.Currency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.banco.dto.EnableCardDTO;
import com.example.banco.entity.Card;
import com.example.banco.repository.CardRepository;

@RestController
public class CardController {
    private static final Logger log = LoggerFactory.getLogger(CardController.class);

    private final CardRepository repository;

    public CardController(CardRepository repository) {
        this.repository = repository;
    }
    
    
    @GetMapping("/card/{productId}/number")
    String generateNumber(@PathVariable Long productId) {
        return productId+String.valueOf(Math.random());
    }



    @PostMapping("/card/enroll")
    Card enableCard(@RequestBody EnableCardDTO dto){
        Card card = new Card();
        card.setAmount(1000000d);
        card.setIdCustomer(10);
        card.setOwner("Prueba de customer");
        card.setNumber(Integer.valueOf(dto.getCardId()));
        repository.save(card);
        return card;
    }
    

    @DeleteMapping("/card/{cardid}")
    String bockCard(@PathVariable Integer cardid){
        log.info("Entrando en bockCard");
        Card cardToDelete = repository.findByNumber(cardid);
        repository.delete(cardToDelete);
        for(Card card: repository.findAll()){
            log.info(card.toString());
        }
        return String.valueOf(cardid);
    }

}
