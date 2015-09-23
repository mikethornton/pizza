/**
 * 
 */
package com.compton.pizza.repository;

import java.text.MessageFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.compton.pizza.PizzaApplication;

/**
 * Abstract class for implementation rest integration tests to extend.
 * 
 * @author dell
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PizzaApplication.class)
@WebIntegrationTest(randomPort = true)
public abstract class AbstractImplementationTest<V, T extends CrudRepository<V, Long>> {
	private static final String URI_WITH_PLACEHOLDER = "http://localhost:{0}/";
	private String baseUrl;
	private RestTemplate template = new RestTemplate();
	@Value("${local.server.port}")
	int port;
	@Autowired
	private T repository;
	
	@Before
	public void setup(){
		baseUrl = MessageFormat.format(URI_WITH_PLACEHOLDER + getURLPath(), Integer.toString(port));
		doSetup();
	}
	
	public abstract void doSetup();

	/**
	 * After test delete any objects created.
	 */
	@After
	public void after() {
		getRepository().deleteAll();
	}
	
	protected abstract String getURLPath();
	
	protected T getRepository(){
		return repository;
	}
	
	protected String getBaseUrl(){
		return baseUrl;
	}
	
	protected RestTemplate getTemplate(){
		return template;
	}
}
