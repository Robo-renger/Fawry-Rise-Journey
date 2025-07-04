package model;
import java.util.HashMap;
import interfaces.Product;
import java.util.Map;
public class Cart {
    private HashMap<Product, Integer> products;

    public Cart() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) throws IllegalArgumentException {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (product.getStock() < quantity) {
            throw new IllegalArgumentException("Insufficient stock for product: " + product.getName() + ". Available stock: " + product.getStock());
        }
        if (product instanceof interfaces.Expirable) {
            if (((interfaces.Expirable) product).isExpired()) {
                throw new IllegalArgumentException("Cannot add expired product: " + product.getName());
            }
        }
        products.put(product, products.getOrDefault(product, 0) + quantity);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public double calculateTotalPrice() {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total += product.getPrice() * quantity;
        }
        return total;
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }
    public void clear() {
        products.clear();
    }
}
