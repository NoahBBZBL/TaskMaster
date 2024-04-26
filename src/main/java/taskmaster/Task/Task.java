package taskmaster.Task;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import taskmaster.Category.Category;
import taskmaster.Tag.Tag;

import java.util.List;

@Data
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;

    @NotEmpty
    @Column(nullable = false)
    private String title;

    @NotEmpty
    @Column(nullable = false)
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "task_tag", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
