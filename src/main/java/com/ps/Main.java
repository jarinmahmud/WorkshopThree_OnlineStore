package com.ps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        ArrayList<String> inventory = new ArrayList<String>();

        try (Scanner scanner = new Scanner(System.in)) {
            boolean isRunning = true;

            while (isRunning) {


                System.out.println("Welcome to The Best Online Store!");
                System.out.println("Please indicate what you would like to do");
                System.out.println("\t1. Display all available products.");
                System.out.println("\t2. Display your cart.");
                System.out.println("\t3. Exit.");

                int homeScreenChoice = scanner.nextInt();

                switch (homeScreenChoice) {
                    case 1:
                        viewAllProducts(scanner, inventory);
                    case 2:
                       // viewCart(scanner);
                    case 3:
                        isRunning = false;
                    default:
                        System.out.println("Invalid... Please select a valid choice");

                }
            }
        } catch (Exception e) {
            System.out.println("Error!!");
        }
    }

    public static void viewAllProducts(Scanner scanner, ArrayList<String> inventory) {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Welcome to our product page.");
            System.out.println("Displaying all products...");

            //display inventory
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader("products.txt"));
                String input;
                while ((input = bufferedReader.readLine()) != null) {
                    System.out.println(input);
                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            System.out.println("What would you like to do?");
            System.out.println("\t1.Search for products.");
            System.out.println("\t2. Add product(s) to cart");
            System.out.println("\t3. Return to previous screen.");


            int productScreenChoice = scanner.nextInt();
            switch (productScreenChoice) {
                case 1:
                    // filter products
                    break;
                case 2:
                    // ShoppingCart.addProduct(scanner);
                    break;
                case 3:
                    isRunning = false;
                default:
                    System.out.println("Invalid. Please select a valid choice");
            }
        }
    }
}


//    public static void viewCart(Scanner scanner) {
//        boolean isRunning = true;
//        while (isRunning) {
//          //  ShoppingCart.displayCart();
//            System.out.println("What would you like to do?");
//            System.out.println("\t1. Check Out");
//            System.out.println("\t2. Remove a product.");
//            System.out.println("\t3. Go back to home screen.");
//
//            int cartScreenChoice = scanner.nextInt();
//            switch (cartScreenChoice) {
//                case 1:
//                //    ShoppingCart.checkout(scanner);
//                    break;
//                case 2:
//                  //  ShoppingCart.removeProduct(scanner);
//                    break;
//                case 3:
//                    isRunning = false;
//                    break;
//                default:
//                    System.out.println("Invalid...Please select a valid choice");
//            }
//        }
//    }
//
//
