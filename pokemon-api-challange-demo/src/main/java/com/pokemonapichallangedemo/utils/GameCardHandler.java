package com.pokemonapichallangedemo.utils;

import java.util.List;
import java.util.Random;

import com.pokemonapichallangedemo.entity.GameCard;
import com.pokemonapichallangedemo.entity.Pokemon;

public class GameCardHandler {

    private static String CORRECT_ANSWER="";
    
/**
 * this method will generate a game card by getting a random pokemon by calling getRandomPokemon(),
 * adding the random pokemon name to options and delete its name so its will not bee visible in the front end
 * and generate 2 more options by calling addrandomOptions() method
 * @param pokemons the list taht hold all pokemons
 * @return
 */
public static GameCard GenerateGamecard (List<Pokemon> pokemons){
    GameCard gameCard=new GameCard();
    Pokemon randPokemon=getRandomPokemon(pokemons);
    //store the correct answer
    CORRECT_ANSWER=randPokemon.getName();
    System.out.println(CORRECT_ANSWER);
    //delete the name of the pokemon from the pokemon 
    //and add the correct answer to options  
    randPokemon.setName("");
    gameCard.setPokemon(randPokemon);
    gameCard.addOption(CORRECT_ANSWER);
    //change the pokemon disc by deleting the pokemon name 
    changePokemondisc(randPokemon);
    //add the rest of options randomly
    addrandomOptions(gameCard,pokemons);
    return gameCard;

}

private static void changePokemondisc(Pokemon randPokemon) {
    String input=randPokemon.getDescription();
    int i = input.indexOf(' ');
    String rest = input.substring(i+1);
    rest=capitalizeFirstLetter(rest);
    randPokemon.setDescription(rest);

}

private static void addrandomOptions(GameCard gameCard, List<Pokemon> pokemons) {
    while(gameCard.getOptions().size()<3){
        int randomIndex= new Random().nextInt(pokemons.size()); 
        String randomName=pokemons.get(randomIndex).getName();
        gameCard.addOption(randomName);
    }

}

private static Pokemon getRandomPokemon (List<Pokemon> pokemons){
    int randomPokemonId=new Random().nextInt(pokemons.size());
    Pokemon randPokemon = pokemons.get(randomPokemonId);
    return randPokemon!=null ? randPokemon: null;
}

public static String capitalizeFirstLetter(String name) {
    String firstLetter = name.substring(0, 1).toUpperCase();
    String nameRest = name.substring(1).toLowerCase();
    return firstLetter + nameRest;

}

public static boolean checkAnswer (String answer){
    System.out.println("The correct answer is "+CORRECT_ANSWER+" the input answer is "+answer);
    return answer.toLowerCase().equals(CORRECT_ANSWER.toLowerCase())? true:false;
}

}
