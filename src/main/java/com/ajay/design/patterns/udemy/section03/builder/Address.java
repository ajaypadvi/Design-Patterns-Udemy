package com.ajay.design.patterns.udemy.section03.builder;

/**
 * An example to verify builder generator plugin
 * Alt+Shift+B to generate builder class for which builder has not already generated
 *
 * @author Padvi
 */
public class Address {
    private final String firstName;
    private final String lastName;
    private final String city;
    private final Integer street;
    private final String zip;

    public Address(String firstName,
                   String lastName,
                   String city,
                   Integer street,
                   String zip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.street = street;
        this.zip = zip;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public Integer getStreet() {
        return street;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append("[firstname=" + firstName)
                .append(", lastname=" + lastName)
                .append(", city=" + city)
                .append(", street=" + street)
                .append(", zip=" + zip + "]");
        return sb.toString();
    }

    public static final class AddressBuilder {
        private String firstName;
        private String lastName;
        private String city;
        private Integer street;
        private String zip;

        private AddressBuilder() {
        }

        public static AddressBuilder anAddress() {
            return new AddressBuilder();
        }

        public AddressBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public AddressBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public AddressBuilder city(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder street(Integer street) {
            this.street = street;
            return this;
        }

        public AddressBuilder zip(String zip) {
            this.zip = zip;
            return this;
        }

        public AddressBuilder but() {
            return anAddress().firstName(firstName).lastName(lastName).city(city).street(street).zip(zip);
        }

        public Address build() {
            return new Address(firstName, lastName, city, street, zip);
        }

        public static void main(String[] args) {
            AddressBuilder builder = anAddress().firstName("ajay").lastName("padvi").city("mumbai").street(12345678).zip("4568900");
            Address address1 = builder.build();
            AddressBuilder duplicateBuilder = builder.but();
            duplicateBuilder.firstName("chandrika");
            Address address2 = duplicateBuilder.build();


            System.out.println("first address:" + address1);
            System.out.println("second address:" + address2);
        }
    }
}
