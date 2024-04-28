package com.ps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Product> products = loadProducts("products.txt");
        ShoppingCart cart = new ShoppingCart();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Welcome to The Best Online Store!");
            System.out.println("Please indicate what you would like to do");
            System.out.println("\t1. Display all available products.");
            System.out.println("\t2. Display your cart.");
            System.out.println("\t3. Exit.");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    displayProducts(products, scanner, cart);
                    break;
                case 2:
                    displayCart(cart, scanner);
                    break;
                case 3:
                    isRunning = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
        scanner.close();
    }

    private static void displayProducts(List<Product> products, Scanner scanner, ShoppingCart cart) {
        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("Product List:");
            products.forEach(System.out::println);
            System.out.println("Actions: 1 - Add to Cart, 2 - Search, 3 - Return to Main Menu");

            int action = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (action) {
                case 1:
                    addProductToCart(products, scanner, cart);
                    break;
                case 2:
                    keepGoing = searchProducts(products, scanner);
                    break;
                case 3:
                    keepGoing = false;
                    break;
                default:
                    System.out.println("Invalid action. Please choose a correct action.");
                    break;
            }
        }
    }

    private static void addProductToCart(List<Product> products, Scanner scanner, ShoppingCart cart) {
        boolean addingProducts = true;
        while (addingProducts) {
            System.out.println("Enter SKU of product to add:");
            String sku = scanner.nextLine().trim();
            System.out.println("Enter quantity to add:");
            int quantity = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            boolean productAdded = products.stream()
                    .filter(p -> p.getSku().equalsIgnoreCase(sku))
                    .findFirst()
                    .map(product -> {
                        cart.addProduct(product, quantity);
                        return true;
                    }).orElse(false);

            if (!productAdded) {
                System.out.println("Product not found. Please try again.");
            }

            System.out.println("Do you want to add another product? (yes/no)");
            String response = scanner.nextLine().trim();
            if (!response.equalsIgnoreCase("yes")) {
                addingProducts = false;
            }
        }
    }


    private static boolean searchProducts(List<Product> products, Scanner scanner) {
        System.out.println("Choose your search type:");
        System.out.println("\t1. By Product Name");
        System.out.println("\t2. By Department");
        System.out.println("\t3. By Price");
        System.out.println("Enter your choice:");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        List<Product> filteredProducts = new ArrayList<>();
        switch (choice) {
            case 1:
                System.out.println("Enter product name keyword:");
                String nameKeyword = scanner.nextLine().trim().toLowerCase();
                filteredProducts = products.stream()
                        .filter(p -> p.getProductName().toLowerCase().contains(nameKeyword))
                        .collect(Collectors.toList());
                break;
            case 2:
                System.out.println("Enter department name:");
                String departmentName = scanner.nextLine().trim().toLowerCase();
                filteredProducts = products.stream()
                        .filter(p -> p.getDepartment().toLowerCase().equals(departmentName))
                        .collect(Collectors.toList());
                break;
            case 3:
                System.out.println("Enter price range (min max):");
                double minPrice = scanner.nextDouble();
                double maxPrice = scanner.nextDouble();
                scanner.nextLine(); // Consume the newline character
                filteredProducts = products.stream()
                        .filter(p -> p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                        .collect(Collectors.toList());
                break;
            default:
                System.out.println("Invalid search type. Please try again.");
                return true;
        }

        if (!filteredProducts.isEmpty()) {
            filteredProducts.forEach(System.out::println);
            return postSearchActions(filteredProducts, scanner);
        } else {
            System.out.println("No products found.");
        }
        return true;
    }

    private static boolean postSearchActions(List<Product> filteredProducts, Scanner scanner) {
        System.out.println("Actions: 1 - Add Product to Cart, 2 - Return to Main Menu");
        int action = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        switch (action) {
            case 1:
                addProductToCart(filteredProducts, scanner, new ShoppingCart());  // Needs adjustment to pass actual ShoppingCart
                return false;  // Keep the user in the search context
            case 2:
                return true;  // Return to main menu
            default:
                System.out.println("Invalid action. Returning to main menu.");
                return true;
        }
    }

    private static void displayCart(ShoppingCart cart, Scanner scanner) {
        boolean keepGoing = true;
        while (keepGoing) {
            cart.displayCart();
            System.out.println("Actions: 1 - Remove from Cart, 2 - Checkout, 3 - Return to Main Menu");
            int action = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (action) {
                case 1:
                    System.out.println("Enter SKU of product to remove:");
                    String sku = scanner.nextLine();
                    System.out.println("Enter quantity to remove:");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over
                    cart.removeProduct(sku, quantity);
                    break;
                case 2:
                    cart.checkout();
                    break;
                case 3:
                    keepGoing = false;
                    break;
                default:
                    System.out.println("Invalid action. Please choose a correct action.");
                    break;
            }
        }
    }

    public static List<Product> loadProducts(String filePath) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length >= 4) {
                    products.add(new Product(data[0].trim(), data[1].trim(), Double.parseDouble(data[2].trim()), data[3].trim()));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading products: " + e.getMessage());
        }
        return products;
    }
}
