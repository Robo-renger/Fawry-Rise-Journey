package model;

public class Customer {
    private String name;
    private String email;
    private String phoneNumber;
    private double balance;

    public Customer(String name, String email, String phoneNumber, double balance) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }
        public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
