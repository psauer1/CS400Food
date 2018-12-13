/**
 * Filename:   FoodItem.java
 * Project:    p5 - Meal Analysis
 * Authors:    Peter Sauer, Riley Ley, Taylor Chappell, Andy Boho, Nathan Husemoller
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * Lecture:    002 & 003
 * 
 * 
 * Due Date:   Before 10pm on December 12, 2018
 * Version:    1.0
 * 
 * Credits:    aka
 *
 * Bugs:       
 */

package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class represents a food item with all its properties.
 *
 * @author aka
 */
public class FoodItem {

	/* fields */
	private String name; // The name of the food item.
	private String id; // The id of the food item.
	private HashMap<String, Double> nutrients = new HashMap<>(); // Map of nutrients and value.

	/**
	 * Constructor
	 * 
	 * @param name
	 *            name of the food item
	 * @param id
	 *            unique id of the food item
	 */
	public FoodItem(String id, String name) {
		this.name = name;
		this.id = id;
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 *            name of the food item
	 * @param id
	 *            unique id of the food item
	 */
	public FoodItem(String id, String name, double cal, double fat, double protein, double carb, double fiber) {
		this.name = name;
		this.id = id;
		addNutrient("calories", cal);
		addNutrient("fat", fat);
		addNutrient("protein", protein);
		addNutrient("carb", carb);
		addNutrient("fiber", fiber);
	}

	/**
	 * Gets the name of the food item
	 *
	 * @return name of the food item
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the unique id of the food item
	 *
	 * @return id of the food item
	 */
	public String getID() {
		return id;
	}

	/**
	 * Gets the nutrients of the food item
	 *
	 * @return nutrients of the food item
	 */
	public HashMap<String, Double> getNutrients() {
		return nutrients;
	}

	/**
	 * Adds a nutrient and its value to this food. If nutrient already exists,
	 * updates its value.
	 * 
	 * @param name,
	 *            value
	 */
	public void addNutrient(String name, double value) {
		nutrients.put(name, value);
	}

	/**
	 * Adds the inputed nutritional values to the data structure
	 * 
	 * @param cal
	 * @param fat
	 * @param protein
	 * @param carb
	 * @param fiber
	 */
	public void addAllNutrients(double cal, double fat, double protein, double carb, double fiber) {
		addNutrient("calories", cal);
		addNutrient("fat", fat);
		addNutrient("protein", protein);
		addNutrient("carb", carb);
		addNutrient("fiber", fiber);
	}

	/**
	 * Returns the value of the given nutrient value for this food item Returns 0 if
	 * food item is not found
	 * 
	 * @param name
	 * @return
	 */
	public double getNutrientValue(String name) {
		return nutrients.get(name);
	}

	/* accessors */

	public double getCal() {
		return nutrients.get("calories");
	}

	public double getFat() {
		return nutrients.get("fat");
	}

	public double getProtein() {
		return nutrients.get("protein");
	}

	public double getCarb() {
		return nutrients.get("carb");
	}

	public double getFiber() {
		return nutrients.get("fiber");
	}
}
