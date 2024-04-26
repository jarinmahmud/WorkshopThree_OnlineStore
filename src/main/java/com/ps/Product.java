package com.ps;

public class Product {
   // properties
    private String sku, productName, department;
    private double price;


    // Constructors
    public Product(int id, String sku, String productName, double price, String department) {
        this.sku = sku;
        this.productName = productName;
        this.price = price;
        this.department = department;

    }

    // getters

    public String getSku() {
        return sku;
    }


    public String getProductName() {
        return productName;
    }


    public double getPrice() {
        return price;
    }


    public String getDepartment() {
        return department;
    }


}
