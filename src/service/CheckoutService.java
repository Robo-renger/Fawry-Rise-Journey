package service;
import model.Cart;
import model.Customer;
public class CheckoutService {
    private Cart cart;
    private Customer customer;

    public CheckoutService(Cart cart, Customer customer) {
        this.cart = cart;
        this.customer = customer;
    }

    public void checkout() {
        double totalPrice = cart.calculateTotalPrice();
        if (this.cart.getProducts().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty. Please add products before checkout.");
        }
        if (totalPrice > customer.getBalance()) {
            throw new IllegalArgumentException("Insufficient balance for checkout. Required: " + totalPrice + ", Available: " + customer.getBalance());
        }
        customer.setBalance(customer.getBalance() - totalPrice);
        cart.clear();
    }
    public void clear() {
        this.cart = new Cart();
    }
}
