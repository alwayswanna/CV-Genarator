package a.gleb.cv.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.Properties;

@Slf4j
@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(
                        new Info()
                                .title("CV-generator-backend")
                                .description("University task")
                                .termsOfService(StringUtils.EMPTY)
                                .version(getBuildVersion())
                );
    }

    @SneakyThrows
    private String getBuildVersion() {
        InputStream inputStream = null;
        try {
            Properties properties = new Properties();
            inputStream = getClass().getResourceAsStream("/META-INF/build-info.properties");
            properties.load(inputStream);
            return properties.getProperty("build.version");
        } catch (Exception e) {
            log.error("{}_ERROR, can not load application version", getClass().getSimpleName());
        } finally {
            if (inputStream != null) inputStream.close();
        }
        return StringUtils.EMPTY;
    }
}
