package br.com.infnet.sobreviventesapi.api.controller;

import br.com.infnet.sobreviventesapi.api.dto.NovoSobreviventeRequest;
import br.com.infnet.sobreviventesapi.api.dto.SobreviventeResponse;
import br.com.infnet.sobreviventesapi.service.SobreviventeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sobreviventes")
@AllArgsConstructor
public class SobreviventeController {
    private final SobreviventeService sobreviventeService;

    @PostMapping
    public void cadastrar(@RequestBody NovoSobreviventeRequest novoSobreviventeRequest) {

    }

    @GetMapping("/{id}")
    public ResponseEntity<SobreviventeResponse> buscarPeloId(@PathVariable Long id) {
        return ResponseEntity.ok(this.sobreviventeService.buscarPeloId(id));
    }
}
