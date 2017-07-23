package com.show.car.config;

import com.show.car.security.AjaxLogoutSuccessHandler;
import com.show.car.security.AuthoritiesConstants;
import com.show.car.security.Http401UnauthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.InMemoryApprovalStore;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2ServerConfiguration {

    private CarProperties properties;

    public OAuth2ServerConfiguration(CarProperties properties) {
        this.properties = properties;
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey(properties.getOauth2().getJwt_key());
        return tokenConverter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        private final TokenStore tokenStore;

        private final Http401UnauthorizedEntryPoint http401UnauthorizedEntryPoint;

        private final AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;


        public ResourceServerConfiguration(TokenStore tokenStore, Http401UnauthorizedEntryPoint http401UnauthorizedEntryPoint,
                                           AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler) {

            this.tokenStore = tokenStore;
            this.http401UnauthorizedEntryPoint = http401UnauthorizedEntryPoint;
            this.ajaxLogoutSuccessHandler = ajaxLogoutSuccessHandler;
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .exceptionHandling()
                    .authenticationEntryPoint(http401UnauthorizedEntryPoint)
                    .and()
                    .logout()
                    .logoutUrl("/api/logout")
                    .logoutSuccessHandler(ajaxLogoutSuccessHandler)
                    .and()
                    .csrf()
                    .disable()
                    .headers()
                    .frameOptions().disable()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .antMatchers("/api/user/authenticate").permitAll()
                     .antMatchers("/api/car/file/**").permitAll()
                    .antMatchers("/api/car/search/**").permitAll()
                    .antMatchers("/api/car/details/**").permitAll()
                    .antMatchers("/api/user/register/**").permitAll()
                    .antMatchers("/api/**").authenticated()
                    .antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN)
                    .antMatchers("/swagger-ui/html").permitAll();
        }

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId("car_show").tokenStore(tokenStore);
        }
    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        private final AuthenticationManager authenticationManager;

        private final TokenStore tokenStore;

        private final CarProperties properties;

        private final AccessTokenConverter accessTokenConverter;


        public AuthorizationServerConfiguration(@Qualifier("authenticationManagerBean") AuthenticationManager authenticationManager,
                                                TokenStore tokenStore, CarProperties properties, AccessTokenConverter accessTokenConverter) {

            this.authenticationManager = authenticationManager;
            this.tokenStore = tokenStore;
            this.properties = properties;
            this.accessTokenConverter = accessTokenConverter;
        }

        @Bean
        protected AuthorizationCodeServices authorizationCodeServices() {
            return new InMemoryAuthorizationCodeServices();
        }

        @Bean
        public ApprovalStore approvalStore() {
            return new InMemoryApprovalStore();
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints)
                throws Exception {
            endpoints
                    .authorizationCodeServices(authorizationCodeServices())
                    .approvalStore(approvalStore())
                    .tokenStore(tokenStore)
                    .accessTokenConverter(accessTokenConverter)
                    .authenticationManager(authenticationManager);
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            oauthServer.allowFormAuthenticationForClients();
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory()
                    .withClient(properties.getOauth2().getClient_id())
                    .secret(properties.getOauth2().getClient_secret())
                    .accessTokenValiditySeconds(properties.getOauth2().getToken_validity())
                    .refreshTokenValiditySeconds(properties.getOauth2().getRefresh_token_validity())
                    .authorizedGrantTypes(properties.getOauth2().getGrant_type().split(","))
                    .scopes(properties.getOauth2().getScope().split(","));
        }
    }
}
