package petShop.persistence.Dao;

import petShop.domain.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProductListByCategory(String categoryId);

    Product getProduct(String productId);

    List<Product> searchProductList(String keywords);

}
