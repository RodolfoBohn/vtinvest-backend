package br.com.vtinvest.vtinvest.controller;

import br.com.vtinvest.vtinvest.representation.cadastroUsuario.CadastrarUsuarioRequest;
import br.com.vtinvest.vtinvest.representation.cadastroUsuario.CadastrarUsuarioResponse;
import br.com.vtinvest.vtinvest.service.admin.CadastrarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    CadastrarUsuarioService cadastrarUsuarioService;

    @RolesAllowed("ADMIN")
    @PostMapping("/cadastro-usuario")
    public CadastrarUsuarioResponse cadastrarUsuario(@RequestBody @Valid CadastrarUsuarioRequest cadastroUsuarioRequest) {
        return cadastrarUsuarioService.cadastrarUsuario(cadastroUsuarioRequest);
    }
}
