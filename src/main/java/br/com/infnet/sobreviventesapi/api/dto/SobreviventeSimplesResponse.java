package br.com.infnet.sobreviventesapi.api.dto;

public record SobreviventeSimplesResponse(Long id,
                                          String nome,
                                          String localizacao,
                                          boolean infectado
) {
}
