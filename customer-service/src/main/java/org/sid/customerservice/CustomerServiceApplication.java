package org.sid.customerservice;

import org.sid.customerservice.config.CustomerConfigParams;
import org.sid.customerservice.entities.Customer;
import org.sid.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
    //chaque methode qui utilise  annotation bean cest une methode qui va s'executer au demarrage c-a-d il va retourner un boject de type comandelinnerunner
    @Bean
    public CommandLineRunner commandLineRunner(CustomerRepository customerRepository,
                                               RepositoryRestConfiguration restConfiguration)
    {
        return  args -> {
            //permet d'exposer id de micro service qui est generer imlecitement par spring-data-rest
            restConfiguration.exposeIdsFor(Customer.class);
            customerRepository.saveAll(
                    List.of(
                            Customer.builder().name("youssef").email("youssef@gmail.com").build(),
                            Customer.builder().name("walid").email("walid@gmail.com").build(),
                            Customer.builder().name("ahmed").email("ahmedmp@gmail.com").build()
                    )
            );
            customerRepository.findAll().forEach(
                    customer -> System.out.println(customer)
            );


        };

    }
}
