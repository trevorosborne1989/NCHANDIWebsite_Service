package gov.srs.spring.services.NCHANDIWebsite_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"gov.srs"})
@EnableAutoConfiguration
public class NCHANDIWebsite_ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NCHANDIWebsite_ServiceApplication.class, args);
	}
}
