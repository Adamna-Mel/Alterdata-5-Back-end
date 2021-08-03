package br.com.alterdata.pack.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization" , "header");
	}

	private SecurityContext securityContext(){
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth(){

		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;

		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
		.apiInfo(apiInfo())
		.securityContexts(Arrays.asList(securityContext()))
		.securitySchemes(Arrays.asList(apiKey()))
		.select()
		.apis(RequestHandlerSelectors.any())
		.paths(PathSelectors.any())
		.build();
		
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Aplicação PACK", "Api para Sistema de Status e Papéis", "1.0", "Termos de Serviços",
				new Contact("Equipe Alterdata 5", "https://github.com/Adamna-Mel/Alterdata-5-Back-end",
						"amanda9@estudante.firjan.senai.br"),
				"Apache License Version 2.0", "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>());
	}
/*
	@Bean
	public Docket swagger() {
		final List<ResponseMessage> getMessages = new ArrayList<ResponseMessage>();
		getMessages.add(new ResponseMessageBuilder().code(500).message("500 message")
				.responseModel(new ModelRef("Error")).build());
		getMessages.add(new ResponseMessageBuilder().code(403).message("Forbidden").build());
		getMessages.add(new ResponseMessageBuilder().code(401).message("Unauthorized").build());

		Set<String> produces = new HashSet<>();
		produces.add("application/json");

		Set<String> consumes = new HashSet<>();
		consumes.add("application/json");

		
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.any()).paths(regex("/api.*"))
				.build()
				.securitySchemes(Collections.singletonList(new ApiKey("JWT", "Authorization", "Header")))
		        .securityContexts(singletonList(
		            SecurityContext.builder()
		                .securityReferences(
		                    singletonList(SecurityReference.builder()
		                        .reference("JWT")
		                        .scopes(new AuthorizationScope[0])
		                        .build()
		                    )
		                )
		                .build())
		        )
				.produces(produces).consumes(consumes).globalResponseMessage(RequestMethod.GET, getMessages)
	            .globalResponseMessage(RequestMethod.GET, getMessages);

	}

	// final Predicate<RequestHandler> requestHandlers() {
		
	// 	Set<Predicate<RequestHandler>> matchers = new HashSet<Predicate<RequestHandler>>();
	// 	matchers.add(RequestHandlerSelectors.basePackage("com.salesmanager.shop.store.api.v1"));
	// 	matchers.add(RequestHandlerSelectors.basePackage("com.salesmanager.shop.store.api.v2"));
		
	// 	return Predicates.or(matchers);

 	// }

 	@SuppressWarnings("rawtypes")
	private ApiInfo apiInfo() {
		return new ApiInfo("Aplicação PACK", "Api para Sistema de Status e Papéis", "1.0", "Termos de Serviços",
				new Contact("Equipe Alterdata 5", "https://github.com/Adamna-Mel/Alterdata-5-Back-end",
						"amanda9@estudante.firjan.senai.br"),
				"Apache License Version 2.0", "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>());
	}

	
	private static ArrayList<? extends SecurityScheme> securitySchemes() {
		return (ArrayList<? extends SecurityScheme>) Stream.of(new ApiKey("Bearer", "Authorization", "header"))
				.collect(Collectors.toList());
	}
*/
}
