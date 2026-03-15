package br.com.infnet.sobreviventesapi;

import br.com.infnet.sobreviventesapi.service.SobreviventeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SobreviventesApiApplicationTests {

	@Autowired
	SobreviventeService service;

	@Test
	void deveBuscarPeloId() {
		service.buscarPeloId(1L);
	}

	@Test
	void deveBuscarTodos() {
		service.buscarTodos();
	}

}
