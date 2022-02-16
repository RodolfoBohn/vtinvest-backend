package br.com.vtinvest.vtinvest.service.carteira;

import br.com.vtinvest.vtinvest.domain.Carteira;
import br.com.vtinvest.vtinvest.exception.CadastroNaoEncontradoException;
import br.com.vtinvest.vtinvest.mapper.CarteiraMapper;
import br.com.vtinvest.vtinvest.repository.CarteiraRepository;
import br.com.vtinvest.vtinvest.representation.carteira.DetalhesCarteiraResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalharCarteiraServiceImpl implements DetalharCarteiraService {

    @Autowired
    CarteiraRepository carteiraRepository;

    @Autowired
    CarteiraMapper carteiraMapper;

    @Override
    public DetalhesCarteiraResponse detalharCarteira(Integer idCarteira) {

        Carteira carteira = carteiraRepository.findById(idCarteira).orElse(null);

        if(carteira == null) {
            throw new CadastroNaoEncontradoException("Carteira não cadastrada");
        }

        return carteiraMapper.toDetailsResponse(carteira);
    }
}
