package br.com.vtinvest.vtinvest.domain;

import br.com.vtinvest.vtinvest.domain.acao.AcaoCarteira;
import br.com.vtinvest.vtinvest.domain.pessoa.Cliente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "carteira")
@SequenceGenerator(name = "seq_carteira", sequenceName = "seq_carteira", allocationSize = 1)
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_carteira")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "carteira")
    private List<AcaoCarteira> acoesCompradas;

    @OneToMany(mappedBy = "carteira")
    private List<Movimentacao> movimentacoes;

    @Column(name = "ativo")
    private boolean isAtivo;

    public Carteira(String nome, Cliente cliente) {
        this.nome = nome;
        this.cliente = cliente;
    }

    public void adicionarAcao(AcaoCarteira acao) {
        if(this.acoesCompradas == null) {
            this.acoesCompradas = new ArrayList<>();
        }
        acoesCompradas.add(acao);
    }
}
