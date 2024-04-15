package taskmaster.Category;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import taskmaster.MessageResponse;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.repository = categoryRepository;
    }

    public List<Category> getCategories() {
        return repository.findByOrderByNameAsc();
    }

    public Category getCategory(Long id) {
        return repository.findById(String.valueOf(id)).orElseThrow(() -> new EntityNotFoundException("Category with id: " + id + " not found"));
    }

    public Category insertCategory(Category category) {
        return repository.save(category);
    }

    public Category updateCategory(Category category, Long id) {
        return repository.findById(String.valueOf(id)).map(categoryOrig -> {
            categoryOrig.setTitle(category.getTitle());
            return repository.save(categoryOrig);
        }).orElseGet(() -> repository.save(category));
    }

    public MessageResponse deleteCategory(Long id) {
        repository.deleteById(String.valueOf(id));
        return new MessageResponse("Category " + id + " deleted");
    }
}
