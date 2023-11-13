package petShop.persistence.Dao;

import petShop.domain.Category;

import java.util.List;

public interface CategoryDao {

    List<Category> getCategoryList();

    Category getCategory(String categoryId);

}
