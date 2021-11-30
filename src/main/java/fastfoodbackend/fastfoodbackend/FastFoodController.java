package fastfoodbackend.fastfoodbackend;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/restaurant")
public class FastFoodController {

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/test", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public void test() throws Exception{
        try {
            System.out.println("FastFoodController OK");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
