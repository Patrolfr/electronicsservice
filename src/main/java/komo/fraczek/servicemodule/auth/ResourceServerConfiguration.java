package komo.fraczek.servicemodule.auth;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableResourceServer
@RestController
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter
{
    @RequestMapping("/publictest")
    public String publictest() {
        return "Public access";
    }

    @RequestMapping("/privatetest")
    public String privatetest() {
        return "Private access";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "Admin access";
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/oauth/token", "/oauth/authorize**", "/publictest").permitAll();
//			 .anyRequest().authenticated();
        http.requestMatchers().antMatchers("/privatetest")
                .and().authorizeRequests()
                .antMatchers("/privatetest").access("hasRole('USER')")
                .and().requestMatchers().antMatchers("/admin")
                .and().authorizeRequests()
                .antMatchers("/admin").access("hasRole('ADMIN')");
    }
}