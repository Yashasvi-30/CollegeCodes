import java.util.*;

class Supermarket {
    private static Product[] products = new Product[100];
    private static int productCount = 0;
    private static double total_price = 0;

    static {
        total_price = 0;
    }

    public static void addProduct(Product product) {
        if (productCount < products.length) {
            products[productCount++] = product;
        } else {
            System.out.println("Product inventory is full. Cannot add more products.");
        }
    }

    public static boolean removeProduct(int productId) {
        for (int i = 0; i < productCount; i++) {
            if (products[i].getProductId() == productId) {
                for (int j = i; j < productCount - 1; j++) {
                    products[j] = products[j + 1];
                }
                productCount--;
                break;
            }
        }
        return true;
    }

    public static Product[] getProductList() {
        return Arrays.copyOf(products, productCount);
    }

    public static double getTotalPrice() {
        total_price = 0;
        for (int i = 0; i < productCount; i++) {
            total_price += products[i].getProductPrice() * products[i].getProductQuantity();
        }
        return total_price;
    }

    public static Product getProductById(int productId) {
        try (Scanner scanner = new Scanner(System.in)) {
            for (Product product : products) {
                if (product != null && product.getProductId() == productId) {
                    return product;
                }
            }
            return null;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input for Product ID. Please enter a valid integer.");
            return null;
        }
    }
}

class Product {
    private int product_id;
    private String product_name;
    private double product_price;
    private int product_quantity;

    public Product(int id, String name, double price, int quantity) {
        this.product_id = id;
        this.product_name = name;
        this.product_price = price;
        this.product_quantity = quantity;
    }

    public int getProductId() {
        return product_id;
    }

    public String getProductName() {
        return product_name;
    }

    public double getProductPrice() {
        return product_price;
    }

    public int getProductQuantity() {
        return product_quantity;
    }
}

class Customer {
    private int customer_id;
    private String customer_name;
    private String customer_phoneno;

    public Customer(int id, String name, String phone) {
        this.customer_id = id;
        this.customer_name = name;
        this.customer_phoneno = phone;
    }

    public int getCustomerId() {
        return customer_id;
    }

    public String getCustomerName() {
        return customer_name;
    }

    public String getCustomerPhoneNo() {
        return customer_phoneno;
    }
}

public class SupermarketManagementSystem2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! Welcome to the Supermarket Management System.");

        int cashierId = -1;

        System.out.print("Please enter your Cashier ID: ");
        try {
            cashierId = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input for Cashier ID. Please enter a valid integer.");
            scanner.nextLine();
            return;
        }

        while (true) {
            printMainMenu();

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input for choice. Please enter a valid integer.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    Customer customer = getCustomerDetails(scanner);
                    System.out.println("Customer Details:");
                    System.out.println("Customer ID: " + customer.getCustomerId());
                    System.out.println("Customer Name: " + customer.getCustomerName());
                    System.out.println("Customer Phone No: " + customer.getCustomerPhoneNo());

                    List<Product> cart = new ArrayList<>();
                    while (true) {
                        System.out.print("Enter Product ID (0 to finish): ");
                        int productId;
                        try {
                            productId = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input for Product ID. Please enter a valid integer.");
                            scanner.nextLine();
                            continue;
                        }

                        if (productId == 0) {
                            break;
                        }

                        Product product = Supermarket.getProductById(productId);
                        if (product != null) {
                            cart.add(product);
                            System.out.println("Added Product: " + product.getProductName());
                        } else {
                            System.out.println("Product not found.");
                        }
                    }

                    double totalBill = calculateBill(cart);
                    System.out.println("Total Bill: $" + totalBill);
                    break;

                case 2:
                    Product productToAdd = getProductAttributes(scanner);
                    Supermarket.addProduct(productToAdd);
                    System.out.println("Product Added.");
                    break;

                case 3:
                    System.out.print("Enter Product ID to Remove: ");
                    int productIdToRemove;
                    try {
                        productIdToRemove = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input for Product ID. Please enter a valid integer.");
                        scanner.nextLine();
                        continue;
                    }

                    if (Supermarket.removeProduct(productIdToRemove)) {
                        System.out.println("Product Removed.");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 4:
                    Product[] productList = Supermarket.getProductList();
                    System.out.println("Product List:");
                    for (Product p : productList) {
                        System.out.println("Product ID: " + p.getProductId());
                        System.out.println("Product Name: " + p.getProductName());
                        System.out.println("Product Price: $" + p.getProductPrice());
                        System.out.println("Product Quantity: " + p.getProductQuantity());
                    }
                    break;

                case 5:
                    double totalPrice = Supermarket.getTotalPrice();
                    System.out.println("Total Price: $" + totalPrice);
                    break;

                case 6:
                    System.out.print("Enter Product ID: ");
                    int prodId;
                    try {
                        prodId = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input for Product ID. Please enter a valid integer.");
                        scanner.nextLine();
                        continue;
                    }

                    Product productById = Supermarket.getProductById(prodId);
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
                    System.out.println("Logged out Cashier with ID: " + cashierId);
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static Customer getCustomerDetails(Scanner scanner) {
        System.out.println("Enter Customer Details:");

        System.out.print("Customer ID: ");
        int customerId;
        try {
            customerId = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input for Customer ID. Please enter a valid integer.");
            scanner.nextLine();
            return new Customer(0, "", ""); // Return a dummy customer
        }

        System.out.print("Customer Name: ");
        String customerName = scanner.nextLine();

        System.out.print("Customer Phone Number: ");
        String customerPhoneNumber = scanner.nextLine();

        return new Customer(customerId, customerName, customerPhoneNumber);
    }

    private static Product getProductAttributes(Scanner scanner) {
        System.out.println("Enter Product Attributes:");

        System.out.print("Product ID: ");
        int productId;
        try {
            productId = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input for Product ID. Please enter a valid integer.");
            scanner.nextLine();
            return new Product(0, "", 0, 0); // Return a dummy product
        }

        System.out.print("Product Name: ");
        String productName = scanner.nextLine();

        System.out.print("Product Price: ");
        double productPrice;
        try {
            productPrice = scanner.nextDouble();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input for Product Price. Please enter a valid number.");
            scanner.nextLine();
            return new Product(productId, productName, 0, 0); // Return a dummy product
        }

        System.out.print("Product Quantity: ");
        int productQuantity;
        try {
            productQuantity = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input for Product Quantity. Please enter a valid integer.");
            scanner.nextLine();
            return new Product(productId, productName, productPrice, 0); // Return a dummy product
        }

        return new Product(productId, productName, productPrice, productQuantity);
    }

    private static double calculateBill(List<Product> cart) {
        double totalPrice = 0;

        System.out.println("Bill Details:");
        for (Product product : cart) {
            System.out.println("Product ID: " + product.getProductId());
            System.out.println("Product Name: " + product.getProductName());
            System.out.println("Product Quantity: " + product.getProductQuantity());

            double productTotalPrice = product.getProductPrice() * product.getProductQuantity();
            System.out.println("Product Total Price: $" + productTotalPrice);

            totalPrice += productTotalPrice;
            System.out.println();
        }

        System.out.println("Total Price: $" + totalPrice);
        return totalPrice;
    }

    private static void printMainMenu() {
        System.out.println("Main Menu:");
        System.out.println("1. Print Bill");
        System.out.println("2. Add Product");
        System.out.println("3. Remove Product");
        System.out.println("4. Get Product List");
        System.out.println("5. Get Total Price");
        System.out.println("6. Get Product by ID");
        System.out.println("7. Logout Cashier");
        System.out.print("Enter your choice: ");
    }
}
