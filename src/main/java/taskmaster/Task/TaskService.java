package taskmaster.Task;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import taskmaster.Message.MessageResponse;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository taskRepository) {
        this.repository = taskRepository;
    }

    public List<Task> getTasks() {
        return repository.findByOrderByTitleAsc();
    }

    public Task getTask(Long id) {
        return repository.findById(String.valueOf(id)).orElseThrow(() -> new EntityNotFoundException("Task with id: " + id + " not found"));
    }

    public Task insertTask(Task task) {
        return repository.save(task);
    }

    public Task updateTask(Task task, Long id) {
        return repository.findById(String.valueOf(id)).map(taskOrig -> {
            taskOrig.setTitle(task.getTitle());
            return repository.save(taskOrig);
        }).orElseGet(() -> repository.save(task));
    }

    public MessageResponse deleteTask(Long id) {
        repository.deleteById(String.valueOf(id));
        return new MessageResponse("Task " + id + " deleted");
    }
}
