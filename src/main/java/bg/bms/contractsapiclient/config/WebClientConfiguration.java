package bg.bms.contractsapiclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebClientConfiguration {
    @Bean
    public RestTemplate createWebClient(){
       return new RestTemplate();
    }
}