package br.com.vtinvest.vtinvest.service.carteira;

import br.com.vtinvest.vtinvest.representation.carteira.DetalhesCarteiraResponse;
import br.com.vtinvest.vtinvest.representation.carteira.DetalhesCarteiraSugestaoCompraResponse;

public interface SugestirCompraAtivoService {


    DetalhesCarteiraSugestaoCompraResponse gerarSugestaoCompra (Integer idCarteira, Double valor);

}
