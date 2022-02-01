package com.mycompany.myapp.process.informacoesProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/informacoes-process/task-busca-posto")
public class TaskBuscaPostoController {

    private final Logger log = LoggerFactory.getLogger(TaskBuscaPostoController.class);

    private final TaskBuscaPostoService taskBuscaPostoService;

    public TaskBuscaPostoController(TaskBuscaPostoService taskBuscaPostoService) {
        this.taskBuscaPostoService = taskBuscaPostoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskBuscaPostoContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskBuscaPostoContextDTO taskBuscaPostoContext = taskBuscaPostoService.loadContext(id);
        return ResponseEntity.ok(taskBuscaPostoContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskBuscaPostoContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskBuscaPostoContextDTO taskBuscaPostoContext = taskBuscaPostoService.claim(id);
        return ResponseEntity.ok(taskBuscaPostoContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskBuscaPostoContextDTO taskBuscaPostoContext) {
        log.debug("REST request to complete InformacoesProcess.TaskBuscaPosto {}", taskBuscaPostoContext.getTaskInstance().getId());
        taskBuscaPostoService.complete(taskBuscaPostoContext);
        return ResponseEntity.noContent().build();
    }
}
