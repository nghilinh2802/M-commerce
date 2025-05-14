package com.nghilinh.connectors;

import com.nghilinh.models.Product;
import com.nghilinh.models.ListProduct;

import java.util.List;

public class ProductConnector {


    // Method to find a product by name
    public Product findProductByName(String productName) {
        ListProduct lp = new ListProduct();
        lp.addSampleProducts();
        for (Product pr : lp.getProducts()) {
            if (pr.getName().equalsIgnoreCase(productName)) {
                return pr;
            }
        }
        return null;
    }

    // Method to get all products
    public List<Product> get_all_products() {
        ListProduct lp = new ListProduct();
        lp.addSampleProducts(); // Adding sample products to the list
        return lp.getProducts(); // Returning the list of products
    }
}
