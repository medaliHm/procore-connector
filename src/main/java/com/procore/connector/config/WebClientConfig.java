package com.procore.connector.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebClientConfig {



	@Bean
	@Qualifier("login-rest")
	public RestTemplate restLoginTemplate(RestTemplateBuilder builder) {

		RestTemplate template = builder.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE,
				MediaType.APPLICATION_JSON_UTF8_VALUE).build();
		return template;
	}

	@Bean
	@Qualifier("multipart-rest")
	public RestTemplate restPartTemplate(RestTemplateBuilder builder) {

		RestTemplate template = builder.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE)
				.build();
		return template;
	}

	@Bean
	@Qualifier("json-rest")
	public RestTemplate restJsonTemplate(RestTemplateBuilder builder) {
		RestTemplate template = builder.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE,
				MediaType.APPLICATION_JSON_UTF8_VALUE).build();
		return template;
	}
}
