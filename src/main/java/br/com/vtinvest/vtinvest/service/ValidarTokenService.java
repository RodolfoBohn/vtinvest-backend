package br.com.vtinvest.vtinvest.service;

import br.com.vtinvest.vtinvest.representation.login.ValidarTokenRequest;
import br.com.vtinvest.vtinvest.representation.login.ValidarTokenResponse;

public interface ValidarTokenService {

    ValidarTokenResponse validarToken(ValidarTokenRequest request);

}
