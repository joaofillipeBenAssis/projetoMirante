package ProjetoMirante;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

@SpringBootApplication
@Controller
public class UiApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(UiApplication.class, args);
    }

    @RequestMapping(value = "/{[path:[^\\.]*}")
    public String redirect()
    {
        return "forward: /";
    }

    @RequestMapping("/user")
    @ResponseBody
    public Principal user(Principal user)
    {
        return user;
    }

    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter
    {
        
        @Override
        protected void configure(HttpSecurity http) throws Exception
        {
            http.anonymous().disable();

            http.authorizeRequests()
            .antMatchers("/", "/inicio", "/operador", "/novo-operador", "/pessoa", "/nova-pessoa").permitAll()
            //.antMatchers(HttpMethod.GET, "/pessoas").permitAll()
            .antMatchers(HttpMethod.POST, "/novo-operador").permitAll()
            .anyRequest().authenticated()
            .and().formLogin().permitAll()
            .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .and().csrf().disable();

            //.and().csrf().csrfTokenRepository(csrfTokenRepository())
            //.and().addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
        }

        private Filter csrfHeaderFilter()
        {
            return new OncePerRequestFilter()
            {
                @Override
                protected void doFilterInternal(HttpServletRequest request,
                        HttpServletResponse response, FilterChain filterChain)
                        throws ServletException, IOException
                {
                    CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
                    
                    if (csrf != null)
                    {
                        Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
                        String token = csrf.getToken();
                        
                        if (cookie == null || token != null
                                && !token.equals(cookie.getValue()))
                        {
                            cookie = new Cookie("XSRF-TOKEN", token);
                            cookie.setPath("/");
                            response.addCookie(cookie);
                        }
                    }
                    
                    filterChain.doFilter(request, response);
                }
            };
        }

        
        private CsrfTokenRepository csrfTokenRepository()
        {
            HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
            repository.setHeaderName("XSRF-TOKEN");
            System.out.println(repository);
            return repository;
        }

        @Autowired
        DataSource dataSource;

        @Bean
        public PasswordEncoder passwordEncoder()
        {
            return new BCryptPasswordEncoder();
        }
        
        @Autowired
        public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception
        {
            auth.inMemoryAuthentication()
                .withUser("usuario").password("1").roles("USER");

            auth.jdbcAuthentication().dataSource(dataSource)
                    .usersByUsernameQuery("select login, senha, ativo from Operador l where login = ?")
                    .authoritiesByUsernameQuery("select l.login as login, l.perfil as role from Operador l where l.login = ?")
                    .passwordEncoder(passwordEncoder());
        }

    }

}
