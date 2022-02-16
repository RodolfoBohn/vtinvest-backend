package br.com.vtinvest.vtinvest.representation.carteira;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class EditarCarteiraRequest{

    @NotNull(message = "O id da carteira é obrigatório")
    private Integer id;

    @NotEmpty(message = "O nome da carteira é obrigatório")
    private String nome;

    @NotNull(message = "O id do cliente é obrigatório")
    private Integer idCliente;

    private List<@Valid EditarAcaoCarteiraRequest> acoes;
}
