package br.com.vtinvest.vtinvest.service.admin;

import br.com.vtinvest.vtinvest.domain.pessoa.Pessoa;
import br.com.vtinvest.vtinvest.exception.RegraDeNegocioException;
import br.com.vtinvest.vtinvest.repository.UsuarioRepository;
import br.com.vtinvest.vtinvest.representation.cadastroUsuario.CadastrarUsuarioRequest;
import br.com.vtinvest.vtinvest.representation.cadastroUsuario.CadastrarUsuarioResponse;
import br.com.vtinvest.vtinvest.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CadastrarUsuarioServiceImpl implements CadastrarUsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    private MD5 md5 = new MD5();
    @Override
    public CadastrarUsuarioResponse cadastrarUsuario(CadastrarUsuarioRequest request) {
        Pessoa pessoa = usuarioRepository.findByCpf(request.getCpf());

        if(pessoa != null) {
            throw new RegraDeNegocioException("Já existe um usuario cadastrado para este CPF");
        }
        Pessoa novaPessoa = new Pessoa();
        String preparaToken = request.getCpf() + LocalDateTime.now().toString();
        novaPessoa.setCpf(request.getCpf());
        novaPessoa.setTokenUsuario(md5.getMd5(preparaToken));
        usuarioRepository.save(novaPessoa);
        CadastrarUsuarioResponse response = new CadastrarUsuarioResponse();
        response.setCpf(novaPessoa.getCpf());
        response.setToken(novaPessoa.getTokenUsuario());
        return response;
    }
}
