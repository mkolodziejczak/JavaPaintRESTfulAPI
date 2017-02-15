package pl.edu.uam.restapi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
@EnableSwagger2
public class RestApiApp {
    public static void main(String[] args) {
        SpringApplication.run(RestApiApp.class, args);
    }
}

