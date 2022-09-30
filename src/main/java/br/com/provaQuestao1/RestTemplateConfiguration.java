package br.com.provaQuestao1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.apache.http.impl.client.CloseableHttpClient;
public class RestTemplateConfiguration {
	
	@Autowired
	CloseableHttpClient httpClient;
	 
	@Value("https://jsonplaceholder.typicode.com/users")
	private String apiHost;
	 
	@Bean
	public RestTemplate restTemplate() {

		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
		restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(apiHost));
		return restTemplate;
	}
	 
	@Bean
	@ConditionalOnMissingBean
	public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {

		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
				= new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setHttpClient(httpClient);
		return clientHttpRequestFactory;
	}
}



