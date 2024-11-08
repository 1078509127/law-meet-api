package com.example.meet.oauth.serve.config.oauth;

import com.example.meet.oauth.serve.config.security.MyUserDatailService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * 授权服务器
 * 客户端信息、授权类型、令牌配置
 */

@Configuration
@EnableAuthorizationServer
public class OAuthConfig extends AuthorizationServerConfigurerAdapter {
    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    @Qualifier("jwtTokenStore")
    private TokenStore jwtTokenStore;

    @Resource
    private TokenEnhancer jwtTokenEnhancer;

    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Lazy
    @Resource
    private MyUserDatailService myUserDatailService;

    //允许资源服务调用
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
        security.checkTokenAccess("isAuthenticated()");
        security.tokenKeyAccess("isAuthenticated()");
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //JdbcClientDetailsServiceBuilder jdbc = clients.jdbc(dataSource);
        clients.inMemory()
               .withClient("web-client")
               .secret(passwordEncoder.encode("client-secret-8888"))
               .autoApprove(false)
               .scopes("all")
               .authorizedGrantTypes("client_credentials", "password", "implicit", "authorization_code", "refresh_token")
               .accessTokenValiditySeconds(3600)
               .refreshTokenValiditySeconds(3600)
               .redirectUris("http://www.baidu.com");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancerList = new ArrayList<>();
        enhancerList.add(jwtTokenEnhancer);
        enhancerList.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(enhancerList);

        endpoints.tokenStore(jwtTokenStore)
                .userDetailsService(myUserDatailService)
                .authenticationManager(authenticationManager)
                .tokenEnhancer(enhancerChain)
                .accessTokenConverter(jwtAccessTokenConverter);
                //authorizationCodeServices：配置授权码服务，以支持授权码模式
                //implicitGrantService：隐式授权模式需要
                //tokenGranter：配置这个，说明要自定义授权逻辑
    }
}
