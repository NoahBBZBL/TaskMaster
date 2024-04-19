package taskmaster.Tag;

import org.springframework.stereotype.Service;
import taskmaster.storage.EntityNotFoundException;

import java.util.List;

@Service
public class TagService {
    private final TagRepository repository;

    public TagService(TagRepository tagRepository) {
        this.repository = tagRepository;
    }

    public List<Tag> getAllTags() {
        return repository.findByOrderByTitleAsc();
    }

    public Tag getTag(Long id) {
        return repository.findById(String.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException(id, Tag.class));
    }

    public Tag insertTag(Tag tag) {
        return repository.save(tag);
    }

    public Tag updateTag(Tag tag, Long id) {
        return repository.findById(String.valueOf(id))
                .map(tagOrig -> {
                    tagOrig.setTitle(tag.getTitle());
                    return repository.save(tagOrig);
                })
                .orElseGet(() -> repository.save(tag));
    }

    public void deleteTag(Long id) {
        if (!repository.existsById(id.toString())) {
            throw new EntityNotFoundException(id, Tag.class);
        }
        repository.deleteById(id.toString());
    }
}
