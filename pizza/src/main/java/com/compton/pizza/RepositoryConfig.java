package com.compton.pizza;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import com.compton.pizza.model.Pizza;

/**
 * Ensure that ids are returned by spring data rest when domain
 * objects are loaded
 * 
 * @author mike
 *
 */
@Configuration
public class RepositoryConfig extends RepositoryRestMvcConfiguration {
	@Override
	protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Pizza.class);
	}
}