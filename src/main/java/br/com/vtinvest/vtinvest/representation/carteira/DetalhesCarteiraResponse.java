package br.com.vtinvest.vtinvest.representation.carteira;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DetalhesCarteiraResponse {

    private Integer id;

    private String nome;

    private Integer idCliente;

    private Double totalInvestido;

    private List<AcaoCarteiraResponse> acoes;
}
