package com.example.shoppingcart.controller;

import com.example.shoppingcart.model.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.example.shoppingcart.model.Product;

import java.util.Map;

@Controller
@SessionAttributes("cart")
public class ShoppingController {
    @ModelAttribute("cart")
    public Cart setupCart(){
        return new Cart();
    }

    @GetMapping("/shopping-cart")
    public ModelAndView showCart (@ModelAttribute("cart") Cart cart){
        ModelAndView modelAndView = new ModelAndView("/cart");
        modelAndView.addObject("cart",cart);
        return modelAndView;
    }

    @PostMapping("/cart/increase/{id}")
    public String increaseItem(@PathVariable Long id, @SessionAttribute("cart") Cart cart) {
        Product product = new Product();
        product.setId(id);
        cart.addProduct(product);
        return "redirect:/shopping-cart";
    }

    @PostMapping("/cart/decrease/{id}")
    public String decreaseItem(@PathVariable Long id, @SessionAttribute("cart") Cart cart) {
        Product product = new Product();
        product.setId(id);
        cart.decreaseProduct(product);
        return "redirect:/shopping-cart";
    }

    @PostMapping("/cart/update/{id}")
    public String updateItem(@PathVariable Long id,
                             @RequestParam("quantity") int quantity,
                             @SessionAttribute("cart") Cart cart) {
        Product product = new Product();
        product.setId(id);
        cart.updateProductQuantity(product, quantity);
        return "redirect:/shopping-cart";
    }

    @PostMapping("/cart/remove/{id}")
    public String removeItem(@PathVariable Long id, @SessionAttribute("cart") Cart cart) {
        Product product = new Product();
        product.setId(id);
        cart.removeProduct(product);
        return "redirect:/shopping-cart";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeItemGet(@PathVariable Long id, @SessionAttribute("cart") Cart cart) {
        Product product = new Product();
        product.setId(id);
        cart.removeProduct(product);
        return "redirect:/shopping-cart";
    }

    @GetMapping("/cart/detail/{id}")
    public ModelAndView viewItemDetail(@PathVariable Long id, @SessionAttribute("cart") Cart cart) {
        ModelAndView modelAndView = new ModelAndView("/product_detail");
        for (Map.Entry<Product, Integer> entry : cart.getProducts().entrySet()) {
            if (entry.getKey().getId().equals(id)) {
                modelAndView.addObject("product", entry.getKey());
                modelAndView.addObject("quantity", entry.getValue());
                return modelAndView;
            }
        }
        modelAndView.setViewName("redirect:/shopping-cart");
        return modelAndView;
    }

    @GetMapping("/checkout")
    public ModelAndView checkout(@SessionAttribute("cart") Cart cart) {
        ModelAndView mv = new ModelAndView("/checkout");
        mv.addObject("cart", cart);
        return mv;
    }

    @PostMapping("/checkout")
    public String doCheckout(@SessionAttribute("cart") Cart cart) {
        cart.clear();
        return "redirect:/shop";
    }
}
