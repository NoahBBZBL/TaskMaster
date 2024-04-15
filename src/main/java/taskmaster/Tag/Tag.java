package taskmaster.Tag;

import jakarta.persistence.*;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Data
@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    @Column(nullable = false)
    @NotEmpty()
    @Size(max = 255)
    private String name;

    public Tag(Long tagId, String name) {
        this.tagId = tagId;
        this.name = name;
    }

    public Tag() {
    }
}
