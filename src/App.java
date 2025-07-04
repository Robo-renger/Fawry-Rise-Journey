import service.CheckoutService;
import service.ConsoleFormatter;
import service.ShippingService;

import java.time.LocalDate;

import decorators.ExpirableProductDecorator;
import decorators.ShippableProductDecorator;
import interfaces.Product;
import model.*;
public class App {
    public static void main(String[] args) throws Exception {
        // Create a customer
        Customer customer = new Customer("John Doe", "john.doe@example.com", "1234567890", 2500.0);

        // Create a cart
        Cart cart = new Cart();

        // Create some simple products
        Product laptop = new ShippableProductDecorator((new SimpleProduct(0,"Laptop", 1000.0, 5)), 2.5); // Shippable product with weight 2.5kg
        Product smartphone = new ShippableProductDecorator(new SimpleProduct(1,"Smartphone", 500.0, 10), 1.5); // Shippable product with weight 2.5kg
        Product milk = new ExpirableProductDecorator((new SimpleProduct(2,"Milk", 5.0, 20)), LocalDate.now().plusDays(5));
        Product shippableMilk = new ShippableProductDecorator(milk, 0.5); // Shippable product with weight 0.5kg
        Product eBook = new SimpleProduct(3,"E-Book", 15.0, 100); // Non-shippable product
        Product book = new ShippableProductDecorator(new SimpleProduct(4,"Book", 20.0, 50), 1.0); // Shippable product
        // Add products to the cart
        try{
            cart.addProduct(laptop, 1);
            cart.addProduct(smartphone, 2);
            cart.addProduct(shippableMilk, 1);
            cart.addProduct(eBook, 3); // E-Book is not shippable, but can be added to the cart
            cart.addProduct(book, 1);
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding product: " + e.getMessage());
        }

        // Create a checkout service
        CheckoutService checkoutService = new CheckoutService(cart, customer);

        // Create a shipping service
        ShippingService shippingService = new ShippingService(cart);
        // Checkout
        try{
            ConsoleFormatter.printShipmentNotice(cart.getProducts());
            ConsoleFormatter.printCheckoutReceipt(cart.getProducts(), cart.calculateTotalPrice(), shippingService.calculateShippingCost());
            checkoutService.checkout();
            System.out.println("Checkout successful! Remaining balance: " + customer.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("Checkout failed: " + e.getMessage());
        }
    }
}
