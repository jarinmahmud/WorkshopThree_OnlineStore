package com.ps;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

// ShoppingCart class represents a shopping cart where products can be added or removed.
public class ShoppingCart {
    // Map to store products and their corresponding quantities
    private Map<Product, Integer> cartItems;

    // Constructor for ShoppingCart initializes an empty cart.

    public ShoppingCart() {
        cartItems = new HashMap<>();
    }


    public void addProduct(Product product, int quantity) {
        // If product exists, increments existing quantity; otherwise, sets initial quantity.
        cartItems.put(product, cartItems.getOrDefault(product, 0) + quantity);
        System.out.println(quantity + " x " + product.getProductName() + " added to cart.");
    }


    public void removeProduct(String sku, int quantityToRemove) {
        Iterator<Map.Entry<Product, Integer>> iterator = cartItems.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Product, Integer> entry = iterator.next();
            // Check if the product matches the SKU provided
            if (entry.getKey().getSku().equals(sku)) {
                if (entry.getValue() > quantityToRemove) {
                    // Decrease quantity of the product
                    entry.setValue(entry.getValue() - quantityToRemove);
                    System.out.println(quantityToRemove + " x " + entry.getKey().getProductName() + " removed from cart.");
                } else {
                    // Remove product completely if quantity to remove is not specified or too high
                    System.out.println(entry.getValue() + " x " + entry.getKey().getProductName() + " removed from cart.");
                    iterator.remove();
                }
                return; // Early return after handling product
            }
        }
        System.out.println("Product not found or incorrect quantity.");
    }

    // Displays all products in the cart with their quantities.

    public void displayCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            cartItems.forEach((product, quantity) ->
                    System.out.println(quantity + " x " + product + " in cart."));
        }
    }

    // Completes the checkout process by calculating total cost and clearing the cart.

    public void checkout() {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        double total = 0;
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double subtotal = product.getPrice() * quantity;
            total += subtotal;
            System.out.println(quantity + " x " + product.getProductName() + " at $" + product.getPrice() + " each: $" + subtotal);
        }
        System.out.println("Total: $" + total);
        cartItems.clear(); // Clear the cart after checkout
        System.out.println("Checkout complete.");
    }
}

