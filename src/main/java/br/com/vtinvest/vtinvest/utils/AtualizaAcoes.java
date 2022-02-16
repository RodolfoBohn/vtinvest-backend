package br.com.vtinvest.vtinvest.utils;

import br.com.vtinvest.vtinvest.domain.acao.Acao;
import br.com.vtinvest.vtinvest.repository.AcaoRepository;
import br.com.vtinvest.vtinvest.repository.AtualizarValorAcaoRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AtualizaAcoes {

    @Autowired
    AcaoRepository acaoRepository;

    @Autowired
    AtualizarValorAcaoRepository atualizarValorAcaoRepository;

    private static final String TIME_ZONE = "America/Sao_Paulo";

    @Scheduled(cron = "00 47 20 * * *", zone = TIME_ZONE)
    public void verificaPorHora() throws InterruptedException {

        List<Acao> acoes = (List<Acao>) acaoRepository.findAll();

        for (Acao acao : acoes) {
            String busca = acao.getCodigo()+".SA";
            String response = atualizarValorAcaoRepository.atualizarValor(busca);
            JSONObject root = new JSONObject(response);

            //busca o ultimo dia que a ação foi atualizada
            JSONObject metaData = root.getJSONObject("Meta Data");
            String lastUpdated = metaData.getString("3. Last Refreshed");

            //busca o valor da ação
            JSONObject seriesDaily = root.getJSONObject("Time Series (Daily)");
            JSONObject today = seriesDaily.getJSONObject(lastUpdated);
            Double closeDouble = today.getDouble("4. close");
            acao.setCotacao(closeDouble);
            acaoRepository.save(acao);
            Thread.sleep(15 * 1000);
        }
    }
}
