package com.imd.config.mudanca.banco.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.imd.config.mudanca.banco.service.BancoAuthProviderService;


/**
 * @author André Santiago
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {


		@Autowired
		private BancoAuthProviderService authProviderService;
	
	 	@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
	
	 	@Override
	    public void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(authProviderService);
	    }
	
		protected void configure(HttpSecurity http) throws Exception {
			secureStaticResources(http);
			securePublic(http);
			secureAdmin(http);
			
		}
	

		protected String pageLoginSucess() {
			return "/login";
		}
	
		protected String pageLogoutSucess() {
			return "/login";
		}
	
		protected BancoAuthProviderService getAuthProvider() {
			return authProviderService;
		}
		
		private void securePublic(HttpSecurity http) throws Exception {
			
			http
				.authorizeRequests()
				.antMatchers("/esqueciSenha").permitAll()
				.antMatchers("/recuperarSenha").permitAll()
				.antMatchers("/formRedefinirSenha").permitAll();
		}

		private void secureAdmin(HttpSecurity http) throws Exception {
	
			http
				.authorizeRequests()
				.and()
					.antMatcher("/**")
					.authorizeRequests()
					.anyRequest()
					.authenticated()
				.and()
					.formLogin()
					.loginPage("/login")
					.loginProcessingUrl("/login")
					.defaultSuccessUrl("/operacoes")
					.permitAll()
				.and()
					.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl(pageLogoutSucess())
					.permitAll();
		}
	
		private void secureStaticResources(HttpSecurity http) throws Exception {
			http.authorizeRequests()
					.antMatchers("/img/**").permitAll()
					.antMatchers("/js/**", "/css/**", "/assets/**", "/fragments/**", "/vendor/**").permitAll();
		}
	


}
