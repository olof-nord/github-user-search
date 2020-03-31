package info.olof.githubsearch.config;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriTemplate;

import java.time.OffsetDateTime;

@Configuration
public class GsonConfiguration {

    @Bean
    public Gson gson() {
        return new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setPrettyPrinting()
            .registerTypeAdapter(OffsetDateTime.class, (JsonDeserializer<OffsetDateTime>)
                (json, type, context) -> OffsetDateTime.parse(json.getAsString())
            )
            .registerTypeAdapter(UriTemplate.class, (JsonDeserializer<UriTemplate>)
                (json, type, context) -> new UriTemplate(json.getAsString())
            )
            .create();
    }
}
