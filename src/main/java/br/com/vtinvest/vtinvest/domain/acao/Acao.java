package br.com.vtinvest.vtinvest.domain.acao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name="acao")
public class Acao {

    @Id
    private Integer id;

    @Column(name="codigo")
    private String codigo;

    @Column(name="valor")
    private Double cotacao;

    @ManyToOne
    @JoinColumn(name = "id_segmento")
    private Segmento segmento;
}
