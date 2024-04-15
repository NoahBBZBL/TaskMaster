package taskmaster.Task_Tag;

import jakarta.persistence.*;
import lombok.Data;
import taskmaster.Tag.Tag;
import taskmaster.Task.Task;

@Data
@Entity
@Table(name = "task_tag")
public class Task_Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_tag_id")
    private Long taskTagId;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "tag_id", referencedColumnName = "tag_id")
    private Tag tag;

    public Task_Tag(Task task, Tag tag) {
        this.task = task;
        this.tag = tag;
    }

}
