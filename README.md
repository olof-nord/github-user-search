# GitHub User Search

Search for GitHub users using the GitHub Search API

### Build requirements
- Java 13
- Maven 3.6

### Tooling
- [spring-boot](https://github.com/spring-projects/spring-boot)
- [openapi-generator-maven-plugin](https://github.com/OpenAPITools/openapi-generator/tree/master/modules/openapi-generator-maven-plugin) for API client and data model generation
- [retrofit](https://github.com/square/retrofit) with [rxjava2](https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava2) as HTTP client 
- [java-dotenv](https://github.com/cdimascio/java-dotenv) for .env file support

### GitHub Search API
- The information is fetched from the [GitHub API](https://developer.github.com).
- Note that an GitHub OAuth2 token is required for authentication. 
- This should be put in a `.env` file in the root folder with a key `GITHUB_ACCESS_TOKEN=xyz` specified.

### Build JAR and run tests
`mvn clean package`  

### Build docker  
`docker build . --tag github-user-search`

### Start service
`mvn spring-boot:run`

## Usage
The service provides a single API endpoint to to search for GitHub users by specifying a programming language 
they use in their public repositories as well as an username string.

Note that the username should be written with an initial @ sign.

An example request:  
`http://localhost:8080/search?username=@olof-nord&programming_language=Java`

Example response:
```json
[
    {
        "username": "olof-nord",
        "name": "java-clock",
        "avatarURL": "https://avatars2.githubusercontent.com/u/488769?v=4",
        "numberOfFollowers": 0
    },
    {
        "username": "olof-nord",
        "name": "bank-backend-java",
        "avatarURL": "https://avatars2.githubusercontent.com/u/488769?v=4",
        "numberOfFollowers": 0
    },
    {
        "username": "olof-nord",
        "name": "github-user-search",
        "avatarURL": "https://avatars2.githubusercontent.com/u/488769?v=4",
        "numberOfFollowers": 0
    }
]
```
