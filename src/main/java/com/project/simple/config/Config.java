package com.project.simple.config;


import com.project.simple.entities.Address;
import com.project.simple.entities.Client;
import com.project.simple.repositories.AddressRepository;
import com.project.simple.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Arrays;
import java.util.Date;

@Configuration
@EnableSwagger2
public class Config implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    //POPULANDO O BANCO
    public void run(String... args) throws Exception {
        Client client1 = new Client(null, "Luiz", new Date());
        Address ad1 = new Address(null, "Rua das conchas", 91561958, 100, "Guarujá", client1, 'n');
        Address ad2 = new Address(null, "Av dos peixes", 8676546, 101, "Mauá", client1, 'n');
        Address ad3 = new Address(null, "Viela da esquina", 8764535, 102, "Santo André", client1, 'n');
        Address ad4 = new Address(null, "Av senador flaquer", 456876, 103, "Sao Paulo", client1, 'n');
        Address ad5 = new Address(null, "Est das pitanguerias", 9145687, 104, "Santos", client1, 'y');

        clientRepository.save(client1);
        addressRepository.saveAll(Arrays.asList(ad1, ad2, ad3, ad4, ad5));

    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.project.simple.resources"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(buildApiInfo());

    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("Gerenciador de Pessoas")
                .description("Api do projeto que gerência pessoas")
                .version("0.1")
                .contact(new Contact("Guilherme",
                        "https://github.com/GuilhermeS369",
                        "malito:guilherme460@yahoo.com.br"))
                .build();

    }

}

