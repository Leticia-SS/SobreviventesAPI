package br.com.infnet.sobreviventesapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter@Setter
@Table(name = "comunidades")
public class Comunidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 70)
    private String nome;
    @Column(nullable = false)
    private boolean zonaSegura;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "comunidades")
    private Set<Sobrevivente> membros = new HashSet<>();

    public void adicionarMembro(Sobrevivente sobrevivente) {
        this.membros.add(sobrevivente);
    }
}
