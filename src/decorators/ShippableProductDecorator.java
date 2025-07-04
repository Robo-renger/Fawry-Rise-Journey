package decorators;

import interfaces.Product;
import interfaces.Shippable;

public class ShippableProductDecorator implements Product, Shippable {
    private final Product product;
    private final double weight;

    public ShippableProductDecorator(Product product, double weight) {
        this.product = product;
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be greater than zero.");
        }
        this.weight = weight;
    }

    @Override public String getName() {return product.getName();}
    @Override public double getWeight() {return weight;}
    @Override public int getId() {return product.getId();}
    @Override public double getPrice() {return product.getPrice();}
    @Override public int getStock() {return product.getStock();}
    @Override public void setId(int id) {product.setId(id);}
    @Override public void setName(String name) {product.setName(name);}
    @Override public void setPrice(double price) {product.setPrice(price);}
    @Override public void setStock(int stock) {product.setStock(stock);}
}
