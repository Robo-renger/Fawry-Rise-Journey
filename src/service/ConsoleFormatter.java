package service;

import interfaces.Product;
import java.util.HashMap;
import java.util.Map;

public class ConsoleFormatter {

    public static void printShipmentNotice(HashMap<Product, Integer> products) {
        System.out.println("** Shipment notice **");

        double totalWeight = 0.0;

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product instanceof interfaces.Shippable) {
                double weight = ((interfaces.Shippable) product).getWeight();
                double itemWeight = weight * quantity;
                totalWeight += itemWeight;

                System.out.printf("%dx %s %.0fg%n", quantity, product.getName(), weight * 1000);
            }
        }

        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }

    public static void printCheckoutReceipt(HashMap<Product, Integer> products, double subtotal, double shippingCost) {
        System.out.println("** Checkout receipt **");

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double totalProductPrice = product.getPrice() * quantity;

            System.out.printf("%dx %s %.0f%n", quantity, product.getName(), totalProductPrice);
        }

        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f%n", subtotal);
        System.out.printf("Shipping %.0f%n", shippingCost);
        System.out.printf("Amount %.0f%n", subtotal + shippingCost);
    }
}
