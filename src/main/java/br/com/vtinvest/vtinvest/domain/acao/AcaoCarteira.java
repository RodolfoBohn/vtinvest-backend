package br.com.vtinvest.vtinvest.domain.acao;

import br.com.vtinvest.vtinvest.domain.Carteira;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "seq_acao_carteira", sequenceName = "seq_acao_carteira", allocationSize = 1)
@Table(name = "acao_carteira")
public class AcaoCarteira{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_acao_carteira")
    private Integer id;

    @Column(name = "quantidade_atual")
    private Integer quantidadeAtual;

    @Column(name = "objetivo")
    private Double objetivo;

    @ManyToOne
    @JoinColumn(name="id_acao")
    private Acao acao;

    @Column(name = "ativo")
    private boolean isAtivo;

    @ManyToOne
    @JoinColumn(name="id_carteira")
    private Carteira carteira;
}
