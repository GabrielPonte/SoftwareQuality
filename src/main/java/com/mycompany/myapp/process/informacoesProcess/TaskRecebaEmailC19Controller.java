package com.mycompany.myapp.process.informacoesProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/informacoes-process/task-receba-email-c-19")
public class TaskRecebaEmailC19Controller {

    private final Logger log = LoggerFactory.getLogger(TaskRecebaEmailC19Controller.class);

    private final TaskRecebaEmailC19Service taskRecebaEmailC19Service;

    public TaskRecebaEmailC19Controller(TaskRecebaEmailC19Service taskRecebaEmailC19Service) {
        this.taskRecebaEmailC19Service = taskRecebaEmailC19Service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskRecebaEmailC19ContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskRecebaEmailC19ContextDTO taskRecebaEmailC19Context = taskRecebaEmailC19Service.loadContext(id);
        return ResponseEntity.ok(taskRecebaEmailC19Context);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskRecebaEmailC19ContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskRecebaEmailC19ContextDTO taskRecebaEmailC19Context = taskRecebaEmailC19Service.claim(id);
        return ResponseEntity.ok(taskRecebaEmailC19Context);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskRecebaEmailC19ContextDTO taskRecebaEmailC19Context) {
        log.debug("REST request to complete InformacoesProcess.TaskRecebaEmailC19 {}", taskRecebaEmailC19Context.getTaskInstance().getId());
        taskRecebaEmailC19Service.complete(taskRecebaEmailC19Context);
        return ResponseEntity.noContent().build();
    }
}
