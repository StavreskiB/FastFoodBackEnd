package fastfoodbackend.fastfoodbackend;

import fastfoodbackend.fastfoodbackend.Class.Reports;
import fastfoodbackend.fastfoodbackend.Models.Bills;
import fastfoodbackend.fastfoodbackend.Models.Company;
import fastfoodbackend.fastfoodbackend.Service.BillsService;
import fastfoodbackend.fastfoodbackend.Service.ProductService;
import fastfoodbackend.fastfoodbackend.Service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/management")
public class ManagementController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BillsService billsService;


    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/getReports", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Reports> getReports(@RequestHeader (value="companyId") Integer companyId,
                                  HttpServletRequest httpServletRequest) throws Exception{
        Specification<Bills> spec;
        Reports reports = new Reports();
        List<Bills> billsList = null;
        List<Reports> reportsList = new ArrayList<Reports>();
        Double totalPrice = 0.0;

        String shiftStart = "";
        String shiftEnd = "";

        try {

           for(int i = 0; i < 5; i++) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDateTime now = LocalDateTime.now();
                    now = now.minusDays(i);

                    shiftStart = "00:00:01";
                    shiftEnd = "14:00:00";
                    spec = billsService.getBillsForReports(String.valueOf(companyId), dtf.format(now), shiftStart, shiftEnd);
                    billsList = billsService.getBillsBySpec(spec);

                    for (int j = 0; j < billsList.size(); j++) {
                        totalPrice += billsList.get(j).getQuantity() * billsList.get(j).getIdProduct().getPrice();
                    }

                    reports = new Reports();
                   if(billsList.size() > 0) {
                       reports.setPrice(String.valueOf(totalPrice));
                       reports.setEmployee(billsList.get(0).getEmployee());
                       reports.setDate(dtf.format(now));
                       reports.setShift("14:00:00");

                       reportsList.add(reports);
                   }
                    billsList = null;
                    spec = null;

                    totalPrice = 0.0;
                    shiftStart = "14:00:01";
                    shiftEnd = "23:59:59";
                    spec = billsService.getBillsForReports(String.valueOf(companyId), dtf.format(now), shiftStart, shiftEnd);
                    billsList = billsService.getBillsBySpec(spec);


                    for (int j = 0; j < billsList.size(); j++) {
                        totalPrice += billsList.get(j).getQuantity() * billsList.get(j).getIdProduct().getPrice();
                    }

                    reports = new Reports();

                    if(billsList.size() > 0) {
                        reports.setPrice(String.valueOf(totalPrice));
                        reports.setEmployee(billsList.get(0).getEmployee());
                        reports.setDate(dtf.format(now));
                        reports.setShift("23:59:59");
                        reportsList.add(reports);
                    }
           }
        } catch (Exception e){
            e.printStackTrace();
        }
        return reportsList;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/getItemForExpPanel", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Bills> getItemForExpPanel(@RequestHeader (value="companyId") String companyId,
                                    @RequestHeader (value="dateForReport") String dateForReport,
                                    @RequestHeader (value="shift") String shift,
                                    HttpServletRequest httpServletRequest) throws Exception{
        Specification<Bills> spec;
        Reports reports = new Reports();
        List<Bills> billsList = null;
        List<Bills> filteredList = null;
        String shiftStart = "";
        try {
            if(shift == "23:59:59") {
                 shiftStart = "14:00:00";
            } else {
                 shiftStart = "00:00:01";
            }

            spec = billsService.getBillsForReports(String.valueOf(companyId), dateForReport, shiftStart, shift);
            billsList = billsService.getBillsBySpec(spec);


        } catch (Exception e){
            e.printStackTrace();
        }


        return billsList;
    }




}
