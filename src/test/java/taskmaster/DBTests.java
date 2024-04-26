package taskmaster;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import taskmaster.Category.Category;
import taskmaster.Category.CategoryRepository;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class DBTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void insertCategory() {
        Category schuleCategory = new Category("Schule", "Red");
        Category arbeitCategory = new Category("Arbeit", "Blue");

        Category savedSchuleCategory = categoryRepository.save(schuleCategory);
        Category savedArbeitCategory = categoryRepository.save(arbeitCategory);

        assertNotNull(savedSchuleCategory);
        assertNotNull(savedArbeitCategory);
    }

    @Test
    void getCategory() {
        Category schuleCategory = categoryRepository.save(new Category("Schule", "Red"));

        Category retrievedCategory = categoryRepository.findById(schuleCategory.getCategoryId().toString()).orElse(null);

        assertNotNull(retrievedCategory);
        assertEquals("Schule", retrievedCategory.getTitle());
        assertEquals("Red", retrievedCategory.getColor());
    }

    @Test
    void updateCategory() {
        Category schuleCategory = categoryRepository.save(new Category("Schule", "Red"));

        schuleCategory.setColor("Green");
        Category updatedCategory = categoryRepository.save(schuleCategory);
        Category retrievedCategory = categoryRepository.findById(updatedCategory.getCategoryId().toString()).orElse(null);

        assertNotNull(retrievedCategory);
        assertEquals("Schule", retrievedCategory.getTitle());
        assertEquals("Green", retrievedCategory.getColor());
    }

    @Test
    void deleteCategory() {
        Category schuleCategory = categoryRepository.save(new Category("Schule", "Red"));

        categoryRepository.delete(schuleCategory);
        Category deletedCategory = categoryRepository.findById(schuleCategory.getCategoryId().toString()).orElse(null);

        assertNull(deletedCategory);
    }
}
