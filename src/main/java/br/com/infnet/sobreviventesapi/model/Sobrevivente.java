package br.com.infnet.sobreviventesapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
@Table(name="sobreviventes",uniqueConstraints = {
        @UniqueConstraint(name = "uk_sobreviventes_nome", columnNames = "nome")
})
public class Sobrevivente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 80)
    private String localizacao;

    @Column(nullable = false)
    private boolean infectado = false;
}
