package com.pokemonapichallangedemo;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




/*"com.pokemonapichallangedemo.controller",
								"com.pokemonapichallangedemo.dao",
								"com.pokemonapichallangedemo.entity",
							"com.pokemonapichallangedemo.service",
						"com.pokemonapichallangedemo.configuration"*/



@SpringBootApplication(scanBasePackages = { "com.pokemonapichallangedemo" } )
public class PokemonApiChallangeDemoApplication { 


	

	public static void main(String[] args) {
		SpringApplication.run(PokemonApiChallangeDemoApplication.class, args);
		

	
	}

}
