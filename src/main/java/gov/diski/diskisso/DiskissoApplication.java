package gov.diski.diskisso;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
public class DiskissoApplication extends SpringBootServletInitializer implements WebApplicationInitializer {
	
    public static void main(String[] args) {
        SpringApplication.run(DiskissoApplication.class, args);
    }
    
    @PostConstruct
    public void init(){
    	
    }
}
