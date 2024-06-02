package by.VIWARR.FruitMarket.controllers;

import by.VIWARR.FruitMarket.models.Product;
import by.VIWARR.FruitMarket.services.ProductService;
import by.VIWARR.FruitMarket.util.ProductErrorResponse;
import by.VIWARR.FruitMarket.util.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController (ProductService productService) {
        this.productService = productService;
    }

    @ResponseBody
    @GetMapping("/findAll")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/addNew")
    public String addNew() {
        return "product/formForAddingANewProduct";
    }

    @PostMapping("/addNew")
    public String addNew(@RequestParam("name") String name) {
        productService.save(name);
        return "redirect:/product/findAll";
    }

    @ResponseBody
    @GetMapping("/findById/{id}")
    public Product findById(@PathVariable("id") int id) {
        return productService.findById(id);
    }

    @ExceptionHandler
    private ResponseEntity<ProductErrorResponse> handleException(ProductNotFoundException e) {
        ProductErrorResponse response = new ProductErrorResponse(
                "Продукт не найден",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}