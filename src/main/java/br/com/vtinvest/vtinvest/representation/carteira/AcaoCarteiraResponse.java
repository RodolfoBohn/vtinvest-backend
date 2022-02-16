package br.com.vtinvest.vtinvest.representation.carteira;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcaoCarteiraResponse {

    private Integer id;

    private Integer idAtivo;

    private String nome;

    private String setor;

    private String subsetor;

    private String segmento;

    private Double cotacaoAtualAtivo;

    private Integer quantidade;

    private Double patrimonioAtualizado;

    private Double participacaoAtual;

    private Double objetivo;

    private Double distanciaObjetivo;

    private boolean acaoAtiva;



}
