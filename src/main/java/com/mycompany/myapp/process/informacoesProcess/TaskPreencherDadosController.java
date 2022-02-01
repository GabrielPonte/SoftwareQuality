package com.mycompany.myapp.process.informacoesProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/informacoes-process/task-preencher-dados")
public class TaskPreencherDadosController {

    private final Logger log = LoggerFactory.getLogger(TaskPreencherDadosController.class);

    private final TaskPreencherDadosService taskPreencherDadosService;

    public TaskPreencherDadosController(TaskPreencherDadosService taskPreencherDadosService) {
        this.taskPreencherDadosService = taskPreencherDadosService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskPreencherDadosContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskPreencherDadosContextDTO taskPreencherDadosContext = taskPreencherDadosService.loadContext(id);
        return ResponseEntity.ok(taskPreencherDadosContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskPreencherDadosContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskPreencherDadosContextDTO taskPreencherDadosContext = taskPreencherDadosService.claim(id);
        return ResponseEntity.ok(taskPreencherDadosContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskPreencherDadosContextDTO taskPreencherDadosContext) {
        log.debug("REST request to complete InformacoesProcess.TaskPreencherDados {}", taskPreencherDadosContext.getTaskInstance().getId());
        taskPreencherDadosService.complete(taskPreencherDadosContext);
        return ResponseEntity.noContent().build();
    }
}
