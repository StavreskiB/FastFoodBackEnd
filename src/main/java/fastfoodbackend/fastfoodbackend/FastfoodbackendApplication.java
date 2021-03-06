package fastfoodbackend.fastfoodbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.concurrent.ExecutionException;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class FastfoodbackendApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(FastfoodbackendApplication.class, args);

        try {
            System.out.println("=========== Start FastFoodSoftware ===========");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
