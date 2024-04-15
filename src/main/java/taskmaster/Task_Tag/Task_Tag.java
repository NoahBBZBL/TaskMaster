package taskmaster.Task_Tag;

import jakarta.persistence.*;
import lombok.Data;
import taskmaster.Task.Task;
import taskmaster.Tag.Tag;

@Data
@Entity
@Table(name = "task_tag")
public class Task_Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_tag_id")
    private Long taskTagId;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public Task_Tag(Long taskTagId, Task task, Tag tag) {
        this.taskTagId = taskTagId;
        this.task = task;
        this.tag = tag;
    }

    public Task_Tag() {
    }
}
