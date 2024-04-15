package taskmaster.Tag;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import taskmaster.MessageResponse;
import taskmaster.security.Roles;

import java.util.List;

@RestController
@SecurityRequirement(name = "admin")
@Validated
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("api/tag")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Tag>> all() {
        List<Tag> result = tagService.getTags();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("api/tag/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Tag> one(@PathVariable Long id) {
        Tag tag = tagService.getTag(id);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @PostMapping("api/tag")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Tag> newTag(@Valid @RequestBody Tag tag) {
        Tag savedTag = tagService.insertTag(tag);
        return new ResponseEntity<>(savedTag, HttpStatus.OK);
    }

    @PutMapping("api/tag/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Tag> updateTag(@Valid @RequestBody Tag tag, @PathVariable Long id) {
        Tag savedTag = tagService.updateTag(tag, id);
        return new ResponseEntity<>(savedTag, HttpStatus.OK);
    }

    @DeleteMapping("api/tag/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MessageResponse> deleteTag(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(tagService.deleteTag(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
