package componenttest.setup.wiremock

import org.junit.Before
import org.junit.BeforeClass

trait WireMockTesting {

    @BeforeClass
    def setupSpecAllWireMocks() {
        GitHubSearchAPIMock.instance.start()
    }

    @Before
    def setupAllWireMocks() {
        GitHubSearchAPIMock.instance.reset()
    }
}
