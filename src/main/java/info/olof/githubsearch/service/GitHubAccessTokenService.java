package info.olof.githubsearch.service;

import info.olof.githubsearch.config.DotenvConfiguration;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GitHubAccessTokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GitHubAccessTokenService.class);
    private final DotenvConfiguration dotenvConfiguration;

    public String getToken() {

        String accessToken = System.getProperty("GITHUB_ACCESS_TOKEN");

        if(accessToken == null) {
            accessToken =  dotenvConfiguration.dotenv().get("GITHUB_ACCESS_TOKEN");
        }

        if(accessToken == null){
            LOGGER.error("A GitHub API token is required!");
            LOGGER.error("Please provide a value using the GITHUB_ACCESS_TOKEN environment variable");
            System.exit(0);
        }

        return "token " + accessToken;
    }
}
