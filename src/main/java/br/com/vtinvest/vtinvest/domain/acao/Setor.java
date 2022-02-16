package br.com.vtinvest.vtinvest.domain.acao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "setor")
public class Setor {

    @Id
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

}
