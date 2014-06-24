package models;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private static List<Product> products;

    static {
        products = new ArrayList<Product>();
        products.add(new Product("111111", "Paperclips 1", "Paperclips description 1"));
        products.add(new Product("222222", "Paperclips 2", "Paperclips description 2"));
        products.add(new Product("333333", "Paperclips 1", "Paperclips description 3"));
        products.add(new Product("444444", "Paperclips 1", "Paperclips description 4"));
        products.add(new Product("555555", "Paperclips 1", "Paperclips description 5"));
        products.add(new Product("666666", "Paperclips 1", "Paperclips description 6"));
    }

    public String ean;
    public String name;
    public String description;

    public Product() {}

    public Product(String ean, String name, String description){
        this.ean = ean;
        this.name = name;
        this.description = description;
    }

    public String toString(){
        return String.format("%s - %s", ean, name);
    }

    public static List<Product> findAll() {
        return new ArrayList<Product>(products);
    }

    public static Product findByEan(String ean){
        for (Product candidate:products){
            if (candidate.ean.equals(ean)){
                return candidate;
            }
        }
        return null;
    }

    public static List<Product> findByName(String name){
        final List<Product> results = new ArrayList<Product>();
        for (Product candidate:products){
            if (candidate.name.toLowerCase().contains(name.toLowerCase())){
                results.add(candidate);
            }
        }
        return results;
    }

    public static boolean remove(Product product){
        return products.remove(product);
    }

    public void save(){
        products.remove(findByEan(this.ean));
        products.add(this);
    }

}