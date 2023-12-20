package api.v1.authentication.config;

import api.v1.authentication.handler.CustomAuthenticationFailureHandler;
import api.v1.authentication.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserService userService;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @SneakyThrows
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        http.csrf().disable()
                .requestCache((cache) -> cache
                        .requestCache(requestCache))
                .formLogin(login -> login
                        .loginProcessingUrl("/perform-login")
                        .usernameParameter("login")
                        .passwordParameter("password")
                        .loginPage("/login")
                        .failureHandler(customAuthenticationFailureHandler))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                )
                .cors(AbstractHttpConfigurer::disable
                );
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return provider;
    }

}
