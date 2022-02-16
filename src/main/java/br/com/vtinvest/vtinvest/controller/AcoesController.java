package br.com.vtinvest.vtinvest.controller;

import br.com.vtinvest.vtinvest.representation.acoes.AcaoResponse;
import br.com.vtinvest.vtinvest.representation.carteira.ListarCarteiraResponse;
import br.com.vtinvest.vtinvest.service.acoes.ListarAcoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RolesAllowed("USER")
@RequestMapping("/acao")
public class AcoesController {

    @Autowired
    ListarAcoesService listarAcoesService;

    @GetMapping
    public List<AcaoResponse> listarAcoes() {
        return listarAcoesService.listarAcoes();
    }
}
