package taskmaster.Task;


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

    @PostMapping("api/task")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Task> newTask(@Valid @RequestBody Task Task) {
        Task savedTask = TaskService.insertTask(Task);
        return new ResponseEntity<>(savedTask, HttpStatus.OK);
    }

    @PutMapping("api/task/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Task> updateTask(@Valid @RequestBody Task task, @PathVariable Long id) {
        Task savedTask = TaskService.updateTask(task, id);
        return new ResponseEntity<>(savedTask, HttpStatus.OK);
    }

    @DeleteMapping("api/task/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MessageResponse> deleteTask(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(taskService.deleteTask(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }


    }
}
