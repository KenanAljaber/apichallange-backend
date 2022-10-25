package com.pokemonapichallangedemo.database;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.pokemonapichallangedemo.entity.Ability;
import com.pokemonapichallangedemo.entity.Pokemon;
import com.pokemonapichallangedemo.entity.Type;
import com.pokemonapichallangedemo.service.MyService;



@Configuration
public class FilesLoader {

    @Autowired
    private MyService service;
    private Logger myLogger =LoggerFactory.getLogger(FilesLoader.class);
    /*
     * this class is used to fill the database with all pokemons info provided in
     * the api
     * this class will be called to check if db has already data or not
     * then it will load all the data from the api to the database
     *
     */

    @Bean
    public InitializingBean init() {
        return () -> {
            Long dbEntriesCount=service.countPokemons();
            if(dbEntriesCount==0){
                myLogger.debug("Populating  db with data");
                loadPokemonsIntoDB();
            }else{
                System.out.println("======================================================================Database has already data");
                
                myLogger.debug("======================================================================Database has already data");
            }
            

        };
    }


    /*
     * The method will fetch all the urls from the api provided by Kruger Team
     * for each api will invoke createPokemon method internaly
     */
    public void loadPokemonsIntoDB() {
        // The given Api
        String api = "https://pokeapi.co/api/v2/pokemon";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(api, String.class);
        // Convert the result to JSON Object
        JSONObject json = new JSONObject(result);
        try {
            // Iterate through results array to get URLS
            json.getJSONArray("results").iterator().forEachRemaining(current -> {
                JSONObject currentJosn = new JSONObject(current.toString());
                // System.out.println(currentJosn.getString("url"));
                callPokemonUrl(currentJosn.getString("url"));
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * This method will create a list of abilites out of the json array
     * 
     * @param jsonArray
     * @param properteyName
     * @param key
     * @return a list of all values with the key provided on the JSONArray
     */
    private Set<Ability> getAbilitiesList(JSONArray jsonArray) {
        Set<Ability> items = new HashSet<>();
        jsonArray.forEach(it -> {
            JSONObject currentItem = new JSONObject(it.toString()).getJSONObject("ability");
            String abilityStr = currentItem.getString("name");
            // items.add(new Ability(abilityStr));

            /// check if ability is already added to the database so we dont add it again
            Ability abilityInDb = service.findAbilityByAbility(abilityStr);
            if (abilityInDb == null) {

                items.add(new Ability(abilityStr));
            } else {
                // add the pair ability_id,pokeomn_id to the table ability_pokemon
                items.add(abilityInDb);

            }

        });
        return items;
    }

    private Set<Type> getTypesList(JSONArray jsonArray) {
        Set<Type> items = new HashSet<>();
        jsonArray.forEach(it -> {
            JSONObject currentItem = new JSONObject(it.toString()).getJSONObject("type");

            // check if type is already added to the database so we dont add it again
            String typeStr = currentItem.getString("name");
            Type typeInDb = service.findTypeByType(typeStr);
            if (typeInDb == null) {
                items.add(new Type(typeStr));
            } else {
                items.add(typeInDb);
            }

        });
        return items;
    }

    /**
     * This mrthod will call the pokemon url provided and parse the results into a
     * josn object
     * call create pokemon to extract all the required info
     * and save the pokemon
     * 
     * @param pokemonUrl the url that leads to the pokemon data
     */
    private void callPokemonUrl(String pokemonUrl) {
        RestTemplate restTemplate = new RestTemplate();
        // Get the pkemon url response
        String result = restTemplate.getForObject(pokemonUrl, String.class);
        if (result != null && !result.isEmpty()) {
            // convert the api call result to a json object
            JSONObject jsonUrlResult = new JSONObject(result);
            // Create pokemon object
            Pokemon pokemon = createPokemon(jsonUrlResult);
            // save the pokemon to the db

            service.savePokemon(pokemon);
            System.out.println(pokemon.toString());

        }
    }

    private Pokemon createPokemon(JSONObject jsonUrlResult) {
        Pokemon pokemon = new Pokemon();
        // extract the JSON arrays we want to use from the JSON object
        JSONArray abilitiesJsonArray = jsonUrlResult.getJSONArray("abilities");
        JSONArray typesJsonArray = jsonUrlResult.getJSONArray("types");
        String sprite = jsonUrlResult.getJSONObject("sprites").getString("front_default");

        // Setting all the attribuites to the pokemon object
        pokemon.setAbilities(getAbilitiesList(abilitiesJsonArray));
        pokemon.setTypes(getTypesList(typesJsonArray));
        String name = jsonUrlResult.getString("name");
        pokemon.setName(capitalizeFirstLetter(name));

        pokemon.setWeight(jsonUrlResult.getInt("weight"));
        pokemon.setHeight(jsonUrlResult.getInt("height"));
        pokemon.setPhoto(sprite);

        descriptionGenerator(pokemon);

        return pokemon;
    }

    private String capitalizeFirstLetter(String name) {
        String firstLetter = name.substring(0, 1).toUpperCase();
        String nameRest = name.substring(1).toLowerCase();
        return firstLetter + nameRest;

    }

    private void descriptionGenerator(Pokemon pokemon) {

        String disc = String.format(
            
                "%s es un pokemon que cuenta con %s habilidades, su peso es %s y su altura es %s  ",
                pokemon.getName(), pokemon.getAbilities().size(), pokemon.getWeight(), pokemon.getHeight());
        pokemon.setDescription(disc);

    }

}
