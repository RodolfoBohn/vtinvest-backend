package br.com.vtinvest.vtinvest.representation.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private String token;

    private String nome;

    private String permissao;
}
