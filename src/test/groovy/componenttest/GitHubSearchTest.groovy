package componenttest

import componenttest.setup.BaseComponentTest
import componenttest.setup.httpclient.HttpClient
import componenttest.setup.wiremock.GitHubSearchAPIMock

class GitHubSearchTest extends BaseComponentTest {

    def "Should successfully search for a repository"() {

        given: "A valid GitHub repository search query"
        def userName = "olof-nord"
        def programmingLanguage = "Java"

        and: "The GitHub API will respond successfully to our request"
        GitHubSearchAPIMock.instance.mockRepositorySearchSuccess()

        when: "The request is sent"
        HttpClient.Response gitHubSearchResponse = HttpClient.request().withUrl("/search")
            .withQueryParameters([username: userName, programming_language: programmingLanguage])
            .create()
            .get()

        then: "The request is successful"
        gitHubSearchResponse.statusCodeValue == 200

        gitHubSearchResponse.json.username =~ userName
        gitHubSearchResponse.json.name =~ 'github-user-search'
        gitHubSearchResponse.json.avatarUrl == ['https://avatars2.githubusercontent.com/u/488769?v=4']
        gitHubSearchResponse.json.numberOfFollowers =~ 0

    }

    def "Correctly handles a search failure"() {

        given: "An invalid GitHub repository search query"
        def userName = "does-not-exist-at-all"
        def programmingLanguage = "Java"

        and: "The GitHub API will respond with search validation failed"
        GitHubSearchAPIMock.instance.mockRepositorySearchValidationFailed()

        when: "The request is sent"
        HttpClient.Response gitHubSearchResponse = HttpClient.request().withUrl("/search")
            .withQueryParameters([username: userName, programming_language: programmingLanguage])
            .create()
            .get()

        then: "The request is not successful"
        gitHubSearchResponse.statusCodeValue == 400
        !gitHubSearchResponse.hasBody()

    }
}
