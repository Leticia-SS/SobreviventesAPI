package br.com.infnet.sobreviventesapi.repository;

import br.com.infnet.sobreviventesapi.model.Sobrevivente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SobreviventeRepository extends JpaRepository<Sobrevivente, Long> {
}
