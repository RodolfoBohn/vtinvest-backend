package br.com.vtinvest.vtinvest.security;

import br.com.vtinvest.vtinvest.domain.pessoa.Pessoa;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class UserDetailsImpl implements UserDetails {

    private final Optional<Pessoa> usuarioOptional;

    public UserDetailsImpl(Optional<Pessoa> usuarioOptional) {
        this.usuarioOptional = usuarioOptional;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(usuarioOptional.get().getPermissao());
        return authorities;
    }

    @Override
    public String getPassword() {
        return usuarioOptional.orElse(new Pessoa()).getSenha();
    }

    @Override
    public String getUsername() {
        return usuarioOptional.orElse(new Pessoa()).getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
