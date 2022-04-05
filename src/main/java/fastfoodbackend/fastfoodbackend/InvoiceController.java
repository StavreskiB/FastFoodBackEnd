package fastfoodbackend.fastfoodbackend;

import fastfoodbackend.fastfoodbackend.Class.InvoiceItem;
import fastfoodbackend.fastfoodbackend.Models.*;
import fastfoodbackend.fastfoodbackend.Service.InvoiceService;
import fastfoodbackend.fastfoodbackend.Service.ProductService;
import fastfoodbackend.fastfoodbackend.Service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private ProductService productService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private SettingsService settingsService;

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/saveNewInvoice", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Boolean saveNewInvoice(@RequestBody Invoice invoice, HttpServletRequest httpServletRequest) throws Exception{

        Boolean flag = false;
        try {
            invoiceService.saveNewInvoice(invoice);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/getAllInvoice", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Invoice> getAllInvoice(@RequestHeader (value="companyId") String companyId, HttpServletRequest httpServletRequest) throws Exception{
        List<Invoice> invoiceList = null;

        try {
            invoiceList = invoiceService.getAllInvoice();

        } catch (Exception e){
            e.printStackTrace();
        }

        return invoiceList.stream().filter(o -> o.getCompanyId() == Integer.parseInt(companyId)).collect(Collectors.toList());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/getLimitInvoice", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Invoice> getLimitInvoice(@RequestHeader (value="companyId") String companyId, HttpServletRequest httpServletRequest) throws Exception{
        Specification<Invoice> invSpec;
        List<Invoice> invoiceList = null;
        Specification<Settings> settSpec;
        List<Settings> settList;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Using today's date

        try {
//            settSpec = settingsService.getSettingsByCompanyId(Integer.valueOf(companyId));
//            settList = settingsService.getInvItemBySpec(settSpec);
//            c.add(Calendar.DATE, settList.get(0).getLimitDays()); // Adding 5 days
//
//            String output = sdf.format(c.getTime());
//            invSpec = invoiceService.getInvByDate(Integer.valueOf(companyId));
//            invoiceList = invoiceService.getInvBySpec(invSpec);

        } catch (Exception e){
            e.printStackTrace();
        }

        return invoiceList;
    }


    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/saveInvoiceItem", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Boolean saveInvoiceItem(@RequestBody InvoiceItem invoiceItem, HttpServletRequest httpServletRequest) throws Exception{
        Boolean flag = false;
        Specification<Product> prodSpec;
        List<Product> productList;
        Specification<Invoice> invSpec;
        List<Invoice> invoiceList;

        Specification<Stock> stockSpecification;
        List<Stock> stockList;

        fastfoodbackend.fastfoodbackend.Models.InvoiceItem invoiceItem1;
        try {
            invoiceItem1 = new fastfoodbackend.fastfoodbackend.Models.InvoiceItem();

            prodSpec = productService.getProductById(invoiceItem.getProductId());
            productList = productService.getAllProduct(prodSpec);

            invSpec = invoiceService.getInvoiceById(invoiceItem.getInvoiceName());
            invoiceList = invoiceService.getInvBySpec(invSpec);

            stockSpecification = productService.getStockByProductId(invoiceItem.getProductId());
            stockList = productService.getAllStock(stockSpecification);

            invoiceItem1.setCompanyId(invoiceItem.getCompanyId());
            invoiceItem1.setIdInvoice(invoiceList.get(0));
            invoiceItem1.setIdProduct(productList.get(0));
            invoiceItem1.setPrice(invoiceItem.getPrice());
            invoiceItem1.setQuantity(invoiceItem.getQuantity());

            invoiceService.saveNewInvoiceItem(invoiceItem1);

            stockList.get(0).setQuantity(stockList.get(0).getQuantity() + invoiceItem.getQuantity());

            productService.saveNewStock(stockList.get(0));

            flag = true;

        } catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/getItemsByInvoiceId", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<fastfoodbackend.fastfoodbackend.Models.InvoiceItem> getItemsByInvoiceId(@RequestHeader (value="invoiceName") String invoiceName, HttpServletRequest httpServletRequest) throws Exception{

        Specification<fastfoodbackend.fastfoodbackend.Models.InvoiceItem> spec;
        List<fastfoodbackend.fastfoodbackend.Models.InvoiceItem> itemList = null;

        Specification<Invoice> invSpec;
        List<Invoice> invoiceList;

        try {
            invSpec = invoiceService.getInvoiceById(Integer.valueOf(invoiceName));
            invoiceList = invoiceService.getInvBySpec(invSpec);

            spec = invoiceService.getInvoiceItemsById(Integer.valueOf(invoiceList.get(0).getIdInvoice()));
            itemList = invoiceService.getInvItemBySpec(spec);

        } catch (Exception e){
            e.printStackTrace();
        }

        return itemList;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/getInvoiceByName", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Invoice> getInvoiceByName(@RequestHeader (value="invoiceName") String invoiceName, HttpServletRequest httpServletRequest) throws Exception{


        Specification<Invoice> invSpec;
        List<Invoice> invoiceList = null;

        try {
            invSpec = invoiceService.getInvoiceById(Integer.valueOf(invoiceName));
            invoiceList = invoiceService.getInvBySpec(invSpec);

        } catch (Exception e){
            e.printStackTrace();
        }

        return invoiceList;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/changeInvoiceStatus", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Boolean changeInvoiceStatus(@RequestHeader (value="invoiceName") String invoiceName, HttpServletRequest httpServletRequest) throws Exception{


        Specification<Invoice> invSpec;
        List<Invoice> invoiceList = null;
        Boolean flag = false;

        try {
            invSpec = invoiceService.getInvoiceById(Integer.valueOf(invoiceName));
            invoiceList = invoiceService.getInvBySpec(invSpec);

            invoiceList.get(0).setStatus("Платена");


            invoiceService.saveNewInvoice(invoiceList.get(0));

            flag = true;


        } catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }


}
