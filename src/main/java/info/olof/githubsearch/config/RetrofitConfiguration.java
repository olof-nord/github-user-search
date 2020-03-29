package info.olof.githubsearch.config;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriTemplate;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.time.Duration;
import java.time.OffsetDateTime;

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
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .registerTypeAdapter(OffsetDateTime.class, (JsonDeserializer<OffsetDateTime>)
                    (json, type, context) -> OffsetDateTime.parse(json.getAsString())
                )
                .registerTypeAdapter(UriTemplate.class, (JsonDeserializer<UriTemplate>)
                    (json, type, context) -> new UriTemplate(json.getAsString())
                )
                .create())
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(new OkHttpClient.Builder()
                .callTimeout(callTimeout)
                .build())
            .build();
    }
}
