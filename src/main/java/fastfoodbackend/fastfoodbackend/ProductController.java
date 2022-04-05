package fastfoodbackend.fastfoodbackend;

import fastfoodbackend.fastfoodbackend.Models.*;
import fastfoodbackend.fastfoodbackend.Service.ProductService;
import fastfoodbackend.fastfoodbackend.Service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SettingsService settingsService;

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/getAllProductType", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<ProductType> getAllProductTypeCompany(HttpServletRequest httpServletRequest) throws Exception{

        List<ProductType> productList = null;

        try {
            productList = productService.getAllProductType();
        } catch (Exception e){
            e.printStackTrace();
        }

        return productList;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/saveNewProduct", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Boolean saveNewProduct(@RequestBody Product product, @RequestHeader (value="quantity") String Quantity, HttpServletRequest httpServletRequest) throws Exception{
        Boolean flag = false;
        Specification <Product> spec;
        List<Product> productList;
        Stock stock = new Stock();

        try {

            spec = productService.getAllProductByName(product.getName());
            productList = productService.getAllProduct(spec);

            if(productList.size() > 0){
                flag = false;
            } else {
                product.setDateInsert(new Date());
                productService.saveNewProduct(product);

                stock.setCompanyId(product.getCompanyId());
                stock.setDateInsert(product.getDateInsert());
                stock.setIdInvoice(0);
                stock.setIdProduct(product);
                stock.setQuantity(Double.valueOf(Quantity));
                stock.setStatus(1);

                productService.saveNewStock(stock);
                flag = true;
           }

        } catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/getAllStockByCompanyId", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Stock> getAllStockByCompanyId(@RequestHeader (value="companyId") String CompanyId, HttpServletRequest httpServletRequest) throws Exception{

        Specification <Stock> specification;
        List<Stock> stockList = null;

        try {
            specification = productService.getAllStockBySpec(Integer.valueOf(CompanyId));
            stockList = productService.getAllStock(specification);
        } catch (Exception e){
            e.printStackTrace();
        }

        return stockList;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/getAllProductForSell", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Product> getAllProductForSell(@RequestHeader (value="companyId") String CompanyId, HttpServletRequest httpServletRequest) throws Exception{

        Specification <Product> specification;
        List<Product> productkList = null;

        try {
            specification = productService.getAllProductForSell(Integer.valueOf(CompanyId));
            productkList  = productService.getAllProductForSell(specification);
        } catch (Exception e){
            e.printStackTrace();
        }

        return productkList ;
    }


    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/getAllProductByCompanyId", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Product> getAllProductByCompanyId(@RequestHeader (value="companyId") String CompanyId, HttpServletRequest httpServletRequest) throws Exception{

        Specification <Product> specification;
        List<Product> stockList = null;

        try {
            specification = productService.getAllProductBySpec(Integer.valueOf(CompanyId));
            stockList = productService.getAllProduct(specification);
        } catch (Exception e){
            e.printStackTrace();
        }

        return stockList.stream().filter(o -> o.getPrice() == null || o.getQuantity() == null || o.getQuantity() == 0).collect(Collectors.toList());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/getProductForStockTable", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Stock> getProductForStockTable(@RequestHeader (value="companyId") String CompanyId, HttpServletRequest httpServletRequest) throws Exception{

        Specification <Stock> specification;
        List<Stock> stockList = null;

        try {
            specification = productService.getAllStockBySpec(Integer.valueOf(CompanyId));
            stockList = productService.getAllStock(specification);
        } catch (Exception e){
            e.printStackTrace();
        }

        return stockList;
    }


    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/getStockByCompanyId", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Stock> getStockByCompanyId(@RequestHeader (value="companyId") String CompanyId, HttpServletRequest httpServletRequest) throws Exception{

        Specification <Stock> specification;
        List<Stock> stockList = null;

        try {
            specification = productService.getAllStockBySpec(Integer.valueOf(CompanyId));
            stockList = productService.getAllStock(specification);
        } catch (Exception e){
            e.printStackTrace();
        }

        return stockList.stream().filter(o -> o.getIdProduct().getPrice() != null || o.getIdProduct().getQuantity() != null).collect(Collectors.toList());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/getStockByCompanyIdAndType", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Stock> getStockByCompanyIdAndType(@RequestHeader (value="companyId") String CompanyId, HttpServletRequest httpServletRequest) throws Exception{

        Specification <Stock> specification;
        List<Stock> stockList = null;

        try {
            specification = productService.getAllStockBySpec(Integer.valueOf(CompanyId));
            stockList = productService.getAllStock(specification);
        } catch (Exception e){
            e.printStackTrace();
        }

        return stockList.stream().filter(o -> o.getIdProduct().getIdProductType().getIdProductType().equals(1)).collect(Collectors.toList());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/getProductNorms", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Stock> getProductNorms(@RequestHeader (value="companyId") String CompanyId, HttpServletRequest httpServletRequest) throws Exception{

        Specification <Stock> specification;
        List<Stock> stockList = null;

        try {
            specification = productService.getAllStockBySpec(Integer.valueOf(CompanyId));
            stockList = productService.getAllStock(specification);
        } catch (Exception e){
            e.printStackTrace();
        }

        return stockList.stream().filter(o -> o.getIdProduct().getIdProductType().getIdProductType().equals(2)).collect(Collectors.toList());
    }


    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/getProductDataById", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Integer getStockDataById(@RequestHeader (value="companyId") String CompanyId,
                                    @RequestHeader (value="productId") String ProductId, HttpServletRequest httpServletRequest) throws Exception{

        Specification <Product> specification;
        List<Product> productList = null;
        Integer type;

        try {
            specification = productService.getProductById(Integer.valueOf(ProductId));
            productList = productService.getAllProduct(specification);

            productList = productList.stream().filter(o -> o.getCompanyId() == Integer.parseInt(CompanyId)).collect(Collectors.toList());
        } catch (Exception e){
            e.printStackTrace();
        }

        return type = productList.get(0).getIdProductType().getIdProductType();
    }


    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/getStockById", method = RequestMethod.GET,  produces = "application/json;charset=UTF-8")
    public List<Stock> addProductInStock(@RequestHeader (value="companyId") String companyId,
                                     @RequestHeader (value="stockId") String stockId,
                                     HttpServletRequest httpServletRequest) throws Exception{

        Specification <Stock> specification;
        List<Stock> stockList = null;

        try {
            specification = productService.getStockById(Integer.valueOf(stockId));
            stockList  = productService.getAllStock(specification);

        } catch (Exception e){
            e.printStackTrace();
        }

        return stockList.stream().filter(o -> o.getCompanyId() == Integer.parseInt(companyId)).collect(Collectors.toList());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/getStockFromTableByProductId", method = RequestMethod.GET,  produces = "application/json;charset=UTF-8")
    public List<Stock> getStockFromTableByProductId(@RequestHeader (value="companyId") String companyId,
                                         @RequestHeader (value="productId") String productId,
                                         HttpServletRequest httpServletRequest) throws Exception{

        Specification <Stock> specification;
        List<Stock> stockList = null;

        try {
            specification = productService.getStockByProductId(Integer.valueOf(productId));
            stockList  = productService.getAllStock(specification);

        } catch (Exception e){
            e.printStackTrace();
        }

        return stockList;
    }


    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/addProductInStock", method = RequestMethod.GET,  produces = "application/json;charset=UTF-8")
    public Boolean addProductInStock(@RequestHeader (value="companyId") String companyId,
                                     @RequestHeader (value="productId") String productId,
                                     @RequestHeader (value="quantity") String quantity, HttpServletRequest httpServletRequest) throws Exception{

        Specification <Stock> specification;
        List<Stock> stockList = null;
        boolean flag = false;

        try {
            specification = productService.getStockByProductId(Integer.valueOf(productId));
            stockList  = productService.getAllStock(specification);
            stockList  = stockList .stream().filter(o -> o.getCompanyId() == Integer.valueOf(companyId)).collect(Collectors.toList());

            stockList.get(0).setQuantity(stockList.get(0).getQuantity() + Double.valueOf(quantity));
            stockList.get(0).setIdStock(stockList.get(0).getIdStock());

            productService.saveNewStock(stockList.get(0));

            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }


    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/saveProductInMenu", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Boolean saveProductInMenu(@RequestBody Product product, HttpServletRequest httpServletRequest) throws Exception{
        Specification <Product> specification;
        List<Product> productList = null;
        Boolean flag = false;
        Product prod = new Product();
        ProductType productType = new ProductType();
        try {

            specification = productService.getProductById(Integer.parseInt(product.getName()));
            productList = productService.getAllProduct(specification);

            if(productList.size() > 0){
                productList.get(0).setQuantity(product.getQuantity());
                productList.get(0).setPrice(product.getPrice());
                productService.saveNewProduct(productList.get(0));
                flag = true;

            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/addNewNorms", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Boolean addNewNorms(@RequestHeader (value="CompanyId") String companyId,
                               @RequestHeader (value="idProduct") String idProduct,
                               @RequestHeader (value="idProductN") String idProductN,
                               @RequestHeader (value="quantity") String quantity, HttpServletRequest httpServletRequest) throws Exception{

        Specification <Product> productSpecification;
        List<Product> productList = null;
        Boolean flag = false;
        Norms norms = new Norms();

        try {

            productSpecification = productService.getProductById(Integer.valueOf(idProductN));
            productList = productService.getAllProduct(productSpecification);

            norms.setCompanyId(Integer.valueOf(companyId));
            norms.setQuantity(Double.valueOf(quantity));
            norms.setIdProductN(Integer.valueOf(idProduct));
            norms.setIdProduct(productList.get(0));


            productService.saveNewNorms(norms);


           flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }


    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/getNormsForProductId", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Norms> addNewNorms(@RequestHeader (value="idProduct") String idProduct, HttpServletRequest httpServletRequest) throws Exception{
        
        Specification<Norms> normsSpecification;
        List<Norms> normsList = null;
        
        try {
            normsSpecification = productService.getNormsByProductIdN(Integer.valueOf(idProduct));
            normsList = productService.getNormsBySpec(normsSpecification);

        } catch (Exception e){
            e.printStackTrace();
        }

        return normsList;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/getNormsById", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Norms> getNormsById(@RequestHeader (value="idNorms") String idNorms, HttpServletRequest httpServletRequest) throws Exception{

        Specification<Norms> normsSpecification;
        List<Norms> normsList = null;

        try {
            normsSpecification = productService.getNormsById(Integer.valueOf(idNorms));
            normsList = productService.getNormsBySpec(normsSpecification);

        } catch (Exception e){
            e.printStackTrace();
        }

        return normsList;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/updateNorms", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Norms> updateNorms(@RequestHeader (value="idNorms") String idNorms,
                                   @RequestHeader (value="quantity") String quantity, HttpServletRequest httpServletRequest) throws Exception{

        Specification<Norms> normsSpecification;
        List<Norms> normsList = null;


        try {
            normsSpecification = productService.getNormsById(Integer.valueOf(idNorms));
            normsList = productService.getNormsBySpec(normsSpecification);

            normsList.get(0).setQuantity(Double.valueOf(quantity));

            productService.saveNewNorms(normsList.get(0));
        } catch (Exception e){
            e.printStackTrace();
        }

        return normsList;
    }


    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/getLimitProduct", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Stock> getLimitProduct(@RequestHeader (value="companyId") String companyId, HttpServletRequest httpServletRequest) throws Exception{

        Specification<Stock> stockSpec;
        List<Stock> stockList = null;
        Specification<Settings> settingsSpec;
        List<Settings> settList = null;

        try {
            settingsSpec = settingsService.getSettingsByCompanyId(Integer.valueOf(companyId));
            settList = settingsService.getInvItemBySpec(settingsSpec);

            stockSpec = productService.getStockLimit(Integer.valueOf(companyId), Double.valueOf(settList.get(0).getLimitPiece()));
            stockList = productService.getAllStock(stockSpec);

        } catch (Exception e){
            e.printStackTrace();
        }

        return stockList;
    }



}




