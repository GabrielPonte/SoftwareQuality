package com.mycompany.myapp.process.agendamentoProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agendamento-process/preencher-dados")
public class PreencherDadosController {

    private final Logger log = LoggerFactory.getLogger(PreencherDadosController.class);

    private final PreencherDadosService preencherDadosService;

    public PreencherDadosController(PreencherDadosService preencherDadosService) {
        this.preencherDadosService = preencherDadosService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreencherDadosContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        PreencherDadosContextDTO preencherDadosContext = preencherDadosService.loadContext(id);
        return ResponseEntity.ok(preencherDadosContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<PreencherDadosContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        PreencherDadosContextDTO preencherDadosContext = preencherDadosService.claim(id);
        return ResponseEntity.ok(preencherDadosContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody PreencherDadosContextDTO preencherDadosContext) {
        log.debug("REST request to complete AgendamentoProcess.PreencherDados {}", preencherDadosContext.getTaskInstance().getId());
        preencherDadosService.complete(preencherDadosContext);
        return ResponseEntity.noContent().build();
    }
}
