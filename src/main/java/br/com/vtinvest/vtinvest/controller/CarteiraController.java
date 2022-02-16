package br.com.vtinvest.vtinvest.controller;

import br.com.vtinvest.vtinvest.representation.carteira.*;
import br.com.vtinvest.vtinvest.service.carteira.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RolesAllowed("USER")
@RequestMapping("/carteira")
public class CarteiraController {

    @Autowired
    CadastrarCarteiraService cadastrarCarteiraService;

    @Autowired
    DetalharCarteiraService detalharCarteiraService;

    @Autowired
    SugestirCompraAtivoService sugestirCompraAtivoService;

    @Autowired
    ListarCarteirasService listarCarteirasService;

    @Autowired
    MovimentarCarteiraService movimentarCarteiraService;

    @Autowired
    EditarCarteiraService editarCarteiraService;

    @Autowired
    ExcluirCarteiraService excluirCarteiraService;

    @Autowired
    ListarHistoricoMovimentacaoService listarHistoricoMovimentacaoService;

    @PostMapping("/cadastrar")
    public String cadastrarCarteira(@RequestBody @Valid CadastrarCarteiraRequest cadastrarCarteiraRequest) {
        return cadastrarCarteiraService.cadastrarCarteira(cadastrarCarteiraRequest);
    }

    @PutMapping("/editar")
    public String editarCarteira(@RequestBody @Valid EditarCarteiraRequest editarCarteiraRequest) {
        return editarCarteiraService.editarCarteira(editarCarteiraRequest);
    }

    @GetMapping("/listar")
    public List<ListarCarteiraResponse> listarCarteiras() {
        return  listarCarteirasService.listarCarteiras();
    }

    @GetMapping("/detalhes/{id}")
    public DetalhesCarteiraResponse detalharCarteira(@PathVariable Integer id) {
        return detalharCarteiraService.detalharCarteira(id);
    }

    @GetMapping("sugestao-compra/{id}/{valor}")
    public DetalhesCarteiraSugestaoCompraResponse sugerirCompraAtivo(@PathVariable Integer id, @PathVariable Double valor) {
        return  sugestirCompraAtivoService.gerarSugestaoCompra(id, valor);
    }

    @PutMapping("/movimentar")
    public String movimentarCarteira(@RequestBody @Valid MovimentarCarteiraRequest request) {
        return movimentarCarteiraService.movimentarCarteira(request);
    }

    @GetMapping("/historico-movimentacao/{id}")
    public List<HistoricoMovimentacaoResponse> movimentarCarteira(@PathVariable Integer id) {
        return listarHistoricoMovimentacaoService.listarHistoricoMovimentacao(id);
    }


    @DeleteMapping("/{id}")
    public String deletarCarteira(@PathVariable Integer id) {
        return excluirCarteiraService.excluirCarteira(id);
    }
}
