package br.com.vtinvest.vtinvest.domain.pessoa;

import br.com.vtinvest.vtinvest.domain.Carteira;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Table(name="cliente")
@NoArgsConstructor
public class Cliente extends Pessoa {

    public Cliente(String emailPrimario, String emailSecundario, String senha, String nome, String sobrenome, String telefone, String cpf, String login) {
        super(emailPrimario, emailSecundario, senha, nome, sobrenome, telefone, cpf, login);
    }

    @OneToMany(mappedBy = "cliente")
    private List<Carteira> carteiras;
}
