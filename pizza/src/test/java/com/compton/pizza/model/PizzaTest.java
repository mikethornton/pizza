/**
 * 
 */
package com.compton.pizza.model;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * Test to check equals hashcode impls
 * 
 * @author dell
 *
 */
public class PizzaTest {
	@Test
	public void equalsContract() {
	    EqualsVerifier.forClass(Pizza.class).verify();
	}
}
