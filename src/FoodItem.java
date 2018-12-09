
import java.util.ArrayList;
		import java.util.HashMap;
		import java.util.List;

/**
 * This class represents a food item with all its properties.
 *
 * @author aka
 */
public class FoodItem {
	// The name of the food item.
	private String name;

	// The id of the food item.
	private String id;

	// Map of nutrients and value.
	private HashMap<String, Double> nutrients = new HashMap<>();

	/**
	 * Constructor
	 * @param name name of the food item
	 * @param id unique id of the food item
	 */
	public FoodItem(String id, String name) {
		this.name = name;
		this.id = id;
	}

	/**
	 * Constructor
	 * @param name name of the food item
	 * @param id unique id of the food item
	 */
	public FoodItem(String id, String name, double cal, double fat, double protein, double carb, double fiber) {
		this.name = name;
		this.id = id;
		addNutrient("calories",cal);
		addNutrient("fat",fat);
		addNutrient("protein",protein);
		addNutrient("carbs",carb);
		addNutrient("fiber",fiber);

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
	 * Adds a nutrient and its value to this food.
	 * If nutrient already exists, updates its value.
	 */
	public void addNutrient(String name, double value) {
		nutrients.put(name,value);
	}

	public void addAllNutrients(double cal, double fat, double protein, double carb, double fiber){
		addNutrient("calories",cal);
		addNutrient("fat",fat);
		addNutrient("protein",protein);
		addNutrient("carbs",carb);
		addNutrient("fiber",fiber);

	}

	/*accessors*/
	public double getCal() {
		return nutrients.get("calories");
	}

	public double getFat() { return nutrients.get("fat"); }

	public double getProtein() {
		return nutrients.get("protein");
	}

	public double getCarb() {
		return nutrients.get("carbs");
	}

	public double getFiber() {
		return nutrients.get("fiber");
	}

	/**
	 * Returns the value of the given nutrient for this food item.
	 * If not present, then returns 0.
	 */
	public double getNutrientValue(String name) {
		return nutrients.get(name);
	}

}