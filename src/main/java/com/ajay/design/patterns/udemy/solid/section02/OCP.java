package com.ajay.design.patterns.udemy.solid.section02;

import java.util.List;
import java.util.stream.Stream;

enum Color {
    RED, GREEN, BLUE
}

enum Size {
    SMALL, MEDIUM, LARGE, YUGE
}

enum Category {
    NATURAL,
    MANMADE;
}

class Product {
    public String name;
    public Color color;
    public Size size;
    public Category category;

    public Product(String name, Color color, Size size, Category category) {
        this.name = name;
        this.color = color;
        this.size = size;
        this.category = category;
    }
}

class ProductFilter {
    public Stream<Product> filterByColor(List<Product> products, Color color) {
        return products.stream().filter(p -> p.color == color);
    }

    public Stream<Product> filterBySize(List<Product> products, Size size) {
        return products.stream().filter(p -> p.size == size);
    }

    public Stream<Product> filterBySizeAndColor(List<Product> products, Size size, Color color) {
        return products.stream().filter(p -> p.size == size && p.color == color);
    }
    // state space explosion
    // 3 criteria = 7 methods
    // if there is an inclusion of third criteria, lets say 'category' then we will have overall of 7 methods in total to filter by considering all cases of criterion
    /*
     * Size Color Category
     * 0    0       0
     * 0    0       1
     * 0    1       0
     * 0    1       1
     * 1    0       0
     * 1    0       1
     * 1    1       0
     * 1    1       1
     */

}

// we introduce two new interfaces that are open for extension
interface Specification<T> {
    boolean isSatisfied(T item);
}

interface Filter<T> {
    Stream<T> filter(List<T> items, Specification<T> spec);
}

class ColorSpecification implements Specification<Product> {
    private Color color;

    public ColorSpecification(Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(Product p) {
        return p.color == color;
    }
}

class SizeSpecification implements Specification<Product> {
    private Size size;

    public SizeSpecification(Size size) {
        this.size = size;
    }

    @Override
    public boolean isSatisfied(Product p) {
        return p.size == size;
    }
}

class CategorySpecification implements Specification<Product> {
    private Category category;

    public CategorySpecification(Category category) {
        this.category = category;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.category == category;
    }
}

class AndSpecification<T> implements Specification<T> {
    private Specification<T> first, second;

    public AndSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied(T item) {
        return first.isSatisfied(item) && second.isSatisfied(item);
    }
}

class TripleAndSpecification<T> implements Specification<T> {
    private Specification<T> first, second, third;

    public TripleAndSpecification(Specification<T> first, Specification<T> second, Specification<T> third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public boolean isSatisfied(T item) {
        return first.isSatisfied(item) && second.isSatisfied(item) && third.isSatisfied(item);
    }

}

class BetterFilter implements Filter<Product> {
    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
        return items.stream().filter(p -> spec.isSatisfied(p));
    }
}

class OCP {
    public static void main(String[] args) {
        Product apple = new Product("Apple", Color.GREEN, Size.SMALL, Category.NATURAL);
        Product tree = new Product("Tree", Color.GREEN, Size.LARGE, Category.NATURAL);
        Product house = new Product("House", Color.BLUE, Size.LARGE, Category.MANMADE);

        List<Product> products = List.of(apple, tree, house);

        ProductFilter pf = new ProductFilter();
        System.out.println("Green products (old):");
        pf.filterByColor(products, Color.GREEN)
                .forEach(p -> System.out.println(" - " + p.name + " is green"));

        // ^^ BEFORE

        // vv AFTER
        BetterFilter bf = new BetterFilter();
        System.out.println("Green products (new):");
        bf.filter(products, new ColorSpecification(Color.GREEN))
                .forEach(p -> System.out.println(" - " + p.name + " is green"));

        System.out.println("Large products:");
        bf.filter(products, new SizeSpecification(Size.LARGE))
                .forEach(p -> System.out.println(" - " + p.name + " is large"));

        System.out.println("Natural products:");
        bf.filter(products, new CategorySpecification(Category.NATURAL))
                .forEach(p -> System.out.println(" - " + p.name + " is natural"));

        System.out.println("Large blue items:");
        bf.filter(products,
                new AndSpecification<>(
                        new ColorSpecification(Color.BLUE),
                        new SizeSpecification(Size.LARGE)
                ))
                .forEach(p -> System.out.println(" - " + p.name + " is large and blue"));

        System.out.println("Small green natural items:");
        bf.filter(products,
                new TripleAndSpecification<>(
                        new ColorSpecification(Color.GREEN),
                        new SizeSpecification(Size.SMALL),
                        new CategorySpecification(Category.NATURAL)
                ))
                .forEach(p -> System.out.println(" - " + p.name + " is small, green and manmade"));
    }
}
