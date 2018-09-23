package com.ajay.udemy.design.patterns.creational.section04.factories.exercise;

class Person {

    public String name;
    public int id;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{person=[id=" + id + ",name=" + name + "]}";
    }
}

class PersonFactory {
    private static int count = 0;

    public static Person createPerson(String name) {
        return new Person(++count, name);
    }
}

public class PersonFactoryDemo {
    public static void main(String[] args) {
        System.out.println(PersonFactory.createPerson("ajay"));
        System.out.println(PersonFactory.createPerson("chandrika"));
    }
}
