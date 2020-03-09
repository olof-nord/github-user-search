package info.olof.githubsearch.rest.controller;

import info.olof.githubsearch.rest.dto.SearchResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GitHubSearchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GitHubSearchController.class);

    @GetMapping("/search")
    public ResponseEntity<SearchResultDTO> search(@RequestParam String username, @RequestParam String programmingLanguage) {

        LOGGER.info("search request: username: {}, programming language: {}", username, programmingLanguage);

        return ResponseEntity.status(HttpStatus.OK)
            .body(SearchResultDTO.builder()
                .username(username)
                .build());
    }
}
