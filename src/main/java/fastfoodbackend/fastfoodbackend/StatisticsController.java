package fastfoodbackend.fastfoodbackend;

import fastfoodbackend.fastfoodbackend.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/statistic")
public class StatisticsController {

    @Autowired
    private ProductService productService;

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/getAllProductType", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Boolean getAllProductTypeCompany(HttpServletRequest httpServletRequest) throws Exception{


        try {
        } catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

}
