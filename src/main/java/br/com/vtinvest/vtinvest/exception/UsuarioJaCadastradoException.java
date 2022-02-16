package br.com.vtinvest.vtinvest.exception;

public class UsuarioJaCadastradoException extends RuntimeException{

    public UsuarioJaCadastradoException(String mensagem) {
        super(mensagem);
    }
}
