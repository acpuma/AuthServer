package gov.diski.diskisso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import gov.diski.diskisso.domain.User;
import gov.diski.diskisso.handler.Oauth2LogoutHandler;
import gov.diski.diskisso.service.JdbcUserDetailsService;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private ShaPasswordEncoder passwordEncoder;

    @Autowired 
    private Oauth2LogoutHandler logoutHandler;
    
    @Bean
	@Override
	protected UserDetailsService userDetailsService() {
		return new JdbcUserDetailsService();
	}
    
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("endy").password("123").roles("ADMIN").and()
//                .withUser("anton").password("123").roles("STAFF");
//    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		daoAuthenticationProvider.setSaltSource(new SaltSource() {
			@Override
			public Object getSalt(UserDetails userDetails) {
				User user = (User) userDetails;
				return user.getSalt();
			}
		});

		daoAuthenticationProvider.setUserDetailsService(userDetailsService);

		return daoAuthenticationProvider;
	}


    @Override
    protected void configure(HttpSecurity http) throws Exception {        
        http.authorizeRequests()
//                .antMatchers("/", "/user/**").access("hasRole('ADMIN')")
                .antMatchers("/").access("hasRole('ADMIN')")
                .and()
                .formLogin()
                    .loginPage("/login.jsp")
                    .loginProcessingUrl("/j_spring_security_check")
                    .usernameParameter("j_username")
                    .passwordParameter("j_password")
                .and()
                    .logout()
                    .invalidateHttpSession(true)
                    .logoutUrl("/j_spring_security_logout")
                    .logoutSuccessHandler(logoutHandler)
        		.and()
        			.exceptionHandling().accessDeniedPage("/403.jsp");
        http.csrf().disable();
    }
    
    
}
