package info.olof.githubsearch.rest.controller;

import info.olof.githubsearch.config.exception.NoGitHubSearchResultsException;
import info.olof.githubsearch.generated.model.GitHubRepositorySearch;
import info.olof.githubsearch.generated.model.SimpleRepositorySearchErrorResponse;
import info.olof.githubsearch.generated.model.SimpleRepositorySearchResponse;
import info.olof.githubsearch.generated.model.SimpleSearchResponse;
import info.olof.githubsearch.rest.mapper.GitHubErrorMapperImpl;
import info.olof.githubsearch.rest.mapper.GitHubSearchResultMapperImpl;
import info.olof.githubsearch.service.GitHubSearchService;
import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GitHubSearchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GitHubSearchController.class);

    private final GitHubSearchService gitHubSearchService;
    private final GitHubSearchResultMapperImpl searchResultMapper;
    private final GitHubErrorMapperImpl errorMapper;

    private DeferredResult<ResponseEntity<SimpleSearchResponse>> deferredResult;

    public GitHubSearchController(GitHubSearchService gitHubSearchService, GitHubSearchResultMapperImpl searchResultMapper, GitHubErrorMapperImpl errorMapper) {
        this.gitHubSearchService = gitHubSearchService;
        this.searchResultMapper = searchResultMapper;
        this.errorMapper = errorMapper;
    }

    @GetMapping("/search")
    public DeferredResult<ResponseEntity<SimpleSearchResponse>> search(@RequestParam String username, @RequestParam(name = "programming_language") List<String> programmingLanguages) throws NoGitHubSearchResultsException {
        this.deferredResult = new DeferredResult<>();

        LOGGER.info("Search input: username: {}, programming language(s): {}", username, programmingLanguages);

        Observable<GitHubRepositorySearch> call = gitHubSearchService
            .searchGitHubRepositories(username, programmingLanguages.get(0));

        call.subscribe(this::handleResults, this::handleError);

        return deferredResult;
    }

    private void handleResults(GitHubRepositorySearch result){
        List<SimpleRepositorySearchResponse> repositories = null;

        if(result != null) {
            repositories = result.getItems().stream()
                .map(searchResultMapper::gitHubSearchToSimpleRepositorySearchResponse)
                .collect(Collectors.toList());
        }

        deferredResult.setResult(ResponseEntity.ok()
            .body(new SimpleSearchResponse().data(repositories))
        );
    }

    private void handleError(Throwable exception) {
        SimpleRepositorySearchErrorResponse error = new SimpleRepositorySearchErrorResponse();

        if(exception instanceof NoGitHubSearchResultsException) {
            NoGitHubSearchResultsException noGitHubSearchResultsException = (NoGitHubSearchResultsException) exception;

            error = errorMapper.gitHubErrorResponseToSimpleRepositorySearchErrorResponse(noGitHubSearchResultsException.getResponse());
        }

        this.deferredResult.setResult(ResponseEntity.badRequest()
            .body(new SimpleSearchResponse().error(error).data(Collections.emptyList()))
        );
    }
}
