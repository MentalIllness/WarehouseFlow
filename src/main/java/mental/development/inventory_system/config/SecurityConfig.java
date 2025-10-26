package mental.development.inventory_system.config;

import lombok.RequiredArgsConstructor;
import mental.development.inventory_system.service.AppUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AppUserDetailsService appUserDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // expose your impl as UserDetailsService for Spring Security
    @Bean
    public UserDetailsService userDetailsService() {
        return appUserDetailsService;
    }

    // tell Spring how to actually authenticate username/password using the DB
    @Bean
    public AuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsService,
            BCryptPasswordEncoder passwordEncoder
    ) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService); // this is NOT deprecated here
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthenticationProvider authenticationProvider
    ) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth
                        // bootstrap endpoint so you can create the first admin user
//                        .requestMatchers("/api/users/create-admin").permitAll() uncomment if you want to create an admin user

                        // read-only / safe endpoints anyone can view
                        .requestMatchers("/api/products", "/api/products/**").permitAll()
                        .requestMatchers("/api/warehouses", "/api/warehouses/**").permitAll()
                        .requestMatchers("/api/categories", "/api/categories/**").permitAll()
                        .requestMatchers("/api/suppliers", "/api/suppliers/**").permitAll()
                        .requestMatchers("/api/inventory-items", "/api/inventory-items/**").permitAll()
                        .requestMatchers("/api/reports/**").permitAll()
                        .requestMatchers("/api/movements").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()

                        // protected endpoints (write / admin ops)
                        .requestMatchers("/api/inventory/**").hasRole("ADMIN")
                        // .requestMatchers("/api/users/**").hasRole("ADMIN") // uncomment when ready

                        // anything else must be authenticated
                        .anyRequest().authenticated()
                )

                // basic auth for now (username/password popup)
                .httpBasic(Customizer.withDefaults())

                // allow H2 console in a frame
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))

                // <- MOST IMPORTANT: plug in the provider we defined
                .authenticationProvider(authenticationProvider);

        return http.build();
    }
}
