package info.olof.githubsearch.rest.mapper;

import info.olof.githubsearch.generated.model.GitHubRepository;
import info.olof.githubsearch.generated.model.SimpleRepositorySearchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GitHubSearchResultMapper {

    @Mapping(source = "owner.login", target = "username")
    @Mapping(source = "owner.avatarUrl", target = "avatarUrl")
    @Mapping(source = "watchersCount", target = "numberOfFollowers")
    SimpleRepositorySearchResponse GitHubSearchToSimpleRepositorySearchResponse(GitHubRepository repository);

}
