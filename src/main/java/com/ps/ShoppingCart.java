package com.ps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShoppingCart {
    private List<Product> cartItems;

    public ShoppingCart() {
        cartItems = new ArrayList<>();
    }
    // Method to remove a product from the cart
    public void removeProduct(String sku) {
        // We start walking down the line of students (products).
        Iterator<Product> iterator = cartItems.iterator();

        // As long as there are more students, we keep going.
        while (iterator.hasNext()) {
            // We look at the card of the next student in line.
            Product product = iterator.next();

            // We check if the number on the card is the one we're looking for (sku).
            if (product.getSku().equals(sku)) {
                // If we find the student (product) with the right number (sku),
                // we ask them to step out of the line (remove the product).
                iterator.remove();
            }
        }
    }
}
            /*

In this code:

* Iterator<Product> is like having a helper who keeps track of which student you're talking to in the line.
* iterator.hasNext() is like asking the helper, "Is there another student for me to check?"
* Product product = iterator.next() is like moving to the next student in line and looking at their number.
* product.getSku().equals(sku) is like comparing the number on the card to the number you're looking for, number 5.
* iterator.remove() is like asking the student with the right number to leave the line.
 */





