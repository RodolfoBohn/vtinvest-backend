package br.com.vtinvest.vtinvest.mapper;

import br.com.vtinvest.vtinvest.representation.login.LoginResponse;

public interface LoginMapper {

    LoginResponse toResponse(String login, String token);

}
