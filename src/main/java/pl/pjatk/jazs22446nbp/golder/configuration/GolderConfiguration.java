package pl.pjatk.jazs22446nbp.golder.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class GolderConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Docket get() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("pl.pjatk.jazs22446nbp"))
                .build().apiInfo(createApiInfo())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo createApiInfo() {
        return new ApiInfo("NBP Gold Exchange Rate Scrapper",
                "NBP API Gold Exchange Scrapper",
                "1.0",
                "",
                new Contact("Michał", "https://github.com/s22446", "s22446@pjwstk.edu.pl"),
                "",
                "",
                Collections.emptyList()
        );
    }
}
