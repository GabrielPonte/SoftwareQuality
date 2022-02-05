package com.mycompany.myapp.process.informacoesProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/informacoes-process/task-receba-local-c-19")
public class TaskRecebaLocalC19Controller {

    private final Logger log = LoggerFactory.getLogger(TaskRecebaLocalC19Controller.class);

    private final TaskRecebaLocalC19Service taskRecebaLocalC19Service;

    public TaskRecebaLocalC19Controller(TaskRecebaLocalC19Service taskRecebaLocalC19Service) {
        this.taskRecebaLocalC19Service = taskRecebaLocalC19Service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskRecebaLocalC19ContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskRecebaLocalC19ContextDTO taskRecebaLocalC19Context = taskRecebaLocalC19Service.loadContext(id);
        return ResponseEntity.ok(taskRecebaLocalC19Context);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskRecebaLocalC19ContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskRecebaLocalC19ContextDTO taskRecebaLocalC19Context = taskRecebaLocalC19Service.claim(id);
        return ResponseEntity.ok(taskRecebaLocalC19Context);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskRecebaLocalC19ContextDTO taskRecebaLocalC19Context) {
        log.debug("REST request to complete InformacoesProcess.TaskRecebaLocalC19 {}", taskRecebaLocalC19Context.getTaskInstance().getId());
        taskRecebaLocalC19Service.complete(taskRecebaLocalC19Context);
        return ResponseEntity.noContent().build();
    }
}
