package com.ps;

public class Product {
    private String sku;
    private String productName;
    private double price;
    private String department;

    public Product(String sku, String productName, double price, String department) {
        this.sku = sku;
        this.productName = productName;
        this.price = price;
        this.department = department;
    }

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

    @Override
    public String toString() {
        return "SKU: " + sku + ", Name: " + productName + ", Price: $" + price + ", Department: " + department;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 3db31a36bf092fd10256d645f020513be4e763e4
