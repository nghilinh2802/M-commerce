//package com.nghilinh.connectors;
//
//import com.nghilinh.models.Category;
//import com.nghilinh.models.ListCategory;
//
//public class CategoryConnector {
//
//    // Method to find a category by name
//    public Category findCategoryByName(String categoryName) {
//        ListCategory lc = new ListCategory();
//        lc.addSampleCategories(); // Assuming the categories are pre-filled in ListCategory
//        for (Category cat : lc.getCategories()) {
//            if (cat.getName().equalsIgnoreCase(categoryName)) {
//                return cat; // Return the category if found
//            }
//        }
//        return null; // Return null if no category found
//    }
//
//}

package com.nghilinh.connectors;

import com.nghilinh.models.Category;
import com.nghilinh.models.ListCategory;

import java.util.List;

public class CategoryConnector {

    // Method to find a category by name
    public Category findCategoryByName(String categoryName) {
        ListCategory lc = new ListCategory();
        lc.addSampleCategories();
        for (Category cat : lc.getCategories()) {
            if (cat.getName().equalsIgnoreCase(categoryName)) {
                return cat;
            }
        }
        return null;
    }


    public List<Category> get_all_categories() {
        ListCategory lc = new ListCategory();
        lc.addSampleCategories();
        return lc.getCategories();
    }
}
