package fr.eni.projetsortie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@EnableGlobalMethodSecurity()
@EnableSwagger2
public class ProjetsortieApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetsortieApplication.class, args);
	}
}
