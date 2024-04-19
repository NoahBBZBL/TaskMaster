package taskmaster;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import taskmaster.Category.Category;
import taskmaster.Category.CategoryRepository;

@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class DBTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void insertCategory() {
        Category objCat = this.categoryRepository.save(new Category("Schule", "Red"));
        Assertions.assertNotNull(objCat.getClass());
        Category objCat1 = this.categoryRepository.save(new Category("Arbeit", "Blue"));
        Assertions.assertNotNull(objCat1.getClass());
    }
}
