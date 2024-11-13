package nchandi.spring.services.NCHANDIWebsite_Service.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("http://ec2-54-241-237-139.us-west-1.compute.amazonaws.com:3000"));
    configuration.setAllowedMethods(Arrays.asList("*"));
    configuration.setAllowedHeaders(Arrays.asList("Content-Type", "X-XSRF-TOKEN", "Authorization"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf(AbstractHttpConfigurer::disable)
        .exceptionHandling(c -> c.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
        .cors(Customizer.withDefaults()) // By default, looks for bean called corsConfigurationSource()
        .authorizeHttpRequests(authorize -> {
          authorize
            .requestMatchers(
              antMatcher("/swagger-ui/**"),
              antMatcher("/swagger-ui.html"),
              antMatcher("/v3/**"),
              antMatcher("/h2-console/**"),
              antMatcher(HttpMethod.OPTIONS, "/**")).permitAll()
            // .requestMatchers(antMatcher(HttpMethod.GET, "/books/**")).permitAll()
            // .requestMatchers(antMatcher(HttpMethod.POST, "/people"))
            //   .hasAnyRole("Chair", "Co-Chair", "Librarian", "Technology", "Treasurer", "Facilities")
            .anyRequest().permitAll();
        })
        .formLogin(form -> form.loginPage("/login").permitAll()
          .loginProcessingUrl("/login"))
        .logout(logout -> logout.logoutUrl("/logout")
          .deleteCookies("JSESSIONID", "isAdmin"))
        .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)) // This so embedded frames in h2-console are working.
        .httpBasic(Customizer.withDefaults())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return httpSecurity.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12);
  }
}
