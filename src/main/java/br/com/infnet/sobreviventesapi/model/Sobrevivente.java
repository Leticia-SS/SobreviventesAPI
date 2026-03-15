package br.com.infnet.sobreviventesapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Recurso> recursos;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "sobrevivente_comunidade",
            joinColumns = @JoinColumn(
                    nullable = false,
                    name = "sobrevivente_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "comunidade_id",
                    nullable = false)
    )
    Set<Comunidade> comunidades = new HashSet<>();

    protected Sobrevivente() {
    }

    public Sobrevivente(String nome, String localizacao) {
        this.nome = nome;
        this.localizacao = localizacao;
    }

    public void marcarComoInfectado(){
        this.infectado = true;
    }

    public void entrarNaComunidade(Comunidade comunidade) {
        if (this.infectado && comunidade.isZonaSegura()){
            throw new IllegalArgumentException("Infectado não pode entrar em zona segura");
        }
        this.comunidades.add(comunidade);
        comunidade.adicionarMembro(this);
    }

    public void addRecurso(String nome, int quantidade) {
        Recurso recurso = new Recurso(nome,quantidade);
        this.recursos.add(recurso);
        recurso.vincularA(this);
    }
}
