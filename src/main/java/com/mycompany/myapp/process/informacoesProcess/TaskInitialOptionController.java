package com.mycompany.myapp.process.informacoesProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/informacoes-process/task-initial-option")
public class TaskInitialOptionController {

    private final Logger log = LoggerFactory.getLogger(TaskInitialOptionController.class);

    private final TaskInitialOptionService taskInitialOptionService;

    public TaskInitialOptionController(TaskInitialOptionService taskInitialOptionService) {
        this.taskInitialOptionService = taskInitialOptionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskInitialOptionContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskInitialOptionContextDTO taskInitialOptionContext = taskInitialOptionService.loadContext(id);
        return ResponseEntity.ok(taskInitialOptionContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskInitialOptionContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskInitialOptionContextDTO taskInitialOptionContext = taskInitialOptionService.claim(id);
        return ResponseEntity.ok(taskInitialOptionContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskInitialOptionContextDTO taskInitialOptionContext) {
        log.debug("REST request to complete InformacoesProcess.TaskInitialOption {}", taskInitialOptionContext.getTaskInstance().getId());
        taskInitialOptionService.complete(taskInitialOptionContext);
        return ResponseEntity.noContent().build();
    }
}
