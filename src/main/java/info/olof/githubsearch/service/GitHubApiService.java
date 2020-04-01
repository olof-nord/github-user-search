package info.olof.githubsearch.service;

import info.olof.githubsearch.config.RetrofitConfiguration;
import info.olof.githubsearch.generated.api.GitHubSearchApi;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GitHubApiService {

    private final RetrofitConfiguration retrofitConfiguration;

    public GitHubSearchApi getGitHubSearchApi() {
        return retrofitConfiguration.retrofit().create(GitHubSearchApi.class);
    }
}
