package br.com.vtinvest.vtinvest.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "api", url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&interval=5min&apikey=${ALPHAVANTAGE_API_KEY}" )
public interface AtualizarValorAcaoRepository {

    @RequestMapping(method = RequestMethod.GET)
    String atualizarValor(@RequestParam(value = "symbol") String symbol);

}
