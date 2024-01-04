package n1b3lung0.apiGym.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@Profile(AppProfile.SWAGGER)
public class OpenAPIConfig {

    @Value("${n1b3lung0.openapi.dev-url}")
    private String devUrl;

    @Value("${n1b3lung0.openapi.prod-url}")
    private String prodUrl;

    private static final String DEVSERVER_DESCRIPTION = "Server URL in Development Environment";
    private static final String PRODSERVER_DESCIPTION = "Server URL in Production Environment";

    @Bean
    public OpenAPI configOpenAPI() {
        Contact contact = new Contact()
                .email("carlos.martinez.crespo@gmail.com")
                .name("n1b3lung0")
                .url("https://n1b3lung0.dev");

        License mitLicense = new License().name("MIT License").url("whatever");

        Info info = new Info()
                .title("Exercise API")
                .version("v1.0")
                .contact(contact)
                .description("API to manage GYM Exercises.")
                .license(mitLicense);

        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription(DEVSERVER_DESCRIPTION);

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription(PRODSERVER_DESCIPTION);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }

    @Bean
    public OpenApiCustomiser sortTagsAlphabetically() {
        return openApi -> openApi.setTags(openApi.getTags()
                .stream()
                .sorted(Comparator.comparing(tag -> StringUtils.stripAccents(tag.getName())))
                .collect(Collectors.toList()));
    }
}
