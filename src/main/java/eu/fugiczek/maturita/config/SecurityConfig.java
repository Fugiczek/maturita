package eu.fugiczek.maturita.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**", "/css/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin/**").hasAuthority("ADMIN")
		.antMatchers("/", "/blog/**").permitAll()
		.antMatchers("/login", "/register/**").anonymous()
		.anyRequest().authenticated();

		FormLoginConfigurer<HttpSecurity> formLogin = http.formLogin();
		formLogin.loginPage("/login")
		.loginProcessingUrl("/login")
		.failureUrl("/login/error")
		.usernameParameter("username")
		.passwordParameter("password")
		.defaultSuccessUrl("/blog");

		LogoutConfigurer<HttpSecurity> logout = http.logout();
		logout.logoutUrl("/logout")
		.logoutSuccessUrl("/")
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

		http.csrf();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.passwordEncoder(new BCryptPasswordEncoder())
				.usersByUsernameQuery(
						"select name, password, enabled from app_user where name = ?")
				.authoritiesByUsernameQuery(
						"select app_user.name, role.name from app_user join app_user_roles on app_user.id = app_user_roles.users_id join role on app_user_roles.roles_id = role.id where app_user.name = ?")
				.rolePrefix("");
	}
}
