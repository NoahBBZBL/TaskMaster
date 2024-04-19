package taskmaster.Category;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import taskmaster.security.Roles;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("api/category")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Category>> all() {
        List<Category> result = categoryService.getCategories();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("api/category/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Category> one(@PathVariable Long id) {
        Category category = categoryService.getCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("api/category")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Category> newCategory(@Valid @RequestBody Category category) {
        Category savedCategory = categoryService.insertCategory(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.OK);
    }

    @PutMapping("api/category/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category, @PathVariable Long id) {
        Category savedCategory = categoryService.updateCategory(category, id);
        return new ResponseEntity<>(savedCategory, HttpStatus.OK);
    }

    @DeleteMapping("api/category/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
