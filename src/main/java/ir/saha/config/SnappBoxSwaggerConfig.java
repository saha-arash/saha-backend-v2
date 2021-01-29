package ir.saha.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Profile("!pro")
@Configuration
@EnableSwagger2
public class SnappBoxSwaggerConfig {

@Bean
public Docket api() {
return new Docket(DocumentationType.SWAGGER_2).groupName("Arsham kolah keyri")
.globalOperationParameters(
Collections.singletonList(new ParameterBuilder()
.name("Authorization")
.description("Authorization token")
.modelRef(new ModelRef("string"))
.parameterType("header")
.required(true)
.build()))
.select()
.apis(RequestHandlerSelectors.basePackage("ir.saha"))
.paths(PathSelectors.any())
.build();
}
@Bean
public UiConfiguration uiConfig() {
return UiConfigurationBuilder.builder()
.deepLinking(true)
.displayOperationId(false)
.defaultModelsExpandDepth(1)
.defaultModelExpandDepth(1)
.defaultModelRendering(ModelRendering.EXAMPLE)
.displayRequestDuration(false)
.docExpansion(DocExpansion.NONE)
.filter(false)
.maxDisplayedTags(null)
.operationsSorter(OperationsSorter.ALPHA)
.showExtensions(false)
.tagsSorter(TagsSorter.ALPHA)
.supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
.validatorUrl(null)
.build();
}
}