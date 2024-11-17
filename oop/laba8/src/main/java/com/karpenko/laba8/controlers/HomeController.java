package com.karpenko.laba8.controlers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.karpenko.laba8.models.Product;
import com.karpenko.laba8.services.ProductService;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }



    @GetMapping({"/", "/home"})
    public String Home(Model model) {

        List<Product> products = productService.readAll();
        model.addAttribute("products", products );

        return "index"; 
    }
}
