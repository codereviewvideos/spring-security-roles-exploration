package uk.co.a6software.spring_security_roles_exploration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain appSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/only-authenticated").authenticated()
                                .requestMatchers("/only-authorised").hasAnyRole("ADMIN", "AUTHORISED")
                                .requestMatchers("/admin").hasRole("ADMIN")
                                .anyRequest().denyAll()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        User.UserBuilder builder = User.withDefaultPasswordEncoder();

        UserDetails authenticatedUser = builder
                .username("authenticated")
                .password("authenticated")
                .build();

        UserDetails authorisedUser = builder
                .username("authorised")
                .password("authorised")
                .roles("AUTHORISED")
                .build();

        UserDetails adminUser = builder
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(authenticatedUser, authorisedUser, adminUser);
    }
}
