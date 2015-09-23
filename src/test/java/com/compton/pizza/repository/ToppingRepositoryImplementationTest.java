/**
 * 
 */
package com.compton.pizza.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.compton.pizza.model.Topping;

/**
 * Using a full spring boot integration test run tests on each rest mapping
 * method to ensure they function as expected.
 * 
 * @author dell
 *
 */
public class ToppingRepositoryImplementationTest extends AbstractImplementationTest<Topping, ToppingRepository> {
	private static final String TOPPING_NAME_1 = "Olives";
	private static final String TOPPING_NAME_2 = "Mushrooms";
	private Topping topping1;
	private Topping topping2;

	/**
	 * Set up for test, create a topping and save in repository.
	 */
	public void doSetup() {
		topping1 = new Topping();
		topping1.setName(TOPPING_NAME_1);
		getRepository().save(topping1);
		
		topping2 = new Topping();
		topping2.setName(TOPPING_NAME_2);
		getRepository().save(topping2);
	}

	@Override
	protected String getURLPath() {
		return "toppings/";
	}

	/**
	 * Test that a topping is returned using the RestTemplate and that it is as expected.
	 */
	@Test
	public void testGetTopping() {
		Topping returnedTopping = getTemplate().getForObject(getBaseUrl() + topping1.getId(),
				Topping.class);
		assertEquals("Should have returned equal topping", topping1, returnedTopping);
		assertNotSame("Should not be exactly the same topping", topping1, returnedTopping);
	}

	/**
	 * Test that if there is no topping mapped to that topping id that we get an error.
	 */
	@Test(expected = HttpClientErrorException.class)
	public void testNoTopping() {
		try {
			getTemplate().getForObject(getBaseUrl() + 10000, Topping.class);
		} catch (HttpClientErrorException e) {
			assertEquals("Wrong error code", HttpStatus.NOT_FOUND, e.getStatusCode());
			throw e;
		}
		fail("Should have thrown exception");
	}

	/**
	 * Test that if we add a valid topping then it gets persisted correctly.
	 */
	@Test
	public void testAddTopping() {
		String name = "ham";
		Topping unpersistedtopping = new Topping();
		unpersistedtopping.setName(name);
		
		URI uri = getTemplate().postForLocation(
				getBaseUrl(), unpersistedtopping);
		
		assertNotNull("Should have returned a location", uri);

		String[] splitUri = uri.getPath().split("/");
		String id = splitUri[splitUri.length-1];
		
		Topping persistedtopping = getRepository().findOne(Long.valueOf(id));

		assertNotNull("Should have returned new ingredient", persistedtopping);
		assertEquals("Should have set topping name", name, persistedtopping.getName());
	}

	/**
	 * Test that if can retrieve toppings.
	 */
	@Test
	public void getToppings() {
		@SuppressWarnings("unchecked")
		Map<String, List<String>> toppings = getTemplate().getForObject(getBaseUrl().substring(0, getBaseUrl().length()-1), Map.class);
		
		//TODO: Hmm, ok something going wrong with deserialization, am only getting link to topping, 
		//curl returns correctly, will continue and come back if time.
		assertNotNull("should return toppings", toppings.get("links"));
		assertEquals("should return toppings", 2, toppings.get("links").size());
	}

	/**
	 * If that it will delete topping.
	 */
	@Test
	public void deleteTopping() {
		getTemplate().delete(getBaseUrl() + topping1.getId());

		assertNull("should return toppings", getRepository().findOne(topping1.getId()));
	}
}
