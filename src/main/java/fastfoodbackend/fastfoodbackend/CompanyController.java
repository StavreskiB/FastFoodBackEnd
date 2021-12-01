package fastfoodbackend.fastfoodbackend;

import fastfoodbackend.fastfoodbackend.Models.Company;
import fastfoodbackend.fastfoodbackend.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/getCompanyById", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Company getCompany(@RequestHeader (value="companyId") Integer companyId,
                              HttpServletRequest httpServletRequest) throws Exception{
        Specification<Company> spec;
        List<Company> companyList = null;

        try {
            spec = companyService.getCompanyById(String.valueOf(companyId));
            companyList = companyService.getCompanyBySpec(spec);
        } catch (Exception e){
            e.printStackTrace();
        }

        return companyList.get(0);
    }
}
