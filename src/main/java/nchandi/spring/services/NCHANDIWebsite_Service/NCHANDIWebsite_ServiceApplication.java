package nchandi.spring.services.NCHANDIWebsite_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan({"nchandi"})
@EnableAutoConfiguration
@EnableScheduling
public class NCHANDIWebsite_ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NCHANDIWebsite_ServiceApplication.class, args);
	}
}
