package ProjetoMirante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter
    {
        
        @Override
        protected void configure(HttpSecurity http) throws Exception
        {
            http.csrf().disable();   
        }

    }

}
