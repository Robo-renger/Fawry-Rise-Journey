package service;
import model.Cart;

import java.util.Iterator;
import java.util.Map;


import interfaces.Expirable;
import interfaces.Product;
import interfaces.Shippable;
public class ShippingService {
    private Cart cart;
    private Map<Product, Integer> expireableShippableProducts = new java.util.HashMap<>(); // Store expireable and shippable products
    private static final double BASE_SHIPPING_COST = 10.0; // Base cost for shipping
    private static final double EXPIREABLE_SHIPPABLE_MULTIPLIER = 1.2; // Multiplier for expirable shippable products
    private static final double PER_KG_PRICE = 5.0; // Price per kg for shipping

    public ShippingService(Cart cart) {
        this.cart = cart;
    }

    private void filterExpirableProducts() {
        Iterator<Map.Entry<Product, Integer>> iterator = cart.getProducts().entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Product, Integer> entry = iterator.next();
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product instanceof Expirable && product instanceof Shippable) {
                expireableShippableProducts.put(product, quantity);
            }
        }
    }
    /**
     * Calculates the total price of all expirable and shippable products in the cart.
     * 
     * @return Total price of expirable and shippable products.
     */
    private double calculateTotalPriceOfExpirableShippableProducts() {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : expireableShippableProducts.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            // Assuming a multiplier for expirable shippable products as expirable products may have a higher shipping cost
            total += product.getPrice() * quantity * EXPIREABLE_SHIPPABLE_MULTIPLIER; 
        }
        return total;
    }
    private double calculateShippableTotalWeight(){
        double totalWeight = 0.0;
        for (Map.Entry<Product, Integer> entry : cart.getProducts().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            if (product instanceof Shippable && !(product instanceof Expirable)) {
                totalWeight += ((Shippable) product).getWeight() * quantity;
            }
        }
        return totalWeight;
    }
    
    public double calculateShippingCost() {
        filterExpirableProducts();
        double weight = calculateShippableTotalWeight();

        // Example shipping cost calculation based on weight and expirable products
        return (weight > 0 ? BASE_SHIPPING_COST : 0) + weight * PER_KG_PRICE + calculateTotalPriceOfExpirableShippableProducts();
    }
}
