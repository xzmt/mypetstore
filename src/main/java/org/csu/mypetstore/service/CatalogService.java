package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.*;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.*;
import org.csu.mypetstore.persistence.ProductDAO;
import org.csu.mypetstore.persistence.impl.*;

import java.util.List;

public class CatalogService {
    private CategoryDAO categoryDAO;
    private ProductDAO productDAO;
    private ItemDAO itemDAO;

    public CatalogService()
    {
        categoryDAO = new CategoryDAOimpl();
        productDAO = new ProductDAOimpl();
        itemDAO = new ItemDAOimpl();
    }
    public List<Category> getCategoryList() {
        return categoryDAO.getCategoryList();
    }

    public Category getCategory(String categoryId) {

        return categoryDAO.getCategory(categoryId);
    }

    public Product getProduct(String productId) {
        return productDAO.getProduct(productId);
    }

    public List<Product> getProductListByCategory(String categoryId) {
        return productDAO.getProductListByCategory(categoryId);
    }

    // TODO enable using more than one keyword
    public List<Product> searchProductList(String keyword) {
        System.out.println(keyword);
        return productDAO.searchProductList("%" + keyword.toLowerCase() + "%");
    }
    public List<String> searchAllProductList(String username) {
        return productDAO.searchAllProductList(username);
    }
    public List<Item> getItemListByProduct(String productId) {
        return itemDAO.getItemListByProduct(productId);
    }

    public Item getItem(String itemId) {
        return itemDAO.getItem(itemId);
    }

    public boolean isItemInStock(String itemId) {
        return itemDAO.getInventoryQuantity(itemId) > 0;
    }


}
