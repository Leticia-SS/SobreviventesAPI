package br.com.infnet.sobreviventesapi.service;

import br.com.infnet.sobreviventesapi.api.dto.ComunidadeResponse;
import br.com.infnet.sobreviventesapi.api.dto.RecursoResponse;
import br.com.infnet.sobreviventesapi.api.dto.SobreviventeResponse;
import br.com.infnet.sobreviventesapi.api.dto.SobreviventeSimplesResponse;
import br.com.infnet.sobreviventesapi.api.exception.EntidadeNaoLocalizadaException;
import br.com.infnet.sobreviventesapi.model.Sobrevivente;
import br.com.infnet.sobreviventesapi.repository.SobreviventeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    private SobreviventeSimplesResponse toSimpleResponse(Sobrevivente saved) {
        return new SobreviventeSimplesResponse(
                saved.getId(),saved.getNome(),saved.getLocalizacao(), saved.isInfectado());
    }

    @Transactional(readOnly = true)
    public SobreviventeResponse buscarPeloId(Long id) {
        //TODO resolver N+1
        return toResponse(buscar(id));
    }

    @Transactional
    public List<SobreviventeSimplesResponse> buscarTodos() {
        //TODO resolver N+1
        return sobreviventeRepository.findAll().stream()
                .map(this::toSimpleResponse)
                .toList();
    }

    private Sobrevivente buscar(Long id) {
        return sobreviventeRepository.findById(id).orElseThrow(() ->
                new EntidadeNaoLocalizadaException("Sobrevivente não localizado"));
    }

    @Transactional
    public void marcarComoInfectado(Long id){
        Sobrevivente saved = buscar(id);
        // Managed
        saved.marcarComoInfectado(); // DIRTY CHECKING
    }
}
