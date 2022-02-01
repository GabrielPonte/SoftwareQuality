package com.mycompany.myapp.process.informacoesProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/informacoes-process/task-agendar")
public class TaskAgendarController {

    private final Logger log = LoggerFactory.getLogger(TaskAgendarController.class);

    private final TaskAgendarService taskAgendarService;

    public TaskAgendarController(TaskAgendarService taskAgendarService) {
        this.taskAgendarService = taskAgendarService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskAgendarContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskAgendarContextDTO taskAgendarContext = taskAgendarService.loadContext(id);
        return ResponseEntity.ok(taskAgendarContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskAgendarContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskAgendarContextDTO taskAgendarContext = taskAgendarService.claim(id);
        return ResponseEntity.ok(taskAgendarContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskAgendarContextDTO taskAgendarContext) {
        log.debug("REST request to complete InformacoesProcess.TaskAgendar {}", taskAgendarContext.getTaskInstance().getId());
        taskAgendarService.complete(taskAgendarContext);
        return ResponseEntity.noContent().build();
    }
}
