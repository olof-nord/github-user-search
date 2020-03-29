package info.olof.githubsearch.rest.mapper;

import info.olof.githubsearch.generated.model.GitHubRepository;
import info.olof.githubsearch.generated.model.GitHubRepositoryOwner;
import info.olof.githubsearch.generated.model.SimpleRepositorySearchResponse;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.OffsetDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@Import({
    GitHubSearchResultMapperImpl.class
})
public class GitHubSearchResultMapperTests {

    @Autowired
    private GitHubSearchResultMapperImpl searchResultMapper;

    @SuppressWarnings("unused")
    private static Stream<Arguments> provideTestInput() throws URISyntaxException {
        GitHubRepository repository1 = setUpTestRepositoryResponse(
            "olof-nord",
            "github-user-search",
            0,
            "https://avatars2.githubusercontent.com/u/488769?v=4"
        );

        GitHubRepository repository2 = setUpTestRepositoryResponse(
            "cdimascio",
            "java-dotenv",
            241,
            "https://avatars1.githubusercontent.com/u/4706618?v=4"
        );

        return Stream.of(
            Arguments.of(
                repository1
            ),
            Arguments.of(
                repository2
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestInput")
    void givenGitHubSearchResult_thenMapTo_SimpleResponse(GitHubRepository repository) {

        SimpleRepositorySearchResponse result = searchResultMapper.GitHubSearchToSimpleRepositorySearchResponse(repository);

        assertEquals(result.getUsername(), repository.getOwner().getLogin());
        assertEquals(result.getAvatarUrl(), repository.getOwner().getAvatarUrl());
        assertEquals(result.getName(), repository.getName());
        assertEquals(result.getNumberOfFollowers(), repository.getWatchersCount());
    }

    private static GitHubRepository setUpTestRepositoryResponse(String username, String repoName, int watchCount, String avatarUrl) throws URISyntaxException {
        GitHubRepository repository = new GitHubRepository();
        GitHubRepositoryOwner owner = new GitHubRepositoryOwner();

        owner.setAvatarUrl(new URI(avatarUrl));
        owner.setLogin(username);
        owner.setId(1);
        owner.setFollowingUrl(new UriTemplate("https://api.github.com/users/olof-nord/following{/other_user}"));

        repository.setOwner(owner);
        repository.setWatchersCount(watchCount);
        repository.setName(repoName);

        repository.setPrivate(false);
        repository.setArchived(false);

        repository.setCreatedAt(OffsetDateTime.now().minusDays(10));
        repository.setUpdatedAt(OffsetDateTime.now().minusDays(5));
        repository.setPushedAt(OffsetDateTime.now());

        repository.setNotificationsUrl(new UriTemplate("https://api.github.com/repos/olof-nord/github-user-search/notifications{?since,all,participating}"));

        return repository;
    }
}
