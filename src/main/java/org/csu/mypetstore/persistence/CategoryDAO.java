package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Category;

import java.util.List;

public interface CategoryDAO {
    //Select all categoies
    List<Category> getCategoryList();

    //Select a category by Id
    Category getCategory(String categoryId);
}
