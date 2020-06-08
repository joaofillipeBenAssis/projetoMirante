package ProjetoMirante;

import java.security.Principal;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@SpringBootApplication
@Controller
public class UiApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(UiApplication.class, args);
        System.out.println("###########################################");
        System.out.println("USUÁRIO E SENHA (PADRÃO DO SISTEMA)");
        System.out.println("Usuário: usuario // Senha: 123");
        System.out.println("###########################################");
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
            .anyRequest().authenticated()
            .and().formLogin().permitAll()
            .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .and().csrf().disable();
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
                .withUser("usuario").password("123").roles("Administrador");

            auth.jdbcAuthentication().dataSource(dataSource)
                    .usersByUsernameQuery("select login, senha, ativo from Operador l where login = ?")
                    .authoritiesByUsernameQuery("select l.login as login, l.perfil as role from Operador l where l.login = ?")
                    .passwordEncoder(passwordEncoder());
        }

    }

}