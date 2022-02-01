package com.mycompany.myapp.process.agendamentoProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agendamento-process/agendar-teste-covid")
public class AgendarTesteCovidController {

    private final Logger log = LoggerFactory.getLogger(AgendarTesteCovidController.class);

    private final AgendarTesteCovidService agendarTesteCovidService;

    public AgendarTesteCovidController(AgendarTesteCovidService agendarTesteCovidService) {
        this.agendarTesteCovidService = agendarTesteCovidService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendarTesteCovidContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        AgendarTesteCovidContextDTO agendarTesteCovidContext = agendarTesteCovidService.loadContext(id);
        return ResponseEntity.ok(agendarTesteCovidContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<AgendarTesteCovidContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        AgendarTesteCovidContextDTO agendarTesteCovidContext = agendarTesteCovidService.claim(id);
        return ResponseEntity.ok(agendarTesteCovidContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody AgendarTesteCovidContextDTO agendarTesteCovidContext) {
        log.debug("REST request to complete AgendamentoProcess.AgendarTesteCovid {}", agendarTesteCovidContext.getTaskInstance().getId());
        agendarTesteCovidService.complete(agendarTesteCovidContext);
        return ResponseEntity.noContent().build();
    }
}
