package br.com.infnet.sobreviventesapi.service;

import br.com.infnet.sobreviventesapi.api.dto.ComunidadeResponse;
import br.com.infnet.sobreviventesapi.api.dto.RecursoResponse;
import br.com.infnet.sobreviventesapi.api.dto.SobreviventeResponse;
import br.com.infnet.sobreviventesapi.api.exception.EntidadeNaoLocalizadaException;
import br.com.infnet.sobreviventesapi.model.Recurso;
import br.com.infnet.sobreviventesapi.model.Sobrevivente;
import br.com.infnet.sobreviventesapi.repository.SobreviventeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SobreviventeService {
    private final SobreviventeRepository sobreviventeRepository;

    private SobreviventeResponse toResponse(Sobrevivente saved) {
        return new SobreviventeResponse(
                saved.getId(),saved.getNome(),saved.getLocalizacao(), saved.isInfectado(),
                saved.getRecursos().stream()
                        .map(r -> new RecursoResponse(r.getId(),r.getNome(),r.getQuantidade()))
                        .toList(),
                saved.getComunidades().stream()
                        .map(c -> new ComunidadeResponse(c.getId(), c.getNome(), c.isZonaSegura()))
                        .collect(Collectors.toSet())
        );
    }

    @Transactional(readOnly = true)
    public SobreviventeResponse buscarPeloId(Long id) {
        Sobrevivente saved = sobreviventeRepository.findById(id).orElseThrow(() ->
                new EntidadeNaoLocalizadaException("Sobrevivente não localizado"));
        return toResponse(saved);
    }
}
