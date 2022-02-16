package br.com.vtinvest.vtinvest.service;

import br.com.vtinvest.vtinvest.domain.pessoa.Pessoa;
import br.com.vtinvest.vtinvest.exception.CadastroNaoEncontradoException;
import br.com.vtinvest.vtinvest.exception.RegraDeNegocioException;
import br.com.vtinvest.vtinvest.repository.UsuarioRepository;
import br.com.vtinvest.vtinvest.representation.login.ValidarTokenRequest;
import br.com.vtinvest.vtinvest.representation.login.ValidarTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidarTokenServiceImpl implements ValidarTokenService{

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public ValidarTokenResponse validarToken(ValidarTokenRequest request) {

        Pessoa pessoa = usuarioRepository.findByCpf(request.getCpf());

        if(pessoa == null) {
            throw new CadastroNaoEncontradoException("Cpf não está cadastrado");
        }

        if(!pessoa.getTokenUsuario().equals(request.getToken())) {
            throw new RegraDeNegocioException("Token inválido");
        }

        ValidarTokenResponse response = new ValidarTokenResponse();
        response.setTokenValido(true);
        return response;
    }
}
