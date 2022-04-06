package bg.bms.contractsapiclient.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Configuration
public class UtilityConfiguration {
    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public BufferedReader bufferedReader() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader;
    }
}
