//package com.ps;
//
//public class Product {
//   // properties
//    private String sku, productName, department;
//    private double price;
//
//
//    // Constructors
//    public Product(int id, String sku, String productName, double price, String department) {
//        this.sku = sku;
//        this.productName = productName;
//        this.price = price;
//        this.department = department;
//
//    }
//
//    // getters
//
//    public String getSku() {
//        return sku;
//    }
//
//
//    public String getProductName() {
//        return productName;
//    }
//
//
//    public double getPrice() {
//        return price;
//    }
//
//
//    public String getDepartment() {
//        return department;
//    }
//
//
//}


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
}
