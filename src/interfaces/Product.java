package interfaces;

public interface Product {
    int getId();
    String getName();
    double getPrice();
    int getStock();
    void setId(int id);
    void setName(String name);
    void setPrice(double price);
    void setStock(int quantity);
}
