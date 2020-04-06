package info.olof.githubsearch.config;

import info.olof.githubsearch.config.exception.NoGitHubSearchResultsException;
import info.olof.githubsearch.generated.model.GitHubErrorResponse;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.time.Duration;

@Configuration
public class OkHttpClientConfig {

    @Value("${retrofit.call-timeout}")
    private Duration callTimeout;

    private final GsonConfiguration gsonConfiguration;

    public OkHttpClientConfig(GsonConfiguration gsonConfiguration) {
        this.gsonConfiguration = gsonConfiguration;
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
            .callTimeout(callTimeout)
            .addInterceptor(chain -> {
                Response response = chain.proceed(chain.request());

                if(response.code() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
                    if (response.body() != null) {
                        GitHubErrorResponse gitHubErrorResponse = gsonConfiguration.gson().fromJson(response.body().string(), GitHubErrorResponse.class);
                        throw new NoGitHubSearchResultsException(gitHubErrorResponse);
                    }
                }

                return response;
            })
            .build();
    }
}
