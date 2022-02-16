package br.com.vtinvest.vtinvest.service.admin;

import br.com.vtinvest.vtinvest.representation.cadastroUsuario.CadastrarUsuarioRequest;
import br.com.vtinvest.vtinvest.representation.cadastroUsuario.CadastrarUsuarioResponse;

public interface CadastrarUsuarioService {

    CadastrarUsuarioResponse cadastrarUsuario(CadastrarUsuarioRequest request);

}
