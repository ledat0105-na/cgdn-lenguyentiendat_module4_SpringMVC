package com.example.shoppingcart.controller;

import com.example.shoppingcart.model.Cart;
import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@SessionAttributes("cart")
public class ProductController {
    @Autowired
    private IProductService productService;

    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }

    @GetMapping("/shop")
    public ModelAndView showShop() {
        ModelAndView modelAndView = new ModelAndView("/shop");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }

    @GetMapping("/")
    public String redirectToShop() {
        return "redirect:/shop";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id,
                            @ModelAttribute Cart cart,
                            @RequestParam(value = "action", required = false, defaultValue = "list") String action,
                            org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttributes) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return "/error_404";
        }
        redirectAttributes.addFlashAttribute("message", "Đã thêm sản phẩm vào giỏ hàng!");
        if (action.equals("show")) {
            cart.addProduct(productOptional.get());
            return "redirect:/shopping-cart";
        }
        cart.addProduct(productOptional.get());
        return "redirect:/shop";
    }

    @GetMapping("/product/{id}")
    public ModelAndView showProductDetail(@PathVariable Long id, @ModelAttribute("cart") Cart cart) {
        ModelAndView mv = new ModelAndView("/product_detail");
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            mv.setViewName("/error_404");
            return mv;
        }
        Product p = productOptional.get();
        mv.addObject("product", p);
        mv.addObject("quantity", cart.getQuantityByProductId(id));
        return mv;
    }
}