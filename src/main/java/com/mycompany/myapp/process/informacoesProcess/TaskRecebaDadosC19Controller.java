package com.mycompany.myapp.process.informacoesProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/informacoes-process/task-receba-dados-c-19")
public class TaskRecebaDadosC19Controller {

    private final Logger log = LoggerFactory.getLogger(TaskRecebaDadosC19Controller.class);

    private final TaskRecebaDadosC19Service taskRecebaDadosC19Service;

    public TaskRecebaDadosC19Controller(TaskRecebaDadosC19Service taskRecebaDadosC19Service) {
        this.taskRecebaDadosC19Service = taskRecebaDadosC19Service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskRecebaDadosC19ContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskRecebaDadosC19ContextDTO taskRecebaDadosC19Context = taskRecebaDadosC19Service.loadContext(id);
        return ResponseEntity.ok(taskRecebaDadosC19Context);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskRecebaDadosC19ContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskRecebaDadosC19ContextDTO taskRecebaDadosC19Context = taskRecebaDadosC19Service.claim(id);
        return ResponseEntity.ok(taskRecebaDadosC19Context);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskRecebaDadosC19ContextDTO taskRecebaDadosC19Context) {
        log.debug("REST request to complete InformacoesProcess.TaskRecebaDadosC19 {}", taskRecebaDadosC19Context.getTaskInstance().getId());
        taskRecebaDadosC19Service.complete(taskRecebaDadosC19Context);
        return ResponseEntity.noContent().build();
    }
}
