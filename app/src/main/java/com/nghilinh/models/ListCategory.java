package com.nghilinh.models;

import java.io.Serializable;
import java.util.ArrayList;

public class ListCategory implements Serializable {
    private ArrayList<Category>categories;
    public ListCategory(){categories=new ArrayList<>();}

    public ArrayList<Category> getCategories(){return categories;}

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;}
    public void addCategory(Category ca)
    {
        categories.add(ca);
    }

    public void addSampleCategories() {
        categories.add(new Category(1, "Electronics"));
        categories.add(new Category(2, "Clothing"));
        categories.add(new Category(3, "Home Appliances"));
        categories.add(new Category(4, "Books"));
        categories.add(new Category(5, "Sports"));
        categories.add(new Category(6, "Toys"));
        categories.add(new Category(7, "Beauty"));
        categories.add(new Category(8, "Food"));
        categories.add(new Category(9, "Furniture"));
        categories.add(new Category(10, "Automobiles"));
    }
}
