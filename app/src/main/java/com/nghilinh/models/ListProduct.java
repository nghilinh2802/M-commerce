package com.nghilinh.models;

import java.io.Serializable;
import java.util.ArrayList;

public class ListProduct implements Serializable {
    private ArrayList<Product> products;

    public ListProduct() {
        products = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void addProduct(Product pr) {
        products.add(pr);
    }

    public void addSampleProducts() {
        products.add(new Product(1, "Laptop", 10, 799.99, 1, "High-performance laptop"));  // Electronics
        products.add(new Product(2, "Smartphone", 20, 599.99, 2, "Latest model smartphone"));  // Clothing
        products.add(new Product(3, "Headphones", 50, 199.99, 1, "Noise-canceling headphones"));  // Electronics
        products.add(new Product(4, "Desk", 15, 129.99, 3, "Ergonomic desk for home office"));  // Home Appliances
        products.add(new Product(5, "Chair", 30, 89.99, 3, "Comfortable office chair"));  // Home Appliances
        products.add(new Product(6, "Smartwatch", 25, 199.99, 2, "Smartwatch with fitness tracking"));  // Clothing
        products.add(new Product(7, "Keyboard", 40, 49.99, 1, "Mechanical keyboard with RGB"));  // Electronics
        products.add(new Product(8, "Mouse", 60, 29.99, 1, "Wireless mouse"));  // Electronics
        products.add(new Product(9, "Monitor", 12, 299.99, 3, "27-inch 4K monitor"));  // Home Appliances
        products.add(new Product(10, "Speakers", 8, 99.99, 1, "Bluetooth portable speakers"));  // Electronics

        products.add(new Product(11, "Tablet", 35, 349.99, 2, "Portable and lightweight tablet"));  // Clothing
        products.add(new Product(12, "Webcam", 45, 59.99, 3, "HD webcam for streaming"));  // Home Appliances
        products.add(new Product(13, "Router", 18, 89.99, 1, "High-speed Wi-Fi router"));  // Electronics
        products.add(new Product(14, "Projector", 10, 299.99, 3, "Portable mini projector"));  // Home Appliances
        products.add(new Product(15, "Microwave", 12, 129.99, 3, "Compact microwave oven"));  // Home Appliances
        products.add(new Product(16, "Refrigerator", 8, 499.99, 3, "Energy-efficient refrigerator"));  // Home Appliances
        products.add(new Product(17, "Washing Machine", 6, 349.99, 3, "Top-load washing machine"));  // Home Appliances
        products.add(new Product(18, "Camera", 20, 399.99, 1, "Digital camera with 4K recording"));  // Electronics
        products.add(new Product(19, "Electric Kettle", 25, 29.99, 3, "Cordless electric kettle"));  // Home Appliances
        products.add(new Product(20, "Blender", 30, 49.99, 3, "High-speed blender for smoothies"));  // Home Appliances

        products.add(new Product(21, "Air Fryer", 22, 119.99, 3, "Healthy cooking with air fryer"));  // Home Appliances
        products.add(new Product(22, "Hair Dryer", 40, 29.99, 3, "Professional hair dryer"));  // Home Appliances
        products.add(new Product(23, "Vacuum Cleaner", 10, 149.99, 3, "Cordless vacuum cleaner"));  // Home Appliances
        products.add(new Product(24, "Electric Toothbrush", 50, 39.99, 3, "Smart electric toothbrush"));  // Home Appliances
        products.add(new Product(25, "Coffee Maker", 15, 99.99, 3, "Espresso coffee maker"));  // Home Appliances
        products.add(new Product(26, "Printer", 30, 79.99, 1, "Wireless inkjet printer"));  // Electronics
        products.add(new Product(27, "Flash Drive", 50, 19.99, 1, "32GB USB flash drive"));  // Electronics
        products.add(new Product(28, "Hard Drive", 18, 79.99, 1, "1TB external hard drive"));  // Electronics
        products.add(new Product(29, "Smart TV", 5, 399.99, 3, "50-inch smart TV with 4K support"));  // Home Appliances
        products.add(new Product(30, "Bluetooth Speaker", 60, 49.99, 1, "Portable Bluetooth speaker"));  // Electronics
    }
}
