package br.com.vtinvest.vtinvest.security;

import br.com.vtinvest.vtinvest.mapper.LoginMapper;
import br.com.vtinvest.vtinvest.repository.UsuarioRepository;
import br.com.vtinvest.vtinvest.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private LoginMapper loginMapper;


    private static final RequestMatcher URLS_PROTEGIDAS = new OrRequestMatcher(
            new AntPathRequestMatcher("/admin/**"),
            new AntPathRequestMatcher("/carteira/**"),
            new AntPathRequestMatcher("/acao/**")
            );

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .requestMatchers(URLS_PROTEGIDAS)
                .authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), loginMapper))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserDetailService))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
