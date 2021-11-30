package fastfoodbackend.fastfoodbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.concurrent.ExecutionException;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class FastfoodbackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastfoodbackendApplication.class, args);

        try {
            System.out.println("=========== Start FastFoodSoftware ===========");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
