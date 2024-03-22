package org.csu.petstore;

import org.csu.petstore.entity.Category;
import org.csu.petstore.persistence.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PetStorePlusApplicationTests {
    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    void contextLoads() {
        Category category = (Category) categoryMapper.selectById("CATS");
        System.out.println(category);
    }

}
