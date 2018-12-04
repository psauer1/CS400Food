package application;

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
	public Food(String name, int cal, int fat, int protein, int carb, int fiber) {
		this.name = name;
		this.cal = cal;
		this.fat = fat;
		this.protein = protein;
		this.carb = carb;
		this.fiber = fiber;
	}
	
	/*fields*/
	String name;
	int cal;
	int fat;
	int protein;
	int carb;
	int fiber;
	
	/*accessors*/
	public String getName() {
		return name;
	}
	
	public int getCal() {
		return cal;
	}
	
	public int getFat() {
		return fat;
	}
	
	public int getProtein() {
		return protein;
	}
	
	public int getCarb() {
		return carb;
	}
	
	public int getFiber() {
		return fiber;
	}
}
