package info.olof.githubsearch.rest.controller;

import info.olof.githubsearch.rest.dto.SearchResultDTO;
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

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GitHubSearchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GitHubSearchController.class);

    private GitHubSearchService gitHubSearchService;

    @GetMapping("/search")
    public ResponseEntity<SearchResultDTO> search(@RequestParam String username, @RequestParam(name = "programming_language") List<String> programmingLanguages) {

        LOGGER.info("search input: username: {}, programming language(s): {}", username, programmingLanguages);

        gitHubSearchService.searchGitHubRepositories(username, programmingLanguages);

        return ResponseEntity.status(HttpStatus.OK)
            .body(SearchResultDTO.builder()
                .username(username)
                .build());
    }
}
