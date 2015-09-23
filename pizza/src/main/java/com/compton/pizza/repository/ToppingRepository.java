/**
 * 
 */
package com.compton.pizza.repository;

import org.springframework.data.repository.CrudRepository;

import com.compton.pizza.model.Topping;

/**
 * Topping repository which spring data rest will create concrete class
 * for at runtime and hook up to spring mvc to expose.
 * 
 * @author mike
 *
 */
public interface ToppingRepository extends CrudRepository<Topping, Long> {
}
