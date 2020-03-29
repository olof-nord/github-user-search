package info.olof.githubsearch.rest.controller;

import info.olof.githubsearch.generated.model.SimpleRepositorySearchResponse;
import info.olof.githubsearch.rest.mapper.GitHubSearchResultMapperImpl;
import info.olof.githubsearch.service.GitHubSearchService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GitHubSearchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GitHubSearchController.class);

    private final GitHubSearchService gitHubSearchService;
    private final GitHubSearchResultMapperImpl searchResultMapper;

    @GetMapping("/search")
    public DeferredResult<ResponseEntity<List<SimpleRepositorySearchResponse>>> search(@RequestParam String username, @RequestParam(name = "programming_language") List<String> programmingLanguages) {
        DeferredResult<ResponseEntity<List<SimpleRepositorySearchResponse>>> deferredResult = new DeferredResult<>();

        LOGGER.info("Search input: username: {}, programming language(s): {}", username, programmingLanguages);

        gitHubSearchService.searchGitHubRepositories(username, programmingLanguages.get(0))
            .subscribe(
                result -> {
                    List<SimpleRepositorySearchResponse> repositories = result.getItems().stream()
                        .map(searchResultMapper::GitHubSearchToSimpleRepositorySearchResponse)
                        .collect(Collectors.toList());

                    deferredResult.setResult(ResponseEntity.ok().body(repositories));
                },
                error -> {
                    LOGGER.error(error.getMessage());
                    deferredResult.setResult(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
                });

        return deferredResult;

    }
}
