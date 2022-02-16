package br.com.vtinvest.vtinvest.security;

import br.com.vtinvest.vtinvest.domain.pessoa.Pessoa;
import br.com.vtinvest.vtinvest.mapper.LoginMapper;
import br.com.vtinvest.vtinvest.mapper.LoginMapperImpl;
import br.com.vtinvest.vtinvest.repository.UsuarioRepository;
import br.com.vtinvest.vtinvest.representation.login.LoginResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static br.com.vtinvest.vtinvest.security.SecurityConstants.*;

//aqui eu to autenticando o usuario, fazendo o login dele.
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final LoginMapper loginMapper;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, LoginMapper loginMapper) {
        this.authenticationManager = authenticationManager;
        this.loginMapper = loginMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            Pessoa usuario = new ObjectMapper().readValue(request.getInputStream(), Pessoa.class);
            return  this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            usuario.getLogin(),
                            usuario.getSenha()));
        } catch (IOException e) {
            throw new RuntimeException("Falha ao carregar usuario, ", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {

        UserDetailsImpl usuario = (UserDetailsImpl) authResult.getPrincipal();

        String username = usuario.getUsername();
        String token = Jwts
                .builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        LoginResponse loginResponse = loginMapper.toResponse(username, token);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(loginResponse));
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}
