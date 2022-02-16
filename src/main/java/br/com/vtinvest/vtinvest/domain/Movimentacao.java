package br.com.vtinvest.vtinvest.domain;

import br.com.vtinvest.vtinvest.domain.acao.AcaoCarteira;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "movimentacao")
@SequenceGenerator(name = "seq_movimentacao", sequenceName = "seq_movimentacao", allocationSize = 1)
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_movimentacao")
    private Integer id;

    @Column(name = "data_movimentacao")
    private Date data;

    @ManyToOne
    @JoinColumn(name = "id_acao_carteira")
    private AcaoCarteira acao;

    @ManyToOne
    @JoinColumn(name="id_carteira")
    private Carteira carteira;

    @Column(name="quantidade")
    private Integer quantidade;

    @Column(name = "movimentacao")
    @Enumerated(EnumType.STRING)
    private TipoMovimentacao movimentacao;
}
