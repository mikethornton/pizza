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
public class ToppingTest {
	@Test
	public void equalsContract() {
	    EqualsVerifier.forClass(Topping.class).verify();
	}
}
