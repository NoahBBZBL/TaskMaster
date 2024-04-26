package taskmaster;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmaster.Category.Category;
import taskmaster.Category.CategoryRepository;
import taskmaster.Category.CategoryService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryServiceTests {

    private CategoryService categoryService;
    private final CategoryRepository categoryRepositoryMock = mock(CategoryRepository.class);

    private final Category CategoryMock = mock(Category.class);

    @BeforeEach
    void setUp() {
        categoryService = new CategoryService(categoryRepositoryMock);
    }

    @Test
    void createCategory() {
        when(categoryRepositoryMock.save(CategoryMock)).thenReturn(CategoryMock);
        categoryService.insertCategory(CategoryMock);
        verify(categoryRepositoryMock, times(1)).save(any());
    }

    @Test
    void findCategory() {
        when(categoryRepositoryMock.findById(any())).thenReturn(Optional.ofNullable(CategoryMock));
        Category v = categoryService.getCategory(any());
        verify(categoryRepositoryMock, times(1)).findById(any());
    }

    @Test
    void deleteCategory() {
        Long id = 1L;
        when(categoryRepositoryMock.existsById(id.toString())).thenReturn(true);
        categoryService.deleteCategory(id);
        verify(categoryRepositoryMock, times(1)).deleteById(id.toString());
    }
}