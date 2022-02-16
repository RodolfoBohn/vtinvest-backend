package br.com.vtinvest.vtinvest.mapper;

import br.com.vtinvest.vtinvest.domain.pessoa.Pessoa;
import br.com.vtinvest.vtinvest.repository.UsuarioRepository;
import br.com.vtinvest.vtinvest.representation.login.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginMapperImpl implements LoginMapper{

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public LoginResponse toResponse(String login, String token) {
        Pessoa usuario = usuarioRepository.findByLogin(login);
        LoginResponse loginResponse = new LoginResponse();

        loginResponse.setPermissao(usuario.getPermissao());
        loginResponse.setToken(token);
        loginResponse.setNome(usuario.getNome());

        return loginResponse;
    }
}
