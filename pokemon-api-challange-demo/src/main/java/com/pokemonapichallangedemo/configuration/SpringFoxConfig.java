package com.pokemonapichallangedemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;



@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.any())
                .paths(regex("/api.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact myContact=new Contact("Kenan Aljaber",
        "https://www.linkedin.com/in/kenan-aljaber-a232aa187/",
        "kenan.aljaber.p@gmail.com");
        
		return new ApiInfoBuilder().title("Kruger-Api")
				.description("This is the docs of Pokeomn Api Challange")
				.contact(myContact)
				.build();
	}


}
