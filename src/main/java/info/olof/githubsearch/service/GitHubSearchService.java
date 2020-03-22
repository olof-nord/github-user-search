package info.olof.githubsearch.service;

import info.olof.githubsearch.config.RetrofitConfiguration;
import info.olof.githubsearch.generated.api.GitHubSearchApi;
import info.olof.githubsearch.generated.model.GitHubRepositorySearch;
import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GitHubSearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GitHubSearchService.class);

    @Value("${github.accept-header}")
    private String acceptHeader;

    private GitHubSearchApi gitHubSearchApi;
    private GitHubAccessTokenService accessTokenService;

    public GitHubSearchService(RetrofitConfiguration retrofitConfiguration,
                               GitHubAccessTokenService accessTokenService) {

        this.accessTokenService = accessTokenService;
        this.gitHubSearchApi = retrofitConfiguration.retrofit().create(GitHubSearchApi.class);

    }

    public Observable<GitHubRepositorySearch> searchGitHubRepositories(String username, String programmingLanguage) {
        String accessToken = this.accessTokenService.getToken();

        username = addAtSignIfNotPresent(username);

        String searchQuery = username + " language:" + programmingLanguage;
        LOGGER.info("GitHub search query: '{}'", searchQuery);

        return gitHubSearchApi.getRepositories(accessToken, searchQuery, acceptHeader);

    }

    private String addAtSignIfNotPresent(String username) {

        if(!username.startsWith("@")) {
            username = "@" + username;
        }

        return username;
    }
}
