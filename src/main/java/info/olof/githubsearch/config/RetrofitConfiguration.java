package info.olof.githubsearch.config;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.time.Duration;

@Configuration
public class RetrofitConfiguration {

    @Value("${github.base-url}")
    private String githubBaseUrl;

    @Value("${retrofit.call-timeout}")
    private Duration callTimeout;

    @Bean
    public Retrofit retrofit() {
        return new Retrofit.Builder()
            .baseUrl(githubBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(new OkHttpClient.Builder()
                .callTimeout(callTimeout)
                .build())
            .build();
    }
}
