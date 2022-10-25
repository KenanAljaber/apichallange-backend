package com.pokemonapichallangedemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.pokemonapichallangedemo.entity.Pokemon;
import com.pokemonapichallangedemo.service.MyService;

@RequestMapping("/api-kruger/pokemon")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PokemonController {
    @Autowired
    private MyService service;

    /*
     * This end point will return all pokemons in the database
     */
    @GetMapping(value = "/all", produces="application/json")
    public ResponseEntity<String> allPokemons() {
        
        List<Pokemon> p = service.getAllPokemons();
        System.out.println("<<<<< pokemon/all HAS BEN CALLD >>>>");
        String jsonstr = "";
        if (p.size() > 0) {
            try {
                jsonstr = new ObjectMapper().writeValueAsString(p);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return ResponseEntity.internalServerError().build();

            }
            System.out.println("JSON SENT ====> "+jsonstr);
            return ResponseEntity.ok(jsonstr);

        } else {
            return ResponseEntity.notFound().build();
        }

    }
    /*
     * This end point will return the pokemon with the provided id
     */
    @GetMapping(value = "/{id}", produces="application/json")
    public ResponseEntity<String> pokemonById(@PathVariable("id") int id) {
        Pokemon pokemon = service.findPokemonById(id);
        String jsonStr ="";
        if (pokemon != null) {
            try {
                 jsonStr = new ObjectMapper().writeValueAsString(pokemon);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return  ResponseEntity.internalServerError().build();
            }
            
            return ResponseEntity.ok(jsonStr);
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/welcome")
    public String test (){
        return "wlecome";
    }

}
