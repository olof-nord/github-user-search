package info.olof.githubsearch.config.exception;

import info.olof.githubsearch.generated.model.GitHubErrorResponse;
import lombok.Getter;

@Getter
public class NoGitHubSearchResultsException extends RuntimeException {

    private final GitHubErrorResponse response;

    public NoGitHubSearchResultsException(GitHubErrorResponse response) {
        this.response = response;
    }
}
