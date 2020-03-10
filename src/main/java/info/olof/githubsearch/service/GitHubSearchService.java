package info.olof.githubsearch.service;

import info.olof.githubsearch.generated.api.GitHubSearchApi;
import info.olof.githubsearch.generated.model.RepositorySearch;
import io.github.cdimascio.dotenv.Dotenv;
import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

@Service
public class GitHubSearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GitHubSearchService.class);
    private static final String ACCEPT_HEADER = "application/vnd.github.mercy-preview+json";
    private GitHubSearchApi service;
    private String accessToken;

    public GitHubSearchService() {

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

        service = retrofit.create(GitHubSearchApi.class);

        Dotenv dotenv = Dotenv.load();
        accessToken = "token " + dotenv.get("GITHUB_ACCESS_TOKEN");
    }

    public Observable<RepositorySearch> searchGitHubRepositories(String username, List<String> programmingLanguages) {
        String searchQuery = username + " language:" + programmingLanguages.get(0);

        LOGGER.info("GitHub search query: {}", searchQuery);

        return service.getRepositories(
            accessToken,
            searchQuery,
            ACCEPT_HEADER,
            "stars",
            "desc"
            );

    }
}
