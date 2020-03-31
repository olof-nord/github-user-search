package info.olof.githubsearch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetrofitConfiguration {

    @Value("${github.base-url}")
    private String githubBaseUrl;

    private final OkHttpClientConfig okHttpClientConfig;
    private final GsonConfiguration gsonConfiguration;

    public RetrofitConfiguration(OkHttpClientConfig okHttpClientConfig,
                                 GsonConfiguration gsonConfiguration) {
        this.okHttpClientConfig = okHttpClientConfig;
        this.gsonConfiguration = gsonConfiguration;
    }

    @Bean
    public Retrofit retrofit() {
        return new Retrofit.Builder()
            .baseUrl(githubBaseUrl)
            .addConverterFactory(GsonConverterFactory.create(gsonConfiguration.gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClientConfig.okHttpClient())
            .build();
    }

}
