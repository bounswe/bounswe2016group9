package boun.cmpe451.group9;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class BackendApplication {

	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/").allowedOrigins("http://52.67.44.90:8080");
			}
		};
	}

	@Bean(destroyMethod = "")
    public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf){
        return hemf.getSessionFactory();
    }


	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
}
