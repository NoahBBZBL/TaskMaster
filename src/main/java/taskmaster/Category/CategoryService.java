package taskmaster.Category;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findByOrderByTitleAsc();
    }

    public Category getCategory(Long id) {
        return categoryRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("Category with id: " + id + " not found"));
    }

    public Category insertCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category, Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(String.valueOf(id));
        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setTitle(category.getTitle());
            return categoryRepository.save(existingCategory);
        } else {
            throw new EntityNotFoundException("Category with id: " + id + " not found");
        }
    }

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id.toString())) {
            throw new taskmaster.storage.EntityNotFoundException(id, Category.class);
        }
        categoryRepository.deleteById(id.toString());
    }
}
