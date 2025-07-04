# Fawry Rise Journey - Ecommerce System

This project implements a modular shopping cart system in Java, demonstrating the use of **decorator pattern**, **interface-driven design**, and **service abstraction** for checkout and shipping logic.

## Key Features

### 1. Decorator Pattern for Product Features

Products can be dynamically extended with new behaviors (e.g., expirable, shippable) without modifying their core implementation. This is achieved using the decorator pattern.

**Example: Decorating a product as both expirable and shippable**

```java
import java.time.LocalDate;
import model.SimpleProduct;
import decorators.ExpirableProductDecorator;
import decorators.ShippableProductDecorator;

SimpleProduct milk = new SimpleProduct(2, "Milk", 5.0, 20);
ExpirableProductDecorator expirableMilk = new ExpirableProductDecorator(milk, LocalDate.now().plusDays(5));
ShippableProductDecorator shippableMilk = new ShippableProductDecorator(expirableMilk, 0.5);
```

### 2. Interface-Driven Design

All products implement the Product interface, and additional capabilities are defined by interfaces like Expirable and Shippable.

**Example: Product interface**

```java
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
```

### 3. Smart Cart Validation

The Cart class validates product stock and expiry before adding to the cart, preventing invalid operations.

**Example: Adding a product with validation**

```java
cart.addProduct(shippableMilk, 1);
```

### 4. Flexible Shipping Cost Calculation

The ShippingService calculates shipping costs based on product weight and applies a multiplier for expirable shippable products.

**Example: Shipping cost calculation**

```java
ShippingService shippingService = new ShippingService(cart);
double shippingCost = shippingService.calculateShippingCost();
```

### Critical Design Decisions

- Decorator pattern enables flexible product feature extension.

- Interface segregation keeps the codebase modular and testable.
- Service classes encapsulate business logic for checkout and shipping, promoting single responsibility

#
