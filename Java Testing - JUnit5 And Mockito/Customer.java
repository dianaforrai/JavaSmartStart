package com.gl.app.model;

public class Customer {
    private int customerid;
    private String firstName;
    private String lastName;
    private String email;

    public Customer (int customerid, String firstName, String lastName, String email) {

        this.customerid = customerid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    public int getCustomerid() {
        return customerid;
    }
    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void display(){
        System.out.println("Customer ID: " + customerid);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Email: " + email);
    }
    @Override
    public String toString() {
        return "Customer{" + "customerid=" + customerid + ", firstName='" + firstName + '\'' + ", lastName='"
                + lastName + '\'' + ", email='" + email + '\'' + '}';
    }
    public static void main (String[] args) {
        Customer firstcustomer = new Customer (1, "Diana", "Forrai","diana.forrai12@yahoo.com");
        System.out.println(firstcustomer);
        Customer secondcustomer = new Customer(2,"Bogdan","Bargaoanu","bn19bba@yahoo.com");
        System.out.println(secondcustomer);
        Customer thirdcustomer = new Customer(3,"Feri","Forrai","ferifor@yahoo.com");
        System.out.println(thirdcustomer);

    }

    public String getCustomerName() {
        return firstName + " " + lastName;
    }
}

