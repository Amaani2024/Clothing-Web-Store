package com.clothing.clthstore.controller;

import com.clothing.clthstore.model.Cart;
import com.clothing.clthstore.model.Cartitem;
import com.clothing.clthstore.model.product;
import com.clothing.clthstore.repository.CartitemRepository;
import com.clothing.clthstore.repository.CartRepository;
import com.clothing.clthstore.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class Cartcontroller {

    private final CartRepository cartRepository;
    private final CartitemRepository cartItemRepository; // matches your repository
    private final ProductRepository productRepository;

    public Cartcontroller(CartRepository cartRepository,
            CartitemRepository cartItemRepository,
            ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    // Create a new empty cart
    @PostMapping("/create")
    public Cart createCart() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    // Add product to cart
    @PostMapping("/{cartId}/add/{productId}")
    public Cart addToCart(@PathVariable Long cartId,
            @PathVariable Long productId,
            @RequestParam(defaultValue = "1") Integer quantity) {

        Cart cart = cartRepository.findById(cartId).orElseThrow();
        product product = productRepository.findById(productId).orElseThrow();

        Cartitem item = new Cartitem();
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(quantity);

        cartItemRepository.save(item);

        cart.getItems().add(item);
        cart.setTotalPrice(cart.getItems().stream()
                .mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity()).sum());

        return cartRepository.save(cart);
    }

    // View cart
    @GetMapping("/{cartId}")
    public Cart getCart(@PathVariable Long cartId) {
        return cartRepository.findById(cartId).orElseThrow();
    }

    // Remove item from cart
    @DeleteMapping("/{cartId}/remove/{itemId}")
    public Cart removeItem(@PathVariable Long cartId, @PathVariable Long itemId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        Cartitem item = cartItemRepository.findById(itemId).orElseThrow();

        cart.getItems().remove(item);
        cartItemRepository.delete(item);

        cart.setTotalPrice(cart.getItems().stream()
                .mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity()).sum());

        return cartRepository.save(cart);
    }
}
