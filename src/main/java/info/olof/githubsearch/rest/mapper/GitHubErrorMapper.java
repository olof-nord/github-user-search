package info.olof.githubsearch.rest.mapper;

import info.olof.githubsearch.generated.model.GitHubError;
import info.olof.githubsearch.generated.model.GitHubErrorResponse;
import info.olof.githubsearch.generated.model.SimpleRepositorySearchError;
import info.olof.githubsearch.generated.model.SimpleRepositorySearchErrorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GitHubErrorMapper {

    SimpleRepositorySearchErrorResponse gitHubErrorResponseToSimpleRepositorySearchErrorResponse(GitHubErrorResponse response);

    @Mapping(source = "message", target = "detailedMessage")
    SimpleRepositorySearchError gitHubErrorToSimpleRepositorySearchError(GitHubError error);
}
