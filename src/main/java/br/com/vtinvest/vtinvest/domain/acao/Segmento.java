package br.com.vtinvest.vtinvest.domain.acao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "segmento")
public class Segmento {

    @Id
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_subsetor")
    private Subsetor subsetor;
}
