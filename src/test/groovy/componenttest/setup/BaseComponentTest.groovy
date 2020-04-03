package componenttest.setup

import componenttest.setup.httpclient.HttpClientTesting
import componenttest.setup.wiremock.WireMockTesting
import info.olof.githubsearch.GithubSearchApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles("test")
@SpringBootTest(classes = GithubSearchApplication, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BaseComponentTest extends Specification implements HttpClientTesting, WireMockTesting {

}
