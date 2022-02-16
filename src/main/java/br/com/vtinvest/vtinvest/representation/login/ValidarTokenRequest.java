package br.com.vtinvest.vtinvest.representation.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidarTokenRequest {

    private String cpf;

    private String token;
}
