package br.com.vtinvest.vtinvest.controller;

import br.com.vtinvest.vtinvest.representation.cliente.CadastrarClienteRequest;
import br.com.vtinvest.vtinvest.representation.login.ValidarTokenRequest;
import br.com.vtinvest.vtinvest.representation.login.ValidarTokenResponse;
import br.com.vtinvest.vtinvest.service.ValidarTokenService;
import br.com.vtinvest.vtinvest.service.cliente.CadastrarClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    ValidarTokenService validarTokenService;

    @Autowired
    CadastrarClienteService cadastrarCliente;


    @PostMapping("/validar-token")
    public ValidarTokenResponse valirarToken(@RequestBody @Valid ValidarTokenRequest request) {
        return validarTokenService.validarToken(request);
    }

    @PostMapping("/cadastrar-cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public String cadastrarCliente(@RequestBody @Valid CadastrarClienteRequest cadastrarClienteRequest) {
        return cadastrarCliente.cadastrarCliente(cadastrarClienteRequest);
    }
}
