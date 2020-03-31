package info.olof.githubsearch.config;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class OkHttpClientConfig {

    @Value("${retrofit.call-timeout}")
    private Duration callTimeout;

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
            .callTimeout(callTimeout)
            .build();
    }
}
