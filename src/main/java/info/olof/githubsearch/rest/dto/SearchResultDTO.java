package info.olof.githubsearch.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchResultDTO {

    private String username;
    private String name;
    private String avatarURL;
    private int numberOfFollowers = 0;

}
