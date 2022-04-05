package fastfoodbackend.fastfoodbackend;

import fastfoodbackend.fastfoodbackend.Models.*;
import fastfoodbackend.fastfoodbackend.Service.BillsService;
import fastfoodbackend.fastfoodbackend.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/restaurant")
public class FastFoodController {

    @Autowired
    private BillsService billsService;

    @Autowired
    private ProductService productService;

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/saveForMark", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Boolean saveForMark(@RequestHeader (value="table") String table,
                                    @RequestHeader (value="productName") String productName,
                                    @RequestHeader (value="quantity") String quantity,
                                    @RequestHeader (value="description") String description,
                                    @RequestHeader (value="addons") String addons,
                                    @RequestHeader (value="companyId") String companyId,
                                    @RequestHeader (value="userInsert") String userInsert,
                                    @RequestHeader (value="billsType") String billsType,
                                    @RequestHeader (value="billsStatus") String billsStatus, HttpServletRequest httpServletRequest) throws Exception{
        Boolean flag = true;
        Bills bills = new Bills();
        Specification<Product> prodSpec;
        List<Product> prodList;
        Specification<OrderType> otSpec;
        List<OrderType> otList;
        Specification<OrderStatus> osSpec;
        List<OrderStatus> osList;
        Specification<Stock> stockSpec;
        List<Stock> stockList;
        Specification<Norms> normsSpec;
        List<Norms> normsList;
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        try {


            prodSpec = productService.getAllProductByName(productName);
            prodList = productService.getAllProduct(prodSpec);

            osSpec = billsService.getOsByNameSpec(billsStatus);
            osList = billsService.getOsByName(osSpec);

            otSpec = billsService.getOtByNameSpec(billsType);
            otList = billsService.getOtByName(otSpec);


           // billsSpec = productService.getBillsForUpdate(Integer.parseInt(companyId), table, prodList.get(0).getIdProduct());
           // billsList = billsService.getBillsBySpec(billsSpec);

//            if(billsList.size() > 0) {
//                billsList.get(0).setQuantity(billsList.get(0).getQuantity() + Double.valueOf(quantity));
//                billsService.saveBills(billsList.get(0));
//            } else {
                bills = new Bills();
                bills.setTables(table);
                bills.setQuantity(Double.valueOf(quantity));
                bills.setAddons(addons);
                bills.setAddress(description);
                bills.setCompanyId(Integer.parseInt(companyId));
                bills.setEmployee(userInsert);
                bills.setIdProduct(prodList.get(0));
                bills.setIdOrderStatus(osList.get(0));
                bills.setIdOrderType(otList.get(0));
                bills.setTimeInser(String.valueOf(localTime));
                bills.setDateInsert(String.valueOf(localDate));
                billsService.saveBills(bills);
//            }

            stockSpec = productService.getStockByProductId(prodList.get(0).getIdProduct());
            stockList = productService.getAllStock(stockSpec);
            stockList.get(0).setQuantity(stockList.get(0).getQuantity() - Double.valueOf(quantity));
            productService.saveNewStock(stockList.get(0));

            normsSpec = productService.getNormsByProductIdN(prodList.get(0).getIdProduct());
            normsList = productService.getNormsBySpec(normsSpec);


            for(int i = 0; i < normsList.size(); i++){
               stockList.clear();
               stockSpec = productService.getStockByProductId(normsList.get(i).getIdProduct().getIdProduct());
               stockList = productService.getAllStock(stockSpec);

               stockList.get(0).setQuantity(stockList.get(0).getQuantity() - (Double.valueOf(quantity) * normsList.get(i).getQuantity()));
               productService.saveNewStock(stockList.get(0));
            }


        } catch (Exception e){
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/getBillsForMark", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Bills> getBillsForMark(@RequestHeader (value="table") String table,
                                       @RequestHeader (value="companyId") String companyId,
                                       @RequestHeader (value="billsType") String billsType,
                                       @RequestHeader (value="billsStatus") String billsStatus,
                                       HttpServletRequest httpServletRequest) throws Exception{

        Specification<Bills> billsSpec;
        List<Bills> billsList = null;
        Specification<OrderType> otSpec;
        List<OrderType> otList;

        try {

            otSpec = billsService.getOtByNameSpec(billsType);
            otList = billsService.getOtByName(otSpec);

            billsSpec = billsService.getBillsForMark(table, Integer.parseInt(companyId), Integer.parseInt(billsStatus), otList.get(0).getIdOrderType());
            billsList = billsService.getBillsBySpec(billsSpec);


        } catch (Exception e){
            e.printStackTrace();
        }
        return billsList;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/deleteMark", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Boolean deleteMark(@RequestHeader (value="idBills") String idBills,
                                       HttpServletRequest httpServletRequest) throws Exception{

        Specification<Bills> billsSpec;
        List<Bills> billsList = null;
        Boolean flag = false;
        try {

            billsSpec = billsService.getBillsById(Integer.parseInt(idBills));
            billsList = billsService.getBillsBySpec(billsSpec);

            billsService.deleteBills(billsList.get(0));

            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }


//    @ResponseStatus(value = HttpStatus.OK)
//    @RequestMapping(value="/convertToBills", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
//    public Boolean convertToBills(@RequestHeader (value="table") String TableName,
//                                  @RequestHeader (value="companyId") String companyId,
//                              HttpServletRequest httpServletRequest) throws Exception{
//
//        Specification<Bills> billsSpec;
//        List<Bills> billsList = null;
//        Specification<Bills> checkIfSpec;
//        List<Bills> checkIfList = null;
//        Specification<Bills> delSpec;
//        OrderStatus orderStatus = new OrderStatus();
//        Bills bills = new Bills();
//        Boolean flag = false;
//        try {
//
//            orderStatus.setIdOrderStatus(2);
//            orderStatus.setName("Smetka");
//
//            billsSpec = billsService.convertMarkToBills(TableName, Integer.parseInt(companyId));
//            billsList = billsService.getBillsBySpec(billsSpec);
//
//
//            for(int i = 0; i < billsList.size(); i++) {
//                billsList.get(i).setIdOrderStatus(orderStatus);
//                billsService.saveBills(billsList.get(i));
//            }
////            for(int i = 0; i < billsList.size(); i++){
////
//////
////                checkIfSpec = billsService.checkForExist(billsList.get(i).getIdProduct().getIdProduct(), Integer.parseInt(companyId), TableName);
////                checkIfList = billsService.getBillsBySpec(checkIfSpec);
//
//                //if(checkIfList.size() > 0){
//
//                    //billsService.deleteBillsByID(checkIfList.get(0));
////                    billsList.get(i).setIdOrderStatus(orderStatus);
////                    checkIfList.get(0).setQuantity(checkIfList.get(0).getQuantity() + billsList.get(i).getQuantity());
////                    checkIfList.get(0).setIdOrderStatus(orderStatus);
////                    checkIfList.get(0).setIdBills(checkIfList.get(0).getIdBills());
//
//                //    billsService.saveBills(checkIfList.get(0));
////                } else {
////                    billsList.get(i).setIdOrderStatus(orderStatus);
////
////                    billsService.saveBills(billsList.get(i));
////                }
//
//
//            flag = true;
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        return flag;
//    }


    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/convertToBills", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Boolean convertToBills(@RequestHeader (value="table") String TableName,
                                  @RequestHeader (value="companyId") String companyId,
                                  HttpServletRequest httpServletRequest) throws Exception{

        Specification<Bills> billsSpec;
        List<Bills> billsList = null;
        Specification<Bills> checkIfSpec;
        List<Bills> checkIfList = null;
        Specification<Bills> delSpec;
        OrderStatus orderStatus = new OrderStatus();
        Bills bills = new Bills();
        Boolean flag = false;
        try {

            orderStatus.setIdOrderStatus(2);
            orderStatus.setName("Smetka");

            billsSpec = billsService.convertMarkToBills(TableName, Integer.parseInt(companyId));
            billsList = billsService.getBillsBySpec(billsSpec);


            for(int i = 0; i < billsList.size(); i++) {
//                checkIfSpec = billsService.checkForExist(billsList.get(i).getIdProduct().getIdProduct(), Integer.parseInt(companyId), TableName);
//                checkIfList = billsService.getBillsBySpec(checkIfSpec);
//
//                if(checkIfList.size() > 0){
//                    checkIfList.get(i).setIdOrderStatus(orderStatus);
//                    checkIfList.get(i).setQuantity(billsList.get(i).getQuantity() + checkIfList.get(i).getQuantity());
//                    billsService.saveBills(checkIfList.get(i));
//
//                } else {
//                    billsList.get(i).setIdOrderStatus(orderStatus);
                    billsList.get(i).setIdOrderStatus(orderStatus);
                    billsService.saveBills(billsList.get(i));
            //
                // }


            }
//            for(int i = 0; i < billsList.size(); i++){
//
////
//                checkIfSpec = billsService.checkForExist(billsList.get(i).getIdProduct().getIdProduct(), Integer.parseInt(companyId), TableName);
//                checkIfList = billsService.getBillsBySpec(checkIfSpec);

            //if(checkIfList.size() > 0){

            //billsService.deleteBillsByID(checkIfList.get(0));
//                    billsList.get(i).setIdOrderStatus(orderStatus);
//                    checkIfList.get(0).setQuantity(checkIfList.get(0).getQuantity() + billsList.get(i).getQuantity());
//                    checkIfList.get(0).setIdOrderStatus(orderStatus);
//                    checkIfList.get(0).setIdBills(checkIfList.get(0).getIdBills());

            //    billsService.saveBills(checkIfList.get(0));
//                } else {
//                    billsList.get(i).setIdOrderStatus(orderStatus);
//
//                    billsService.saveBills(billsList.get(i));
//                }


            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }


    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/getBillsItem", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Bills> getBillsItem(@RequestHeader (value="table") String TableName,
                                        @RequestHeader (value="companyId") String companyId,
                                        HttpServletRequest httpServletRequest) throws Exception{

        Specification<Bills> billsSpec;
        List<Bills> billsList = null;
        Boolean flag = false;
        try {

            billsSpec = billsService.getBillsItem(TableName, Integer.parseInt(companyId));

            billsList = billsService.getBillsBySpec(billsSpec);

        } catch (Exception e){

            e.printStackTrace();
        }
        return billsList;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/printBills", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Bills> printBills(@RequestHeader (value="table") String TableName,
                                    @RequestHeader (value="companyId") String companyId,
                                    HttpServletRequest httpServletRequest) throws Exception{

        Specification<Bills> billsSpec;
        List<Bills> billsList = null;
        Boolean flag = false;
        OrderStatus orderStatus = new OrderStatus();
        try {

            orderStatus.setIdOrderStatus(3);
            orderStatus.setName("Platena");

            billsSpec = billsService.getBillsItem(TableName, Integer.parseInt(companyId));

            billsList = billsService.getBillsBySpec(billsSpec);

            for(int i = 0 ; i < billsList.size(); i++){
                billsList.get(i).setIdOrderStatus(orderStatus);
                billsService.saveBills(billsList.get(0));
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return billsList;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/getReservedTable", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Bills> getReservedTable(@RequestHeader (value="companyId") String companyId,
                                        HttpServletRequest httpServletRequest) throws Exception{

        Specification<Bills> billsSpec;
        List<Bills> billsList = null;
        List<Bills> filtederList = null;

        try {

            billsSpec = billsService.getReservedBills(companyId);
            billsList = billsService.getBillsBySpec(billsSpec);


        } catch (Exception e){
            e.printStackTrace();
        }

        return billsList;
    }


}
