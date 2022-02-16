package br.com.vtinvest.vtinvest.representation.carteira;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcaoCarteiraSugestaoCompra extends AcaoCarteiraResponse{

    private Double distanciaObjetivoFutura;

    private Integer quantidadeCompra;

}
