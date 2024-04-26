package taskmaster.Category;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import taskmaster.Task.Task;

import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @NotEmpty
    @Column(nullable = false)
    private String title;

    @NotEmpty
    @Column(nullable = false)
    private String color;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    public Category(String title, String color) {
        this.title = title;
        this.color = color;
    }

    public Category() {
    }
}
