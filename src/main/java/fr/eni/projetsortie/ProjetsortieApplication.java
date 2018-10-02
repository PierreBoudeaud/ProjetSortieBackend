package fr.eni.projetsortie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ProjetsortieApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetsortieApplication.class, args);
	}
}
