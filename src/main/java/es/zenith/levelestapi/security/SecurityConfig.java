package es.zenith.levelestapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import es.zenith.levelestapi.security.jwt.JwtRequestFilter;
import es.zenith.levelestapi.security.jwt.UnauthorizedHandlerJwt;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    public RepositoryUserDetailsService userDetailService;

    @Autowired
	private UnauthorizedHandlerJwt unauthorizedHandlerJwt;

    @Autowired
	private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
		
		http.authenticationProvider(authenticationProvider());

		http
			.securityMatcher("/**")
			.exceptionHandling(handling -> handling.authenticationEntryPoint(unauthorizedHandlerJwt));

		http
			.authorizeHttpRequests(authorize -> authorize
                    // PRIVATE ENDPOINTS
                    .requestMatchers(HttpMethod.GET, "/users/").permitAll()
                    .requestMatchers(HttpMethod.GET, "/users/*").hasRole("USER")
                    .requestMatchers(HttpMethod.PUT, "/users/*").hasRole("USER")
                    .requestMatchers(HttpMethod.DELETE, "/users/*").hasRole("USER")
                    .requestMatchers(HttpMethod.POST, "/users/*/completedLevels").hasRole("USER")
                    .requestMatchers(HttpMethod.GET, "/users/*/insignias").hasRole("DEVELOPER")
                    .requestMatchers(HttpMethod.POST, "/users/*/insignias").hasRole("DEVELOPER")
                    .requestMatchers(HttpMethod.POST, "/users/*/roles").hasRole("DEVELOPER")
                    .requestMatchers(HttpMethod.GET, "/levels/").hasRole("DEVELOPER")
                    .requestMatchers(HttpMethod.POST, "/levels/").hasRole("DEVELOPER")
                    .requestMatchers(HttpMethod.GET, "/levels/*").hasRole("USER")
                    .requestMatchers(HttpMethod.PUT, "/levels/*").hasRole("DEVELOPER")
                    .requestMatchers(HttpMethod.DELETE, "/levels/*").hasRole("DEVELOPER")
                    .requestMatchers(HttpMethod.GET, "/levels/query/*").hasRole("USER")
                    .requestMatchers(HttpMethod.GET, "/insignias/").hasRole("DEVELOPER")
                    .requestMatchers(HttpMethod.POST, "/insignias/").hasRole("DEVELOPER")
                    .requestMatchers(HttpMethod.GET, "/insignias/*").hasRole("DEVELOPER")
                    .requestMatchers(HttpMethod.PUT, "/insignias/*").hasRole("DEVELOPER")
                    .requestMatchers(HttpMethod.DELETE, "/insignias/*").hasRole("DEVELOPER")
                    .requestMatchers(HttpMethod.POST, "/insignias/*/image").hasRole("DEVELOPER")
                    .requestMatchers(HttpMethod.GET, "/images/*").hasRole("DEVELOPER")
					// PUBLIC ENDPOINTS
					.anyRequest().permitAll()
			);

        // Disable Form login Authentication
        http.formLogin(formLogin -> formLogin.disable());

        // Disable CSRF protection (it is difficult to implement in REST APIs)
        http.csrf(csrf -> csrf.disable());

        // Disable Basic Authentication
        http.httpBasic(httpBasic -> httpBasic.disable());

        // Allow sessions
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

		// Add JWT Token filter
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

    @Bean
    @Order(1)
    public SecurityFilterChain webFilterChain (HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider());
        http
        .authorizeHttpRequests(authorize -> authorize
            //Public pages
            .requestMatchers(
                    "/**"
                ).permitAll()
        )
        .exceptionHandling(exception -> exception
            .accessDeniedPage("/error/403")
        );

        return http.build();
    }
}
