package br.com.infnet.sobreviventesapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
@Table(name = "recursos")
public class Recurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int quantidade;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "sobrevivente_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_recurso-sobrevivente")
    )
    private Sobrevivente sobrevivente;

    protected Recurso() {
    }

    public Recurso(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public void vincularA(Sobrevivente sobrevivente) {
        this.sobrevivente = sobrevivente;
    }
}
