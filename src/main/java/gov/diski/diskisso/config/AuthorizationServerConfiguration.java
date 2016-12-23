package gov.diski.diskisso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends
        AuthorizationServerConfigurerAdapter {

    @Value("${diski.resourceId}")
    private String resourceId;

    @Value("${diski.clientId}")
    private String clientId;

    @Value("${diski.clientSecret}")
    private String clientSecret;

    @Value("${tekser.resourceId}")
    private String tekserResourceId;

    @Value("${tekser.clientId}")
    private String tekserClientId;

    @Value("${tekser.clientSecret}")
    private String tekserClientSecret;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {
        endpoints
                .tokenStore(new InMemoryTokenStore())
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.checkTokenAccess("hasRole('CLIENT')");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient("clientauthcode")
                    .secret("123456")
                    .authorizedGrantTypes("authorization_code", "refresh_token")
                    .scopes("read", "write")
                    .resourceIds(resourceId)
                .and()
                .withClient("clientcred")
                    .secret("123456")
                    .authorizedGrantTypes("client_credentials")
                    .scopes("trust")
                    .resourceIds(resourceId)
                .and()
                .withClient("clientapp")
                    .secret("123456")
                    .authorizedGrantTypes("password")
                    .scopes("read", "write")
                    .resourceIds(resourceId)
                .and()
                .withClient(clientId)
                    .secret(clientSecret)
                    .authorizedGrantTypes("implicit")
                    .scopes("read", "write")
                    .resourceIds(resourceId)
                    .authorities("CLIENT")
                    .accessTokenValiditySeconds(60 * 60 * 24)
                    .autoApprove(true)
                .and()
                .withClient(tekserClientId)
                .secret(tekserClientSecret)
                .authorizedGrantTypes("implicit")
                .scopes("read", "write")
                .resourceIds(resourceId)
                .authorities("CLIENT")
                .accessTokenValiditySeconds(60 * 60 * 24)
                .autoApprove(true);
    }

}