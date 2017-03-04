package br.edu.unievangelica;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.OAuth2ClientContext;

public class FeignOAuthInterceptor implements RequestInterceptor {

    private final OAuth2ClientContext oAuth2ClientContext;
    private final String tokenTypeName;
    private final String headerName;
    private final Logger logger = LoggerFactory.getLogger(FeignOAuthInterceptor.class);


    public FeignOAuthInterceptor(OAuth2ClientContext oAuth2ClientContext) {
        this(oAuth2ClientContext, "Bearer", "Authorization");
    }

    public FeignOAuthInterceptor(OAuth2ClientContext oAuth2ClientContext, String tokenTypeName, String headerName) {
        this.oAuth2ClientContext = oAuth2ClientContext;
        this.tokenTypeName = tokenTypeName;
        this.headerName = headerName;
    }

    @Override
    public void apply(RequestTemplate template) {
        if (oAuth2ClientContext.getAccessTokenRequest().getExistingToken() == null) {
            logger.warn("Cannot obtain existing token for request, if it is a non secured request, ignore.");
        } else {
            logger.debug("Constructing Header {} for Token {}", headerName, tokenTypeName);
            template.header(headerName, String.format("%s %s", tokenTypeName, oAuth2ClientContext.getAccessTokenRequest().getExistingToken().toString()));
        }

    }






}
