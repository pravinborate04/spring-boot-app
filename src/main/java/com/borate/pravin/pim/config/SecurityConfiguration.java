package com.borate.pravin.pim.config;

import com.borate.pravin.pim.helper.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

/**
 * The class provide the configuration setup for Authentication, Authorization,CORS configuration and spring security
 *
 * @author Pravin Borate
 * 12/03/21
 */
@SuppressWarnings("deprecation")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter implements Constants {

    @Autowired
    AuthUserDetailService authUserDetailService;

    @Autowired
    CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Value("${app.portal.url}")
    String portalUrl;

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authUserDetailService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> {
            var cors = new CorsConfiguration();
            cors.setAllowedOrigins(List.of("http://localhost:3000"));
            cors.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
            cors.setAllowedHeaders(Arrays.asList("Content-Type", "Accept", "X-Requested-With", "Authorization", "schema"));
            cors.setAllowedHeaders(List.of("*"));
            cors.setAllowCredentials(true);
            return cors;
        }).and().csrf().disable()
                .authorizeRequests().antMatchers(SEPARATOR + LOGIN).permitAll()
                // Only admin can perform HTTP delete operation
                .antMatchers(HttpMethod.DELETE).hasAuthority("ADMIN")
                // Only admin can perform HTTP POST operation
                .antMatchers(HttpMethod.POST).hasAnyAuthority("ADMIN")
                // Only admin can perform HTTP PUT operation
                .antMatchers(HttpMethod.PUT).hasAnyAuthority("ADMIN").and().httpBasic()
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler()).authenticationEntryPoint(customAuthenticationEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // Permit all other request without authentication
                .and().authorizeRequests().antMatchers(HttpMethod.GET).hasAnyAuthority("ADMIN", "USER");
        // We don't need sessions to be created.
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CustomAccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        final CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList(portalUrl.split(",")));
//        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
//        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Accept", "X-Requested-With", "Authorization", "schema"));
//        configuration.setAllowCredentials(true);
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }


}
