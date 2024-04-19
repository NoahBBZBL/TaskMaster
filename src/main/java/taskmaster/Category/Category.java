package taskmaster.Category;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import taskmaster.Task.Task;

@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(nullable = false)
    @NotEmpty
    private String title;

    @Column(nullable = true)
    private String color;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public Category(String title, String color) {
        this.title = title;
        this.color = color;
    }

    public Category() {
    }


}
