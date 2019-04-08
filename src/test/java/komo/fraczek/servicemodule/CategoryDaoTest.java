package komo.fraczek.servicemodule;

import komo.fraczek.servicemodule.config.DbConfig;
import komo.fraczek.servicemodule.domain.Category;
import komo.fraczek.servicemodule.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DbConfig.class})
@ActiveProfiles("test")
public class CategoryDaoTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void when_categorySaved_then_findByName_shouldReturn_category(){
        Category category = createCategoryFake();
        categoryRepository.save(category);
        Optional<Category> byName = categoryRepository.findByName(category.getName());

        assertTrue(byName.isPresent());
        assertEquals(category.getName(), byName.get().getName());
    }

    @Test
    void when_categoryNotSaved_then_findByName_shouldReturn_OptionalEmpty(){
        Optional<Category> returned = categoryRepository.findByName("FakeName");

        assertFalse(returned.isPresent());
    }


    private Category createCategoryFake(){
        return new Category(1L,"categoryStringFake");
    }
}
