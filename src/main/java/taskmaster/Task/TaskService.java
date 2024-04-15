package taskmaster.Task;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
private final TaskRepository repository;

    public TaskService(TaskRepository taskRepository) {
        this.repository = taskRepository;
    }
    public List<Task> getTasks() {
        return repository.findByOrderByNameAsc();
    }
    public Task getTask(Integer id) {
        return repository.findById(String.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException(id, Task.class));
    }
}
