package decorators;
import java.time.LocalDate;

import interfaces.Expirable;
import interfaces.Product;
public class ExpirableProductDecorator implements Product, Expirable {
    private final Product product;
    private final LocalDate expiryDate;

    public ExpirableProductDecorator(Product product, LocalDate expiryDate) {
        this.product = product;
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    @Override
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    @Override public int getId() { return product.getId(); }
    @Override public String getName() { return product.getName(); }
    @Override public double getPrice() { return product.getPrice(); }
    @Override public int getStock() { return product.getStock(); }
    @Override public void setId(int id) { product.setId(id); }
    @Override public void setName(String name) { product.setName(name); }
    @Override public void setPrice(double price) { product.setPrice(price); }
    @Override public void setStock(int stock) { product.setStock(stock); }
}