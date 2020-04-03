package componenttest.setup.wiremock

import static com.github.tomakehurst.wiremock.client.WireMock.*
import static groovy.json.JsonOutput.toJson

@Singleton(lazy = true, strict = false)
class GitHubSearchAPIMock extends BaseWireMock {

    public static final String SEARCH_URL = "/search/repositories"

    @Override
    int getPort() {
        8000
    }

    void mockRepositorySearchSuccess() {

        Map responseJson = [
            total_count       : 1,
            incomplete_results: false,
            items             : [
                [
                    id               : 245885678,
                    node_id          : "MDEwOlJlcG9zaXRvcnkyNDU4ODU2Nzg=",
                    name             : "github-user-search",
                    full_name        : "olof-nord/github-user-search",
                    private          : false,
                    owner            : [
                        login              : "olof-nord",
                        id                 : 488769,
                        node_id            : "MDQ6VXNlcjQ4ODc2OQ==",
                        avatar_url         : "https://avatars2.githubusercontent.com/u/488769?v=4",
                        gravatar_id        : "",
                        url                : "https://api.github.com/users/olof-nord",
                        html_url           : "https://github.com/olof-nord",
                        followers_url      : "https://api.github.com/users/olof-nord/followers",
                        following_url      : "https://api.github.com/users/olof-nord/following{/other_user}",
                        gists_url          : "https://api.github.com/users/olof-nord/gists{/gist_id}",
                        starred_url        : "https://api.github.com/users/olof-nord/starred{/owner}{/repo}",
                        subscriptions_url  : "https://api.github.com/users/olof-nord/subscriptions",
                        organizations_url  : "https://api.github.com/users/olof-nord/orgs",
                        repos_url          : "https://api.github.com/users/olof-nord/repos",
                        events_url         : "https://api.github.com/users/olof-nord/events{/privacy}",
                        received_events_url: "https://api.github.com/users/olof-nord/received_events",
                        type               : "User",
                        site_admin         : false
                    ],
                    html_url         : "https://github.com/olof-nord/github-user-search",
                    description      : "A GitHub user search using the GitHub Search API ",
                    fork             : false,
                    url              : "https://api.github.com/repos/olof-nord/github-user-search",
                    forks_url        : "https://api.github.com/repos/olof-nord/github-user-search/forks",
                    keys_url         : "https://api.github.com/repos/olof-nord/github-user-search/keys{/key_id}",
                    collaborators_url: "https://api.github.com/repos/olof-nord/github-user-search/collaborators{/collaborator}",
                    teams_url        : "https://api.github.com/repos/olof-nord/github-user-search/teams",
                    hooks_url        : "https://api.github.com/repos/olof-nord/github-user-search/hooks",
                    issue_events_url : "https://api.github.com/repos/olof-nord/github-user-search/issues/events{/number}",
                    events_url       : "https://api.github.com/repos/olof-nord/github-user-search/events",
                    assignees_url    : "https://api.github.com/repos/olof-nord/github-user-search/assignees{/user}",
                    branches_url     : "https://api.github.com/repos/olof-nord/github-user-search/branches{/branch}",
                    tags_url         : "https://api.github.com/repos/olof-nord/github-user-search/tags",
                    blobs_url        : "https://api.github.com/repos/olof-nord/github-user-search/git/blobs{/sha}",
                    git_tags_url     : "https://api.github.com/repos/olof-nord/github-user-search/git/tags{/sha}",
                    git_refs_url     : "https://api.github.com/repos/olof-nord/github-user-search/git/refs{/sha}",
                    trees_url        : "https://api.github.com/repos/olof-nord/github-user-search/git/trees{/sha}",
                    statuses_url     : "https://api.github.com/repos/olof-nord/github-user-search/statuses/{sha}",
                    languages_url    : "https://api.github.com/repos/olof-nord/github-user-search/languages",
                    stargazers_url   : "https://api.github.com/repos/olof-nord/github-user-search/stargazers",
                    contributors_url : "https://api.github.com/repos/olof-nord/github-user-search/contributors",
                    subscribers_url  : "https://api.github.com/repos/olof-nord/github-user-search/subscribers",
                    subscription_url : "https://api.github.com/repos/olof-nord/github-user-search/subscription",
                    commits_url      : "https://api.github.com/repos/olof-nord/github-user-search/commits{/sha}",
                    git_commits_url  : "https://api.github.com/repos/olof-nord/github-user-search/git/commits{/sha}",
                    comments_url     : "https://api.github.com/repos/olof-nord/github-user-search/comments{/number}",
                    issue_comment_url: "https://api.github.com/repos/olof-nord/github-user-search/issues/comments{/number}",
                    contents_url     : "https://api.github.com/repos/olof-nord/github-user-search/contents/{+path}",
                    compare_url      : "https://api.github.com/repos/olof-nord/github-user-search/compare/{base}...{head}",
                    merges_url       : "https://api.github.com/repos/olof-nord/github-user-search/merges",
                    archive_url      : "https://api.github.com/repos/olof-nord/github-user-search/{archive_format}{/ref}",
                    downloads_url    : "https://api.github.com/repos/olof-nord/github-user-search/downloads",
                    issues_url       : "https://api.github.com/repos/olof-nord/github-user-search/issues{/number}",
                    pulls_url        : "https://api.github.com/repos/olof-nord/github-user-search/pulls{/number}",
                    milestones_url   : "https://api.github.com/repos/olof-nord/github-user-search/milestones{/number}",
                    notifications_url: "https://api.github.com/repos/olof-nord/github-user-search/notifications{?since,all,participating}",
                    labels_url       : "https://api.github.com/repos/olof-nord/github-user-search/labels{/name}",
                    releases_url     : "https://api.github.com/repos/olof-nord/github-user-search/releases{/id}",
                    deployments_url  : "https://api.github.com/repos/olof-nord/github-user-search/deployments",
                    created_at       : "2020-03-08T20:46:57Z",
                    updated_at       : "2020-04-01T21:58:00Z",
                    pushed_at        : "2020-04-01T21:57:57Z",
                    git_url          : "git://github.com/olof-nord/github-user-search.git",
                    ssh_url          : "git@github.com:olof-nord/github-user-search.git",
                    clone_url        : "https://github.com/olof-nord/github-user-search.git",
                    svn_url          : "https://github.com/olof-nord/github-user-search",
                    homepage         : "",
                    size             : 85,
                    stargazers_count : 0,
                    watchers_count   : 0,
                    language         : "Java",
                    has_issues       : true,
                    has_projects     : true,
                    has_downloads    : true,
                    has_wiki         : true,
                    has_pages        : false,
                    forks_count      : 0,
                    mirror_url       : null,
                    archived         : false,
                    disabled         : false,
                    open_issues_count: 0,
                    license          : [
                        key    : "gpl-3.0",
                        name   : "GNU General Public License v3.0",
                        spdx_id: "GPL-3.0",
                        url    : "https://api.github.com/licenses/gpl-3.0",
                        node_id: "MDc6TGljZW5zZTk="
                    ],
                    forks            : 0,
                    open_issues      : 0,
                    watchers         : 0,
                    default_branch   : "master",
                    score            : 1.0
                ]
            ]
        ]

        mockGitHubRepositorySearch(200, responseJson)
    }

    void mockRepositorySearchValidationFailed() {

        Map responseJson = [
            message            : "Validation Failed",
            errors             : [
                [
                    message : "The listed users and repositories cannot be searched either because the resources do not exist or you do not have permission to view them.",
                    resource: "Search",
                    field   : "q",
                    code    : "invalid"
                ]
            ],
            documentation_url: "https://developer.github.com/v3/search/"

        ]

        mockGitHubRepositorySearch(422, responseJson)
    }

    void mockGitHubRepositorySearch(int responseStatus, Map response) {

        wireMock.register(
            get(urlPathEqualTo(SEARCH_URL))
                .willReturn(aResponse()
                    .withStatus(responseStatus)
                    .withHeader("content-type", "application/json; charset=utf-8")
                    .withBody(toJson(response))
                )
        )
    }
}
