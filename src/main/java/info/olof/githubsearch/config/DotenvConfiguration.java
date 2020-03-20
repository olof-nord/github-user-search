package info.olof.githubsearch.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotenvConfiguration {

    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure()
            .ignoreIfMissing()
            .ignoreIfMalformed()
            .load();
    }
}
