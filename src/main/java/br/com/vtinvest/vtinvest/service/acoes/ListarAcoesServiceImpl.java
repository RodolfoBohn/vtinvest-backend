package br.com.vtinvest.vtinvest.service.acoes;

import br.com.vtinvest.vtinvest.domain.acao.Acao;
import br.com.vtinvest.vtinvest.mapper.AcaoMapper;
import br.com.vtinvest.vtinvest.repository.AcaoRepository;
import br.com.vtinvest.vtinvest.representation.acoes.AcaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListarAcoesServiceImpl implements ListarAcoesService{

    @Autowired
    AcaoRepository acaoRepository;

    @Autowired
    AcaoMapper acaoMapper;

    @Override
    public List<AcaoResponse> listarAcoes() {
        List<Acao> acoes = (List<Acao>) acaoRepository.findAll();

        List<AcaoResponse> acaoResponses = new ArrayList<>();

        for(Acao acao : acoes) {
            acaoResponses.add(acaoMapper.toResponse(acao));
        }

        return acaoResponses;
    }
}
