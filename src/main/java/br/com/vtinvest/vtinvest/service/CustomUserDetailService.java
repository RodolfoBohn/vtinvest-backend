package br.com.vtinvest.vtinvest.service;

import br.com.vtinvest.vtinvest.domain.pessoa.Pessoa;
import br.com.vtinvest.vtinvest.repository.UsuarioRepository;
import br.com.vtinvest.vtinvest.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
       Optional<Pessoa> usuario = Optional.ofNullable(usuarioRepository.findByLogin(login));

       if(!usuario.isPresent()) {
           throw new UsernameNotFoundException("Usuario [" + login + "] nao encontrado");
       }

        return new UserDetailsImpl(usuario);
    }

    public String get() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
