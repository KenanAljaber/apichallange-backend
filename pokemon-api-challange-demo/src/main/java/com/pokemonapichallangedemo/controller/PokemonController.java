package com.pokemonapichallangedemo.controller;

import java.util.List;
import java.util.Random;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemonapichallangedemo.entity.GameCard;
import com.pokemonapichallangedemo.entity.Pokemon;
import com.pokemonapichallangedemo.service.MyService;
import com.pokemonapichallangedemo.utils.GameCardHandler;

@RequestMapping("/api-kruger/pokemon")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PokemonController {
    @Autowired
     MyService service;

    /*
     * This end point will return all pokemons in the database
     */
    @GetMapping(value = "/all", produces="application/json")
    public ResponseEntity<String> allPokemons() {
        
        List<Pokemon> pokemonsList = service.getAllPokemons();
        System.out.println("<<<<< pokemon/all HAS BEN CALLD >>>>");
        
        String jsonstr = "";
        if (pokemonsList.size() > 0) {
            try {
                jsonstr = new ObjectMapper().writeValueAsString(pokemonsList);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return ResponseEntity.internalServerError().build();

            }
           // System.out.println("JSON SENT ====> "+jsonstr);
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

    @GetMapping(value="/game", produces = "application/json")
    public ResponseEntity<String> game (){
        List<Pokemon> pokemonList=service.getAllPokemons();
       System.out.println(pokemonList.size());
        GameCard gameCard= GameCardHandler.GenerateGamecard(pokemonList);

        String jsonStr="";
        if(gameCard!=null){
        try{
            jsonStr=new ObjectMapper().writeValueAsString(gameCard);
            return ResponseEntity.ok(jsonStr);
        }catch(JsonProcessingException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
        return ResponseEntity.notFound().build(); 

       
    }

   
    @GetMapping(value = "game/{answer}", produces = "application/json")
    public ResponseEntity<String> checkAnswer (@PathVariable("answer") String answer){
        boolean correct=GameCardHandler.checkAnswer(answer);
        String response= correct ? "CORRECT" : "WRONG";
        String jsonStr="";
        
        try{
            jsonStr=new ObjectMapper().writeValueAsString(response);
            return ResponseEntity.ok(jsonStr);
        }catch(JsonProcessingException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }   
    }
}
