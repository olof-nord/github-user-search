package info.olof.githubsearch.service;

import com.ginsberg.junit.exit.ExpectSystemExit;
import info.olof.githubsearch.config.DotenvConfiguration;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junitpioneer.jupiter.ClearSystemProperty;
import org.junitpioneer.jupiter.SetSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import({
    GitHubAccessTokenService.class
})
public class GitHubAccessTokenServiceTests {

    @Autowired
    private GitHubAccessTokenService customerService;

    @MockBean
    private DotenvConfiguration dotenvConfiguration;

    @MockBean
    private Dotenv dotenv;

    @BeforeEach
    public void setup() {
        when(dotenvConfiguration.dotenv()).thenReturn(dotenv);
    }

    @Test
    public void givenGetToken_whenDotEnvAvailable_thenUseIt() {
        String dotEnvTokenValue = "DotEnv";

        when(dotenv.get(anyString())).thenReturn(dotEnvTokenValue);

        assertEquals(customerService.getToken(), "token " + dotEnvTokenValue);
    }

    @Test
    @SetSystemProperty(key = "GITHUB_ACCESS_TOKEN", value = "System")
    public void givenGetToken_whenNoDotEnvAvailable_thenCheckSystemProperty() {

        assertEquals(customerService.getToken(), "token " + "System");
    }

    @Test
    @ExpectSystemExit
    @ClearSystemProperty(key = "GITHUB_ACCESS_TOKEN")
    public void givenGetToken_whenNoTokenAvailable_thenShutdown() {

        when(dotenv.get(anyString())).thenReturn(null);

        customerService.getToken();
    }

}
