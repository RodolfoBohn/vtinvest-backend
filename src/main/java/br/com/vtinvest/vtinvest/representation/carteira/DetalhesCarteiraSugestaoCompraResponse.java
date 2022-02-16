package br.com.vtinvest.vtinvest.representation.carteira;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DetalhesCarteiraSugestaoCompraResponse {


    private Integer id;

    private String nome;

    private Integer idCliente;

    private Double totalInvestido;

    List<AcaoCarteiraSugestaoCompra> acoes;

    private boolean possuiSugestao;

}
