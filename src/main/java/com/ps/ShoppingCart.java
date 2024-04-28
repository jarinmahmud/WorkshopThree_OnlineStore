//package com.ps;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//public class ShoppingCart {
//    private List<Product> cartItems;
//
//    // Constructor initializes the cart,
//    //Requirement meets - add products to a customer's cart.
//    public ShoppingCart() {
//        cartItems = new ArrayList<>();
//    }
//
//    //Requirement meets - Add a product to shopping cart
//    public void addProduct(Product product) {
//        cartItems.add(product);
//    }
//
//    //Requirements meets - Remove a product from shopping cart and prompt customer te remove product
//    public void removeProduct(String sku){
//        Iterator<Product> iterator = cartItems.iterator();
//        while (iterator.hasNext()) {
//            Product product = iterator.next();
//            if(product.getSku().equals(sku)) {
//                iterator.remove();
//                System.out.println("Product removed: ") + product.getProductName());
//                return;
//            }
//        }
//        System.out.println("Product not found.");
//    }
//
//
//}

package com.ps;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class ShoppingCart {
    private Map<Product, Integer> cartItems;

    public ShoppingCart() {
        cartItems = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        cartItems.put(product, cartItems.getOrDefault(product, 0) + quantity);
        System.out.println(quantity + " x " + product.getProductName() + " added to cart.");
    }

    public void removeProduct(String sku, int quantityToRemove) {
        Iterator<Map.Entry<Product, Integer>> iterator = cartItems.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Product, Integer> entry = iterator.next();
            if (entry.getKey().getSku().equals(sku)) {
                if (entry.getValue() > quantityToRemove) {
                    entry.setValue(entry.getValue() - quantityToRemove);
                    System.out.println(quantityToRemove + " x " + entry.getKey().getProductName() + " removed from cart.");
                } else {
                    System.out.println(entry.getValue() + " x " + entry.getKey().getProductName() + " removed from cart.");
                    iterator.remove();
                }
                return;
            }
        }
        System.out.println("Product not found or incorrect quantity.");
    }


    public void displayCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            cartItems.forEach((product, quantity) ->
                    System.out.println(quantity + " x " + product + " in cart."));
        }
    }

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
        cartItems.clear();
        System.out.println("Checkout complete.");
    }
}







