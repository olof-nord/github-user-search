package info.olof.githubsearch.service;

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

    private final GitHubAccessTokenService accessTokenService;
    private final GitHubApiService gitHubApiService;

    public GitHubSearchService(GitHubAccessTokenService accessTokenService,
                               GitHubApiService gitHubApiService) {
        this.accessTokenService = accessTokenService;
        this.gitHubApiService = gitHubApiService;
    }

    public Observable<GitHubRepositorySearch> searchGitHubRepositories(String username, String programmingLanguage) {
        String accessToken = this.accessTokenService.getToken();

        username = addAtSignIfNotPresent(username);

        String searchQuery = username + " language:" + programmingLanguage;
        LOGGER.info("GitHub search query: '{}'", searchQuery);

        return gitHubApiService.getGitHubSearchApi().getRepositories(accessToken, searchQuery, acceptHeader);

    }

    private String addAtSignIfNotPresent(String username) {

        if(!username.startsWith("@")) {
            username = "@" + username;
        }

        return username;
    }
}
