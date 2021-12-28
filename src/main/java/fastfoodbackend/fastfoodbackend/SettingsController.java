package fastfoodbackend.fastfoodbackend;

import fastfoodbackend.fastfoodbackend.Models.Invoice;
import fastfoodbackend.fastfoodbackend.Models.Settings;
import fastfoodbackend.fastfoodbackend.Service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

//    @ResponseStatus(value = HttpStatus.OK)
//    @RequestMapping(value="/getAllInvoice", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
//    public List<Invoice> getAllInvoice(@RequestHeader (value="companyId") String companyId, HttpServletRequest httpServletRequest) throws Exception{
//        List<Invoice> invoiceList = null;
//
//        try {
//            invoiceList = invoiceService.getAllInvoice();
//
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return invoiceList.stream().filter(o -> o.getCompanyId() == Integer.parseInt(companyId)).collect(Collectors.toList());
//    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/saveLimitInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public boolean saveLimitInfo(@RequestBody Settings settings , HttpServletRequest httpServletRequest) throws Exception{
        boolean flag = false;
        Specification<Settings> settSpec;
        List<Settings> settingsList;

        try {
            settSpec = settingsService.getSettingsByCompanyId(settings.getCompanyId());
            settingsList = settingsService.getInvItemBySpec(settSpec);

            settingsList.get(0).setLimitGr(settings.getLimitGr());
            settingsList.get(0).setLimitPiece(settings.getLimitPiece());


            settingsService.saveSettings(settingsList.get(0));

            flag = true;

        } catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/saveShiftInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public boolean saveShiftInfo(@RequestBody Settings settings , HttpServletRequest httpServletRequest) throws Exception{
        boolean flag = false;
        Specification<Settings> settSpec;
        List<Settings> settingsList;

        try {
            settSpec = settingsService.getSettingsByCompanyId(settings.getCompanyId());
            settingsList = settingsService.getInvItemBySpec(settSpec);

            settingsList.get(0).setFirstShiftStart(settings.getFirstShiftStart());
            settingsList.get(0).setSecondShiftStart(settings.getSecondShiftStart());


            settingsService.saveSettings(settingsList.get(0));

            flag = true;

        } catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/getSettingsInfo", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Settings> getSettingsInfo(@RequestHeader (value="companyId") String CompanyId, HttpServletRequest httpServletRequest) throws Exception{
        Specification<Settings> settSpec;
        List<Settings> settingsList = null;

        try {
            settSpec = settingsService.getSettingsByCompanyId(Integer.valueOf(CompanyId));
            settingsList = settingsService.getInvItemBySpec(settSpec);


        } catch (Exception e){
            e.printStackTrace();
        }

        return settingsList;
    }



}
