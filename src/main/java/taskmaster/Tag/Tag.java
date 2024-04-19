package taskmaster.Tag;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import taskmaster.Task.Task;

import java.util.List;

@Data
@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    @NotEmpty
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String title;

    @ManyToMany(mappedBy = "tags", cascade = CascadeType.ALL)
    private List<Task> tasks;
}
