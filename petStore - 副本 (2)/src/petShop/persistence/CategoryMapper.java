package petShop.persistence;

import petShop.domain.Category;

import java.util.List;

public interface CategoryMapper {
    Category selectAll() ;

    List<Category> getCategoryList();

    Category getCategory(String categoryId);
}
