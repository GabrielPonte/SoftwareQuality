package com.mycompany.myapp.process.agendamentoProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agendamento-process/buscar-posto-saude")
public class BuscarPostoSaudeController {

    private final Logger log = LoggerFactory.getLogger(BuscarPostoSaudeController.class);

    private final BuscarPostoSaudeService buscarPostoSaudeService;

    public BuscarPostoSaudeController(BuscarPostoSaudeService buscarPostoSaudeService) {
        this.buscarPostoSaudeService = buscarPostoSaudeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuscarPostoSaudeContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        BuscarPostoSaudeContextDTO buscarPostoSaudeContext = buscarPostoSaudeService.loadContext(id);
        return ResponseEntity.ok(buscarPostoSaudeContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<BuscarPostoSaudeContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        BuscarPostoSaudeContextDTO buscarPostoSaudeContext = buscarPostoSaudeService.claim(id);
        return ResponseEntity.ok(buscarPostoSaudeContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody BuscarPostoSaudeContextDTO buscarPostoSaudeContext) {
        log.debug("REST request to complete AgendamentoProcess.BuscarPostoSaude {}", buscarPostoSaudeContext.getTaskInstance().getId());
        buscarPostoSaudeService.complete(buscarPostoSaudeContext);
        return ResponseEntity.noContent().build();
    }
}
