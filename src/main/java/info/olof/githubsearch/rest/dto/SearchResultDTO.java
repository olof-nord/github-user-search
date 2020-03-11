package info.olof.githubsearch.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchResultDTO {

    private String username;
    private String name;
    private URI avatarURL;
    private int numberOfFollowers = 0;

}
