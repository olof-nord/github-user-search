package info.olof.githubsearch.rest.controller;

import info.olof.githubsearch.generated.model.SimpleRepositorySearchResponse;
import info.olof.githubsearch.generated.model.SimpleRepositorySearchResponses;
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

@RestController
@AllArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GitHubSearchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GitHubSearchController.class);

    private GitHubSearchService gitHubSearchService;

    @GetMapping("/search")
    public DeferredResult<ResponseEntity<SimpleRepositorySearchResponses>> search(@RequestParam String username, @RequestParam(name = "programming_language") List<String> programmingLanguages) {
        DeferredResult<ResponseEntity<SimpleRepositorySearchResponses>> deferredResult = new DeferredResult<>();

        LOGGER.info("Search input: username: {}, programming language(s): {}", username, programmingLanguages);

        gitHubSearchService.searchGitHubRepositories(username, programmingLanguages)
            .subscribe(
                result -> {
                    SimpleRepositorySearchResponses repositories = new SimpleRepositorySearchResponses();

                    result.getItems().forEach(item -> {
                        SimpleRepositorySearchResponse repository = new SimpleRepositorySearchResponse();

                        repository.setUsername(item.getOwner().getLogin());
                        repository.setName(item.getName());
                        repository.setAvatarUrl(item.getOwner().getAvatarUrl());

                        repositories.add(repository);
                    });

                    deferredResult.setResult(ResponseEntity.ok().body(repositories));
                },
                error -> {
                    LOGGER.error("No search results found");
                    deferredResult.setResult(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
                });

        return deferredResult;

    }
}
