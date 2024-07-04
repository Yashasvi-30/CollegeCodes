

/**
 * Supermarket Management System
 * 
 * This program simulates a basic supermarket management system, allowing cashiers to interact with customers,
 * add and remove products, calculate bills, and manage the supermarket's inventory.
 * 
 * The program is structured into several classes:
 * 
 * - Supermarket: Manages the supermarket's inventory of products and calculates the total price of items in the cart.
 * 
 * - Product: Represents a product available in the supermarket with attributes such as product ID, name, price, and quantity.
 * 
 * - Customer: Represents a customer who shops at the supermarket with attributes like customer ID, name, and phone number.
 * 
 * - Cashier: Represents a cashier working at the supermarket with attributes including cashier ID and name.
 * 
 * The program includes a simple text-based user interface to interact with the system, where cashiers can log in, add and
 * remove products, calculate bills, and log out.
 * 
 * The program uses inheritance for the Product class, allowing for the creation of other product-related classes if needed.
 * 
 * The Cashier class is loaded from a file, "CashierLoginDetails," but in practice, you would load cashier details from
 * a database or other storage system.
 * 

 */

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Project.people.*;
import Project.supermarket.*;
import supermarket.Customer;
import supermarket.Product;

public class SupermarketManagementSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! Welcome to the Supermarket Management System.");

        // Initialize a variable to store the current cashier ID.
        int cashierId = -1;

        // Continuously loop until a valid cashier ID is entered.
        while (true) {
            System.out.print("Please enter your Cashier ID: ");
            cashierId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Check if the entered cashier ID is valid.
            if (isCashierValid(cashierId)) {
                System.out.println("Welcome, Cashier " + cashierId + "!");
                break; // Exit the loop when a valid ID is entered.
            } else {
                System.out.println("Invalid Cashier ID. Please try again.");
            }
        }

        // Main menu loop
        while (true) {
            printMainMenu();

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Print Bill
                    Customer customer = getCustomerDetails(scanner);
                    System.out.println("Customer Details:");
                    System.out.println("Customer ID: " + customer.getCustomerId());
                    System.out.println("Customer Name: " + customer.getCustomerName());
                    System.out.println("Customer Phone No: " + customer.getCustomerPhoneNo());

                    List<Product> cart = new ArrayList<>();
                    while (true) {
                        System.out.print("Enter Product ID (0 to finish): ");
                        int productId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (productId == 0) {
                            break; // Exit the loop when 0 is entered.
                        }

                        Product product = getProductById(productId);
                        if (product != null) {
                            cart.add(product);
                            System.out.println("Added Product: " + product.getProductName());
                        } else {
                            System.out.println("Product not found.");
                        }
                    }

                    // Calculate and print the bill
                    double totalBill = calculateBill(cart);
                    System.out.println("Total Bill: $" + totalBill);
                    break;

                case 2:
                    // Add Product
                    Product product = getProductAttributes(scanner);
                    Supermarket.addProduct(product);
                    System.out.println("Product Added.");
                    break;

                case 3:
                    // Remove Product
                    System.out.print("Enter Product ID to Remove: ");
                    int productId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (Supermarket.removeProduct(productId)) {
                        System.out.println("Product Removed.");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 4:
                    // Get Product List
                    List<Product> productList = getProductList();
                    System.out.println("Product List:");
                    for (Product p : productList) {
                        System.out.println("Product ID: " + p.getProductId());
                        System.out.println("Product Name: " + p.getProductName());
                        System.out.println("Product Price: $" + p.getProductPrice());
                        System.out.println("Product Quantity: " + p.getProductQuantity());
                    }
                    break;

                case 5:
                    // Get Total Price
                    double totalPrice = getTotalPrice();
                    System.out.println("Total Price: $" + totalPrice);
                    break;

                case 6:
                    // Get Product by ID
                    System.out.print("Enter Product ID: ");
                    int prodId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Product productById = getProductById(prodId);
                    if (productById != null) {
                        System.out.println("Product Details:");
                        System.out.println("Product ID: " + productById.getProductId());
                        System.out.println("Product Name: " + productById.getProductName());
                        System.out.println("Product Price: $" + productById.getProductPrice());
                        System.out.println("Product Quantity: " + productById.getProductQuantity());
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 7:
                    // Logout Cashier
                    System.out.println("Logged out Cashier with ID: " + cashierId);
                    // Return to the login screen or exit the program if needed.
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static Customer getCustomerDetails(Scanner scanner) 
    {
        System.out.println("Enter Customer Details:");
    
        System.out.print("Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
    
        System.out.print("Customer Name: ");
        String customerName = scanner.nextLine();
    
        System.out.print("Customer Phone Number: ");
        String customerPhoneNumber = scanner.nextLine();
    
        // Create a Customer object with the entered details
        Customer customer = new Customer(customerId, customerName, customerPhoneNumber);
    
        System.out.println("Customer Details captured.");
        return customer;
    }
    
    private static Product getProductById(int productId) {
        Product[] products;
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product; // Found the product with the specified ID
            }
        }
        
        // Product with the specified ID was not found
        return null;
    }
    
    private static double getTotalPrice() {
        double totalPrice = 0;
        Product[] products;
        for (Product product : products) {
            totalPrice += product.getProductPrice() * product.getProductQuantity();
        }
        return totalPrice;
    }


    private static double calculateBill(List<Product> cart) {
        double totalPrice = 0;
    
        System.out.println("Bill Details:");
        for (Product product : cart) {
            System.out.println("Product ID: " + product.getProductId());
            System.out.println("Product Name: " + product.getProductName());
            System.out.println("Product Price: " + product.getProductPrice());
            System.out.println("Product Quantity: " + product.getProductQuantity());
    
            double productTotalPrice = product.getProductPrice() * product.getProductQuantity();
            System.out.println("Product Total Price: " + productTotalPrice);
            
            totalPrice += productTotalPrice;
            System.out.println();
        }
    
        System.out.println("Total Price: " + totalPrice);
        return totalPrice;
    }
   
    private static boolean isCashierValid(int cashierId) 
    {
        File file = new File("CashierDetails.txt");
        Scanner scanner = new Scanner(file);
        
        while (scanner.hasNextLine()) 
        {
            String line = scanner.nextLine();
            
            // Split the line by whitespace and check for an int value (assuming the line contains an int)
            String[] parts = line.split("\\s+");
            for (String part : parts) 
            {
                
                    int value = Integer.parseInt(part);
                    if (value == cashierId) {
                        scanner.close();
                        return true;
                    }
               
                }
            }
             scanner.close();
             return false;
        }
        
        private static Product getProductAttributes(Scanner scanner) {
            System.out.println("Enter Product Attributes:");
        
            System.out.print("Product ID: ");
            int productId = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
        
            System.out.print("Product Name: ");
            String productName = scanner.nextLine();
        
            System.out.print("Product Price: ");
            double productPrice = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character
        
            System.out.print("Product Quantity: ");
            int productQuantity = scanner.nextInt();
        
            // Create a Product object with the entered attributes
            Product product = new Product(productId, productName, productPrice, productQuantity);
        
            System.out.println("Product Attributes captured.");
            return product;
        }
        
       
        private static List<Product> getProductList() {
            List<Product> products;
            return products;
        }
        

    private static void printMainMenu()
    {
        System.out.println("Main Menu:");
        System.out.println("1. Print Bill");
        System.out.println("2. Add Product");
        System.out.println("3. Remove Product");
        System.out.println("4. Get Product List");
        System.out.println("5. Get Total Price");
        System.out.println("6. Get Product by ID");
        System.out.println("7. Logout Cashier");
        System.out.println("Enter your choice: ");
    }


}
