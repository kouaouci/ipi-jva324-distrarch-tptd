package com.ipi.jva324;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.ipi.jva324.stock.model.ProduitEnStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class Jva324Application {

	public static void main(String[] args) {
		SpringApplication.run(Jva324Application.class, args);
	}

	/**
	 * else can't resolve HTML views because no defaultViewResolver
	 * because no WebMvcAutoConfiguration even with @EnableWebMvc
	 */
	///@Configuration

	@Bean
	public RepositoryRestConfigurer repositoryRestConfigurer() {
		return RepositoryRestConfigurer.withConfig(config -> {
			config.exposeIdsFor( ProduitEnStock.class, ProduitEnStock.class);
		});
	}
	//Communication  connsomation produit
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
  //Communication  connsomation produit
	@Bean
	public RestTemplate springDataRestTemplate(@Autowired ObjectMapper objectMapper) {
   // converter qui sait convertir le format HAL et le dit :
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes( Arrays.asList(org.springframework.hateoas.MediaTypes.HAL_JSON));
		converter.setObjectMapper(objectMapper);
		return new RestTemplate( Collections.singletonList(converter));
	}
	public class TriggeringWebMvcAutoConfiguration extends WebMvcAutoConfiguration {

		public TriggeringWebMvcAutoConfiguration() {

		}

	}

}
