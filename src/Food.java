public class Food {

	/**
	 * Single constructor that creates the instance of the food
	 * 
	 * @param name
	 * @param cal
	 * @param fat
	 * @param protein
	 * @param carb
	 * @param fiber
	 */
	public Food(String name, float cal, float fat, float protein, float carb, float fiber) {
		this.name = name;
		this.cal = cal;
		this.fat = fat;
		this.protein = protein;
		this.carb = carb;
		this.fiber = fiber;
	}
	
	/*fields*/
	String name;
	float cal;
	float fat;
	float protein;
	float carb;
	float fiber;
	
	/*accessors*/
	public String getName() {
		return name;
	}
	
	public float getCal() {
		return cal;
	}
	
	public float getFat() {
		return fat;
	}
	
	public float getProtein() {
		return protein;
	}
	
	public float getCarb() {
		return carb;
	}
	
	public float getFiber() {
		return fiber;
	}
}
