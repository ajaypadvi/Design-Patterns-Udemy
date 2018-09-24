package com.ajay.udemy.design.patterns.creational.section05.prototype.copy.constructor;

class Address {
    public String streetAddress, city, country;

    public Address(String streetAddress, String city, String country) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
    }

    /**
     * Copy Constructor for an {@link Address} object
     *
     * @param other an {@link Address} object of which copy needs to be made
     */
    public Address(Address other) {
        this(other.streetAddress, other.city, other.country);
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

class Employee {
    public String name;
    public Address address;

    public Employee(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    /**
     * Copy Constructor for an {@link Employee} object
     *
     * @param other An Employee object of which copy needs to be made
     */
    public Employee(Employee other) {
        name = other.name;
        address = new Address(other.address);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}

class CopyConstructor {
    public static void main(String[] args) {
        Employee john = new Employee("John",
                new Address("123 London Road", "London", "UK"));

        //Employee chris = john;
        Employee chris = new Employee(john);

        chris.name = "Chris";
        System.out.println(john);
        System.out.println(chris);
    }
}