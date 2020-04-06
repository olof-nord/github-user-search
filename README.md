# GitHub User Search

Search for GitHub users using the GitHub Search API

### Build requirements
- Java 13
- Maven 3.6

### Tooling
- [spring-boot](https://github.com/spring-projects/spring-boot)
- [openapi-generator-maven-plugin](https://github.com/OpenAPITools/openapi-generator/tree/master/modules/openapi-generator-maven-plugin) for API client and data model generation
- [retrofit](https://github.com/square/retrofit) with [rxjava2](https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava2) as HTTP client 
- [mapstruct](https://github.com/mapstruct/mapstruct) for response object field mapping
- [java-dotenv](https://github.com/cdimascio/java-dotenv) for .env file support
- [junit-pioneer](https://github.com/junit-pioneer/junit-pioneer) for Java properties testing support
- [junit5-system-exit](https://github.com/tginsberg/junit5-system-exit) for Junit5 System.exit testing support
- [spockframework](https://github.com/spockframework/spock) with [groovy](https://github.com/apache/groovy) for component tests
- [wiremock](https://github.com/tomakehurst/wiremock) for mocking HTTP calls

### GitHub Search API
- The information is fetched from the [GitHub API](https://developer.github.com).
- Note that a GitHub OAuth2 token is required for authentication. 
- This could be put in a `.env` file in the root folder with a key `GITHUB_ACCESS_TOKEN=xyz` specified.
- This could also be set with a system property `mvn -DGITHUB_ACCESS_TOKEN=xyz spring-boot:run`.

### Build JAR and run tests
`mvn clean package`  

### Build docker  
`docker build . --tag github-user-search`

### Start service with Maven
`mvn spring-boot:run`

### Start service with Docker
`docker run -p 8080:8080 -e GITHUB_ACCESS_TOKEN=xyz -it github-user-search:latest`

## Usage
The service provides a single API endpoint to to search for GitHub users by specifying a programming language 
they use in their public repositories as well as an username string.

An example request:  
`http://localhost:8080/search?username=olof-nord&programming_language=Java`

Example successful response:
```json
{
    "data": [
        {
            "username": "olof-nord",
            "name": "java-clock",
            "avatarUrl": "https://avatars2.githubusercontent.com/u/488769?v=4",
            "numberOfFollowers": 1
        },
        {
            "username": "olof-nord",
            "name": "bank-backend-java",
            "avatarUrl": "https://avatars2.githubusercontent.com/u/488769?v=4",
            "numberOfFollowers": 0
        },
        {
            "username": "olof-nord",
            "name": "github-user-search",
            "avatarUrl": "https://avatars2.githubusercontent.com/u/488769?v=4",
            "numberOfFollowers": 0
        }
    ],
    "error": null
}
```

Another example request:  
`http://localhost:8080/search?username=does-not-exist-at-all&programming_language=Java`

Example unsuccessful response:
```json
{
    "data": [],
    "error": {
        "message": "Validation Failed",
        "errors": [
            {
                "detailedMessage": "The listed users and repositories cannot be searched either because the resources do not exist or you do not have permission to view them."
            }
        ]
    }
}
```
