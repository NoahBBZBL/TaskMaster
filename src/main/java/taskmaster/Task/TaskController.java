package taskmaster.Task;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import taskmaster.security.Roles;

import java.util.List;

@RestController
@SecurityRequirement(name = "admin")
@Validated
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("api/task")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Task>> all() {
        List<Task> result = taskService.getTasks();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("api/task/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Task> one(@PathVariable Long id) {
        Task task = TaskService.getTask(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }
}
