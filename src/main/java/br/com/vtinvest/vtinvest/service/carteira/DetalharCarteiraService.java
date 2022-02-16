package br.com.vtinvest.vtinvest.service.carteira;

import br.com.vtinvest.vtinvest.representation.carteira.DetalhesCarteiraResponse;

public interface DetalharCarteiraService {

    DetalhesCarteiraResponse detalharCarteira (Integer idCarteira);
}
