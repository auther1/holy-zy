package petShop.service;

import petShop.domain.Category;
import petShop.domain.Item;
import petShop.domain.Product;
import petShop.persistence.Dao.CategoryDao;
import petShop.persistence.Dao.ItemDao;
import petShop.persistence.Dao.ProductDao;
import petShop.persistence.impl.CategoryDaoImpl;
import petShop.persistence.impl.ItemDaoImpl;
import petShop.persistence.impl.ProductDaoImpl;

import java.util.List;

public class CatalogService {
    /*实现功能*/
    private CategoryDao categoryDao;
    private ItemDao itemDao;
    private ProductDao productDao;

    public CatalogService(){
        this.categoryDao = new CategoryDaoImpl();
        this.itemDao = new ItemDaoImpl();
        this.productDao = new ProductDaoImpl();
    }

    public List<Category> getCategoryList() {
        return categoryDao.getCategoryList();
    }

    public Category getCategory(String categoryId) {
        return categoryDao.getCategory(categoryId);
    }

    public Product getProduct(String productId) {
        return productDao.getProduct(productId);
    }

    public List<Product> getProductListByCategory(String categoryId) {
        return productDao.getProductListByCategory(categoryId);
    }

    // TODO enable using more than one keyword
    public List<Product> searchProductList(String keyword) {
        return productDao.searchProductList("%" + keyword.toLowerCase() + "%");
    }

    public List<Item> getItemListByProduct(String productId) {
        return itemDao.getItemListByProduct(productId);
    }

    public Item getItem(String itemId) {
        return itemDao.getItem(itemId);
    }

    public boolean isItemInStock(String itemId) {
        return itemDao.getInventoryQuantity(itemId) > 0;
    }

}
