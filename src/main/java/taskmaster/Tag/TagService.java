package taskmaster.Tag;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import taskmaster.MessageResponse;

import java.util.List;

@Service
public class TagService {
    private final TagRepository repository;

    public TagService(TagRepository tagRepository) {
        this.repository = tagRepository;
    }

    public List<Tag> getTags() {
        return repository.findByOrderByNameAsc();
    }

    public Tag getTag(Long id) {
        return repository.findById(String.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("Tag with id: " + id + " not found"));
    }

    public Tag insertTag(Tag tag) {
        return repository.save(tag);
    }

    public Tag updateTag(Tag tag, Long id) {
        return repository.findById(String.valueOf(id))
                .map(tagOrig -> {
                    tagOrig.setName(tag.getName());
                    return repository.save(tagOrig);
                })
                .orElseGet(() -> repository.save(tag));
    }

    public MessageResponse deleteTag(Long id) {
        repository.deleteById(String.valueOf(id));
        return new MessageResponse("Tag " + id + " deleted");
    }
}
